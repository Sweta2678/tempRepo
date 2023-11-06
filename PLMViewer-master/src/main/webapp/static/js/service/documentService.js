App.service('documentService', function(){
     this.documentSearch = function($scope,$http)   {
	    var parameters ={};
		//  parameters.productSearch=$scope.productSearch;
		  parameters.docName=$scope.searchValue;
		    $scope.documentName = $scope.searchValue;
   	
		this.advdocumentSearch($scope,$http);
		  
	 };
	 
	 this.advdocumentSearch = function ($scope, $http) {
        var advdocSearchVo = {};

        advdocSearchVo.documentName = $scope.documentName;
		if($scope.documenType  != 'null' && ( $scope.documenType != undefined || $scope.documenType != "undefined"  ||  $scope.documenType != "")) {
        advdocSearchVo.documentType = $scope.documenType;
	     }else{
			advdocSearchVo.documentType =  0;
		}
        advdocSearchVo.createdDate = $scope.createddate;
        advdocSearchVo.updatedDate = $scope.updateddate;
        var scopevalue = document.getElementById("contentlayout");
        var controllerScope = angular.element(scopevalue).scope();
        controllerScope.documentcriteriadata = advdocSearchVo;
        $scope.toggleloading();
        var promise = $http.post('/PLMViewer/userlogin/LandingLayout/getDocumentSearchList', advdocSearchVo);
        promise.success(function (data, status, headers, config) {

            $scope.toggleloading();
            var scopevalue = document.getElementById("contentlayout");
            var controllerScope = angular.element(scopevalue).scope();
            controllerScope.datalists = data;
            controllerScope.content = "documentSearchResult";
            $scope.showAdvPrdSearch = false;
            $scope.showsearchresult = true;
        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("error in advance Document Search");
        });
    };
	
	
	this.documentDetail  = function(docName,ida3a11,$scope,$http){
		
		var parameters ={};
		  parameters.docName=docName;
		  parameters.ida3a11 = ida3a11;
		$scope.toggleloading();
		var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getDocumentDetails',  {params: parameters} );
		promise.success(function (data,status,headers,config) {
			$scope.toggleloading();
			var scopevalue = document.getElementById("contentlayout");
			var controllerScope = angular.element(scopevalue).scope();
			controllerScope.documentDetailsData = data;
			$scope.documentDetailsData = data;
			controllerScope.docDetails = true;
			controllerScope.content ="documentDetail";
			controllerScope.subSearch ="Details";
			controllerScope.documentDetail = true;
			 var treescope = document.getElementById("treeModel");
            var treecontrollerScope = angular.element(treescope).scope();
          /*  var  tree = "/PLMViewer/static/json/document.json";
           $http.get(tree).success(function(data,status, headers, config) {
          treecontrollerScope.items = data;
            }).error(function(data, status, headers, config){
             alert('Document JSON error');
       }); */
			
			treecontrollerScope.ProductTreeModel  = false;
			treecontrollerScope.DocumentTreeModel  = true;
			treecontrollerScope.MaterialTreeModel  = false;
			treecontrollerScope.materiallist = [];
			treecontrollerScope.product = [];
			
			
		}).error (function (data,status,headers,config) {
			$scope.toggleloading();
			
		});
		
	};

	 });
