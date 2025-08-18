<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Item Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
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
<br>
<h1 class="dashboard-title">Welcome cashier, <%= session.getAttribute("userName") %> </h1>


<!-- Back Button -->
<button onclick="history.back()" class="back-btn">Back</button>
<footer>
    &copy; 2025 Pahana Edu. All rights reserved.
</footer>
</body>
</html>
