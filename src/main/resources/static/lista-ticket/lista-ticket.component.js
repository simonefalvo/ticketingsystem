'use strict';

angular.
module('listaTicket').factory('myService',['$http','$location', function($http,$location) {

    return{
        getAll: function () {
            return $http.get('ticket/').then(function(response) {
                console.log("Success: " + response.statusText);
                return response.data;
            }, function (reason) {
                console.log("Error: " + reason.statusText);
            });
        },
        cancella: function ($utenteId) {
            return $http.delete('ticket/' + $utenteId.toString()).
            then(function (response) {
                console.log("Success: " + response.statusText);
            }, function (reason) {
                console.log("Error: " + reason.statusText);
            })
        }
    };
}]);

// Register `listaUtente` component, along with its associated controller and template
angular.
module('listaTicket').
component('listaTicket', {
    templateUrl: 'lista-ticket/lista-ticket.template.html',
    controller: ['$http','$location','$uibModal','$log','$document','myService', function listaTicketController($http,$location,$uibModal, $log, $document,myService) {

        var self = this;


        self.getAll = function () {
            myService.getAll().then(function(ticket) {
                self.tickets = ticket;
            });
        };

        self.getAll();

        self.animationsEnabled = true;

        self.open = function (id,assistenteid,customerid,categoria,descrizione,priorita_customer,priorita_team,prodotto,stato,teamid,time_stamp,titolo,oggetto_id,size, parentSelector) {
            var items = [id,assistenteid,customerid,categoria,descrizione,priorita_customer,priorita_team,prodotto,stato,teamid,time_stamp,titolo,oggetto_id];
            var parentElem = parentSelector ?
                angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
            var modalInstance = $uibModal.open({
                animation: self.animationsEnabled,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'dettagliTicket.html',
                controller: 'ModalInstanceCtrl',
                controllerAs: '$modCtrl',
                size: size,
                appendTo: parentElem,
                resolve: {
                    items: function () {
                        return items;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                self.selected = selectedItem;
                self.getAll();
            }, function () {
                self.getAll();
            });
        };

        self.toggleAnimation = function () {
            self.animationsEnabled = !self.animationsEnabled;
        };

    }]
});