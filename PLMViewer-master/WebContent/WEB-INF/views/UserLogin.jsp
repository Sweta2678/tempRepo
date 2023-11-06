<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>User Login</title>
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
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body data-ng-app="myApp"  >
<div data-ng-controller="loginCtrl">
  <form name="myForm" id="myForm" class="ng-scope container;" novalidate  data-ng-submit="submit()">  
  <div class="form-group"><h1><small>
    <p class="text-center text-primary"> Login Screen </span>
	</p></small>
	</h1>
	</div>
   <div class="form-horizontal">
    <label for="UserName" class="col-sm-5 control-label">
    First Name:</label>
    <input type="text" class="col-sm-5 control-label" name="firstName" data-ng-model="user.firstName" placeholder="Enter your name" required /><span class="glyphicon glyphicon-user" /><br>
	 <span class="text-danger" ng-show="myForm.firstName.$error.required">UserName is required</span>
</div>

   <div class="form-horizontal">
    <label for="Password" class="col-sm-5 control-label">  Password:</label>
    <input type="password" name="pwd" class="col-sm-5 control-label"  data-ng-model="user.password" placeholder="Enter your password" required/>
	<span class="text-danger"  ng-show="myForm.pwd.$error.required">Password is required</span>
    </div>
	<br><br>
	 <div lass="form-horizontal"><center>
    <button data-ng-click="reset()" class="btn btn-default"><span class=" text-success text-center"> RESET</span></button>&nbsp;<button class="btn btn-default">SUBMIT</button>
</center>	</div>
<br>
	<div class="form-horizontal"><center>
    <div data-ng-if= "user.message === 'success' "><b> <span class=" text-success text-center"> Authenticated Successfully </span></b></div>
    <div data-ng-if="user.message === 'failed'"  ><b> <span class=" text-warning text-center">Authenticated Failed </span></b>	</div>
</center>
	</div>
  
  
  </form>
  </div>
 
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-route.js"></script>
  <script src="<c:url value='/static/js/app.js' />"></script>
  <script src="<c:url value='/static/js/service/user_login_service.js' />"></script>
  <script src="<c:url value='/static/js/controller/user_login_controller.js' />"></script>
</body>
</html>