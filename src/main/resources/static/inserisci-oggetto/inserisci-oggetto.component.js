'use strict';

// Register `inserisciOggetto` component, along with its associated controller and template
angular.
module('inserisciOggetto').
component('inserisciOggetto', {
    templateUrl: 'inserisci-oggetto/inserisci-oggetto.template.html',
    controller: ['$http', '$location', function inserisciOggettoController($http, $location) {

        var self = this;

        self.inserisci = function () {
            console.log(self.oggetto);
            $http.post('oggetto/', self.oggetto)
                .then(function () {
                    $location.path('/oggetto');
                    alert("oggetto inserito con successo!")
                }, function (reason) {
                    alert('Error: ' + JSON.stringify(reason));
            });
        };

        self.annulla = function () {
            $location.path('/oggetto');
        };
    }]
});