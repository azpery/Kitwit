'use strict';

/* SidebarController */

angular.module('Rdel').
controller('PortfolioController', ['WebService','$scope', function(WebService, $scope) {
	if(refresh()){
		setTimeout(function(){ 
			initMoveAvatar(); 
			moveAvatar();
		}, 100);
	}else{
		setTimeout(function(){ 
			initMoveAvatar(); 
			moveAvatar();
		}, 3300);
	}
	WebService.get("http://localhost/lab_ws/web/app_dev.php/api/projects").$promise.then(function(data) {
		$scope.projects = data;
		setTimeout(function(){ 
			Modal.init();
		}, 1000);
		
	});

}]);