/*
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

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

angular.module('ui.bootstrap').controller('ModalInstanceCtrl',function ($uibModalInstance,$uibModal,$http ,items,myService) {
    var $ctrl = this;
    $ctrl.items = items;
    $ctrl.selected = {
        item: $ctrl.items[0]
    };
    $ctrl.animationsEnabled = true;

    $ctrl.ok = function () {
        $uibModalInstance.close();
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

    $ctrl.elimina = function ($id) {
        var bool = confirm("Vuoi davvero cancellare il ticket?");
        if (bool == true){
            myService.cancella($id).then(function () {
                $ctrl.ok();
            });
        }else {
            $ctrl.ok();
        }
    };

    $ctrl.modifica = function ($id,parentSelector,size) {
        $ctrl.ok();

        var parentElem = parentSelector ?
            angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
        var modalInstance = $uibModal.open({
            animation: $ctrl.animationsEnabled,
            ariaLabelledBy: 'modifica-title',
            ariaDescribedBy: 'modifica-body',
            templateUrl: 'modificaTicket.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$modificaCtrl',
            size: size,
            appendTo: parentElem,
            resolve: {
                items: function () {
                    return items;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            //boh
        }, function () {
            //boh
        });

    };

    $ctrl.conferma = function ($ticket_id) {
        $http.put("ticket/"+$ticket_id.toString(),$ctrl.tickets)
            .then(function (response) {
                    console.log('Success: ' + response.statusText);
                }, function (reason) {
                    console.log('Error: ' + JSON.stringify(reason));
                }
            );
    };

});
*/

'use strict';

// Register `listaUtente` component, along with its associated controller and template
angular.
module('listaTicket').
component('listaTicket', {
    templateUrl: 'lista-ticket/lista-ticket.template.html',
    controller: ['$http', '$location', function listaTicketController($http, $location) {

        var self = this;

        self.getAll = function () {
            $http.get('ticket/').then(function(response) {
                self.tickets = response.data;
                console.log("Success: " + response.statusText);
            }, function (reason) {
                console.log("Error: " + reason.statusText);
            });
        };

        self.getAll();

        self.dettagli = function (ticketId) {
            $location.path('/dettagli-ticket/' + ticketId.toString());
            //$http.get('ticket/' + ticketId.toString());
        };

        self.elimina = function (ticketId) {
            $http.delete('ticket/' + ticketId.toString()).
            then(function () {
                self.getAll();
                alert("Ticket eliminato con successo!");
            }, function (reason) {
                alert("Error: " + reason.statusText);
            })
        };

    }]
});