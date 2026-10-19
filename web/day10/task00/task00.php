<?php

// Send plain text back to the browser.
echo "Hello from PHP!";

?>
<!-- For a POST request, PHP can read the submitted value using: -->

<?php

// Read the value sent in the POST request.
$name = $_POST["name"] ?? "";

// Send a response.
echo "Hello, " . htmlspecialchars($name, ENT_QUOTES, "UTF-8");

?>


<!-- GET and POST are not simply "safe" and "unsafe" versions of a request. 
GET is primarily for retrieving information, 
while POST is commonly used to submit data that may change server-side state. 
POST does not automatically encrypt information; HTTPS is important for protecting data in transit. -->