'use strict';

// Register `navbar` component, along with its associated controller and template
angular.
module('welcome')
    .component('welcome', {
        templateUrl: 'components/welcome/welcome.html',
        controller: ['$scope','$location',function welcomeController($scope, $location) {

        }]});