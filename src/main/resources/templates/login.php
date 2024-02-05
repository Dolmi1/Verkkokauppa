<?php
// Simulate user authentication
$validUsername = 'demo';
$validPassword = 'password';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $enteredUsername = $_POST['username'];
    $enteredPassword = $_POST['password'];

    if ($enteredUsername === $validUsername && $enteredPassword === $validPassword) {
        // Authentication successful
        header('Location: dashboard.php'); // Redirect to the dashboard or any other page
        exit();
    } else {
        // Authentication failed
        echo 'Invalid username or password';
    }
}
?>
