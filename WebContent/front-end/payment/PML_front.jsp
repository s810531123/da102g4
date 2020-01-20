<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.paymentproject.model.*"%>

<html>
<head>
<meta charset="UTF-8">
<%@ include file="../Head"%>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/payment/asc.css">
</head>
<body>
<%@ include file="../Nav"%>
	<%@ include file="../Top"%>
<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="outer">		
				

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
				<input type="button" value="返回"
			onclick="location.href='<%=request.getContextPath()%>/front-end/payment/PMF_front.jsp'">
		</div>
			</div>
		</div>
	</section>

	<%@ include file="../Footer"%>
</body>

</html>