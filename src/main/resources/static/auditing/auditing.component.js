'use strict';

// Register `auditing` component, along with its associated controller and template
angular.
module('auditing').
component('auditing', {
    templateUrl: 'auditing/auditing.template.html',
    controller: ['$http', '$q', function auditingController($http, $q) {

        var self = this;

        var getNumber = function(status) {
            var deferred = $q.defer();
            $http.get("ticketaudit/" + status).then(function (response) {
                deferred.resolve(response.data);
            }, function (reason) {
                alert(reason);
            });
            return deferred.promise;
        };

        var init = function () {

            getNumber("pending").then(function (value) {
                self.pendingTickets = value;
            }, function (reason) {
                alert(reason);
            });

            getNumber("open").then(function (value) {
                self.openTickets = value;
            }, function (reason) {
                alert(reason);
            });

            getNumber("released").then(function (value) {
                self.releasedTickets = value;
            }, function (reason) {
                alert(reason);
            });

            getNumber("closed").then(function (value) {
                self.closedTickets = value;
            }, function (reason) {
                alert(reason);
            });

            getNumber("rejected").then(function (value) {
                self.rejectedTickets = value;
            }, function (reason) {
                alert(reason);
            });

        };

        self.myJson = {
            type : 'line',
            series : [
                { values : [54,23,34,23,43] },
                { values : [10,15,16,20,40] }
            ]
        };

        init();

    }]
});