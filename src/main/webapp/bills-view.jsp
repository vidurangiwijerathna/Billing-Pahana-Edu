<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.book.dto.BillDTO, com.book.dto.BillItemDTO, java.util.List" %>
<html>
<head>
    <title>Bill Details</title>
</head>
<body>
<%
    BillDTO bill = (BillDTO) request.getAttribute("bill");
    if (bill == null) {
%>
<p>Bill not found.</p>
<%  } else { %>
<h2>Bill #<%= bill.getId() %></h2>
<p>Customer ID: <%= bill.getCustomerId() %></p>
<p>Created At: <%= bill.getCreatedAt() %></p>
<p>Created By: <%= bill.getCreatedBy() %></p>
<p>Total: <%= bill.getTotalAmount() %></p>

<h3>Items</h3>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Item ID</th>
        <th>Qty</th>
        <th>Unit Price</th>
        <th>Line Total</th>
    </tr>
    <%
        List<BillItemDTO> its = bill.getBillItems();
        if (its != null) {
            for (BillItemDTO i : its) {
                double line = (i.getPrice() != null ? i.getPrice() : 0.0) * (i.getQuantity() != null ? i.getQuantity() : 0);
    %>
    <tr>
        <td><%= i.getItemId() %></td>
        <td><%= i.getQuantity() %></td>
        <td><%= i.getPrice() %></td>
        <td><%= line %></td>
    </tr>
    <%      }
    }
    %>
</table>
<% } %>

<br/>
<a href="bills">Back</a>
<!-- Back Button -->
<button onclick="history.back()" class="back-btn"> Back</button>

</body>
</html>
