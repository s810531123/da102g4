
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>


<%
	MenuVO menuVO = (MenuVO) request.getAttribute("menuVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="../Head"%>
<style>

#table-1 {
	border: 3px dotted black;
	margin-left: auto;
	margin-right: auto;
	padding: 7px;
	margin-top: 20px;
}

#table-1, th, td {
	border: 2px dotted black;
	padding: 8px;
	font-family: Microsoft JhengHei !important;
	color: #0B4114;
	background-color: #FFF1EB;
	font-weight: bolder;
}

.out {
	background-image: url(image/front_menu_back.jpg) !important;
	background-repeat: repeat;
	margin: 0;
	padding: 0;
	min-height: 600px;
	height: 100%;
	width: 100%;
	border: 3px solid rgba(0, 0, 0, 0);
	padding: 0;
}

#titleimage {
	height: 35%;
	width: 35%;
	display: block;
	margin: auto;
	margin-top: 20px;
}

.titleimage {
	background-color: rgba(255, 255, 255, 0.5);
	margin: auto;
}

.in:hover {
	background-color: #FFF8EB;
}

.buttonHolder {
	text-align: center;
	margin-top: 30px;
}
</style>


</head>
<body>

	<%@ include file="../Nav"%>
	<%@ include file="../Top"%>

	<!-- ======================================================================================= -->

	<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="out">

					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<!-- ======================================================================================= -->

					<div class="context">







					</div>
				</div>
			</div>

		</div>
	</section>
	<%@ include file="../Footer"%>

</body>

<%@ include file="MenuCalendar.file"%>
<%@ include file="/back-end/includePage/BootStrap"%>



</html>