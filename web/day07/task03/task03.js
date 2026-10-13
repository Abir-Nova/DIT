const box = document.querySelector("footer div");

let text = "";

document.addEventListener(
    "keydown", 
    function (event) {
        if (event.key.length===1){
            text += event.key; //Add the pressed key , event.key gives us the key's value
            text = text.slice(-42); // returns the last 42 characters of a string, or the entire string if it is shorter than 42 characters
            box.textContent = text; // This updates the white box with the latest string  -> Display the result
        } 
    }
);

// we get also the special characters , tab , shift , control -> so if we want to to keep only letters we can use if ()===1