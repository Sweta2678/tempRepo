App.controller('documentCtrl', function ($scope, $rootScope, $http, documentService) {

	
	//back button changes starts
	
	var scopevalue = document.getElementById("contentlayout");
    var controllerScope = angular.element(scopevalue).scope();


    
    var docscopevalue = document.getElementById("DocumentSearch");
    if (docscopevalue != null) {
        var doccontrollerScope = angular.element(docscopevalue).scope();
        if (docscopevalue != null) {
            doccontrollerScope.documentName = controllerScope.documentcriteriadata.documentName;
            doccontrollerScope.documenType = controllerScope.documentcriteriadata.documentType;
            doccontrollerScope.createddate = controllerScope.documentcriteriadata.createdDate;
            doccontrollerScope.updateddate = controllerScope.documentcriteriadata.updatedDate;
        }
    }


    $scope.goback = function (value) {
        if (value == 'welcome') {
            controllerScope.content = "welcome";
        } else if (value == 'documentcriteria') {
            controllerScope.content = "documentSearch";
            if(!controllerScope.showDocumentSearch){
            	controllerScope.content = "welcome";
            }
            
        } else if (value == 'documentresult') {
            controllerScope.content = "documentSearchResult";
        }
        // alert("reached end");
    }
    
    //back button ends
    $scope.advdocumentSearch = function () {
        documentService.advdocumentSearch($scope, $http);
    };
	
	$scope.documentSearch = function($scope,$http) {
		documentService.documentSearch($scope,$http);
	};

    $scope.documentDetail = function (docName,ida3a11) {
        $scope.documentDetail = [];
        documentService.documentDetail(docName,ida3a11, $scope, $http);
    };
    
	$scope.docFileLink = function(fileName){
		alert("hhhhhhh");
		alert(fileName);
	};

});