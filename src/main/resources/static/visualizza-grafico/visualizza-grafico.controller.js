
var app = angular.module('visualizzaGrafico',['zingchart-angularjs']); //old visualizzaGrafico

app.controller('GraphController', function($scope){
    var self = this;


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
                values : [1,2,3,4],
                backgroundColor : "#4DC0CF"
            }
        ]
    };

    $scope.addValues = function(){
        var val = Math.floor((Math.random() * 10));
        console.log(val);
        $scope.myJson.series[0].values.push(val);
    }

});