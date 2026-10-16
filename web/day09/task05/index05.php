
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Task 05</title>
</head>
<body>
    <?php
        // Load the two functions from task05.php.
        require_once __DIR__ . "/task05.php";

        // Check whether the user has already submitted the form.
        if (form_is_submitted()) {
    ?>

        <!-- If submitted, display the greeting instead of the form. -->
        <p><?php whoami(); ?></p>

    <?php
        } else {
            // If not submitted, display the form.
    ?>

        <form method="post">
            <div>
                <label for="name">Name</label>
                <input type="text" id="name" name="name">
            </div>

            <div>
                <label for="age">Age</label>
                <input type="number" id="age" name="age" min="0">
            </div>

            <div>
                <label for="curriculum">Curriculum</label>
                <select name="curriculum" id="curriculum">
                    <option value="">--Please choose an option--</option>
                    <option value="pge">PGE (Programme Grande Ecole)</option>
                    <option value="bachelor">Bachelor</option>
                    <option value="msc">MSc Pro</option>
                    <option value="coding">Coding Academy</option>
                    <option value="wac">Web@cademie</option>
                </select>
            </div>

            <div>
                <input type="submit" name="submit" value="Send">
            </div>
        </form>

    <?php
        }
    ?>
</body>
</html>
