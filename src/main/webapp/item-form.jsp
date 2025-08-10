<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.book.dto.ItemDTO" %>
<%
    ItemDTO item = (ItemDTO) request.getAttribute("item");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Item Form</title>
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
<h2><%= (item != null) ? "Edit Item" : "Add Item" %></h2>
<form action="items" method="post">
    <input type="hidden" name="id" value="<%= (item != null) ? item.getId() : "" %>">
    Name: <input type="text" name="name" value="<%= (item != null) ? item.getName() : "" %>" required><br>
    Category: <input type="text" name="category" value="<%= (item != null) ? item.getCategory() : "" %>" required><br>
    Price: <input type="number" step="0.01" name="price" value="<%= (item != null) ? item.getPrice() : "" %>" required><br>
    Quantity: <input type="number" name="quantity" value="<%= (item != null) ? item.getQuantity() : "" %>" required><br>
    <input type="submit" value="Save">
    <br>
    <a href="admin-db.jsp" class="tab"> Admin Page</a>
</form>

<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>
</body>
</html>

