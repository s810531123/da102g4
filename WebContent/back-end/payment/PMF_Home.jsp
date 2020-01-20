<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.paymentform.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/includePage/Head"%>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/payment/asc.css">
</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
		<div class="col-lg-10 col-md-10 cb rightContent" id="background">
	<br>
	<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
	<div>
	<input type="button" value="繳費單項目管理" onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMP.jsp'">
		<input type="button" value="繳費單設定" onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMF_ADD.jsp'">
		</div>			
			<form METHOD="post"
				ACTION="<%=request.getContextPath()%>/payment/pmf.do">
				請輸入查詢的學號 <input type="text" name="St_num" maxlength="4" value="">
				<input type="submit" value="確認"> <input type="hidden"
					name="action" value="select">
			</form>
		<div class="own">
			<!-- ----------------------------------------------------展示內容(查詢所有繳費單) -------------------------------------------------------------------------------------------------->
		
			
			
			<%				
				List<PMF_VO> list = (List<PMF_VO>) request.getAttribute("st_num");
				pageContext.setAttribute("list", list);
			%>

			<table id="gradient-style" summary="Employee Pay Sheet" >
				<thead>
					<tr>
						<th scope="col">繳費單編號</th>
						<th scope="col">學號</th>
						<th scope="col">年月份</th>
						<th scope="col">繳費金額</th>
						<th scope="col">繳費狀態</th>
						<th scope="col">繳費時間</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pmfVO" items="${list}" begin="0" end="100">
						<tr>
						<td>
						<form METHOD="post"	ACTION="<%=request.getContextPath()%>/payment/pmf.do">
							<input type="hidden"	name="action" value="select2">
							<input type="hidden"	name="pf_num" value="${pmfVO.pf_num}">
							<input type="submit" value="${pmfVO.pf_num}">
							</form>
							</td>
							<td>${pmfVO.st_num}</td>
							<td><fmt:formatDate value="${pmfVO.month}" pattern="yyyy-MM"/></td>							
							<td>${pmfVO.pml_trail }</td>
							<td>${pmfVO.pf_status}</td>
							<td>${pmfVO.pf_time}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>			
			<!-- ----------------------------------------------------展示內容(查詢所有繳費單) -------------------------------------------------------------------------------------------------->
					</div>
		<input type="button" value="查看所有紀錄"
				onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMF.jsp'">
	</div>
	<%@ include file="/back-end/includePage/BootStrap"%>
</body>
</html>