
<?php

// Connect to the existing SQLite database
$dbPath = __DIR__ . "/ajax_products.db";
$pdo = new PDO("sqlite:" . $dbPath);

// Retrieve every product from the database
// We select only the columns we want to inspect
$sql = "SELECT id, type, brand, price, stock FROM products ORDER BY id";

$products = $pdo->query($sql)->fetchAll(PDO::FETCH_ASSOC);

// Display the results as a readable HTML table
echo "<h1>Products in the database</h1>";
echo "<table border='1' cellpadding='8'>";
echo "<tr>
        <th>ID</th>
        <th>Type</th>
        <th>Brand</th>
        <th>Price</th>
        <th>Stock</th>
      </tr>";

// glass    fly 

foreach ($products as $product) {
    echo "<tr>";

    // Escape database values before displaying them in HTML
    foreach ($product as $value) {
        echo "<td>" . htmlspecialchars((string) $value) . "</td>";
    }

    echo "</tr>";
}

echo "</table>";
?>


