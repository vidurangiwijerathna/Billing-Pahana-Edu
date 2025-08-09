<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

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
    <h2>User List</h2>

    <table border="1" width="100%" cellpadding="10" cellspacing="0" style="border-collapse: collapse;">
        <thead>
        <tr style="background-color: #004d99; color: white;">
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.role}"/></td>
            </tr>
        </c:forEach>
        <c:if test="${empty users}">
            <tr><td colspan="3" style="text-align: center;">No users found.</td></tr>
        </c:if>
        </tbody>
    </table>
    <a href="admin-db.jsp">Admin Page</a>
</div>

<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>

</body>
</html>
