App.directive('contentScreen', function () {
    return {
        restrict: 'E',
        scope: false,
        template: '<div ng-include="templateUrl"></div>',
        link: function (scope, iElement, iAttrs) {
            // scope.content =scope.$parent.content;
            scope.$watch('content', function (newValue, oldValue) {
                if (newValue !== oldValue) {
                    if (newValue == 'welcome') {
                        scope.templateUrl = '/PLMViewer/static/js/template/welcome.html';
                    } else if (newValue == 'productSearch') {
                        scope.templateUrl = '/PLMViewer/static/js/template/productDetail.html';
                    } else if (newValue == 'materialSearch') {
                        scope.templateUrl = '/PLMViewer/static/js/template/materialDetail.html';
                    } else if (newValue == 'advSearch') {
                        scope.templateUrl = '/PLMViewer/static/js/template/advanceSearch.html';
                    } else if (newValue == 'serResult') {
                        scope.templateUrl = '/PLMViewer/static/js/template/advanceSearchResult.html';
                    } else if (newValue == 'advMaterialSearch') {
                        scope.templateUrl = '/PLMViewer/static/js/template/advanceMaterialSearch.html';
                    } else if (newValue == 'materialSearchResult') {
                        scope.templateUrl = '/PLMViewer/static/js/template/advanceMaterialSearchResult.html';
                    } else if (newValue == 'documentSearch') {
                        scope.templateUrl = '/PLMViewer/static/js/template/advanceDocumentSearch.html';
                    } else if (newValue == 'documentDetail') {
                        scope.templateUrl = '/PLMViewer/static/js/template/documentDetail.html';
                    } else if (newValue == 'documentSearchResult') {
                        scope.templateUrl = '/PLMViewer/static/js/template/advDocumentSearchresult.html';
                    }else if (newValue == 'imagesearch') {
                        scope.templateUrl = '/PLMViewer/static/js/template/advanceImageSearch.html';
                    }else if (newValue == 'imagesearchresult') {
                        scope.templateUrl = '/PLMViewer/static/js/template/advanceImageSearchResult.html';
                    }
                    else if (newValue == 'materialReport') {
                        scope.templateUrl = '/PLMViewer/static/js/template/materialReports.html';
                    }
                    else if(newValue == 'error'){
                        scope.templateUrl = '/PLMViewer/static/js/template/error.html';
                    }
                    console.log("New value! ", newValue);
                }
            }, true);


        }



    }


});

