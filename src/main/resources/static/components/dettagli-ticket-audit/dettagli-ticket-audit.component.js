'use strict';

// Register `dettagliTicket` component, along with its associated controller and template
angular.
module('dettagliTicketAudit').
component('dettagliTicketAudit', {
    templateUrl: 'components/dettagli-ticket-audit/dettagli-ticket-audit.html',
    controller: ['$http', '$routeParams', '$location','$scope', function dettagliTicketAuditController($http, $routeParams, $location,scope) {

        var self = this;
        self.modifyMode = false;
        self.audit = $location.path() === '/dettagli-ticket/audit/' + $routeParams.ticketId.toString();

        self.get = function () {
            var path = self.audit ? 'ticketaudit/' : 'ticket/';
            $http.get(path + $routeParams.ticketId.toString()).
            then(function(response) {
                self.ticket = response.data;
                if (self.audit) self.ticket.id = self.ticket.idTicket;
            }, function (reason) {
                alert(reason.toLocaleString());
            });
        };

        self.get();

        self.indietro = function () {
            var path = self.audit ? '/tickethistory/' + self.ticket.id.toString() : '/visualizza_ticket';
            $location.path(path);
        };

    }]
});