'use strict';

angular.module('userManagement')
    .controller('CourseController', function ($scope, $route, CourseService, EmployeeService) {

        $scope.$emit('to-parent-itemsListActive');

        $scope.courses = [];
        CourseService.getCourses(function (data) {

            $scope.courses = data;
        });

        EmployeeService.getEmployees(function(data) {

            $scope.employees = EmployeeService.getAllCoaches(data);
            console.log($scope.employees);
        });
    });