<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_pictures.model.*"%>
<%@ page import="com._class.model.*"%>

<% 
	Class_picturesVO pic = new Class_picturesVO();
// 	pic.setCs_num("C001");
// 	session.setAttribute("class_", pic);
	
	Class_picturesService class_picturesSvc = new Class_picturesService();
	List<Class_picturesVO> list = class_picturesSvc.getAll();
	
	
	String err = (String) request.getAttribute("errInsert");
%>

<%
	Class_picturesVO class_picturesVO = (Class_picturesVO)request.getAttribute("class_picturesVO");
%>



<html>
<head>
<script src="<%=request.getContextPath()%>/back-end/vendors/jquery/jquery-1.12.4.min.js"></script>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/class_pics/datetimepicker/jquery.datetimepicker.css" />
<title>班級相簿首頁-1</title>
<%@ include file="/front-end/Head"%>
</head>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/class_pics/css/select_page.css">

<body>
<%@ include file="/front-end/Nav"%>
<%@ include file="/front-end/Top"%>

	
	<section class="ftco-section">
	<div class="container">
	
		<h1 class="h1_pic"><b>班 級 相 簿</b></h1>
		
					

		<a href="<%=request.getContextPath()%>/front-end/class_pics/search_page.jsp">
		<div class="select_do"><img src="<%=request.getContextPath()%>/front-end/class_pics/photo/icon_search_80.png" id="search_page"><br>查詢照片</div>
		</a>
						
	
	
			
<!-- 全部照片展示 -->
		<div class="into">
			<c:forEach var="class_picturesVO" items="<%=list%>">
			<c:if test="${stp.cs_num == class_picturesVO.cs_num}">
			
				<img src="<%= request.getContextPath()%>/get_pics/get_pics.do?cs_pic_num=${class_picturesVO.cs_pic_num}" class="in_pic" value="${class_picturesVO.cs_pic_num}">

					
				<input type="hidden"  name="cs_pic_num" value="${class_picturesVO.cs_pic_num}">
				
				<input type="hidden"  name="cs_pic_num" value="${class_picturesVO.cs_num}">
				 	
			</c:if>
			</c:forEach>
			<c:if test=""></c:if>
		</div>
	
	
	
	</div>




	
			


	
	<script src="<%=request.getContextPath()%>/back-end/class_pics/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/class_pics/datetimepicker/datetimepicker/jquery.js"></script>
	
	
	</div>
	<section class="ftco-section">
</body>
<script>

//  關燈箱(新增)
// 	$('.close').click(function() {
// 		$('div.lightbox_add').hide(1000);
// 	})
// // 	開燈箱(新增)
// 	$('#add_page').click(function(){
// 		$('div.lightbox_add').show(650);
// 	});		
// // 	月曆(新增照片的時間)
// 	$.datetimepicker.setLocale('zh'); 
//     $('#ul_date').datetimepicker({
//        theme: '',         
//        timepicker: true,   
//        step: 1,            
//        format: 'Y-m-d H:i',
//        value: new Date(),
//     });

</script>
<%@ include file="/front-end/Footer"%>
</html>