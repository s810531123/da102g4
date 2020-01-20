<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="com._class.model.*"%>
<%
	ClassVO classVO = (ClassVO) request.getAttribute("classVO");
%>


<html>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/class_manage/css/class_new.css">
<head>
<title>新增班級頁面-2</title>

<%@ include file="/back-end/includePage/Head"%>
</head>

<body>


	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->


	<div class="col-lg-10 col-md-10 cb rightContent">

		<h1 class="add_h1">新增班級資料</h1>

		<a class="a"
			href="<%=request.getContextPath()%>/back-end/class_manage/select_page.jsp"><input
			type="button" class="pre" value="回上一頁"></a>

		<%--錯誤列表--%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red" size="5px">請修正以下錯誤</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: purple">${message}</li>
				</c:forEach>
			</ul>
		</c:if>


		<form method="post"
			action="<%=request.getContextPath()%>/class/class.doclass" name="form_add">
			<table class="table">
				<tr>
					<td>班級名稱</td>
					<td><input type="text" name="cs_name" placeholder="請輸入班級名稱"
						value="<%=(classVO == null) ? "" : classVO.getCs_name()%>" /></td>
				</tr>


				<jsp:useBean id="teacherSvc" scope="page"
					class="com.teacher.model.TeacherService" />
				<tr>
					<td>班級導師</td>
					<td><select name="t_num_1">
							<c:forEach var="teacherVO" items="${teacherSvc.all}">
								<option value="${teacherVO.t_num}"
									${(classVO.t_num_1==teacherVO.t_num)? 'selected':'' }>${teacherVO.t_name}
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td>副班導師</td>
					<td><select name="t_num_2">
							<c:forEach var="teacherVO" items="${teacherSvc.all}">
								<option value="${teacherVO.t_num}"
									${(classVO.t_num_2==teacherVO.t_num)? 'selected':'' }>${teacherVO.t_name}
							</c:forEach>
					</select></td>
				</tr>


				<tr>
					<td>入學年度</td>
					<td>民國 <input type="text" name="cs_years"
						value="<%=(classVO == null) ? "" : classVO.getCs_years()%>" />年
					</td>
				</tr>


			</table>
			<input type="hidden" name="action" value="insert"> <input
				type="submit" class="submit" value="送出新增"> <input
				type="reset" class="reset" value="重新填寫">
		</form>


	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>
</html>