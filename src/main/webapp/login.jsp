<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Pahana Edu Billing</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp" />
<nav>
    <div class="nav-left">
        <a href="index.jsp">Home</a>
        <a href="about.jsp">About</a>
        <a href="help.jsp">Help</a>
    </div>
    <div class="nav-right">
        <a href="logout.jsp">Logout</a>
    </div>
</nav>

<div class="container">
    <h2>User Login</h2>

    <% String error = request.getParameter("error"); %>
    <% if ("InvalidCredentials".equals(error)) { %>
    <div class="error-message">Invalid email or password. Please try again.</div>
    <% } else if ("UnknownRole".equals(error)) { %>
    <div class="error-message">User role is not recognized.</div>
    <% } %>

    <form method="post" action="login">
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required>

        <input type="submit" value="Login">
    </form>
    <p style="text-align:center;">Don't have an account? <a href="register.jsp">Register here</a></p>
</div>

<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>

</body>
</html>
