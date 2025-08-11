<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.book.dto.ItemCategoryDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Category Form</title>
    <link rel="stylesheet" href="css/styles2.css">
</head>
<body>

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
    <a href="admin-db.jsp" class="tab"> Admin Page</a>
</form>
<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>
</body>
</html>
