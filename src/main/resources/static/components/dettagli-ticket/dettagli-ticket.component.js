'use strict';

// Register `dettagliTicket` component, along with its associated controller and template
angular.
module('dettagliTicket').
component('dettagliTicket', {
    templateUrl: 'components/dettagli-ticket/dettagli-ticket.html',
    controller: ['$http', '$routeParams', '$location','$scope', function dettagliTicketController($http, $routeParams, $location,scope) {

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
            var path = self.audit ? '/tickethistory/' + self.ticket.id.toString() : '/visualizza_ticket';
            $location.path(path);
        };

        self.elimina = function (ticketId) {

            //if (confirm("Procedere con l'eliminazione?")) {
                $http.delete('ticket/' + ticketId.toString()).then(function () {
                    self.modalText = "Ticket eliminato con successo!";
                    scope.openModal = true;
                }, function (reason) {
                    //alert(reason.toLocaleString());
                    self.modalText = "Si è verificato un Errore!";
                    scope.openModal = true;
                });
            //}
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
                self.modalText = "Ticket modificato con successo!";
                scope.openModal = true;
                self.modifyMode = false;
            }, function (reason) {
                //alert(reason.toLocaleString());
                self.modalText = "Si è verificato un Errore!";
                scope.openModal = true;
            });
        };

        self.history = function () {
            $location.path('/tickethistory/' + self.ticket.id.toString());
        };

        self.log = function () {
            $location.path('/ticketlog/' + self.ticket.id.toString());
        };

    }]
});