<%@ page import="java.util.List" %>
<%@ page import="com.book.dto.ItemDTO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Manage Items</title>
    <link rel="stylesheet" href="css/styles3.css">
</head>
<body>

<div class="navbar">
    <div class="logo"> Welcome to manage Items page</div>
    <div>
        <a href="index.jsp">Home</a>
        <a href="admin-db.jsp">Admin Page</a>
        <a href="category-list.jsp">Manage Categories</a>
    </div>
</div>

<div class="container">
    <h2>Items List</h2>
    <br>
    <br>
    <a href="item-form.jsp" class="btn btn-add">Add New Item</a>
    <a href="item-view.jsp" class="btn btn-add">View Items</a>
    <br>
    <br>
    <br>

    <!-- Back Button -->
    <button onclick="history.back()" class="back-btn">Back</button>
</div>

<div class="footer">
    &copy; 2025 Book Management System. All rights reserved.
</div>

</body>
</html>
