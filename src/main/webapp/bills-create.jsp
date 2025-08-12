<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.book.dto.ItemDTO" %>
<%@ page import="com.book.dto.CustomerDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Bill</title>
    <link rel="stylesheet" href="css/styles5.css">
    <script>
        function addRow() {
            const container = document.getElementById('itemsContainer');
            const idx = container.children.length;
            const row = document.createElement('div');
            row.className = 'item-row';
            row.innerHTML = `
                <select name="itemId[]" required>
                    <option value="">-- Select item --</option>
                    <%
                        List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("items");
                        if (items != null) {
                            for (ItemDTO it : items) {
                    %>
                        <option value="<%= it.getId() %>"><%= it.getName() %> - <%= it.getPrice() %></option>
                    <%      }
                        }
                    %>
                </select>
                <input type="number" name="quantity[]" value="1" min="1" required />
                <button type="button" onclick="removeRow(this)">Remove</button>
            `;
            container.appendChild(row);
        }

        function removeRow(btn) {
            const row = btn.parentNode;
            row.parentNode.removeChild(row);
        }

        window.onload = function() {
            // Add one row by default
            if (document.getElementById('itemsContainer').children.length === 0) {
                addRow();
            }
        }
    </script>
</head>
<body>
<h2>Create Bill</h2>
<form action="bills" method="post">
    <label>Customer:</label>
    <select name="customerId" required>
        <option value="">-- Select customer --</option>
        <%
            List<CustomerDTO> customers = (List<CustomerDTO>) request.getAttribute("customers");
            if (customers != null) {
                for (CustomerDTO c : customers) {
        %>
        <option value="<%= c.getId() %>"><%= c.getName() %> - <%= c.getAccountNumber() %></option>
        <%      }
        }
        %>
    </select>

    <h3>Items</h3>
    <div id="itemsContainer"></div>
    <button type="button" onclick="addRow()">Add Item</button>

    <div style="margin-top:12px;">
        <button type="submit">Create Bill</button>
        <br>
        <a href="bills-list.jsp">Back to list</a>
        <br>
        <a href="bill-view.jsp">View Bill</a>
    </div>
</form>
</body>
</html>
