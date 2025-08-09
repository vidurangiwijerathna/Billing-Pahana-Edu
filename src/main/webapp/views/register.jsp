<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <style>
        /* Basic simple styling */
        body { font-family: Arial, sans-serif; }
        form { max-width: 400px; margin: auto; padding: 1em; border: 1px solid #ccc; }
        input { width: 100%; padding: 0.5em; margin-bottom: 1em; }
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>

<h2 style="text-align:center;">Register</h2>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

<form action="register" method="post">
    <input type="text" name="username" placeholder="Username" required />
    <input type="email" name="email" placeholder="Email" required />
    <input type="password" name="password" placeholder="Password" required />
    <button type="submit">Register</button>
</form>

<p style="text-align:center;">
    Already have an account? <a href="login">Login here</a>
</p>

</body>
</html>
