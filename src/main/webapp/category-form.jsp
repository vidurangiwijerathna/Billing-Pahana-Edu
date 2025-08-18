<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.book.dto.ItemCategoryDTO" %>
<!DOCTYPE html>
<html>
<title>Add Categories</title>
<link rel="stylesheet" href="css/styles2.css">

</head>
<body>

<div class="navbar">
    <div class="logo"> Welcome to Add Categories page</div>
    <div>
        <a href="index.jsp">Home</a>
        <a href="admin-db.jsp">Admin Page</a>
        <a href="category-list.jsp">Manage Category</a>
    </div>
</div>
<%
    ItemCategoryDTO category = (ItemCategoryDTO) request.getAttribute("category");
    boolean editing = (category != null && category.getId() != null);
%>

<h2 style="text-align:center;"><%= editing ? "Edit Category" : "Add New Category" %></h2>

<form method="post" action="categories" style="max-width:600px; margin: 20px auto; background:#fff; padding:20px; border-radius:8px;">
    <% if (editing) { %>
    <input type="hidden" name="id" value="<%= category.getId() %>">
    <p><strong>ID: </strong> <%= category.getId() %></p>
    <% } %>

    <label for="categoryName">Category Name</label><br>
    <input type="text" id="categoryName" name="categoryName" required
           value="<%= editing ? category.getCategoryName() : "" %>">

    <input type="submit" value="<%= editing ? "Update Category" : "Add Category" %>">
    <a href="item-list.jsp" class="tab"> Item Page</a>
    <!-- Back Button -->
    <button onclick="history.back()" class="back-btn">Back</button>

</form>
<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>
</body>
</html>
