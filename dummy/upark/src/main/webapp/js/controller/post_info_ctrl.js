app.controller( 'postInfoCtrl',function($scope,$resource,$location,Greeting){
	if(Greeting.greet.isLogIn == false){
		$location.path("/");
	}
	else{
		$scope.username = Greeting.greet.text;
		$scope.email = Greeting.greet.email;
	}
	
	
	$scope.postInfo = function(){
		var postInfo = $resource(
				'/upark/post' 
				);
		
		var info = postInfo.save(
    			{
    				area: $scope.area,
    				address1: $scope.address1,
    				address2: $scope.address2,
    				date: $scope.date,
    				startTime: $scope.startTime,
    				endTime: $scope.endTime,
    				unitPrice: $scope.unitPrice
    			},
    			function(){
    				
    				if(info.infoId != "0"){

    					$location.path("/postSucceed");
    					
    				}
    				else{
    					
                    }
				}
		);
	}
	  $scope.open = function($event) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope.opened = true;
		  };

	  $scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	  };

	  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	  $scope.format = $scope.formats[0];
})