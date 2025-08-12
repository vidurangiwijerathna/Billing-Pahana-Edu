<%@ page import="com.book.service.ItemCategoryService" %>
<%@ page import="com.book.model.ItemCategory" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Items</title>
    <link rel="stylesheet" href="css/styles3.css">

</head>
<body>

<div class="navbar">
    <div class="logo"> Welcome to Add Items page</div>
    <div>
        <a href="index.jsp">Home</a>
        <a href="admin-db.jsp">Admin Page</a>
        <a href="item-list.jsp">Manage Items</a>
    </div>
</div>


<%
    ItemCategoryService catService = new ItemCategoryService();
    List<ItemCategory> categories = catService.getAllCategories();
%>

<% if (request.getParameter("success") != null) { %>
<p class="success"> Book added successfully!</p>
<% } %>

<form method="post" action="items">
    <label>Title:</label>
    <input type="text" name="name" required>

    <label>Author:</label>
    <input type="text" name="author" required>

    <label>Price:</label>
    <input type="number" step="0.01" name="price" required>

    <label>Stock Quantity:</label>
    <input type="number" name="stock" required>

    <label>Category:</label>
    <select name="categoryId" required>
        <% for (ItemCategory cat : categories) { %>
        <option value="<%= cat.getId() %>"><%= cat.getCategoryName() %></option>
        <% } %>
    </select>

    <input type="submit" value="Add Book">
</form>

<div class="footer">
    &copy; 2025 Book Management System. All rights reserved.
</div>

</body>
</html>
