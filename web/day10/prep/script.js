// Find the button in the HTML
const button = document.querySelector("#load-button");

// Find the element where we will display the response
const result = document.querySelector("#result");

// React to the user's click
button.addEventListener("click", function () {

    // For now, test the page without making a server request
    result.textContent = "Your AJAX practice starts here!";
});