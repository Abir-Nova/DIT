<?php
// This file handles the database. It has two modes:
// action=list: read messages and return them as JSON
// action=send: validate and save a new message, then return a JSON confirmation
// The SQLite table will be created automatically if it doesn't exist

// Tell the browser that all responses are JSON
header("Content-Type: application/json; charset=utf-8");

// Return JSON and stop the script
function sendResponse(array $data, int $status = 200): void
{
    http_response_code($status);
    echo json_encode($data, JSON_UNESCAPED_UNICODE);
    exit;
}

// Determine which operation JavaScript is requesting
$action = $_GET["action"] ?? "";

// Use a separate database for the chat
// __DIR__ means the folder containing this PHP file
$dbPath = __DIR__ . "/chat.db";

try {
    // Connect to SQLite
    // SQLite creates the file if it does not already exist
    $pdo = new PDO("sqlite:" . $dbPath);

    // Throw exceptions when database operations fail
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Create the messages table the first time the script runs
    $pdo->exec("
        CREATE TABLE IF NOT EXISTS messages (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            message TEXT NOT NULL,
            created_at TEXT NOT NULL
        )
    ");

    // ACTION 1: GET THE EXISTING MESSAGES
    if ($action === "list") {
        // Only retrieve the most recent 100 messages
        // The inner query finds the newest 100 records
        // The outer query displays those records oldest to newest
        $sql = "
            SELECT id, name, message, created_at
            FROM (
                SELECT id, name, message, created_at
                FROM messages
                ORDER BY id DESC
                LIMIT 100
            )
            ORDER BY id ASC
        ";
        //ASC means ascending, so older messages appear before newer ones

        $query = $pdo->query($sql);

        // Fetch every returned row as an associative array
        $messages = $query->fetchAll(PDO::FETCH_ASSOC);

        // Send the message array back as JSON
        sendResponse([
            "success" => true,
            "messages" => $messages
        ]);
    }

    // JavaScript reads data.messages and creates the visible message bubbles. PHP doesn't return a rendered HTML page

    // ACTION 2: SAVE A NEW MESSAGE

    if ($action === "send") {
        // Only accept POST requests for sending messages
        if ($_SERVER["REQUEST_METHOD"] !== "POST") {
            sendResponse([
                "success" => false,
                "error" => "Sending messages requires a POST request."
            ], 405);
        }

        // Read the submitted form values
        $rawName = $_POST["name"] ?? "";
        $rawMessage = $_POST["message"] ?? "";

        // Make sure both values are strings
        if (!is_string($rawName) || !is_string($rawMessage)) {
            sendResponse([
                "success" => false,
                "error" => "Invalid message data."
            ], 400);
        }

        // Remove spaces at the beginning and end
        $name = trim($rawName);
        $message = trim($rawMessage);

        // Reject empty names and messages
        if ($name === "" || $message === "") {
            sendResponse([
                "success" => false,
                "error" => "Name and message cannot be empty."
            ], 400);
        }

        // Limit the amount of text stored in the database
        if (strlen($name) > 50) {
            sendResponse([
                "success" => false,
                "error" => "The name must be 50 characters or fewer."
            ], 400);
        }

        if (strlen($message) > 1000) {
            sendResponse([
                "success" => false,
                "error" => "The message must be 1000 characters or fewer."
            ], 400);
        }

        // Create a UTC timestamp for the message
        $createdAt = gmdate("Y-m-d H:i:s");

        // Use a prepared statement to safely insert the values
        $insert = $pdo->prepare("
            INSERT INTO messages (name, message, created_at)
            VALUES (:name, :message, :created_at)
        ");

        $insert->execute([
            "name" => $name,
            "message" => $message,
            "created_at" => $createdAt
        ]);

        //The values are passed separately from the SQL statement. 
        // This is a prepared statement, which is safer than concatenating user input into SQL

        // Tell JavaScript the message was saved successfully
        sendResponse([
            "success" => true,
            "message" => "Message saved successfully.",
            "id" => (int) $pdo->lastInsertId()
        ]);
    }

    // If the action is missing or unknown, return an error
    sendResponse([
        "success" => false,
        "error" => "Unknown action. Use 'list' or 'send'."
    ], 400);

} catch (PDOException $e) {
    // Record technical details in the PHP server log
    error_log($e->getMessage());

    // Send a safe JSON error to the browser
    sendResponse([
        "success" => false,
        "error" => "A database error occurred."
    ], 500);
}
