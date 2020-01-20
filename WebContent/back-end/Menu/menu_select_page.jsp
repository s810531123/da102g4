<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<html>
<head>
<title>選擇餐點日期</title>


<%@ include file="/back-end/includePage/Head"%>

<style>
.izumi-color {
	border-color: none;
}

div.context {
	background-image: url(image/foodbackground.png);
	background-repeat: repeat;
	margin: 0;
	padding: 0;
	height: 100%;
	border: none;
}

div.title {
	font-size: 30px;
	background-color: none;
	padding: 3px;
}

div.inputsomething {
	background-color: rgba(255, 255, 255, 0.6);
	border-radius: 10px;
	border-style: dotted;
	border-color: #743a3a;
	padding: 20px;
	border-width: 3px;
	margin: 20px;
	width: 60%;
	margin-left: auto;
	margin-right: auto;
}

b {
	font-size: 20px;
	color:#544945;
}


</style>
</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->

	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="bg-image">
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
			</div>
			<div class="context">
				<br> <br>
				<div class="inputsomething">
					<div class="form-group mx-sm-3 mb-2">
						<FORM METHOD="post" ACTION="menu.do">
							<b>請輸入日期:</b>
								<div class="col-lg-4">
							</div>
							<div class="col-lg-4">
								<input class="form-control" type="text" id="f_date3"
									name="mu_Date"><br>
								<input type="hidden" name="action"
									value="GetMenuByDate">
									
							</div>
							<div class="col-lg-4">
							</div>
							<input type="image" name="submit"
								src="image/search_menu_icon.png" width="200" height="55"
								alt="Submit">
						</FORM>
					</div>
				</div>
			</div>
		</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>
<link href="css/menu_select_page.css" rel="stylesheet" type="text/css" />
<%@ include file="MenuDateTime.file"%>
</html>