
<?php

function calc_average(array $numbers): float
{
    // array_sum() adds all the numbers in the array
    // count() returns the number of elements
    // round() keeps one digit after the decimal point
    return round(array_sum($numbers) / count($numbers), 1);
}

?>
