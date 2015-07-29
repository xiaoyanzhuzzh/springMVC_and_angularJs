'use strict';

angular.module('userManagement')
    .controller('CourseController', function ($scope, $route, CourseService) {

        $scope.$emit('to-parent-itemsListActive');

        $scope.courses = [];
        CourseService.getCourses(function (data) {

            $scope.courses = data;
        });
    });