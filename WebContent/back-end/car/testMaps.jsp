<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <title>Simple Map</title>
    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 80%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
  </head>
  <body>
    <div id="map"></div>
    <script>
    /***WebSocket***/
  	var host = window.location.host;
  	var path = window.location.pathname;
  	var webCtx = path.substring(0, path.indexOf('/', 1));
  	var URL = "ws://" + host + webCtx + "/map";
  	var webSocket;
  	/************/
    
    
      var map;
      var myLatlng = {lat: 24.9677468, lng: 121.1895113};
      var marker;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: myLatlng,
          zoom: 20
        });
        
//          marker = new google.maps.Marker({
//             position: myLatlng,
//             map: map,
//             icon: 'images/marker.png',
//             title: '1',
//           });
      
       
        
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };
                marker = new google.maps.Marker({
                    position: pos,
                    icon:'images/marker.png',
                    map: map
                });
                map.setZoom(17);
                map.setCenter(pos);
            });
        } else {
            // Browser doesn't support Geolocation
            alert("未允許或遭遇錯誤！");
        }

        
      	webSocket = new WebSocket(URL);
      	webSocket.onopen = function(event) {
	        webSocket.send("start");
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