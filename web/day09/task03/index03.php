<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Task 03</title>
</head>
<body>
    <?php
        // Load the file containing the dynamic_body() function
        require_once __DIR__ . "/task03.php";

        // The function reads the "page" value from the URL
        $body = dynamic_body();

        // Display the HTML returned by the function
        echo $body;
    ?>
</body>
</html>
