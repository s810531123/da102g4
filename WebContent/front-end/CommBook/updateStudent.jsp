<%@page import="com.commBook.model.CommBookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.student.model.*"%>
<% 
	CommBookVO commBookVO = (CommBookVO) request.getAttribute("commBookVO"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="../Head"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Insert title here</title>
<style type="text/css">
.outer {
	background: #FFFFFF !important;
}
</style>
</head>
<!-- 自己的css -->
<link rel="stylesheet" href="./css/update.css">
<body>

	<%@ include file="../Nav"%>
	<%@ include file="../Top"%>

	<!-- ======================================================================================= -->

	<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="outer">
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					
					<form action="commBookVO.do" method="post">
						<h3>修改前台聯絡簿</h3>
						<table>
							<tr class="one">
								<th>項目</th>
								<th class="dateday1">					
									<% java.sql.Date toDay = new java.sql.Date(System.currentTimeMillis());%>
									<%=toDay %>
									<input type="hidden" name="comm_Date" value="<%=toDay%>"><br> 
								</th>
							</tr>
							<tr class="two">
								<th>老師的話</th>
								<td class="day1"><%=commBookVO.getComm_T_Msg()%></td>
								<input type="hidden" name="comm_T_Msg" value="<%=commBookVO.getComm_T_Msg()%>">	
							</tr>
							<tr class="three">
								<th>給家長的話</th>
								<td class="day2"><%=commBookVO.getComm_Res()%></td>
								
							</tr>
							<tr class="four">
								<th class="botton">家長回應</th>
								<td class="botton day2"><%=commBookVO.getComm_P_Msg()%>
									<input type="text" name="comm_P_Msg">
								</td>
							</tr>
						</table>
						<input type="hidden" name="action" value="updateStudent"> <br>
						<button class="w3-button w3-blue w3-round-xlarge">送出</button>
					</form>
				</div>
			</div>
		</div>
	</section>

	<!-- ======================================================================================= -->

	<!-- 	<section class="ftco-section"> -->
	<!-- 		<div class="container"> -->
	<!-- 			<div class="col-lg-12"> -->
	<!-- 				<div class="outer"></div> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</section> -->

	<!-- ======================================================================================= -->

	<%@ include file="../Footer"%>
</body>
</html>