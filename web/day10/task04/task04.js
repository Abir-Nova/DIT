// This file listens to changes in both inputs and sends their current values to PHP using fetch()

"use strict";

// Find the two input fields
const typeInput = document.querySelector("#type");
const brandInput = document.querySelector("#brand");

// Find the two elements that display validation messages
const typeMessage = document.querySelector("#type-message");
const brandMessage = document.querySelector("#brand-message");

// Display a message and choose its color
// isValid = true  -> green
// isValid = false -> red
function displayMessage(element, message, isValid) {
    element.textContent = message;

    // Remove the previous color class before applying the new one
    element.classList.remove("error", "valid");

    // Add the appropriate class
    element.classList.add(isValid ? "valid" : "error");
}

// Ask PHP to validate both fields
async function validateFields() {
    // Read the current values each time the function runs
    const type = typeInput.value;
    const brand = brandInput.value;

    // URLSearchParams safely encodes the values for a GET query string
    const params = new URLSearchParams({
        type: type,
        brand: brand
    });

    // A GET request sends values in the URL
    // URLSearchParams encodes characters correctly and builds a query string

    try {
        // Send a GET request to PHP with both current field values
        const response = await fetch("task04.php?" + params.toString());

        // If PHP returns an HTTP error, stop and report the failure
        if (!response.ok) {
            throw new Error("Server returned HTTP " + response.status);
        }

        // Convert the JSON response into a JavaScript object
        const data = await response.json();

        // Why use await response.json()?
        // PHP will return the two validation messages and their validity flags as JSON 
        // response.json() parses that response into a JavaScript object


        // Update the type message and its color
        displayMessage(
            typeMessage,
            data.typeMessage,
            data.typeValid
        );

        // Update the brand message and its color
        displayMessage(
            brandMessage,
            data.brandMessage,
            data.brandValid
        );

    } catch (error) {
        // Log technical details for debugging
        console.error("Validation request failed:", error);

        // Show a visible error if the server cannot be reached
        displayMessage(
            typeMessage,
            "Could not validate the type. Check the server.",
            false
        );

        displayMessage(
            brandMessage,
            "Could not validate the brand. Check the server.",
            false
        );
    }
}

// Validate whenever the user types, deletes, or pastes text
// The "input" event fires as the field's value changes
typeInput.addEventListener("input", validateFields);
brandInput.addEventListener("input", validateFields);

// Show the initial messages when the page opens
validateFields();

//input versus change

// We use the input event because the task requires real-time updates. 
// It fires as the value changes while the user types, deletes or pastes text. 
// The change event usually fires later, when the value is committed, such as when a text input loses focus.


// Why send both fields in every request?
// The brand's validity depends on the product type
// When the type changes, the brand message might also need to change
// Sending both values ensures PHP can validate the relationship between them