'use strict';

// Register `inserisciUtente` component, along with its associated controller and template
angular.
module('inserisciUtente').
component('inserisciUtente', {
    templateUrl: 'inserisci-utente/inserisci-utente.template.html',
    controller: ['$http', function inserisciUtenteController($scope, $http) {

        var inserisci = function () {
            $http.post('/utente', $scope.utente);
        }

    }]
});