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

        this.setCartItems = function(item) {

            this.getCartItems(function(data) {

                updateCartItems(item, data);
            });
        };

        this.addCartItemNumber = function(id, callback) {

            this.getCartItems(function(data) {

                modifyCartItemNumberData(addCartItemNumberData(data, id), function(){
                    callback();
                });
            });
        };

        this.reduceCartItemNumber = function(id, callback) {

            this.getCartItems(function(data) {

                modifyCartItemNumberData(reduceCartItemNumberData(data, id), function(){
                    callback();
                });
            });
        };

        this.changeCartItemNumber = function(changeCartItem, callback) {

            modifyCartItemNumberData(changeCartItem ,function(){
                callback();
            });
        };

        this.deleteCartItem = function(id) {

            deleteCartItemData(id);
        };

        this.emptyCartItems = function() {

            emptyCartItemsData();
        };

        this.getTotalNumber = function(array){
            var totalNum = 0;
            if(!array){
                array = [];
            }
            for(var i = 0; i < array.length; i++){
                totalNum += array[i].num;
            }
            return totalNum;
        };

        this.getTotalMoney = function(array){
            var total = 0;
            if(!array){
                array = [];
            }
            for(var i = 0; i < array.length; i++){
                total += array[i].num * array[i].item.price;
            }
            return total;
        };
    });