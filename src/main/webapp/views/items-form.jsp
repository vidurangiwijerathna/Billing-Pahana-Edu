<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${item == null ? "Add New Item" : "Edit Item"} - PahanaEdu</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; max-width: 600px; margin: auto; }
        h2 { color: #34495e; }
        form label { display: block; margin-top: 15px; font-weight: bold; }
        form input[type="text"], form input[type="number"], form select {
            width: 100%; padding: 8px; margin-top: 5px; box-sizing: border-box;
            border: 1px solid #ccc; border-radius: 4px;
        }
        form input[type="submit"] {
            margin-top: 20px; background-color: #2980b9; color: white;
            border: none; padding: 10px 20px; border-radius: 4px;
            cursor: pointer;
        }
        form input[type="submit"]:hover {
            background-color: #1c5980;
        }
        a.back-link {
            display: inline-block; margin-top: 20px; text-decoration: none;
            color: #555;
        }
    </style>
</head>
<body>

<h2>${item == null ? "Add New Item" : "Edit Item"}</h2>

<form action="items" method="post">
    <input type="hidden" name="id" value="${item != null ? item.id : 0}"/>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required value="${item != null ? item.name : ''}"/>

    <label for="author">Author:</label>
    <input type="text" id="author" name="author" required value="${item != null ? item.author : ''}"/>

    <label for="price">Price (LKR):</label>
    <input type="number" step="0.01" id="price" name="price" required value="${item != null ? item.price : ''}"/>

    <label for="stock">Stock:</label>
    <input type="number" id="stock" name="stock" required value="${item != null ? item.stock : ''}"/>

    <label for="categoryId">Category:</label>
    <select id="categoryId" name="categoryId" required>
        <option value="">-- Select Category --</option>
        <c:forEach var="cat" items="${categoriesList}">
            <option value="${cat.id}" ${item != null && item.category != null && item.category.id == cat.id ? "selected" : ""}>${cat.name}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Save"/>
</form>

<a href="items?action=list" class="back-link">Back to Items List</a>

</body>
</html>
