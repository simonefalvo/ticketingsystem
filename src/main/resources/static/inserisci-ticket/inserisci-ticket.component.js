'use strict';

// Register `inserisciTicket` component, along with its associated controller and template
angular.
module('inserisciTicket').
component('inserisciTicket', {
    templateUrl: 'inserisci-ticket/inserisci-ticket.template.html',
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