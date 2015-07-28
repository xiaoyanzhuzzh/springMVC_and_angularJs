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

        $scope.showUpdateEmployee = false;
        $scope.updateCurrentEmployee = function(employee) {

            $scope.showUpdateEmployee = true;
            $scope.currentEmployee = {
                id: employee.id,
                name: employee.name,
                gender: employee.gender,
                age: employee.age,
                email: employee.email,
                role: employee.role
            }
        };

        $scope.updateEmployee = function(employee) {

            EmployeeService.updateEmployee(employee, function() {

                EmployeeService.getEmployees(function(data) {
                    $scope.employees = data;
                })
            });

            $scope.showUpdateEmployee = false;
        };

        $scope.cancelUpdateEmployee = function() {
            $scope.showUpdateEmployee = false;
        }

    });