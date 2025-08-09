<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bills List - PahanaEdu</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        h2 { color: #2c3e50; }
        table {
            width: 90%; border-collapse: collapse; margin-top: 20px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 10px 15px; border: 1px solid #ddd; text-align: center;
        }
        th {
            background-color: #c0392b; color: white;
        }
        a.button {
            display: inline-block; padding: 8px 16px; background-color: #e74c3c;
            color: white; text-decoration: none; border-radius: 4px; margin-bottom: 15px;
        }
        a.button:hover {
            background-color: #a93226;
        }
    </style>
</head>
<body>

<h2>Bills List</h2>

<a href="bills?action=new" class="button">Create New Bill</a>

<table>
    <thead>
    <tr>
        <th>Bill ID</th>
        <th>Customer</th>
        <th>Date</th>
        <th>Total Amount (LKR)</th>
        <th>Created By</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="bill" items="${billsList}">
        <tr>
            <td>${bill.id}</td>
            <td>${bill.customer.name}</td>
            <td><fmt:formatDate value="${bill.billDate}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>${bill.totalAmount}</td>
            <td>${bill.createdBy.username}</td>
            <td>
                <a href="bills?action=view&id=${bill.id}">View</a> |
                <a href="bills?action=delete&id=${bill.id}" onclick="return confirm('Delete this bill?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="index.jsp" style="margin-top: 20px; display: inline-block;">Back to Home</a>

</body>
</html>
