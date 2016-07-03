app.controller('postSucCtrl',function($scope,$rootScope,$resource,$location,Greeting){
	
	if(Greeting.greet.isLogIn == false){
		$location.path("/");
	}
	
	$scope.postAnother = function(){
		$location.path("postInfo");
	}
	
});
