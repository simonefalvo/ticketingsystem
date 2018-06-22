'use strict';

// Register `dettagliOggetto` component, along with its associated controller and template
angular.
module('dettagliOggetto').
component('dettagliOggetto', {
    templateUrl: 'components/dettagli-oggetto/dettagli-oggetto.html',
    controller: ['$http', '$routeParams', '$location', function dettagliOggettoController($http, $routeParams, $location) {

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
            $location.path('/oggetto');
        };

        self.elimina = function (oggettoId) {

            if (confirm("Procedere con l'eliminazione?")) {
                $http.delete('oggetto/' + oggettoId.toString()).then(function () {
                    $location.path('/oggetto');
                    alert("Oggetto eliminato con successo!");
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
            $http.put('oggetto/' + self.oggetto.id.toString(), self.oggetto).
                then(function () {
                    alert("Oggetto modificato con successo!");
                    self.modifyMode = false;
            }, function (reason) {
                    alert(reason.toLocaleString());
            });
        };

    }]
});