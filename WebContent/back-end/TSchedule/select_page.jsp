<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.teacher.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.schedule.model.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>排班表</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/back-end/TSchedule/css/indexTSchedule.css">
<link href="https://fonts.googleapis.com/css?family=Ma+Shan+Zheng&display=swap" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/TSchedule/datetimepicker/jquery.datetimepicker.css" />


<%@ include file="/back-end/includePage/Head"%>
<link href="<%=request.getContextPath()%>/back-end/TSchedule/css/toastr.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/back-end/TSchedule/JS/toastr.js"></script>

</head>
<body>
<!--新增燈箱   -->
	<div id="lightbox">
		<table id="addLightbox" >
						<thead>
							<tr id="addHeadtr">
								<th class="addHeadth" colspan="3"><span id="addSpan">編輯班表</span><input name="hiredate" id="f_date1" type="text"></th>
							</tr>
						</thead>
						<tbody>
							<tr class="addBodytr">
								
								<td class="addBodytd">老師：<select class="selTeacher"><option>請選擇</select></td>
								<td class="addBodytd">班別：<select class="selClass"><option>請選擇</select></td>
							</tr>
							<tr class="addBodytr">
								<td class="addBodytd">老師：<select class="selTeacher"><option>請選擇</select></td>
								<td class="addBodytd">班別：<select class="selClass"><option>請選擇</select></td>
							</tr>
							<tr class="addBodytr">
								<td class="addBodytd">老師：<select class="selTeacher"><option>請選擇</select></td>
								<td class="addBodytd">班別：<select class="selClass"><option>請選擇</select></td>
							</tr>
							<tr class="addBodytr">
								<td class="addBodytd">老師：<select class="selTeacher"><option>請選擇</select></td>
								<td class="addBodytd">班別：<select class="selClass"><option>請選擇</select></td>
							</tr>
							<tr class="addBodytr">
								<td class="addBodytd" colspan="3"><input id="addSubmit" type="submit" value="確定"><input id="addClose" type="button" value="取消"></td>
							</tr>
						</tbody>
			</table>
	</div>
<!-- ************ -->

<!--刪除燈箱   -->
		<div id="lightbox_2">
			<table id="deleteLightbox" >
						<thead>
							<tr id="deleteHeadtr">
								<th class="deleteHeadth" colspan="2"><span id="deleteSpan">刪除班表</span></th>
							</tr>
						</thead>
						<tbody>
							<tr class="deleteBodytr">
								<td class="deleteBodytd">日期：<input name="hiredate" id="f_date2" type="text"></td>
							</tr>
							<tr class="deleteBodytr">
								<td class="deleteBodytd">班別：<select id="deleteClass"><option>請選擇</select></td>
							</tr>
							<tr class="deleteBodytr">
								<td class="deleteBodytd" colspan="2"><input id="deleteSubmit" type="submit" value="確定"><input id="deleteClose" type="button" value="取消"></td>
							</tr>
						</tbody>
			</table>
	</div>
	<!-- ************ -->
		
		<%@ include file="/back-end/includePage/Nav"%>
		<%@ include file="/back-end/includePage/LeftSide"%>
		<div class="col-lg-10 col-md-10 cb rightContent">


			<div id="nav" >   	
				<button class="nav" id="previous" style="width: 5%">◀</button>
				<button class="nav" id="now" style="width: 8%">今天</button>
		    	<div class="nav" id="year_month" style="width: 45%; font-size: 2rem">1970</div>
		    	<button class="nav" id="next" style="width: 5%">▶</button>
		    	<button class="nav" id="set" style="width: 12% "><img id="img_set"src="<%=request.getContextPath()%>/back-end/TSchedule/images/set.png">編輯</button>
		    	<button class="nav" id="delete" style="width: 12% "><img id="img_delete"src="<%=request.getContextPath()%>/back-end/TSchedule/images/delete.png">刪除</button>
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
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    </tr>
		    <tr id="row1" >
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    </tr>
		    <tr id="row2" >
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    </tr>
		    <tr id="row3" >
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    </tr>
		    <tr id="row4" >
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
			</tr>
			<tr id="row5" >
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
		    	<td class="box"><div class="date"></div><div class="class_sort"></div></td>
			</tr>
		
			</table>

					
					
						
		</div>
		<%@ include file="/back-end/includePage/BootStrap"%>
		
