
<?php

// This function does not receive an argument
// It gets the page name directly from the URL using $_GET
function dynamic_body(): string
{
    // Check whether the URL contains a parameter named "page"
    // Example: index03.php?page=home
    // If "page" is missing, return the required error message
    if (!isset($_GET["page"])) {
        //isset() checks whether a variable or array entry exists and is not null
        return "<p>Unknown page</p>";
    }

    // Retrieve the value of the "page" parameter from the URL
    $page = $_GET["page"];
    //$_GET["page"] reads the page choice from the URL

    // Make sure the value is a string
    // This also safely rejects unusual URLs such as ?page[]=home
    if (!is_string($page)) {
        return "<p>Unknown page</p>";
    }

    // Select the HTML file that corresponds to the requested page
    if ($page === "home") {
        $file = __DIR__ . "/home.html";
    } 
    elseif ($page === "php") {
        $file = __DIR__ . "/php.html";
    } 
    elseif ($page === "sql") {
        $file = __DIR__ . "/sql.html";
    } 
    else {
        // The page name is not one of the three allowed values.
        return "<p>Unknown page</p>";
    }

    // Read the selected HTML file and store its content as a string
    $body = file_get_contents($file);
    //file_get_contents() reads the matching HTML file

    // If PHP cannot read the file, return the fallback message
    if ($body === false) {
        return "<p>Unknown page</p>";
    }

    // Send the HTML content back to the code that called this function
    return $body;
}
