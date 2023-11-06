App.controller('landingCtrl', function ($scope,$filter, $rootScope, $http, $modal, $timeout, $window, landingService, materialService,
        documentService, myService) {

    var funcCalled = "";
    var mydata = $window.sessionStorage.getItem('USER');

    $scope.isopen = false;
    var tmpresetCompCollapse = "false";
    var tmpresetSpecDocCollapse = "false";
    $scope.curPage = 0;
    $scope.pageSize = 10;
  //  $scope.productSearch = "Style#";

    //back button change starts
    $scope.productcriteriadata = {};
    $scope.documentcriteriadata = {};
    $scope.imagecriteriadata = {};
    $scope.materialcriteriadata = {};

    var scopevalue = document.getElementById("contentlayout");
    var controllerScope = angular.element(scopevalue).scope();

    var prdscopevalue = document.getElementById("AdvancedSearch");
    if (prdscopevalue != null) {
        var prdcontrollerScope = angular.element(prdscopevalue).scope();
        if (prdcontrollerScope != null) {
            prdcontrollerScope.styleName = controllerScope.productcriteriadata.styleName;
            prdcontrollerScope.stylenumber = controllerScope.productcriteriadata.stylenumber;
            prdcontrollerScope.slot = controllerScope.productcriteriadata.slot;
            prdcontrollerScope.factorystoretype = controllerScope.productcriteriadata.factorystoretype;
            prdcontrollerScope.gender = controllerScope.productcriteriadata.gender;
            prdcontrollerScope.material = controllerScope.productcriteriadata.productmaterial;
            prdcontrollerScope.channelexclusive = controllerScope.productcriteriadata.channelexclusive;
            prdcontrollerScope.team = controllerScope.productcriteriadata.team;
            prdcontrollerScope.adaptoted = controllerScope.productcriteriadata.adaptoted;
            prdcontrollerScope.dropped = controllerScope.productcriteriadata.dropped;
            prdcontrollerScope.styleclass = controllerScope.productcriteriadata.styleclass;
            prdcontrollerScope.subclass = controllerScope.productcriteriadata.subclass;
            prdcontrollerScope.collection = controllerScope.productcriteriadata.collection;
            prdcontrollerScope.subcollection = controllerScope.productcriteriadata.subcollection;
            prdcontrollerScope.department = controllerScope.productcriteriadata.department;
            prdcontrollerScope.introdate = controllerScope.productcriteriadata.introdate;
            prdcontrollerScope.fsintrodate = controllerScope.productcriteriadata.fsintrodate;
            prdcontrollerScope.attitude = controllerScope.productcriteriadata.attitude;
            prdcontrollerScope.look = controllerScope.productcriteriadata.look;
            prdcontrollerScope.lifecyclestate = controllerScope.productcriteriadata.lifecyclestate;
            prdcontrollerScope.silhouette = controllerScope.productcriteriadata.silhouette;
            prdcontrollerScope.stylegroup = controllerScope.productcriteriadata.stylegroup;
            prdcontrollerScope.collaboration = controllerScope.productcriteriadata.collaboration;
            prdcontrollerScope.createdate = controllerScope.productcriteriadata.createdate;
            prdcontrollerScope.lastupdated = controllerScope.productcriteriadata.lastupdated;
            prdcontrollerScope.productType = controllerScope.productcriteriadata.productType;
        }
    }

    $scope.goback = function (value) {
        if (value == 'welcome') {
            controllerScope.content = "welcome";
        } else if (value == 'productcriteria') {
            controllerScope.content = "advSearch";
            if(!controllerScope.showAdvPrdSearch){
            	controllerScope.content = "welcome";
            }
        } else if (value == 'productresult') {
            controllerScope.content = "serResult";
        } else if (value == 'documentresult') {
            controllerScope.content = "documentSearchResult";
        } else if (value == 'imageresult') {
            controllerScope.content = "imagesearchresult";
        } else if (value == 'materialresult') {
            controllerScope.content = "materialSearchResult";
        }
    }
    //back button change ends
    var tmpMaterialTab = "";
    if (mydata) {
        mydata = $window.JSON.parse(mydata);
        $scope.user = mydata;
    }
    $scope.content = "";
    landingService.initload($scope, $http);

    $scope.search = function () {
        $scope.isLoading = true;
        if ($scope.productSearch == "Material Name" || $scope.productSearch == "Material CM Number") {
            var tree = "/PLMViewer/static/json/material.json";
//            $http.get(tree).success(function (data, status, headers, config) {
//               // $scope.items = data;
//            }).error(function (data, status, headers, config) {
//                alert('json error');
//            });
            //angular.element(document.getElementById('materialCtrl')).scope().advMaterialSearch($scope,$http);
            materialService.materialSearch($scope, $rootScope, $http);
        } else if ($scope.productSearch == "Style#" || $scope.productSearch == "Product Name") {
            landingService.search($scope, $http);
        } else {
//            var tree = "/PLMViewer/static/json/document.json";
//            $http.get(tree).success(function (data, status, headers, config) {
//               // $scope.items = data;
//            }).error(function (data, status, headers, config) {
//                alert('json error');
//            });
            documentService.documentSearch($scope, $http);
        }
        $scope.isLoading = false;
    };


    $scope.productsearch = function (styleId, searchby) {
        var scopevalue = document.getElementById("contentlayout");
        var controllerScope = angular.element(scopevalue).scope();
       // controllerScope.productSearch = "Style#";
        controllerScope.subSearch = "Details";
        controllerScope.searchValueToBackend = styleId;
        controllerScope.productsearchby = searchby;


        landingService.prodSearch(controllerScope, $http);
    };
    $scope.changeSkuStyle = function (prodSkuStyle, dept) {

    	if(prodSkuStyle == ''){
    		 $scope.visiblesku = false;
    		 return;
    	}
        $timeout(function () {
            $scope.visiblesku = false;
            landingService.fetchSkuData($scope, $http, $timeout, prodSkuStyle, dept);
        })

    };
    $scope.showsku = function () {
        console.log("landing controller Called Search sku selected", $scope.prodSkuStyle);
        $scope.visiblesku = true;
    };

    $scope.selectedItemsChanged = function (item, subitem, id) {

        $scope.currentTab = item.mainNode;


        var scopevalue = document.getElementById("tabs");
        var controllerScope = angular.element(scopevalue).scope();
        controllerScope.productSearch = subitem;
        if (item.mainNode == 'Pricing' || item.mainNode == 'Colors') {
            controllerScope.materialSupplier = "";
            materialService.materialColorSearch(id, $scope, $http);
        }

        controllerScope.visibleMaterialSupplier = false;
      // controllerScope.searchValue = id;
        controllerScope.subSearch = item.mainNode;
        $scope.subSearch = item.mainNode;
        controllerScope.currentTab = item.mainNode;


    };

    $scope.showAdvpage = function (item, subitem) {
       // $scope.searchValue = "";
        $scope.prodDetail = false;
        $scope.product = [];
        var treescope = document.getElementById("treeModel");
        var treecontrollerScope = angular.element(treescope).scope();
        treecontrollerScope.ProductTreeModel = false;
        treecontrollerScope.MaterialTreeModel = false;
        treecontrollerScope.DocumentTreeModel = false;
        treecontrollerScope.materiallist = [];
        if (item.mainNode == 'Advanced Search' && (subitem != '' && subitem != 'null' && (subitem.list != 'undefined' || subitem.item != ''))) {
            $scope.advSearchLoad(item, subitem, '');
        }
        if(subitem.list == 'Karat Viewer'){
        	 $window.open('http://njipwcsswebvip02/cgi-bin/Karat/KaratViewer/UI/Default.aspx', '_blank');
        }
    };
    $scope.logout = function () {
        landingService.logout($scope, $http);
    };

    $scope.getSpecComponent = function (data) {

        tmpresetCompCollapse = "true";
        $scope.specComponentCollapsed = !$scope.specComponentCollapsed;

        landingService.fetchSpecComponent($scope, $http, data);

    };
    $scope.getSpecDocument = function (data) {

        tmpresetSpecDocCollapse = "true";
        $scope.specDocCollapsed = !$scope.specDocCollapsed;

        landingService.fetchSpecDocument($scope, $http, data);

    };
    
    

    $scope.test = function (e) {

        if (tmpresetCompCollapse == "false") {

            tmpresetCompCollapse = "false";
            $scope.specComponentCollapsed = false;
        }
        if (tmpresetCompCollapse == "true") {

            tmpresetCompCollapse = "false";
            $scope.specComponentCollapsed = true;
        }

        if (tmpresetSpecDocCollapse == "false") {

            tmpresetSpecDocCollapse = "false";
            $scope.specDocCollapsed = false;
        }
        if (tmpresetSpecDocCollapse == "true") {

            tmpresetSpecDocCollapse = "false";
            $scope.specDocCollapsed = true;
        }
        if (tmpMaterialTab == 'Material') {
            if (e) {
                e.preventDefault();
                e.stopPropagation();
            }
            tmpMaterialTab = "";
            $scope.subSearch = "Material";
            $scope.testBom();

        }



    };


    $scope.advSearchLoad = function (item, subitem, searchCriteria) {
        $scope.currentTab = "";
		$scope.stylenumber ="";
        //alert($scope.searchCriteria.searchStyleName);
        landingService.advSearchInitLoad(item, subitem, $scope, $http);
    };
    $scope.advSearch = function () {
        $scope.currentTab = "";
        landingService.advSearch($scope, $http);
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
		$scope.noOfpages = Math.ceil($scope.specImageDataList.length / 10);
        $scope.ok = function () {
            // $modalInstance.close($scope.selected.item);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
        $scope.close = function () {
            $modalInstance.dismiss('cancel');
        };
    };
    $scope.testBom = function () {

        landingService.MaterialInitLoad($scope, $http);
    }
    $scope.openBOM = function (imageUrl, specName, specId, componentType,ida3MasterRef) {
        var tmp = false;
        //alert("inside open Bom::"+specName+" ::: "+specId+"   ::: "+componentType);
        if (componentType == 'BOM') {
            $scope.currentTab = "Material";
            tmpMaterialTab = "Material";
			landingService.getbomDetail(ida3MasterRef,specId, $scope, $http);
        } else {

            // landingService.loadProdImgList(specName,specId,$scope, $http,$modal)().then(function(promise) {
            $scope.toggleloading();

            myService.loadProdImgList(specName, specId, $scope, $http, $modal).then(function (d) {
                $scope.specImageDataList = d.data;
                $scope.toggleloading();
                $scope.openImagePopup($scope);

            });


        }
    };
    $scope.showData = function () {
        $scope.numberOfPages = function () {
            return Math.ceil($scope.datalists.length / $scope.pageSize);
        };
    }

    $scope.isProductTypeselected = function () {

        if ($scope.producttype) {
            if ($scope.producttype == '') {
                $scope.stylegroupenable = true;
            } else {
                $scope.stylegroupenable = false;
            }
        }
        //alert("***** " + $scope.producttype);
    }
    $scope.isdepartmentselected = function () {

        if ($scope.department == 'D01' || $scope.department == 'D02' || $scope.department == 'D03' || $scope.department == 'D04' || $scope.department == 'D05' || $scope.department == 'D06' || $scope.department == 'D08' || $scope.department == 'D10' || $scope.department == 'D14' || $scope.department == 'D23') {
            $scope.producttype = 'Accessories';
            $scope.stylegroupenable = false;
        } else if ($scope.department == 'D11' || $scope.department == 'D19') {
            $scope.producttype = 'FootWear';
            $scope.stylegroupenable = false;
        } else if ($scope.department == 'D17' || $scope.department == 'D21') {
            $scope.producttype = 'Fragrance';
            $scope.stylegroupenable = false;
        } else if ($scope.department == 'D13') {
            $scope.producttype = 'Jewelry';
            $scope.stylegroupenable = false;
        } else if ($scope.department == 'D12' || $scope.department == 'D20') {
            $scope.producttype = 'SunWear';
            $scope.stylegroupenable = false;
        } else if ($scope.department == 'D09' || $scope.department == 'D18') {
            $scope.producttype = 'Watches';
            $scope.stylegroupenable = false;
        } else if ($scope.department == 'D07' || $scope.department == 'D15' || $scope.department == 'D16' || $scope.department == 'D22') {
            $scope.producttype = 'Wearables';
            $scope.stylegroupenable = false;
        }

    }
    $scope.showselecteddate = function (targetcomp) {
//         alert(targetcomp);
        new JsDatePick({
            useMode: 2,
            target: targetcomp,
            dateFormat: "%d-%m-%Y"

        });
    }

    $scope.toggleloading = function () {
        var scopevalue = document.getElementById("loadimg");
        var controllerScope = angular.element(scopevalue).scope();
        controllerScope.showloading = !controllerScope.showloading;

    };

    $scope.materialPricingDataList = function (ida3MasterRef, $scope, $http) {
        materialService.materialPricingDataListbySupplier(ida3MasterRef, $scope, $http);
    };

    $scope.materialSupplierDetail = function (ida3MasterRef, materialTypeId) {

        if ($scope.subSearch == 'Details' && ida3MasterRef !== '') {
            materialService.materialSupplierDetail(ida3MasterRef, materialTypeId, $scope, $http);
        } else if ($scope.subSearch == 'Pricing' && ida3MasterRef !== '') {
            this.materialPricingDataList(ida3MasterRef, $scope, $http);
        } else if (ida3MasterRef == '') {
            $scope.materialPriceList = false;
            $scope.visibleMaterialSupplier = false;
        }
    };

    $scope.materialColorSearch = function () {
        materialService.materialColorSearch($scope.material.partNumber, $scope, $http);

    };



    $scope.matColorInitData = function () {
        if ($scope.materialColorLists != null || $scope.materialColorLists != "") {
            $scope.numberOfMaterialColorPages = Math.ceil($scope.materialColorLists.length / $scope.pageSize);
        }
    };

    $scope.materialColorDetails = function (matColorId) {
        materialService.materialColorDetails(matColorId, $scope, $http);
    }

    $scope.displayMaterialColorSearch = function () {
        $scope.materialColorList = true;
        $scope.materialColorDetail = false;
    }
    
    
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
			

   	$window.open('/LCSWDoc/'+folderName+'/content/'+ fileName, '_blank', ' top=100, left=300, width=600, height=400');
     };

    $scope.docImageLink = function (fileName) {
        $window.open(fileName, '_blank', ' top=100, left=300, width=600, height=400');
    };

    $scope.openDocumentDetails = function (documentName, documentTypeId) {
        documentService.documentDetail(documentName, documentTypeId, $scope, $http);
    };

    $scope.productDocumentDetails = function (prodNum, type) {
        landingService.productDocumentDetails(prodNum, type, $scope, $http);
    };

    $scope.productImageData = function (ida3MasterRef) {
        landingService.loadImageList(ida3MasterRef, $scope, $http);
    };
    
    $scope.getbomDetail = function (ida3MasterRef,bomPartId) {
        landingService.getbomDetail(ida3MasterRef,bomPartId, $scope, $http);
    };
	
	$scope.showdiv = function(){
      $scope.templateURL = '/PLMViewer/static/js/template/Calendar.html';

    };
    
  
    $scope.tableToExcel = function() {
        var uri = 'data:application/vnd.ms-excel;base64,'
          , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
          , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
          , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
        
          var table ="table2excel";
          var name ="BOM";
          if (!table.nodeType) table = document.getElementById(table);
         var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML};
         window.location.href = uri + base64(format(template, ctx)); 
		 
      };
	  



$scope.fnExcelReport=function()
    {
		$scope.mydata = $window.sessionStorage.getItem('USER');
	 var today = $filter('date')(new Date(),'yyyyMMdd-hhmm');
     var fileName = 'View_BOM_'+mydata.firstName+'_'+mydata.lastName+'_'+today+'.xls'
             var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
             var textRange; var j=0;
          tab = document.getElementById('table2excel'); // id of table


          for(j = 0 ; j < tab.rows.length ; j++) 
          {     
                tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
                //tab_text=tab_text+"</tr>";
          }

          tab_text=tab_text+"</table>";
          tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
          tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
                      tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

               var ua = window.navigator.userAgent;
              var msie = ua.indexOf("MSIE "); 

                 if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
                    {
                           txtArea1.document.open("txt/html","replace");
                           txtArea1.document.write(tab_text);
                           txtArea1.document.close();
                           txtArea1.focus(); 
                            sa=txtArea1.document.execCommand("SaveAs",true,fileName);
                          }  
                 else if(navigator.userAgent.indexOf("Firefox") > 0 || navigator.userAgent.toLowerCase().indexOf('chrome') > -1){
 					
					 var link = document.createElement("a");						
						document.body.appendChild(link);
                        link.download = fileName;
                        link.href ='data:application/vnd.ms-excel,' + encodeURIComponent(tab_text);
                        link.click();
					    document.body.removeChild(link);					

				 }
                  else   {   			  
					       sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  					

				  }
					   
					 


                      return (sa);
            };



});
