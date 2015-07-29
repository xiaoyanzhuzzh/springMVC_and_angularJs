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

        function deleteUserData(id, callback) {

            $http({
                method: 'DELETE',
                url: '/web/api/users/' + id,
                success: function() {
                    callback();
                }
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

        this.deleteUser = function(id, callback) {

            deleteUserData(id, callback);
        };

        this.updateUser = function(user, callback) {

            updateUserData(user, callback);
        }

    });