
const canvas = document.querySelector("canvas");
// querySelector("canvas") selects the canvas element

// Set the actual drawing dimensions to 20 × 20 pixels.
canvas.width = 20;
canvas.height = 20;
// width and height set its internal drawing dimensions to 20 × 20 pixels

const context = canvas.getContext("2d"); // getContext("2d") gives us the tools to draw two-dimensional shapes

// Draw the triangle , path
context.beginPath(); // starts a new drawing path
context.moveTo(6, 6); // moves to the first vertex without drawing a line
context.lineTo(14, 10); //draws to the second vertex
context.lineTo(6, 14); // draws to the third vertex
context.closePath(); // connects the last vertex back to the first

//Fill and outline the triangle

context.fillStyle = "red";//defines the triangle's interior color
context.fill();//fills the enclosed shape

context.strokeStyle = "blue";//defines the outline color
context.lineWidth = 1; // sets the outline to 1 pixel
context.stroke(); //draws the outline

// Audio controls
// Play the music when the play icon is clicked.

const audio = document.querySelector("audio");
const buttons = document.querySelectorAll("footer button");

// querySelectorAll() selects all matching elements and returns a collection

const pauseButton = buttons[0];
const stopButton = buttons[1];

// Set the music URL
audio.src = "./audio.MP3";

// Play the music

canvas.addEventListener(
    "click", 
    function () {
        audio.play();
    }
);

// Pause the music
pauseButton.addEventListener(
    "click", 
    function () {
        audio.pause();
    }
);

//pause() pauses playback without resetting the current position -> Clicking Play again resumes from where the music was paused

// Stop the music and return to the beginning
stopButton.addEventListener(
    "click", 
    function () {
        audio.pause();
        audio.currentTime = 0;
    }
);
