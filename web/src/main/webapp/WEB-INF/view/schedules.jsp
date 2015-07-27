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
    <div class="jumbotron">
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/web/employees/">雇员管理</a>
                    <a class="navbar-brand" href="/web/users/">用户管理</a>
                    <a class="navbar-brand" href="/web/courses/">课程管理</a>
                    <a class="navbar-brand" href="/web/schedules/">课表管理</a>
                    <a class="navbar-brand" href="/web/customers/">顾客管理</a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/web/logout">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div><a href="/web/schedules/create">addSchedule</a></div>
            <div><a href="/web/schedules/createPrivate">addPrivateSchedule</a></div>
            <form id="publicScheduleUpdate" class="form-inline">
                <input id="publicScheduleId" type="hidden" name="id"><br>
                <div class="form-group">
                    <label for="courseId">Course</label>
                    <select id="mySelect" class="form-control"></select>
                </div>
                <div class="form-group">
                    <label for="time">Time</label>
                    <input type="date" class="form-control">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </form>
            <form id="privateScheduleUpdate" class="form-inline">
                <input type="hidden" name="id"><br>
                <div class="form-group">
                    <label for="courseId">Customer</label>
                    <select class="mySelect" class="form-control" name="customer">
                        <option value="${schedule.customer.id}"> ${schedule.customer.name}</option>
                        <c:forEach items="${customers}" var="customer">
                            <option value="${customer.id}">${customer.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="courseId">Course</label>
                    <select class="form-control" id="courseId" name="courseId">
                        <option value="${schedule.course.id}"> ${schedule.course.name}</option>
                        <c:forEach items="${courses}" var="course">
                            <option value="${course.id}">${course.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="time">Time</label>
                    <input type="date" class="form-control" id="time" name="time">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </form>
            <table class="table table-bordered">
                <caption><h2>公开课程表</h2></caption>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Coach</th>
                    <th>Time</th>
                    <th>Customer</th>
                    <th>Type</th>
                    <th>Operation</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${publicSchedules}" var="publicSchedule">
                    <tr>
                        <td>${publicSchedule.course.name}</td>
                        <td>${publicSchedule.course.employee.name}</td>
                        <td>${publicSchedule.time}</td>
                        <td></td>
                        <td>public</td>
                        <td>
                            <a href="#myModal" data-toggle="modal" data-id="${publicSchedule.id}" class="btn btn-warning btn-sm deleteSchedule">delete</a>
                            <a data-id="${publicSchedule.id}" class="btn btn-success btn-sm updatePublicSchedule">update</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:forEach items="${privateSchedules}" var="privateSchedule">
                    <tr>
                        <td>${privateSchedule.course.name}</td>
                        <td>${privateSchedule.course.employee.name}</td>
                        <td>${privateSchedule.time}</td>
                        <td>${privateSchedule.customer.name}</td>
                        <td>private</td>
                        <td>
                            <a href="#myModal" data-toggle="modal" data-id="${privateSchedule.id}" class="btn btn-warning btn-sm deleteSchedule">delete</a>
                            <a href="/web/schedules/${privateSchedule.id}" class="btn btn-success btn-sm updatePrivateSchedule">update</a>
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
                            <p>确认删除这节课吗？</p>
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
    <spring:url value="/lib/js/self/schedules.js" var="scheduleJs" />
    <script src="${jqueryJs}"></script>
    <script src="${bootstrapJs}"></script>
    <script src="${scheduleJs}"></script>
</body>
</html>
