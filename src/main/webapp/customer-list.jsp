<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp" />

<h2>Customer List</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Name</th><th>Email</th><th>Phone</th><th>Address</th><th>Account No.</th>
    </tr>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.name}</td>
            <td>${customer.email}</td>
            <td>${customer.phone}</td>
            <td>${customer.address}</td>
            <td>${customer.accountNumber}</td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="customer-form.jsp">Add New Customer</a>

</body>
</html>
