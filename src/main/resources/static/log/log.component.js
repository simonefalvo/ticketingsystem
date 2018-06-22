'use strict';

// Register `log` component, along with its associated controller and template
angular.
module('log').
component('log', {
    templateUrl: 'log/log-ticket.template.html',
    controller: ['$http', function logController($http) {

        var self = this;

        self.getAll = function () {
            $http.get('log/').then(function(response) {
                self.log = response.data;
            }, function (reason) {
                alert("Error: " + JSON.stringify(reason));
            });
        };

        self.getAll();

    }]
});