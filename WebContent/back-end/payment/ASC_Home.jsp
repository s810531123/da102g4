<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.afterschoolclass.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/includePage/Head"%>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/payment/asc.css">
<style>
table {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide_Teacher"%>
	<div class="col-lg-10 col-md-10 cb rightContent" id="background">
		<br>
		<form METHOD="post"
			ACTION="<%=request.getContextPath()%>/payment/asc.do">
			請輸入查詢的學號 <input type="text" name="St_num" maxlength="4" value="">
			<input type="submit" value="確認"> <input type="hidden"
				name="action" value="select">
		</form>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="own">
			<%
				List<ASC_VO> list = (List<ASC_VO>) request.getAttribute("st_num");						
				pageContext.setAttribute("list", list);
			%>

			<table id="gradient-style" summary="Meeting Results">
				<thead>
					<tr>
						<th scope="col">托育編號</th>
						<th scope="col">學號</th>
						<th scope="col">托育費用</th>
						<th scope="col">離校時間</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="ascVO" items="${list}" begin="0" end="100">
						<tr>
							<td>${ascVO.nursery_no}</td>
							<td>${ascVO.st_num}</td>
							<td>${ascVO.st_sum }</td>
							<td>${ascVO.time}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
		<input type="button" value="查看所有紀錄"
			onclick="location.href='<%=request.getContextPath()%>/back-end/payment/ASC.jsp'">
	</div>
	<%@ include file="/back-end/includePage/BootStrap"%>
</body>
</html>