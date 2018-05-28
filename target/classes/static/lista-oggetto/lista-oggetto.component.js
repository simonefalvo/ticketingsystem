'use strict';

angular.
module('listaOggetto').factory('myService',['$http','$location', function($http,$location) {

    return{
        getAll: function () {
            return $http.get('oggetto/').then(function(response) {
                console.log("Success: " + response.statusText);
                return response.data;
            }, function (reason) {
                console.log("Error: " + reason.statusText);
            });
        },
        cancella: function ($oggettoID) {
            return $http.delete('oggetto/' + $oggettoID.toString()).
            then(function (response) {
                console.log("Success: " + response.statusText);
            }, function (reason) {
                console.log("Error: " + reason.statusText);
            })
        }
    };
}]);

// Register `listaOggetto` component, along with its associated controller and template
angular.
module('listaOggetto').
component('listaOggetto', {
    templateUrl: 'lista-oggetto/lista-oggetto.template.html',
    controller: ['$http','$location','$uibModal','$log','$document','myService', function listaOggettoController($http, $location,$uibModal, $log, $document,myService) {

        var self = this;

        self.getAll = function () {
            myService.getAll().then(function(oggetto) {
                self.oggetti = oggetto;
            });
        };

        self.getAll();

        self.animationsEnabled = true;

        self.open = function (id,nome,versione,size, parentSelector) {
            var items = [id,nome,versione];
            var parentElem = parentSelector ?
                angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
            var modalInstance = $uibModal.open({
                animation: self.animationsEnabled,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'dettagliOggetto.html',
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
        var bool = confirm("Vuoi davvero cancellare l'oggetto?");
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
            templateUrl: 'modificaOggetto.html',
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
        $http.put("oggetto/"+$ticket_id.toString(),$ctrl.tickets)
            .then(function (response) {
                    console.log('Success: ' + response.statusText);
                }, function (reason) {
                    console.log('Error: ' + JSON.stringify(reason));
                }
            );
    };

});