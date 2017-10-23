'use strict';
 
angular.module('letsPlayApp').controller('PlayerController',
    ['PlayerService', '$scope',  function( PlayerService, $scope) {
 
        var self = this;
        self.player = {};
        self.players=[];
 
        self.submit = submit;
        self.getAllPlayers = getAllPlayers;
        self.createPlayer = createPlayer;
        self.updatePlayer = updatePlayer;
        self.removePlayer = removePlayer;
        self.editPlayer = editPlayer;
        self.reset = reset;
 
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
 
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
 
        function submit() {
            console.log('Submitting');
            if (self.player.id === undefined || self.player.id === null) {
                console.log('Saving New Player', self.player);
                createPlayer(self.player);
            } else {
                updatePlayer(self.player, self.player.id);
                console.log('Player updated with id ', self.player.id);
            }
        }
 
        function createPlayer(player) {
            console.log('About to create player');
            PlayerService.createPlayer(player)
                .then(
                    function (response) {
                        console.log('Player created successfully');
                        self.successMessage = 'Player created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.player={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Player');
                        self.errorMessage = 'Error while creating Player: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
 
        function updatePlayer(player, id){
            console.log('About to update player');
            PlayerService.updatePlayer(player, id)
                .then(
                    function (response){
                        console.log('Player updated successfully');
                        self.successMessage='Player updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Player');
                        self.errorMessage='Error while updating Player '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
 
        function removePlayer(id){
            console.log('About to remove Player with id '+id);
            PlayerService.removePlayer(id)
                .then(
                    function(){
                        console.log('Player '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing player '+id +', Error :'+errResponse.data);
                    }
                );
        }
        
        function importCollectionPlayer(id){
            console.log('About to import collection Player with id '+id);
            PlayerService.importCollectionPlayer(id)
                .then(
                    function(){
                        console.log('Player '+id + ' collection imported successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing player '+id +', Error :'+errResponse.data);
                    }
                );
        }
 
        function getAllPlayers(){
            return PlayerService.getAllPlayers();
        }
 
        function editPlayer(id) {
            self.successMessage='';
            self.errorMessage='';
            PlayerService.getPlayer(id).then(
                function (player) {
                    self.player = player;
                },
                function (errResponse) {
                    console.error('Error while removing player ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.player={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);