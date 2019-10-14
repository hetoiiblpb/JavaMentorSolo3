<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>Solo1-registration</title>
</head>
<body>
<form action="/addUser" method="post">
    <table style=" width: 10%; border: 4px double black;">
        <tr><td>Name </td> <td><input name="name" type="text" /></td></tr>
        <tr><td> E-mail </td> <td> <input name="mail" type="text"/></td></tr>
        <tr><td> age </td> <td> <input name="age" type="text"/></td></tr>
        <tr><td><input type="submit" value="Добавить"></td></tr>
    </table>
</form>
<form action="/allUsers" method="get"><input type="submit" value="Отмена"></form>
</body>
</html>

