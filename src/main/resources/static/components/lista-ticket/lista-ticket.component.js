'use strict';

// Register `listaTicket` component, along with its associated controller and template
angular.
module('listaTicket').
component('listaTicket', {
    templateUrl: 'components/lista-ticket/lista-ticket.html',
    controller: ['$http', '$location', function listaTicketController($http, $location) {

        var self = this;

        function get_role() {
            $http.get('utente/isAdmin').then(function (response) {
                return response.data;
            });
        }


        self.getAll = function () {
            $http.get('ticket/').then(function(response) {
                self.tickets = response.data;
            }, function (reason) {
                alert("Error: " + JSON.stringify(reason));
            });
        };

        self.getAll();

        self.dettagli = function (ticketId) {
            $location.path('/dettagli-ticket/' + ticketId.toString());
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
