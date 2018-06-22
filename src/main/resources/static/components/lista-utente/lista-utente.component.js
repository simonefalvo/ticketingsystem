'use strict';

// Register `listaUtente` component, along with its associated controller and template
angular.
module('listaUtente').
component('listaUtente', {
    templateUrl: 'components/lista-utente/lista-utente.html',
    controller: ['$http', '$location', function listaUtenteController($http, $location) {

        var self = this;

        self.getAll = function () {
            $http.get('utente/').then(function (response) {
                self.utenti = response.data;
            }, function (reason) {
                alert("Error: " + reason.status);
            });
        };

        self.getAll();

        self.dettagli = function (utenteId) {
            $location.path('/dettagli-utente/' + utenteId.toString());
        };

    }]
});
