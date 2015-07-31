'use strict';

angular.module('userManagement')
    .controller('UserController', function ($scope, $route, UserService, EmployeeService) {

        $scope.$emit('to-parent-itemsListActive');

        $scope.employees = [];
        $scope.users = [];
        UserService.getUsers(function(data) {

            $scope.users = data;
            EmployeeService.getEmployeesWithoutAccount(data, function(employees) {

                $scope.employees = employees;
            });
        });

        $scope.addNewUser = function(user) {

            UserService.addUser(user, function() {

                $route.reload();
            });
        };

        $scope.deleteCurrentUser = function(id) {

            UserService.deleteUser(id, function() {


            });
        };

        $scope.showUpdateUser = false;
        $scope.updateCurrentUser = function(user) {

            $scope.showUpdateUser = true;
            $scope.currentUser = {
                id: user.id,
                name: user.name,
                password: user.password,
                employee: user.employee
            }
        };

        $scope.updateUser = function(currentUser) {
            console.log(currentUser);

            UserService.updateUser(currentUser, function() {

                UserService.getUsers(function(data) {

                    $scope.users = data;
                    EmployeeService.getEmployeesWithoutAccount(data, function(employees) {

                        $scope.employees = employees;
                    });
                });
            });

            $scope.showUpdateUser = false;
        };

        $scope.cancelUpdateUser = function() {
            $scope.showUpdateUser = false;
        }



    });