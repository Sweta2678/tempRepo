App.controller('MaterialTabsCtrl', ['$scope', function ($scope) {
    $scope.materialTabs = [{
            title: 'Details',
            url: 'Details'
        },
        {
            title: 'Colors',
            url: 'Colors'
        },
        {
            title: 'Pricing',
            url: 'Pricing'
        },		{
            title: 'Document',
            url: 'Document'
    }];

    $scope.currentTab = $scope.subSearch;
	
    $scope.onClickTab = function (tab) {
          $scope.currentTab = tab.url;
          if($scope.currentTab == 'Colors'){
        	  $scope.materialColorSearch();
          }else if($scope.currentTab == 'Pricing'){
			/* var scopevalue = document.getElementById("contentlayout");
           var controllerScope = angular.element(scopevalue).scope();
            controllerScope.materialPriceList = false;*/
		  }
		  $scope.materialSupplier ="";
		  var scopevalue = angular.element(document.getElementById("contentlayout")).scope();
          scopevalue.subSearch = $scope.currentTab;
		  scopevalue.materialPriceList = false;
    }
    
    $scope.isActiveTab = function(tabUrl) {
        return tabUrl == $scope.currentTab;
    }
    
	
	
	
}]);