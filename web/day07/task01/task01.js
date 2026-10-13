const box = document.querySelector("footer div");

let clicks =0;

box.addEventListener(
    "click",
    function(){
        clicks++;
        box.textContent = clicks ;
    }
);
/* 
document represents my HTML page
querySelector() finds the first element matching a CSS selector
"footer div" selects a div inside a footer

box.textContent = clicks displays the new number inside the box
*/