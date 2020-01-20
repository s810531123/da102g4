<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.teacher.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.schedule.model.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
// 		session.setAttribute("t_num_Login", "T002");

%>

<!DOCTYPE html>
<html>
<head>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>排班表</title>
<%@ include file="/back-end/includePage/Head"%>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/back-end/TSchedule/css/indexTSchedule_teacher.css">
<link href="https://fonts.googleapis.com/css?family=Ma+Shan+Zheng&display=swap" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/TSchedule/css/toastr.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/back-end/TSchedule/JS/toastr.js"></script>


</head>
<body>

		
		<%@ include file="/back-end/includePage/Nav"%>
		<%@ include file="/back-end/includePage/LeftSide"%>
		<div class="col-lg-10 col-md-10 cb rightContent">


			<div id="nav" >   	
				<button class="nav" id="previous" style="width: 5%">◀</button>
				<button class="nav" id="now" style="width: 8%">今天</button>
		    	<div class="nav" id="year_month" style="width: 45%; font-size: 2rem">1970</div>
		    	<button class="nav" id="next" style="width: 5%">▶</button>
	    	</div>
	    	
	    	
	    <table id="main">
			<tr id="title" >
				<td class="title">日</td>
				<td class="title">一</td>
				<td class="title">二</td>
				<td class="title">三</td>
				<td class="title">四</td>
				<td class="title">五</td>
				<td class="title">六</td>
			</tr>
		
	
		    <tr id="row0" >
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    </tr>
		    <tr id="row1" >
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    </tr>
		    <tr id="row2" >
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    </tr>
		    <tr id="row3" >
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    </tr>
		    <tr id="row4" >
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
			</tr>
			<tr id="row5" >
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date">&nbsp;</div><div class="class_sort"></div></td>
			</tr>
		
			</table>

					
					
						
		</div>
		<%@ include file="/back-end/includePage/BootStrap"%>
		
		
		
</body>

