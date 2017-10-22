
'use strict';
 
angular.module('letsPlayApp').factory('GroupService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllGroups: loadAllGroups,
                getAllGroups: getAllGroups,
                getGroup: getGroup,
                createGroup: createGroup,
                updateGroup: updateGroup,
                removeGroup: removeGroup
            };
 
            return factory;
 
            function loadAllGroups() {
                console.log('Fetching all groups');
                var deferred = $q.defer();
                $http.get(urls.GROUP_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all groups');
                            $localStorage.groups = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading groups');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function getAllGroups(){
                return $localStorage.groups;
            }
 
            function getGroup(id) {
                console.log('Fetching Group with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.GROUP_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Group with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading group with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function createGroup(group) {
                console.log('Creating Group');
                var deferred = $q.defer();
                $http.post(urls.GROUP_SERVICE_API, group)
                    .then(
                        function (response) {
                            loadAllGroups();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Group : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function updateGroup(group, id) {
                console.log('Updating Group with id '+id);
                var deferred = $q.defer();
                $http.put(urls.GROUP_SERVICE_API + id, group)
                    .then(
                        function (response) {
                            loadAllGroups();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Group with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removeGroup(id) {
                console.log('Removing Group with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.GROUP_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllGroups();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Group with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
        }
    ]);