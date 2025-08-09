<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.book.dto.BillItemDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bill Item List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        h2 {
            color: #333;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px 15px;
            border: 1px solid #ccc;
            text-align: center;
        }
        th {
            background-color: #f4f4f4;
        }
        .back-button {
            margin-top: 20px;
            padding: 8px 16px;
            background-color: #007BFF;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<h2>Bill Item List</h2>

<%
    List<BillItemDTO> items = (List<BillItemDTO>) request.getAttribute("items");
    if (items == null || items.isEmpty()) {
%>
<p>No bill items found for this bill.</p>
<%
} else {
%>
<table>
    <tr>
        <th>Item ID</th>
        <th>Quantity</th>
        <th>Price (LKR)</th>
    </tr>
    <% for (BillItemDTO item : items) { %>
    <tr>
        <td><%= item.getItemId() %></td>
        <td><%= item.getQuantity() %></td>
        <td><%= item.getPrice() %></td>
    </tr>
    <% } %>
</table>
<%
    }
%>

<a href="/dashboard.html" class="back-button">Back to Dashboard</a>

</body>
</html>
