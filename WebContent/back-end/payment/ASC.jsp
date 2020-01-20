<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.afterschoolclass.model.*"%>
<%
	ASC_Service dao = new ASC_Service();
	List<ASC_VO> list = dao.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/back-end/includePage/Head"%>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/payment/asc.css">
</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
		<div class="col-lg-10 col-md-10 cb rightContent" id="background">
	<br>
	<%@ include file="page1.file" %> 
		<div class="own" >
			<!-- ----------------------------------------------------展示內容(顯示所有托育內容) -------------------------------------------------------------------------------------------------->
			<form METHOD="post"
				ACTION="<%=request.getContextPath()%>/payment/asc.do">

				<table id="gradient-style" summary="Meeting Results">
					<thead>
						<tr>
							<th>托育編號</th>
							<th>學號</th>
							<th>托育費用</th>
							<th>離校時間</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach var="ascVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td scope="col">${ascVO.nursery_no}</td>
								<td scope="col">${ascVO.st_num}</td>
								<td scope="col">${ascVO.st_sum }</td>
								<td scope="col">${ascVO.time}</td>
							</tr>
						</c:forEach>							
					</tbody>
				</table>				
			</form>
		<%@ include file="page2.file" %>
		
			<!-- ----------------------------------------------------展示內容(顯示所有托育內容) -------------------------------------------------------------------------------------------------->
		</div>
			<input type="button" value="返回"
				onclick="location.href='<%=request.getContextPath()%>/back-end/payment/ASC_Home.jsp'">
	</div>
	<%@ include file="/back-end/includePage/BootStrap"%>
</body>
</html>