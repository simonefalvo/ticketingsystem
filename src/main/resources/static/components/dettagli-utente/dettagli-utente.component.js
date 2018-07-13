'use strict';

// Register `dettagliUtente` component, along with its associated controller and template
angular.
module('dettagliUtente').
component('dettagliUtente', {
    templateUrl: 'components/dettagli-utente/dettagli-utente.html',
    controller: ['$http', '$routeParams', '$location','$scope', function dettagliUtenteController($http, $routeParams, $location,scope) {

        var self = this;
        var modifyMode = false;

        self.get = function () {
            $http.get('utente/' + $routeParams.utenteId.toString()).
            then(function(response) {
                self.utente = response.data;
            }, function (reason) {
                alert(reason.toLocaleString());
            });
        };

        self.get();

        self.indietro = function () {
            $location.path('/visualizza_utenti');
        };

        self.elimina = function (utenteId) {

            if (confirm("Procedere con l'eliminazione?")) {
                $http.delete('utente/' + utenteId.toString()).then(function () {
                    self.modalText = "Utente eliminato con successo!";
                    scope.openModal = true;
                }, function (reason) {
                    //alert(reason.toLocaleString());
                    self.modalText = "Si è verificato un Errore!";
                    scope.openModal = true;
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
            $http.put('utente/' + self.utente.id.toString(), self.utente).
            then(function () {
                self.modalText = "Utente modificato con successo!";
                scope.openModal = true;
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
                }else{
                    self.modalText = "Si è verificato un Errore!";
                    scope.openModal = true;
                }
            });
        };

        self.opLog = function () {
            $location.path('/log/author/' + self.utente.username);
        }
    }]
});