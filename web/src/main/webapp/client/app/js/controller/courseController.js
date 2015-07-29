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
        });

        $scope.addNewCourse = function(course) {
            console.log(course);

            CourseService.addCourse(course, function() {
                $route.reload();
            })
        }
    });