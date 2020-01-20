<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<html>

<head>
<%@ include file="/back-end/includePage/Head"%>
<style>
div.context {
	background-image: url(image/foodbackground.png);
	background-repeat: repeat;
	margin: 0;
	padding: 50px;
	height: 100%;
	border: none;
}

.mtable {
	width: 70%;
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
</style>
<style>
.izumi-color {
	border-color: none;
}
</style>
</head>

<body>
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
				<table class="mtable">

					<tr>
						<th>餐點編號</th>
						<th>餐點日期</th>
						<th>餐點時段</th>
						<th>餐點名稱</th>
					</tr>
					<c:forEach var="menuVO" items="${listVO}">
						<tr>
							<td>${menuVO.mu_Num}</td>
							<td>${menuVO.mu_Date}</td>
							<td>${menuVO.mu_Time}</td>
							<td>${menuVO.mu_Name}</td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>

<link href="css/menu.css" rel="stylesheet" type="text/css" />
</html>
