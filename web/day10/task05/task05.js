
"use strict";
// JS handles the button click, sends the request, reads the JSON response, and displays the products
// Find the form and the HTML elements we need to update
const form = document.querySelector("#search-form");
const errorMessage = document.querySelector("#error-message");
const results = document.querySelector("#results");

// Listen for the form submission (when the user clicks Search)
form.addEventListener("submit", async function (event) {
    // Prevent the browser from refreshing the page letting JS manage the search instead
    event.preventDefault();

    // IMPORTANT: clear previous results before starting a new search
    results.replaceChildren();

    // Clear the previous error message
    errorMessage.textContent = "";

    // Read the values entered in the four fields
    const type = document.querySelector("#type").value.trim();
    const brand = document.querySelector("#brand").value.trim();
    const price = document.querySelector("#price").value.trim();
    const number = document.querySelector("#number").value.trim();

    // URLSearchParams safely encodes values for a GET request
    // This prepares the input values for a URL
    // a request might look like : 
    // task05.php?type=phone&brand=Samsung&price=%3E100&number=2
    // Here %3E represents the > symbol. -> PHP automatically decodes it when reading $_GET["price"]
    // For example, a price condition like ">100" is URL-encoded
    const params = new URLSearchParams({
        type: type,
        brand: brand,
        price: price,
        number: number
    });

    try {
        // Ask PHP to search the database
        const response = await fetch("task05.php?" + params.toString()); 
        // fetch() sends the HTTP request 
        // await waits for the server's response

        // PHP must return JSON, even when the request fails
        const data = await response.json();
        // response.json() parses the JSON text into a JS object

        // Check HTTP errors, such as status 400 or 500
        //An HTTP error doesn't automatically make fetch() throw an error. That's why we check response.ok ourselves
        if (!response.ok) {
            throw new Error(data.error || "The request failed.");
        }

        // A valid HTTP response can still contain a search error
        if (!data.success) {
            errorMessage.textContent = data.error;
            return;
        }

        // Display every product returned by PHP
        data.products.forEach(function (product) {
            // Create a new table row
            const row = document.createElement("tr");

            // Each row must contain exactly five cells:
            // type, brand, price, requested number, and stock
            const values = [
                product.type,
                product.brand,
                product.price,
                number,
                product.stock
            ];

            // forEach() and creating table rows
            // PHP will return an array of products.
            // JS loops through that array and creates 1 <tr> for each product, containing 5 <td> cells. 
            // Using textContent also avoids treating database values as HTML

            values.forEach(function (value) {
                const cell = document.createElement("td");

                // textContent displays text safely instead of interpreting database values as HTML
                cell.textContent = value;

                row.appendChild(cell);
            });

            // Add the completed row to the table
            results.appendChild(row);
        });

    } catch (error) {
        // This catches network problems, invalid JSON, and HTTP errors thrown above
        errorMessage.textContent =
            "Error: the search request failed. Please try again.";

        console.error("Search failed:", error);
    }
});
