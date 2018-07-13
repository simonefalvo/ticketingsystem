'use strict';

// Register `logTicket` component, along with its associated controller and template
angular.
module('logTicket').
component('logTicket', {
    templateUrl: 'components/log-ticket/log-ticket.html',
    controller: ['$http', '$routeParams', '$location', function logController($http, $routeParams, $location) {

        var self = this;

        self.getAll = function () {
            $http.get('ticket/log/' + $routeParams.ticketId).then(function(response) {
                self.log = response.data;
            }, function (reason) {
                alert("Error: " + JSON.stringify(reason));
            });
        };

        self.getAll();
    }]
});