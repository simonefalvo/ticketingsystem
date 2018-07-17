'use strict';

// Register `listaOggetto` component, along with its associated controller and template
angular.
module('listaOggetto').
component('listaOggetto', {
    templateUrl: 'components/lista-oggetto/lista-oggetto.html',
    controller: ['$http', '$location', '$scope',function listaOggettoController($http, $location,$scope) {

        var self = this;

        $scope.sortType     = 'id'; // set the default sort type
        $scope.sortReverse  = false;  // set the default sort order
        $scope.searchFilter   = '';

        self.getAll = function () {
            $http.get('oggetto/').then(function(response) {
                self.oggetti = response.data;
            }, function (reason) {
                alert("Error: " + reason.statusText);
            });
        };

        self.getAll();

        self.dettagli = function (oggettoId) {
            $location.path('/dettagli-oggetto/' + oggettoId.toString());
        };

    }]
});