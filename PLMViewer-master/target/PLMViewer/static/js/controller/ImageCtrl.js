App.controller('imageCtrl', function ($scope, $rootScope, $http, $modal,$window, imageservice,myService) {
     
	var scopevalue = document.getElementById("contentlayout");
    var controllerScope = angular.element(scopevalue).scope();

    var imgscopevalue = document.getElementById("MaterialSearch");
    if (imgscopevalue != null) {
        var imgcontrollerScope = angular.element(imgscopevalue).scope();
        if (imgcontrollerScope != null) {
          
            imgcontrollerScope.stylename = controllerScope.imagecriteriadata.stylename;
            imgcontrollerScope.stylenumber = controllerScope.imagecriteriadata.stylenumber;
            imgcontrollerScope.department = controllerScope.imagecriteriadata.department;
            imgcontrollerScope.styleclass = controllerScope.imagecriteriadata.styleclass;
            imgcontrollerScope.subclass = controllerScope.imagecriteriadata.subclass;
            imgcontrollerScope.collection = controllerScope.imagecriteriadata.collection;
            imgcontrollerScope.subcollection = controllerScope.imagecriteriadata.subcollection;
            imgcontrollerScope.createdfrom = controllerScope.imagecriteriadata.createdfrom;
            imgcontrollerScope.createdto = controllerScope.imagecriteriadata.createdto;
            imgcontrollerScope.updatedfrom = controllerScope.imagecriteriadata.updatedfrom;
            imgcontrollerScope.updatedto = controllerScope.imagecriteriadata.updatedto;
            imgcontrollerScope.pagename = controllerScope.imagecriteriadata.pagename;
            imgcontrollerScope.pagetype = controllerScope.imagecriteriadata.pagetype;
            imgcontrollerScope.introdate = controllerScope.imagecriteriadata.introdate;
            imgcontrollerScope.fsintrodate = controllerScope.imagecriteriadata.fsintrodate;



        }
    }
    $scope.goback = function (value) {
        if (value == 'welcome') {
            controllerScope.content = "welcome";
        } else if (value == 'imagecriteria') {
            controllerScope.content = "imagesearch";
        } else if (value == 'imageresult') {
            controllerScope.content = "imagesearchresult";
        }
    }

    
    $scope.advImageSearch = function () {
            imageservice.imageSearch($scope, $rootScope, $http);
    };
    
    $scope.docFileLink = function (fileName,fileType) {  	
        console.log("Filetype is "+fileType); 		
     	  var folderName;	 
     	    	if( fileType.indexOf("Packaging & Packing Details") > -1){
	  		folderName="PackagingAndPackingDetails";
	  		}
	    	else	if( fileType.indexOf("Other") > -1){
	  		folderName="Other";
	  		}
	    	else if( fileType.indexOf("Business Process Solutions") > -1){
	  		folderName="Training";
	  		}
			else if( fileType.indexOf("Job Aids & Shortcuts") > -1){
	  		folderName="Training";
	  		}	
			else if( fileType.indexOf("Wearables TD") > -1){
	  		folderName="WearablesTD";
	  		}
	    	else if( fileType.indexOf("Adv Dev Tracking") > -1){
	  		folderName="AdvDevTrn";
	  		}
	    	else if( fileType.indexOf("Line Presentation") > -1){
	  		folderName="LinePresentation";
	  		}
	    	else if( fileType.indexOf("Eng. Construction Details") > -1){
	  		folderName="EngConstructionDetails";
	  		}	  
	    	else if( fileType.indexOf("Trade Compliance") > -1){
	  		folderName="TradeCompliance";
	  		}
	    	else if( fileType.indexOf("TOL Approval Comments") > -1){
	  		folderName="TOLApprovalComments";
	  		}
	    	else if( fileType.indexOf("Prelim Product Comments") > -1){
	  		folderName="PrelimProdComnts";
	  		}	  
	    	else if( fileType.indexOf("Fabric Placement Documents") > -1){
	  		folderName="FabricPlacementDocuments";
	  		}
	    	else if( fileType.indexOf("Material Test Results") > -1){
	  		folderName="MaterialTestResults";
	  		}
	    	else if( fileType.indexOf("Product Safety Compliance") > -1){
	  		folderName="ProductSafetyCompliance";
	  		}
	    	else if( fileType.indexOf("Development") > -1){
	  		folderName="PatternDevelopment";
	  		}
	    	else if( fileType.indexOf("Product Revision Notice") > -1){
	  		folderName="ProductRevisionNotice";
	  		}
	    	else if( fileType.indexOf("Product Approval Comments") > -1){
	  		folderName="ProductApprovalComments";
	  		}
	  		else if( fileType.indexOf("Confirmation Approval Comments") > -1){
	  		folderName="ConfirmationApprovalComments";
	  		}
	  		else if( fileType.indexOf("Engineer Approval Comments") > -1){
	  		folderName="EngineerApprovalComments";
	  		}
			else if( fileType.indexOf("Design Documents") > -1){
	  		folderName="DesignDocuments";
	  		}
	  		else if( fileType.indexOf("Document") > -1){
	  		folderName="Documents";
	  		}
			else if( fileType.indexOf("Wearables Construction Details") > -1){
	  		folderName="Wearables";
	  		}
			else if( fileType.indexOf("Training") > -1){
	  		folderName="Training";
	  		}


     	  $window.open('/LCSWDoc/'+folderName+'//content/'+ fileName, '_blank', ' top=100, left=300, width=600, height=400');
       };
       
    $scope.docImageLink = function (fileName) {
        $window.open(fileName, '_blank', ' top=100, left=300, width=600, height=400');
    };
    
    
    $scope.loadDocImgList = function(docMasterId){

        myService.loadProdImgList("", docMasterId, $scope, $http, $modal).then(function (d) {
            $scope.specImageDataList = d.data;
            $scope.openImagePopup($scope);
        });
    };
    
    $scope.openImagePopup = function ($scope) {
        specImageDataList = $scope.specImageDataList;
        var modalInstance = $modal.open({
            templateUrl: '/PLMViewer/static/js/template/popup.html',
            controller: ModalInstanceCtrl,
            resolve: {
                params: function () {
                    return specImageDataList;
                }
            }
        });

        modalInstance.result.then(function () {
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });

    };
    var ModalInstanceCtrl = function ($scope, $modalInstance, params) {
		
        $scope.specImageDataList = params;
		$scope.noOfpages = Math.ceil($scope.specImageDataList.length / 2);
        $scope.ok = function () {      };
        $scope.cancel = function () {   $modalInstance.dismiss('cancel');        };
        $scope.close = function () {    $modalInstance.dismiss('cancel');        };
    };
});