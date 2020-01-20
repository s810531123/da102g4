<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.car.model.*"%>
<html>
<head>
<%@ include file="/back-end/includePage/Head"%>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/back-end/car/css/indexCar.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
<script src="sweetalert2.all.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<div class="col-lg-10 col-md-10 cb rightContent">
	<%@ include file="RightButton"%>
	
	
			<table class="car_index">
				<thead class="car_index">
					<tr class="car_index">
						<th class="car_index">
							<form action="<%=request.getContextPath()%>/car/car.do" method="post">
								<b>請輸入幼童車編號：</b>
								<input type="text" name="car_num" placeholder="2001">
								<input type="hidden" name="action" value="getOne_car">
								<button class="car_index" type="submit">查詢</button>
							</form>
						</th>
						<th class="car_index"><b>查詢全部：</b><a href="<%= request.getContextPath()%>/back-end/car/listAllCar.jsp"><button class="car_index">查詢</button></a></th>
						<th class="car_index"><b>新增：</b><a href="<%= request.getContextPath()%>/back-end/car/addCar.jsp"><button id="insert" class="car_index" value="0">新增</button></a></th>
					</tr>
				</thead>
			</table>
<!-- 	接收錯誤列表 顯示出來 -->
		<div id="error">
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
<!-- 				<h4>錯誤訊息：</h4> -->
<%-- 				<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 					<b style="color:red">${message}</b> --%>
<%-- 				</c:forEach> --%>
			</c:if>			
		</div>
		<img id="imgCar" src="<%=request.getContextPath()%>/back-end/car/images/car.png">
	</div>
	<%@ include file="/back-end/includePage/BootStrap"%>
	<script src="<%=request.getContextPath()%>/back-end/car/JS/indexCar.js"></script>
</body>
</html>