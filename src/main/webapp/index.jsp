<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PahanaEdu Billing System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 40px;
            text-align: center;
        }
        h1 {
            color: #333;
        }
        .menu {
            margin-top: 30px;
        }
        .menu a {
            display: inline-block;
            margin: 10px;
            padding: 12px 25px;
            text-decoration: none;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
        }
        .menu a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h1>📚 Welcome to PahanaEdu Bookshop Billing System</h1>
<p>Select a module below:</p>

<div class="menu">
    <a href="users?action=list">👤 Users</a>
    <a href="customers?action=list">👥 Customers</a>
    <a href="items?action=list">📘 Items</a>
    <a href="categories?action=list">🗂️ Categories</a>
    <a href="bills?action=list">🧾 Bills</a>
</div>

</body>
</html>
