
var app = angular.module('visualizzaGrafico',['zingchart-angularjs']); //old visualizzaGrafico

app.controller('GraphController', function($scope, $http, $q){


    var getNumber = function(status) {
        var deferred = $q.defer();
        $http.get("ticketaudit/" + status).then(function (response) {
            console.log(response.data);
            deferred.resolve(response.data);
        }, function (reason) {
            alert("Error: " + reason.status);
            deferred.reject({data: response, status: status});
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
        backgroundColor : "white",
        series : [
            {
                values : [
                    getNumber('pending'),
                    getNumber('open'),
                    getNumber('closed')
                ],
                text : [
                    'pending',
                    'open',
                    'closed'
                ],
                backgroundColor : "#4DC0CF"
            }
        ]
    };


    var init = function() {

        $scope.myJson.series[0].values.push(getNumber("open"));
    };

    init();

    $scope.addValues = function(){
        var val = Math.floor((Math.random() * 10));
        console.log(val);
        $scope.myJson.series[0].values.push(val);
    }
});