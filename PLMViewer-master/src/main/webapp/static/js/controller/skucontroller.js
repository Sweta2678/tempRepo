
App.controller('skucontroller',function($scope,$rootScope,$http,$window,landingService	) {
    console.log("skucontroller Called 1123","");
    $scope.visiblesku=!$scope.visiblesku;
	$scope.showsku = function() {
            console.log("landing controller Called Search sku selected",$scope.prodSkuStyle);
            $scope.visiblesku=true;
        //    landingService.search($scopecope,$http,$rootScope);
        };
	     
	   
	   
	   
});	