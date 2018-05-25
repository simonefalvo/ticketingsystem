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
                .then(function (response) {
                    $location.path('/oggetto');
                    console.log('Success: ' + response.statusText);
                }, function (reason) {
                    console.log('Error: ' + JSON.stringify(reason));
            });
        };

        self.annulla = function () {
            console.log('inserimento annullato');
            $location.path('/oggetto');
        };
    }]
});