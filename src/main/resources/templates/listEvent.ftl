<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Specific Event </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.event.id" />
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="ugroup">Group</label>
                            <div class="col-md-7">
                            	<select name="selectgroup" ng-model="ctrl.event.group.id">
      								<option ng-repeat="group in ctrl.getAllGroups()" ng-value="{{group.id}}">{{group.name}}</option>
      								<option value="">-- Select Group --</option>
    							</select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="ugames">Games</label>
                            <div class="col-md-7">
                            	<select name="selectgroup" ng-model="ctrl.event.selectedGames" multiple>
      								<option ng-repeat="boardgame in ctrl.getAllBoardGames()" ng-value="{{boardgame.id}}">{{boardgame.name}}</option>
      								<option value="">-- Select BoardGames --</option>
    							</select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="ulocal">Location</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.event.location" id="ulocal" class="username form-control input-sm" placeholder="Enter the event location" required ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="udate">Date</label>
                            <div class="col-md-7">
								<input type="date" ng-model="ctrl.event.date" value="{{ ctrl.event.date | date: 'dd/MM/yyyy' }}" id="item_date" required/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="ugroup">Start Time</label>
                            <div class="col-md-7">
								<input type="time" id="event_time" name="input" ng-model="ctrl.event.time" placeholder="HH:mm" min="16:00" max="17:30" required />
                            </div>
                        </div>
                    </div>
                     
                    
                     <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="uobs">Observations</label>
                            <div class="col-md-7">
                                <textarea rows="10" cols="30" ng-model="ctrl.event.observations" id="uobs" class="username form-control input-sm" placeholder="Enter the observations of the event"></textarea>
                            </div>
                        </div>
                    </div>
                     
                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!ctrl.event.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>    
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Events </span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>DATE</th>
                        <th>START DATE</th>
                        <th>LOCATION</th>
                        <th>GROUP</th>
                        <th>OBSERVATIONS</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="u in ctrl.getAllEvents()">
                        <td>{{u.id}}</td>
                        <td>{{u.date}}</td>
                        <td>{{u.time}}</td>
                        <td>{{u.location}}</td>
                        <td>{{u.group.name}}</td>
                        <td>{{u.observations}}</td>
                        <td><button type="button" ng-click="ctrl.editEvent(u.id)" class="btn btn-success custom-width">Edit</button></td>
                        <td><button type="button" ng-click="ctrl.removeEvent(u.id)" class="btn btn-danger custom-width">Remove</button></td>
                    </tr>
                    </tbody>
                </table>      
            </div>
        </div>
    </div>
</div>