<?php

// Tell the browser that our response body will contain JSON -> before sending any output
header("Content-Type: application/json; charset=utf-8");

// Read the email submitted through the POST request
// If the field is missing, use an empty string
$email = $_POST["email"] ?? "";

// Check that the submitted value is a string
// For example, email[]=abc would produce an array, not a string
if (!is_string($email)) {
    http_response_code(400);

    echo json_encode([
        "valid" => false,
        "message" => "Invalid email input."
    ]);

    exit;
}

// Validate the email format using PHP's built-in filter
if (filter_var($email, FILTER_VALIDATE_EMAIL) === false) {
    // Tell the client that the request contains invalid data
    http_response_code(400);

    echo json_encode([
        "valid" => false,
        "message" => "The email address is invalid."
    ]);

    exit;
}

// If the code reaches this point, the email passed validation
// HTTP 200 OK is the default status when no error is set
echo json_encode([
    "valid" => true,
    "message" => "The email address is valid."
]);
