App.controller('TabsCtrl', ['$scope', function ($scope) {
    $scope.tabs = [{
            title: 'Details',
            url: 'Details'
        }, {
            title: 'Specifications',
            url: 'Specifications'
        },{
            title: 'Material',
            url: 'Material'
            	
        },{
            title: 'Images',
            url: 'Images'
        }, {
            title: 'Document',
            url: 'Document'
    }];

    $scope.currentTab = $scope.subSearch;
	if($scope.currentTab == 'Specifications')
           $scope.prodSpec=$scope.prop.productSpecification;

    $scope.onClickTab = function (tab) {
    	$scope.prodSpec=$scope.prop.productSpecification;
        $scope.currentTab = tab.url;
		$scope.visibleSku = false;
		$scope.productSkuStyle ="";
		if($scope.currentTab == 'Document'){
    		if($scope.prop.styleNumber != 'null' && $scope.prop.styleNumber != 'undefined' 
    			  && $scope.prop.styleNumber != '')
    		$scope.productDocumentDetails($scope.prop.styleNumber,"stylenumber");
    	} else if ($scope.currentTab == 'Images'){
		//	alert($scope.prop.ida3masterreference);
			$scope.productImageData($scope.prop.ida3masterreference);
		} else if($scope.currentTab == 'Material'){
			$scope.getbomDetail($scope.prop.ida3masterreference,'');
		}
    	else{
    		$scope.productDocumentDetails($scope.prop.slotNumber,"slotnumber");
    	}
    }
    
    $scope.isActiveTab = function(tabUrl) {
        return tabUrl == $scope.currentTab;
    }
    
	$scope.changeProdSpecification = function(productSpecification){
		$scope.prodSpec=[];
		if(productSpecification == ''){
			$scope.prodSpec[0]=$scope.prop.productSpecification;
		}else{
			$scope.prodSpec[0]=productSpecification;
		}
	}
	
	
}]);