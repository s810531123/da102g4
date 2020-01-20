<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0">
<%@ include file="/front-end/Head"%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/map/css/map.css">
</head>
<body>

	<%@ include file="/front-end/Nav"%>
	<%@ include file="/front-end/Top"%>

	<!-- ======================================================================================= -->

	<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="outer">
					<div id="newMap"></div>
				</div>
			</div>
		</div>
	</section>

	<!-- ======================================================================================= -->

<!-- 	<section class="ftco-section"> -->
<!-- 		<div class="container"> -->
<!-- 			<div class="col-lg-12"> -->
<!-- 				<div class="outer"></div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</section> -->

	<!-- ======================================================================================= -->

	<%@ include file="/front-end/Footer"%>
	
	<script>
    /***WebSocket***/
  	var host = window.location.host;
  	var path = window.location.pathname;
  	var webCtx = path.substring(0, path.indexOf('/', 1));
  	var URL = "ws://" + host + webCtx + "/map";
  	var webSocket;
  	/************/
    
    
      var map;
      var myLatlng = {lat: 24.9698051, lng: 121.1912047};
      var marker;
      function initMap() {
        map = new google.maps.Map(document.getElementById('newMap'), {
          center: myLatlng,
          zoom: 18
        });
        
         marker = new google.maps.Marker({
            position: myLatlng,
            map: map,
            icon: 'images/marker.png',
            title: '1',
          });
      
       
        
//         if (navigator.geolocation) {
//             navigator.geolocation.getCurrentPosition(function(position) {
//                 var pos = {
//                     lat: position.coords.latitude,
//                     lng: position.coords.longitude
//                 };
//                 marker = new google.maps.Marker({
//                     position: pos,
//                     icon:'images/marker.png',
//                     map: map
//                 });
//                 map.setZoom(17);
//                 map.setCenter(pos);
//             });
//         } else {
//             // Browser doesn't support Geolocation
//             alert("未允許或遭遇錯誤！");
//         }

        
      	webSocket = new WebSocket(URL);
      	webSocket.onopen = function(event) {
      		/***下下策**/
// 	        webSocket.send("start");
      	}
      	
      	
		webSocket.onmessage = function(event) {
			var json = JSON.parse(event.data)
			var newLat = json.lat;
			var newLng = json.lng;
			
			console.log(newLat)
			console.log(newLng)
			
			var newLatLng = new google.maps.LatLng(newLat, newLng);
			marker.setPosition(newLatLng);

  			
  		}
        
      }

      
  		
  	
  	$(window).on("unload", function(e) {
  		webSocket.close();
  	});
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB6zkrhvutco8bWmqZYjQ4yRigqFnMAMOc&callback=initMap"
    async defer></script>
</body>

 
</html>