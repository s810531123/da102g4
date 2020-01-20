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

<!-- css import -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/class_pics/css/update_input_2.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/class_pics/css/update_input_page.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/class_pics/css/search_page.css">

<% 
	Class_picturesVO class_picturesVO = (Class_picturesVO)request.getAttribute("class_picturesVO");
%>


<html>
<head>
<title>修改中</title>
</head>
<body>

	<div class="col-lg-10 col-md-10 cb rightContent">
	<h1 class="up_h1">修改照片資料</h1>
		
		
		<%--錯誤列表 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">Oops!修改中出現錯誤!</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		
		
		
		<form method="post" action="<%=request.getContextPath()%>/class_pics/class_pics.do">
			
			
			<table>
				<tr>
					<td><b style="color:black">照片編號:</td>
					<td><b style="color:black"><%=class_picturesVO.getCs_pic_num()%></td>
				</tr>
				
				
				<jsp:useBean id="classSvc" scope="page" class="com._class.model.ClassService"/>
				<tr>
					<td><b style="color:black">班級編號:</td>
					
					<td><select name="cs_num">
						<c:forEach var="classVO" items="${classSvc.all}">
							<option value="${classVO.cs_num}" 
							${(classVO.cs_num == class_picturesVO.cs_num)? 'selected':''}>${classVO.cs_num}
						</c:forEach>
					</select></td>
					
				</tr>
				
				<tr>
					<td><b style="color:black">上傳日期:</td>
					<td><input type="text" name="ul_date" id="ul_date" value="<%=class_picturesVO.getUl_date() %>"></td>
				</tr>
				
				<tr>
					<td><b style="color:black">照片:</td>
					<td>
						<img src="<%= request.getContextPath()%>/get_pics/get_pics.do?cs_pic_num=${class_picturesVO.cs_pic_num}" class="in_pic">
					</td>
				</tr>
				
				<tr>
					<td><b style="color:black">照片名稱:</td>
					<td><input type="text" name="pic_cs" value="<%=class_picturesVO.getPic_cs()%>"></td>
				</tr>
				
			</table>	  	
			
			
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="cs_pic_num" value="<%=class_picturesVO.getCs_pic_num()%>">
			<input type="submit" value="送出修改">	
		
		</form>
		
	
	
	
	
	
	
		<div class="edit_in">
			<a href="<%=request.getContextPath()%>/back-end/class_pics/select_page.jsp">
				<img src="<%=request.getContextPath()%>/back-end/class_pics/photo/icon_edit.png" class="edit_img">
				<b class="tt">回相簿管理首頁
			</a>
		</div>
		
			
	</div>
	
</body>

<script>

// 月曆
$.datetimepicker.setLocale('zh'); 
$('#ul_date').datetimepicker({
   theme: '',         
   timepicker: true,   
   step: 1,            
   format: 'Y-m-d H:i',
   value: new Date(),
});

</script>


</html>