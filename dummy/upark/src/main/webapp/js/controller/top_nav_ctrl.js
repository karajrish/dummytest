// angularJS navCtrl implementation for nav
app.controller('topNavCtrl', function($scope,$location,$resource,Greeting){
    
    var User = $resource('/upark/checkSession');

    $scope.greeting = Greeting.greet;

    var user = User.save(function(response){
      if(user.username !=""){
        $scope.greeting.text = user.firstname + " " + user.lastname;
        $scope.greeting.email = user.username;
        Greeting.greet.isLogIn = true;
      }
    });

    
    $scope.signIn = function(){
    	if($scope.greeting.isLogIn == false)
        	$("#modal-sign-in").modal().css({'padding-top': function(){return ($(this).height()/2-120);}});
      else{
       	  $location.path("/account/"+$scope.greeting.text);     
        }
    };

    

     $scope.logOut = function(){
        
    	var User = $resource('/upark/logout');
        var user = User.save(function(response){
          if(user.username == ""){
              Greeting.greet.isLogIn = false;
              $scope.greeting.text = "Sign In";
              $location.path("/");
            }
        });
      // if($scope.greeting.isLogIn == false)
      //     $("#modal-sign-in").modal().css({'margin-top': function(){return ($(this).height()/2-120);}});
      // else{
      //     $location.path("/account/"+$scope.greeting.text);
          
      //   }
    };

});
