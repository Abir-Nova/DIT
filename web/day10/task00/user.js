fetch("user.php")
    .then(response => response.json())
    .then(user => {
        // We now have a normal JavaScript object.
        console.log(user.name);
        console.log(user.age);
        console.log(user.student);
    });