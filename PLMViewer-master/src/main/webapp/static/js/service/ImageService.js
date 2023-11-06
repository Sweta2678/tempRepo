App.service('imageservice', function () {
    this.imageSearch = function ($scope, $rootScope, $http) {
        var advoimagesearch = {};
        advoimagesearch.stylename = $scope.stylename;
        advoimagesearch.stylenumber = $scope.stylenumber;
        advoimagesearch.department = $scope.department;
        advoimagesearch.styleclass = $scope.styleclass;
        advoimagesearch.subclass = $scope.subclass;
        advoimagesearch.collection = $scope.collection;
        advoimagesearch.subcollection = $scope.subcollection;
        advoimagesearch.createdfrom = $scope.createdfrom;
        advoimagesearch.createdto = $scope.createdto;
        advoimagesearch.updatedfrom = $scope.updatedfrom;
        advoimagesearch.updatedto = $scope.updatedto;
        advoimagesearch.pagename = $scope.pagename;
        advoimagesearch.pagetype = $scope.pagetype;
        advoimagesearch.introdate = $scope.introdate;
        advoimagesearch.fsintrodate = $scope.fsintrodate;
        var scopevalue = document.getElementById("contentlayout");
        var controllerScope = angular.element(scopevalue).scope();
        controllerScope.imagecriteriadata = advoimagesearch;
        $scope.toggleloading();
        var promise = $http.post('/PLMViewer/userlogin/LandingLayout/getImagesSearchList', advoimagesearch);
        promise.success(function (data, status, headers, config) {

            $scope.toggleloading();
            var scopevalue = document.getElementById("contentlayout");
            var controllerScope = angular.element(scopevalue).scope();
            controllerScope.datalists = data;

            //alert(JSON.stringify(data));
            controllerScope.content = "imagesearchresult";
            $scope.showAdvPrdSearch = false;
            $scope.showsearchresult = true;
        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error While processing Image  Adv search");
        });


    };
    
    
    this.loadDocImgList = function(docMasterId,$scope,$http){
   	 $scope.toggleloading();
   	 var parameters ={};
   	 parameters.docMasterId = docMasterId;
     $http.get('/PLMViewer/userlogin/LandingLayout/getProductSpecImagesList', {params: parameters})
       .success(function (data, status, headers, config) {
            $scope.toggleloading();
            $scope.DocImageDataList = data;
            $scope.content = "imagesearchresult"; 
             	 
        }).error(function (data, status, headers, config) {
            $scope.toggleloading();
            alert("Error Occurred while getting Product Document Details.");
        });
    };

});