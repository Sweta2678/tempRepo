


App.directive('ngTree', function() {
    	return {
		restrict: 'E',
		transclude: true,
		controller: function($scope) {
    			$scope.showStates = function(item){
				item.active = !item.active;
                                
			};
			$scope.showpage = function(item,subitem,stylenumber){
                            console.log("Selected Stylenumber",stylenumber);
                            
                             $scope.content = "search";
				item.active = false;
                                 
			};

//$scope.items = [
//
//{
//mainNode: "Details",
//sublist: [
//{list: "General Details"},
//{list: "Identification"}
//]
//},
//{
//mainNode: "Specification",
//},
//{
//mainNode: "Documentation",
//
//}
//];
},
templateUrl: '/PLMViewer/static/js/template/treeview.html'
};
});



