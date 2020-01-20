<%@page import="com.student.model.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/front-end/Head"%>
<!-- sweet alert .css & .js cdn -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/arrive_leave_time/css/arrive_leave_time.css">
<title>Insert title here</title>
</head>
<jsp:useBean id="stSvc" class="com.student.model.StudentService"/>
<jsp:useBean id="classSvc" class="com._class.model.ClassService"/>
<body class="body">
		<%@ include file="/front-end/Nav"%>
		<%@ include file="/front-end/Top"%>  
		
		<div class="col-lg-10 col-md-10 cb rightContent m-auto">
			<div class="own">
				
				<!-- ********************************************** -->
				
					
					<% 
						StudentVO studentVO = (StudentVO) session.getAttribute("studentVO");%>
		
					
					<!-- =============================== -->
<input type="hidden" value="${stp.st_num}" id="st_num">
<table border="1" cellspacing="0">
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
		<%@ include file="/front-end/Footer"%>
		
		
		
		
		
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
	   			document.getElementsByTagName("td")[i].innerHTML = day;//讓表格印上數字(day)日期
	   			document.getElementsByTagName("td")[i].onmouseover = mouselight; //順便在這裡註冊事件
	   			document.getElementsByTagName("td")[i].onmouseout = mousenormal; //同上
	   			day++; // (日期)1號~2號~3號 ~~~30號~31號  依此類推
//                td[i].onclick = gogo;
	   		}
	   		
	   		//如果年份還有月分跟今天一樣就把今天的日期底色變成藍色
	   		if(a.getMonth() == getmonth && a.getFullYear() == getyear){
	   			document.getElementsByTagName("td")[a.getDate()+getday-1].style.backgroundColor = "#00ffff";
	   		}
	   		document.getElementById("up").onclick = changeup; //當>>被按下
	   		document.getElementById("down").onclick = changedown; //當<<被按下
	   		
	   		
	   		
	   		var st_num = $("#st_num").val();
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
   	       				 var dd = $("td:eq("+j+")").text();
   	       				 var d = dd.substring(0,2).trim();
   	       				 if((d - 10) >= 0 ) {
   	       					 
   	       				 }else{
   	       					d = dd.substring(0,1).trim();
   	       				 }
   	       				 
	   	       			$.each(data, function(i, item){
// 	   	       				console.log(data[i].day)
		   	       			if(data[i].day == d) {
		   	       				$("td:eq("+j+")").html(d + " " +"<br>到:"+ data[i].arrTime +"<br>離:"+ data[i].leaTime);	
	  	       				 }
	  	       					
	   					 });		 
   	       			 }
   	       	     },
   	              error: function(){alert("AJAX-class發生錯誤囉!")}
   	          })		
   			}	
	   		
	   		
	   		
	   		
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
   				document.getElementsByTagName("td")[i].innerHTML = "";
   				document.getElementsByTagName("td")[i].style.backgroundColor ="rgba(170,230,230,0.2)";
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
   				document.getElementsByTagName("td")[i].innerHTML = "";
   				document.getElementsByTagName("td")[i].style.backgroundColor ="rgba(170,230,230,0.2)";
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
   		
   		
		
			
			
			
		
		
		
   		
   		
   		<%--**************************************************************************************************************--%>
   		
   		<%--******************* << click 查詢學生到離校時間VV*************************--%>
   		
   		$("#down").click(function() {
   			var st_num = $("#st_num").val();
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
   	       				 var d = $("td:eq("+j+")").text();
	   	       			$.each(data, function(i, item){
// 	   	       				console.log(data[i].day)
		   	       			if(data[i].day == d) {
		   	       			console.log(data[i].month)
		   	       					$("td:eq("+j+")").html(d +"<br>到:"+ data[i].arrTime +"<br>離:"+ data[i].leaTime);			
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
   			var st_num = $("#st_num").val();
   			
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
   	       				 var d = $("td:eq("+j+")").text();
	   	       			$.each(data, function(i, item){
// 	   	       				console.log(data[i].day)
		   	       			if(data[i].day == d) {	
		   	       					$("td:eq("+j+")").html(d +"<br>到:"+ data[i].arrTime +"<br>離:"+ data[i].leaTime);				
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
   		
   		
   		
   		function clearinneralt(){
			$('.inneralt').empty();
			$('.inneralt').append("<div></div>");
		}
   		
   		$("#close").click(function() {
   			$(".lightbox1").hide(1000);
   		})
   		
   	</script>
		
		
		
</body>
</html>