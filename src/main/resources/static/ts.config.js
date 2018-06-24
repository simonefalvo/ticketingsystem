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
            when('/utente', {
                template: '<lista-utente></lista-utente>'
            }).
            when('/ticket', {
                template: '<lista-ticket></lista-ticket>'
            }).
            when('/oggetto', {
                template: '<lista-oggetto></lista-oggetto>'
            }).
            when('/nuovo-ticket', {
                template: '<inserisci-ticket></inserisci-ticket>'
            }).
            when('/nuovo-utente', {
                template: '<inserisci-utente></inserisci-utente>'
            }).
            when('/nuovo-oggetto', {
                template: '<inserisci-oggetto></inserisci-oggetto>'
            }).
            when('/dettagli-utente/:utenteId?', {
                template: '<dettagli-utente></dettagli-utente>'
            }).
            when('/dettagli-ticket/:ticketId?', {
                template: '<dettagli-ticket></dettagli-ticket>'
            }).
            when('/dettagli-ticket/audit/:ticketId?', {
                template: '<dettagli-ticket></dettagli-ticket>'
            }).
            when('/dettagli-oggetto/:oggettoId?', {
                template: '<dettagli-oggetto></dettagli-oggetto>'
            }).
            when('/grafico', {
                templateUrl: "./visualizza-grafico/visualizza-grafico.html",
                controller: "GraphCtrl"
            }).
            when('/auditing', {
                template: '<auditing></auditing>'
            }).
            when('/tickethistory/:ticketId?', {
                template: '<ticket-history></ticket-history>'
            }).
            when('/ticketlog/:ticketId?', {
                template: '<log-ticket></log-ticket>'
            }).
            when('/log', {
                template: '<log></log>'
            }).
            when('/log/author/:username?', {
                template: '<log-utente></log-utente>'
            }).
            otherwise('/');
        }
    ]);
