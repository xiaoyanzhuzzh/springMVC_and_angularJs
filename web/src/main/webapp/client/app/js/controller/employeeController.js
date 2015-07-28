'use strict';

angular.module('userManagement')
    .controller('EmployeeController', function ($scope, EmployeeService) {

        $scope.$emit('to-parent-itemsListActive');

        $scope.employees = [];
        EmployeeService.getEmployees(function(data) {
            $scope.employees = data;
        });
    });