<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head><title>Admin Dashboard</title></head>
<body>
<h2>Welcome Admin, <%= session.getAttribute("userName") %></h2>
<p>This is the admin dashboard.</p>
<a href="logout.jsp">Logout</a>
</body>
</html>
