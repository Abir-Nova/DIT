<?php

// Build the path to the database file in this folder
$dbPath = __DIR__ . "/ajax_products.db";

// Make sure we are inspecting the provided file
if (!file_exists($dbPath)) {
    exit("Database file not found: " . $dbPath);
}

try {
    // Open the existing SQLite database
    $pdo = new PDO("sqlite:" . $dbPath);

    // Ask SQLite which tables exist in this database
    $query = "
        SELECT name, sql
        FROM sqlite_master
        WHERE type = 'table'
        ORDER BY name
    ";

    $tables = $pdo->query($query)->fetchAll(PDO::FETCH_ASSOC);

    // Display the results for inspection
    echo "<pre>";
    print_r($tables);
    echo "</pre>";

} catch (PDOException $e) {
    echo "Database error: " . htmlspecialchars($e->getMessage());
}
