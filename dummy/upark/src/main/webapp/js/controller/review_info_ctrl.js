app.controller('reviewInfoCtrl',function($scope,$location,$resource,Greeting,$routeParams){
	if(Greeting.greet.isLogIn == false){
		$location.path("/");
	}    
	
	
    var Review = $resource(
                    '/upark/review'  
                );

    var info = Review.save(
    			{
    				username:$routeParams.username
    			},
    			function(){
    				if( info.list.length != 0)
     					$scope.markers =  info.list;
				}
			);
    
    $scope.deleteInfo = function(id){
    	var delInfo = $resource(
                '/upark/delete',{},{
                	remove:{
                		method: "DELETE",
                		headers: {'Content-Type': 'application/json'}
                	}
                	
                }
                
                
            );

		var status = delInfo.remove(
					{
						infoId:id
					},
					function(){
					}
		);
    }
    
});
