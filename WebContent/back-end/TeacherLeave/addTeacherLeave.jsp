<%@ page import="com.teacherLeave.model.TeacherLeaveVO"%>
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
<style type="text/css">
.color-manto {
	border-color: none;
}

table {
	margin-left: auto;
	margin-right: auto;
	border-color: #FFFFFF;
	width: 500px;
	height: 150px;
	font-size: 25px;
	border: 2px #000000 solid;
	
}

tr {
	text-align: center;
}

td {
	background-color: #E0FFFF; /*表格淺藍色*/
	font-family: Microsoft JhengHei;
	border: 2px #000000 solid;
}

textarea {
	width: 95%;
	height: 90%;
}

h2 {
	font-family: Microsoft JhengHei;
	color: #FF6347; /*橘紅色*/
}

button{
	font-size: 20px;
	padding: 5px 10px;
}
</style>
</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->
	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="color-manto">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${sessionScope.tp.t_num == null}">請先登入</c:if>
			<c:if test="${!(sessionScope.tp.t_num == null)}">
				<c:if test="${!(sessionScope.tp.t_num == 'T001')}">
					<form action="teacherLeave.do" method="post">
						<div>
							<img src="image/Logo.png" width="100px" id="ting">
							<h2>教 職 員 請 假 單</h2>
						</div>
						<table border="2">
							<tr >
								<td style="width:150px;">假別</td>
								<td align="left" style="padding:10px;"><select name="tl_Type">
										<option value="0"
											<%=(teacherLeaveVO != null && teacherLeaveVO.getTl_Type() == 0) ? "selected" : ""%>>病假</option>
										<option value="1"
											<%=(teacherLeaveVO == null || teacherLeaveVO.getTl_Type() == 1) ? "selected" : ""%>>事假</option>
										<option value="2"
											<%=(teacherLeaveVO != null && teacherLeaveVO.getTl_Type() == 2) ? "selected" : ""%>>喪假</option>
										<option value="3"
											<%=(teacherLeaveVO != null && teacherLeaveVO.getTl_Type() == 3) ? "selected" : ""%>>其它</option>
								</select></td>
							</tr>
							<tr>
								<td>事由</td>
								<td><textarea name="tl_Reason" style="padding:10px" id="Reason"><%=(teacherLeaveVO == null || teacherLeaveVO.getTl_Reason().length() == 0) ? ""
							: teacherLeaveVO.getTl_Reason()%></textarea></td>
							</tr>
							<tr>
								<td>起訖時間</td>
								<td colspan="3" align="left" style="padding:10px;">
								起始日期 
								<input type="text"	name="tl_Sdate" id="start_date" value="<%=(teacherLeaveVO == null) ? "" : teacherLeaveVO.getTl_Sdate()%>"><br>
								結束日期
								<input type="text" name="tl_Edate" id="end_date" value="<%=(teacherLeaveVO == null) ? "" : teacherLeaveVO.getTl_Edate()%>"><br>
								</td>
							</tr>
						</table>
						<br> <input type="hidden" name="action" value="insert">
						<button type="submit" class="btn-primary"
							style="border-radius: 15px">送出</button>
					</form><br>
					<form action="select_page.jsp">
						<button type="submit" class="btn-info" style="border-radius: 15px">返回請假系統</button>
					</form>
				</c:if>
			</c:if>
		</div>
	</div>
</body>



<%@ include file="/back-end/includePage/BootStrap"%>
<%@ include file="datePicker"%>
<script>
	$("#ting").click(function() {
		$("#Reason").val("想請假");
	    $("#start_date").val("2019-10-01");
	    $("#end_date").val("2019-10-04");
	});
</script>

</html>