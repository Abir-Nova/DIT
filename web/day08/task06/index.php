
<?php
    // Load the file containing the sequence() function
    require("task06.php");

    // Display a heading
    // PHP_EOL moves the cursor to the next line
    echo "Sequence 0:" . PHP_EOL;

    // Print the sequence through iteration 0
    sequence(0);

    // Display another heading
    echo "Sequence 3:" . PHP_EOL;

    // Print the sequence through iteration 3
    sequence(3);
?>
