
<?php

// This function takes no arguments
// It reads the submitted name and age from the POST request,
// then prints the appropriate greeting
function whoami(): void
{
    // Check whether "name" was sent and is a string
    // A request could contain an unexpected value such as name[]=Jane.
    $name = "";
    if (isset($_POST["name"]) && is_string($_POST["name"])) {
        // trim() removes spaces at the beginning and end
        $name = trim($_POST["name"]);
    }

    // Check whether "age" was sent as a string.
    $age = null;
    if (isset($_POST["age"]) && is_string($_POST["age"])) {
        // FILTER_VALIDATE_INT checks whether the value is an integer
        $validatedAge = filter_var($_POST["age"], FILTER_VALIDATE_INT);

        // Accept only non-negative integers
        if ($validatedAge) {
            $age = $validatedAge;
        }
    }

    // Escape the name before displaying it in HTML
    // This helps prevent HTML or JavaScript entered as a name
    // from being interpreted by the browser
    $safeName = htmlspecialchars($name, ENT_QUOTES, "UTF-8");

    // Case 1: We have both a name and a valid age.
    if ($name !== "" && $age !== null) {
        echo "Hi, my name is " . $safeName
            . " and I'm " . $age . " years old.";
    }
    // Case 2: We have a valid age, but no name.
    elseif ($name === "" && $age !== null) {
        echo "Hi, I have no name and I'm " . $age . " years old.";
    }
    // Case 3: We have a name, but no valid age.
    elseif ($name !== "" && $age === null) {
        echo "Hi, my name is " . $safeName . ".";
    }
    // Case 4: We have neither a name nor a valid age.
    else {
        echo "Hi, I have no name.";
    }
}
