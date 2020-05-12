<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form th:action="@{/login}" method="POST">
    <div>
        <input type="text" name="name" placeholder="Name">
    </div>

    <div>
        <input type="password" name="password" placeholder="Password">
    </div>
    <button th:marginheight="5px" type="submit">Sign in</button>
</form>
</body>
</html>
