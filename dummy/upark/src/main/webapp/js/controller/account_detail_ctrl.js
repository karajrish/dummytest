function accountDetailCtrl($scope, $resource,$location, $routeParams, Greeting) {
	
	if(Greeting.greet.isLogIn == false){
		$location.path("/");
	}
	
	$scope.user = {username: ''};
    

    var User = $resource(
                    '/upark/checkSession'  
                );

    var user = User.save(
    			{
    				username:$routeParams.username
    			},
    			function(){
    				
    				if(user.username == ""){

    					$location.path("/");
    					
    				}
    				else{
    					$scope.user.username = user.username;
                        $scope.user.lastname = user.lastname;
                        $scope.user.firstname = user.firstname;
                    }
				}
			);
    $scope.postInfo = function(){
    	$location.path("/postInfo");
    }
    
    $scope.review = function(){
    	$location.path("/reviewInfo/"+$scope.user.username);
    }

}