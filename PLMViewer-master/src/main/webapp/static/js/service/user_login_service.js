

App.service('UserLoginService', function () {
    this.submit = function ($scope, $http, $rootScope, $window) {

        var userObj = {
            userName: $scope.user.userName,
            password: $scope.user.password,
            message: $scope.user.message
        };
        if (userObj != null && userObj != "" && userObj != "undefined") {
            if (userObj.userName != null && userObj.userName.trim() != "") {
                if (userObj.password != null && userObj.password.trim() != "") {
                    $http.post('/PLMViewer/userlogin/postvalidate/', userObj).
                            success(function (data, status, headers, config) {
                                $scope.user = data;
                                if ($scope.user.successMsg == true) {
                                    // if($scope.user.message == 'success'){
                                    $window.sessionStorage.setItem('USER', $window.JSON.stringify($scope.user));
                                    $window.location.href = "/PLMViewer/userlogin/LandingLayout/";

                                }
                            }).
                            error(function (data, status, headers, config) {
                                if (status == 400) {
                                    $scope.message = data;
                                } else {
                                    alert('Unexpected server error.');
                                }

                            });

                } else {
                    $window.alert("Please enter your Password!");
                    return;
                }
            } else {
                $window.alert("Please enter your UserName!");
                return;
            }
        } else {
            $window.alert("Please enter your Credentials!");
            return;
        }



    }
});