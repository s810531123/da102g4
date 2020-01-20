<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/includePage/Head"%>
<!-- sweet alert .css & .js cdn -->
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/course/datetimepicker/jquery.datetimepicker.css" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>




<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/arrive_leave_time/css/arrive_leave_time.css">
<title>Insert title here</title>

<style type="text/css">
.xdsoft_date{
	padding:0px;
}

</style>
</head>
<jsp:useBean id="stSvc" class="com.student.model.StudentService"/>
<jsp:useBean id="classSvc" class="com._class.model.ClassService"/>
<body class="body">
		<%@ include file="/back-end/includePage/Nav"%>
		<%@ include file="/back-end/includePage/LeftSide"%>
		<div class="col-lg-10 col-md-10 cb rightContent">
			<div class="own">
				
				<!-- ********************************************** -->
				
				<!-- =============搜尋列表============ -->
					
					
					
<%-- 						 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/course.do" > --%>
						 <div class="col-lg-10 row">
						 <div class="col-lg-3">
						    
						        <b>班級名稱:</b>
						       <select size="1" name="cs_num" class="cs_num">
						       <option value="0">請選擇
         						<c:forEach var="classVO" items="${classSvc.all}" > 
          							<option value="${classVO.cs_num}">${classVO.cs_name}
        						 </c:forEach>   
       							</select>				
						  </div>						   
						  	<div class="col-lg-3">		    
						       <b>學生姓名:</b>
						       <select size="1" name="st_num" id="selectSt">
						       	  <option value="-1">請選擇					  		          
						       </select>			   
						  </div>				  
						 
						 
						  </div>
<!-- 						</form> -->
						  
					
			<div class="lightbox1 col-lg-10">
				<div class="col-lg-10">
					<div class="row" style="font-size:20px;font-weight:800;border:1px;"><div class="col-lg-2">學生姓名</div><div class="col-lg-4">到校時間</div><div class="col-lg-4">離校時間</div> </div>
						<div class="inneralt">
							
						</div>
					<button id="close" class="btn">取消</button>
				</div>
			</div>			 
					
					
					
					<!-- =============================== -->
<!-- 					<input type="text" class="form-control" id="account"> -->
<table border="1" cellspacing="0" class="calendar">
   		<tr><th class="head" id="down"> << </th> <th class="head" >  </th> <th class="head" >  </th> <th class="head"> </th> <th class="head">  </th> <th class="head" >  </th>  
   			<th class="head" id="up"> >> </th> </tr>
		<tr><th class="days">日</th><th class="days">一</th><th class="days">二</th><th class="days">三</th><th class="days">四</th><th class="days">五</th><th class="days">六</th></tr>

		<tr>  <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>
		<tr>  <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>
		<tr>  <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>
		<tr>  <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>
		<tr>  <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>
		<tr>  <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>

   	</table>




				<!-- ********************************************** -->
								
			</div>
		</div>
		<%@ include file="/back-end/includePage/BootStrap"%>
		
		
		
		
