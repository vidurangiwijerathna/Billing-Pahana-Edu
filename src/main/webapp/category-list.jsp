<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.book.dto.ItemCategoryDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Item Categories</title>
    <link rel="stylesheet" href="css/styles2.css"> <!-- or styles1.css -->
</head>
<body>

<h2 style="text-align:center;">Item Categories</h2>

<!-- messages -->
<%
    String message = (String) request.getAttribute("message");
    String messageType = (String) request.getAttribute("messageType");
    if (message != null) {
%>
<div class="<%= "success".equals(messageType) ? "success-message" : "error-message" %>" style="max-width:800px;margin:10px auto;text-align:center;">
    <%= message %>
</div>
<%
    }
%>

<div style="text-align:center; margin-bottom: 15px;">
    <a href="category-form.jsp" class="tab"> Add New Category</a>
    <a href="admin-db.jsp" class="tab"> Admin Page</a>
</div>

<div style="max-width:900px;margin: 0 auto;">
    <table class="styled-table" style="width:100%;">
        <thead>
        <tr>
            <th>Item ID</th>
            <th>Category Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<ItemCategoryDTO> categories = (List<ItemCategoryDTO>) request.getAttribute("categories");
            if (categories != null && !categories.isEmpty()) {
                for (ItemCategoryDTO c : categories) {
        %>
        <tr>
            <td><%= c.getItemId() %></td>
            <td><%= c.getCategoryName() %></td>
            <td>
                <a href="categories?action=edit&itemId=<%= c.getItemId() %>">Edit</a> |
                <a href="categories?action=delete&itemId=<%= c.getItemId() %>" onclick="return confirm('Delete this category?');">Delete</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="3" style="text-align:center;">No categories found.</td></tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>
