'use strict';

angular.module('userManagement')
    .controller('UserController', function ($scope, UserService) {

        $scope.$emit('to-parent-itemsListActive');

        $scope.users = [];
        UserService.getUsers(function(data) {
            $scope.users = data;
        });
    });