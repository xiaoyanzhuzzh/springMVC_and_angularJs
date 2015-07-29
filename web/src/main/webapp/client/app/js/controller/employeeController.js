'use strict';

angular.module('userManagement')
    .controller('EmployeeController', function ($scope, $route, EmployeeService) {

        $scope.$emit('to-parent-itemsListActive');

        $scope.employees = [];
        EmployeeService.getEmployees(function(data) {
            $scope.employees = data;
        });

        $scope.addNewEmployee = function(employee) {

            EmployeeService.addEmployee(employee, function() {

                $route.reload();
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

                $route.reload();
            });

            $scope.showUpdateEmployee = false;
        };

        $scope.cancelUpdateEmployee = function() {
            $scope.showUpdateEmployee = false;
        }

    });