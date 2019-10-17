<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>Solo2-registration</title>
</head>
<body>
<h1>Добавление пользователя</h1>
<h5 style="color: red">${message1} </h5>
<form action="/addUser" method="post">
    <table style=" width: 10%; border: 4px double black;">
        <tr>
            <td>Имя</td>
            <td><input name="name" type="text" required/></td>
        </tr>
        <tr>
            <td> E-mail</td>
            <td><input name="mail" type="email" required/></td>
        </tr>
        <tr>
            <td> Возраст</td>
            <td><input name="age" type="number" required min="1" max="200"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Добавить"></td>
        </tr>
    </table>
</form>
<form action="/allUsers" method="get"><input type="submit" value="Отмена"></form>
</body>
</html>

