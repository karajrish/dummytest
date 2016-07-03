//Angular App Module and Controller


app.controller('MapCtrl', function ($scope,$resource,$location,$filter,Greeting) {


    var mapOptions = {
        zoom: 13,
        center: new google.maps.LatLng(42.350925, -71.06544),
        mapTypeId: google.maps.MapTypeId.TERRAIN
    }

    $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);
//
//    $scope.markers = [];
//    
    $scope.markersSet = [];
       
   
   
   function mapSearch(address){
	   var geocoder = new google.maps.Geocoder();
       var Search = $resource("/upark/search");
       var markers = Search.save(
       		{
   				address:address
   			},
   			function(){		
   				geocoder.geocode( { 'address': address}, function(results, status) {

   						 if (status == google.maps.GeocoderStatus.OK) {
   						 for (var j = 0;  j< $scope.markersSet.length; j++) {
   							 
   							 $scope.markersSet[j].setMap(null);
   							 }
   						 $scope.markersSet.length = 0;    							 
   							 
   							 var image = 'img/icon/parking.png';
   							 var destination = new google.maps.Marker({
   								 map: $scope.map,
   								 position: results[0].geometry.location,
   								 });
   							 
   							 $scope.markersSet.push(destination);
   							 
   							 var bounds = new google.maps.LatLngBounds();
	     			             bounds.extend(destination.position);
	     			             $scope.map.fitBounds(bounds);
   						 for (i = 0; i < markers.list.length; i++){
   	    					 //alert(markers.list[i].address1);
   	    					 var lattitude = markers.list[i].lattitude;
   				             var longitude = markers.list[i].longitude;
		     			            
		     			           // 	alert(lattitude +" "+longitude);
		     			                $scope.map.setCenter(results[0].geometry.location);
		     			                var marker1 = new google.maps.Marker({
		     			                    map: $scope.map,     			               
		     			                    position:new google.maps.LatLng(lattitude, longitude),
		     			                    icon: image
				     			      });
		     			                $scope.markersSet.push(marker1);

                                   
                                   //info window
                                   var contentString = '<div class="map-marker">'+
                                   '<h4 id="firstHeading" class="firstHeading">'+markers.list[i].address1+'</h4>'+
                                   '<hr />'+
                                   '<div class="bodyContent"><dl class="dl-horizontal">'+
                                   '<span class="col1">Date:</span><span class="col2">'+$filter('date')(markers.list[i].date,"yyyy-MM-dd")+'</span><br/>'+
                                   '<span class="col1">Start Time:</span><span class="col2">'+$filter('date')(markers.list[i].startTime,"H:mm")+'</span><br/>'+
                                   '<span class="col1">End Time:</span><span class="col2">'+$filter('date')(markers.list[i].endTime,"H:mm")+'</span><br/>'+
                                   '<span class="col1">Unit Price:</span><span class="col2">'+$filter('currency')(markers.list[i].unitPrice,"$")+'</span><br/>'+
                                   '<span class="col1">Email:</span><span class="col2">'+markers.list[i].username+'</span><br/>'+                              
                                   '</dl></div>'+
                                   '</div>';

                                   var infowindow = new google.maps.InfoWindow({
                                   	content: contentString
                                   });
                                   
                                   function makeInfoWindowEvent(map, infowindow, marker) {  
                                   	   return function() {  
                                   	      infowindow.open(map, marker);
                                   	   };  
                                   	}
                                   
                                   google.maps.event.addListener(marker1, 'click', 
                                   	makeInfoWindowEvent($scope.map,infowindow,marker1));
				     			    } 
   						 }else {
				     			      alert('Geocode was not successful for the following reason: ' + status);
				     			    }
   						 
		     			  });
				}
       );
   }
    
    	if(Greeting.greet.address!=""){
    		 var address = Greeting.greet.address;
    		 mapSearch(address);
    	}
    
    
    $scope.$on("mapSearch",function(event,addr) {
        mapSearch(addr);       
    });

    $scope.listView = function(){
    	$location.path('/mapListView/'+Greeting.greet.address)
    }
});