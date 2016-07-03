// the angularJS module intetface for all controllers
var app = angular.module('UPark', ['ngRoute','ngResource','ui.bootstrap']);
// routing manage
app.config(['$routeProvider', function($routeProvider) {
$routeProvider.
      when('/', {templateUrl: 'view/page_home.html'}).
      when('/account/:username',{templateUrl: 'view/page_account_view.html',controller: accountDetailCtrl}).
      when('/postInfo', {templateUrl: 'view/page_post_info.html'}).
      when('/postSucceed', {templateUrl: 'view/page_post_succeed.html'}).
      when('/mapListView/:address', {templateUrl: 'view/page_map_list.html'}).
      when('/reviewInfo/:username', {templateUrl: 'view/page_review_info.html'}).
      otherwise({redirectTo: '/'});
}]);
//Greeting service
app.factory("Greeting", function() {

  	return {
  			greet: {
  				text:'Sign In',
  				email:"",
  				isLogIn: false,
  				address:""
  			}
  		};
});

