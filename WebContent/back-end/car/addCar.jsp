<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.car.model.*"%>
<html>
<head>
<%@ include file="/back-end/includePage/Head"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/back-end/car/css/addCar.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
<script src="sweetalert2.all.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<div class="col-lg-10 col-md-10 cb rightContent">
	<%@ include file="RightButton"%>

	<jsp:useBean id="student" scope="page" class="com.student.model.StudentService"/>
		
		<% CarVO carVO =  (CarVO)request.getAttribute("carVO");%>
		
		<a class="listAll" href="<%= request.getContextPath()%>/back-end/car/select_page.jsp"><div class="return">返回</div></a>
			<div class="addCar">
		    	<form action="<%=request.getContextPath()%>/car/car.do" method="post">
		    		<h3 class="addCar">新增幼童車資料</h3>
		    		<b class="addCar">請選擇學生：</b>
		    		<select size="1" name="st_num">
		    		<c:forEach var="stVO" items="${student.all}">
		    			<option value="${stVO.st_num}" ${carVO.st_num == stVO.st_num ? 'selected' : ''} >${stVO.st_name}
		    		</c:forEach>
		    		</select><br>
		    		<b class="addCar">請選擇月份：</b><input type="month" class="addCar" name="car_month" value="<%= carVO == null ? null : carVO.getCar_month()%>"><br>
		    		<input type="hidden" name="action" value="insert">
		    		<button type="submit" class="addCar">確定</button><button type="reset" class="addCar">重置</button>
		    	</form>
			<!-- 	接收錯誤列表 顯示出來 -->
				<c:if test="${! empty errorMsgs}">
				<script>
					Swal.fire(
					   '錯誤訊息',
					  	<c:forEach var="message" items="${errorMsgs}">
					   '${message}'
					  	</c:forEach>
					   ,'error'
					)
				</script>
					
				</c:if>
			</div>
	</div>
	<%@ include file="/back-end/includePage/BootStrap"%>
</body>
</html>