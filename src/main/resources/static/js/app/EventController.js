'use strict';
 
angular.module('letsPlayApp').controller('EventController',
    ['EventService','GroupService', '$scope',  function( EventService, GroupService, $scope) {
 
        var self = this;
        self.event = {};
        self.events=[];
 
        self.submit = submit;
        self.getAllEvents = getAllEvents;
        self.createEvent = createEvent;
        self.updateEvent = updateEvent;
        self.removeEvent = removeEvent;
        self.editEvent = editEvent;
        self.reset = reset;
 
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
 
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
 
        function submit() {
            console.log('Submitting');
            if (self.event.id === undefined || self.event.id === null) {
                console.log('Saving New Event', self.event);
                createEvent(self.event);
            } else {
                updateEvent(self.event, self.event.id);
                console.log('Event updated with id ', self.event.id);
            }
        }
 
        function createEvent(event) {
            console.log('About to create event');
            EventService.createEvent(event)
                .then(
                    function (response) {
                        console.log('Event created successfully');
                        self.successMessage = 'Event created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.event={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Event');
                        self.errorMessage = 'Error while creating Event: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
 
        function updateEvent(event, id){
            console.log('About to update event');
            EventService.updateEvent(event, id)
                .then(
                    function (response){
                        console.log('Event updated successfully');
                        self.successMessage='Event updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Event');
                        self.errorMessage='Error while updating Event '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
 
        function removeEvent(id){
            console.log('About to remove Event with id '+id);
            EventService.removeEvent(id)
                .then(
                    function(){
                        console.log('Event '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing event '+id +', Error :'+errResponse.data);
                    }
                );
        }
 
        function getAllEvents(){
            return EventService.getAllEvents();
        }
        
        function getAllGroups(){
            return GroupService.getAllGroups();
        }
 
        function editEvent(id) {
            self.successMessage='';
            self.errorMessage='';
            EventService.getEvent(id).then(
                function (event) {
                    self.event = event;
                },
                function (errResponse) {
                    console.error('Error while removing event ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.event={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);