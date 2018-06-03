var app = angular.module('visualizzaGrafico',['zingchart-angularjs']); //old visualizzaGrafico


app.controller('GraphController', function($scope, $http, $q){

    var getNumber = function(status) {
        var deferred = $q.defer();
        $http.get("ticketaudit/status/" + status).then(function (response) {
            deferred.resolve(response.data);
        }, function (reason) {
            alert(reason);
        });
        return deferred.promise;
    };

    $scope.myJson = {
        type : "bar",
        title:{
            backgroundColor : "transparent",
            fontColor :"black",
            text : "Statistiche Ticket "
        },
        backgroundColor : "transparent",
        series : [
            {
                values : [],
                backgroundColor : "#00B08E"
            }
        ]
    };

    var init = function() {

        getNumber("pending").then(function (value) {
            $scope.myJson.series[0].values[0] = value;
        }, function (reason) {
            alert(reason);
        });

        getNumber("open").then(function (value) {
            $scope.myJson.series[0].values[1] = value;
        }, function (reason) {
            alert(reason);
        });

        getNumber("closed").then(function (value) {
            $scope.myJson.series[0].values[2] = value;
        }, function (reason) {
            alert(reason);
        });
    };


    init();

});