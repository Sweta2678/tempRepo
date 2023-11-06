<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html data-ng-app="myApp">


    <head>
        <title>Landing Layout</title>
        <script>document.write('<base href="' + document.location + '" />');</script>
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />-->
        <link href="<c:url value='/static/js/Lib/bootstrap.min.css' />" rel="stylesheet"></link>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
        <link href="<c:url value='/static/css/styles.css'/>" rel="stylesheet"></link>

        <link href="<c:url value='/static/css/resizer.css' />" rel="stylesheet"></link>
        <!--<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.14.3/ui-bootstrap-tpls.js"></script>-->

        <script src="http://code.jquery.com/jquery-2.0.3.min.js"
        data-semver="2.0.3" data-require="jquery"></script>
        <!--<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-route.js"></script>-->
        
        <script src="<c:url value='/static/js/Lib/angular.js' />"></script>
        <script src="<c:url value='/static/js/Lib/ui-bootstrap-tpls.js' />"></script>
        <script src="<c:url value='/static/js/Lib/angular-route.js' />"></script>
        
        
        <script src="<c:url value='/static/js/app.js' />"></script>
        <script src="<c:url value='/static/js/controller/landingCtrl.js' />"></script>
        <script src="<c:url value='/static/js/service/landingService.js' />"></script>
        <script src="<c:url value='/static/js/controller/materialCtrl.js' />"></script>
        <script src="<c:url value='/static/js/service/materialService.js' />"></script>
        <script src="<c:url value='/static/js/controller/documentCtrl.js' />"></script>
        <script src="<c:url value='/static/js/controller/ImageCtrl.js' />"></script>
        <script src="<c:url value='/static/js/service/documentService.js' />"></script>
        <script src="<c:url value='/static/js/service/ImageService.js' />"></script>
        <script src="<c:url value='/static/js/directive/resizer.js' />"></script>
        <script src="<c:url value='/static/js/directive/content.js' />"></script>
        <script src="<c:url value='/static/js/controller/TabsCtrl.js' />"></script>
        <script src="<c:url value='/static/js/controller/MaterialTabsCtrl.js' />"></script>
        <script src="<c:url value='/static/js/controller/DocumentTabsCtrl.js' />"></script>
        <script src="<c:url value='/static/js/treeview.js' />"></script>
        <script src="<c:url value='/static/js/directive/factoryStore.js' />"></script>
        <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/static/css/jsDatePick_ltr.min.css' />" />
        <script type="text/javascript" src="<c:url value='/static/js/directive/jsDatePick.min.1.3.js' />"></script>
        <script src="<c:url value='/static/js/directive/materialColor.js' />"></script>
        <script src="<c:url value='/static/js/directive/materialPrice.js' />"></script>
        <script src="<c:url value='/static/js/directive/materialColorDetail.js' />"></script>
        <script src="<c:url value='/static/js/service/myService.js' />"></script>
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

    <body data-ng-controller="landingCtrl"
          style="background-image: none; background-color: #f3f3f3;" onload="changeHashOnLoad();">



        <!--        <div style="float: left;width: 7%;min-height: 675px; max-height: 720px;background-color: #0d528b"></div>
        <div style="float: right;width: 7%;min-height: 675px;max-height: 720px;background-color: #0d528b"></div>
        -->



        <div id="loadingcontent"
             style="float: none; width: 100%; overflow: auto; background-color: #f3f3f3;">

            <div id="topnav">



                <div>
                    <table>
                        <tr>
                            <td width="30%"><img
                                    src="/PLMViewer/static/images/coach-logo-INTL@1x.png" width="65%"
                                    height="55px" /></td>
                            <td><select class="droupdown" ng-model="productSearch">
                                    <option value="">-Select-</option>
                                        <option ng-repeat="(key,value)  in productSearchOption">{{
                                                                                value}}</option>
                                </select> &nbsp; <input type="text" ng-model="searchValue"
                                                        class="textboxbg" placeholder="Keywords..." style="width: 250px" />&nbsp;
                                <input type="button" class="buttonwithimage"
                                       data-ng-click="search()"></input>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
                                    class="pargagraphstyling  text-right"><b>
                                        {{user.firstName}} {{user.lastName}}</b></span> <a
                                    class="logout text-right" href="/PLMViewer/" ng-click="logout();"><b>Logout</b></a>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        </tr>
                    </table>

                </div>

            </div>
            <div id="topnav-resizer" resizer="horizontal" resizer-width="4">
            </div>


            <div id="sidebar">

                <div id="treeModel">

                    <li style="list-style-type: none;" ng-class="text - center"
                        ng-repeat="item in advSrcItems" ng-click="showStates(item)"><span><b>{{item.mainNode}}</b></span>

                        <div>
                            <ul>
                                <li ng-repeat="subItem in item.sublist" ng-class="text - center">
                                    <img src="/PLMViewer/static/images/arrow.png" height="10px"
                                         width="10px"></img> &nbsp;<a href=""
                                         ng-click="showAdvpage(item, subItem)"><b>{{subItem.list}}</b>
                                    </a>
                                </li>
                            </ul>
                        </div></li>

                </div>

            </div>

            <div id="content">

                <div id="top-content">
                    <div id="loadimg" ng-show="showloading" class="Loadingimage">
                        <!--  <center><img src="/PLMViewer/static/images/image_937077.gif" width="50px" height="50px"/></center>-->
                    </div>
                    <content-screen id="contentlayout"></content-screen>




                </div>
                <!--div id="content-resizer" 
                resizer="horizontal" 
                resizer-height="6" 
                resizer-top="#top-content" 
                
                >
        </div -->

            </div>

            <div id="sidebar-resizer" resizer="vertical" resizer-width="6"
                 resizer-left="#sidebar" resizer-right="#content" resizer-max="220">
            </div>

        </div>



    </body>
</html>