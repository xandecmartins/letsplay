
'use strict';
 
angular.module('letsPlayApp').factory('EventService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllEvents: loadAllEvents,
                getAllEvents: getAllEvents,
                getEvent: getEvent,
                createEvent: createEvent,
                updateEvent: updateEvent,
                removeEvent: removeEvent
            };
 
            return factory;
 
            function loadAllEvents() {
                console.log('Fetching all events');
                var deferred = $q.defer();
                $http.get(urls.EVENT_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all events');
                            $localStorage.events = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading events');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function getAllEvents(){
                return $localStorage.events;
            }
 
            function getEvent(id) {
                console.log('Fetching Event with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.EVENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Event with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading event with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function createEvent(event) {
                console.log('Creating Event');
                var deferred = $q.defer();
                $http.post(urls.EVENT_SERVICE_API, event)
                    .then(
                        function (response) {
                            loadAllEvents();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Event : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function updateEvent(event, id) {
                console.log('Updating Event with id '+id);
                var deferred = $q.defer();
                $http.put(urls.EVENT_SERVICE_API + id, event)
                    .then(
                        function (response) {
                            loadAllEvents();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Event with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removeEvent(id) {
                console.log('Removing Event with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.EVENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllEvents();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Event with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
        }
    ]);