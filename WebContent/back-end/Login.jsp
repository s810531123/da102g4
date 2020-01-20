<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>

<% 
	TeacherVO teacherVO = (TeacherVO) request.getAttribute("teacherVO");
	TeacherVO tp = (TeacherVO) request.getAttribute("tp");
%>

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

<meta charset="UTF-8">
<%@ include file="/back-end/includePage/Head"%>
<title>Login.jsp</title>
<script>
$(document).ready(function(){	
	$("#btn1").click(function() {
	    $("#password").attr("value","000001");
	    $("#account").attr("value","T001");
	});
	$("#btn2").click(function() {
		 $("#password").attr("value","000002");
		    $("#account").attr("value","T002");
	});
	$("#btn3").click(function() {
		 $("#password").attr("value","000003");
		    $("#account").attr("value","T003");
	});
});
</script>
<style>


table {
	margin-left: auto;
	margin-right: auto;
}


#myWay{
	position:relative;
	z-index:99;
	background-image:url(<%= request.getContextPath()%>/back-end/images/meishin.PNG);
	background-repeat:no-repeat;
	height:1000px;
	opacity:0.5;
	margin-left:462px;
	background-size:700px 700px;
}

#logincss{
	width:50px;
	height:55px;
}


#noopacity{
	position:relative;
	z-index:999;
	opacity:1;
	bottom:1000px;
}

#forgot:hover{
	color:red;
}

</style>
</head>
<body>
		<%@ include file="/back-end/includePage/Nav_afterLogin"%>
		<%@ include file="/back-end/includePage/LeftSide_afterLogin"%>
		<div class="col-lg-10 col-md-10 cb rightContent">

			<div class="own">

				<div id="myWay">
				</div>
					<div id="noopacity">
					
				
					 <div class="meiSin">
	                 	<span class="blue">美</span>
	                 	<span class="lightblue">心</span>
	                 	<span class="pink">幼</span>
	                 	<span class="gold">兒</span>
	                 	<span class="red">園</span><br>
	                 	<span class="lightgreen">教</span>
	                 	<span class="gold">職</span>
	                 	<span class="pink">員</span>
	                 	<span class="lightblue">登</span>
	                 	<span class="pink">入</span>
	                </div>
	
					<%-- 錯誤表列 --%>
					<table id="error">
						<tr>
							<td colspan='2'>
								<c:if test="${not empty errorMsgs}">
		    						<c:forEach var="message" items="${errorMsgs}">
										<span style="color:red">${message}</span><br>
									</c:forEach>
								</c:if>
							</td>
						</tr>
					</table>
					<table id="error">
						<tr>
							<td colspan='2'>
								<c:if test="${not empty errorMsg}">
		    						<c:forEach var="message" items="${errorMsg}">
										<span style="color:red">${message}</span><br>
									</c:forEach>
								</c:if>
							</td>
						</tr>
					</table>
	
	                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/teacher/teacher.do">
						<table>
							<tr>
								<td>帳號:</td><td><input type='text' name='account' id="account" value="<%= (tp==null)? "" : tp.getT_num()%>" onkeyup="this.value=this.value.toUpperCase()" /></td>
								<td rowspan="2">									
									<input type="hidden" name="action" value="login">
									<button><img src="<%= request.getContextPath()%>/back-end/images/user.png" id="logincss"></button>
								</td>
							</tr>
							<tr>
								<td>密碼:</td><td><input type='password' name='password' id="password" /></td>
							</tr>
							<tr>
								<td colspan='2'>
									<a href="<%=request.getContextPath()%>/back-end/forget1.jsp" id="forgot">忘記密碼</a>
								</td>
							</tr>
							<tr>
							<td>
							<img src="<%=request.getContextPath()%>/back-end/images/meishin.PNG" id="btn1" style="width:50px;height:50px;opacity: 0.5;">
<!-- 							<input style="width:50px;height:50px;opacity: 0.1;" type="button" name="btn1" id="btn1">										 -->
							</td>
							<td>
<!-- 							<input style="width:50px;height:50px;opacity: 0.1;" type="button" name="btn2" id="btn2"> -->
								<img src="<%=request.getContextPath()%>/back-end/images/meishin.PNG" id="btn2" style="width:50px;height:50px;">
							</td>
							<td>
<!-- 							<input style="width:50px;height:50px;opacity: 0.1;" type="button" name="btn3" id="btn3">	 -->
								<img src="<%=request.getContextPath()%>/back-end/images/meishin.PNG" id="btn3" style="width:50px;height:50px;">
							</td>
						</tr>
						</table>
					</FORM>
					</div>
			</div>
		</div>
		<%@ include file="/back-end/includePage/BootStrap"%>
</body>
</html>