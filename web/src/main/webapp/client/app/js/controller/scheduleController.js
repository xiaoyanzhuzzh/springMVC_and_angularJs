'use strict';

angular.module('userManagement')
    .controller('ScheduleController', function ($scope, $route, $filter, ScheduleService, CourseService, CustomerService) {

        $scope.$emit('to-parent-itemsListActive');

        $scope.publicSchedules = [];
        $scope.schedule = {

            time: new Date()
        };
        
        ScheduleService.getSchedules(function(data) {

            $scope.schedules = data;
        });

        CourseService.getCourses(function(data) {

            $scope.courses = data;
        });

        CustomerService.getCustomers(function (data) {

            $scope.customers = data;
        });

        $scope.addNewSchedule = function(schedule) {

            ScheduleService.addSchedule(schedule, function() {

            })
        };

        $scope.showUpdateSchedule = false;
        $scope.updateCurrentSchedule = function(schedule) {
            $scope.showUpdateSchedule = true;
            $scope.currentSchedule = {
                id: schedule.id,
                name: schedule.name,
                employee: schedule.employee
            }
        };

        $scope.updateSchedule = function(currentSchedule) {

            ScheduleService.updateSchedule(currentSchedule, function () {

                ScheduleService.getSchedules(function (data) {

                    $scope.schedules = data;
                    EmployeeService.getEmployees(function(empolyees) {

                        $scope.employees = EmployeeService.getAllCoaches(empolyees);
                    });
                });
            });
        };

        $scope.deleteCurrentSchedule = function(schedule) {
            ScheduleService.deleteSchedule(schedule.id, function() {

                ScheduleService.getSchedules(function (data) {

                    $scope.schedules = data;
                    EmployeeService.getEmployees(function(empolyees) {

                        $scope.employees = EmployeeService.getAllCoaches(empolyees);
                    });
                });
            })
        };
    });