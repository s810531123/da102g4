<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>
<%
	MenuVO menuVO = (MenuVO) request.getAttribute("menuVO"); //MenuServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的,menuVO, 也包括輸入資料錯誤時的menuVO物件)
%>

<html>
<head>
<%@ include file="/back-end/includePage/Head"%>
<title>餐點表修改 - update_menu_input.jsp</title>

<style>
div.context {
	background-image: url(image/foodbackground.png);
	background-repeat: repeat;
	margin: 0;
	padding: 50px;
	height: 100%;
	border: none;
}

table {
	width: 60%;
	background-color: rgba(255, 255, 255, 0.88);
	margin-left: auto;
	margin-right: auto;
	border-radius:10px;

}

th, td {
	z-index: 3;
	border-style: dotted;
	border-width: 2px;
	border-color:#743a3a;
	padding: 10px;
	text-align: center;
	color: black;

}

th {
	background-color: #e7f3f4;
}
b {
	font-size: 16px;
	color:#544945;
}

</style>
<style>
.izumi-color {
	border-color: none;
}
</style>
</head>

<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>

	<!-- 自己的畫面 -->

	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="izumi-color">
		<div class="row">
				<div class="col">
					<a href='MenuAdd.jsp'>
						<button type="button" class="btn-success float-right">新增餐點</button>
					</a> <a href='menu_select_page.jsp'><button type="button"
							class="btn-warning float-right">查詢餐點</button></a> <a
						href='getAllMenu.jsp'>
						<button type="button" class="btn-link float-left">餐點列表</button>
					</a>
				</div>
			</div>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<div class="context">
			<FORM METHOD="post" ACTION="menu.do" name="form1">
				<table>
					<tr>
						<td><b>餐點編號:</b></td>
						<td><%=menuVO.getMu_Num()%></td>
					</tr>
				
							<tr>
								<td><b>選擇餐點時段:</b></td>
								<td><select size="1" name="mu_Time">
										<option value="早餐">早餐</option>
										<option value="午餐">午餐</option>
										<option value="點心">點心</option>
								</select></td>
							</tr>
							
								<tr>
						<td><b>餐點時段:</b></td>
						<td><input type="TEXT" name="mu_Time" size="20"
							value="<%=menuVO.getMu_Time()%>" /></td>
					</tr>
							
							
							
							
					<tr>
						<td><b>餐點名稱:</b></td>
						<td><input type="TEXT" name="mu_Name" size="20"
							value="<%=menuVO.getMu_Name()%>" /></td>
					</tr>
					<tr>
						<td><b>餐點日期:</b></td>
						<td><input name="mu_Date" id="f_date4" type="text"></td>
					</tr>



				</table>
				<br> <input type="hidden" name="action" value="update">
				<input type="hidden" name="mu_Num" value="<%=menuVO.getMu_Num()%>">
				<input type="submit" value="送出修改">
			</FORM>
			</div>
</body>

<%@ include file="/back-end/includePage/BootStrap"%>
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<%@ include file="MenuDateTime.file"%>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date4').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=menuVO.getMu_Date()%>', //value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//		minDate : '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>

</html>
