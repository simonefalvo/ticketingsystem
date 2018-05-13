'use strict';

angular.
  module('ticketingSystemApp').
  config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
      $locationProvider.hashPrefix('!');

      $routeProvider.
        when('/', {
          template: '<lista-utente></lista-utente>'
        }).
        when('/utente', {
          template: '<lista-utente></lista-utente>'
        }).
        when('/ticket', {
          template: '<lista-ticket></lista-ticket>'
        }).
        when('/dettaglio/:personaId?', {
          template: '<dettagli-persona></dettagli-persona>'
        }).
        otherwise('/');
    }
  ]);
