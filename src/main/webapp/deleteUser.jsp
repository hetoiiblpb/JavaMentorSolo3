<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>Solo1-Update</title>
</head>
<body>

<h1>Удалить пользователя?</h1>

<form action="/deleteUser" method="post">
    <table border="2">
        <tr>
            <td align="right">ID:<input type="hidden" name="id" value="${id}"></td>
            <td>${id}</td>
        </tr>
        <tr>
            <td align="right">Name:</td>
            <td>${name}</td>
        </tr>
        <tr>
            <td align="right">E-mail:</td>
            <td>${mail}</td>
        </tr>
        <tr>
            <td align="right">Age:</td>
            <td>${age}</td>
        </tr>

    </table>
    <input type="submit" value="Удалить">
</form>
<form action="/allUsers" method="get"><input type="submit" value="Отмена"></form>
</body>
</html>

