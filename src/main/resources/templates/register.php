<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];

    // Perform validation (you may want to add more validation)
    if (empty($username) || empty($password)) {
        echo 'Please fill in all fields.';
    } else {
        // Store user data in a database (replace with your database connection code)
        $servername = "localhost";
        $db_username = "your_database_username";
        $db_password = "your_database_password";
        $database = "your_database_name";

        $conn = new mysqli($servername, $db_username, $db_password, $database);

        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        $hashed_password = password_hash($password, PASSWORD_DEFAULT);

        $sql = "INSERT INTO users (username, password) VALUES ('$username', '$hashed_password')";

        if ($conn->query($sql) === TRUE) {
            echo 'Registration successful. You can now <a href="login.html">login</a>.';
        } else {
            echo 'Error: ' . $sql . '<br>' . $conn->error;
        }

        $conn->close();
    }
}
?>
