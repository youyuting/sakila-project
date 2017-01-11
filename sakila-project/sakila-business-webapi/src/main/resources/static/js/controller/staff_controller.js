'use strict';

App.controller('StaffController', ['$scope', '$cookieStore', 'StaffService', function($scope, $cookieStore, StaffService) {
	var self = this;
    self.auth = false;
	self.staff={username:'', password:''};
	
	self.submit = function(callBack) {
        StaffService.logStaff(self.staff).then(
    		function(data){
    			var status = data.username == null ? false : true;
    			if(status){
    				$cookieStore.put('staff_id', data.staffId);
        			$cookieStore.put('store_id',data.storeId);
        			$cookieStore.put('username',data.username);
    			}
    			//easy testable status
    			callBack(status);
    		});
    };
    
    self.redir = function(status){
    	if (status){
    		window.location.href="/Home.html";
    	}else{
    		alert("Wrong username or password.");
    	}
    };
    
    self.reset = function(){
    	self.staff={username:'', password:''};
        $scope.myForm.$setPristine(); //reset Form
    };
}]);