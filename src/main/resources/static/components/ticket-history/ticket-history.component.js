'use strict';

angular.
module('ticketHistory').filter('operationFilter', function() {
    return function(operazione){
        if(operazione==0)
            return 'Inserimento';
        if (operazione==1)
            return 'Modifica';

    }
});

// Register `ticketHistory` component, along with its associated controller and template
angular.
module('ticketHistory').
component('ticketHistory', {
    templateUrl: 'components/ticket-history/ticket-history.html',
    controller: ['$http', '$routeParams', '$location','$scope', function ticketHistoryController($http, $routeParams, $location,$scope) {

        var self = this;

        self.get = function () {
            $http.get('ticket/' + $routeParams.ticketId).then(function (response) {
                self.ticket = response.data;
            }, function (reason) {
                alert('Error: '+ JSON.stringify(reason));
            })
        };

        self.getAll = function () {
            $http.get('ticketaudit/history/' + $routeParams.ticketId).then(function(response) {
                self.tickets = response.data;
            }, function (reason) {
                alert("Error: " + JSON.stringify(reason));
            });
        };

        self.get();

        self.getAll();

        self.dettagli = function (ticketId) {
            $location.path('/dettagli-ticket/audit/' + ticketId);
        };

        self.indietro = function () {
            $location.path('/dettagli-ticket/' + self.ticket.id);
        };

    }]
});