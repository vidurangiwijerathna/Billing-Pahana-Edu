<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.book.dto.ItemCategoryDTO" %>
<%
    List<ItemCategoryDTO> categories = (List<ItemCategoryDTO>) request.getAttribute("categories");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Item Categories</title>
    <link rel="stylesheet" href="css/styles2.css">
</head>
<body>

<h2>Item Categories</h2>

<div style="text-align:center; margin-bottom: 20px;">
    <a href="categories?action=new" class="table-btn btn-edit" style="background-color:#28a745;">+ Add New Category</a>
</div>

<table class="category-table">
    <thead>
    <tr>
        <th>Item ID</th>
        <th>Category Name</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <% if (categories != null && !categories.isEmpty()) {
        for (ItemCategoryDTO cat : categories) { %>
    <tr>
        <td><%= cat.getItemId() %></td>
        <td><%= cat.getCategoryName() %></td>
        <td>
            <a href="categories?action=edit&itemId=<%= cat.getItemId() %>" class="table-btn btn-edit">Edit</a>
            <a href="categories?action=delete&itemId=<%= cat.getItemId() %>"
               class="table-btn btn-delete"
               onclick="return confirm('Are you sure you want to delete this category?');">Delete</a>
        </td>
    </tr>
    <%  }
    } else { %>
    <tr>
        <td colspan="3" style="text-align:center;">No categories found</td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>
