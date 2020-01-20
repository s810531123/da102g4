
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="com._class.model.*"%>


<%
	ClassVO classVO = (ClassVO) request.getAttribute("classVO");
%>

<jsp:useBean id="teacherSvc" class="com.teacher.model.TeacherService" />
<jsp:useBean id="classSvc" class="com._class.model.ClassService" />

<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/class_manage/css/select_page.css">
<title>查詢班級頁面-2</title>
<%@ include file="/back-end/includePage/Head"%>
</head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/class_manage/css/search_page.css">
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->
	<div class="col-lg-10 col-md-10 cb rightContent">

		<form method="post" action="<%=request.getContextPath()%>/class/class.doclass">
		
			<tr><h3 class="sear_h3">以班級編號查詢</h3></tr>
			
			<tr><td><select name="cs_num">
				<c:forEach var="classVO" items="${classSvc.all}">
					<option value="${classVO.cs_num}">${classVO.cs_num}
				</c:forEach>
			</select></td></tr>
			
			
				<input type="hidden" name="action" value="getOne_For_Display"> 
				<input type="submit" value="開始查詢">
			
		</form>



		<table>
			<tr>
				<td><b style="color: black">班級編號</b></td>

				<td><b style="color: black">班級名稱</b></td>

				<td><b style="color: black">班導師資料</b></td>

				<td><b style="color: black">副班導師資料</b></td>

				<td><b style="color: black">入學年度</b></td>
			</tr>

			<tr>
				<td><b style="color: blue">${classVO.getCs_num()}</b></td>
				<td><b style="color: gray">${classVO.getCs_name()}</b></td>
				<td><b style="color: gray">${classVO.getT_num_1()} <b
						style="color: gray">${teacherSvc.getOneTeacher(classVO.t_num_1).t_name}</b></td>
				<td><b style="color: gray">${classVO.getT_num_2()} <b
						style="color: gray">${teacherSvc.getOneTeacher(classVO.t_num_2).t_name}</b></td>
				<td><b style="color: gray">${classVO.getCs_years()}</b></td>
			</tr>

		</table>

		<%--錯誤列表--%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: purple">Oops!請再檢查一下!</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: purple">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		
		<div>
			<!-- 排版用 -->
		</div>
		
		<a href="<%=request.getContextPath()%>/back-end/class_manage/select_page.jsp">
			<input type="button" class="pre" value="回上一頁">
		</a>

	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>

</html>