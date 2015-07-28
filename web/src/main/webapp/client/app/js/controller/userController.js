'use strict';

angular.module('userManagement')
    .controller('UserController', function ($scope, UserService, EmployeeService) {

        $scope.$emit('to-parent-itemsListActive');


        $scope.employees = [];

        $scope.users = [];
        UserService.getUsers(function(data) {

            $scope.users = data;
            EmployeeService.getEmployees(function(employees) {

                $scope.employees = employees;
            });
        });

    });