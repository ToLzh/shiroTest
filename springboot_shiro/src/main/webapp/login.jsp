<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<>
    login
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        用户名:<input type="text" name="username"><br/>
        密码:<input type="password" name="password">
        <input type="submit" value="提交">
    </form>

</body>
</html>