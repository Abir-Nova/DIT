"use strict";

// JS collects the values -> sends them to PHP, and displays the response without refreshing the page
// We'll use a POST request because we're creating or modifying database records

// Get the form and message element from the HTML
const form = document.querySelector("#product-form");
const message = document.querySelector("#message");

// Listen for the user submitting the form
form.addEventListener("submit", async function (event) {
    // Prevent the browser from reloading the page
    event.preventDefault();

    // Clear the previous result.
    message.textContent = "";
    message.className = "";

    // Read the four input values.
    const type = document.querySelector("#type").value.trim();
    const brand = document.querySelector("#brand").value.trim();
    const price = document.querySelector("#price").value.trim();
    const number = document.querySelector("#number").value.trim();

    // Prepare the values as form-encoded data
    // PHP will read them using $_POST
    const formData = new URLSearchParams({
        type: type,
        brand: brand,
        price: price,
        number: number
    });

    try {
        // Send the product information to PHP
        const response = await fetch("task06.php", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData.toString()
        });

        // Parse the JSON response from PHP
        const data = await response.json();

        // If PHP returned an HTTP error, display its message
        if (!response.ok) {
            throw new Error(data.message || "The request failed.");
        }

        // Display the result sent by PHP
        message.textContent = data.message;

        // Use green for success and red for an error
        message.classList.add(data.success ? "success" : "error");

    } catch (error) {
        // Handle network errors or unexpected server responses
        message.textContent =
            error.message || "Unable to contact the server.";

        message.classList.add("error");

        console.error("Save product error:", error);
    }
});
