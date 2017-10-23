
var app = angular.module('letsPlayApp',['ui.router','ngStorage']);
 
app.constant('urls', {
    BASE: '/',
    PLAYER_SERVICE_API : '/api/player/',
    GROUP_SERVICE_API : '/api/group/',
    EVENT_SERVICE_API : '/api/event/',
    BOARD_GAME_SERVICE_API : '/api/board_game/'
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
        .state('events', {
            url: '/events',
            templateUrl: 'partials/listEvent',
            controller:'EventController',
            controllerAs:'ctrl',
            resolve: {
                events: function ($q, EventService) {
                    console.log('Load all events');
                    var deferred = $q.defer();
                    EventService.loadAllEvents().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                }
            }
        });
        
        $stateProvider
        .state('groups', {
            url: '/groups',
            templateUrl: 'partials/listGroup',
            controller:'GroupController',
            controllerAs:'ctrl',
            resolve: {
                groups: function ($q, GroupService) {
                    console.log('Load all groups');
                    var deferred = $q.defer();
                    GroupService.loadAllGroups().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                }
            }
        });
        
        $stateProvider
        .state('boardGames', {
            url: '/boardGames',
            templateUrl: 'partials/listBoardGame',
            controller:'BoardGameController',
            controllerAs:'ctrl',
            resolve: {
            	boardGames: function ($q, BoardGameService) {
                    console.log('Load all groups');
                    var deferred = $q.defer();
                    BoardGameService.loadAllBoardGames().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                }
            }
        });
        
        $urlRouterProvider.otherwise('/');
    }]);