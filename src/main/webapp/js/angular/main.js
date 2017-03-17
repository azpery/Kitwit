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