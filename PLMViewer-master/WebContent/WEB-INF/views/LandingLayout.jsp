<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html data-ng-app="myApp">
<head>
<title>Landing Layout</title>
    <script>document.write('<base href="' + document.location + '" />');</script>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	 <link href="<c:url value='/static/css/resizer.css' />" rel="stylesheet"></link>
   <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script src="http://code.jquery.com/jquery-2.0.3.min.js" data-semver="2.0.3" data-require="jquery"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-route.js"></script>
  <script src="<c:url value='/static/js/app.js' />"></script>
    <script src="<c:url value='/static/js/controller/landingCtrl.js' />"></script>
    <script src="<c:url value='/static/js/directive/resizer.js' />"></script>

  </head>

  <body data-ng-controller="landingCtrl">

  	<div id="topnav">
	<table>	<tr>
	<td  class="text-left"><div style="white-space: pre;">{{ text }}</div> <h5><p>Welcome {{user.firstName}}</p></h5></div></td>
	<td class="text-right">Logout</td></tr>
	
	<tr><td class ="text-center">
	<select data-ng-model="myOption" data-ng-options="value.id as value.label group by value.group for value in myOptions">
</select>
<td></tr>
</table>
	</div>
  
  	<div id="sidebar">
  		
  	</div>
  
  	<div id="content" >

  		<div id="top-content" > <p >{{content}}</p></div>
  
  		
  
  		<div id="content-resizer" 
  			resizer="horizontal" 
  			resizer-height="6" 
  			resizer-top="#top-content" 
			
  			>
  		</div>
  
  	</div>
  
  	<div id="sidebar-resizer" 
  		resizer="vertical" 
  		resizer-width="6" 
  		resizer-left="#sidebar" 
  		resizer-right="#content"
  		resizer-max="600">
  	</div>
	
	 
  
  </body>
</html>