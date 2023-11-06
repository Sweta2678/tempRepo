App.service('landingService', function ($http, $q) {
    this.productSearchOption = {};
    this.subSearchOption = {};
    var deffered = $q.defer();

    this.initload = function ($scope, $http, $rootScope, $window) {
        $scope.visiblesku = false;
        $http.get('/PLMViewer/userlogin/LandingLayout/intiLoad').
                success(function (data, status, headers, config) {
                    $scope.content = "welcome";
                    $scope.prodDetail = false;
                    $scope.productSearchOption = data[0];
                    $scope.productSearch = $scope.productSearchOption[0];
                    $scope.subSearchList = false;
                    $scope.advSrcItems = [];
                    $scope.searchCriteria = [];
                    $http.get("/PLMViewer/static/json/advSearch.json").success(function (data, status, headers, config) {
                        $scope.advSrcItems = data;
                    }).error(function (data, status, headers, config) {
                        alert("init load error");
                    });
                }).
                error(function (data, status, headers, config) {
                    alert("Error while Loading");
                });
    };
    this.search = function ($scope, $http) {

        var parameters = {};
        parameters.productSearch = $scope.productSearch;
        //  parameters.subSearch=$scope.subSearch;
        parameters.searchValue = $scope.searchValue;

        var tree = "";
        $scope.speccollapsed = false;
        var treescope = document.getElementById("treeModel");
        var treecontrollerScope = angular.element(treescope).scope();
        treecontrollerScope.ProductTreeModel = false;
        treecontrollerScope.MaterialTreeModel = false;
        treecontrollerScope.DocumentTreeModel = false;
        if ($scope.productSearch == "Product Name" || $scope.productSearch == "Style#") {
           // tree = "/PLMViewer/static/json/product.json";
            if ($scope.productSearch == "Style#") {
                $scope.stylenumber = $scope.searchValue;
                $scope.styleName = "";
            } else if ($scope.productSearch == "Product Name") {
                $scope.styleName = $scope.searchValue;
                $scope.stylenumber = "";
            }
            $scope.content = "advSearch";
            $scope.showAdvPrdSearch = false;
            $scope.showsearchresult = true;
            this.advSearch($scope, $http);

        } else if ($scope.productSearch == "Material" || $scope.productSearch == "Material CM Number") {
//            tree = "/PLMViewer/static/json/material.json";
            $scope.content = "advMaterialSearch";
            $scope.prodDetail = false;
            $scope.materialDetail = true;
            this.advMaterialSearch($scope, $http);
        }

//        $http.get(tree).success(function (data, status, headers, config) {
//            $scope.items = data;
//        }).error(function (data, status, headers, config) {
//            alert('json error');
//        });




    };

    this.setSelectedItem = function (subNode, $scope, $http) {

        $scope.toggleloading();

        $scope.visiblesku = false;
        $scope.prodDetail = true;
        $scope.content = "productSearch";
        $scope.subSearch = subNode;
        this.prodSearch($scope, $http);
        $scope.toggleloading();
    };

    this.prodSearch = function ($scope, $http) {
        $scope.list = [];
        var parameters = {};
        parameters.productSearch = $scope.productSearch;
        parameters.searchValue = $scope.searchValueToBackend;
        parameters.productsearchby = $scope.productsearchby;
        $scope.stylenumber = $scope.searchValue;
        $scope.toggleloading();
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getProductList', {params: parameters});
        promise.success(function (data, status, headers, config) {
            $scope.toggleloading();
            if (data.exceptionmessage) {
                $scope.errordata = data;
                $scope.content = "error";
                return;
            }
            $scope.list.push(data);
            $scope.visiblesku = false;
            $scope.prodDetail = true;
            $scope.content = "productSearch";
         //   $scope.productSearch = "Style#";
            $scope.product = data;
            //alert(JSON.stringify(data));
            var treescope = document.getElementById("treeModel");
            var treecontrollerScope = angular.element(treescope).scope();
            treecontrollerScope.ProductTreeModel = true;
            treecontrollerScope.MaterialTreeModel = false;
        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("error");
        });
    };

    this.fetchSubSearchData = function ($scope, $http) {

        var parameters = {};
        parameters.productSearch = $scope.productSearch;
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/subSearchLoad', {params: parameters});
        promise.success(function (data, status, headers, config) {
            $scope.prodDetail = true;
            $scope.subSearchOption = data[0];
            $scope.subSearchList = true;
        }).error(function (data, status, headers, config) {
            alert("error");
        });

    };
    this.fetchSkuData = function ($scope, $http, $timeout, prodSkuStyle, dept) {
        $scope.toggleloading();
        //alert("prodSkuStyle :: " + prodSkuStyle);
        if (prodSkuStyle == '') {
            $scope.visiblesku = false;
            return;
        }
        var parameters = {};
        parameters.styleNumber = $scope.searchValue;
        parameters.skuStyleName = prodSkuStyle;
        parameters.dept = dept;
        parameters.productsearchby = "stylenumber";

        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getProductSkuList', {params: parameters});
        promise.success(function (data, status, headers, config) {

            $scope.sku = data;
            //alert(JSON.stringify(data));
           // $scope.productSearch = "Style#";
            var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getProductSkuAdditionalList', {params: parameters});
            promise.success(function (addidata, status, headers, config) {
                // alert("before additional details");
                $scope.skuAdditionalDetails = addidata;
                // alert(JSON.stringify($scope.skuAdditionalDetails));
                $scope.content = "productSearch";
                $scope.prodDetail = true;
                $scope.visiblesku = true;
                $scope.toggleloading();
            }).error(function (data, status, headers, config) {
                alert("Error While fetching SKU additional detail error");

            });
        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error While fetching SKU Data");
        });


//        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getProductSkuAdditionalList', {params: parameters});
//        promise.success(function (data, status, headers, config) {
//            $scope.skuAdditionalDetails = data;
//            $scope.content = "productSearch";
//            $scope.prodDetail = true;
//            $scope.visiblesku = true;
//        }).error(function (data, status, headers, config) {
//            alert("Error While fetching SKU additional detail error");
//
//        });


    };

    this.fetchSpecComponent = function ($scope, $http, prodSpecComponent) {
        $scope.toggleloading();
        var parameters = {};
        parameters.specComponent = prodSpecComponent;
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getSpecComponentList', {params: parameters});
        promise.success(function (data, status, headers, config) {
            $scope.toggleloading();
            $scope.content = "productSearch";

            $scope.subSearch = "Specifications";
            $scope.currentTab = "Specifications";
            $scope.specComponentList = data;
        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error Occured while fetching SpecComponent in LandingService");

        });
    };

    this.fetchSpecDocument = function ($scope, $http, prodSpecComponent) {
        $scope.toggleloading();
        var parameters = {};
        parameters.specComponent = prodSpecComponent;
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getSpecDocList', {params: parameters});
        promise.success(function (data, status, headers, config) {
            $scope.toggleloading();
            $scope.content = "productSearch";
            $scope.subSearch = "Specifications";
            $scope.currentTab = "Specifications";
            $scope.specDocList = data;
        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error Occured while fetching SpecDocument in LandingService");
        });
    };

    this.logout = function ($scope, $http) {
        $scope = $scope.$new(true);
        sessionStorage.clear();
        $http.get('/PLMViewer/logout/').
                success(function (data, status, headers, config) {
                    //alert("Successfully Logout");
                }).error(function (data, status, headers, config) {
            alert("error on logout");
        });
    };

    /*this.advSearchInitLoad = function($scope,$http){
     $http.get('/PLMViewer/userlogin/searchLayout/initLoad').
     success(function(data, status, headers, config) {
     $scope.content= data;
     
     //$scope.searchCriteria.searchStyleName="";
     }).error(function(data, status, headers, config) {
     alert("error in advSearchInitLoad");
     });
     };*/

    this.MaterialInitLoad = function ($scope, $http) {
        $scope.toggleloading();
        $scope.list = [];
        var parameters = {};
        parameters.productSearch = $scope.productSearch;
        parameters.subSearch = $scope.subSearch;
        parameters.searchValue = $scope.searchValue;
        parameters.productsearchby = "stylenumber";
        var tree = "";
        $scope.speccollapsed = false;
//        if ($scope.productSearch == "Product" || $scope.productSearch == "Style#") {
//            tree = "/PLMViewer/static/json/product.json";
//            $scope.content = "productSearch";
//        }

//        $http.get(tree).success(function (data, status, headers, config) {
//            $scope.items = data;
//        }).error(function (data, status, headers, config) {
//            alert('json error');
//        });

        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getProductList', {params: parameters});
        promise.success(function (data, status, headers, config) {
            if (data.exceptionmessage) {
                $scope.errordata = data;
                $scope.content = "error";
                return;
            }
            $scope.list.push(data);
            $scope.prodDetail = true;
            $scope.product = data;
            var treescope = document.getElementById("treeModel");
            var treecontrollerScope = angular.element(treescope).scope();
            treecontrollerScope.ProductTreeModel = false;
            treecontrollerScope.MaterialTreeModel = false;
            $scope.toggleloading();

        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error while getting Product List");
        });
    };

    this.advSearch = function ($scope, $http) {
        // alert("started ");
        var advSearchVo = {};
        $scope.sizecalevisible = false;
        if ($scope.department) {
            $scope.sizecalevisible = true;
        } else if ($scope.producttype) {
            $scope.sizecalevisible = true;
        } else {
            $scope.sizecalevisible = false;
        }
        advSearchVo.styleName = $scope.styleName;
        advSearchVo.stylenumber = $scope.stylenumber;
        advSearchVo.slot = $scope.slot;
        advSearchVo.factorystoretype = $scope.factorystoretype;
        advSearchVo.gender = $scope.gender;
        advSearchVo.material = $scope.productmaterial;
        advSearchVo.channelexclusive = $scope.channelexclusive;
        advSearchVo.team = $scope.team;
        advSearchVo.adaptoted = $scope.adaptoted;
        advSearchVo.dropped = $scope.dropped;
        advSearchVo.styleclass = $scope.styleclass;
        advSearchVo.subclass = $scope.subclass;
        advSearchVo.collection = $scope.collection;
        advSearchVo.subcollection = $scope.subcollection;
        advSearchVo.department = $scope.department;
        advSearchVo.introdate = $scope.introdate;
        advSearchVo.fsintrodate = $scope.fsintrodate;
        advSearchVo.attitude = $scope.attitude;
        advSearchVo.look = $scope.look;
        advSearchVo.lifecyclestate = $scope.lifecyclestate;
        advSearchVo.silhouette = $scope.silhouette;
        advSearchVo.stylegroup = $scope.stylegroup;
        advSearchVo.collaboration = $scope.collaboration;
        advSearchVo.createdate = $scope.createdate;
        advSearchVo.lastupdated = $scope.lastupdated;
        advSearchVo.productType = $scope.producttype;

        
        var scopevalue = document.getElementById("contentlayout");
        var controllerScope = angular.element(scopevalue).scope();
        controllerScope.productcriteriadata = advSearchVo;
        $scope.toggleloading();
        var promise = $http.post('/PLMViewer/userlogin/LandingLayout/getSearchProductList', advSearchVo);
        promise.success(function (data, status, headers, config) {

            $scope.toggleloading();
            if (data.exceptionmessage) {
                $scope.errordata = data;
                $scope.content = "error";
                return;
            }
            var scopevalue = document.getElementById("contentlayout");
            var controllerScope = angular.element(scopevalue).scope();
            controllerScope.datalists = data;

            controllerScope.showData();
            controllerScope.issizecalevisible = $scope.sizecalevisible;
            controllerScope.content = "serResult";


            $scope.showAdvPrdSearch = false;
            $scope.showsearchresult = true;
            //controllerScope.productSearch = "Style#";
            var treescope = document.getElementById("treeModel");
            var treecontrollerScope = angular.element(treescope).scope();
            treecontrollerScope.ProductTreeModel = false;
            treecontrollerScope.MaterialTreeModel = false;

        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error while processing advSearch");
        });
    };




    this.advSearchInitLoad = function (item, subitem, $scope, $http) {


        var parameters = {};

        if (subitem.list == 'Product') {
            parameters.attributelevel = "PRODUCT";

            parameters.attributename = "STYLEGROUP";
            var treescope = document.getElementById("treeModel");
            var treecontrollerScope = angular.element(treescope).scope();
            treecontrollerScope.ProductTreeModel = false;
            treecontrollerScope.MaterialTreeModel = false;
            $scope.toggleloading();
            var promise = $http.get('/PLMViewer/userlogin/searchLayout/initLoad', {params: parameters});
            promise.success(function (data, status, headers, config) {
                $scope.toggleloading();
                if (data.exceptionmessage) {
                    $scope.errordata = data;
                    $scope.content = "error";
                    return;
                }
                $scope.advancesearchmap = data;
                $scope.showAdvPrdSearch = true;
                $scope.content = "advSearch";

            }).error(function (data, status, headers, config) {
                $scope.toggleloading();
                alert("error in advSearch Department InitLoad");
            });
        } else if (subitem.list == 'Material') {
            var parameters = {};
            parameters.attributelevel = "Material";
            parameters.attributename = "STYLEGROUP"
            $scope.toggleloading();
            var promise = $http.get('/PLMViewer/userlogin/searchLayout/materialinitLoad', {params: parameters});
            promise.success(function (data, status, headers, config) {
                if (data.exceptionmessage) {
                    $scope.errordata = data;
                    $scope.content = "error";
                    return;
                }
                $scope.toggleloading();
                $scope.materialmap = data;
                $scope.showmaterialSearch = true;
                $scope.stylenumber = "";
                $scope.content = "advMaterialSearch";
            }).error(function (data, status, headers, config) {
                $scope.toggleloading();
                alert("error in advMaterial Search");
            });
        } else if (subitem.list == 'Document') {

            parameters.attributelevel = "PRODUCT";

            parameters.attributename = "STYLEGROUP";
            var treescope = document.getElementById("treeModel");
            var treecontrollerScope = angular.element(treescope).scope();
            $scope.toggleloading();
            var promise = $http.get('/PLMViewer/userlogin/searchLayout/initDocumentLoad', {params: parameters});
            promise.success(function (data, status, headers, config) {
                $scope.toggleloading();
                // alert(JSON.stringify(data));
                // alert(JSON.stringify(data.exceptionmessage));
                if (data.exceptionmessage) {
                    $scope.errordata = data;
                    $scope.content = "error";
                    return;
                }
                $scope.documentmap = data;
                $scope.showDocumentSearch = true;
                $scope.stylenumber = "";
                $scope.content = "documentSearch";
            }).error(function (data, status, headers, config) {
                $scope.toggleloading();
                $scope.errordata = data;
                $scope.content = "error";

            });
        } else if (subitem.list == 'Images') {
            $scope.toggleloading();
            var promise = $http.get('/PLMViewer/userlogin/searchLayout/initImageLoad');
            promise.success(function (data, status, headers, config) {
                $scope.toggleloading();
                //alert(JSON.stringify(data));
                $scope.imagesmaplist = data;
                $scope.stylenumber = "";
                $scope.content = "imagesearch";
            }).error(function (data, status, headers, config) {
                $scope.toggleloading();
                alert("error in advSearch Image InitLoad");
            });
        }else if (subitem.list == 'Where Used Report') {
            $scope.toggleloading();
            var promise = $http.get('/PLMViewer/userlogin/LandingLayout/initReportLoad');
            promise.success(function (data, status, headers, config) {
                $scope.toggleloading();
                $scope.departmentmap = data;
                $scope.stylenumber = "";
                $scope.content = "materialReport";
								$scope.showmaterialReport=true;

            }).error(function (data, status, headers, config) {
                $scope.toggleloading();
                alert("error in advSearch Image InitLoad");
            });
        }






    };

    this.setTreeModel = function () {
        var treescope = document.getElementById("treeModel");
        var treecontrollerScope = angular.element(treescope).scope();
        treecontrollerScope.ProductTreeModel = false;
        treecontrollerScope.MaterialTreeModel = false;
    };

    this.productDocumentDetails = function (prodNum, type, $scope, $http) {
        $scope.toggleloading();
        var parameters = {};
        parameters.prodNum = prodNum;
        parameters.type = type;
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getProductDocumentList', {params: parameters});
        promise.success(function (data, status, headers, config) {
            $scope.toggleloading();
            if (data.exceptionmessage) {
                $scope.errordata = data;
                $scope.content = "error";
                return;
            }
            $scope.prodDocumentList = data;
            $scope.content = "productSearch";
            $scope.subSearch = "Document";
            $scope.currentTab = "Document";

        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error Occurred while get Product Document Details.");
        });
    };



    this.loadProdImgList = function (specName, specId, $scope, $http, $modal) {
        $scope.toggleloading();
        var successMsg = false;
        var parameters = {};
        parameters.docMasterId = specId;
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getProductSpecImagesList', {params: parameters})
                .success(function (data, status, headers, config) {
                    $scope.toggleloading();
                    if (data.exceptionmessage) {
                        $scope.errordata = data;
                        $scope.content = "error";
                        return;
                    }
                    $scope.specImageDataList = data;
                    $scope.content = "productSearch";
                    $scope.subSearch = "Specifications";
                    $scope.currentTab = "Specifications";
                    successMsg = true;

                }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error Occurred while get Product Document Details.");
        });



    };

    this.loadImageList = function (ida3MasterRef, $scope, $http) {
        $scope.toggleloading();
        var successMsg = false;
        var parameters = {};
        parameters.ida3MasterRef = ida3MasterRef;
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getProductImagesList', {params: parameters})
                .success(function (data, status, headers, config) {
                    $scope.toggleloading();
                    if (data.exceptionmessage) {
                        $scope.errordata = data;
                        $scope.content = "error";
                        return;
                    }
                    $scope.prodImageDataList = data;
                    $scope.content = "productSearch";
                    $scope.subSearch = "Images";
                    $scope.currentTab = "Images";

                }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error Occurred while get Product Image tab Details.");
        });
    };


    this.getbomDetail = function (ida3MasterRef, bomPartId, $scope, $http) {
        $scope.toggleloading();
        var successMsg = false;
        var parameters = {};
        parameters.ida3MasterRef = ida3MasterRef;
        parameters.bomPartId = bomPartId;
        var promise = $http.get('/PLMViewer/userlogin/LandingLayout/getBomDetails', {params: parameters})
                .success(function (data, status, headers, config) {
                    $scope.toggleloading();
                    if (data.exceptionmessage) {
                        $scope.errordata = data;
                        $scope.content = "error";
                        return;
                    }
                    $scope.model = data;
                    $scope.selectedValue = data.currentBOM;

                    $scope.content = "productSearch";
                    $scope.subSearch = "Material";
                    $scope.currentTab = "Material";

                }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error Occurred while get Product Material tab Details.");
        });
        
        
        
    };


});