
/*
 * Goal:
 * 1. Drag the black square inside the first white box
 * 2. Display the square's position as {x: ..., y: ...}
 * 3. Prevent the square from leaving the white box
 */

// STEP 1: SELECT THE HTML ELEMENTS

// Select the first <canvas> element in the HTML
// In this task, the canvas is our black square
const canvas = document.querySelector("canvas");

// parentElement returns the direct parent of the canvas
// According to our HTML, this is the first <div> inside <footer>
// This div is the white box that will contain the square 
const box = canvas.parentElement;

// Select the second <div> inside <footer>
// This is the white box where we display the coordinates
const coordinatesBox = document.querySelector("footer div:nth-child(2)");

// STEP 2: PREPARE THE WHITE BOX

// position: relative makes this box the reference point
// for the absolutely positioned black square.
// In other words, the square's top and left positions
// will be measured from this box.
box.style.position = "relative";

// Give the white box a height of 200 pixels.
// This creates enough vertical space to drag the square.
box.style.height = "200px";

// Remove the box's original 22px padding.
// This makes the box's top-left corner (0, 0)
// the reference point for our coordinates.
box.style.padding = "0";

// STEP 3: PREPARE THE BLACK SQUARE

// position: absolute allows us to move the square
// by changing its left and top CSS properties.
// Because its parent has position: relative,
// the square is positioned relative to that parent.
canvas.style.position = "absolute";

// Start the square at the top-left corner of the box.
// left controls the horizontal position (x).
canvas.style.left = "0px";

// top controls the vertical position (y).
canvas.style.top = "0px";

// Remove the canvas's original margin so its position
// matches the coordinates we calculate.
canvas.style.margin = "0";

// Show a hand cursor to indicate that the square
// can be grabbed and moved.
canvas.style.cursor = "grab";

// Prevent the browser from interpreting pointer gestures
// on the square as touch scrolling or zooming.
canvas.style.touchAction = "none";

// STEP 4: CREATE VARIABLES FOR DRAGGING

// This variable remembers whether the user is currently
// dragging the square.
// false means we are not dragging.
// true means the user has pressed the square and is moving it.
let isDragging = false;

// offsetX remembers the horizontal distance between
// the pointer and the square's left edge when dragging starts.
let offsetX = 0;

// offsetY remembers the vertical distance between
// the pointer and the square's top edge when dragging starts.
// These offsets prevent the square from suddenly jumping
// so that its top-left corner moves directly under the pointer.

// STEP 5: CREATE A FUNCTION TO DISPLAY COORDINATES

// A function is a reusable block of code.
// We call this function whenever the square moves.
function updateCoordinates(x, y) {

    // textContent replaces the text inside the second white box.
    // The backticks (`) allow us to insert variables using ${...}.
    // For example, updateCoordinates(50, 30) displays:
    // New coordinates => {x:50, y:30}
    coordinatesBox.textContent = `New coordinates => {x:${x}, y:${y}}`;
}

// STEP 6: START DRAGGING WHEN THE SQUARE IS PRESSED

// pointerdown runs when the user presses the square
// with a mouse, a finger, or another pointer device.
canvas.addEventListener("pointerdown", function (event) {

    // Remember that dragging has started.
    isDragging = true;

    // getBoundingClientRect() gives us the square's position
    // and dimensions relative to the browser's viewport.
    const squareRect = canvas.getBoundingClientRect();

    // event.clientX is the pointer's horizontal position
    // relative to the viewport.
    // squareRect.left is the square's left edge.
    // Subtracting them tells us where inside the square
    // the user clicked.
    offsetX = event.clientX - squareRect.left;
    // Do the same calculation vertically.
    // clientY is the pointer's vertical position.
    // squareRect.top is the square's top edge.
    offsetY = event.clientY - squareRect.top;
    // Change the cursor to show that the square is being held.
    canvas.style.cursor = "grabbing";
    // Keep receiving pointer events for this interaction,
    // even if the pointer moves outside the square.
    // event.pointerId identifies the current pointer.
    canvas.setPointerCapture(event.pointerId);
});

// STEP 7: MOVE THE SQUARE WHILE DRAGGING

// pointermove runs whenever the pointer moves.
canvas.addEventListener("pointermove", function (event) {

    // If the user has not pressed the square,
    // do nothing and exit this function.
    if (!isDragging) {
        return;
    }

    // Get the white box's position relative to the viewport.
    // We need this because the pointer coordinates (clientX
    // and clientY) are also measured relative to the viewport.
    const boxRect = box.getBoundingClientRect();
 
    // STEP 7A: CALCULATE THE NEW COORDINATES

    // Horizontal position:
    // 1. event.clientX: pointer's horizontal position on screen.
    // 2. Subtract boxRect.left to measure from the white box.
    // 3. Subtract offsetX to preserve where the square was grabbed.
    let x = event.clientX - boxRect.left - offsetX;

    // Vertical position:
    // 1. event.clientY: pointer's vertical position on screen.
    // 2. Subtract boxRect.top to measure from the white box.
    // 3. Subtract offsetY to preserve where the square was grabbed.
    let y = event.clientY - boxRect.top - offsetY;

    // STEP 7B: KEEP THE SQUARE INSIDE THE WHITE BOX

    // Math.min(a, b) returns the smaller of two numbers.
    // Math.max(a, b) returns the larger of two numbers.

    // First, Math.min() prevents x from exceeding the
    // rightmost position the square can occupy.

    // Then, Math.max() prevents x from becoming negative.

    // box.clientWidth is the inside width of the white box.
    // canvas.offsetWidth is the square's displayed width.
    
    // Subtracting the square's width ensures that its
    // right edge does not extend beyond the white box.
    x = Math.max(
        0,
        Math.min(x, box.clientWidth - canvas.offsetWidth)
    );

    // Apply the same logic vertically:
    // - The smallest y is 0 (the top edge).
    // - The largest y is the box's height minus the square's height.
    y = Math.max(
        0,
        Math.min(y, box.clientHeight - canvas.offsetHeight)
    );


    // STEP 7C: APPLY THE NEW POSITION

    // Update the square's horizontal position.
    // CSS position values need units, so we add "px".
    canvas.style.left = `${x}px`;

    // Update the square's vertical position.
    canvas.style.top = `${y}px`;

    // STEP 7D: DISPLAY THE COORDINATES

    // Math.round() rounds a number to the nearest integer.
    // This avoids displaying decimal coordinates such as 50.7.

    // Pass the rounded coordinates to our display function.
    updateCoordinates(Math.round(x), Math.round(y));
});

// STEP 8: STOP DRAGGING WHEN THE POINTER IS RELEASED

// pointerup runs when the user releases the mouse button
// or lifts their finger from the screen.
canvas.addEventListener("pointerup", function () {

    // Dragging is finished.
    isDragging = false;

    // Return the cursor to its normal grab appearance.
    canvas.style.cursor = "grab";
});

// STEP 9: HANDLE A CANCELLED POINTER INTERACTION

// pointercancel can happen if the browser interrupts
// the pointer interaction, for example because of
// certain system gestures.
canvas.addEventListener("pointercancel", function () {

    // Stop dragging so the square does not remain
    // in a permanently "held" state.
    isDragging = false;

    // Restore the normal cursor.
    canvas.style.cursor = "grab";
});

// STEP 10: DISPLAY THE INITIAL POSITION

// Before the user moves the square, it starts at
// the top-left corner of the white box.
// Therefore, its initial coordinates are x = 0, y = 0.
updateCoordinates(0, 0);
