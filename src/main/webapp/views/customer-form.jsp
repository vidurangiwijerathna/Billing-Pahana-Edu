<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String formAction = (request.getAttribute("customer") != null) ? "update" : "insert";
%>
<html>
<head>
    <title>${formAction == "insert" ? "Add" : "Edit"} Customer - PahanaEdu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f2f5;
            padding: 30px;
        }
        h2 {
            color: #333;
        }
        form {
            background: white;
            padding: 20px;
            border-radius: 8px;
            width: 400px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input, textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            margin-top: 20px;
            padding: 10px 20px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
        }
        button:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>

<h2>${formAction == "insert" ? "Add" : "Edit"} Customer</h2>
<form method="post" action="customers">
    <input type="hidden" name="action" value="${formAction}">
    <c:if test="${formAction == 'update'}">
        <input type="hidden" name="id" value="${customer.id}">
    </c:if>

    <label for="name">Full Name</label>
    <input type="text" name="name" id="name" required value="${customer.name}">

    <label for="email">Email</label>
    <input type="email" name="email" id="email" required value="${customer.email}">

    <label for="mobile">Mobile</label>
    <input type="text" name="mobile" id="mobile" required value="${customer.mobile}">

    <label for="address">Address</label>
    <textarea name="address" id="address" required>${customer.address}</textarea>

    <button type="submit">${formAction == "insert" ? "Add" : "Update"} Customer</button>
</form>

</body>
</html>
