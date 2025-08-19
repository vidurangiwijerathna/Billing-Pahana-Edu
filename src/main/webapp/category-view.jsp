
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>View Book Categories</title>
    <link rel="stylesheet" href="css/styles8.css">

</head>
<body>
<nav>
    <div class="nav-left">
        <a href="index.jsp">Home</a>
        <a href="index.jsp">About</a>
        <a href="help.jsp">Help</a>
        <a href="logout.jsp">Logout</a>
    </div>
</nav>
<h2> Book Categories</h2>

<table id="categoriesTable">
    <thead>
    <tr>
        <th>ID</th>
        <th>Category Name</th>
    </tr>
    </thead>
    <tbody>
    <!-- Data will be inserted dynamically -->
    </tbody>
</table>

<script>
    const contextPath = '<%= request.getContextPath() %>';

    fetch(contextPath + '/api/viewCategories')
        .then(response => response.json())
        .then(categories => {
            const tbody = document.getElementById('categoriesTable').getElementsByTagName('tbody')[0];
            categories.forEach(cat => {
                const row = tbody.insertRow();
                row.insertCell(0).textContent = cat.id;
                row.insertCell(1).textContent = cat.categoryName; // ✅ match your field name in ItemCategory
            });
        })
        .catch(error => {
            console.error('Error fetching categories:', error);
            const tbody = document.getElementById('categoriesTable').getElementsByTagName('tbody')[0];
            const row = tbody.insertRow();
            const cell = row.insertCell(0);
            cell.colSpan = 2;
            cell.style.color = 'red';
            cell.textContent = 'Failed to load category data.';
        });
</script>

<button onclick="history.back()" class="back-btn">Back</button>
</body>
</html>
