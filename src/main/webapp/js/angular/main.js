/**
 * Robin Delaporte
 */
var app = angular.module('Rdel', [
  'ngRoute',
  'ngAnimate',
  'Rdel.Services',
  'ngSanitize'
]);

app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Index
    .when("/", {templateUrl: "./partials/home.html", controller: "HomeController"})
    .when("/projects", {templateUrl: "./partials/portfolio.html", controller: "PortfolioController"})
    .otherwise({
        redirectTo: '/'
    });
}]);