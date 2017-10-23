
'use strict';
 
angular.module('letsPlayApp').factory('BoardGameService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllBoardGames: loadAllBoardGames,
                getAllBoardGames: getAllBoardGames,
                getBoardGame: getBoardGame,
                createBoardGame: createBoardGame,
                updateBoardGame: updateBoardGame,
                removeBoardGame: removeBoardGame
            };
 
            return factory;
 
            function loadAllBoardGames() {
                console.log('Fetching all boardGames');
                var deferred = $q.defer();
                $http.get(urls.BOARD_GAME_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all boardGames');
                            $localStorage.boardGames = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading boardGames');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function getAllBoardGames(){
                return $localStorage.boardGames;
            }
 
            function getBoardGame(id) {
                console.log('Fetching BoardGame with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.BOARD_GAME_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully BoardGame with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading boardGame with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function createBoardGame(boardGame) {
                console.log('Creating BoardGame');
                var deferred = $q.defer();
                $http.post(urls.BOARD_GAME_SERVICE_API, boardGame)
                    .then(
                        function (response) {
                            loadAllBoardGames();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating BoardGame : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function updateBoardGame(boardGame, id) {
                console.log('Updating BoardGame with id '+id);
                var deferred = $q.defer();
                $http.put(urls.BOARD_GAME_SERVICE_API + id, boardGame)
                    .then(
                        function (response) {
                            loadAllBoardGames();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating BoardGame with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removeBoardGame(id) {
                console.log('Removing BoardGame with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.BOARD_GAME_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllBoardGames();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing BoardGame with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
        }
    ]);