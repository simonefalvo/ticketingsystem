'use strict';

// Register `listaUtente` component, along with its associated controller and template
angular.
module('listaUtente').
component('listaUtente', {
    templateUrl: 'components/lista-utente/lista-utente.html',
    controller: ['$http', '$location', '$scope', function listaUtenteController($http, $location,$scope) {

        var self = this;

        $scope.sortType     = 'id'; // set the default sort type
        $scope.sortReverse  = false;  // set the default sort order
        $scope.searchFilter   = '';

        self.getAll = function () {
            $http.get('utente/').then(function (response) {
                self.utenti = response.data;
            }, function (reason) {
                alert("Error: " + reason.status);
            });
        };

        self.getAll();

        self.dettagli = function (utenteId) {
            $location.path('/dettagli-utente/' + utenteId.toString());
        };

    }]
});
