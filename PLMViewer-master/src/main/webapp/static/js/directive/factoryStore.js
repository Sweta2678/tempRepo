App.directive('factoryStore', function() {
    	return {
		restrict: 'E',
		controller: function($scope) {
			//alert("inside facotry store");
		},

 link: function (scope, element, attrs){
   // alert("hiii");
	 },
    	 
      
templateUrl: '/PLMViewer/static/js/template/FactoryStore.html'
};
});
