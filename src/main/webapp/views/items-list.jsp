<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Items List - PahanaEdu</title>
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
            background-color: #2980b9; color: white;
        }
        a.button {
            display: inline-block; padding: 8px 16px; background-color: #27ae60;
            color: white; text-decoration: none; border-radius: 4px; margin-bottom: 15px;
        }
        a.button:hover {
            background-color: #1e8449;
        }
    </style>
</head>
<body>

<h2>Items List</h2>

<a href="items?action=new" class="button">Add New Item</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Author</th>
        <th>Price (LKR)</th>
        <th>Stock</th>
        <th>Category</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${itemsList}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.author}</td>
            <td>${item.price}</td>
            <td>${item.stock}</td>
            <td>${item.category.name}</td>
            <td>
                <a href="items?action=edit&id=${item.id}">Edit</a> |
                <a href="items?action=delete&id=${item.id}" onclick="return confirm('Are you sure to delete?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="index.jsp" style="margin-top: 20px; display: inline-block;">Back to Home</a>

</body>
</html>
