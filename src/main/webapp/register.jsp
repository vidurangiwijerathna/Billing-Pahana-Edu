<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Register - Pahana Edu Billing</title>
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
  <h2>User Registration</h2>

  <% String success = request.getParameter("success"); %>
  <% if ("registered".equals(success)) { %>
  <div class="success-message">Registration successful! You may now <a href="login.jsp">login</a>.</div>
  <% } %>

  <form method="post" action="register">
    <label for="name">Name:</label>
    <input type="text" name="name" id="name" required>

    <label for="email">Email:</label>
    <input type="email" name="email" id="email" required>

    <label for="password">Password:</label>
    <input type="password" name="password" id="password" required>

    <label for="role">Role:</label>
    <select name="role" id="role" required>
      <option value="User" selected>User</option>
      <option value="Admin">Admin</option>
    </select>

    <input type="submit" value="Register">
  </form>

  <p style="text-align:center;">Already have an account? <a href="login.jsp">Login here</a></p>
</div>

<footer>
  &copy; 2025 Pahana Edu. All rights reserved.
</footer>

</body>
</html>
