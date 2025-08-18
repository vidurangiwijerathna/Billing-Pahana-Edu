<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.book.dto.ItemCategoryDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Item Categories</title>
    <link rel="stylesheet" href="css/styles4.css">
</head>
<body>
<nav>
    <div class="navbar">
        <div class="logo"> Welcome to Item Categories page</div>
        <div>
            <a href="index.jsp">Home</a>
            <a href="admin-db.jsp">Admin Page</a>
            <a href="category-list.jsp">Manage Category</a>
        </div>
    </div>
</nav>
<h2>Item Categories</h2>
<a href="category-form.jsp">Add New Category</a>
<a href="category-view.jsp">View Category</a>
<br>
<br>
<!-- Back Button -->
<button onclick="history.back()" class="back-btn">Back</button>
<br>
<br>
<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>
</body>
</html>
