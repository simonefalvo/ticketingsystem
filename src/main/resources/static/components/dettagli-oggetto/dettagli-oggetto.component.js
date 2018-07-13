'use strict';

// Register `dettagliOggetto` component, along with its associated controller and template
angular.
module('dettagliOggetto').
component('dettagliOggetto', {
    templateUrl: 'components/dettagli-oggetto/dettagli-oggetto.html',
    controller: ['$http', '$routeParams', '$location', '$scope',function dettagliOggettoController($http, $routeParams, $location,scope) {

        var self = this;
        var modifyMode = false;

        self.get = function () {
            $http.get('oggetto/' + $routeParams.oggettoId.toString()).
            then(function(response) {
                self.oggetto = response.data;
            }, function (reason) {
                alert(reason.toLocaleString());
            });
        };

        self.get();

        self.indietro = function () {
            $location.path('/visualizza_oggetti');
        };

        self.elimina = function (oggettoId) {

            if (confirm("Procedere con l'eliminazione?")) {
                $http.delete('oggetto/' + oggettoId.toString()).then(function () {
                    self.modalText = "Oggetto eliminato con successo!";
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
            $http.put('oggetto/' + self.oggetto.id.toString(), self.oggetto).
                then(function () {
                    //alert("Oggetto modificato con successo!");
                    self.modalText = "Oggetto modificato con successo!";
                    scope.openModal = true;
                    self.modifyMode = false;
            }, function (reason) {
                    //alert(reason.toLocaleString());
                    self.modalText = "Si è verificato un Errore!";
                    scope.openModal = true;
            });
        };

    }]
});