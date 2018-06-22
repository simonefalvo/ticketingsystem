'use strict';

// Register `dettagliTicket` component, along with its associated controller and template
angular.
module('dettagliTicket').
component('dettagliTicket', {
    templateUrl: 'components/dettagli-ticket/dettagli-ticket.html',
    controller: ['$http', '$routeParams', '$location', function dettagliTicketController($http, $routeParams, $location) {

        var self = this;
        self.modifyMode = false;
        self.audit = $location.path() === '/dettagli-ticket/audit/' + $routeParams.ticketId.toString();

        self.get = function () {
            var path = self.audit ? 'ticketaudit/' : 'ticket/';
            $http.get(path + $routeParams.ticketId.toString()).
            then(function(response) {
                self.ticket = response.data;
                if (self.audit) self.ticket.id = self.ticket.idTicket;
            }, function (reason) {
                alert(reason.toLocaleString());
            });
        };

        self.get();

        self.indietro = function () {
            var path = self.audit ? '/tickethistory/' + self.ticket.id.toString() : '/ticket';
            $location.path(path);
        };

        self.elimina = function (ticketId) {

            if (confirm("Procedere con l'eliminazione?")) {
                $http.delete('ticket/' + ticketId.toString()).then(function () {
                    $location.path('/ticket');
                    alert("Ticket eliminato con successo!");
                }, function (reason) {
                    alert(reason.toLocaleString());
                });
            }
        };

        self.modifica = function () {
            self.modifyMode = true;
        };

        self.annulla = function () {
            self.modifyMode = false;
        };

        self.conferma = function () {
            console.log(self.ticket);
            $http.put('ticket/' + self.ticket.id.toString(), self.ticket).
            then(function () {
                alert("Ticket modificato con successo!");
                self.modifyMode = false;
            }, function (reason) {
                alert(reason.toLocaleString());
            });
        };

        self.history = function () {
            $location.path('/tickethistory/' + self.ticket.id.toString());
        };

    }]
});