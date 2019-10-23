<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>Solo3-Registration</title>
</head>
<body>
<h1>Регистрация</h1>

<form action="/registration" method="post">
    <table style=" width: 10%; border: 4px double black;">
        <tr>
            <td>Имя</td>
            <td><input name="name" type="text" required/></td>
        </tr>
        <tr>
            <td>E-mail</td>
            <td><input name="email" type="email" required/></td>
        </tr>
        <tr>
            <td> Пароль</td>
            <td><input name="password" type="password" required/></td>
        </tr>
        <tr>
            <td> Возраст</td>
            <td><input name="age" type="number" required min="1" max="200"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Зарегестрироваться"></td>
        </tr>
    </table>
</form>
<form action="/registration" method="get"><input type="submit" value="Очистить поля"></form>
</body>
</html>

