<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer List - PahanaEdu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f2f5;
            padding: 30px;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px;
            border: 1px solid #ccc;
            text-align: left;
        }
        th {
            background: #007bff;
            color: white;
        }
        a.button {
            padding: 8px 15px;
            background: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        a.button:hover {
            background: #218838;
        }
        .action-buttons a {
            margin-right: 5px;
        }
    </style>
</head>
<body>
<h2>📋 Customer List</h2>
<a class="button" href="customers?action=new">+ Add New Customer</a>
<br/><br/>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Mobile</th>
        <th>Address</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.email}</td>
            <td>${customer.mobile}</td>
            <td>${customer.address}</td>
            <td class="action-buttons">
                <a class="button" style="background:#ffc107;" href="customers?action=edit&id=${customer.id}">Edit</a>
                <a class="button" style="background:#dc3545;" href="customers?action=delete&id=${customer.id}" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
