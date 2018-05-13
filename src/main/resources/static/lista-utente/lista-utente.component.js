'use strict';

// Register `listaUtente` component, along with its associated controller and template
angular.
module('listaUtente').
component('listaUtente', {
    templateUrl: 'lista-utente/lista-utente.template.html',
    controller: ['$http', '$location', function listaUtenteController($http, $location) {

        var self = this;

        $http.get('utente/').then(function(response) {
            self.utenti = response.data;
            console.log("Success: " + response.statusText);
        }, function (reason) {
            console.log("Error: " + reason.statusText);
        });

        self.visualizza = function (utenteId) {
            $location.path('/dettagli-utente/' + utenteId.toString());
            //$http.get('utente/' + utenteId.toString());
        }

        self.elimina = function (utenteId) {
            $http.delete('utente/' + utenteId.toString());
            //TODO: rimuovere il record da self.utenti
            //self.utenti.remove(id, utenteId.toString());
        }

    }]
});