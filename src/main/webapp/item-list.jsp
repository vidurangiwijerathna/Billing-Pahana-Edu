<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.book.dto.ItemDTO" %>
<%
    List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("items");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Item List</title>
</head>
<body>
<nav>
    <div class="nav-left">
        <a href="index.jsp">Home</a>
        <a href="about.jsp">About</a>
        <a href="help.jsp">Help</a>
    </div>
    <div class="nav-right">
        <a href="logout.jsp">Logout</a>
    </div>
</nav>
<h2>Items</h2>
<a href="item-form.jsp">Add New Item</a>
<table border="1" cellpadding="5">
    <tr>
        <th>ID</th><th>Name</th><th>Category</th><th>Price</th><th>Quantity</th><th>Actions</th>
    </tr>
    <%
        for (ItemDTO item : items) {
    %>
    <tr>
        <td><%= item.getId() %></td>
        <td><%= item.getName() %></td>
        <td><%= item.getCategory() %></td>
        <td><%= item.getPrice() %></td>
        <td><%= item.getQuantity() %></td>
        <td>
            <a href="items?action=edit&id=<%= item.getId() %>">Edit</a>
            <a href="items?action=delete&id=<%= item.getId() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
</table>

<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>
</body>
</html>

