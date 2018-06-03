
'use strict';

// Register `listaUtente` component, along with its associated controller and template
angular.
module('listaTicket').
component('listaTicket', {
    templateUrl: 'lista-ticket/lista-ticket.template.html',
    controller: ['$http', '$location', function listaTicketController($http, $location) {

        var self = this;

        self.getAll = function () {
            $http.get('ticket/').then(function(response) {
                self.tickets = response.data;
                console.log("Success: " + response.statusText);
            }, function (reason) {
                console.log("Error: " + reason.statusText);
            });
        };

        self.getAll();

        self.dettagli = function (ticketId) {
            $location.path('/dettagli-ticket/' + ticketId.toString());
            //$http.get('ticket/' + ticketId.toString());
        };

        self.elimina = function (ticketId) {
            $http.delete('ticket/' + ticketId.toString()).
            then(function () {
                self.getAll();
                alert("Ticket eliminato con successo!");
            }, function (reason) {
                alert("Error: " + reason.statusText);
            })
        };

    }]
});