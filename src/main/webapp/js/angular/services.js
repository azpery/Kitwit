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
factory('ActeurService', ['$resource', function($resource) {
    return {
        get : function(url){
            return $resource(url,{},{
             query: {method: "GET", isArray:false}
            }).query();
        }
    }
}]);
