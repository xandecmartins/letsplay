
'use strict';
 
angular.module('letsPlayApp').factory('PlayerService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllPlayers: loadAllPlayers,
                getAllPlayers: getAllPlayers,
                getPlayer: getPlayer,
                createPlayer: createPlayer,
                updatePlayer: updatePlayer,
                removePlayer: removePlayer,
                importCollectionPlayer: importCollectionPlayer
            };
 
            return factory;
 
            function loadAllPlayers() {
                console.log('Fetching all players');
                var deferred = $q.defer();
                $http.get(urls.PLAYER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all players');
                            $localStorage.players = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading players');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function getAllPlayers(){
                return $localStorage.players;
            }
 
            function getPlayer(id) {
                console.log('Fetching Player with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.PLAYER_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Player with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading player with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function createPlayer(player) {
                console.log('Creating Player');
                var deferred = $q.defer();
                $http.post(urls.PLAYER_SERVICE_API, player)
                    .then(
                        function (response) {
                            loadAllPlayers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Player : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function updatePlayer(player, id) {
                console.log('Updating Player with id '+id);
                var deferred = $q.defer();
                $http.put(urls.PLAYER_SERVICE_API + id, player)
                    .then(
                        function (response) {
                            loadAllPlayers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Player with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removePlayer(id) {
                console.log('Removing Player with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.PLAYER_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllPlayers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Player with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function importCollectionPlayer(id) {
                console.log('Importing collection of Player with id '+id);
                var deferred = $q.defer();
                $http.post(urls.PLAYER_SERVICE_API + id+"/import")
                    .then(
                        function (response) {
                            loadAllPlayers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while importing collection of Player with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
        }
    ]);