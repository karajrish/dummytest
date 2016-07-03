app.controller('headerCtrl',function($scope,$rootScope,Greeting,$location){
    $scope.map_address = Greeting.greet.address;
    $scope.mapSearch = function(){
    	if($scope.map_address != ""){
	        $rootScope.$broadcast("mapSearch",$scope.map_address);        
	        Greeting.greet.address = $scope.map_address; 
	        $location.path("/");
    	}
    }

});
