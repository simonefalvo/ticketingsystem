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
            fontColor :"black"
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
            fontColor :"black"
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



    $scope.logBar = {
        type : "bar",
        title:{
            adjustLayout: true,
            backgroundColor : "transparent",
            fontColor :"black"
        },
        backgroundColor : "transparent",
        scaleX: {
            transform: {
                type: 'date',
                all: "%d.%m.%Y"
            },
            zooming: true,
            values: []
        },
        series : [
            {
                values : [],
                backgroundColor : "#4DC0CF"
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
        }, function (reason) {
            alert(reason);
        });

        getNumber("ticketaudit/status/", "rejected").then(function (value) {
            $scope.myPie.series[2].values[0]= value;
        }, function (reason) {
            alert(reason);
        });

        getNumber("ticketaudit/status/", "others").then(function (value) {
            $scope.myPie.series[0].values[0] = value;
        }, function (reason) {
            alert(reason);
        });
    };


    var getLogTickets = function(path, start, end) {
        var deferred = $q.defer();
        $http.get(path + start + "/" + end).then(function (response) {
            deferred.resolve(response.data);
        }, function (reason) {
            alert(reason);
        });
        return deferred.promise;
    };

    $scope.logCalc = function(start, end) {
        getLogTickets("log/", start.getTime().toString(), end.getTime().toString()).then(function (value) {
            buildSeries(value);
        }, function (reason) {
            alert(reason);
        });
    };

    var buildSeries = function (map) {
        $scope.logBar.scaleX.values = [];
        $scope.logBar.series[0].values = [];
        var total = 0;
        var sum = 0;
        console.log("BUILDING");
        for (key in map) {
            console.log(new Date(parseInt(key, 10)));
            console.log(map[key]);
            $scope.logBar.scaleX.values.push(key);
            $scope.logBar.series[0].values.push(map[key]);
            sum += map[key];
            total++;
        }
        $scope.media = sum / total;
    };

    init();

});