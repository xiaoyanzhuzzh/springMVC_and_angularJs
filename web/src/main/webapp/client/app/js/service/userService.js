'use strict';

angular.module('userManagement')
    .service('UserService', function($http){

        function getUsersData(callback) {

            $http.get('/web/api/users')
                .success(function(data) {
                    callback(data);
                });
        }

        function addUserData(user, callback) {

            $http({
                method: 'POST',
                url: '/web/api/users',
                data:{
                    id: null,
                    name: user.name,
                    password: user.password,
                    employee: user.employee
                },
                success: function() {
                    callback();
                }
            });
        }

        function deleteUserData(id) {

            $http({
                method: 'DELETE',
                url: '/web/api/users/' + id
            });
        }

        function updateUserData(user, callback) {

            $http({
                method: 'PUT',
                url: '/web/api/users/' + user.id,
                data: {

                    id:  user.id,
                    name: user.name,
                    password: user.password,
                    employee: user.employee
                },
                success: function() {
                    callback();
                }
            })
        }

        this.getUsers = function(callback) {

            getUsersData(function(data) {

                callback(data);
            });
        };

        this.addUser = function(user, callback) {

            addUserData(user, callback);
        };

        this.deleteUser = function(id) {

            deleteUserData(id);
        };

        this.updateUser = function(user, callback) {

            updateUserData(user, callback);
        }

    });