<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<%
	MenuVO menuVO = (MenuVO) request.getAttribute("menuVO");
%>

<html>
<head>
<%@ include file="/back-end/includePage/Head"%>
<title>新增餐點 - MenuAdd.jsp</title>

<style>
.izumi-color {
	border-color: none;
}

div.context {
	background-image: url(image/foodbackground.png);
	background-repeat: repeat;
	margin: 0;
	padding: 30px;
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
	width: 65%;
	margin-left: auto;
	margin-right: auto;
}

b {
	font-size: 17px;
	color:#544945;
}

table {
	width: 70%;
	background-color:#FAFBF4;
	margin-left: auto;
	margin-right: auto;
	
}

th, td {
	z-index: 3;
	border-style: solid;
	border-width: 2px;
	border-color: #743a3a;
	padding: 10px;
	text-align: center;
	color: black;
}

th {
	background-color: #e7f3f4;
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
				<div class="inputsomething">

					<FORM METHOD="post" ACTION="menu.do" name="form1">
						<table>
							<tr>
								<td><b>選擇餐點日期:</b></td>
								<td><input name="mu_Date" id="f_date3" type="text"
									size="13"></td>
							</tr>


							<tr>
								<td><b>選擇餐點時段:</b></td>
								<td><select size="1" name="mu_Time">
										<option value="早餐">早餐</option>
										<option value="午餐">午餐</option>
										<option value="點心">點心</option>
								</select></td>
							</tr>
							<tr>
								<td><b>輸入餐點名稱:</b></td>
								<td><input type="TEXT" name="mu_Name" size="20" value=""></td>
							</tr>
						</table>
						<br> <input type="hidden" name="action" value="insert">

						<input type="image" name="submit" src="image/add_menu_icon.png"
							width="200" height="55" alt="Submit">
					</FORM>
				</div>
			</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>
<%@ include file="MenuDateTime.file"%>
<link href="css/menu.css" rel="stylesheet" type="text/css" />
</html>