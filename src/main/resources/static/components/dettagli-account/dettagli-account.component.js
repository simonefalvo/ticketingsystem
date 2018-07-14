'use strict';

// Register `dettagliUtente` component, along with its associated controller and template
angular.
module('dettagliAccount').
component('dettagliAccount', {
    templateUrl: 'components/dettagli-account/dettagli-account.html',
    controller: ['$http', '$routeParams', '$location', function dettagliAccountController($http, $routeParams, $location) {

        var self = this;
        var modifyMode = false;

        self.cercaUtente = function () {
            $http.get('utente/dettagli-utente')
                .then(function(response) {
                    self.utente = response.data;
                }, function (reason) {
                    alert(reason.toLocaleString());
                });
        };

        self.cercaUtente();

        self.indietro = function () {
            $location.path('/navbar');
        };

        self.elimina = function (utenteId) {

            if (confirm("Procedere con l'eliminazione?")) {
                $http.delete('utente/' + utenteId.toString()).then(function () {
                    $location.path('/visualizza_utenti');
                    alert("Utente eliminato con successo!");
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
            $http.put('utente/dettagliAccount/' + self.utente.id.toString(), self.utente).
            then(function () {
                alert("Utente modificato con successo!");
                self.modifyMode = false;
            }, function (reason) {
                if (reason.data == -1){
                    self.errData = "Si è verificato un errore";
                }
                if (reason.data == 1){
                    self.errData = "Username già presente!";
                }
                if (reason.data == 2){
                    self.errData = "Email già presente!";
                }
            });
        };
    }]
});