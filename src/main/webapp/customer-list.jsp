<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp" />

<h2>Customer List</h2>
<br>
<br>
<h1 class="dashboard-title">Welcome Admin, <%= session.getAttribute("userName") %> </h1>
<br>
<a href="customer-form.jsp">Add New Customer</a>
<a href="customer"> View Customers Info</a>
<a href="custom"> Update Customer Info</a>
<a href="admin-db.jsp">Admin Page</a>

</body>
</html>
