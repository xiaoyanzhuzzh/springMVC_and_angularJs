'use strict';

angular.module('userManagement')
    .service('EmployeeService', function($http){

        function getEmployeesData(callback) {

            $http.get('/web/api/employees')
                .success(function(data) {

                    callback(data);
                });
        }

        this.getEmployees = function(callback) {

            getEmployeesData(function(data) {

                callback(data);
            });
        };

        function addEmployeeData(employee, callback) {

            $http({
                method: 'POST', url: '/web/api/employees',
                data:{
                    id: null,
                    name: employee.name,
                    gender: employee.gender,
                    role: employee.role,
                    age: employee.age,
                    email: employee.email
                },
                success: function() {
                    callback();
                }
            });
        }

        this.addEmployee = function(employee) {

            addEmployeeData(employee);
        };

    });