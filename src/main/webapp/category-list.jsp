<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Item Categories</title>
    <link rel="stylesheet" href="css/styles.css" />
</head>
<body>

<h2>Item Categories</h2>

<%
    String message = (String) request.getAttribute("message");
    String messageType = (String) request.getAttribute("messageType");
    if (message != null) {
%>
<div class="<%= "success".equals(messageType) ? "success-message" : "error-message" %>">
    <%= message %>
</div>
<%
    }
%>

<form method="post" action="categories">
    <label for="name">New Category Name:</label>
    <input type="text" id="name" name="name" required />
    <input type="submit" value="Add Category" />
</form>

<table border="1" cellpadding="10" cellspacing="0" style="margin-top: 20px;">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<com.book.dto.ItemCategoryDTO> categories = (List<com.book.dto.ItemCategoryDTO>) request.getAttribute("categories");
        if (categories != null) {
            for (com.book.dto.ItemCategoryDTO cat : categories) {
    %>
    <tr>
        <td><%= cat.getId() %></td>
        <td><%= cat.getName() %></td>
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
