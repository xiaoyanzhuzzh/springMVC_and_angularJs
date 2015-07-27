<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>模板页</title>
    <spring:url value="/lib/css/self/layout.css" var="layoutCss" />
    <link href="${layoutCss}" rel="stylesheet" />
</head>
<body>
    <div class="header">
        <div class="topBar">
            <div class="welcomeTop">
                <h1 id="gymName">
                    慧小猪_GYM
                </h1>
            </div>
        </div>
        <div id="logout">
            <a class="link" href="/web/logout/">Logout</a>
        </div>
        <div class="navigation">
            <ul>
                <li><a class="link" href="/web/employees/">员工管理</a></li>
                <li><a class="link" href="/web/users/">用户管理</a></li>
                <li><a class="link" href="/web/courses/">课程管理</a></li>
                <li><a class="link" href="/web/schedules/">课表管理</a></li>
                <li><a class="link" href="/web/customers/">顾客管理</a></li>
            </ul>
        </div>
    </div>
</body>
</html>
