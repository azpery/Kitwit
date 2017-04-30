'use strict';

/* Services */

angular.module('Kitwit.Services', ['ngResource']).
factory('WebService', ['$resource', function($resource) {
    return {
        get : function(url){
            return $resource(url,{},{
             query: {method: "GET", isArray:true}
            }).query();
        }
    }
}]).
factory('PostService', ['$resource',  function($resource){
	  var resource = $resource('./score', {}, {
	      post: { method: 'POST', isArray:true}
	  });
	   return resource;
}]);
