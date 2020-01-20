<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body onload="connect();" onunload="disconnect();">


<h1>請說話</h1>
<textarea id="messagesArea" readonly></textarea>
<div id="mess" >
<input type="text" id="message" onkeydown="if (event.keyCode == 13) sendMessage();">
</div>


</body>


<script type="text/javascript">
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var URL = "ws://" + host + webCtx + "/webTest";

var webSocket;

alert(webCtx)
alert(webCtx)

// function connect() {
// 	// create a websocket
// 	webSocket = new WebSocket(URL);

// 	webSocket.onopen = function(event) {
		
// 	};

// 	webSocket.onmessage = function(event) {
// 		var jsonObj = JSON.parse(event.data);
// 		var message = jsonObj.message;		
		
// 		var messagesArea = document.getElementById("messagesArea");
// 		messagesArea.value = messagesArea.value + message;
// 		messagesArea.scrollTop = messagesArea.scrollHeight;
		
		
// 	};

// 	webSocket.onclose = function(event) {
		
// 	};
// }

// function sendMessage() {
		
// 	var inputMessage = document.getElementById("message");
// 	var message = inputMessage.value.trim();
	
// 	if (message === "") {
// 		alert("Input a message");
// 		inputMessage.focus();
// 	} else {
// 		var jsonObj = {
// 			"message" : message
// 		};
		
// 		webSocket.send(JSON.stringify(jsonObj));
// 		inputMessage.value = "";
// 		inputMessage.focus();
// 	}
// }



// function disconnect() {
// 	webSocket.close();
// }


</script>
</html>