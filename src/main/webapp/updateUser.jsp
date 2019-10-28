<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>Solo3-Update User: ${name}</title>
</head>
<body>

Редактировать пользователя
<h5 style="color: red">${message1} </h5>
<form action="/admin/updateUser" method="post">
    <table border="2">
        <tr>
            <td align="right">ID:<input type="hidden" name="id" value="${id}" required></td>
            <td>${id}</td>
        </tr>
        <tr>
            <td align="right">Имя:</td>
            <td><input type="text" name="name" value="${name}" required></td>
        </tr>
        <tr>
            <td align="right">Пароль:</td>
            <td><input type="password" name="password" value="${password}" required></td>
        </tr>
        <tr>
            <td align="right">E-mail:</td>
            <td><input type="email" name="mail" value="${mail}" required></td>
        </tr>
        <tr>
            <td align="right">Возраст:</td>
            <td><input type="number" name="age" value="${age}" required min="1" max="200"></td>
        </tr>

    </table>
    <input type="submit" value="Обновить">
</form>
</body>
</html>

