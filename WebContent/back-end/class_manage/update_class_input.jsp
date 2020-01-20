<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="com._class.model.*"%>

<%
	ClassVO classVO = (ClassVO) request.getAttribute("classVO");
%>

<html>
<head>
<title>修改班級頁面-2</title>

<%@ include file="/back-end/includePage/Head"%>
</head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/class_manage/css/select_page.css">
<body>

	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->


	<div class="col-lg-10 col-md-10 cb rightContent">

		<h1 class="up_h1">修改班級資料</h1>

			
		<%--錯誤列表 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">Oops!修改中出現錯誤!</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>


		<form method="post"
			action="<%=request.getContextPath()%>/class/class.doclass"
			name="form_update">
			<table>
				<tr>
					<td><b style="color:gray">班級編號</b></td>
					<td><b style="color:blue"><%=classVO.getCs_num()%></b></td>
				</tr>
				<tr>
					<td><b style="color:gray">新班級名稱</b></td>
					<td><input type="text" name="cs_name"
						value="<%=classVO.getCs_name()%>" /></td>
				</tr>


				<jsp:useBean id="teacherSvc" scope="page"
					class="com.teacher.model.TeacherService" />

				<tr>
					<td><b style="color:gray">修改班級導師</b></td>
					<td><select name="t_num_1">
							<c:forEach var="teacherVO" items="${teacherSvc.all}">
								<option value="${teacherVO.t_num}"
									${(classVO.t_num_1==teacherVO.t_num)? 'selected':'' }>${teacherVO.t_name}
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td><b style="color:gray">修改副班導師</b></td>
					<td><select name="t_num_2">
							<c:forEach var="teacherVO" items="${teacherSvc.all}">
								<option value="${teacherVO.t_num}"
									${(classVO.t_num_2==teacherVO.t_num)? 'selected':'' }>${teacherVO.t_name}
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td><b style="color:gray">修改入學年度</b></td>
					<td><input type="text" name="cs_years"
						value="<%=classVO.getCs_years()%>" /></td>
				</tr>
			</table>
			<input type="hidden" name="action" value="update"> <input
				type="hidden" name="cs_num" value="<%=classVO.getCs_num()%>">
			<input type="submit" value="送出修改">
		</form>
			
		<a
			href="<%=request.getContextPath()%>/back-end/class_manage/select_page.jsp"><input
			type="button" class="pre" value="回上一頁"></a>
	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>
</html>