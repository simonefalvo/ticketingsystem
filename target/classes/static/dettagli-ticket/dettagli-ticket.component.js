'use strict';

// Register `dettagliTicket` component, along with its associated controller and template
angular.
module('dettagliTicket').
component('dettagliTicket', {
    templateUrl: 'dettagli-ticket/dettagli-ticket.template.html',
    controller: ['$http', '$routeParams', '$location', function dettagliTicketController($http, $routeParams, $location) {

        var self = this;
        var modifyMode = false;

        self.get = function () {
            $http.get('ticket/' + $routeParams.ticketId.toString()).
            then(function(response) {
                self.ticket = response.data;
                console.log(self.ticket);
            }, function (reason) {
                alert(reason.toLocaleString());
            });
        };
        console.log("\n\n\nfuori dalla funzione get "+self.ticket);

        self.get();

        self.indietro = function () {
            $location.path('/ticket');
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
            $http.put('ticket/' + self.ticket.id.toString(), self.ticket).
            then(function () {
                alert("Ticket modificato con successo!");
                self.modifyMode = false;
            }, function (reason) {
                alert(reason.toLocaleString());
            });
        };

    }]
});