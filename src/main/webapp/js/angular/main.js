/**
 * Robin Delaporte
 */
var app = angular.module('Kitwit', [
  'ngRoute',
  'ngAnimate',
  'Kitwit.Services',
  'ngSanitize'
]);

app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Index
    .when("/", {templateUrl: "./partials/home.html", controller: "HomeController"})
    .when("/play", {templateUrl: "./partials/game.html", controller: "GameController"})
    .otherwise({
        redirectTo: '/'
    });
}]);

app.run(function($rootScope, $location) {
	function onSignIn(googleUser) {
	    var profile = googleUser.getBasicProfile();
	    console.log('ID: ' + profile.getId());
	    $rootScope.name =  profile.getName()
	    console.log('Image URL: ' + profile.getImageUrl());
	    $rootScope.email = profile.getEmail();
	    html = $(".g-signin2").html();
	    $(".g-signin2").html('<a href="#" onclick="signOut();">Sign out</a>');
	   }
	
	function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	    	$(".g-signin2").html(html);
	    });
	  }
	
	


	  window.onSignIn = onSignIn;
	  window.signOut = signOut;
	  
	  $rootScope.$on( "$routeChangeStart", function(event, next, current) {
	      if ( $rootScope.email == null ) {
	        if ( next.templateUrl == "partials/game.html" ) {
	        	alert("You must be connected")
	        	$location.path( "/" );
	        }
	      }         
	    });
	  
	  
	  
	
});