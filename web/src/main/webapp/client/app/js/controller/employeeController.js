'use strict';

angular.module('userManagement')
    .controller('EmployeeController', function ($scope, EmployeeService) {

        $scope.$emit('to-parent-itemsListActive');

        $scope.employees = [];
        EmployeeService.getEmployees(function(data) {
            $scope.employees = data;
        });

        $scope.addNewEmployee = function(employee) {

            EmployeeService.addEmployee(employee, function() {

                EmployeeService.getEmployees(function(data) {
                    $scope.employees = data;
                })
            });
        };
    });