<?php

    // This is the backend. Its job is to receive the name, create a JSON object, and send it back to the browser

    // Tell the browser that the response contains JSON data
    // This header must be sent before any response body output
    header("Content-Type: application/json; charset=utf-8");

    // Read the "name" query parameter sent by the GET request
    // The null coalescing operator (??) provides an empty string if the parameter does not exist
    $name = $_GET["name"] ?? "";

    // Make sure the value is a string before using it -> avoids problems if an unexpected parameter is supplied
    if (!is_string($name)) {
        http_response_code(400);

        echo json_encode([
            "error" => "Invalid name parameter."
        ]);

        exit;
    }

    // Build the response as a PHP associative array
    // "name" is the JSON key - $name is its value
    $response = [
        "name" => $name
    ];

    // Convert the PHP array into a JSON string
    // echo sends the response body back to the browser
    // Convert the PHP array to JSON.
    $json = json_encode($response, JSON_UNESCAPED_UNICODE);

    // Detect a JSON encoding failure
    if ($json === false) {
        http_response_code(500);

        echo json_encode([
            "error" => "JSON encoding failed.",
            "details" => json_last_error_msg()
        ]);

        exit;
    }

    // Send the JSON response.
    echo $json;

?>