<%-- 		<script src="<%=request.getContextPath()%>/back-end/TSchedule/JS/indexTSchedule.js"></script> --%>
		
		
</body>

<script src="<%=request.getContextPath()%>/back-end/TSchedule/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/TSchedule/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  400px;   /* width:  300px; */
           background-color: white;
           
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }

</style>

	<script>
		/**************日期選擇器相關設定*******************************************************************/
		/**/		$.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value:   new Date(),
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
            //startDate:	            '2017/07/10',  // 起始日
            minDate:               '-1970-01-01', // 去除今日(不含)之前
            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
         });		
        
        $('#f_date2').datetimepicker({
  	       theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value:   new Date(),
             //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
             //startDate:	            '2017/07/10',  // 起始日
             minDate:               '-1970-01-01', // 去除今日(不含)之前
             //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
          });		
		/**********************************************************************************************/
	</script>
	
	
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

//置入Add icon    				//當天的毫秒<每天的毫秒
//if(($("#set").text()=="完成") && (today_LongTime < longTime)){					
//class_sorts[i].innerHTML = "<img class=img_add src=images/add.png>";					
//}
		
		}
		
//add按下
//$(".img_add").click(function(){
//alert(666);
//});


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
	
	function WebSocketConnect(){
		webSocket = new WebSocket(URL);
		
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			var action = jsonObj.action;
			if(action == "add"){
				var yy = parseInt(jsonObj.ts_date.substring(0,4));
				var mm = parseInt(jsonObj.ts_date.substring(5,7));
				
// 				console.log(mm)
// 				console.log(month)
				jsonObj.ts_date
				toastr.info('新增了 ' + jsonObj.ts_date +' 班表資料!')
				if((yy == year) && (mm == (month +1))){
					placeDate(jsonObj);
					;
// 					Swal.fire(
// 					 	 '正確',
// 					 	 '刪除成功',
// 					 	 'success'
// 					)
				}
				
			} else if(action == "update"){
				updateWebSocket(jsonObj);
				toastr.info('修改了  '+ jsonObj.ts_date + ' 班表資料!');
			} else if(action == "delete"){
				
				deleteWebSocket(jsonObj);
				toastr.info('移除了  '+ jsonObj.ts_date + ' 班表資料!');
			}
		}
	};
	
	
	
	
	
	
	
	
	
	
	//Ajax取得每月資料
	function getDate(year, month){

		$.ajax({
			
			type:"POST",
/*注意路徑*/		url:"<%=request.getContextPath()%>/schedule/schedule.do",
			data:{"action":"getAll","year":year,"month":month},
			dataType:"json",
			success:function(data){
				$(data).each(function(i,item){
						placeDate(item);
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
	
	function creatQueryString(value){
		var queryString = {"action":"addDateChange","changeDate":value};
		return queryString;
		
	}
	
	function updateWebSocket(item){
		var t_name = item.t_name;
		var on_duty_cs = item.on_duty_cs;
		var ts_date = parseInt(item.ts_date.substring(8,10));
		for(var i =0; i < dates.length; i++){
			if($('.date').eq(i).html() == ts_date){
				var arr = $('.class_sort').eq(i).find('span');
				for(var j = 0; j < arr.length; j++){
					if((arr[j].innerHTML.substring(0,2) == on_duty_cs) && (arr[j].innerHTML.substring(3,arr[j].innerHTML.length) != t_name)){
						arr[j].innerHTML = on_duty_cs + " " + t_name;
						
// 						alert(arr[j].innerHTML.substring(3,arr[j].innerHTML.length));
					}
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
	
	
	
	function selectTeacher(item){
		
		var t_num = item.t_num;
		var t_name = item.t_name;
		var selTeachers = $('.selTeacher');
		for(var i =0; i < selTeachers.length; i++){
			$('.selTeacher').eq(i).append('<option value="' + t_num +'">' + t_name);
				
		}
	}

	function selectClass(item){
		var on_duty_num = item.on_duty_num;
		var on_duty_cs = item.on_duty_cs;
		var selClasses = $('.selClass');
		for(var i =0; i < selClasses.length; i++){
			$('.selClass').eq(i).append('<option value="' + on_duty_num +'">' + on_duty_cs);
		}

	}
	
	
	
	
	
	
	function dailySchedule(i,item){
		var t_name = item.t_name;
		var on_duty_cs = item.on_duty_cs;
		var t_num = item.t_num;
		var on_duty_num = item.on_duty_num;
		$('.selTeacher').eq(i).append('<option value="' + t_num +'">' + t_name);
		$('.selClass').eq(i).append('<option value="' + on_duty_num +'">' + on_duty_cs);
	}
	
	
	
	
	//清空select
	function clearSelect(){
		$('.selTeacher').empty().append("<option value='-1' selected>請選擇</option>");
		$('.selClass').empty().append("<option value='-1' selected>請選擇</option>");
	}
	
	
	//選項刪除以選過的老師
	function selectTeacher_2(item){
		var t_num = item.t_num;
		var t_name = item.t_name;
		var selTeachers = $('.selTeacher');
		for(var i = 0; i < selTeachers.length ; i++){
			if($('.selTeacher').eq(i).val() == -1){
				$('.selTeacher').eq(i).append('<option value="' + t_num +'">' + t_name);
			}
		}
		
	}
	
	//選項刪除以選過的班別
	function selectClass_2(item){
		var on_duty_num = item.on_duty_num;
		var on_duty_cs = item.on_duty_cs;
		var selClasses = $('.selClass');
		for(var i = 0; i < selClasses.length ; i++){
			if($('.selClass').eq(i).val() == -1){
				$('.selClass').eq(i).append('<option value="' + on_duty_num +'">' + on_duty_cs);
			}
		}
		
	}
	
	
	function defaultSchedule(teacher,onduty,schedule){
		if(schedule.length == 4){
			$(teacher).each(function(i,item){
				var t_num = item.t_num;
				var t_name = item.t_name;
				$('.selTeacher').eq(0).append('<option value="' + t_num +'"' + (t_num == schedule[0].t_num ? "selected" : "") + '>' + t_name);
				$('.selTeacher').eq(1).append('<option value="' + t_num +'"' + (t_num == schedule[1].t_num ? "selected" : "") + '>' + t_name);
				$('.selTeacher').eq(2).append('<option value="' + t_num +'"' + (t_num == schedule[2].t_num ? "selected" : "") + '>' + t_name);
				$('.selTeacher').eq(3).append('<option value="' + t_num +'"' + (t_num == schedule[3].t_num ? "selected" : "") + '>' + t_name);
						
			});
			$(onduty).each(function(i,item){
				var on_duty_num = item.on_duty_num;
				var on_duty_cs = item.on_duty_cs;
				$('.selClass').eq(0).append('<option value="' + on_duty_num +'"' + (on_duty_num == schedule[0].on_duty_num ? "selected" : "") + '>' + on_duty_cs);
				$('.selClass').eq(1).append('<option value="' + on_duty_num +'"' + (on_duty_num == schedule[1].on_duty_num ? "selected" : "") + '>' + on_duty_cs);
				$('.selClass').eq(2).append('<option value="' + on_duty_num +'"' + (on_duty_num == schedule[2].on_duty_num ? "selected" : "") + '>' + on_duty_cs);
				$('.selClass').eq(3).append('<option value="' + on_duty_num +'"' + (on_duty_num == schedule[3].on_duty_num ? "selected" : "") + '>' + on_duty_cs);
			});
		}else if(schedule.length == 3){
			$(teacher).each(function(i,item){
				var t_num = item.t_num;
				var t_name = item.t_name;
				$('.selTeacher').eq(0).append('<option value="' + t_num +'"' + (t_num == schedule[0].t_num ? "selected" : "") + '>' + t_name);
				$('.selTeacher').eq(1).append('<option value="' + t_num +'"' + (t_num == schedule[1].t_num ? "selected" : "") + '>' + t_name);
				$('.selTeacher').eq(2).append('<option value="' + t_num +'"' + (t_num == schedule[2].t_num ? "selected" : "") + '>' + t_name);
				$('.selTeacher').eq(3).append('<option value="' + t_num +'">' + t_name);
						
			});
			$(onduty).each(function(i,item){
				var on_duty_num = item.on_duty_num;
				var on_duty_cs = item.on_duty_cs;
				$('.selClass').eq(0).append('<option value="' + on_duty_num +'"' + (on_duty_num == schedule[0].on_duty_num ? "selected" : "") + '>' + on_duty_cs);
				$('.selClass').eq(1).append('<option value="' + on_duty_num +'"' + (on_duty_num == schedule[1].on_duty_num ? "selected" : "") + '>' + on_duty_cs);
				$('.selClass').eq(2).append('<option value="' + on_duty_num +'"' + (on_duty_num == schedule[2].on_duty_num ? "selected" : "") + '>' + on_duty_cs);
				$('.selClass').eq(3).append('<option value="' + on_duty_num +'">' + on_duty_cs);
			});
		}else if(schedule.length == 2){
			$(teacher).each(function(i,item){
				var t_num = item.t_num;
				var t_name = item.t_name;
				$('.selTeacher').eq(0).append('<option value="' + t_num +'"' + (t_num == schedule[0].t_num ? "selected" : "") + '>' + t_name);
				$('.selTeacher').eq(1).append('<option value="' + t_num +'"' + (t_num == schedule[1].t_num ? "selected" : "") + '>' + t_name);
				$('.selTeacher').eq(2).append('<option value="' + t_num +'">' + t_name);
				$('.selTeacher').eq(3).append('<option value="' + t_num +'">' + t_name);
						
			});
			$(onduty).each(function(i,item){
				var on_duty_num = item.on_duty_num;
				var on_duty_cs = item.on_duty_cs;
				$('.selClass').eq(0).append('<option value="' + on_duty_num +'"' + (on_duty_num == schedule[0].on_duty_num ? "selected" : "") + '>' + on_duty_cs);
				$('.selClass').eq(1).append('<option value="' + on_duty_num +'"' + (on_duty_num == schedule[1].on_duty_num ? "selected" : "") + '>' + on_duty_cs);
				$('.selClass').eq(2).append('<option value="' + on_duty_num +'">' + on_duty_cs);
				$('.selClass').eq(3).append('<option value="' + on_duty_num +'">' + on_duty_cs);
			});
		} else{
			$(teacher).each(function(i,item){
				var t_num = item.t_num;
				var t_name = item.t_name;
				$('.selTeacher').eq(0).append('<option value="' + t_num +'"' + (t_num == schedule[0].t_num ? "selected" : "") + '>' + t_name);
				$('.selTeacher').eq(1).append('<option value="' + t_num +'">' + t_name);
				$('.selTeacher').eq(2).append('<option value="' + t_num +'">' + t_name);
				$('.selTeacher').eq(3).append('<option value="' + t_num +'">' + t_name);
						
			});
			$(onduty).each(function(i,item){
				var on_duty_num = item.on_duty_num;
				var on_duty_cs = item.on_duty_cs;
				$('.selClass').eq(0).append('<option value="' + on_duty_num +'"' + (on_duty_num == schedule[0].on_duty_num ? "selected" : "") + '>' + on_duty_cs);
				$('.selClass').eq(1).append('<option value="' + on_duty_num +'">' + on_duty_cs);
				$('.selClass').eq(2).append('<option value="' + on_duty_num +'">' + on_duty_cs);
				$('.selClass').eq(3).append('<option value="' + on_duty_num +'">' + on_duty_cs);
			});
		}
	
	}
	
	
	
	function not_defaultSchedule(teacher,onduty){
		
		$(teacher).each(function(i,item){
			var t_num = item.t_num;
			var t_name = item.t_name;
			$('.selTeacher').eq(0).append('<option value="' + t_num +'">' + t_name);
			$('.selTeacher').eq(1).append('<option value="' + t_num +'">' + t_name);
			$('.selTeacher').eq(2).append('<option value="' + t_num +'">' + t_name);
			$('.selTeacher').eq(3).append('<option value="' + t_num +'">' + t_name);
					
		});
		
		$(onduty).each(function(i,item){
			var on_duty_num = item.on_duty_num;
			var on_duty_cs = item.on_duty_cs;
			$('.selClass').eq(0).append('<option value="' + on_duty_num +'">' + on_duty_cs);
			$('.selClass').eq(1).append('<option value="' + on_duty_num +'">' + on_duty_cs);
			$('.selClass').eq(2).append('<option value="' + on_duty_num +'">' + on_duty_cs);
			$('.selClass').eq(3).append('<option value="' + on_duty_num +'">' + on_duty_cs);
		});
	}
	
	
	
	
	
	
	
	//清空沒選的teacher select
	function clearTeacherSelect(){
		var selTeachers = $('.selTeacher');
		for(var i = 0; i < selTeachers.length ; i++){
			if($('.selTeacher').eq(i).val() == -1){
				$('.selTeacher').eq(i).empty().append("<option value='-1'>請選擇</option>");
			}
		}
		
	}
	
	//清空沒選的class select
	function clearClassSelect(){
		var selClasses = $('.selClass');
		for(var i = 0; i < selClasses.length ; i++){
			if($('.selClass').eq(i).val() == -1){
				$('.selClass').eq(i).empty().append("<option value='-1'>請選擇</option>");
			}
		}
	}
	
	

		
	//編輯日期改變
	function dateChange(){
		
		$.ajax({
			type: "POST",
			url: "<%=request.getContextPath()%>/schedule/schedule.do",
			data: creatQueryString($('#f_date1').val()),
			dataType: "json",
			success:function(data){
				clearSelect();
// 				console.log(data[2])
				if(data.length ==2){
					not_defaultSchedule(data[0],data[1])
				}else {
					defaultSchedule(data[0],data[1],data[2]);
				}
				
			},
// 			error: function(){alert("AJAX-grade發生錯誤囉!")}
				
			});
	
	
	
	
	
	
	}
	
	//老師值改變
	function teacherChange_1(){
		$.ajax({
			type: "POST",
			url: "<%=request.getContextPath()%>/schedule/schedule.do",
			data: {"action":"teacherChange","teacherChange_1":$('.selTeacher').eq(0).val(),
				"teacherChange_2":$('.selTeacher').eq(1).val(),"teacherChange_3":$('.selTeacher').eq(2).val(),
				"teacherChange_4":$('.selTeacher').eq(3).val()},
			dataType: "json",
			async: false,
			success:function(data){
	// 			console.log(data)
				clearTeacherSelect();
				$(data).each(function(i,item){
					selectTeacher_2(item);
				});
		},
//		error: function(){alert("AJAX-grade發生錯誤囉!")}
			
		});
	}
	
	
	
	//班別值改變
	function classChange_1(){
		$.ajax({
			type: "POST",
			url: "<%=request.getContextPath()%>/schedule/schedule.do",
			data: {"action":"classChange","classChange_1":$('.selClass').eq(0).val(),
				"classChange_2":$('.selClass').eq(1).val(),"classChange_3":$('.selClass').eq(2).val(),
				"classChange_4":$('.selClass').eq(3).val()},
			dataType: "json",
			async: false,
			success:function(data){
// 			console.log(data)
			clearClassSelect();
			$(data).each(function(i,item){
				selectClass_2(item);
			});
		},
		error: function(){alert("AJAX-grade發生錯誤囉!")}
			
		});
	}
	
	
	
	function submit(){
		
		var jsonObj = {"action":"addSubmit","teacherChange_1":$('.selTeacher').eq(0).val(),
				"teacherChange_2":$('.selTeacher').eq(1).val(),"teacherChange_3":$('.selTeacher').eq(2).val(),
				"teacherChange_4":$('.selTeacher').eq(3).val(),"onDutyChange_1":$('.selClass').eq(0).val(),
				"onDutyChange_2":$('.selClass').eq(1).val(),"onDutyChange_3":$('.selClass').eq(2).val(),
				"onDutyChange_4":$('.selClass').eq(3).val(),"selectDate":$('#f_date1').val()};
		
		webSocket.send(JSON.stringify(jsonObj));
		clearSelect();
		
	}
	
	//刪除日期改變
	function deleteDateChange(value){
			$('#deleteClass').empty().append("<option value='-1'>請選擇</option>");
		$.ajax({
			type: "POST",
			url: "<%=request.getContextPath()%>/schedule/schedule.do",
			data: {"action":"deleteDateChange","deleteDateChange":value},
			dataType: "json",
			async: false,
			success:function(data){
	// 			console.log(data)
				$(data).each(function(i,item){
					var on_duty_cs = item.on_duty_cs;
					var ts_num = item.ts_num;
					$('#deleteClass').append('<option value="' + ts_num +'">' + on_duty_cs);
				});
		},
//		error: function(){alert("AJAX-grade發生錯誤囉!")}
		});
	}
	
	//刪除送出
	function deleteSubmit(value){
		
		var jsonObj = {"action":"deleteSubmit","ts_num":value};
		
		webSocket.send(JSON.stringify(jsonObj));
		$('#deleteClass').empty().append("<option value='-1'>請選擇</option>");
		
		
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
			
			//編輯
			$("#set").click(function(){
				    
				$('div#lightbox').css('z-index', '100');
				  
//				clear();
//					now();
//					getDate(year, month);
			});
			//關閉新增頁面
			$('#addClose').on('click',function(){
				$('div#lightbox').css('z-index', '0');
				clearSelect();
			});

			
			//刪除
			$("#delete").click(function(){
				$('div#lightbox_2').css('z-index', '100');
				  
			});
			//關閉刪除頁面
			$('#deleteClose').on('click',function(){
				$('div#lightbox_2').css('z-index', '0');
				clearSelect();
			});
			//刪除日期選擇器值改變
			$('#f_date2').change(function(){
				deleteDateChange($(this).val());
			});
			//刪除送出
			$('#deleteSubmit').on('click',function(){
				deleteSubmit($('#deleteClass').val());
			});
			
			
			
			
			
			//編輯日期選擇器值改變
			$('#f_date1').change(function(){			
				dateChange();
			});
			
			
			
			//老師值改變
			$('.selTeacher').eq(0).change(function(){
				teacherChange_1();
			});
			$('.selTeacher').eq(1).change(function(){
				teacherChange_1();
			});
			$('.selTeacher').eq(2).change(function(){
				teacherChange_1();
			});
			$('.selTeacher').eq(3).change(function(){
				teacherChange_1();
			});
			
			
			//班別值改變
			$('.selClass').eq(0).change(function(){
				classChange_1();
			});
			$('.selClass').eq(1).change(function(){
				classChange_1();
			});
			$('.selClass').eq(2).change(function(){
				classChange_1();
			});
			$('.selClass').eq(3).change(function(){
				classChange_1();
			});
			
			
			
			$('#addSubmit').on('click',function(){
				submit();
			});
			
			
			
			
			
	});
	
	$(window).unload(function(){
		webSocket.close();
	});
	
	
	</script>
</html>