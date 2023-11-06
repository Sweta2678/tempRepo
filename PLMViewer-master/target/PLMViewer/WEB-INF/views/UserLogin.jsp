
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>PLMViewer</title>
        <style>
            .firstName.ng-valid {
                background-color: lightgreen;
            }

            .firstName.ng-dirty.ng-invalid-required {
                background-color: red;
            }

            .firstName.ng-dirty.ng-invalid-minlength {
                background-color: yellow;
            }
        </style>


        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"></link>-->
        <link href="<c:url value='/static/js/Lib/bootstrap.min.css' />" rel="stylesheet"></link>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
        <link href="<c:url value='/static/css/styles.css' />" rel="stylesheet"></link>
        <!--<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.14.3/ui-bootstrap-tpls.js"></script>-->
        
         <script src="<c:url value='/static/js/Lib/angular.js' />"></script>
        <script src="<c:url value='/static/js/Lib/ui-bootstrap-tpls.js' />"></script>
        <script src="<c:url value='/static/js/Lib/angular-route.js' />"></script>
        
       
        <script>
            function changeHashOnLoad() {
                window.location.href += "#";
                setTimeout("changeHashAgain()", "50");
            }

            function changeHashAgain() {
                window.location.href += "1";
            }

            var storedHash = window.location.hash;
            window.setInterval(function () {
                if (window.location.hash != storedHash) {
                    window.location.hash = storedHash;
                }
            }, 50);
        </script>
    </head>
    <body data-ng-app="myApp" onload="changeHashOnLoad();">
        <div id="headerblack"></div>
        <div id="logo">
            <br>
            <br>

            <center>
                <img src="/PLMViewer/static/images/coach-logo-INTL@1x.png" />
            </center>

            <br>

        </div>


        <div data-ng-controller="loginCtrl">
            <form name="myForm" id="myForm" class="ng-scope container;" novalidate
                  data-ng-submit="submit()">
                <div style="height: 80px; width: 1021px;"></div>
                <div class="form-group">
                    <br>
                    <br>
                    <h1>
                        <center>
                        <small>
                            <p class="text-center text-primary">
                                PLM Viewer </span>
                            </p>
                        </small>
                            </center>
                    </h1>
                </div>


                <div align="center">
                    <table>
                        <tr>
                            <td width="50px" align="right"><label for="UserName"
                                                                  class="label">&nbsp;&nbsp;Email: </label></td>
                            <td width="50px"><input type="text" class="inputTexBox"
                                                    style="width: 320px;" name="firstName"
                                                    data-ng-model="user.userName" placeholder="Enter your Email" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" align="right"><br></td>
                        </tr>
                        <tr>
                            <td width="50px" align="right"><label for="Password"
                                                                  class="label">Password:</label></td>
                            <td width="50px"><input type="password" name="pwd"
                                                    class="inputTexBox" style="width: 320px;"
                                                    data-ng-model="user.password" placeholder="Enter your password" />
                            </td>
                            <!-- td width="150px"><span class="text-danger"  ng-show="myForm.pwd.$error.required">&nbsp;Password is required</span></td -->
                        </tr>

                        <tr>
                            <td colspan="3" align="right"><br></td>
                        </tr>
                        <tr>
                            <td colspan="3" align="right">
                                <!-- button data-ng-click="reset()" class="SecButton"><span class=" text-success text-center"> RESET</span></button-->&nbsp;
                                <div align="center">
                                    <button class="SecButton">
                                        <span class=" text-success text-center">SUBMIT<span>
                                                </button>
                                                </div>

                                                </td>
                                                </tr>
                                                </table>
                                                </div>
                                                <br>
                                                <div class="form-horizontal">
                                                    <center>
                                                        <div data-ng-if="user.message === 'success'">
                                                            <b> <span class=" text-success text-center">
                                                                    Authenticated Successfully </span></b>
                                                        </div>
                                                        <div data-ng-if="user.message === 'failed'">
                                                            <b> <span class=" text-warning text-center">Authenticated
                                                                    Failed </span></b>
                                                        </div>
                                                    </center>
                                                </div>


                                                </form>
                                                </div>

                                                <!--                                                <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
                                                                                                <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-route.js"></script>-->
                                                <script src="<c:url value='/static/js/Lib/angular.js' />"></script>
                                                <script src="<c:url value='/static/js/Lib/ui-bootstrap-tpls.js' />"></script>
                                                <script src="<c:url value='/static/js/Lib/angular-route.js' />"></script>



                                                <script src="<c:url value='/static/js/app.js' />"></script>
                                                <script src="<c:url value='/static/js/service/user_login_service.js' />"></script>
                                                <script src="<c:url value='/static/js/controller/user_login_controller.js' />"></script>
                                                </body>