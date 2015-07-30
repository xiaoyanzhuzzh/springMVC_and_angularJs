'use strict';

angular.module('userManagement')
    .controller('CustomerController', function ($scope, $route, CustomerService) {

        $scope.$emit('to-parent-itemsListActive');

        $scope.customers = [];
        CustomerService.getCustomers(function (data) {

            $scope.customers = data;
        });

        $scope.addNewCustomer = function(customer) {

            CustomerService.addCustomer(customer, function() {

                $route.reload();
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

            CustomerService.updateCustomer(currentCustomer, function () {

                $route.reload();
            });
        };

        $scope.deleteCurrentCustomer = function(customer) {
            CustomerService.deleteCustomer(customer.id, function() {

                $route.reload();
            })
        };
    });