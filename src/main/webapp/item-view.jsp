<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title> View Books</title>
    <link rel="stylesheet" href="css/styles9.css">

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
<h2>Books List</h2>

<table id="itemsTable">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Author</th>
        <th>Price</th>
        <th>Stock</th>
        <th>Category</th>
    </tr>
    </thead>
    <tbody>
    <!-- Data will be inserted dynamically -->
    </tbody>
</table>

<script>
    fetch('api/viewItems')
        .then(response => response.json())
        .then(items => {
            const tbody = document.getElementById('itemsTable').getElementsByTagName('tbody')[0];
            items.forEach(item => {
                const row = tbody.insertRow();
                row.insertCell(0).textContent = item.id;
                row.insertCell(1).textContent = item.name;
                row.insertCell(2).textContent = item.author;
                row.insertCell(3).textContent = item.price;
                row.insertCell(4).textContent = item.stock;
                row.insertCell(5).textContent = item.categoryId;

            });
        })
        .catch(error => {
            console.error('Error fetching books:', error);
            const tbody = document.getElementById('itemsTable').getElementsByTagName('tbody')[0];
            const row = tbody.insertRow();
            const cell = row.insertCell(0);
            cell.colSpan = 6;
            cell.style.color = 'red';
            cell.textContent = 'Failed to load book data.';
        });
</script>
<button onclick="history.back()" class="back-btn">Back</button>
</body>
</html>
