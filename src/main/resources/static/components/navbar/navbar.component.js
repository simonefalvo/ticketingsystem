'use strict';

// Register `navbar` component, along with its associated controller and template
var app = angular.
module('navbar')
    .component('navbar', {
    templateUrl: 'components/navbar/navbar.html',
    controller: ['$scope','$location','$http',function navbarController($scope, $location,$http) {

        var self = this;

        function get_role() {
            $http.get('utente/getRole').then(function (response) {
                self.role = response.data;
            });
        }

        function get_logged() {
            $http.get('utente/logged').then(function (response) {
                $scope.logged = response.data;
            });
        }

        get_role();
        get_logged();

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

        $scope.visLog = function () {
            $location.url('/visualizza_log');
        };

        $scope.visAccount = function () {
            $location.url('/dettagli-account');
        };

    }]});