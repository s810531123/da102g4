<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_pictures.model.*"%>
<%@ page import="com._class.model.*"%>

<%
	Class_picturesVO class_picturesVO = (Class_picturesVO) request.getAttribute("class_picturesVO");
	
	ClassService classSvc = new ClassService();
	List<ClassVO> list = classSvc.getAll();
%>

<%-- <jsp:useBean id="classSvc" class="com._class.model.ClassService" /> --%>


<html>
<head>

<%@ include file="/front-end/Head"%>
<title>查詢照片頁面-2</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/class_ann/css/select_page.css">
	<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/class_pics/css/search_page.css">

</head>
<%@ include file="/front-end/Nav"%>
<%@ include file="/front-end/Top"%>
<body>
	<!-- 自己的畫面 -->
	<div class="col-lg-10 col-md-10 cb rightContent">
		
		
		
		<table>
			<tr>
				<td><b style="color: black" class="table_top">照片編號</b></td>

				<td><b style="color: black" class="table_top">班級編號</b></td>

				<td><b style="color: black" class="table_top">上傳時間</b></td>

				<td><b style="color: black" class="table_top">照片</b></td>

				<td><b style="color: black" class="table_top">照片名稱</b></td>
			</tr>
					
			<tr>
				<td><b style="color:blue" class="search_text">${class_picturesVO.cs_pic_num}</b></td>	
				<td><b style="color:black" class="search_text">${class_picturesVO.cs_num}</td>
				<td><b style="color:black" class="search_text">${class_picturesVO.ul_date}</td>
				<td><b style="color:black" class="search_text"><img src="<%= request.getContextPath()%>/get_pics/get_pics.do?cs_pic_num=${class_picturesVO.cs_pic_num}" class="pic_select" ></td>
				<td><b style="color:red" class="search_text">${class_picturesVO.pic_cs}</td>
			</tr>

		</table>
		
		
		
		<form method="post" action="<%=request.getContextPath()%>/class_pics_front/class_pics_front.do">
			<tr>
				<td>以照片編號查詢</td>
				<td>
				<input type="text" name="cs_pic_num"> 
				<input type="hidden" name="action" value="getOne_For_Display"> 
				<input type="submit" value="開始查詢">
				</td>
			</tr>	
		</form>
		
		
		
		
		<form method="post" action="<%=request.getContextPath()%>/class_pics_front/class_pics_front.do">
			
			<jsp:useBean id="class_picturesSvc" scope="page"
					class="com.class_pictures.model.Class_picturesService" />
			
			
				<tr>以照片名稱查詢</tr>
				<tr><td><select name="pic_cs">
					<c:forEach var="class_picturesVO" items="${class_picturesSvc.all}">
						<option value="${class_picturesVO.pic_cs}">${class_picturesVO.pic_cs}
					</c:forEach>
				</select></td></tr>
		
			
			<input type="hidden" name="action" value="getOne_For_Display"> 
			<input type="submit" class="submit" value="開始查詢">
		</form>




		<%--錯誤列表--%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">Oops!請再檢查一下!</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		
		
		
<!-- 		<a -->
<%-- 			href="<%=request.getContextPath()%>/front-end/class_pics/select_page.jsp"><input --%>
<!-- 			type="button" class="pre" value="回上一頁"></a> -->
			
			
			
		
		
		
		
		
		<div class="edit_in">
			<a href="<%=request.getContextPath()%>/front-end/class_pics/select_page.jsp">
				<img src="<%=request.getContextPath()%>/back-end/class_pics/photo/icon_edit.png" class="edit_img">
				<b class="tt">回相簿首頁
			</a>
		</div>



	
			




	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>

</html>