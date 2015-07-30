'use strict';

angular.module('userManagement')
    .service('ScheduleService', function($http){

        function getSchedulesData(callback) {

            $http.get('/web/api/schedules')
                .success(function(data) {
                    callback(data);
                });
        }

        function addCourseData(course, callback) {

            $http({
                method: 'POST',
                url: '/web/api/courses',
                data:{
                    id: null,
                    name: course.name,
                    employee: course.employee
                },
                success: function() {
                    callback();
                }
            });
        }

        function deleteCourseData(id, callback) {

            $http({
                method: 'DELETE',
                url: '/web/api/courses/' + id,
                success: function() {
                    callback();
                }
            });
        }

        function updateCourseData(course, callback) {

            $http({
                method: 'PUT',
                url: '/web/api/courses/' + course.id,
                data: {
                    id:  course.id,
                    name: course.name,
                    employee: JSON.parse(course.employee)
                },
                success: function() {
                    callback();
                }
            })
        }

        this.getSchedules = function (callback) {

            getSchedulesData(function (data) {
                callback(data);
            });
        };

        this.getPrivateSchedules = function (callback) {

            getSchedulesData(function (data) {
                console.log(data);

                _.filter(data, function(privateSchedule) {

                    return privateSchedule.customer !== null;
                });

                callback(data);
            });
        };

        this.getCourses = function(callback) {

            getCoursesData(function(data) {

                callback(data);
            });
        };

        this.addCourse = function(course, callback) {

            addCourseData(course, callback);
        };

        this.deleteCourse = function(id, callback) {

            deleteCourseData(id, callback);
        };

        this.updateCourse = function(course, callback) {

            updateCourseData(course, callback);
        }

    });