<script src="<%=request.getContextPath()%>/back-end/TSchedule/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/TSchedule/datetimepicker/jquery.datetimepicker.full.js"></script>


	
	
	<script>
	//取得當日日期
	var today = new Date();
	
	//取得1970至今天0點的毫秒  /*new Date(year,month,today.getDate())*/
	var today_LongTime = today.getTime();
	
	var today_LongTime
	//取得年分
	var year = today.getFullYear();
	//取得月份
	var month = today.getMonth();		

	var dates = $(".date");
	var class_sorts = $(".class_sort");
	
	var month_first
	var numDay


	function now(){

		//當月1號星期幾
		 month_first = new Date(year,month,1).getDay();
		//取得當月有幾天  ex:7/0--->6月最後一天
		 numDay = new Date(year,month+1,0).getDate();
		var count = 1;
		//當月日期
		for(var i = month_first; i < numDay+month_first; i++){
			dates[i].innerHTML = count;
			//取得每天的毫秒數
			var longTime = new Date(year,month,count).getTime();
			count++;

		}
		


		//顯示現在年月
		$("#year_month").html(year+"　　年　　"+(month+1)+"　　月") ;

		//判斷年月是否等於當下年月
		if(today.getFullYear()==year && today.getMonth()==month){
			dates[today.getDate()+month_first-1].style.backgroundColor="rgba(255,247,0,0.9)";
		}			
		//前面
		for(var i =0; i<month_first; i++){				
			disable(i);
		}
		//後面
		for(var i = numDay+month_first; i < dates.length; i++){
			disable(i);
		}

	}
	//上鎖
	function disable(i){
		class_sorts[i].disabled = true;
		dates[i].disabled = true;
		class_sorts[i].style.backgroundColor="rgba(180,180,180,0.9)";
		dates[i].style.backgroundColor="rgba(180,180,180,0.9)";
	}
	//清除
	function clear(){
		$("div.date").html("&nbsp;").css({"disabled":"false","background-color":"rgba(240,240,240,0.9)"});
		$("div.class_sort").html("").css({"disabled":"false","background-color":"rgba(240,240,240,0.9)"});

	}

	
	toastr.options = {
			  "closeButton": true,
			  "debug": false,
			  "newestOnTop": true,
			  "progressBar": true,
			  "positionClass": "toast-top-right",
			  "preventDuplicates": true,
			  "onclick": null,
			  "showDuration": "300",
			  "hideDuration": "1000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
	
	
	/*********************************************************************************************************/
	/***WebSocket***/
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var URL = "ws://" + host + webCtx + "/TSshedule";
	var webSocket;
	/************/
	var t_num_Login = '${tp.t_num}'
	
	function WebSocketConnect(){
		webSocket = new WebSocket(URL);
		
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			var action = jsonObj.action;
			var t_num = jsonObj.t_num;
			//是否為同一個老師
			if(t_num_Login == t_num){
				
				if(action == "add"){
					var yy = parseInt(jsonObj.ts_date.substring(0,4));
					var mm = parseInt(jsonObj.ts_date.substring(5,7));
					toastr.info('新增了 ' + jsonObj.ts_date +' 班表資料!');
					if((yy == year) && (mm == (month +1))){
						placeDate(jsonObj);
						jsonObj.ts_date
						
					}
					
				}  else if(action == "delete"){
					deleteWebSocket(jsonObj);
					toastr.info('移除了 ' + jsonObj.ts_date +' 班表資料!');
				}
			}
			
			if(action == "update"){
				updateWebSocket(jsonObj);
			}
		}
	};
	
	
	
	
	
	
	
	//Ajax取得每月資料
	function getDate(year, month){

		$.ajax({
			
			type:"POST",
/*注意路徑*/		url:"<%=request.getContextPath()%>/schedule/schedule.do",
			data:{"action":"getAllOneTeacher","year":year,"month":month},
			dataType:"json",
			success:function(data){
//				console.log(data);
				$(data).each(function(i,item){
//					console.log(i);
//					$(item).each(function(name, value){
						placeDate(item);
						
//					})
				});
			},
//			error: function(){alert("AJAX-grade發生錯誤囉!")}
			
		});
	}
	
	
	function placeDate(item){
		var t_name = item.t_name;
		var on_duty_cs = item.on_duty_cs;
		var ts_date = parseInt(item.ts_date.substring(8,10));
		for(var i =0; i < dates.length; i++){
			if($('.date').eq(i).html() == ts_date){
				$('.class_sort').eq(i).append('<span class="span">'+ on_duty_cs +" "+ t_name +'</span>')
				
				
			}
		}
		
	}
	
	
	
	function updateWebSocket(item){
		var t_num = item.t_num;
		var t_name = item.t_name;
		var on_duty_cs = item.on_duty_cs;
		var ts_date = parseInt(item.ts_date.substring(8,10));
		var bool = true;
		var yy = parseInt(item.ts_date.substring(0,4));
		var mm = parseInt(item.ts_date.substring(5,7));
			for(var i =0; i < dates.length; i++){
				if($('.date').eq(i).html() == ts_date){
					var arr = $('.class_sort').eq(i).find('span');
					for(var j = 0; j < arr.length; j++){
						if((arr[j].innerHTML.substring(0,2) == on_duty_cs)){
							if(t_num != t_num_Login){
								var parentObj = arr[j].parentNode;
								parentObj.removeChild(arr[j]);
								toastr.info('移除了 ' + item.ts_date +' 班表資料!');
// 								arr[j].innerHTML = "";
							} else if(t_num == t_num_Login){
// 								arr[j].innerHTML = on_duty_cs + " " + t_name;
								bool = false;
							}
						} 
					}
					if(bool && (t_num == t_num_Login) && (yy == year) && (mm == (month +1))){
						placeDate(item);
						toastr.info('新增了 ' + item.ts_date +' 班表資料!');
					}				
				}
			}
		
	}
	
	
	
	function deleteWebSocket(item){
		var on_duty_cs = item.on_duty_cs;
		var ts_date = parseInt(item.ts_date.substring(8,10));
		for(var i =0; i < dates.length; i++){
			if($('.date').eq(i).html() == ts_date){
				var arr = $('.class_sort').eq(i).find('span');
				for(var j = 0; j < arr.length; j++){
					if(arr[j].innerHTML.substring(0,2) == on_duty_cs){
						var parentObj = arr[j].parentNode;
						parentObj.removeChild(arr[j]);
// 						arr[j].innerHTML = "";
					}
				}
			}
		}
		
		
		
	}

	
	//初始化
	$(document).ready(function() {
			now();
			WebSocketConnect();
			getDate(year,month);
			
			
			$("#next").click(function(){
				clear();
			//按一下 月份+1
			month++;
			//超過12月年分+1
			if(month>11){
				month=0;
				year++;
			}
			//在執行一次
			now();
			
			getDate(year,month);
			});

			$("#previous").click(function(){
				clear();
			//按一下 月份-1
			month--;
			//低於1月 年分-1
			if(month<0){
				month=11;
				year--;
			}
			//在執行一次
			now();
			
			getDate(year,month);
			});
			//當下
			$("#now").click(function(){
				year = today.getFullYear();	
			month = today.getMonth();
			clear();
			now();
			getDate(year, month);
			});
			
			
			
	});
	
	
	$(window).unload(function(){
		webSocket.close();
	});
	
	</script>
</html>