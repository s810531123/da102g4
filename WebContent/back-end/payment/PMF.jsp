<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.paymentform.model.*"%>

<%
	PMF_Service dao = new PMF_Service();
	List<PMF_VO> list = dao.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/payment/asc.css">
<meta charset="UTF-8">
<%@ include file="/back-end/includePage/Head"%>
<title>Insert title here</title>

</head>
<body>
<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	
	<div class="col-lg-10 col-md-10 cb rightContent" id="background">
	<br>
		
		
			<%@ include file="page1.file" %> 
		<div class="own">	
				<div style="left:75%">
				<form METHOD="post"	ACTION="<%=request.getContextPath()%>/payment/pmf.do">
					
				<table id="gradient-style" summary="Employee Pay Sheet">
				<thead>
				<tr >
					<th scope="col">繳費單編號</th>
					<th scope="col">學號</th>
					<th scope="col">年月份</th>
					<th scope="col">繳費金額</th>
					<th scope="col">繳費狀態</th>
					<th scope="col">繳費時間</th>
				</tr>
				</thead>
				<tbody>			
				<c:forEach var="pmfVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${pmfVO.pf_num}</td>			
						<td>${pmfVO.st_num}</td>
						<td><fmt:formatDate value="${pmfVO.month}" pattern="yyyy-MM"/></td>
						<td>${pmfVO.pml_trail }</td>
						<td>
						${pmfVO.pf_status}
						</td>
						<td>${pmfVO.pf_time}</td>							
					</tr>
				</c:forEach>
				</tbody>
			</table>		
			</form>
			</div>
			
			
		</div>
			<%@ include file="page2.file" %>
			<input type="button" value="返回" onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMF_Home.jsp'">
	</div>
	<%@ include file="/back-end/includePage/BootStrap"%>
</body>
</html>