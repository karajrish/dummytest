app.controller('modalRegisterCtrl',function($scope,$resource,$location,Greeting){
	var User = $resource( '/upark/reg');
    $scope.registerConfirm = function(){
    	
    	if($scope.input_password != $scope.input_re_password)
    		$scope.error_message = "passwords do not match.";
    	else{
	        var user = User.save(
	                        {
	                            username:$scope.input_username,
	                            password:$scope.input_password,
	                            firstname:$scope.input_first_name,
	                            lastname:$scope.input_last_name
	                        },
	                        function()
	                            {
	                                if(user.username != "" ){
	                                   // $scope.$parent.account.text = user.username;
	                                   Greeting.greet.email = user.username;
	                                   Greeting.greet.text = user.firstname + " " + user.lastname;
	                                   Greeting.greet.isLogIn = true; 
	                                   $('#modal-register').modal('hide');
	                                }
	                                else{
	                                   $scope.error_message = "This email has been registered.";                             
	                                }
	                                
	                            }
	                        );
	
	    }
    }
    
    $scope.$watch('input_username',function(){
    	$scope.error_message = "";
    });
    $scope.$watch('input_re_password',function(){
    	$scope.error_message = "";
    });
});