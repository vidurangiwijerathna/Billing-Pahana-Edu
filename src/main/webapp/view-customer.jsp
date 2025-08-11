<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Customers - JSON</title>
    <link rel="stylesheet" href="css/styles1.css">
</head>
<body>

<!-- Navigation Tabs -->
<div class="tabs">
    <a href="admin-db.jsp" class="tab">Admin Page</a>
    <a href="customer-form.jsp" class="tab"> Add New Customer</a>
</div>

<h2 style="text-align: center;">Customer List</h2>

<!-- Centered Table -->
<div class="table-container">
    <table class="styled-table" id="customersTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Account Number</th>
        </tr>
        </thead>
        <tbody>
        <!-- Filled dynamically by JS -->
        </tbody>
    </table>
</div>

<!-- Fetch Customers from API -->
<script>
    fetch('/api/viewCustomer')
        .then(response => response.json())
        .then(customers => {
            const tbody = document.getElementById('customersTable').getElementsByTagName('tbody')[0];
            if (customers.length === 0) {
                const row = tbody.insertRow();
                const cell = row.insertCell(0);
                cell.colSpan = 6;
                cell.textContent = "No customers found";
                cell.style.textAlign = "center";
                cell.style.fontStyle = "italic";
            } else {
                customers.forEach(customer => {
                    const row = tbody.insertRow();
                    row.insertCell(0).textContent = customer.id;
                    row.insertCell(1).textContent = customer.name;
                    row.insertCell(2).textContent = customer.email;
                    row.insertCell(3).textContent = customer.phone;
                    row.insertCell(4).textContent = customer.address;
                    row.insertCell(5).textContent = customer.account_number;

                });
            }
        })
        .catch(error => console.error('Error fetching customers:', error));
</script>

<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>

</body>
</html>
