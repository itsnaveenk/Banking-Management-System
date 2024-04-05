<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Withdraw Money</title>
    <style>
        /* Add your CSS here */
    </style>
</head>
<body>
<% if (request.getAttribute("successMessage") != null) { %>
<p class="success-message"><%= request.getAttribute("successMessage") %>
</p>
<% } %>
<% if (request.getAttribute("errorMessage") != null) { %>
<p class="error-message"><%= request.getAttribute("errorMessage") %>
</p>
<% } %>
<form action="withdraw" method="post">
    <label for="amount">Amount:</label>
    <input type="number" id="amount" name="amount" required>
    <input type="submit" value="Withdraw">
</form>
</body>
</html>
