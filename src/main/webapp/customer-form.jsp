<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Customer</title>
    <link rel="stylesheet" href="css/styles7.css">
</head>
<body>

<nav>
    <div class="nav-left">
        <a href="index.jsp">Home</a>
        <a href="index.jsp">About</a>
        <a href="help.jsp">Help</a>
    </div>
    <div class="nav-right">
        <a href="logout.jsp">Logout</a>
    </div>
</nav>

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

<h2>Add New Customer</h2>
<form method="post" action="customers">
    Name: <input type="text" name="name" required><br>
    Email: <input type="email" name="email" required><br>
    Phone: <input type="text" name="phone"><br>
    Address: <textarea name="address"></textarea><br>
    Account Number: <input type="text" name="accountNumber" required><br><br>
    <input type="submit" value="Add Customer">
    <br>
    <br>
    <a href="customer-list.jsp" class="tab"> Customer List </a>
</form>

<!-- Back Button -->
<button onclick="history.back()" class="back-btn">Back</button>
</body>
</html>
