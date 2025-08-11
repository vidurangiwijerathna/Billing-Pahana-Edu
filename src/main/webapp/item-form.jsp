<%@ page import="java.util.List" %>
<%@ page import="com.book.dto.ItemDTO" %>
<%@ page import="com.book.dto.ItemCategoryDTO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Add / Edit Item</title>
    <link rel="stylesheet" href="styles3.css">
</head>
<body>

<div class="navbar">
    <div class="logo"> Admin Panel</div>
    <div>
        <a href="index.jsp">Home</a>
        <a href="item-form.jsp">Manage Items</a>
        <a href="category-list.jsp">Manage Categories</a>
    </div>
</div>

<div class="container">
    <h2><%= request.getAttribute("item") == null ? "Add New Item" : "Edit Item" %></h2>

    <form method="post" action="items">
        <%
            ItemDTO item = (ItemDTO) request.getAttribute("item");
            if (item != null) {
        %>
        <input type="hidden" name="id" value="<%= item.getId() %>">
        <% } %>

        <label>Item Name:</label>
        <input type="text" name="name" value="<%= item != null ? item.getName() : "" %>" required>

        <label>Author:</label>
        <input type="text" name="author" value="<%= item != null ? item.getAuthor() : "" %>" required>

        <label>Price:</label>
        <input type="number" name="price" step="0.01" value="<%= item != null ? item.getPrice() : "" %>" required>

        <label>Stock:</label>
        <input type="number" name="stock" value="<%= item != null ? item.getStock() : "" %>" required>

        <label>Category:</label>
        <select name="categoryId" required>
            <option value="">-- Select Category --</option>
            <%
                List<ItemCategoryDTO> categories = (List<ItemCategoryDTO>) request.getAttribute("categories");
                if (categories != null) {
                    for (ItemCategoryDTO cat : categories) {
            %>
            <option value="<%= cat.getId() %>" <%= (item != null && item.getCategoryId().equals(cat.getId())) ? "selected" : "" %>>
                <%= cat.getCategoryName() %>
            </option>
            <%
                    }
                }
            %>
        </select>

        <br><br>
        <button type="submit" class="btn btn-add"> Save</button>
        <a href="items" class="btn btn-delete"> Cancel</a>
        <a href="admin-db.jsp"class="btn btn">Admin page</a>
    </form>
</div>

<div class="footer">
    &copy; 2025 Book Management System. All rights reserved.
</div>

</body>
</html>
