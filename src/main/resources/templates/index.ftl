<!DOCTYPE html>
 
<html lang="en" ng-app="letsPlayApp">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
    </head>
    <body>
 		<nav class="navbar navbar-default">
    <div class="container">
      <div class="navbar-header">
        <a class="navbar-brand" href="/">Let's Play?</a>
      </div>

      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
        <li><a href="#/groups"><i class="fa fa-shield"></i> Groups</a></li>
        <li><a href="#/players"><i class="fa fa-comment"></i> Players</a></li>
        <li><a href="#/events"><i class="fa fa-comment"></i> Events</a></li>
        <li><a href="#/boardGames"><i class="fa fa-comment"></i> Board Games</a></li>
      </ul>
    </div>
  </nav>
        <div ui-view></div>
        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/PlayerService.js"></script>
        <script src="js/app/PlayerController.js"></script>
        <script src="js/app/GroupService.js"></script>
        <script src="js/app/GroupController.js"></script>
        <script src="js/app/EventService.js"></script>
        <script src="js/app/EventController.js"></script>
        <script src="js/app/BoardGameService.js"></script>
        <script src="js/app/BoardGameController.js"></script>
    </body>
</html>