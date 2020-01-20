<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_pictures.model.*"%>
<%@ page import="com._class.model.*"%>
<%@ include file="/back-end/includePage/Head"%>
<%@ include file="/back-end/includePage/Nav"%>
<%@ include file="/back-end/includePage/LeftSide"%>
<%@ include file="/back-end/includePage/BootStrap"%>	
<script src="<%=request.getContextPath()%>/back-end/class_pics/datetimepicker/jquery.datetimepicker.full.js"></script>
<script src="<%=request.getContextPath()%>/back-end/class_pics/datetimepicker/datetimepicker/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/class_pics/datetimepicker/jquery.datetimepicker.css" />

<!-- import css -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/class_pics/css/update_input_page.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/class_pics/css/search_page.css">
<%
	Class_picturesService class_picturesSvc = new Class_picturesService();
	List<Class_picturesVO> list = class_picturesSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	Class_picturesVO class_picturesVO = (Class_picturesVO) request.getAttribute("class_picturesVO");
%>


<html>
<head>
<title>修改頁面-2</title>
</head>
<body>
	<div class="col-lg-10 col-md-10 cb rightContent">
		
		<table>
			<thead>
				<tr>
					<th>照片編號</th>
					<th>班級編號</th>
					<th>上傳時間</th>
					<th>照片名稱</th>
					<th>照片</th>
					<th>修改</th>
				</tr>
			</thead>
			<tbody>
<%-- 					<%@ include file="page1.file"%> --%>
					
					<c:forEach var="class_picturesVO" items="${list}">
						<tr>
							<td><b style="color: blue" class="bb">${class_picturesVO.cs_pic_num}</td>
							<td><b style="color: black" class="bb">${class_picturesVO.cs_num}</td>
							<td><b style="color: black" class="bb">${class_picturesVO.ul_date}</td>
							<td><b style="color: black" class="bb">${class_picturesVO.pic_cs}  <b>${teacherSvc.getOneTeacher(classVO.t_num_2).t_name}</td>
							<td><img src="<%= request.getContextPath()%>/get_pics/get_pics.do?cs_pic_num=${class_picturesVO.cs_pic_num}" class="in_pic" ></td>
							<td>
								<form method="post"
									action="<%=request.getContextPath()%>/class_pics/class_pics.do">
									<input type="submit" value="修改"> <input type="hidden"
										name="cs_pic_num" value="${class_picturesVO.cs_pic_num}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</form>
							</td>
						</tr>

					</c:forEach>
					
<%-- 					<%@ include file="page2.file"%> --%>
			</tbody>
		</table>
		
		
		<div class="edit_in">
			<a href="<%=request.getContextPath()%>/back-end/class_pics/select_page.jsp">
				<img src="<%=request.getContextPath()%>/back-end/class_pics/photo/icon_edit.png" class="edit_img">
				<b class="tt">回相簿管理首頁
			</a>
		</div>
		
		
	</div>
</body>
</html>