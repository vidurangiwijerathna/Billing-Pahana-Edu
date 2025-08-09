<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pahana Edu Bookshop</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding-top: 100px;
            text-align: center;
            background-color: #f0f0f0;
        }

        h1 {
            color: #2c3e50;
        }

        nav {
            position: absolute;
            top: 0;
            width: 100%;
            background-color: #2c3e50;
            padding: 15px 0;
            text-align: right;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        nav a {
            color: white;
            margin: 0 30px;
            text-decoration: none;
            font-weight: bold;
            font-size: 16px;
        }

        nav a:hover {
            text-decoration: underline;
        }

        .tab-container {
            margin-top: 50px;
        }

        .tab-button {
            padding: 12px 30px;
            margin: 0 20px;
            font-size: 18px;
            cursor: pointer;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 6px;
            text-decoration: none;
            transition: background-color 0.3s;
        }

        .tab-button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>

<nav>
    <a href="#">Home</a>
    <a href="#">About</a>
    <a href="#">Help</a>
</nav>

<h1>Welcome to Pahana Edu Bookshop</h1>

<div class="tab-container">
    <a href="${pageContext.request.contextPath}/views/register.jsp" class="tab-button">Register</a>
    <a href="${pageContext.request.contextPath}/views/login.jsp" class="tab-button">Login</a>
</div>

</body>
</html>
