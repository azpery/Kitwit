'use strict';

/* SidebarController */

angular.module('Kitwit').
controller('GameController', ['WebService','$scope', '$interval', '$rootScope','PostService', function(WebService, $scope, $interval, $rootScope, PostService) {
	$scope.timeLeft = 0; 
	var tweets;
	var answer 
	$scope.score = 0;
	var currentIndex = -1;
	$scope.endGame = false;
	var interval;
	$scope.pause = false;
	
	var endGame = function(){
		if(!$scope.endGame){
			console.log("game ended")
			
			$scope.endGame = true;
			$scope.rightAnswer = false;
			$scope.wrongAnswer = false;
			$scope.pause = true;
			$interval.cancel(interval);
			var params = {
			        username: $rootScope.name,
			        mail: $rootScope.email,
			        result: $scope.score
			      }
			PostService.post(
		    params,
		    function(data) {
		      $scope.top = data
		    },
		    function(err) {
		      console.log(err);
		      $scope.error = err.status;
		    });
		}
	};

	var play = function(){
		$scope.pause = false;
		$interval.cancel(interval);
		if(currentIndex < tweets.length-1){
			$scope.timeLeft = 0;
			currentIndex++;
			$scope.rightAnswer = false;
			$scope.wrongAnswer = false;
			$scope.currentTweet = tweets[currentIndex];
			
			answer = $scope.currentTweet.suggestions[0].answer;
			$scope.currentTweet.suggestions = shuffle($scope.currentTweet.suggestions);
			interval = $interval(
			function(){ 
				if ($scope.timeLeft == 90) {
					play();
				}else{				
					$scope.timeLeft += 10 ;
					console.log("time left : "+ $scope.timeLeft );
				}
			}, 
			1000);
		}else{
			interval = $interval(
			function(){ 
				$scope.timeLeft = 100;
				$interval.cancel(interval);
				play();
			}, 
			100);
			endGame();
		}
		
		
	};

	$scope.attempt = function(index){
		console.log($scope.pause);
		if (!$scope.pause) {
			$scope.pause = true;
			$interval.cancel(interval);
			if ($scope.currentTweet.suggestions[index].answer == answer) {
				$scope.rightAnswer = true;
				$scope.wrongAnswer = false;
				$scope.score += (100-$scope.timeLeft);
			}else{
				$scope.rightAnswer = false;
				$scope.wrongAnswer = true;
			}
			setTimeout(function(){
				play();
			}, 500)
		}
	};

	$scope.start = function(){
			$scope.timeLeft = 0;
			tweets;
			$scope.score = 0;
			currentIndex = -1;
			$scope.endGame = false;
			interval;
			WebService.get("/game").$promise.then(function(data) {
				tweets = data;
				play();
			});
			
		
	}

	$scope.start();
	
	var shuffle = function(array) {
	  var currentIndex = array.length, temporaryValue, randomIndex;

	  // While there remain elements to shuffle...
	  while (0 !== currentIndex) {

	    // Pick a remaining element...
	    randomIndex = Math.floor(Math.random() * currentIndex);
	    currentIndex -= 1;

	    // And swap it with the current element.
	    temporaryValue = array[currentIndex];
	    array[currentIndex] = array[randomIndex];
	    array[randomIndex] = temporaryValue;
	  }

	  return array;
	}

}]);

