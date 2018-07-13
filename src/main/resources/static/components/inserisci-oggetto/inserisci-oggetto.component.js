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
                    //$location.path('/visualizza_oggetti');
                    self.modalText = "Inserimento avventuo con successo!";
                    scope.openModal = true;
                    //alert("oggetto inserito con successo!")
                }, function (reason) {
                    self.modalText = "Si è verificato un Errore!";
                    scope.openModal = true;
                    //alert('Error: ' + JSON.stringify(reason));
            });
        };

        self.annulla = function () {
            $location.path('/visualizza_oggetti');
        };
    }]
});