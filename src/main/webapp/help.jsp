<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Help - System Usage Guidelines</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <h2>System Usage Guidelines</h2>
    <p>Welcome to <strong>Pahana Edu Billing System</strong>.
        Below are some helpful instructions to get you started:</p>

    <h3> Login & Registration</h3>
    <ul>
        <li>Admins and Cashiers can register themselves through the <b>Register</b> page.</li>
        <li>Login using your username and password from the <b>Login</b> page.</li>
    </ul>

    <h3> Managing Users</h3>
    <ul>
        <li>Admins can add, edit, or remove users in the <b>Users</b> section.</li>
        <li>Cashiers can only manage customers and billing.</li>
    </ul>

    <h3> Managing Items & Categories</h3>
    <ul>
        <li>Admins can create item categories and add items under each category.</li>
        <li>Items can be updated or deleted when required.</li>
    </ul>

    <h3>Billing</h3>
    <ul>
        <li>Cashiers can create new bills by selecting a customer and adding items.</li>
        <li>Generated bills will automatically calculate total amounts.</li>
    </ul>

    <h3> Logout</h3>
    <ul>
        <li>Always use the <b>Logout</b> button to safely end your session.</li>
    </ul>

    <div style="text-align:center; margin-top:20px;">
        <a href="index.jsp"> Back to Home</a>
    </div>
</div>
<!-- Back Button -->
<button onclick="history.back()" class="back-btn">Back</button>
</body>
</html>
