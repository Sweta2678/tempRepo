


App.directive('ngTree', function() {
    	return {
		restrict: 'E',
		controller: function($scope) {
    			$scope.showStates = function(item){
				item.active = !item.active;
			};
			$scope.showpage = function(item,subitem,stylenumber){
                            console.log("Selected Stylenumber",stylenumber);
			};

           /* $scope.items = [
                {mainNode: "Details"}, {mainNode: "Specifications"}, {mainNode: "Documentation"}
];*/
   
},
 link: function (scope, element, attrs){
     scope.showpage = function(item,subitem,stylenumber){
		 
	//	 if(item.mainNode != 'Advanced Search' ){
        scope.selectedItemsChanged(item,subitem,stylenumber);
		//
	 };	
    	 
      },
templateUrl: '/PLMViewer/static/js/template/treeview.html'
};
});



