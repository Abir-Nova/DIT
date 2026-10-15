<?php

    $name="abir";
    $age=22;

?>

// The $ is part of the variable's name in PHP. 
// You must include it whenever you refer to that variable.

$name = "Abir";
echo $name;


// functions 

<?php

function sayHello(string $name) {
    echo "Hello " . $name . "!";
}

sayHello("Maria");

?>

// so here the dot . joins strings together. In PHP, this is called string concatenation.

// echo displays output.
// return sends a value back to the code that called the function.

<?php

$words = array("chou", "be", "do");

$words = ["chou", "be", "do"];

?>


<?php

echo strlen("chou"); // Prints 4
echo "\n";

echo strlen("be");   // Prints 2
echo "\n";

echo strlen("a");    // Prints 1
echo "\n";

?>
loop through an array

$words = array("chou", "be", "do");

methode 1 : 

foreach ($words as $word) {
    echo $word . "\n";
}

as: "For each element in $words, temporarily call that element $word."


methode 2 : 

$words = array("chou", "be", "do");

for ($i = 0; $i < count($words); $i++) {
    echo $words[$i] . "\n";
}


var_dump() is a debugging tool that displays a value along with its type and, for strings, their length