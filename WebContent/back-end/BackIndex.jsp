<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/includePage/Head"%>
<title>Insert title here</title>

<style type="text/css">

#background{
	width:1750px;
    height:1500px;
    background-image:url('<%=request.getContextPath()%>/back-end/images/pic.jpg');
    background-repeat:no-repeat;
    border:1px #ccc solid;
    background-size:contain;
	color:red;
}

</style>

</head>
<body>
		<%@ include file="/back-end/includePage/Nav"%>
		<%@ include file="/back-end/includePage/LeftSide"%>
		<div class="col-lg-10 col-md-10 cb rightContent">
			<div class="own" id="background">
			
<%-- 			<img src="<%=request.getContextPath()%>/back-end/images/pic.jpg" style="width:1000px;height:700px;"> --%>
<%-- 			<img src="<%=request.getContextPath()%>/back-end/images/meishin.PNG"> --%>
			
			</div>
		</div>
		<%@ include file="/back-end/includePage/BootStrap"%>
</body>
</html>