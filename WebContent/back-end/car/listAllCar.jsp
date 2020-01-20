<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.car.model.*"%>
<%@ page import="java.util.*"%>


<%
	CarService carSvc = new CarService();
	List<CarVO> list = carSvc.getAll();
	pageContext.setAttribute("list", list);
%>



<jsp:useBean id="studentSvc" scope="page" class="com.student.model.StudentService" />

<html>
<head>
<%@ include file="/back-end/includePage/Head"%>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/back-end/car/css/listAllCar.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
<script src="sweetalert2.all.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
</head>
<body>
<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<div class="col-lg-10 col-md-10 cb rightContent">
	<%@ include file="RightButton"%>
	
	<c:if test="${successAdd}">
		<script>
			Swal.fire(
			 	 '正確',
			 	 '新增成功',
			 	 'success'
			)
		</script>
	</c:if>
	<c:if test="${successUpdate}">
		<script>
			Swal.fire(
			 	 '正確',
			 	 '修改成功',
			 	 'success'
			)
		</script>
	</c:if>
	
	
	<a class="listAll" href="<%= request.getContextPath()%>/back-end/car/select_page.jsp"><div class="listAll">返回</div></a>
	<table class="listAll">
		<thead class="listAll">
			<tr>
				<th class="listAll">編號</th>
				<th class="listAll">學號</th>
				<th class="listAll">姓名</th>
				<th class="listAll">月份</th>
				<th class="listAll">修改</th>
<!-- 				<th class="listAll">刪除</th> -->
			</tr>
		</thead>
		
		<%@ include file="page1.file"%>
		<tbody class="listAll">
			<c:forEach var="carVO" items="${list}" begin="<%= pageIndex %>"
				end="<%= pageIndex + rowsPerPage -1 %>">
				<tr class="listAll">
					<td class="listAll">${carVO.car_num}</td>
					<td class="listAll">${carVO.st_num}</td>
					<td class="listAll">
						<c:forEach var="studentVO" items="${studentSvc.all}">
							<c:if test="${carVO.st_num == studentVO.st_num}">
								${studentVO.st_name}
							</c:if>
						</c:forEach>	
					</td>
					<td class="listAll">${carVO.car_month}</td>
					<td class="listAll">
						<form action="<%=request.getContextPath()%>/car/car.do" method="post">
							<button type="submit">修改</button>
							<input type="hidden" name="car_num" value="${carVO.car_num}">
							<input type="hidden" name="action" value="getOne_For_Update">
						</form>
					</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	<br>
	<%@ include file="page2.file"%>
	</div>
	<%@ include file="/back-end/includePage/BootStrap"%>
	<script src="<%= request.getContextPath()%>/back-end/car/JS/listAllCar.js"></script>
</body>
</html>