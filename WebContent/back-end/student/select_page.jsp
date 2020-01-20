<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>

<%
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
%>

<!DOCTYPE html>
<html>
<head>
	<title>IBM Student: Home</title>
	<%@ include file="/back-end/includePage/Head"%>
	<style>
		#table-1 {
			margin-left:auto;
			margin-right:auto;
			border: 1px solid;
			text-align:left;
		}
		.teahcerTitle{
			text-align:right;
		}
	</style>

</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>

	<%@ include file="/back-end/includePage/LeftSide"%>



	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="own">
			<h3>查詢全體學生資料:</h3>
			
			<a href='<%= request.getContextPath()%>/back-end/student/listAllStudent.jsp'>點此查詢所有學生資料</a><br><br>
			
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<c:forEach var="message" items="${errorMsgs}">
					<span style="color:red">${message}</span>
				</c:forEach>
			</c:if>

	  
	  		<table id="table-1">
	  			<tr>
	  				<td>
	  					<b>輸入學生學號:</b>
	  				</td>
	  				<td>
	  					<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/student/student.do" >
					        <input type="text" name="st_num">
					        <input type="hidden" name="action" value="getOne_For_Display">
					        <input type="submit" value="送出">
	    				</FORM>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<b>選擇學生姓名:</b>
	  				</td>
	  				<td>
	  					<jsp:useBean id="studentSvc" scope="page" class="com.student.model.StudentService" />
	  					<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/student/student.do" >
	       					<select size="1" name="st_num">
	         					<c:forEach var="studentVO" items="${studentSvc.all}" > 
	          						<option value="${studentVO.st_num}">${studentVO.st_name}
	         					</c:forEach>   
	      					</select>
					       <input type="hidden" name="action" value="getOne_For_Display">
					       <input type="submit" value="送出">
	    				</FORM>
	  				</td>
	  			</tr>
	  		</table>
	  		
	  		<br><br>
			
			<c:if test="${'園長' eq tp.t_job}">
				<h3>新增學生資料</h3>
				<a href='<%= request.getContextPath()%>/back-end/guardian/addGuardian.jsp'>Add a new Student.</a>
			</c:if>
			
		
		</div>
	</div>
</body>
	<%@ include file="/back-end/includePage/BootStrap"%>
</html>