'use strict';

angular.module('userManagement')
    .controller('ScheduleController', function ($scope, $route, ScheduleService, EmployeeService) {

        $scope.$emit('to-parent-itemsListActive');

        $scope.publicSchedules = [];
        ScheduleService.getSchedules(function(data) {

            $scope.schedules = data;
        });

        EmployeeService.getEmployees(function(empolyees) {

            $scope.employees = EmployeeService.getAllCoaches(empolyees);
        });

        $scope.addNewCourse = function(course) {

            CourseService.addCourse(course, function() {

                CourseService.getCourses(function (data) {

                    $scope.courses = data;
                    EmployeeService.getEmployees(function(empolyees) {

                        $scope.employees = EmployeeService.getAllCoaches(empolyees);
                    });
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