<script src="<%=request.getContextPath()%>/back-end/course/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/course/datetimepicker/jquery.datetimepicker.full.js"></script>
		
		<script type="text/javascript">

		var a = new Date(); //先建立一個Date物件
   		var getyear = a.getFullYear(); //取得現在的年
   		var getmonth = a.getMonth();   //取得現在的月 (取得的月份會比實際月份-1)
   		var getday;   //宣告變數
         var td = document.getElementsByTagName("td");
   		function init(){

	   		var b = new Date(getyear,getmonth+1,1); //建立b Date 物件 (取得下一個月的第一天)
	   		b.setHours(b.getHours()-2); //取得下個月的第一天 時間為0點0分所以減掉2個小時就是這個月的總天數
	   		var totalday = b.getDate(); //ex: 取得2019/07/01 00:00分 減 兩小時就是 2019/06/30 22:00分
	                                    //再用Date 取得日期就可以得到這一個月的總天數
	   		var c = new Date(getyear,getmonth,1); //建立c Date 物件 
	   		getday = c.getDay();  //傳回當月的第一天是星期幾
	   		document.getElementsByTagName("th")[2].innerHTML = getyear + ' 年  ' ;//在畫面上顯示年分
	   		document.getElementsByTagName("th")[4].innerHTML = getmonth+1 + ' 月';//在畫面上顯示月份
	   		var day=1; //日期
	   		for(var i=getday;i<getday+totalday;i++){ //getday 76行;總天數+星期幾就是當月份td的總長度
	   		
	   			$(".calendar td:eq("+i+")").html(day);
// 	   			document.getElementsByTagName("td")[i].innerHTML = day;//讓表格印上數字(day)日期
				$(".calendar td:eq("+i+")").onmouseover = mouselight;
				$(".calendar td:eq("+i+")").onmouseout = mousenormal;
// 	   			document.getElementsByTagName("td")[i].onmouseover = mouselight; //順便在這裡註冊事件
// 	   			document.getElementsByTagName("td")[i].onmouseout = mousenormal; //同上
	   			day++; // (日期)1號~2號~3號 ~~~30號~31號  依此類推
//                td[i].onclick = gogo;
	   		}
	   		
	   		//如果年份還有月分跟今天一樣就把今天的日期底色變成藍色
	   		if(a.getMonth() == getmonth && a.getFullYear() == getyear){
	   			$(".calendar td:eq("+(a.getDate()+getday-1)+")").css("background-color","#00ffff");
// 	   			document.getElementsByTagName("td")[a.getDate()+getday-1].style.backgroundColor = "#00ffff";
	   		}
	   		document.getElementById("up").onclick = changeup; //當>>被按下
	   		document.getElementById("down").onclick = changedown; //當<<被按下
}     
   
      function gogo(e){
         alert("GOGO");
      }

		// 滑鼠移入移出可以亮或暗
   		function mouselight(e){
   			e.target.style.color = "gold";
   		}
   		function mousenormal(e){
   			e.target.style.color = "black";
   		}

   		// 按下>>時可以跳下一個月
   		function changeup(){
   			for(var i=0;i<42;i++){ //按下<<時把表格內所有數字清空,並把所有背景變回白色
   				$(".calendar td:eq("+i+")").html("");
   				$(".calendar td:eq("+i+")").css("background-color","rgba(170,230,230,0.2)");
//    				document.getElementsByTagName("td")[i].innerHTML = "";
//    				document.getElementsByTagName("td")[i].style.backgroundColor ="rgba(170,230,230,0.2)";
   			}
   			getmonth++;//把月份+1
   			if(getmonth > 11){ //如果月份大於11 讓月份重新回到0
   				getmonth = 0;  //月份是從0-11 
   				getyear++;   //會讓月份回到0表示多了一年
   			}
   			//顯示月份的欄位更新  +1是因為月份從0開始所以顯示出來要加1
   			document.getElementsByTagName("th")[4].innerHTML = getmonth+1 + ' 月';
   			//顯示年份的欄位更新
   			document.getElementsByTagName("th")[2].innerHTML = getyear + ' 年  ' ;
   			init();//跳回主程式繼續執行
   		}

   		// 按下<<時可以跳上一個月
   		function changedown(){
   			for(var i=0;i<42;i++){  //按下<<時把表格內所有數字清空,並把所有背景變回白色
   				$(".calendar td:eq("+i+")").html("");
   				$(".calendar td:eq("+i+")").css("background-color","rgba(170,230,230,0.2)");
//    				document.getElementsByTagName("td")[i].innerHTML = "";
//    				document.getElementsByTagName("td")[i].style.backgroundColor ="rgba(170,230,230,0.2)";
   			}
   			getmonth--; //把月份-1
   			if(getmonth < 0){ //如果月份小於0 讓月份重新回到11
   				getmonth = 11; //月份是從0-11 
   				getyear--;     //會讓月份回到11表示少了一年
   			}
   			//顯示月份的欄位更新  +1是因為月份從0開始所以顯示出來要加1
   			document.getElementsByTagName("th")[4].innerHTML = getmonth+1 + ' 月'; 
   			//顯示年份的欄位更新
   			document.getElementsByTagName("th")[2].innerHTML = getyear + ' 年  ' ;
   			init(); //跳回主程式繼續執行
   		}

   		window.onload = init;
   		
   		<%--*******************動態選單*************************--%>
   		
   		$(".cs_num").change(function() {
   			var cs_num = $(".cs_num").val();
   			
   		 $.ajax({
       		 type: "POST",
       		 url: "<%=request.getContextPath()%>/ArriveLeaveTime/ArriveLeaveTime.do",
       		 data: {action : "selectStNameBar" ,"cs_num": cs_num},
       		 dataType: "json",
       		 async: true, // true預設是非同步，也就是"不會等待"; false是同步，"會等待"
       		 success: function (data){
       			clearSelect();
       			$.each(data, function(i, item){
					 $('#selectSt').append("<option value='"+data[i].st_num+"'>"+data[i].st_name+"</option>");
				 });		
       	     },
              error: function(){alert("AJAX-class發生錯誤囉!")}
          })
   			
   		})
   		
   		function clearSelect(){
			$('#selectSt').empty();
			$('#selectSt').append("<option value='-1'>請選擇</option>");
		}
   		
   		<%--*******************動態選單^^*************************--%>

   		<%--*******************查詢學生到離校時間VV*************************--%>
   		
   		$("#selectSt").change(function() {
   			init();
   			var st_num = $("#selectSt").val();
   			if(st_num != -1) {
   				$.ajax({
   	       		 type: "POST",
   	       		 url: "<%=request.getContextPath()%>/ArriveLeaveTime/ArriveLeaveTime.do",
   	       		 data: {action : "selectStArriveLeaveTime" ,"st_num": st_num , "month" : $("th:eq(4)").text() ,"year" : $("th:eq(2)").text()},
   	       		 dataType: "json",
   	       		 async: true, // true預設是非同步，也就是"不會等待"; false是同步，"會等待"
   	       		 success: function (data){
   	       			 console.log("success")
   	       			 for(var j=0;j<=42;j++) {
   	       				 var dd = $(".calendar td:eq("+j+")").text();
   	       				 var d = dd.substring(0,2).trim();
   	       				 if((d - 10) >= 0 ) {
   	       					 
   	       				 }else{
   	       					d = dd.substring(0,1).trim();
   	       				 }
   	       				 
	   	       			$.each(data, function(i, item){
// 	   	       				console.log(data[i].day)
		   	       			if(data[i].day == d) {
		   	       				$(".calendar td:eq("+j+")").html(d + " " +"<br>到:"+ data[i].arrTime +"<br>離:"+ data[i].leaTime);	
	  	       				 }
	  	       					
	   					 });		 
   	       			 }
   	       	     },
   	              error: function(){alert("AJAX-class發生錯誤囉!")}
   	          })		
   			}	
   		})
   		<%--*******************查詢學生到離校時間^^*************************--%>
   		
   		<%--**************************************************************************************************************--%>
   		
   		<%--******************* << click 查詢學生到離校時間VV*************************--%>
   		
   		$("#down").click(function() {
   			var st_num = $("#selectSt").val();
   			if(st_num != -1) {
   				
   				$.ajax({
   	       		 type: "POST",
   	       		 url: "<%=request.getContextPath()%>/ArriveLeaveTime/ArriveLeaveTime.do",
   	       		 data: {action : "selectStArriveLeaveTimeDOWN" ,"st_num": st_num , "month" : $("th:eq(4)").text(),"year":$("th:eq(2)").text()},
   	       		 dataType: "json",
   	       		 async: true, // true預設是非同步，也就是"不會等待"; false是同步，"會等待"
   	       		 success: function (data){

   	       			 console.log("success")
   	       			 for(var j=0;j<=42;j++) {
   	       				 var d = $(".calendar td:eq("+j+")").text();
	   	       			$.each(data, function(i, item){
// 	   	       				console.log(data[i].day)
		   	       			if(data[i].day == d) {
		   	       			console.log(data[i].month)
		   	       					$(".calendar td:eq("+j+")").html(d +"<br>到:"+ data[i].arrTime +"<br>離:"+ data[i].leaTime);			
	  	       				 }	
	   					 });		 
   	       			 }
   	       	     },
   	              error: function(){alert("AJAX-class發生錯誤囉!")}
   	          })		
   			}	
   		})
   		
   		<%--******************* << click 查詢學生到離校時間c ^^***********************--%>
   		
   		<%--**************************************************************************************************************--%>
   		
		<%--******************* >> click 查詢學生到離校時間VV*************************--%>
   		
		$("#up").click(function() {
   			var st_num = $("#selectSt").val();
   			
   			if(st_num != -1) {
   				
   				$.ajax({
   	       		 type: "POST",
   	       		 url: "<%=request.getContextPath()%>/ArriveLeaveTime/ArriveLeaveTime.do",
   	       		 data: {action : "selectStArriveLeaveTimeUP" ,"st_num": st_num , "month" : $("th:eq(4)").text(),"year" : $("th:eq(2)").text()},
   	       		 dataType: "json",
   	       		 async: true, // true預設是非同步，也就是"不會等待"; false是同步，"會等待"
   	       		 success: function (data){

   	       			 console.log("success")
   	       			 for(var j=0;j<=42;j++) {
   	       				 var d = $(".calendar td:eq("+j+")").text();
	   	       			$.each(data, function(i, item){

		   	       			if(data[i].day == d) {	
		   	       					$(".calendar td:eq("+j+")").html(d +"<br>到:"+ data[i].arrTime +"<br>離:"+ data[i].leaTime);				
	  	       				 }	
	   					 });		 
   	       			 }
   	       	     },
   	              error: function(){alert("AJAX-class發生錯誤囉!")}
   	          })		
   			}	
   		})
		
		
   		<%--******************* >> click 查詢學生到離校時間c ^^***********************--%>

   		<%--****************** 點日期跳出當天所有學生到離校時間*******************--%>
   		
   		$(".calendar td").click(function() {
   			var t = $(this);
   			var cs_num = $(".cs_num").val();
   			var st_num = $("#selectSt").val();
   			var day = $(this).text();
   			console.log(day.length);
   			var day1 = $(this).html();
   			var day2 = $(this).text();
   			if(cs_num != 0 && st_num == -1) {
	   			$(".lightbox1").show(1000);
	   			
	   			
	   			$.ajax({
	  	       		 type: "POST",
	  	       		 url: "<%=request.getContextPath()%>/ArriveLeaveTime/ArriveLeaveTime.do",
	  	       		 data: {action : "selectAllStOnDay" , "month" : $("th:eq(4)").text(),"year" : $("th:eq(2)").text(),"day":day ,"cs_num":cs_num},
	  	       		 dataType: "json",
	  	       		 async: true, // true預設是非同步，也就是"不會等待"; false是同步，"會等待"
	  	       		 success: function (data){
	  	       			 clearinneralt();
	  	       			 console.log(data)
	  	       			 console.log("success")
		   	       			$.each(data, function(i, item){	
			   	       			$(".inneralt").append("<div class=\"row high\" style=\"line-height:20px;border:1px solid black;\"> <div class=\"col-lg-2\">" + data[i].st_name + "</div> <div class=\"col-lg-4\">" + data[i].arrTime + "</div> <div class=\"col-lg-4\">" +  data[i].leaTime + "</div></div>");				     				 	
		   					 });		  
	  	       	     },
	  	              error: function(){alert("AJAX-class發生錯誤囉!")}
	  	          })		
   			}else if(cs_num == 0){
//    				alert("請先選擇班級")			
   				swal({
   		            title: "請先選擇班級",
   		            type: "question", // type can be "success", "error", "warning", "info", "question"
   		            showCancelButton: true,
   		        })
   			}else if(cs_num != 0 && st_num != -1 && day.length > 2){
   				
   				swal({
	    			title: '修改到離校時間 ',
	    			html:
		    			'<form>' +
		    			  '<div class="form-group">' +
		    			    '<label for="account" class="pull-left">到校時間：</label>' +
		    			    
		    			    '<input type="time" step="1" class="form-control" id="arr">' +
		    			  '</div>' +   
		    			  '<div class="form-group">' +
		    			    '<label for="password" class="pull-left">離校時間：</label>' +
		    			    '<input type="time" step="1" class="form-control" id="lea">' +
		    			  '</div>' +
		    			'</form>',
		    			
	    			type: "warning",
	    			preConfirm: function () {
	    				
                    return new Promise(function (resolve, reject) {
                        var data = {};
                        data.action ="sweetAlert";
//                         $('#account').val() = "";
//                         $('#password').val() = "";
                        data.account = $('#arr').val().trim();
                        data.password = $('#lea').val().trim();
                        console.log($('#arr').val())
                    	if(data.account.length > 0 || data.password.length > 0) {	
                    		$.ajax({
                   				 type: "post",
                   				 url: "<%= request.getContextPath()%>/ArriveLeaveTime/ArriveLeaveTime.do",
                   				 data: {action : "update" , "st_num" : st_num , "arr" : data.account , "lea" : data.password , "month" : $("th:eq(4)").text(),"year" : $("th:eq(2)").text(),"day":day },
                   				 dataType: "json",
                   				 success: function (result){
                   					
                   					console.log(result.lea);
                   					
                   					t.html(result.day +"<br>到:"+ result.arr +"<br>離:"+ result.lea);
                   					
                   					resolve(data)
                   			     },
                   	             error: function(){
                   	            	 	reject('AJAX發生錯誤囉!');
                   	            	 }
                   	        });
                    	}	
                    })
                },
                onOpen: function () {

                	$.ajax({
          				 type: "post",
          				 url: "<%= request.getContextPath()%>/ArriveLeaveTime/ArriveLeaveTime.do",
          				 data: {action : "updateCreate" ,"day2":day2},
          				 dataType: "json",
          				 success: function (result){
          					
          					console.log(result.lea);
          					
          					$("#arr").val(result.arr);
          					$("#lea").val(result.lea);
          					
          					
          			     },
          	             error: function(){
          	            	 	reject('AJAX發生錯誤囉!');
          	            	 }
          	        });
                    
                },
	    		}).then(function (data) {
	    			if (data) {
		    			swal({
		    		    		type: 'success',
		    		    		title: '儲存成功',
		    		   		html: 
		    		   			'<b>輸入的到校時間是：</b>' + data.account + '<br>' +
		    		   			'<b>輸入的離校時間是：</b>' + data.password,
		    		    })
	    			}
	    		}).catch(swal.noop);
   				
   				<%-- 新增到離校時間  --%>
   			}else if(cs_num != 0 && st_num != -1 && day.length <= 2) {
   				
   				swal({
	    			title: '新增到離校時間 ',
	    			html:
		    			'<form>' +
		    			  '<div class="form-group">' +
		    			    '<label for="account" class="pull-left">到校時間：</label>' +
		    			    
		    			    '<input type="time" step="1" class="form-control" id="arr" value="">' +
		    			  '</div>' +
		    			  '<div class="form-group">' +
		    			    '<label for="password" class="pull-left">離校時間：</label>' +
		    			    '<input type="time" step="1" class="form-control" id="lea" value="">' +
		    			  '</div>' +
		    			'</form>',	
	    			type: "warning",
	    			preConfirm: function () {
                    return new Promise(function (resolve, reject) {
                        var data = {};
                        data.action ="sweetAlert";
//                         $('#account').val() = "";
//                         $('#password').val() = "";
                        data.account = $('#arr').val().trim();
                        data.password = $('#lea').val().trim();
                        console.log($('#arr').val())
                        console.log(data.account.length)
                        
                    	if(data.account.length >= 5 ) {	
                    		$.ajax({
                   				 type: "post",
                   				 url: "<%= request.getContextPath()%>/ArriveLeaveTime/ArriveLeaveTime.do",
                   				 data: {action : "insert" , "st_num" : st_num , "arr" : data.account , "lea" : data.password , "month" : $("th:eq(4)").text(),"year" : $("th:eq(2)").text(),"day":day },
                   				 dataType: "json",
                   				 success: function (result){
                   					
                   					console.log(result.lea);
                   					t.html("");
                   					t.html(day +"<br>到:"+ result.arr +"<br>離:"+ result.lea);
                   					
                   					resolve(data)
                   			     },
                   	             error: function(){
                   	            	 	reject('AJAX發生錯誤囉!');
                   	            	 }
                   	        });
                    	} else {
                    		swal({
                    			title: "請輸入到校時間",
                    	        //html: "確定後課程將上架",
                    	        type: "question", // type can be "success", "error", "warning", "info", "question"
                    	        showCancelButton: true,
                    	    		showCloseButton: true,
                    		})
                    	}	
                    })
                },
                onOpen: function () {

                    
                    
                },
	    		}).then(function (data) {
	    			if (data) {
		    			swal({
		    		    		type: 'success',
		    		    		title: '儲存成功',
		    		   		html: 
		    		   			'<b>輸入的到校時間是：</b>' + data.account + '<br>' +
		    		   			'<b>輸入的離校時間是：</b>' + data.password,
		    		    })
	    			}
	    		}).catch(swal.noop);
   				
   			}
   		})
   		
   		function clearinneralt(){
			$('.inneralt').empty();
			$('.inneralt').append("<div></div>");
		}
   		
   		$("#close").click(function() {
   			$(".lightbox1").hide(1000);
   		})
   		
   		
   		<%-- timepicker --%>
   		
   		$("td").click(function() {
   			
   			$.datetimepicker.setLocale('zh'); // kr ko ja en
   	        $("#account").datetimepicker({
   	           theme: '',          //theme: 'dark',
   	           timepicker: true,   //timepicker: false,
   	           step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
   		       format: 'Y-m-d H:i:s',
   		       value: new Date(),
   	           //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   	           //startDate:	        '2017/07/10',  // 起始日
   	           //minDate:           '-1970-01-01', // 去除今日(不含)之前
   	           //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
   	        });
   			
   		})
   		
   		$.datetimepicker.setLocale('zh'); // kr ko ja en
        $("#account").datetimepicker({
           theme: '',          //theme: 'dark',
           timepicker: true,   //timepicker: false,
           step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
	       format: 'Y-m-d H:i:s',
	       value: new Date(),
           //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	        '2017/07/10',  // 起始日
           //minDate:           '-1970-01-01', // 去除今日(不含)之前
           //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
        });
   		
   		
   		
   	</script>
		
		
		
</body>
</html>