'use strict';

angular.module('userManagement')
    .service('EmployeeService', function($http){

        function getEmployeesData(callback) {

            $http.get('/web/api/employees')
                .success(function(data) {

                    callback(data);
                });
        }

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

        function updateEmployeeData(employee) {

            $http({
                method: 'PUT',
                url: '/web/api/employees/' + employee.id,
                data: {
                    id: employee.id,
                    name: employee.name,
                    gender: employee.gender,
                    role: employee.role,
                    age: employee.age,
                    email: employee.email
                },
                success: function() {
                    callback();
                }
            })
        }

        this.getEmployees = function(callback) {

            getEmployeesData(function(data) {

                callback(data);
            });
        };

        this.addEmployee = function(employee, callback) {

            addEmployeeData(employee, callback);
        };

        this.updateEmployee = function(employee, callback) {

            updateEmployeeData(employee, callback)
        };

        this.getEmployeesWithoutAccount = function(users, callback) {

            var employees;
            getEmployeesData(function (data) {

                employees = data;

                for(var i = 0; i < users.length; i++) {

                    _.remove(employees, function(employee) {
                        return employee.id === users[i].employee.id;
                    });

                }
                callback(employees);
            });
        };
    });