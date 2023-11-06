App.controller('materialCtrl', function ($scope, $rootScope, $http,$window, $filter,materialService) {

	   $scope.mydata  = $window.sessionStorage.getItem('USER');
	//back button starts
	var scopevalue = document.getElementById("contentlayout");
    var controllerScope = angular.element(scopevalue).scope();

    var matscopevalue = document.getElementById("MaterialSearch");
    if (matscopevalue != null) {
        var matcontrollerScope = angular.element(matscopevalue).scope();
        if (matscopevalue != null) {
            matcontrollerScope.materialname = controllerScope.materialcriteriadata.materialName;
            matcontrollerScope.cmnumber = controllerScope.materialcriteriadata.partNumber;
            matcontrollerScope.materialslot = controllerScope.materialcriteriadata.materialslot;
            matcontrollerScope.materialshotname = controllerScope.materialcriteriadata.materialshotname;
            matcontrollerScope.lifecyclestate = controllerScope.materialcriteriadata.lifecyclestate;
            matcontrollerScope.brand = controllerScope.materialcriteriadata.brand;
            matcontrollerScope.activematerial = controllerScope.materialcriteriadata.activematerial;
            matcontrollerScope.cmparentgroup = controllerScope.materialcriteriadata.cmparentgroup;
            matcontrollerScope.capacitytype = controllerScope.materialcriteriadata.capacitytype;
            matcontrollerScope.materialtype = controllerScope.materialcriteriadata.materialType;
            matcontrollerScope.materialsubtype = controllerScope.materialcriteriadata.materialsubtype;
            matcontrollerScope.exoticmaterial = controllerScope.materialcriteriadata.exoticmaterial;
            matcontrollerScope.exoticcomonname = controllerScope.materialcriteriadata.exoticcomonname;
        }
    }

	


    $scope.goback = function (value) {
        if (value == 'welcome') {
            controllerScope.content = "welcome";
        } else if (value == 'materialcriteria') {
            controllerScope.content = "advMaterialSearch";
            if(!controllerScope.showmaterialSearch){
            	controllerScope.content = "welcome";
            }
        } else if (value == 'materialresult') {
            controllerScope.content = "materialSearchResult";
        }

        // alert("reached end");
    }

	//back button ends
    $scope.advMaterialSearch = function () {
        materialService.advMaterialSearch($scope, $rootScope, $http);
    };

    $scope.materialSearch = function () {
        materialService.materialSearch($scope, $rootScope, $http);
    };
    $scope.materialDetail = function (searchkey, materialTypeId, searchtype) {
        $scope.materialdetails = [];
        materialService.materialDetail(searchkey, materialTypeId, searchtype, $scope, $rootScope, $http);
    };

    $scope.materialSupplierDetail = function (ida3MasterRef, materialTypeId) {
        materialService.materialSupplierDetail(ida3MasterRef, materialTypeId, $scope, $http);
    };

    $scope.materialPricingDataList = function (ida3MasterRef, $scope, $http) {
        $scope.materialPriceLists = [];
        materialService.materialPricingDataListbySupplier(ida3MasterRef);
    };
    
    
    $scope.generateReport = function (cmnumber,selectedDepartment) {	
		if(selectedDepartment == '' || selectedDepartment == 'null' || selectedDepartment == undefined || selectedDepartment == null)
		{
			alert("Please select Department");
			return;
		}		
		var username='';
		var email='';
		 
		  if ($scope.mydata) {			
				var mydata1 = $window.JSON.parse($scope.mydata);
				$scope.user = mydata1;
				username=$scope.user.userName;
				email=$scope.user.email;
			}
			else{
				alert("User session has expired. Please login again");
				return;
			}
		var selectedlist='';
		for (var i = 0; i < selectedDepartment.length; i++) {
			if(selectedlist != ''){
							selectedlist+=',';			

			}
			selectedlist+=selectedDepartment[i];			
		}
        materialService.generateReport(cmnumber,selectedlist,username,email,$scope, $http,$filter,$window);
    };
    
    
    

});