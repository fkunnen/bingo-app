angular.module('devopsbingo', [])
    .controller('bingoCtrl', function ($http) {
        var ctrl = this;
        ctrl.item = {};
        ctrl.items = [];

        this.getItems = function () {
            $http.get('/bingo/items')
                .then(function (response) {
                    ctrl.items = response.data;
                });
        };

        this.addItem = function () {
            $http.post('/bingo/items', ctrl.item)
                .then(function () {
                    ctrl.getItems();
                    ctrl.item = {};
                });
        };

        this.deleteItem = function (id) {
            $http.delete('/bingo/items/' + id)
                .then(function () {
                    ctrl.getItems();
                });
        }

        this.getItems();
    });