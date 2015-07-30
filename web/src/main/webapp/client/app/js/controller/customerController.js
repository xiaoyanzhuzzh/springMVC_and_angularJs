'use strict';

angular.module('userManagement')
    .controller('CustomerController', function ($scope, $route, CustomerService, EmployeeService) {
        //$scope.$emit('to-parent-itemsListActive');

        $scope.customers = [];
        $scope.employees = [];

        CustomerService.getCustomers(function (data) {

            $scope.customers = data;
            EmployeeService.getEmployees(function(employees) {

                $scope.employees = EmployeeService.getAllCoaches(employees);
            });

        });

        $scope.addNewCustomer = function(customer) {

            CustomerService.addCustomer(customer, function() {

                CustomerService.getCustomers(function (data) {

                    $scope.customers = data;
                    EmployeeService.getEmployees(function(employees) {

                        $scope.employees = EmployeeService.getAllCoaches(employees);
                    });

                });

            })
        };

        $scope.showUpdateCustomer = false;
        $scope.updateCurrentCustomer = function(customer) {
            $scope.showUpdateCustomer = true;
            $scope.currentCustomer = {
                id: customer.id,
                name: customer.name,
                employee: customer.employee
            }
        };

        $scope.updateCustomer = function(currentCustomer) {
            console.log(currentCustomer);

            CustomerService.updateCustomer(currentCustomer, function () {

                CustomerService.getCustomers(function (data) {

                    $scope.customers = data;
                    EmployeeService.getEmployees(function(employees) {

                        $scope.employees = EmployeeService.getAllCoaches(employees);
                    });

                });

            });

        };

        $scope.deleteCurrentCustomer = function(customer) {
            CustomerService.deleteCustomer(customer.id, function() {

                CustomerService.getCustomers(function (data) {

                    $scope.customers = data;
                    EmployeeService.getEmployees(function(employees) {

                        $scope.employees = EmployeeService.getAllCoaches(employees);
                    });

                });

            })
        };
    });