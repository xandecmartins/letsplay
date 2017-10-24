<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of BoardGames </span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>IMAGE</th>
                        <th>MIN PLAYERS</th>
                        <th>MAX PLAYERS</th>
                        <th>PLAYING TIME</th>
                        <th>EXPANSION</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="u in ctrl.getAllBoardGames()">
                        <td>{{u.id}}</td>
                        <td>{{u.name}}</td>
                        <td><img src="{{u.thumbnail}}" /></td>
                        <td>{{u.minPlayers}}</td>
                        <td>{{u.maxPlayers}}</td>
                        <td>{{u.playingTime}}</td>
                        <td>{{u.isExpansion}}</td>
                    </tr>
                    </tbody>
                </table>      
            </div>
        </div>
    </div>
</div>