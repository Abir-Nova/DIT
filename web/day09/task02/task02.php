
<?php

// This function receives the name of the page we want to display
// Example: render_body("home")
function render_body(string $page): string
{
    // Choose the correct HTML file according to the page name.
    if ($page === "home") 
        $file = __DIR__ . "/home.html";
    elseif ($page === "php") 
        $file = __DIR__ . "/php.html";
    elseif ($page === "sql") 
        $file = __DIR__ . "/sql.html";
    else 
        // If the page name is not recognized, return this message
        return "<p>Unknown page</p>";

    // Read the HTML file and return its contents as a string
    return file_get_contents($file);
}
