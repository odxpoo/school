<?php

$username = $_POST["user"] ?? "";
$password = $_POST["pass"] ?? "";

echo "Entered username: " . $username . "<br>";
echo "Entered password: " . $password . "<br><br>";

$validCredentials = false;
$configs = array(
    array(
        'desc' => 'development',
        'host' => 'localhost',
        'user' => 'root',
        'pass' => 'Student123!',
        'dbname' => 'gGrades'
    ),
    array(
        'desc' => 'production',
        'host' => 'edu.gplweb.pl',
        'user' => 'root',
        'pass' => 'MegaTajneHas!o',
        'dbname' => 'gGrades'
    )
);

foreach ($configs as $config) {
    echo "Checking config: " . $config['desc'] . "<br>";

    if ($username === $config["user"] && $password === $config["pass"]) {
        $validCredentials = true;
        echo "Success! Logged in to " . $config["desc"] . ".<br>";
        echo "Full config: <br><br>";

        foreach ($config as $key => $value) {
            echo $key . ': ' . $value . "<br>";
        }

        break;
    } else {
        echo "Error! <br>";
        echo "Invalid login credentials.<br>";
        echo "Username: '" . $username . "'" . ($username === $config["user"] ? " = " : " != ") . "'" . $config["user"] . "'";
        echo "<br>";
        echo "Password: '" . $password . "'" . ($password === $config["pass"] ? " = " : " != ") . "'" . $config["pass"] . "'";
        echo "<br><br>";
    }
}

if (!$validCredentials) {
    echo "No matching configuration found for the entered credentials.";
}

?>
