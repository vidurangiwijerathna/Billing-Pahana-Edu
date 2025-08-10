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
    boolean editing = (category != null && category.getItemId() != null);
%>

<h2 style="text-align:center;"><%= editing ? "Edit Category" : "Add New Category" %></h2>

<div style="max-width:600px;margin:20px auto; background:white; padding:20px; border-radius:8px;">
    <form method="post" action="categories">
        <% if (editing) { %>
        <input type="hidden" name="itemId" value="<%= category.getItemId() %>">
        <p><strong>Item ID: </strong> <%= category.getItemId() %></p>
        <% } %>

        <label for="categoryName">Category Name</label><br>
        <input type="text" id="categoryName" name="categoryName" required
               value="<%= editing ? category.getCategoryName() : "" %>" style="width:100%;padding:8px;margin-bottom:12px;">

        <input type="submit" value="<%= editing ? "Update Category" : "Add Category" %>" style="padding:10px 18px;">
        <a href="categories" style="margin-left:12px;">Back</a>
    </form>
</div>

</body>
</html>
