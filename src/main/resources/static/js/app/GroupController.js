'use strict';
 
angular.module('letsPlayApp').controller('GroupController',
    ['GroupService', '$scope',  function( GroupService, $scope) {
 
        var self = this;
        self.group = {};
        self.groups=[];
 
        self.submit = submit;
        self.getAllGroups = getAllGroups;
        self.createGroup = createGroup;
        self.updateGroup = updateGroup;
        self.removeGroup = removeGroup;
        self.editGroup = editGroup;
        self.reset = reset;
 
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
 
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
 
        function submit() {
            console.log('Submitting');
            if (self.group.id === undefined || self.group.id === null) {
                console.log('Saving New Group', self.group);
                createGroup(self.group);
            } else {
                updateGroup(self.group, self.group.id);
                console.log('Group updated with id ', self.group.id);
            }
        }
 
        function createGroup(group) {
            console.log('About to create group');
            GroupService.createGroup(group)
                .then(
                    function (response) {
                        console.log('Group created successfully');
                        self.successMessage = 'Group created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.group={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Group');
                        self.errorMessage = 'Error while creating Group: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
 
        function updateGroup(group, id){
            console.log('About to update group');
            GroupService.updateGroup(group, id)
                .then(
                    function (response){
                        console.log('Group updated successfully');
                        self.successMessage='Group updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Group');
                        self.errorMessage='Error while updating Group '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
 
        function removeGroup(id){
            console.log('About to remove Group with id '+id);
            GroupService.removeGroup(id)
                .then(
                    function(){
                        console.log('Group '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing group '+id +', Error :'+errResponse.data);
                    }
                );
        }
 
        function getAllGroups(){
            return GroupService.getAllGroups();
        }
 
        function editGroup(id) {
            self.successMessage='';
            self.errorMessage='';
            GroupService.getGroup(id).then(
                function (group) {
                    self.group = group;
                },
                function (errResponse) {
                    console.error('Error while removing group ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.group={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);