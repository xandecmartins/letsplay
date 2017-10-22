
var app = angular.module('letsPlayApp',['ui.router','ngStorage']);
 
app.constant('urls', {
    BASE: '/',
    PLAYER_SERVICE_API : '/api/player/',
    GROUP_SERVICE_API : '/api/group/'
});
 
app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
 
        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/listPlayer',
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
        
        $stateProvider
        .state('players', {
            url: '/players',
            templateUrl: 'partials/listPlayer',
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
        
        $stateProvider
        .state('groups', {
            url: '/groups',
            templateUrl: 'partials/listGroup',
            controller:'GroupController',
            controllerAs:'ctrlGroup',
            resolve: {
                groups: function ($q, GroupService) {
                    console.log('Load all groups');
                    var deferred = $q.defer();
                    GroupService.loadAllGroups().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                }
            }
        });
        
        $urlRouterProvider.otherwise('/');
    }]);