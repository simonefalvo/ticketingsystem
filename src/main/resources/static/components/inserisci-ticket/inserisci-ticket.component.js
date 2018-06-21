'use strict';

// Register `inserisciTicket` component, along with its associated controller and template
angular.
module('inserisciTicket').
component('inserisciTicket', {

    templateUrl: 'components/inserisci-ticket/inserisci-ticket.html',
    controller: ['$http', '$location', function inserisciTicketController($http, $location) {

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
            /*
            //dato che si perdono alcuni dati dalla visualizzazione a quando mi tornano
            // dall'inserimento faccio una query per ri ottenerli
            self.ticket.oggetto.nome = "inizializzato da fuori";
            $http.get('oggetto/' + self.ticket.oggetto.id.toString()).
            then(function(response) {
                console.log("response.data= "+JSON.stringify(response.data, null, 4));

                self.ticket.oggetto.nome = response.data.nome;
                self.ticket.oggetto.versione = response.data.versione;

            }, function (reason) {
                alert(reason.toLocaleString());
            });
            */


            console.log("self.oggetti : "+JSON.stringify(self.oggetti, null, 4));

            console.log("self.ticket.oggetto= "+JSON.stringify(self.ticket.oggetto, null, 4));


            for(var i=0; i< self.oggetti.length; i++){

                if(self.oggetti[i].id == self.ticket.oggetto.id){
                    console.log("ho trovato l'oggetto");
                    self.ticket.oggetto.nome = self.oggetti[i].nome;
                    self.ticket.oggetto.versione = self.oggetti[i].versione;
                }
            }


            $http.post('ticket/', self.ticket)
                .then(function () {
                    $location.path('/ticket');
                    alert("ticket inserito con successo!")
                }, function (reason) {
                    alert('Error: ' + JSON.stringify(reason));
                });
        };

        self.annulla = function () {
            $location.path('/ticket');
        };
    }]
});