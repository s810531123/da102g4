<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.schedule.model.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
TScheduleService ser = new TScheduleService();
List<TScheduleVO> list = ser.getAll();
request.setAttribute("list", list);
%>

<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<button id="btn">click Ajax</button>
	
	
		<script type="text/javascript">
		
			
		</script>
	<script type="text/javascript">
		
<!-- // 	$(document).ready(function(){ -->
		
<!-- // 		$('#btn').on('click',function(){ -->

<!-- // 			$.ajax({ -->
				
<!-- // 				type:"POST", -->
<!-- // 				url:"/DA102G4v2/test/test.do", -->
<!-- // 				data: {"action":"123"}, -->
<!-- // 				dataType:"json", -->
<!-- // // 				async:"fales",	 -->
<!-- // 				success:function(data){ -->
<!-- // // 				alert(data) -->
<!-- // 					$(data).each(function(i, item){ -->
<!-- // 						$(item).each(function(k, v){ -->
<!-- // 							$('#btn').after("<p>"+ v.ts_num +" "+ v.t_num +" "+ v.on_duty_num +" "+ v.ts_date +"</p>"); -->
<!-- // 						}); -->
<!-- // 					}); -->
<!-- // 				}, -->
<!-- // 				error: function(){alert("AJAX-grade發生錯誤囉!")} -->
				
<!-- // 			})		 -->
			
<!-- // 		}); -->
		
		
<!-- // 	}); -->
	
<!-- 	</script> -->
</body>
</html>