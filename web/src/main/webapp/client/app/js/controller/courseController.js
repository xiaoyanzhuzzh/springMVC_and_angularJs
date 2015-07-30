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

            CourseService.addCourse(course, function() {

                $route.reload();
            })
        };

        $scope.showUpdateCourse = false;
        $scope.updateCurrentCourse = function(course) {
            $scope.showUpdateCourse = true;
            $scope.currentCourse = {
                id: course.id,
                name: course.name,
                employee: course.employee
            }
        };

        $scope.updateCourse = function(currentCourse) {

            CourseService.updateCourse(currentCourse, function () {

                $route.reload();
            });
        };

        $scope.deleteCurrentCourse = function(course) {
            CourseService.deleteCourse(course.id, function() {

                $route.reload();
            })
        };
    });