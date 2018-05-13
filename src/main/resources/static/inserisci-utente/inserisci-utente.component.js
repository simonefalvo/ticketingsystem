'use strict';

// Register `inserisciUtente` component, along with its associated controller and template
angular.
module('inserisciUtente').
component('inserisciUtente', {
    templateUrl: 'inserisci-utente/inserisci-utente.template.html',
    controller: ['$scope', '$http', '$location', function inserisciUtenteController($scope, $http, $location) {

        var self = this;

        self.inserisci = function () {
            $http.post('/utente', $scope.utente)
                .then(function (response) {
                    $location.path('/utente');
                    console.log('Success: ' + response.statusText);
                }, function (reason) {
                    console.log('Error: ' + reason.statusText);
            });
        };

        self.annulla = function () {
            $location.path('/utente');
        };
    }]
});