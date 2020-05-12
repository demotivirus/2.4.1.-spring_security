<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<c:url value="/admin/addUser" var="addUrl"/>
<c:url value="/admin/editUser" var="editUrl"/>
<div align="center">
    <p>User page</p>
    <form action="${empty user.password ? addUrl : editUrl}" name="user" method="POST">
        <p><input type="text" name="login" placeholder="Login" value="${user.login}" maxlength="50" required>
        <p><input type="password" name="password" placeholder="Password" value="${user.password}" maxlength="50" required>
        <p><input type="text" name="name" placeholder="Name" value="${user.name}" maxlength="20" required><br>
        <td><input type="checkbox" name="admin" value="1">ADMIN<br>
            <input type="checkbox" name="user" value="2">USER<br>
        </td>
        <p><input type="submit" value="Submit"></p>
    </form>
</div>
</body>
</html>
