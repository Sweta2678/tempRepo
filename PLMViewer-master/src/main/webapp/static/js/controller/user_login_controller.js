

App.controller('loginCtrl', function($scope,$http,$window,$rootScope, UserLoginService) {

	  var self = this;
      self.user={firstName:'',password:'',message:''};

     $scope.submit = function() {

		  if ($scope.myForm.$valid) {
		    	 UserLoginService.submit($scope,$http,$rootScope, $window);

		  }else{
			  return;
			  
		  }
   	
     };
  });
 