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
    <br>
    <br>
    <br>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Author</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Category</th>

        </tr>
        </thead>
        <tbody>
        <%
            List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("items");
            if (items != null) {
                for (ItemDTO item : items) {
        %>
        <tr>
            <td><%= item.getId() %></td>
            <td><%= item.getName() %></td>
            <td><%= item.getAuthor() %></td>
            <td>$<%= item.getPrice() %></td>
            <td><%= item.getStock() %></td>
            <td><%= item.getCategoryName() %></td>
            <td>
                <a href="items?action=edit&id=<%= item.getId() %>" class="btn btn-edit"> Edit</a>
                <a href="items?action=delete&id=<%= item.getId() %>" class="btn btn-delete"
                   onclick="return confirm('Are you sure?')"> Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>

<div class="footer">
    &copy; 2025 Book Management System. All rights reserved.
</div>

</body>
</html>
