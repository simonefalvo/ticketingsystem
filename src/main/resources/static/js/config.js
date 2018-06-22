angular.module('materializeApp')
    .config(function ($routeProvider, $locationProvider, $httpProvider) {
        $locationProvider.hashPrefix('');

        $routeProvider.when('/nuovo_utente',
            {
                template:    '<inserisci-utente></inserisci-utente>'
            });
        $routeProvider.when('/visualizza_utenti',
            {
                template : '<lista-utente></lista-utente>'
            });
        $routeProvider.when('/nuovo_ticket',
            {
                template:    '<inserisci-ticket></inserisci-ticket>'
            });
        $routeProvider.when('/visualizza_ticket',
            {
                template:    '<lista-ticket></lista-ticket>'
            });
        $routeProvider.when('/nuovo_oggetto',
            {
                template:    '<inserisci-oggetto></inserisci-oggetto>'
            });
        $routeProvider.when('/visualizza_oggetti',
            {
                template:    '<lista-oggetto></lista-oggetto>'
            });
        $routeProvider.when('/visualizza_grafici',
            {
                templateUrl:    ''
            });
        $routeProvider.when('/dettagli-utente/:utenteId?',
            {
                template:    '<dettagli-utente></dettagli-utente>'
            });
        $routeProvider.when('/dettagli-ticket/:ticketId?',
            {
                template:    '<dettagli-ticket></dettagli-ticket>'
            });
        $routeProvider.when('/dettagli-oggetto/:oggettoId?',
            {
                template:    '<dettagli-oggetto></dettagli-oggetto>'
            });
        $routeProvider.otherwise(
            {
                redirectTo:     '/index'
            }
        );
    });


