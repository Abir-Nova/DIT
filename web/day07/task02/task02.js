
const box = document.querySelector("footer div");

box.addEventListener(
    "click", 
    function () {
        let name;

        while (!name) {
            name = prompt("What's your name?");
            
            if (name !== null) 
                name = name.trim();
            if (name === null)
                return;
        }

        const confirmed = confirm(
            `Are you sure that ${name} is your name ?`
        );

        if (confirmed) {
            const message = `Hello ${name}!`;

            alert(message);
            box.textContent = message;
        }
    }
);
