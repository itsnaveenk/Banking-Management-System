<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .balance-container {
            text-align: center;
            margin-bottom: 20px;
        }

        .balance {
            font-size: 24px;
        }

        .button-container {
            text-align: center;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4caf50;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            margin: 10px;
            cursor: pointer;
        }

        .button:hover {
            background-color: #45a049;
        }

        .success-message {
            color: green;
            font-weight: bold;
        }

        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Welcome to Your Banking Dashboard</h2>
    <div class="balance-container">
        <p class="balance">Your Account Balance:
                <% if (request.getAttribute("balance") != null) {%>
                <%= request.getAttribute("balance") %>
        <form action="checkBalance" method="get">
            <input class="button" type="submit" value="Refresh">
        </form>
        <% } else { %>
        <form action="checkBalance" method="get">
            <input class="button" type="submit" value="Show">
        </form>
        <% } %>
        </p>
    </div>
    <div class="button-container">
        <a href="./transfer.jsp" class="button">Transfer Money</a>
        <a href="./deposit.jsp" class="button">Deposit Money</a>
        <a href="./withdraw.jsp" class="button">Withdraw Money</a>
        <a href="logout" class="button">Logout</a>
    </div>
</div>

<% if (request.getAttribute("successMessage") != null) { %>
<p class="success-message"><%= request.getAttribute("successMessage") %>
</p>
<% } %>
<% if (request.getAttribute("errorMessage") != null) { %>
<p class="error-message"><%= request.getAttribute("errorMessage") %>
</p>
<% } %>

</body>
</html>
