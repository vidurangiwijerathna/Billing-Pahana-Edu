<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.book.dto.CustomerDTO" %>
<%@ page import="com.book.model.Customer" %>
<%@ page import="com.book.service.CustomerService" %>
<html>
<head>
    <title>Manage Customer</title>
    <link rel="stylesheet" href="css/styles7.css">


</head>
<body>

<%
    String message = null;
    CustomerDTO customer = null;
    String nameParam = request.getParameter("name");

    if (nameParam != null && !nameParam.trim().isEmpty()) {
        try {
            CustomerService service = new CustomerService();
            customer = service.getCustomerByName(nameParam.trim());

            if (customer == null) {
                message = "Customer not found!";
            }
        } catch (Exception e) {
            message = "Server error occurred!";
            e.printStackTrace();
        }
    }
%>

<h2>🔍 Search & ✏️ Update Customer</h2>

<!-- ✅ Search Form -->
<form method="get" action="update-customer.jsp">
    <label>Enter Customer Name:</label>
    <input type="text" name="name" placeholder="Search by customer name" required>
    <input type="submit" value="Search">
</form>

<!-- ✅ Message Display -->
<% if (request.getParameter("success") != null) { %>
<p class="success">✅ Customer updated successfully!</p>
<% } else if (message != null) { %>
<p class="error"><%= message %></p>
<% } %>

<!-- ✅ Update Form (Only if customer is found) -->
<% if (customer != null) { %>
<hr>
<form method="post" action="updateCustomer">
    <input type="hidden" name="id" value="<%= customer.getId() %>">

    <label>Name:</label>
    <input type="text" name="name" value="<%= customer.getName() != null ? customer.getName() : "" %>" required>

    <label>Email:</label>
    <input type="email" name="email" value="<%= customer.getEmail() != null ? customer.getEmail() : "" %>" required>

    <label>Phone:</label>
    <input type="text" name="phone" value="<%= customer.getPhone() != null ? customer.getPhone() : "" %>" required>

    <label>Address:</label>
    <input type="text" name="address" value="<%= customer.getAddress() != null ? customer.getAddress() : "" %>" required>

    <label>Account Number:</label>
    <input type="text" name="accountNumber" value="<%= customer.getAccountNumber() != null ? customer.getAccountNumber() : "" %>" required>



    <input type="submit" value="Update Customer">
</form>
<% } %>

</body>
</html>
