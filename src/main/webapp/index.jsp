<style>
    body {
        font-family: Arial, sans-serif;
        text-align: center;
        padding-top: 100px;
        background-image: url('${pageContext.request.contextPath}/images/bookshop.jpeg');
        background-size: cover;
        background-repeat: no-repeat;
        background-position: center;
        color: white;
    }

    h1 {
        color: #ffffff;
        text-shadow: 2px 2px 4px rgba(0,0,0,0.7);
    }

    .tab-container {
        margin-top: 30px;
    }

    .tab-button {
        padding: 12px 25px;
        margin: 0 20px;
        font-size: 16px;
        cursor: pointer;
        background-color: #3498db;
        color: white;
        border: none;
        border-radius: 6px;
        text-decoration: none;
    }

    .tab-button:hover {
        background-color: #2980b9;
    }

    nav {
        position: absolute;
        top: 0;
        width: 100%;
        background-color: rgba(0,0,0,0.7);
        padding: 10px;
        text-align: right;
    }

    nav a {
        color: white;
        margin: 0 15px;
        text-decoration: none;
        font-weight: bold;
    }

    nav a:hover {
        text-decoration: underline;
    }
</style>
