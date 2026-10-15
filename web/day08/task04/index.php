
<?php

// Load the file containing the calc() function
// The function becomes available to this file
require("task04.php");

// TEST 1: Multiplication
// "*" is valid, so 7 * 6 returns 42
var_dump(calc("*", 7, 6));

// TEST 2: Division
// "/" is valid, and the second number isn't zero
// 4 / 3 produces a floating-point result
var_dump(calc("/", 4, 3));

// TEST 3: Invalid operator
// "+/" isn't one of the five supported operators
var_dump(calc("+/", 4, 2));

// TEST 4: Division by zero
// "/" is valid, but the second number is zero
var_dump(calc("/", 4, 0));

?>
