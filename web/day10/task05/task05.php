<?php

// Every response from this file must be JSON, never an HTML page
header("Content-Type: application/json; charset=utf-8");

// Send a JSON response and stop executing the script
function sendResponse(array $data, int $status = 200): void
{
    http_response_code($status);
    echo json_encode($data, JSON_UNESCAPED_UNICODE);
    exit;
}

// Read the four GET parameters
// An absent parameter becomes an empty string
$rawType = $_GET["type"] ?? "";
$rawBrand = $_GET["brand"] ?? "";
$rawPrice = $_GET["price"] ?? "";
$rawNumber = $_GET["number"] ?? "";

// Check that all inputs are strings before using string functions
if (
    !is_string($rawType) ||
    !is_string($rawBrand) ||
    !is_string($rawPrice) ||
    !is_string($rawNumber)
) {
    sendResponse([
        "success" => false,
        "error" => "Error: invalid input."
    ], 400);
}

// Remove unnecessary spaces around the entered values
$type = trim($rawType);
$brand = trim($rawBrand);
$price = trim($rawPrice);
$number = trim($rawNumber);

// 1. VALIDATE THE PRODUCT TYPE

// Check the minimum length
if (strlen($type) < 3) {
    sendResponse([
        "success" => false,
        "error" => "Error ($type): this type does not have enough characters."
    ]);
}

// Check the maximum length
if (strlen($type) > 10) {
    sendResponse([
        "success" => false,
        "error" => "Error ($type): this type has too many characters."
    ]);
}

// Allow letters and hyphens only
// The i flag makes the letter check case-insensitive
if (!preg_match('/^[a-z-]+$/i', $type)) {
    sendResponse([
        "success" => false,
        "error" => "Error ($type): this type has non-alphabetical characters (different from '-')."
    ]);
}

// 2. VALIDATE THE BRAND

if (strlen($brand) < 2) {
    sendResponse([
        "success" => false,
        "error" => "Error ($brand): this brand does not have enough characters."
    ]);
}

if (strlen($brand) > 20) {
    sendResponse([
        "success" => false,
        "error" => "Error ($brand): this brand has too much characters."
    ]);
}

// Brands may contain letters, digits, hyphens and ampersands &
if (!preg_match('/^[a-z0-9&-]+$/i', $brand)) {
    sendResponse([
        "success" => false,
        "error" => "Error ($brand): this brand has invalid characters."
    ]);
}

// 3. VALIDATE THE PRICE CONDITION

// The complete condition must be 2 to 5 characters long
if (strlen($price) < 2) {
    sendResponse([
        "success" => false,
        "error" => "Error ($price): this price does not have enough characters."
    ]);
}

if (strlen($price) > 5) {
    sendResponse([
        "success" => false,
        "error" => "Error ($price): this price has too many characters."
    ]);
}

// The first character must be >, < or =
// Every remaining character must be a digit
if (!preg_match('/^[><=][0-9]+$/', $price)) {
    sendResponse([
        "success" => false,
        "error" => "Error ($price): we cannot define a price - string invalid."
    ]);
}

// Separate the comparison operator from the number
// Example: ">100" becomes operator ">" and value "100"
$operator = substr($price, 0, 1);
$priceValue = (int) substr($price, 1);

// Map the validated operator to a fixed SQL operator
// Never put an unvalidated user-provided operator into SQL
$sqlOperators = [
    ">" => ">",
    "<" => "<",
    "=" => "="
];

$sqlOperator = $sqlOperators[$operator];

// 4. VALIDATE THE REQUESTED QUANTITY

// Only digits are allowed. This rejects decimals and negative signs
if (!preg_match('/^[0-9]+$/', $number) || (int) $number <= 0) {
    sendResponse([
        "success" => false,
        "error" => "Error ($number): not a positive number."
    ]);
}

$requestedNumber = (int) $number;

// 5. CONNECT TO THE EXISTING SQLITE DATABASE

try {
    // The database is stored in the task04 folder.
    $dbPath = dirname(__DIR__) . "/task04/ajax_products.db";

    $pdo = new PDO("sqlite:" . $dbPath);

    // Make database problems throw exceptions
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Check whether the product type exists
    $typeQuery = $pdo->prepare(
        "SELECT 1
         FROM products
         WHERE LOWER(type) = LOWER(:type)
         LIMIT 1"
    );

    $typeQuery->execute(["type" => $type]);

    if ($typeQuery->fetchColumn() === false) {
        sendResponse([
            "success" => false,
            "error" => "Error ($type): this type doesn't exist in our shop."
        ]);
    }

    // Check whether the brand exists anywhere in the database
    $brandQuery = $pdo->prepare(
        "SELECT 1
         FROM products
         WHERE LOWER(brand) = LOWER(:brand)
         LIMIT 1"
    );

    $brandQuery->execute(["brand" => $brand]);

    if ($brandQuery->fetchColumn() === false) {
        sendResponse([
            "success" => false,
            "error" => "Error ($brand): this brand doesn't exist in our database."
        ]);
    }

    // 6. SEARCH FOR PRODUCTS MATCHING TYPE, BRAND AND PRICE

    // The SQL operator comes from our fixed allowlist
    // All user-provided values remain prepared parameters
    $sql = "
        SELECT type, brand, price, stock
        FROM products
        WHERE LOWER(type) = LOWER(:type)
          AND LOWER(brand) = LOWER(:brand)
          AND price $sqlOperator :price
    ";

    $searchQuery = $pdo->prepare($sql);

    $searchQuery->execute([
        "type" => $type,
        "brand" => $brand,
        "price" => $priceValue
    ]);

    // Fetch all products matching the search criteria
    $products = $searchQuery->fetchAll(PDO::FETCH_ASSOC);

    // No products matched type + brand + price
    if (count($products) === 0) {
        sendResponse([
            "success" => false,
            "error" => "Error ($price): no products found at this price."
        ]);
    }

    // 7. CHECK AVAILABLE STOCK

    // Keep only products that have enough units in stock
    $availableProducts = [];

    foreach ($products as $product) {
        if ((int) $product["stock"] >= $requestedNumber) {
            $availableProducts[] = $product;
        }
    }

    // If matching products exist but none has enough stock,
    // report the stock of the first matching product
    if (count($availableProducts) === 0) {
        $stock = (int) $products[0]["stock"];

        sendResponse([
            "success" => false,
            "error" => "Error ($number): sorry, we don't have enough stock, we only have $stock in stock."
        ]);
    }

    // 8. SEND THE RESULTS BACK AS JSON

    // Do not return HTML, JS builds the table
    sendResponse([
        "success" => true,
        "products" => $availableProducts
    ]);

} catch (PDOException $e) {
    // Log the technical details for debugging
    error_log($e->getMessage());

    // Send a readable JSON error to the browser
    sendResponse([
        "success" => false,
        "error" => "Error: the database search failed."
    ], 500);
}
