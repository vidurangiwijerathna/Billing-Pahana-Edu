<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.book.dto.ItemCategoryDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Item Categories</title>
    <link rel="stylesheet" href="css/styles3.css">
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
<h2>Item Categories</h2>
<a href="category-form.jsp">Add New Category</a>
<a href="admin-db.jsp" class="tab"> Admin Page</a>

<table border="1" cellpadding="8" cellspacing="0" style="width:80%; margin:20px auto;">
    <thead>
    <tr>
        <th>ID</th>
        <th>Category Name</th>

    </tr>
    </thead>
    <tbody>
    <%
        List<ItemCategoryDTO> categories = (List<ItemCategoryDTO>) request.getAttribute("categories");
        if (categories != null) {
            for (ItemCategoryDTO cat : categories) {
    %>
    <tr>
        <td><%= cat.getId() %></td>
        <td><%= cat.getCategoryName() %></td>
        <td>
            <a href="categories?action=edit&id=<%= cat.getId() %>">Edit</a> |
            <a href="categories?action=delete&id=<%= cat.getId() %>" onclick="return confirm('Are you sure?')">Delete</a>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>
</body>
</html>
