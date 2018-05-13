'use strict';

// Register `listaUtente` component, along with its associated controller and template
angular.
module('listaUtente').
component('listaUtente', {
    templateUrl: 'lista-utente/lista-utente.template.html',
    controller: ['$http', function listaUtenteController($http) {

        var self = this;

        $http.get('utente/').then(function(response) {
            self.utenti = response.data;
        }, function (reason) {
            console.log("Err: " + reason.statusText);
        });

    }]
});