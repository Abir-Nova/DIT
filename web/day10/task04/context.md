Before writing code, let's translate the assignment into precise rules


1. Product type

* min 3 chars & max 10 chars
* letters & hyphens - only
* digits forbidden 
* must exist in the shop's db 
* case insensitive

2. Brand 
* min 2 chars & max 20 chars 
* letters , digits , hyphens , & : allowed
* other special char : forbidden 
* must not exist in the shop's db 
* case insensitive


3. We need to distinguish two kinds of validation:
- Format validation: Does the value follow the character and length rules?
- Database validation: Does the product type exist, or is the brand already registered?

4. Before coding: understand PDO and SQLite
* Our new backend will use PDO, which stands for PHP Data Objects 
* PDO gives PHP a consistent way to connect to databases and execute SQL queries 
* SQLite stores its database in a file, so we don't need to start a separate database server

5. Connect to the database

$pdo = new PDO("sqlite:" . __DIR__ . "/ajax_products.db");

* new PDO(...) creates a database connection
* sqlite: tells PDO to use the SQLite driver
* __DIR__ is the directory containing the current PHP file
* "/ajax_products.db" identifies the database file next to our PHP file 

// Using __DIR__ is safer than relying on the terminal's current directory. 
The path is based on the location of task04.php

//One important precaution: SQLite can create a new empty database if the path doesn't point to an existing file. That's why we should copy the provided database into the expected location, rather than accidentally querying a newly created empty database.


// we query the db using SQL 

SELECT ...
FROM ....
WHERE ....

Prepared statements are important because they let PDO pass input values separately from the SQL code, reducing the risk of SQL injection


6. Inspect our provided database first
Before completing task04.php, we need to confirm the real table and column names inside ajax_products.db. 

for that we can inspect the db using PHP itself

we'll create a temporary file named inspect_db.php inside task04