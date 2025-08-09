<!DOCTYPE html>
<html>
<head>
  <title>Register</title>
</head>
<body>
<h2>User Registration</h2>
<form method="post" action="register">
  Name: <input type="text" name="name" required><br>
  Email: <input type="email" name="email" required><br>
  Password: <input type="password" name="password" required><br>
  Role:
  <select name="role">
    <option value="User">User</option>
    <option value="Admin">Admin</option>
  </select><br>
  <input type="submit" value="Register">
</form>
</body>
</html>
