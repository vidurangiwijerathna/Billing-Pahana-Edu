<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User List</title>
</head>
<body>

<h2>User List</h2>

<a href="users?action=new">Add New User</a><br/><br/>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th><th>Username</th><th>Email</th><th>Role</th><th>Actions</th>
    </tr>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>
                <a href="users?action=edit&id=${user.id}">Edit</a> |
                <a href="users?action=delete&id=${user.id}" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
