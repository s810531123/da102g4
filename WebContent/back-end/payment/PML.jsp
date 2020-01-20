<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.paymentproject.model.*"%>

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
	<input type="button" value="繳費單查詢"
				onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMF_Home.jsp'">
			<input type="button" value="繳費單設定"
				onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMF_ADD.jsp'">
		<div class="own">	
			
	
				

			<table id="gradient-style" summary="Most Favorit Movies">
				<thead>
					<tr>						
						<th scope="col">繳費項目名稱</th>
						<th scope="col">繳費項目金額</th>						
					</tr>
				</thead>
				<%
				List<PMP_VO> list = (List<PMP_VO>) request.getAttribute("getpmpvo");
				pageContext.setAttribute("list", list);
			%>
				<tbody>
				
					<c:forEach var="pmpVO" items="${list}">
						<tr class="alt">							

							<td >${pmpVO.pml_item}</td>

							<td >${pmpVO.pml_money}</td>
								
						</tr>
					</c:forEach>
				</tbody>
			</table>			
			
		</div>
	</div>
	<%@ include file="/back-end/includePage/BootStrap"%>
</body>

</html>