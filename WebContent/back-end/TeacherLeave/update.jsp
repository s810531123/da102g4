<%@page import="com.teacherLeave.model.TeacherLeaveVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	TeacherLeaveVO teacherLeaveVO = (TeacherLeaveVO) request.getAttribute("teacherLeaveVO");
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/back-end/includePage/Head"%>
</head>
<style type="text/css">
.color-manto {
	border-color: none;
	text-align: center;
}

table {
	margin-left: 22%;
	margin-right: 22%;
	width: 56%;
	height: 150px;
	font-size: 20px;
	border:2px #000000 solid;padding:5px;
}

tr {
	text-align: center;
	font-family: Microsoft JhengHei;
}

td {
	background-color:#E0FFFF;	/*表格淺藍色*/
	font-family: Microsoft JhengHei;
}

textarea {
	width: 90%;
}

h2 {
	font-family: Microsoft JhengHei;
	color: #20B2AA;  /*淺藍綠色*/
}

.test{
	position: absolute;
	right: 22%;
}

button.btn-success {
	margin-right: 25px;
	letter-spacing: 2px;
	border-radius: 7px ;
	outline: none;
    margin: auto;
}
h4{
	color: #FF6347;
	font-family: Microsoft JhengHei;
}

</style>

<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->
	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="manto">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<!-- 園長身份辨別 -->
			<c:if test="${sessionScope.tp.t_num == 'T001'}">
				<div>
					<img src="image/Logo.png"width="100px">
					<h2>審 核 請 假 單</h2>
				</div>
				<div style="margin-left:22%; text-align:left;width: 600px; color: #FF6347;font-weight:bold;">
					假 單 編 號 ：<%=teacherLeaveVO.getTl_Id()%><br>
					假單申請人：${teacherVO.t_name} <span class="test"> 假單申請日：<%=teacherLeaveVO.getTl_App_Date()%> </span> 
				</div>
				<table border="1">
					<tr>
						<td style="width:20%">假別</td>
						<td style="width:20%"><%=(teacherLeaveVO != null && teacherLeaveVO.getTl_Type() == 0) ? "病假" : ""%>
							<%=(teacherLeaveVO == null || teacherLeaveVO.getTl_Type() == 1) ? "事假" : ""%>
							<%=(teacherLeaveVO != null && teacherLeaveVO.getTl_Type() == 2) ? "喪假" : ""%>
							<%=(teacherLeaveVO != null && teacherLeaveVO.getTl_Type() == 3) ? "其它" : ""%>
						</td>
						<td style="width:25%">請假事由</td>
						<td><%=(teacherLeaveVO == null || teacherLeaveVO.getTl_Reason().length() == 0) ? ""
						: teacherLeaveVO.getTl_Reason()%></td>
					</tr>
					<tr>
						<td>起訖時間</td>
						<td colspan="3" align="left">&emsp;起 始 日 期：<%=teacherLeaveVO.getTl_Sdate()%><br>
							&emsp;結 束 日 期：<%=teacherLeaveVO.getTl_Edate()%><br>
						</td>
					</tr>
				</table>
				<div style="margin-right:0%;font-size:18px;font-family: Microsoft JhengHei;">(備註：審核後將會發信通知申請者)</div>
				<div class="row test" >
					<form action="teacherLeave.do" method="post">
						<input type="hidden" name="tl_Id" value="<%=teacherLeaveVO.getTl_Id()%>"> 
						<input type="hidden" name="action" value="review"> 
						<input type="hidden" name="tl_Status" value="1"> 
						<button type="submit" class="btn-primary" style="font-size:25px;border-radius: 15px">核可</button>&emsp;&emsp;
					</form>
	
					<form action="teacherLeave.do" method="post">
						<input type="hidden" name="tl_Id" value="<%=teacherLeaveVO.getTl_Id()%>"> 
						<input type="hidden" name="action" value="review"> 
						<input type="hidden" name="tl_Status" value="2"> 
						<button type="submit" class="btn-danger" style="font-size:25px;border-radius: 15px">駁回</button>
					</form>
				</div>
			</c:if>
			<!-- 教師身份辨別 -->
			<c:if test="${!(sessionScope.tp.t_num == 'T001')}">

				<form action="teacherLeave.do" method="post">
					<div>
						<img src="image/Logo.png"width="100px">
						<h2>修 改 請 假 單</h2>
					</div>
					<h4>假單編號：<%=teacherLeaveVO.getTl_Id()%></h4>
					<input type="hidden" name="tl_Id" value="<%=teacherLeaveVO.getTl_Id()%>">
					<h4> 假單申請日：<%=teacherLeaveVO.getTl_App_Date()%></h4>
					<input type="hidden" name="tl_App_Date" value="<%=teacherLeaveVO.getTl_App_Date()%>"> 
					<input type="hidden" name="tl_Status" value="<%=teacherLeaveVO.getTl_Status()%>">

					<table border="1">
						<tr>
							<td>假別</td>
							<td><select name="tl_Type">
									<option value="0"
										<%=(teacherLeaveVO != null && teacherLeaveVO.getTl_Type() == 0) ? "selected" : ""%>>病假</option>
									<option value="1"
										<%=(teacherLeaveVO == null || teacherLeaveVO.getTl_Type() == 1) ? "selected" : ""%>>事假</option>
									<option value="2"
										<%=(teacherLeaveVO != null && teacherLeaveVO.getTl_Type() == 2) ? "selected" : ""%>>喪假</option>
									<option value="3"
										<%=(teacherLeaveVO != null && teacherLeaveVO.getTl_Type() == 3) ? "selected" : ""%>>其它</option>
							</select></td>
							<td>請假事由</td>
							<td><textarea name="tl_Reason"><%=(teacherLeaveVO == null || teacherLeaveVO.getTl_Reason().length() == 0) ? ""
    						: teacherLeaveVO.getTl_Reason()%></textarea></td> 
						<tr>
							<td>起訖時間</td>
							<td colspan="3">
							
								起始日期&emsp;<input type="text" name="tl_Sdate" id="start_date"
								value="<%=(teacherLeaveVO == null) ? "" : teacherLeaveVO.getTl_Sdate()%>"><br>
								
								結束日期&emsp;<input type="text" name="tl_Edate" id="end_date"
								value="<%=(teacherLeaveVO == null) ? "" : teacherLeaveVO.getTl_Edate()%>"><br>
								
							</td>
						</tr>
					</table>
					<input type="hidden" name="action" value="update"> <br>
					<button type="submit" class="btn-primary" style="padding:8px;font-size:18px;border-radius: 15px">送出修改</button>
				</form><br>
				<form action="listAllTeacherLeave.jsp">
				<button type="submit" class="btn-danger" style="padding:8px;font-size:18px;border-radius: 15px">取消修改</button>
				</form>
			</c:if>
		</div>
	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>
<c:if test="${ !(tp.t_num == 'T001')}">
	<%@ include file="datePicker"%>
</c:if>
</html>