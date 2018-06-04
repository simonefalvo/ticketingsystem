var app = angular.module('visualizzaGrafico',['zingchart-angularjs']); //old visualizzaGrafico


app.controller('GraphController', function($scope, $http, $q){

    var getNumber = function(status) {
        var deferred = $q.defer();
        $http.get("ticket/status/" + status).then(function (response) {
            deferred.resolve(response.data);
        }, function (reason) {
            alert(reason);
        });
        return deferred.promise;
    };

    $scope.myJson = {
        type : "pie",
        plot: {
            borderColor: "#2b313b",
            borderWidth: 0,
            animation: {
                effect: 2,
                method: 5,
                speed: 900,
                sequence: 1,
                delay: 3000
            },
            tooltip: {
                text: "%v %t Tickets"
            }
        },
        title:{
            backgroundColor : "transparent",
            fontColor :"black",
            text : "Statistiche Ticket "
        },
        backgroundColor : "transparent",


        series : [
            {
                values : [],
                backgroundColor : "#b00209",
                text: "Pending ",
                detached: true
            }, {
                values : [],
                backgroundColor : "#aeb037",
                text: "Open ",
                detached: true
            }, {
                values : [],
                backgroundColor : "#32b000",
                text: "Closed ",
                detached: true
            }
        ]
    };

    $scope.myJson2 = {
        type : "bar",
        title:{
            backgroundColor : "transparent",
            fontColor :"black",
            text : "Statistiche Ticket "
        },
        backgroundColor : "transparent",
        series : [
            {
                values : [1,2,3,4],
                backgroundColor : "#b02a43"
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
            $scope.myJson.series[1].values[0]= value;
        }, function (reason) {
            alert(reason);
        });

        getNumber("closed").then(function (value) {
            $scope.myJson.series[2].values[0]= value;
        }, function (reason) {
            alert(reason);
        });
    };


    init();

});