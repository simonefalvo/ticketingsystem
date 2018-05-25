'use strict';

// Register `inserisciTicket` component, along with its associated controller and template
angular.
module('inserisciTicket').
component('inserisciTicket', {
    templateUrl: 'inserisci-ticket/inserisci-ticket.template.html',
    controller: ['$http', function inserisciTicketController($http) {

        var self = this;

        self.getAll = function () {
            $http.get('oggetto/').then(function(response) {
                self.oggetti = response.data;
                console.log("Success: " + response.statusText);
            }, function (reason) {
                console.log("Error: " + reason.statusText);
            });
        };

        self.getAll();

        self.inserisci = function () {
            console.log(self.ticket);
            $http.post('ticket/', self.ticket)
                .then(function (response) {
                    //$location.path('/ticket');
                    console.log('Success: ' + response.statusText);
                }, function (reason) {
                    console.log('Error: ' + JSON.stringify(reason));
                });
        };

        self.annulla = function () {
            console.log('inserimento annullato');
        };
    }]
});