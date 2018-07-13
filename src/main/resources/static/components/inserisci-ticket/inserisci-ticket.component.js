'use strict';

// Register `inserisciTicket` component, along with its associated controller and template
angular.
module('inserisciTicket').
component('inserisciTicket', {

    templateUrl: 'components/inserisci-ticket/inserisci-ticket.html',
    controller: ['$http', '$location','$scope', function inserisciTicketController($http, $location,scope) {

        var self = this;

        self.getAll = function () {
            $http.get('oggetto/').then(function(response) {

                self.oggetti = response.data;
            }, function (reason) {
                console.log("Error: " + reason.statusText);
            });
        };

        self.getAll();

        self.inserisci = function () {
            self.ticket.stato = "pending";
            self.ticket.timestamp = new Date();

            for(var i=0; i< self.oggetti.length; i++){

                if(self.oggetti[i].id == self.ticket.oggetto.id){
                    console.log("ho trovato l'oggetto");
                    self.ticket.oggetto.nome = self.oggetti[i].nome;
                    self.ticket.oggetto.versione = self.oggetti[i].versione;
                }
            }


            $http.post('ticket/', self.ticket)
                .then(function () {
                    self.modalText = "Inserimento avventuo con successo!";
                    scope.openModal = true;
                }, function (reason) {
                    self.modalText = "Si è verificato un Errore!";
                    scope.openModal = true;
                });
        };

        self.annulla = function () {
            $location.path('/visualizza_ticket');
        };
    }]
});