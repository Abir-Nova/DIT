
<?php

// Check whether the user has submitted the form
// The function returns true or false, not printed text
function form_is_submitted(): bool
{
    // The form sends data using POST
    // We also check for the named submit button
    return $_SERVER["REQUEST_METHOD"] === "POST"
        && isset($_POST["submit"])
        && is_string($_POST["submit"]);
}


// Process the submitted information and print a greeting
// This function takes no arguments because it reads $_POST directly
function whoami(): void
{    
    // STEP 1: Read and prepare the name

    // Start with an empty name in case it was not submitted
    $name = "";

    // Only accept the name if it exists and is a string.
    if (isset($_POST["name"]) && is_string($_POST["name"])) {
        // Remove unnecessary spaces from the beginning and end
        $name = trim($_POST["name"]);
    }

    // STEP 2: Read and validate the age
    
    // null means that the age is missing or invalid.
    $age = null;

    // Check that the age field exists and contains a string.
    if (isset($_POST["age"]) && is_string($_POST["age"])) {
        // Convert a valid integer string into an integer.
        $validatedAge = filter_var(
            $_POST["age"],
            FILTER_VALIDATE_INT
        );

        // Accept only whole numbers that are greater than 0
        if ($validatedAge) {
            $age = $validatedAge;
        }
    }
    // STEP 3: Read and validate the curriculum

    // Each key is the value sent by the HTML form
    // Each value is the readable curriculum name to display
    $curriculums = [
        "pge" => "PGE (Programme Grande Ecole)",
        "bachelor" => "Bachelor",
        "msc" => "MSc Pro",
        "coding" => "Coding Academy",
        "wac" => "Web@cademie"
    ];

    // No curriculum is selected by default
    $curriculum = "";

    // Only process the curriculum if it was submitted as a string
    if (
        isset($_POST["curriculum"])
        && is_string($_POST["curriculum"])) 
    {
        $submittedCurriculum = $_POST["curriculum"];

        // Accept only values listed in our allowed curriculum array
        if (isset($curriculums[$submittedCurriculum])) {
            $curriculum = $curriculums[$submittedCurriculum];
        }
    }

    // STEP 4: Prepare the name for safe display 

    // Convert special HTML characters into safe HTML entities
    // This prevents a submitted name from being interpreted as HTML
    $safeName = htmlspecialchars($name, ENT_QUOTES, "UTF-8");

    // STEP 5: Build the first sentence

    // Decide which greeting to print based on the name and age

    if ($name !== "" && $age !== null) {
        // Both the name and age are valid.
        $message = "Hi, my name is " . $safeName . " and I'm " . $age . " years old.";
    } 
    elseif ($name === "" && $age !== null) {
        // The age is valid, but the name is missing
        $message = "Hi, I have no name and I'm " . $age . " years old.";
    } 
    elseif ($name !== "" && $age === null) {
        // The name exists, but the age is missing or invalid
        $message = "Hi, my name is " . $safeName . ".";
    } 
    else {
        // Neither a name nor a valid age was provided.
        $message = "Hi, I have no name.";
    }

    // STEP 6: Add the curriculum sentence if selected

    if ($curriculum !== "") {
        // Add the new sentence after the first greeting.
        $message .= " I'm a student of " . htmlspecialchars($curriculum, ENT_QUOTES, "UTF-8") . ".";
    }
    
    // Print the completed greeting.
    echo $message;
}
