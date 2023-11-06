App.service('documentService', function () {
    this.advdocumentSearch = function ($scope, $rootScope, $http) {
        var advdocSearchVo = {};

        advdocSearchVo.documentName = $scope.documentName;
        advdocSearchVo.documentType = $scope.documenType;
        advdocSearchVo.createdDate = $scope.createddate;
        advdocSearchVo.updatedDate = $scope.updateddate;
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
            alert("error in advSearch");
        });
    };


});