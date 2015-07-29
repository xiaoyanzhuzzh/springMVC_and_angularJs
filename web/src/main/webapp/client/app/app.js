'use strict';

angular
    .module('userManagement', [
        'ngRoute'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/users', {
                templateUrl: '/web/client/app/view/user.html',
                controller: 'UserController'
            })
            .when('/employees', {
                templateUrl: '/web/client/app/view/employee.html',
                controller: 'EmployeeController'
            })
            .when('/courses', {
                templateUrl: '/web/client/app/view/course.html',
                controller: 'CourseController'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
