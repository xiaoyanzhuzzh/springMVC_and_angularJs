<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Javaee首页</title>
    <spring:url value="/lib/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
</head>
<body>
<c:import url="layout.jsp">
</c:import>
<div class="jumbotron">
    <div class="container">
        <div><a class="btn btn-success" href="/web/users/create">addUser</a></div>
        <table class="table table-bordered">
            <caption><h2>用户信息表</h2></caption>
            <thead>
            <tr>
                <th>NickName</th>
                <th>Role</th>
                <th>Name</th>
                <th>Gender</th>
                <th>Age</th>
                <th>Email</th>
                <th>Operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.employee.role}</td>
                    <td>${user.employee.name}</td>
                    <td>${user.employee.gender}</td>
                    <td>${user.employee.age}</td>
                    <td>${user.employee.email}</td>
                    <td>
                        <a href="#myModal" data-toggle="modal" data-id="${user.id}" class="btn btn-warning btn-sm deleteUser">delete</a>
                        <a href="/web/users/${user.id}" class="btn btn-danger btn-sm updateUser">update</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div id="myModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>确认删除用户吗？</p>
                    </div>
                    <div class="modal-footer">
                        <button class="confirmDelete" type="button" class="btn btn-warning">Confirm Delete</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<spring:url value="/lib/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/lib/js/jquery-1.11.1.min.js" var="jqueryJs" />
<spring:url value="/lib/js/self/users.js" var="userJs" />
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${userJs}"></script>
</body>
</html>
