'use strict';

// Register `ticketHistory` component, along with its associated controller and template
angular.
module('ticketHistory').filter('operationFilter', function() {
    return function(op_number){
        if(op_number===0)
            return 'inserimento';
    }
})