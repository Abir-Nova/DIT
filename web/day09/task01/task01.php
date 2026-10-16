
<?php

function display_menu(): string
{
    // Store the HTML menu inside a PHP string
    $menu = "
        <ul>     
            <li><a href=\"home.php\">Home</a></li>
            <li><a href=\"product.php\">Products</a></li>
            <li><a href=\"about.php\">About Us</a></li>
            <li><a href=\"contact.php\">Contact</a></li>
        </ul>
    ";

    // Return the HTML string to whoever calls the function.
    // We use return, NOT echo, because the task asks for a returned string.
    return $menu;
}
