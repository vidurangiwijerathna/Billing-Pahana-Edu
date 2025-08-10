<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Users - JSON</title>
    <link rel="stylesheet" href="css/styles1.css">
</head>
<body>

<!-- Navigation Tabs -->
<div class="tabs">
    <a href="admin-db.jsp" class="tab">Admin Page</a>
    <a href="register.jsp" class="tab"> Add New User</a>

</div>

<h2 style="text-align: center;">Users List</h2>

<!-- Centered Table -->
<div class="table-container">
    <table class="styled-table" id="usersTable">
        <thead>
        <tr>
            <th>ID</th><th>Name</th><th>Email</th><th>Role</th>
        </tr>
        </thead>
        <tbody>
        <!-- Filled dynamically by JS -->
        </tbody>
    </table>
</div>

<!-- Fetch Users from API -->
<script>
    fetch('api/users')
        .then(response => response.json())
        .then(users => {
            const tbody = document.getElementById('usersTable').getElementsByTagName('tbody')[0];
            if (users.length === 0) {
                const row = tbody.insertRow();
                const cell = row.insertCell(0);
                cell.colSpan = 4;
                cell.textContent = "No users found";
                cell.style.textAlign = "center";
                cell.style.fontStyle = "italic";
            } else {
                users.forEach(user => {
                    const row = tbody.insertRow();
                    row.insertCell(0).textContent = user.id;
                    row.insertCell(1).textContent = user.name;
                    row.insertCell(2).textContent = user.email;
                    row.insertCell(3).textContent = user.role;
                });
            }
        })
        .catch(error => console.error('Error fetching users:', error));
</script>

<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>

</body>
</html>
