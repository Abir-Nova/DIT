
<?php

//How PHP creates JSON
//In PHP, you can use json_encode() to convert a PHP value into JSON text.

// Create an associative PHP array.
$user = [
    "name" => "Abir",
    "age" => 20,
    "student" => true
];

// PHP uses => to associate array keys with values
// That's different from JavaScript's : syntax for object properties

// Convert the PHP array into JSON and send it to the browser.
header("Content-Type: application/json; charset=utf-8"); //tells the browser that the response contains JSON
echo json_encode($user); // convert the info to json text
// echo : sends the result in the HTTP response body.

?>