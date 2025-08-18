<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.book.model.Item" %>
<%@ page import="com.book.model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Bill</title>
    <link rel="stylesheet" href="css/styles5.css">
    <style>
        .item-row { margin: 8px 0; }
        .item-row select, .item-row input { margin-right: 8px; }
    </style>
    <script>
        function addRow() {
            const container = document.getElementById('itemsContainer');
            const row = document.createElement('div');
            row.className = 'item-row';

            // clone the server-rendered select template
            const tmpl = document.getElementById('itemSelectTemplate');
            const select = tmpl.cloneNode(true);
            select.removeAttribute('id'); // avoid duplicate IDs
            select.style.display = '';     // make it visible
            select.name = 'itemId[]';

            const qty = document.createElement('input');
            qty.type = 'number';
            qty.name = 'quantity[]';
            qty.min = '1';
            qty.value = '1';
            qty.required = true;

            const btn = document.createElement('button');
            btn.type = 'button';
            btn.textContent = 'Remove';
            btn.onclick = function(){ container.removeChild(row); };

            row.appendChild(select);
            row.appendChild(qty);
            row.appendChild(btn);

            container.appendChild(row);
        }

        window.onload = function() {
            if (document.getElementById('itemsContainer').children.length === 0) {
                addRow();
            }
        }
    </script>
</head>
<body>
<h2>Create Bill</h2>

<% String err = (String) request.getAttribute("error");
    if (err != null) { %>
<div style="color:red"><%= err %></div>
<% } %>

<form action="bills" method="post">
    <label>Customer:</label>
    <select name="customerId" required>
        <option value="">-- Select customer --</option>
        <%
            List<Customer> customers = (List<Customer>) request.getAttribute("customers");
            if (customers != null) {
                for (Customer c : customers) {
        %>
        <option value="<%= c.getId() %>">
            <%= c.getName() %> - <%= c.getAccountNumber() %>
        </option>
        <%      }
        }
        %>
    </select>

    <!-- Created by: you can set from session; for demo an input -->
    <div style="margin-top:10px;">
        <label>Created By (user id):</label>
        <input type="number" name="createdBy" value="1" required />
    </div>

    <h3>Items</h3>

    <!-- Hidden SELECT template rendered server-side -->
    <select id="itemSelectTemplate" style="display:none;">
        <option value="">-- Select item --</option>
        <%
            List<Item> items = (List<Item>) request.getAttribute("items");
            if (items != null) {
                for (Item it : items) {
        %>
        <option value="<%= it.getId() %>">
            <%= it.getName() %> - <%= it.getPrice() %>
        </option>
        <%      }
        }
        %>
    </select>

    <div id="itemsContainer"></div>
    <button type="button" onclick="addRow()">Add Item</button>

    <div style="margin-top:12px;">
        <button type="submit">Create Bill</button>
        <br>
        <a href="bills">Back to list</a>
        <!-- Back Button -->
        <button onclick="history.back()" class="back-btn">Back</button>
    </div>
</form>
</body>
</html>
