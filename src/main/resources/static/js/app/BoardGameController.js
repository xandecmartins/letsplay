'use strict';
 
angular.module('letsPlayApp').controller('BoardGameController',
    ['BoardGameService', '$scope',  function( BoardGameService, $scope) {
 
        var self = this;
        self.boardGame = {};
        self.boardGames=[];
 
        self.submit = submit;
        self.getAllBoardGames = getAllBoardGames;
        self.createBoardGame = createBoardGame;
        self.updateBoardGame = updateBoardGame;
        self.removeBoardGame = removeBoardGame;
        self.editBoardGame = editBoardGame;
        self.reset = reset;
 
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
 
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
 
        function submit() {
            console.log('Submitting');
            if (self.boardGame.id === undefined || self.boardGame.id === null) {
                console.log('Saving New BoardGame', self.boardGame);
                createBoardGame(self.boardGame);
            } else {
                updateBoardGame(self.boardGame, self.boardGame.id);
                console.log('BoardGame updated with id ', self.boardGame.id);
            }
        }
 
        function createBoardGame(boardGame) {
            console.log('About to create boardGame');
            BoardGameService.createBoardGame(boardGame)
                .then(
                    function (response) {
                        console.log('BoardGame created successfully');
                        self.successMessage = 'BoardGame created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.boardGame={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating BoardGame');
                        self.errorMessage = 'Error while creating BoardGame: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
 
        function updateBoardGame(boardGame, id){
            console.log('About to update boardGame');
            BoardGameService.updateBoardGame(boardGame, id)
                .then(
                    function (response){
                        console.log('BoardGame updated successfully');
                        self.successMessage='BoardGame updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating BoardGame');
                        self.errorMessage='Error while updating BoardGame '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
 
        function removeBoardGame(id){
            console.log('About to remove BoardGame with id '+id);
            BoardGameService.removeBoardGame(id)
                .then(
                    function(){
                        console.log('BoardGame '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing boardGame '+id +', Error :'+errResponse.data);
                    }
                );
        }
 
        function getAllBoardGames(){
            return BoardGameService.getAllBoardGames();
        }
 
        function editBoardGame(id) {
            self.successMessage='';
            self.errorMessage='';
            BoardGameService.getBoardGame(id).then(
                function (boardGame) {
                    self.boardGame = boardGame;
                },
                function (errResponse) {
                    console.error('Error while removing boardGame ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.boardGame={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);