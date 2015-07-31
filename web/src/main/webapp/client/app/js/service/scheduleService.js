'use strict';

angular.module('userManagement')
    .service('ScheduleService', function($http, $filter){

        function getSchedulesData(callback) {

            $http.get('/web/api/schedules')
                .success(function(data) {
                    callback(data);
                });
        }

        function addSchedulesData(schedule, callback) {

            $http({
                method: 'POST',
                url: '/web/api/schedules',
                data:{
                    id: null,
                    course: schedule.course,
                    time: $filter('date')(schedule.time, 'yyyy-MM-dd'),
                    customer: schedule.customer || null
                },
                success: function() {
                    callback();
                }
            });
        }

        function deleteScheduleData(id, callback) {

            $http({
                method: 'DELETE',
                url: '/web/api/schedules/' + id,
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

        this.addSchedule = function(schedules, callback) {

            addSchedulesData(schedules, callback);
        };

        this.deleteSchedule = function(id, callback) {

            deleteScheduleData(id, callback);
        };

        this.updateCourse = function(course, callback) {

            updateCourseData(course, callback);
        }

    });