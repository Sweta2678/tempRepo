app.controller('headerCtrl', function ($scope) {
 
    $scope.searchList1 = ['Style Number', 'Style Name', 'Material CM #', 'Material Name', 'Document'];
 
    $scope.searchList1Selected = function () {
        alert("drop box item selected");
    }
});