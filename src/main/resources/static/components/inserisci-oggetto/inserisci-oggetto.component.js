'use strict';

// Register `inserisciOggetto` component, along with its associated controller and template
angular.
module('inserisciOggetto').
component('inserisciOggetto', {
    templateUrl: 'components/inserisci-oggetto/inserisci-oggetto.html',
    controller: ['$http', '$location','$scope', function inserisciOggettoController($http, $location,scope) {

        var self = this;

        self.inserisci = function () {
            console.log(self.oggetto);
            $http.post('oggetto/', self.oggetto)
                .then(function () {
                    self.modalText = "Inserimento avventuo con successo!";
                    scope.openModal = true;
                }, function (reason) {
                    self.modalText = "Si è verificato un Errore!";
                    scope.openModal = true;
            });
        };

        self.annulla = function () {
            $location.path('/visualizza_oggetti');
        };
    }]
});