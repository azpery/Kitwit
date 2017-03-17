'use strict';

/* SidebarController */

angular.module('Rdel').
controller('HomeController', ['$rootScope', '$scope', '$window', '$routeParams', function($rootScope, $scope, $window,$routeParams) {
	refresh();
	// $rootScope.acteurId = $routeParams.id;
	// $scope.$parent.working = $rootScope.working;
	// $scope.$parent.map = true;
	// $rootScope.$watch("acteur", function(data, oldValue) {
	// 	if(data){
	// 		mobileHack();
	// 		$scope.acteur = data;
	// 	}
		
	// });
	// $scope.showZone = function(e){
	// 	$rootScope.showZone = e;
	// }
}]);