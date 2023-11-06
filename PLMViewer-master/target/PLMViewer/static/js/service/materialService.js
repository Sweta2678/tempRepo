App.service('materialService', function () {
    this.materialSearch = function ($scope, $rootScope, $http) {
        // $scope.list =[];
        var parameters = {};
        parameters.productSearch = $scope.productSearch;
        parameters.subSearch = $scope.subSearch;
        parameters.searchValue = $scope.searchValue;
        if ($scope.productSearch == "Material CM Number") {
            $scope.cmnumber = $scope.searchValue;
            $scope.materialname = "";
        } else if ($scope.productSearch == "Material Name") {
            $scope.cmnumber = "";
            $scope.materialname = $scope.searchValue;
        }
        this.advMaterialSearch($scope, $rootScope, $http);

    };

    this.materialSupplierDetail = function (ida3MasterRef, materialTypeId, $scope, $http) {
        var parameter = {};
        parameter.ida3MasterRef = ida3MasterRef;
        parameter.materialTypeId = materialTypeId;
        $scope.toggleloading();
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getMaterialSupplier', {params: parameter});
        promise.success(function (data, status, headers, config) {
            $scope.toggleloading();
            if (data.exceptionmessage) {
                $scope.errordata = data;
                $scope.content = "error";
                return;
            }
            var scopevalue = document.getElementById("contentlayout");
            var controllerScope = angular.element(scopevalue).scope();
            controllerScope.content = "materialSearch";
           // controllerScope.productSearch = "Material";
            controllerScope.subSearch = "Details";
            controllerScope.matSupplierVo = data;
            controllerScope.visibleMaterialSupplier = true;
        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("error while fetching material Supplier data");
        });
    };

    this.materialDetail = function (partNumber, materialTypeId, searchtype, $scope, $rootScope, $http) {
        //alert("Material detail");
        var parameter = {};
        parameter.partNumber = partNumber;
        parameter.materialTypeId = materialTypeId;
        parameter.materialsearchtype = searchtype;

        $scope.toggleloading();
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getMaterialData', {params: parameter});
        promise.success(function (data, status, headers, config) {
            $scope.toggleloading();
            if (data.exceptionmessage) {
                $scope.errordata = data;
                $scope.content = "error";
                return;
            }
            var scopevalue = document.getElementById("contentlayout");
            var controllerScope = angular.element(scopevalue).scope();
            controllerScope.content = "materialSearch";
            //controllerScope.productSearch = "Material CM Number";
            controllerScope.subSearch = "Details";
            controllerScope.material = data;
            controllerScope.materialDetail = true;

            var treescope = document.getElementById("treeModel");
            var treecontrollerScope = angular.element(treescope).scope();
//            var tree = "/PLMViewer/static/json/material.json";
//            $http.get(tree).success(function (data, status, headers, config) {
//                treecontrollerScope.items = data;
//            }).error(function (data, status, headers, config) {
//                alert('json error');
//            });
            treecontrollerScope.materiallist = data;
            treecontrollerScope.ProductTreeModel = false;
            treecontrollerScope.DocumentTreeModel = false;
            treecontrollerScope.MaterialTreeModel = true;
            treecontrollerScope.product = [];


        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("error in advSearch");
        });
    };

    this.advMaterialSearch = function ($scope, $rootScope, $http) {
        var materiaSearchVo = {};

        materiaSearchVo.materialName = $scope.materialname;
        materiaSearchVo.partNumber = $scope.cmnumber;
        materiaSearchVo.materialslot = $scope.materialslot;
        materiaSearchVo.materialshotname = $scope.materialshotname;
        materiaSearchVo.lifecyclestate = $scope.lifecyclestate;
        materiaSearchVo.brand = $scope.brand;
        materiaSearchVo.activematerial = $scope.activematerial;
        materiaSearchVo.cmparentgroup = $scope.cmparentgroup;
        materiaSearchVo.capacitytype = $scope.capacitytype;
        materiaSearchVo.materialType = $scope.materialtype;
        materiaSearchVo.materialsubtype = $scope.materialsubtype;
        materiaSearchVo.exoticmaterial = $scope.exoticmaterial;
        materiaSearchVo.exoticcomonname = $scope.exoticcomonname;
        var scopevalue = document.getElementById("contentlayout");
        var controllerScope = angular.element(scopevalue).scope();
        controllerScope.materialcriteriadata = materiaSearchVo;
        $scope.toggleloading();
        var promise = $http.post('/PLMViewer/userlogin/LandingLayout/getMaterialSearchList', materiaSearchVo);
        promise.success(function (data, status, headers, config) {
            $scope.toggleloading();
            if (data.exceptionmessage) {
                    $scope.errordata = data;
                    $scope.content = "error";
                    return;
                }
            var treescope = document.getElementById("treeModel");
            var treecontrollerScope = angular.element(treescope).scope();
            treecontrollerScope.ProductTreeModel = false;
            treecontrollerScope.MaterialTreeModel = false;
            treecontrollerScope.DocumentTreeModel = false;
            $scope.datalists = data;
            var scopevalue = document.getElementById("contentlayout");
            var controllerScope = angular.element(scopevalue).scope();
            controllerScope.content = "materialSearchResult";
            controllerScope.datalists = data;
            //      $scope.content = "materialSearchResult";
            $scope.showmaterialSearch = false;
            //   $scope.showmaterialsearchresult = true;

        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("error in advSearch");
        });
    };


    this.materialColorSearch = function (partNumber, $scope, $http) {
        var parameter = {};
        parameter.partNumber = partNumber;
        $scope.toggleloading();
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getMaterialColorSearchResult', {params: parameter});
        promise.success(function (data, status, headers, config) {
            if (data.exceptionmessage) {
                $scope.errordata = data;
                $scope.content = "error";
                return;
            }
            var scopevalue = document.getElementById("contentlayout");
            var controllerScope = angular.element(scopevalue).scope();
            controllerScope.content = "materialSearch";
           // controllerScope.productSearch = "Style#";
            controllerScope.subSearch = "Colors";
            controllerScope.materialColorLists = data;
            controllerScope.materialColorList = true;
            if (controllerScope.materialColorLists != null && (controllerScope.materialColorLists != "" ||
                    controllerScope.materialColorLists != "undefined")) {
                controllerScope.numberOfMaterialColorPages = Math.ceil(controllerScope.materialColorLists.length / controllerScope.pageSize);
            }
            $scope.toggleloading();
        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error in Material Color Search");
        });
    };

    this.materialColorDetails = function (matColorId, $scope, $http) {
        var parameter = {};
        parameter.colorId = matColorId;

        $scope.toggleloading();
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getMaterialColorDetail', {params: parameter});
        promise.success(function (data, status, headers, config) {
            if (data.exceptionmessage) {
                $scope.errordata = data;
                $scope.content = "error";
                return;
            }
            var scopevalue = document.getElementById("contentlayout");
            var controllerScope = angular.element(scopevalue).scope();
            controllerScope.materialColorList = false;
            controllerScope.materialColorDetail = true;
            controllerScope.matColorDetails = data;
            $scope.toggleloading();
        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error in Material Color Detail");
        });


    };

    this.materialPricingDataListbySupplier = function (ida3MasterRef, $scope, $http) {
        var parameter = {};
        parameter.ida3MasterRef = ida3MasterRef;
        $scope.toggleloading();
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getMaterialPriceSearchResult', {params: parameter});
        promise.success(function (data, status, headers, config) {
            if (data.exceptionmessage) {
                $scope.errordata = data;
                $scope.content = "error";
                return;
            }
            var scopevalue = document.getElementById("contentlayout");
            var controllerScope = angular.element(scopevalue).scope();
            controllerScope.content = "materialSearch";
         //   controllerScope.productSearch = "Material";
            controllerScope.subSearch = "Pricing";
            controllerScope.materialPriceLists = data;
            controllerScope.materialPriceList = true;
            //controllerScope.materialPricing = true;
            if (controllerScope.materialPriceLists != null && (controllerScope.materialPriceLists != "" ||
                    controllerScope.materialPriceLists != "undefined")) {
                controllerScope.numberOfMaterialPricePages = Math.ceil(controllerScope.materialPriceLists.length / controllerScope.pageSize);
            }

            $scope.toggleloading();
        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error in Material Price Search Result");
        });
    }

    
    this.generateReport = function (cmnumber,selectedDepartment,username,email,$scope, $http,$filter,$window) {

	 
    	 var parameter = {};
           parameter.cmnumber = cmnumber;
           parameter.Department = selectedDepartment;
		     parameter.username = username;
		     parameter.email = email;

         $scope.toggleloading();
         var promise = $http.get('/PLMViewer/userlogin/LandingLayout/gererateMaterialReport', {params: parameter});
         promise.success(function (data, status, headers, config) {
             if (data.exceptionmessage) {
                 $scope.errordata = data;
                 $scope.content = "error";
                 return;
             }             
			JSONToCSVConvertor(data,"Material Report", true,$scope,$filter,$window);
             var scopevalue = document.getElementById("contentlayout");
             var controllerScope = angular.element(scopevalue).scope();
             controllerScope.content = "materialReport"; 
             $scope.toggleloading();
         }).error(function (data, status, headers, config) {
             $scope.toggleloading();
             alert("There was a error in generating report. The report will be mailed to you");
         });
     };
		function JSONToCSVConvertor(JSONData, ReportTitle, ShowLabel,$scope,$filter,$window) {
			var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
			
			var tab_data = ''; 
			tab_data += '\r\n\n';
			 tab_data+="<table border='2px'><tr bgcolor='#87AFC6'>";
		
			if (ShowLabel) {
				var row = "";
					row +='<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">CM Number</td>';						
					row += '<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">Color</td>';	
					row += '<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">Material (Desc),</td>';	
					row += '<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">Supplier</td>';	
					row +='<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">Style Number</td>';	
					row += '<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">Slot Number</td>';	
					row += '<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">Style Name</td>';
					row +='<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">Product Life Cycle State</td>';	
					row += '<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">SKU Name</td>';	
					row += '<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">SKU Status</td>';
					row += '<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">SKU Intro</td>';
					row +='<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">Department</td>';
					row += '<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">Product Intros</td>';
					row +='<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">SKU FS Intro</td>';
			//		row += '<td class="tableHeader" ng-repeat="column in model.columnOrder" width = "15%">Product FSIntro</td>';

			
				//row = row.slice(0, -1);
				tab_data += row+'</tr> ';
			}
			for (var i = 0; i < arrData.length; i++) {
				var row = "";
				tab_data += '<tr>';		
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].cmNumber +'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].color+'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].materialDesc +'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].supplier +'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].styleNumber +'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].slotNumber +'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].styleName +'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].productLifecycleState +'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].skuName +'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].skuStatus +'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].skuIntro +'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].department +'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].productIntros +'</td>';
				  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i].skuFsIntro +'</td>';
				//  row +="<td  class='tableData' ng-repeat='lstvalue  in value'  width = '15%'>"+ arrData[i][productFSIntro] +'</td>';
				  
				//row.slice(0, row.length - 1);
				tab_data += row+'</tr>' ;
			}

			if (tab_data == '') {        
				alert("Invalid data");
				return;
			}   
			 tab_data=tab_data+"</table>";
			//$scope.mydata = $window.sessionStorage.getItem('USER');
			var today = $filter('date')(new Date(),'yyyyMMdd-hhmm');
			var fileName = 'WhereUsedReport'+'_'+today+'.xls'
			  var ua = window.navigator.userAgent;
              var msie = ua.indexOf("MSIE "); 

                 if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
                    {
                           txtArea1.document.open("txt/html","replace");
                           txtArea1.document.write(tab_data);
                           txtArea1.document.close();
                           txtArea1.focus(); 
                            sa=txtArea1.document.execCommand("SaveAs",true,fileName);
							                      return (sa);

                          }  
                 else if(navigator.userAgent.indexOf("Firefox") > 0 || navigator.userAgent.toLowerCase().indexOf('chrome') > -1){
 					
				//	 var link = document.createElement("a");						
				//		document.body.appendChild(link);
                   //     link.download = fileName;
                   //     link.href ='data:application/vnd.ms-excel,' + encodeURIComponent(tab_data);
                   //     link.click();
					//    document.body.removeChild(link);	

					var uri = 'data:text/csv;charset=utf-8,' + escape(tab_data);
						var link = document.createElement("a");    
						link.href = uri;
						link.style = "visibility:hidden";
						link.download = fileName ;
						document.body.appendChild(link);
						link.click();
						document.body.removeChild(link);							

				 }
                  else   {   			  
					       sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_data));  	
						  return (sa);
						   

				  }
		};

    
    this.setTreeModel = function () {
        var treescope = document.getElementById("treeModel");
        var treecontrollerScope = angular.element(treescope).scope();
        treecontrollerScope.ProductTreeModel = false;
        treecontrollerScope.MaterialTreeModel = false;
        treecontrollerScope.DocumentTreeModel = false;
    };
});