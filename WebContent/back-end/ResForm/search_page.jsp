<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resform.model.*"%>

<jsp:useBean id="teacherSvc" scope="page"
	class="com.teacher.model.TeacherService" />

<html>
<head>
<title>search</title>


<%@ include file="/back-end/includePage/Head"%>

<style>
.izumi-color{
border-color:none;
}

div.context {
	background-image: url(image/backimgjpg.jpg);
	background-repeat: repeat;
	margin: 0;
	padding: 50px;
	height: 100%;
	border: none;
}
</style>
</head>
<body bgcolor='white'>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="izumi-color">					
			<div class="row">
				<div class="col">
					<a href='deleteResForm.jsp'><button type="button" class="btn-danger float-right">刪除預約單</button></a>
					<a href='update_res_list.jsp'><button type="button" class="btn-warning float-right">修改預約單</button></a>
					<a href='search_page.jsp'><button type="button" class="btn-success float-right">查詢預約單</button></a>
					<a href='getAllResForm.jsp'><button type="button"
							class="btn-link float-left">預約單列表</button></a>
				</div>
			</div>
		</div>
		<div>
		<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>	
		</div>
<div class="context">
	
	<h3>預約單查詢:</h3>	
	
		
			<FORM METHOD="post" ACTION="resform.do">
				<b>請輸入日期:</b> 
				<input type="text" id="f_date1" name="res_Date">
				<input type="hidden" name="action" value="GetResFormByDay">
				<input type="submit" value="送出">
			</FORM>
	

		<jsp:useBean id="resformSvc" scope="page" class="com.resform.model.ResformService" />
	
	</div>
		</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>
<link href="css/resform.css" rel="stylesheet" type="text/css" />
<%@ include file="DateTime.file" %>
</html>