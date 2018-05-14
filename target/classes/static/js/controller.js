/*app.controller("myController",function($scope, myAjax) {

    $scope.request = function () {

        var postParam = {
            id : 6,
			nome : "paolo",
			cognome : "rossi"
        };

		var param = {
			id : 5
		};

        myAjax.delete(param).then(function(response) {
            $scope.persone = response.data.records;
            $scope.esito = "successo";
        }, function (response) {
            console.log("Err: " + response.statusText);
            $scope.esito = "errore: " + response.statusText;
        });

    };

});*/

app.controller('utenteController', ['$scope', 'utenteService',
    function ($scope, utenteService) {
        // this is base url
        var baseUrl = 'http://localhost:8200/ticketingsystem/';
        // get all utenti from databse
        $scope.getUtenti=function() {
            var apiRoute = baseUrl + 'utente/';
            var _utente = utenteService.getAll(apiRoute);
            _utente.then(function (response) {
                    $scope.utenti = response.data;
                },
                function (error) {
                    console.log("Error: " + error);
                });
        }
        $scope.getUtenti();

    }]);
