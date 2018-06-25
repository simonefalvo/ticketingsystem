'use strict';

// Register `logUtente` component, along with its associated controller and template
angular.
module('logUtente').
component('logUtente', {
    templateUrl: 'log-utente/log-utente.template.html',
    controller: ['$http', '$routeParams', '$location', function logController($http, $routeParams, $location) {

        var self = this;

        self.getAll = function () {
            $http.get('log/author/' + $routeParams.username).then(function(response) {
                self.log = response.data;
            }, function (reason) {
                alert("Error: " + JSON.stringify(reason));
            });
        };

        self.getAll();

    }]
});