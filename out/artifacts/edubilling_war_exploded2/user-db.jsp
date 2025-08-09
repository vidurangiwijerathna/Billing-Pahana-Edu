<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head><title>User Dashboard</title></head>
<body>
<h2>Welcome User, <%= session.getAttribute("userName") %></h2>
<p>This is the user dashboard.</p>
<a href="logout.jsp">Logout</a>
</body>
</html>
