<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of Users</title>
</head>
<body>
<div align="center">
    <h1>User Table</h1>
    <table>
        <tr>
            <th>id</th>
            <th>Login</th>
            <th>Password</th>
            <th>Name</th>

        </tr>
        <c:forEach var="user" items="${usersList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.name}</td>
                <td>
                    <a href="/editUser?id=${user.id}"> Edit</a>
                    <a href="/deleteUser/${user.id}"> Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>

    <c:url value="/admin/addUser" var="add"/>
    <a href="${add}">Add new user</a>
</div>
</body>
</html>
