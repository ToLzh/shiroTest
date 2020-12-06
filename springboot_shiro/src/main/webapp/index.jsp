<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    hello world!
    <a href="${pageContext.request.contextPath}/user/logout">点击退出</a>

    <ul>
        <li><a>用户管理</a>
            <ul>
                <shiro:hasPermission name="user:create:*">
                    <li><a href="#">添加</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:delete:*">
                    <li><a href="#">删除</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:update:*">
                    <li><a href="#">更新</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:find:*">
                    <li><a href="#">查询</a></li>
                </shiro:hasPermission>
            </ul>
        </li>
        <shiro:hasRole name="admin">
            <li><a>商品管理</a></li>
            <li><a>订单管理</a></li>
            <li><a>物流管理</a></li>
        </shiro:hasRole>

    </ul>
</body>
</html>