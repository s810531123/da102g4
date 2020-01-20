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
	
	
	                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/teacher/teacher.do">
						<table>
							<tr>
								<td>請至您的信箱收取密碼</td>
							</tr>
							<tr>
								<td>
									<input type="hidden" name="action" value="back">
									<input type="submit" value="返回登入">
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