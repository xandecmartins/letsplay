
var app = angular.module('letsPlayApp',['ui.router','ngStorage']);
 
app.constant('urls', {
    BASE: '/',
    USER_SERVICE_API : '/api/player/'
});
 
app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
 
        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'PlayerController',
                controllerAs:'ctrl',
                resolve: {
                    players: function ($q, PlayerService) {
                        console.log('Load all players');
                        var deferred = $q.defer();
                        PlayerService.loadAllPlayers().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);