// Find the button and the paragraph in the HTML.
const button = document.querySelector("#load-button");
const result = document.querySelector("#result");

// Wait for the user to click the button.
button.addEventListener("click", function () {

    // Request the PHP script from the server.
    fetch("task00.php")

        // Read the response as plain text.
        .then(function (response) {
            return response.text();
        })

        // Use the text received from PHP.
        .then(function (data) {
            result.textContent = data;
        })

        // Handle errors, such as a network failure.
        .catch(function (error) {
            console.error("Request failed:", error);
            result.textContent = "Something went wrong.";
        });
});

// An alternative: async and await

// The async keyword allows us to use await inside this function.
async function loadMessage() {
    // Wait for the server's response.
    const response = await fetch("task00.php");

    // Wait for the response body to be read as text.
    const data = await response.text();

    // Display the result.
    console.log(data);
}

// Start the operation.
loadMessage();


// handling errors : 
// 200 OK: the request succeeded.
// 404 Not Found: the requested resource wasn't found.
// 500 Internal Server Error: something went wrong on the server.


fetch("task00.php")
    .then(response => {

        // Check whether the HTTP response was successful.
        if (!response.ok) {
            throw new Error("HTTP error: " + response.status);
        }

        // Read the response as text.
        return response.text();
    })
    .then(data => {
        console.log(data);
    })
    .catch(error => {
        // Handle network errors and errors thrown above.
        console.error(error);
    });