'use strict';

// Register `log` component, along with its associated controller and template
angular.
module('log').
component('log', {
    templateUrl: 'components/log/log.html',
    controller: ['$http','$scope', function logController($http,$scope) {

        var self = this;

        $scope.sortType     = 'timestamp'; // set the default sort type
        $scope.sortReverse  = false;  // set the default sort order
        $scope.searchFilter   = '';

        self.getAll = function () {
            $http.get("log").then(function(response) {
                self.log = response.data;
            }, function (reason) {
                alert("Error: " + JSON.stringify(reason));
            });
        };

        self.getAll();

    }]
});