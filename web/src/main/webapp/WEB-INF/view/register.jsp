<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        用户注册页面
    </title>
    <spring:url value="/lib/css/self/register.css" var="registerCss"/>
    <link href="${registerCss}" rel="stylesheet"/>
</head>
<body>
    <div id="registerTitle">
        <h2>用户注册</h2>
    </div>
    <form class="register" id="register" action="/web/register" method="post">
        <div class="formContent">
            <label class="labelContent" for="nickName">Nickname:</label>
            <input type="text" id="nickName" name="nickName" placeholder="NickName">
        </div>
        <div class="formContent">
            <label class="labelContent" for="name">Name:</label>
            <input type="text" id="name" name="name" placeholder="Name">
        </div>
        <div class="formContent">
            <label class="labelContent">Role:</label>
            <label>
                <input type="radio" name="role" value="coach"> coach
            </label>
            <label>
                <input type="radio" name="role" value="hr"> hr
            </label>
            <label>
                <input type="radio" name="role" value="ops"> ops
            </label>
        </div>
        <div class="formContent">
            <label class="labelContent" for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Password">
        </div>
        <div class="formContent">
            <label class="labelContent">Gender:</label>
            <label>
                <input type="radio" name="gender" value="female"> female
            </label>
            <label>
                <input type="radio" name="gender" value="male"> male
            </label>
        </div>
        <div class="formContent">
            <label class="labelContent" for="age">Age:</label>
            <input type="text" id="age" name="age" placeholder="Age">
        </div>
        <div class="formContent">
            <label class="labelContent" for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Email">
        </div>
        <div id="registerButton" class="formContent">
            <button  type="submit">Register</button>
        </div>
    </form>
    <spring:url value="/lib/js/jquery-1.11.1.min.js" var="jqueryJs" />
    <script src="${jqueryJs}"></script>
</body>
</html>
