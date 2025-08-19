<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>View Customers - JSON</title>
    <link rel="stylesheet" href="css/styles8.css">

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
<br>
<h2>Customers List</h2>

<table id="customersTable" aria-label="Customers List Table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Email</th>
        <th scope="col">Phone</th>
        <th scope="col">Address</th>
        <th scope="col">accountNumber</th>
        <th scope="col">Created At</th>
    </tr>
    </thead>
    <tbody>
    <!-- Filled dynamically by JS -->
    </tbody>
</table>

<script>
    (function() {
        const contextPath = '<%= request.getContextPath() %>';
        fetch(contextPath + '/api/viewCustomers')
            .then(response => {
                if (!response.ok) throw new Error('HTTP ' + response.status);
                return response.json();
            })
            .then(customers => {
                const tbody = document.getElementById('customersTable').getElementsByTagName('tbody')[0];
                tbody.innerHTML = '';
                customers.forEach(c => {
                    const row = tbody.insertRow();
                    row.insertCell(0).textContent = c.id;
                    row.insertCell(1).textContent = c.name || '';
                    row.insertCell(2).textContent = c.email || '';
                    row.insertCell(3).textContent = c.phone || '';
                    row.insertCell(4).textContent = c.address || '';
                    row.insertCell(5).textContent = c.accountNumber || '';
                    row.insertCell(6).textContent = c.createdAt || '';
                });
            })
            .catch(error => {
                console.error('Error fetching customers:', error);
                const tbody = document.getElementById('customersTable').getElementsByTagName('tbody')[0];
                const row = tbody.insertRow();
                const cell = row.insertCell(0);
                cell.colSpan = 7;
                cell.style.color = 'red';
                cell.textContent = 'Failed to load customer data.';
            });
    })();
</script>
<button onclick="history.back()" class="back-btn">Back</button>
</body>
</html>
