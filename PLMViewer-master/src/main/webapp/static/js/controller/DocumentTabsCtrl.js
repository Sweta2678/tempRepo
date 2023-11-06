App.controller('DocumentTabsCtrl', ['$scope', function ($scope) {
        $scope.documentTabs = [{
                title: 'Details',
                url: 'Details'
            }];

        $scope.currentTab = $scope.subSearch;

        $scope.onClickTab = function (tab) {
            $scope.currentTab = tab.url;
            var scopevalue = angular.element(document.getElementById("contentlayout")).scope();
            scopevalue.subSearch = $scope.currentTab;
        }

        $scope.isActiveTab = function (tabUrl) {
            return tabUrl == $scope.currentTab;
        }




    }]);