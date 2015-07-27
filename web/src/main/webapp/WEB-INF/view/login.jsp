<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>登陆页面</title>
    <spring:url value="/lib/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/lib/css/self/login.css" var="loginCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${loginCss}" rel="stylesheet"/>
</head>
<body>
    <div id="loginTitle">
        <h2>用户登陆</h2>
    </div>
    <form class="login" id="login" action="/web/login" method="post">
        <div class="formContent">
            <label class="labelContent" for="name">Nickname:</label>
            <input type="text" id="name" name="name" placeholder="NickName">
        </div>
        <div class="formContent">
            <label class="labelContent" for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Password">
        </div>

        <div id="registerButton" class="formContent">
            <button  type="submit">login</button>
        </div>
    </form>
    <spring:url value="/lib/js/bootstrap.min.js" var="bootstrapJs" />
    <spring:url value="/lib/js/jquery-1.11.1.min.js" var="jqueryJs" />
    <script src="${jqueryJs}"></script>
    <script src="${bootstrapJs}"></script>
</body>
</html>
