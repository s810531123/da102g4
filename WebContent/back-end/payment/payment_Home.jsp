<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/includePage/Head"%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/payment/asc.css">	

<title>Insert title here</title>

</head>
<body>
		<%@ include file="/back-end/includePage/Nav"%>
		<%@ include file="/back-end/includePage/LeftSide"%>
			<div class="col-lg-10 col-md-10 cb rightContent" id="background">
		<div class="own">
	<div>
	
	<input type="button" value="繳費單項目管理"
				onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMP.jsp'">
	<input type="button" value="繳費單查詢"
				onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMF_Home.jsp'">
	<input type="button" value="繳費單設定"
				onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMF_ADD.jsp'">
	</div>
				</div></div>
		<%@ include file="/back-end/includePage/BootStrap"%>		
</body>
</html>