'use strict';

var App = angular.module('myApp', ['ngRoute', 'ui.bootstrap'])
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/PLMViewer/userlogin/LandingLayout/', {templateUrl: '/PLMViewer/userlogin/LandingLayout/'})
                        .when('/PLMViewer/userlogin/validate/', {templateUrl: '/PLMViewer/userlogin/validate/'})
                        .when('/PLMViewer/#/', {templateUrl: '/PLMViewer/userlogin/LandingLayout/'})
                        .otherwise({redirectTo: '/PLMViewer/userlogin/LandingLayout/'});
            }], ['$locationProvider', function ($locationProvider) {
                $locationProvider.html5Mode({enabled: true, requireBase: true});
            }]);

App.filter('pagination', function ()
{
    return function (input, start)
    {
        start = +start;
        if (input != null)
            return input.slice(start);
    };
});

	