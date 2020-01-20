<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com._class.model.*"%>
<%@ page import="com.class_ann.model.*"%>
<%@ page import="com.student.model.*"%>

<% 
	StudentVO stVO = new StudentVO();
	
	session.setAttribute("stVO", stVO);
%>


<% 
	Class_annVO ann = new Class_annVO();
// 	ann.setCs_num("C001");
// 	session.setAttribute("ann", ann);
%>

<%
	Class_annService class_annSvc = new Class_annService();
	List<Class_annVO> list = class_annSvc.getAll();
	request.setAttribute("list", list);
%>

<%
	Class_annVO class_annVO = (Class_annVO) request.getAttribute("class_annVO");
%>

<html>
<head>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/class_ann/css/select_page.css">

<%@ include file="/front-end/Head"%>
<%@ include file="/front-end/Nav"%>
<%@ include file="/front-end/Top"%>
<title>瀏覽班級公告</title>
</head>

<body>


	<section class="ftco-section">
	<div class="container">
	<div class="bfontcol-lg-12">
	
	
	
		<h1 class="add_h1"><b>班級公告區</b></h1>

		
	<div class="right_home_div">
	<a href="<%=request.getContextPath()%>/front-end/FrontIndex.jsp" class="right_home">
		<img src="<%=request.getContextPath()%>/front-end/class_ann/photo/tohome.png">
		<br>回首頁
	</a>
	</div>
	
	
		 <table>
			<thead>
				<tr>
					<a href="<%=request.getContextPath()%>/front-end/class_ann/search_page.jsp">
						<button class="search_btn">查詢班級公告</button>
					</a>
				</tr>
			</thead>
		 </table>
		  
		<table>
		
			<!-- 權限 -->
			
			<thead>
				<tr>
					<td><b style="color:black">公告編號</td>
					<td><b style="color:black">班級編號</td>
					<td><b style="color:black">公告種類</td>
					<td><b style="color:black">公告標題</td>
					<td><b style="color:black">公告內容</td>
					<td><b style="color:black">公告日期</td>
				</tr>
			</thead>
			

			<tbody>
					<c:forEach var="class_annVO" items="${list}">
					<c:if test="${stp.cs_num == class_annVO.cs_num}">
					<tr>
						<td><b style="color: blue">${class_annVO.cs_ann_num}</td>
						<td><b style="color: gray">${class_annVO.cs_num}</td>
						<td><b style="color: gray">${class_annVO.cs_ann_kind}</td>
						<td><b style="color: gray">${class_annVO.cs_ann_ti}</td>
						<td><b style="color: gray">${class_annVO.cs_ann_text}</td>
						<td><b style="color: gray">${class_annVO.cs_ann_date}</td>	
					</tr>
					</c:if>
					</c:forEach>	
			</tbody>
			
			
		</table>
		
		
		
		
		

	</div>	
	</div>	
	</section>
	
</body>

<%-- <%@ include file="/front-end/Footer"%> --%>
</html>