// angularJS navCtrl implementation for nav
app.controller('listViewCtrl', function($scope,$location,$resource,$routeParams){
	
	
	 var Search = $resource("/upark/search");
	 
     var markers = Search.save(
     		{
 				address:$routeParams.address
 			},
 			function(){
 					$scope.markers = markers.list;
 			}
 		);
     $scope.predicate = '-address1';
});
