App.factory('myService', function($http) {
 return {
	loadProdImgList : function(specName,specId,$scope,$http,$modal){
	 var parameters ={};
	 parameters.docMasterId = specId;
     return $http.get('/PLMViewer/userlogin/LandingLayout/getProductSpecImagesList', {params: parameters});
}
}; 
});
