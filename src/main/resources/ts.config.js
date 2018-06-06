'use strict';

angular.
module('ticketingSystemApp').
config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.
        when('/', {
            template: ''
        }).
        when('/connect', {
            template: '<dash></dash>'
        }).
        otherwise('/');
    }
]);