'use strict';

// Register `inserisciUtente` component, along with its associated controller and template
angular.
module('inserisciUtente').
component('inserisciUtente', {
    templateUrl: 'components/inserisci-utente/inserisci-utente.html',
    controller: ['$http', '$location','$scope', function inserisciUtenteController($http, $location,$scope) {

        var self = this;

        self.getRuoli = function (){
            $http.get('ruolo/').then(function (response) {
                self.ruoli = response.data;
            }, function (reason) {
                alert("Error: " + reason.status);
            });
        };

        self.getRuoli();

        self.inserisci = function () {
            //console.log(self.utente);
            self.utente.ruolo = self.ruolo;
            console.log(self.utente);
            $http.post('utente/', self.utente)
                .then(function (response) {
                    $location.path('/visualizza_utenti');
                }, function (reason) {
                    //self.respData = reason.data;
                    if (reason.data == 1){
                        //username già presente
                        self.errData = "Username già presente!";
                    }
                    if ( reason.data == 2){
                        self.errData = "Email già presente";
                    }
                    console.log(reason.data);
            });
        };

        self.annulla = function () {
            $location.path('/visualizza_utenti');
        };
    }]
});