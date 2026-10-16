
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Task 02</title>
</head>
<body>
    <?php
        // Load the file that defines render_body()
        require_once __DIR__ . "/task02.php";

        // Ask the function to read home.html
        $body = render_body("home");

        // Display the HTML content returned by the function
        echo $body;
    ?>
</body>
</html>
