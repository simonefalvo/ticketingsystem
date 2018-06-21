'use strict';

// Register `navbar` component, along with its associated controller and template
var app = angular.
module('navbar')
    .config(function ($routeProvider, $locationProvider, $httpProvider) {

        $routeProvider.when('/nuovo_utente',
            {
                templateUrl:    'components/inserisci-utente/inserisci-utente.html'
            });
        $routeProvider.when('/visualizza_utenti',
            {
                templateUrl:    'components/lista-utente/lista-utente.html',
                controller:     'listaUtenteController'
            });
        $routeProvider.when('/nuovo_ticket',
            {
                templateUrl:    'components/inserisci-ticket/inserisci-ticket.html'
            });
        $routeProvider.when('/visualizza_ticket',
            {
                templateUrl:    'components/lista-ticket/lista-ticket.html'
            });
        $routeProvider.when('/nuovo_oggetto',
            {
                templateUrl:    'components/inserisci-oggetto/inserisci-oggetto.html'
            });
        $routeProvider.when('/visualizza_oggetti',
            {
                templateUrl:    'components/lista-oggetto/lista-oggetto.html'
            });
        $routeProvider.when('/visualizza_grafici',
            {
                templateUrl:    'components/visualizza-grafico/visualizza-grafico.html'
            });
        $routeProvider.otherwise(
            {
                redirectTo:     '/index'
            }
        );
    });

app.component('navbar', {
    templateUrl: 'components/navbar/navbar.html',
    controller: ['$scope','$location',function navbarController($scope, $location) {

        $scope.insertUser = function () {
            $location.url('/nuovo_utente');
        };

        $scope.visUsers = function () {
            $location.url('/visualizza_utenti');
        };

        $scope.insertTicket = function () {
            $location.url('/nuovo_ticket');
        };

        $scope.visTickets = function () {
            $location.url('/visualizza_ticket');
        };

        $scope.insertObject = function () {
            $location.url('/nuovo_oggetto');
        };

        $scope.visObjects = function () {
            $location.url('/visualizza_oggetti');
        };

        $scope.visGraphs = function () {
            $location.url('/visualizza_grafici');
        };

    }]});