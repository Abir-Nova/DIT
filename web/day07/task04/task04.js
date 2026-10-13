
const plusButton = document.querySelector("button:first-of-type");//selects the first button +
const minusButton = document.querySelector("button:nth-of-type(2)");//selects the second button
const colorSelect = document.querySelector("select");//selects the dropdown menu

let fontSize = 16;

// make sure the + button work 

plusButton.addEventListener(
    "click", 
    function () {
        fontSize += 1;
        document.body.style.fontSize = fontSize + "px";
    }
);

/* 
    document.body : selects the page's body element
    .style.fontSize changes its inline CSS font size
    "px" adds the CSS unit required for the size
*/

minusButton.addEventListener(
    "click", 
    function () {
        fontSize -= 1;
        document.body.style.fontSize = fontSize + "px";
    }
);

colorSelect.addEventListener(
    "change", 
    function () {
        if (colorSelect.value !== "") 
            document.body.style.backgroundColor = colorSelect.value;
    }
);

/*
change is an event that fires when the dropdown's selected value changes
colorSelect.value retrieves the selected option's value

*/
