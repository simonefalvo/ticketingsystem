'use strict';

// Register `ticketHistory` component, along with its associated controller and template
angular.
module('ticketHistory').
component('ticketHistory', {
    templateUrl: 'ticket-history/ticket-history.template.html',
    controller: ['$http', '$routeParams', '$location', function ticketHistoryController($http, $routeParams, $location) {

        var self = this;

        self.get = function () {
            $http.get('ticket/' + $routeParams.ticketId).then(function (response) {
                self.ticket = response.data;
            }, function (reason) {
                alert('Error: '+ JSON.stringify(reason));
            })
        };

        self.getAll = function () {
            $http.get('ticketaudit/' + $routeParams.ticketId.toString()).then(function(response) {
                self.tickets = response.data;
            }, function (reason) {
                alert("Error: " + JSON.stringify(reason));
            });
        };

        self.get();
        self.getAll();

        self.dettagli = function (ticketId) {
            $location.path('/dettagli-ticket/audit/' + ticketId.toString());
        };
    }]
});