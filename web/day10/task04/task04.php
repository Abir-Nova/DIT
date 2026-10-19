<?php

// Tell the browser that our response contains JSON
// JavaScript will read this JSON using response.json()
header("Content-Type: application/json; charset=utf-8");

// Read the values sent by JavaScript through the URL (GET request)
// If a value wasn't sent, use an empty string
$rawType = $_GET["type"] ?? "";
$rawBrand = $_GET["brand"] ?? "";

// Make sure both values are strings before using string functions
// This also prevents errors if someone sends type[]=phone
if (!is_string($rawType) || !is_string($rawBrand)) {
    http_response_code(400);

    echo json_encode([
        "error" => "Invalid input."
    ]);
    exit;
}

// Remove unnecessary spaces at the beginning and end
$type = trim($rawType);
$brand = trim($rawBrand);

// This function sends our PHP results back to JavaScript
// The array becomes a JSON object
function sendResponse(array $data, int $status = 200): void
{
    http_response_code($status);

    echo json_encode($data, JSON_UNESCAPED_UNICODE);
    exit;
}

// The database file is in the same folder as this PHP file
$dbPath = __DIR__ . "/ajax_products.db";

try {
    // Connect to the existing SQLite database
    $pdo = new PDO("sqlite:" . $dbPath);

    // Make PDO throw an exception if a database query fails
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Search for a product type, ignoring uppercase/lowercase differences
    // Example: "PHONE" and "phone" are treated as the same type
    $typeQuery = $pdo->prepare(
        "SELECT 1
         FROM products
         WHERE LOWER(type) = LOWER(:type)  
         LIMIT 1"
    );
    //prepare() creates a prepared SQL statement with a placeholder, :type
    // case insensitive in SQL : WHERE LOWER(type) = LOWER(:type)  -> converts both sides to lowercase for comparison 
    // This is also why the PHP code uses the i flag in its regular expressions:
    // preg_match('/^[a-z-]+$/i', $type)
    // The i means case-insensitive matching, allowing both uppercase and lowercase letters

    // Search for a brand in the products table, also ignoring case
    $brandQuery = $pdo->prepare(
        "SELECT 1
         FROM products
         WHERE LOWER(brand) = LOWER(:brand)
         LIMIT 1"
    );

    // Initial messages shown when the fields are empty
    $typeMessage = "No type sent yet!";
    $brandMessage = "No brand sent yet!";

    // These booleans tell JavaScript which messages should be green
    $typeValid = false;
    $brandValid = false;

    // We start by assuming the type hasn't been found
    $typeExists = false;

    // PART 1: VALIDATE THE PRODUCT TYPE

    if ($type !== "") {
        $length = strlen($type);

        // The type must contain at least 3 characters
        if ($length < 3) {
            $typeMessage =
                "$type: this type does not have enough characters.";

        // The type must contain no more than 10 characters
        } elseif ($length > 10) {
            $typeMessage =
                "$type: this type has too many characters.";

        // Only letters and hyphens are allowed
        } elseif (!preg_match('/^[a-z-]+$/i', $type)) {
            $typeMessage =
                "$type: this type has non-alphabetical characters (different from '-').";
        // The i means case-insensitive matching, allowing both uppercase and lowercase letters
        } else {
            // The format is correct, so check the database
            $typeQuery->execute(["type" => $type]);
            // provides the user's value separately 
            // This is safer than inserting the input directly into an SQL string

            // fetchColumn() returns false when no matching row exists
            $typeExists = $typeQuery->fetchColumn() !== false;

            // The query uses SELECT 1 because we only need to know whether a matching row exists, not retrieve all its information
            // If a row exists, fetchColumn() returns the value 1
            // If no matching row exists, it returns false
            // We turn that into a Boolean: true or false

            if (!$typeExists) {
                $typeMessage =
                    "$type: this type doesn't exist in our shop.";
            } else {
                $typeMessage =
                    "$type: this type exists in databases.";

                // The type has valid characters and exists in the DB
                $typeValid = true;
            }
        }
    }

    // PART 2: VALIDATE THE BRAND

    if ($brand !== "") {
        $length = strlen($brand);

        // The brand must contain at least 2 characters
        if ($length < 2) {
            $brandMessage =
                "$brand: this brand does not have enough characters.";

        // The brand must contain no more than 20 characters
        } elseif ($length > 20) {
            $brandMessage =
                "$brand: this brand has too many characters.";

        // Letters, numbers, hyphens and ampersands are allowed
        } elseif (!preg_match('/^[a-z0-9&-]+$/i', $brand)) {
            $brandMessage =
                "$brand: this brand has invalid characters.";

        } else {
            // The brand's format is correct
            // Now check whether that brand already exists
            $brandQuery->execute(["brand" => $brand]);

            $brandExists = $brandQuery->fetchColumn() !== false;

            if ($brandExists) {
                $brandMessage =
                    "$brand: this brand already exists in databases.";

            } elseif ($type === "") {
                // We cannot confirm the brand for a type
                // until the user enters a product type
                $brandMessage = "No type sent yet!";

            } elseif (!$typeValid) {
                // The type is missing from the database
                // or failed its own validation rules
                $brandMessage =
                    "$brand: the type $type is not valid.";

            } else {
                // The brand's format is valid, the brand isn't
                // already in the database, and the type is valid
                $brandMessage =
                    "$brand: this brand is valid for the type $type.";

                $brandValid = true;
            }
        }
    }

    // Send both messages in a single JSON response
    // JavaScript updates both messages after each field changes
    sendResponse([
        "typeMessage" => $typeMessage,
        "typeValid" => $typeValid,
        "brandMessage" => $brandMessage,
        "brandValid" => $brandValid
    ]);
    //PHP sends both validation messages and their status flags
    //JS reads the response and updates the two message elements. It doesn't need to know how PHP queried SQLite

} catch (PDOException $e) {
    // Keep technical database details in the server logs,
    // rather than exposing them directly in the browser
    error_log($e->getMessage());

    sendResponse([
        "error" => "A database error occurred."
    ], 500);
}
