<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.book.dto.BillDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bills</title>
    <link rel="stylesheet" href="css/styles5.css">
</head>
<body>
<h2>All Bills</h2>
<a href="bills-create.jsp">Create New Bill</a>
<table>
    <thead>
    <tr>
        <th>ID</th><th>Customer ID</th><th>Created At</th><th>Total Amount</th><th>Created By</th><th>Action</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<BillDTO> bills = (List<BillDTO>) request.getAttribute("bills");
        if (bills != null) {
            for (BillDTO b : bills) {
    %>
    <tr>
        <td><%= b.getId() %></td>
        <td><%= b.getCustomerId() %></td>
        <td><%= b.getCreatedAt() %></td>
        <td><%= b.getTotalAmount() %></td>
        <td><%= b.getCreatedBy() %></td>
        <td><a href="bill-view.jsp">View</a></td>
    </tr>
    <%      }
    } else { %>
    <tr><td colspan="6">No bills found.</td></tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
