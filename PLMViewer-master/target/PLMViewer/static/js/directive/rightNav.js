App.directive('welcomeScreen',function(){
	return ({
	    restrict: 'E',
	    //require: '^content',
		replace: true,
	    scope:{
	    	content:'@'
	    },
		link:link,
		template:'<span class="text-center  "><center>{{content}}</center></span>'
	    //templateUrl: 'PLMViewer/WEB-INF/template/welcome.html',
	});
		

 function link(scope, iElement, iAttrs) {
	 scope.content =scope.$parent.content;
    }
	  });
