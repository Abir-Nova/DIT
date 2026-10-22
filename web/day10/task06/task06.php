
<?php

// All responses from this endpoint are JSON
header("Content-Type: application/json; charset=utf-8");

// Send JSON and stop the script
function sendResponse(array $data, int $status = 200): void
{
    http_response_code($status);
    echo json_encode($data, JSON_UNESCAPED_UNICODE);
    exit;
}

// Only accept POST requests for this operation
if ($_SERVER["REQUEST_METHOD"] !== "POST") {
    sendResponse([
        "success" => false,
        "message" => "Please submit the form using POST."
    ], 405);
}

// Read submitted values
// Null coalescing supplies an empty string when a field is missing
$rawType = $_POST["type"] ?? "";
$rawBrand = $_POST["brand"] ?? "";
$rawPrice = $_POST["price"] ?? "";
$rawNumber = $_POST["number"] ?? "";

// Check that all submitted values are strings
if (
    !is_string($rawType) ||
    !is_string($rawBrand) ||
    !is_string($rawPrice) ||
    !is_string($rawNumber)
) {
    sendResponse([
        "success" => false,
        "message" => "Invalid form data."
    ], 400);
}

// Remove surrounding whitespace
$type = trim($rawType);
$brand = trim($rawBrand);
$price = trim($rawPrice);
$number = trim($rawNumber);

// 1. VALIDATE THE PRODUCT TYPE

// The type must be between 3 and 10 characters
if (strlen($type) < 3) {
    sendResponse([
        "success" => false,
        "message" => "Error ($type): this type does not have enough characters."
    ], 400);
}

if (strlen($type) > 10) {
    sendResponse([
        "success" => false,
        "message" => "Error ($type): this type has too many characters."
    ], 400);
}

// Only letters and hyphens are allowed
if (!preg_match('/^[a-z-]+$/i', $type)) {
    sendResponse([
        "success" => false,
        "message" => "Error ($type): this type has invalid characters."
    ], 400);
}

// 2. VALIDATE THE BRAND

// The brand must be between 2 and 20 characters
if (strlen($brand) < 2) {
    sendResponse([
        "success" => false,
        "message" => "Error ($brand): this brand does not have enough characters."
    ], 400);
}

if (strlen($brand) > 20) {
    sendResponse([
        "success" => false,
        "message" => "Error ($brand): this brand has too many characters."
    ], 400);
}

// Allow letters, digits, hyphens and ampersands
if (!preg_match('/^[a-z0-9&-]+$/i', $brand)) {
    sendResponse([
        "success" => false,
        "message" => "Error ($brand): this brand has invalid characters."
    ], 400);
}

// 3. VALIDATE PRICE AND STOCK

// Price must be a non-negative whole number
// Zero is allowed
if ($price === "" || !preg_match('/^[0-9]+$/', $price)) {
    sendResponse([
        "success" => false,
        "message" => "Error: price must be a non-negative whole number."
    ], 400);
}

// Stock must also be a non-negative whole number
// Zero is allowed here too
if ($number === "" || !preg_match('/^[0-9]+$/', $number)) {
    sendResponse([
        "success" => false,
        "message" => "Error: stock must be a non-negative whole number."
    ], 400);
}

// Convert validated numeric strings to integers

$priceValue = (int) $price;
$stockValue = (int) $number;

// 4. CONNECT TO THE EXISTING DATABASE

try {
    // task06 is next to task04, so go up one folder
    // and then into task04 to find the existing database
    $dbPath = dirname(__DIR__) . "/task04/ajax_products.db";

    $pdo = new PDO("sqlite:" . $dbPath);

    // Make PDO report database errors as exceptions
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Start a transaction so the check and write are grouped together
    $pdo->beginTransaction();

    // A transaction groups database operations together. 
    // If an exception occurs before the changes are committed, rollBack() undoes the uncommitted changes

    // 5. CHECK WHETHER THIS PRODUCT ALREADY EXISTS

    // We consider a product to be identified by its type + brand
    // LOWER() makes the comparison case-insensitive
    $checkQuery = $pdo->prepare(
        "SELECT id
         FROM products
         WHERE LOWER(type) = LOWER(:type)
           AND LOWER(brand) = LOWER(:brand)
         LIMIT 1"
    );

    $checkQuery->execute([
        "type" => $type,
        "brand" => $brand
    ]);

    // Fetch the existing product ID, or false if there is no match
    $existingId = $checkQuery->fetchColumn();

    if ($existingId !== false) {

        // 6A. PRODUCT EXISTS: UPDATE PRICE AND STOCK

        $updateQuery = $pdo->prepare(
            "UPDATE products
             SET price = :price,
                 stock = :stock
             WHERE id = :id"
        );

        $updateQuery->execute([
            "price" => $priceValue,
            "stock" => $stockValue,
            "id" => $existingId
        ]);

        $action = "updated";

    } else {

        // 6B. PRODUCT DOES NOT EXIST: INSERT A NEW ROW
        $insertQuery = $pdo->prepare(
            "INSERT INTO products (type, brand, price, stock)
             VALUES (:type, :brand, :price, :stock)"
        );

        $insertQuery->execute([
            "type" => $type,
            "brand" => $brand,
            "price" => $priceValue,
            "stock" => $stockValue
        ]);

        $action = "added";
    }

    // Commit saves the INSERT or UPDATE permanently
    $pdo->commit();

    // Send a success response to JS
    sendResponse([
        "success" => true,
        "message" => "Product $type / $brand successfully $action."
    ]);

} catch (PDOException $e) {
    // If something failed after the transaction began,
    // undo any uncommitted database changes
    if (isset($pdo) && $pdo->inTransaction()) {
        $pdo->rollBack();
    }

    // Keep technical details in the PHP server log
    error_log($e->getMessage());

    sendResponse([
        "success" => false,
        "message" => "Database error: the product could not be saved."
    ], 500);
}
