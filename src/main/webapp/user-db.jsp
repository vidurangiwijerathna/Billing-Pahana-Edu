<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cahsier Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<nav>
    <div class="nav-left">
        <a href="index.jsp">Home</a>
        <a href="index.jsp">About</a>
        <a href="help.jsp">Help</a>
    </div>
    <div class="nav-right">
        <a href="logout.jsp">Logout</a>
    </div>
</nav>
<br>
<br>
<h1 class="dashboard-title">Welcome Cahsier, <%= session.getAttribute("userName") %> </h1>

<div class="dashboard">
    <a href="register.jsp" class="tab tab-users">Add New User</a>
    <a href="customer-list.jsp" class="tab tab-customers">Manage Customers</a>
    <a href="item-list.jsp" class="tab tab-items">Manage Items</a>
    <a href="category-list.jsp" class="tab tab-categories">Item Categories</a>
    <a href="bills" class="bills-list.jsp">Manage Bills</a>
    <a href="bill-item" class="tab tab-settings">Manage Bill Items</a>


</div>

<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>
</body>
</html>