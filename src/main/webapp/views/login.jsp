<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login - Pahana Edu Billing</title>
</head>
<body>
<h2>Login Page</h2>
<!-- Add your login form here -->
<form method="post" action="/users/login">
  <label>Username:</label><br>
  <input type="text" name="username"><br>
  <label>Password:</label><br>
  <input type="password" name="password"><br><br>
  <button type="submit">Login</button>
</form>
</body>
</html>
