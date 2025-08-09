<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User Form</title>
</head>
<body>

<h2>${user == null ? "Add New User" : "Edit User"}</h2>

<form action="users" method="post">
    <input type="hidden" name="id" value="${user != null ? user.id : 0}"/>

    <label>Username:</label><br/>
    <input type="text" name="username" required value="${user != null ? user.username : ''}"/><br/><br/>

    <label>Email:</label><br/>
    <input type="email" name="email" required value="${user != null ? user.email : ''}"/><br/><br/>

    <label>Password:</label><br/>
    <input type="password" name="password" required/><br/><br/>

    <label>Role:</label><br/>
    <select name="role" required>
        <option value="ADMIN" ${user != null && user.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
        <option value="CASHIER" ${user != null && user.role == 'CASHIER' ? 'selected' : ''}>CASHIER</option>
    </select><br/><br/>

    <input type="submit" value="Save"/>
</form>

<a href="users?action=list">Back to List</a>

</body>
</html>
