'use strict';
var module = angular.module('listaUtente');
// Register `listaUtente` component, along with its associated controller and template

module.factory('myService',['$http','$location', function($http,$location) {

        return{
            getAll: function () {
                return $http.get('utente/').then(function(response) {
                    //self.utenti = response.data;
                    console.log("Success: " + response.statusText);
                    return response.data;
                }, function (reason) {
                    console.log("Error: " + reason.statusText);
                });
            },
            cancella: function ($utenteId) {
                return $http.delete('utente/' + $utenteId.toString()).
                then(function (response) {
                    console.log("Success: " + response.statusText);
                }, function (reason) {
                    console.log("Error: " + reason.statusText);
                })
            }
        };
}]);

module.component('listaUtente', {
    templateUrl: 'lista-utente/lista-utente.template.html',
    controller: ['$http', '$location','$uibModal','$log','$document','myService' ,function listaUtenteController($http, $location,$uibModal, $log, $document,myService) {

        var self = this;

        self.getAll = function () {
            myService.getAll().then(function(utenti) {
                self.utenti = utenti;
            });
        };

        self.getAll();

        self.animationsEnabled = true;

        self.open = function (id,nome,cognome,username,email,password,tipo,size, parentSelector) {
            var items = [id,nome,cognome,username,email,password,tipo];
            var parentElem = parentSelector ?
                angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
            var modalInstance = $uibModal.open({
                animation: self.animationsEnabled,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'dettagliUtente.html',
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
        var bool = confirm("Vuoi davvero cancellare l'utente?");
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
            templateUrl: 'modificaUtente.html',
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

    $ctrl.conferma = function ($utente_id) {
        alert($ctrl.utente);
        $http.put("utente/"+$utente_id.toString(),$ctrl.utente)
            .then(function (response) {
                console.log('Success: ' + response.statusText);
            }, function (reason) {
                console.log('Error: ' + JSON.stringify(reason));
            }
            );
    };

});