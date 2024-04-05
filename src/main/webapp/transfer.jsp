<%--
  Created by IntelliJ IDEA.
  User: conne
  Date: 05-04-2024
  Time: 01:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transfer Money</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 300px;
            text-align: center;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        input[type="email"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4caf50;
            border: none;
            color: #fff;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .success-message {
            color: green;
            font-weight: bold;
        }
    </style>

</head>
<body>
<h1>Transfer Money</h1>
<% if (request.getAttribute("successMessage") != null) { %>
<p class="success-message"><%= request.getAttribute("successMessage") %>
</p>
<% } %>
<form action="transfer" method="POST">
    <label for="receiverEmail">Recipient's Email:</label>
    <input type="email" id="receiverEmail" name="receiverEmail" required>
    <label for="amount">Amount:</label>
    <input type="number" id="amount" name="amount" required>
    <input type="submit" value="Transfer">
</form>
</body>
</html>
