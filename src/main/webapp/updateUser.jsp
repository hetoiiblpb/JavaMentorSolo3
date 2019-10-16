<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>Solo2-Update</title>
</head>
<body>

Редактировать пользователя

<form action="/updateUser" method="post">
    <table border="2">
        <tr>
            <td align="right">ID:<input type="hidden" name="id" value="${id}"></td>
            <td>${id}</td>
        </tr>
        <tr>
            <td align="right">Имя:</td>
            <td><input type="text" name="name" value="${name}" placeholder="${name}"></td>
        </tr>
        <tr>
            <td align="right">E-mail:</td>
            <td><input type="text" name="mail" value="${mail}"></td>
        </tr>
        <tr>
            <td align="right">Возраст:</td>
            <td><input type="text" name="age" value="${age}"></td>
        </tr>

    </table>
    <input type="submit" value="Обновить">
</form>
</body>
</html>

