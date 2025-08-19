<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.book.dto.BillDTO" %>
<html>
<head>
    <title>Bills</title>
    <link rel="stylesheet" href="css/styles5.css">
</head>
<body>
<h2>Bills</h2>
<a href="bills?action=create">Create New Bill</a>
<br/><br/>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Customer ID</th>
        <th>Total</th>
        <th>Actions</th>
    </tr>
    <%
        List<BillDTO> bills = (List<BillDTO>) request.getAttribute("bills");
        if (bills != null) {
            for (BillDTO b : bills) {
    %>
    <tr>
        <td><%= b.getId() %></td>
        <td><%= b.getCustomerId() %></td>
        <td><%= b.getTotalAmount() %></td>
        <td><a href="bills?action=details&id=<%= b.getId() %>">View</a></td>
    </tr>
    <%      }
    } else { %>
    <tr><td colspan="5">No bills.</td></tr>
    <% } %>
</table>
<!-- Back Button -->
<button onclick="history.back()" class="back-btn">Back</button>
</body>
</html>
