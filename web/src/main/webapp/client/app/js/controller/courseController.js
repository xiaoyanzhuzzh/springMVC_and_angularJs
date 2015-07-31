'use strict';

angular.module('userManagement')
    .controller('CourseController', function ($scope, $route, CourseService, EmployeeService) {

        $scope.$emit('to-parent-itemsListActive');

        $scope.courses = [];
        CourseService.getCourses(function (data) {

            $scope.courses = data;
        });

        EmployeeService.getEmployees(function(empolyees) {

            $scope.employees = EmployeeService.getAllCoaches(empolyees);
        });

        $scope.addNewCourse = function(course) {

            CourseService.addCourse(course, function() {

                CourseService.getCourses(function (data) {

                    $scope.courses = data;
                });
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

                CourseService.getCourses(function (data) {

                    $scope.courses = data;
                    EmployeeService.getEmployees(function(empolyees) {

                        $scope.employees = EmployeeService.getAllCoaches(empolyees);
                    });
                });
            });
        };

        $scope.deleteCurrentCourse = function(course) {
            CourseService.deleteCourse(course.id, function() {

                CourseService.getCourses(function (data) {

                    $scope.courses = data;
                    EmployeeService.getEmployees(function(empolyees) {

                        $scope.employees = EmployeeService.getAllCoaches(empolyees);
                    });
                });
            })
        };
    });