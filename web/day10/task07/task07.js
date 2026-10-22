"use strict";

// with AJAX : 
// Send messages when the form is submitted
// Retrieve messages automatically every four seconds

// Find the elements we need to interact with
const chatForm = document.querySelector("#chat-form");
const nameInput = document.querySelector("#name");
const messageInput = document.querySelector("#message");
const messagesContainer = document.querySelector("#messages");
const feedback = document.querySelector("#feedback");
const sendButton = document.querySelector("#send-button");
const connectionStatus = document.querySelector("#connection-status");

// Remember the last message ID displayed
// This helps us avoid rebuilding the chat when nothing has changed
let lastMessageId = -1;

// 1. DISPLAY THE MESSAGES

function displayMessages(messages) {
    // If the newest message ID is unchanged, leave the chat alone  
    const newestId = messages.length > 0
        ? Number(messages[messages.length - 1].id)
        : 0;

    if (newestId === lastMessageId) {
        return;
    }

    lastMessageId = newestId;

    // Remove old message elements before rendering the latest list
    messagesContainer.replaceChildren();

    // Display a helpful message if the database is empty
    if (messages.length === 0) {
        const emptyMessage = document.createElement("p");
        emptyMessage.className = "empty-state";
        emptyMessage.textContent = "No messages yet. Start the conversation!";
        messagesContainer.appendChild(emptyMessage);
        return;
    }

    // Create one message bubble for each database record
    messages.forEach(function (message) {
        const bubble = document.createElement("article");
        bubble.className = "message";

        const sender = document.createElement("div");
        sender.className = "message-name";
        sender.textContent = message.name;

        const text = document.createElement("p");
        text.className = "message-text";
        text.textContent = message.message;
        //This displays a message as text, 
        // even if somebody types something like <script>. 
        // It won't interpret that input as HTML, which is important for a chat application

        const time = document.createElement("div");
        time.className = "message-time";

        // Format the timestamp for display in the browser
        const date = new Date(message.created_at.replace(" ", "T") + "Z");

        time.textContent = Number.isNaN(date.getTime())
            ? message.created_at
            : date.toLocaleString();

        // Build the bubble using DOM elements, not HTML strings
        bubble.appendChild(sender);
        bubble.appendChild(text);
        bubble.appendChild(time);

        messagesContainer.appendChild(bubble);
    });

    // Scroll to the newest message after updating the chat
    messagesContainer.scrollTop = messagesContainer.scrollHeight;
}

// 2. RETRIEVE MESSAGES FROM PHP

async function loadMessages() {
    try {
        // Ask PHP for the latest messages
        const response = await fetch("task07.php?action=list");

        // Convert the JSON response into a JavaScript object
        const data = await response.json();

        // Handle unsuccessful HTTP responses
        if (!response.ok || !data.success) {
            throw new Error(data.error || "Could not load messages.");
        }

        // Update the connection status and the chat display
        connectionStatus.textContent = "● Connected · refreshes every 4 seconds";
        displayMessages(data.messages);

    } catch (error) {
        // The chat remains on screen even if one request fails
        connectionStatus.textContent = "Connection problem · retrying...";
        console.error("Could not load messages:", error);
    }
}

// 3. SEND A NEW MESSAGE

chatForm.addEventListener("submit", async function (event) {
    // Stop the browser from navigating or refreshing the page.
    event.preventDefault();

    // Read the values entered by the user
    const name = nameInput.value.trim();
    const message = messageInput.value.trim();

    // Prevent empty or whitespace-only messages
    if (name === "" || message === "") {
        feedback.textContent = "Please enter both your name and a message.";
        feedback.className = "error";
        return;
    }

    // Prevent accidental double submissions while saving
    sendButton.disabled = true;
    feedback.textContent = "Sending...";
    feedback.className = "";

    // Encode the values for a POST request
    const formData = new URLSearchParams({
        name: name,
        message: message
    });

    try {
        // Send the new message to PHP
        const response = await fetch("task07.php?action=send", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData.toString()
        });
        // The async keyword allows the function to use await. await pauses that function while the request completes, without freezing the whole webpage

        const data = await response.json();

        if (!response.ok || !data.success) {
            throw new Error(data.error || "Could not send the message.");
        }

        // Clear only the message field
        // Keeping the name is convenient for sending multiple messages
        messageInput.value = "";

        feedback.textContent = "Message sent!";
        feedback.className = "success";

        // Refresh immediately rather than making the sender wait 4 seconds
        await loadMessages();

        // Put the cursor back in the message field
        messageInput.focus();

    } catch (error) {
        feedback.textContent = error.message || "Could not send the message.";
        feedback.className = "error";

        console.error("Could not send message:", error);

    } finally {
        // Re-enable the button whether sending succeeded or failed
        sendButton.disabled = false;
    }
});

// 4. START THE CHAT AND REFRESH EVERY 4 SECONDS

// Load existing messages as soon as the page opens
loadMessages();

// Repeat the request every 4000 milliseconds (4 seconds)
setInterval(loadMessages, 4000);
