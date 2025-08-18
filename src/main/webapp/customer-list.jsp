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
<h1 class="dashboard-title">Welcome Manage Customer Page, <%= session.getAttribute("userName") %> </h1>
<br>
<div class="tabs">
    <a href="customer-form.jsp" class="tab"> Add New Customer</a>
    <a href="view-customer.jsp" class="tab"> View Customers Info</a>
    <a href="admin-db.jsp" class="tab"> Admin Page</a>
</div>
<!-- Back Button -->
<button onclick="history.back()" class="back-btn">Back</button>
<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>
</body>
</html>
