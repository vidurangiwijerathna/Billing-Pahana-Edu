<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create New Bill - PahanaEdu</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; max-width: 900px; margin: auto; }
        h2 { color: #34495e; }
        label { display: block; margin-top: 15px; font-weight: bold; }
        select, input[type="number"] {
            padding: 6px; width: 100%; max-width: 300px; margin-top: 5px; border: 1px solid #ccc; border-radius: 4px;
        }
        table {
            width: 100%; border-collapse: collapse; margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd; padding: 10px; text-align: center;
        }
        th {
            background-color: #2980b9; color: white;
        }
        input[type="submit"] {
            margin-top: 20px; background-color: #27ae60; color: white;
            border: none; padding: 10px 20px; border-radius: 4px; cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #1e8449;
        }
        .add-row-btn {
            margin-top: 15px;
            padding: 6px 12px;
            background-color: #2980b9;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .add-row-btn:hover {
            background-color: #1c5980;
        }
    </style>
    <script>
        function addRow() {
            const table = document.getElementById("itemsTableBody");
            const rowCount = table.rows.length;
            const newRow = table.insertRow(rowCount);

            // Item select
            const cell1 = newRow.insertCell(0);
            const selectHTML = document.getElementById("itemSelectTemplate").innerHTML;
            cell1.innerHTML = selectHTML.replace(/__index__/g, rowCount);

            // Quantity input
            const cell2 = newRow.insertCell(1);
            cell2.innerHTML = '<input type="number" name="billItems[' + rowCount + '].quantity" min="1" value="1" required>';

            // Price display (read-only or you can get from backend)
            const cell3 = newRow.insertCell(2);
            cell3.innerHTML = '<input type="number" name="billItems[' + rowCount + '].price" step="0.01" min="0" value="0" readonly>';

            // Remove button
            const cell4 = newRow.insertCell(3);
            cell4.innerHTML = '<button type="button" onclick="removeRow(this)">Remove</button>';
        }

        function removeRow(button) {
            const row = button.parentNode.parentNode;
            row.parentNode.removeChild(row);
        }
    </script>
</head>
<body>

<h2>Create New Bill</h2>

<form action="bills" method="post">

    <label for="customerId">Select Customer:</label>
    <select id="customerId" name="customerId" required>
        <option value="">-- Select Customer --</option>
        <c:forEach var="cust" items="${customersList}">
            <option value="${cust.id}">${cust.name}</option>
        </c:forEach>
    </select>

    <table>
        <thead>
        <tr>
            <th>Item</th>
            <th>Quantity</th>
            <th>Price (LKR)</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody id="itemsTableBody">
        <!-- Start with 1 row -->
        <tr>
            <td>
                <select name="billItems[0].itemId" required>
                    <c:forEach var="item" items="${itemsList}">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="number" name="billItems[0].quantity" min="1" value="1" required></td>
            <td><input type="number" name="billItems[0].price" step="0.01" min="0" value="0" readonly></td>
            <td><button type="button" onclick="removeRow(this)">Remove</button></td>
        </tr>
        </tbody>
    </table>

    <button type="button" class="add-row-btn" onclick="addRow()">Add Item</button>

    <input type="submit" value="Create Bill"/>

</form>

<a href="bills?action=list" style="display: inline-block; margin-top: 20px;">Back to Bills List</a>

<!-- Hidden template for item select -->
<div id="itemSelectTemplate" style="display:none;">
    <select name="billItems[__index__].itemId" required>
        <c:forEach var="item" items="${itemsList}">
            <option value="${item.id}">${item.name}</option>
        </c:forEach>
    </select>
</div>

</body>
</html>
