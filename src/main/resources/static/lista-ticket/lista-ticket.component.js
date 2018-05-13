'use strict';

// Register `listaUtente` component, along with its associated controller and template
angular.
module('listaTicket').
component('listaTicket', {
    templateUrl: 'lista-ticket/lista-ticket.template.html',
    controller: ['$http', function listaTicketController($http) {

        var self = this;

        $http.get('ticket/').then(function(response) {
            self.tickets = response.data;
        }, function (reason) {
            console.log("Err: " + reason.statusText);
        });

    }]
});