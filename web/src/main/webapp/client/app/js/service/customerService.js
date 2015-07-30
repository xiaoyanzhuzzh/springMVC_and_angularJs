'use strict';

angular.module('userManagement')
    .service('CustomerService', function($http){

        function getCustomersData(callback) {

            $http.get('/web/api/customers')
                .success(function(data) {
                    callback(data);
                });
        }

        function addCustomerData(customer, callback) {

            $http({
                method: 'POST',
                url: '/web/api/customers',
                data:{
                    id: null,
                    name: customer.name
                },
                success: function() {
                    callback();
                }
            });
        }

        function deleteCustomerData(id, callback) {

            $http({
                method: 'DELETE',
                url: '/web/api/customers/' + id,
                success: function() {
                    callback();
                }
            });
        }

        function updateCustomerData(customer, callback) {

            $http({
                method: 'PUT',
                url: '/web/api/customers/' + customer.id,
                data: {
                    id:  customer.id,
                    name: customer.name,
                    employee: JSON.parse(customer.employee)
                },
                success: function() {
                    callback();
                }
            })
        }

        this.getCustomers = function(callback) {

            getCustomersData(function(data) {

                callback(data);
            });
        };

        this.addCustomer = function(customer, callback) {

            addCustomerData(customer, callback);
        };

        this.deleteCustomer = function(id, callback) {

            deleteCustomerData(id, callback);
        };

        this.updateCustomer = function(customer, callback) {

            updateCustomerData(customer, callback);
        }

    });