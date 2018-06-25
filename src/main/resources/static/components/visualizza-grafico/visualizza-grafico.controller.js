var app = angular.module('visualizzaGrafico',['zingchart-angularjs']); //old visualizzaGrafico


app.controller('GraphCtrl', function($scope, $http, $q){

    var getNumber = function(path, status) {
        var deferred = $q.defer();
        $http.get(path + status).then(function (response) {
            deferred.resolve(response.data);
        }, function (reason) {
            alert(reason);
        });
        return deferred.promise;
    };

    $scope.myPie = {
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
            adjustLayout: true,
            backgroundColor : "transparent",
            fontColor :"black",
            text : "Percentuale Ticket Rigettati e Risolti"
        },
        legend: {
            backgroundColor : "transparent",
            layout: "x5",
            position: "50%",
            adjustLayout: true,
            borderColor: "transparent",
            marker: {
                borderRadius: 10,
                borderColor: "transparent"
            }
        },
        backgroundColor : "transparent",


        series : [
            {
                values : [],
                backgroundColor : "#616cb0",
                text: "Others ",
                detached: true
            }, {
                values : [],
                backgroundColor : "#07b006",
                text: "Closed ",
                detached: true
            }, {
                values : [],
                backgroundColor : "#b01322",
                text: "Rejected ",
                detached: true
            }
        ]
    };

    $scope.myBar = {
        type : "bar",
        title:{
            adjustLayout: true,
            backgroundColor : "transparent",
            fontColor :"black",
            text : "Numero Ticket nel Sistema"
        },
        legend: {
            adjustLayout: true,
            backgroundColor : "transparent",
            layout: "x5",
            position: "50%",
            borderColor: "transparent",
            marker: {
                borderRadius: 10,
                borderColor: "transparent"
            }
        },
        tooltip: {
            text: "%v %t Tickets"
        },
        backgroundColor : "transparent",
        series : [
            {
                values : [],
                backgroundColor : "#b0a513",
                text: "Pending ",
                detached: true
            }, {
                values : [],
                backgroundColor : "#16b0af",
                text: "Open ",
                detached: true
            }, {
                values : [],
                backgroundColor : "#202db0",
                text: "Released ",
                detached: true
            }, {
                values : [],
                backgroundColor : "#07b006",
                text: "Closed ",
                detached: true
            }, {
                values : [],
                backgroundColor : "#b01322",
                text: "Rejected ",
                detached: true
            }
        ]
    };

    var init = function() {

        getNumber("ticket/status/", "pending").then(function (value) {
            $scope.myBar.series[0].values[0] = value;
        }, function (reason) {
            alert(reason);
        });

        getNumber("ticket/status/", "open").then(function (value) {
            $scope.myBar.series[1].values[0]= value;
        }, function (reason) {
            alert(reason);
        });

        getNumber("ticket/status/", "released").then(function (value) {
            $scope.myBar.series[2].values[0]= value;
        }, function (reason) {
            alert(reason);
        });

        getNumber("ticket/status/", "closed").then(function (value) {
            $scope.myBar.series[3].values[0]= value;
        }, function (reason) {
            alert(reason);
        });

        getNumber("ticket/status/", "rejected").then(function (value) {
            $scope.myBar.series[4].values[0]= value;
        }, function (reason) {
            alert(reason);
        });



        getNumber("ticketaudit/status/", "closed").then(function (value) {
            $scope.myPie.series[1].values[0]= value;
            console.log("closed");
        }, function (reason) {
            alert(reason);
        });

        getNumber("ticketaudit/status/", "rejected").then(function (value) {
            $scope.myPie.series[2].values[0]= value;
            console.log("rejected");
        }, function (reason) {
            alert(reason);
        });

        getNumber("ticketaudit/status/", "others").then(function (value) {
            $scope.myPie.series[0].values[0] = value;
            console.log("others");
        }, function (reason) {
            alert(reason);
        });
    };


    init();

});