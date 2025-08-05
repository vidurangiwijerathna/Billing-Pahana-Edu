<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Pahana Edu Billing</title>
</head>
<body>
<h2>Register Page</h2>

<c:if test="${not empty success}">
    <p style="color: green;">${success}</p>
</c:if>
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/register">
    <label>Username:</label><br>
    <input type="text" name="username" required><br><br>

    <label>Email:</label><br>
    <input type="email" name="email" required><br><br>

    <label>Password:</label><br>
    <input type="password" name="password" required><br><br>

    <label>Role:</label><br>
    <select name="role" required>
        <option value="ADMIN">ADMIN</option>
        <option value="CASHIER">CASHIER</option>
    </select><br><br>

    <button type="submit">Register</button>
</form>
</body>
</html>
