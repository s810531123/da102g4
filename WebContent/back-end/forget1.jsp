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
<meta charset="UTF-8">
<%@ include file="/back-end/includePage/Head"%>
<title>Login.jsp</title>
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
	left:200px;
	background-size:700px 700px;
}


#noopacity{
	position:relative;
	z-index:999;
	opacity:1;
	bottom:1000px;
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
	                 	<span class="blue">忘</span>
	                 	<span class="lightblue">記</span>
	                 	<span class="pink">密</span>
	                 	<span class="gold">碼</span>
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
								<td>身分證字號:</td><td><input type='text' name='t_id' required value="<%= (tp==null)? "" : tp.getT_num()%>" /></td>
							</tr>
							<tr>
								<td>教師編號:</td><td><input type='text' name='t_num' required /></td>
							</tr>
							<tr>
								<td colspan='2'>
									<input type="hidden" name="action" value="forget2">
									<input type="submit" value="送出">
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