<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resform.model.*"%>
<%
	ResformVO resformVO = (ResformVO) request.getAttribute("resformVO");
%>

<jsp:useBean id="teacherSvc" scope="page"
	class="com.teacher.model.TeacherService" />

<html>
<head>

<style>
div.context {
	background-image: url(image/backimgjpg.jpg);
	background-repeat: repeat;
	margin: 0;
	padding: 50px;
	height: 100%;
	border: none;
}

table {
	width: 60%;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: 25px;
	background-color: white;
	padding: 5px;
}

table, th, td {
	z-index: 3;
	border: 1px dashed #0068ad;
}

th, td {
	padding: 5px;
	text-align: left;
}

td {
	color: black;
	font-family: Microsoft JhengHei !important;
}

th {
	background-color: #e7f3f4;
	color: black !important;
}

input[type="submit"] {
	padding: 5px 15px;
	background: #00e3e3;
	border: 10px red;

	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	z-index: 2;
}

div.inputsomething {
	background-color: rgba(255, 255, 255, 0.6);
	border-radius: 10px;
	padding: 20px;
	border-width: 3px;
	margin: 20px;
	width: 100%;
	margin-left: auto;
	margin-right: auto;
}

</style>
</head>

<%@ include file="/back-end/includePage/Head"%>
<body>

	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<div class="col-lg-10 col-md-10 cb rightContent">


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
<div class="inputsomething">
			<FORM METHOD="post" ACTION="resform.do" name="form1">
				<table>
					<tr>
						<td>預約導覽教師:</td>
						<td><input type="TEXT" name="t_Num" size="10"
							value="<%=resformVO.getT_Num()%>" /></td>
					</tr>
					<tr>
						<td>預約單編號:<font color=red></font></td>
						<td><%=resformVO.getRes_Num()%></td>
					</tr>
					<tr>
						<td>預約人姓名:</td>
						<td><input type="TEXT" name="res_Name" size="15"
							value="<%=resformVO.getRes_Name()%>" /></td>
					</tr>
					<tr>
						<td>預約人E-mail:</td>
						<td><input type="TEXT" name="res_Email" size="30"
							value="<%=resformVO.getRes_Email()%>" /></td>
					</tr>
					<tr>
						<td>預約人電話:</td>
						<td><input type="TEXT" name="res_Phone" size="30"
							value="<%=resformVO.getRes_Phone()%>" /></td>
					</tr>
					<tr>
						<td>預約日期:</td>
						<td><input name="res_Date" id="f_date2" type="text"></td>
					</tr>
					<tr>
						<td>備註:</td>
						<td><input type="TEXT" name="res_Msg" size="45"
							value="<%=resformVO.getRes_Msg()%>" /></td>
					</tr>

				</table>
				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="res_Num" value="<%=resformVO.getRes_Num()%>">
				<input type="submit" value="送出修改">	</FORM>
		</div>

	</div>

	<br>

</body>

<%@ include file="/back-end/includePage/BootStrap"%>
<%@ include file="DateTime.file"%>
<link href="css/resform.css" rel="stylesheet" type="text/css" />

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
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=resformVO.getRes_Date()%>' ,//value:   new Date(),
		//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	            '2017/07/10',  // 起始日
		minDate : '-1970-01-01', // 去除今日(不含)之前
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
