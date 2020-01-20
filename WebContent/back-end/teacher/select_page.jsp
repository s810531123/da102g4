<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*" %>

<%
	TeacherVO teacherVO = (TeacherVO) session.getAttribute("teacherVO");
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
%>

<!DOCTYPE html>
<html>
<head>
	<title>IBM Teacher: Home</title>
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
		
		#hi{
			position:absolute;
			z-index:10;
			margin-left:78%
		}		
	</style>
</head>

<body>
	<%@ include file="/back-end/includePage/Nav"%>

	<%@ include file="/back-end/includePage/LeftSide"%>


	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="own">
			<h3>查詢全體教職員資料:</h3>

			<a href='<%= request.getContextPath()%>/back-end/teacher/listAllTeacher.jsp'>點此查詢所有教職員資料</a><br><br>

  			

  			
  			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<c:forEach var="message" items="${errorMsgs}">
					<span style="color:red">${message}</span>
				</c:forEach>
			</c:if>
			
			
  			<table id="table-1">
  				<tr>
  					<td class="teahcerTitle">
						<b>輸入教職員編號 (如T001):</b>				
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/teacher/teacher.do" >
						<input type="text" name="t_num">
						<input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出">
						</FORM>
					</td>
				</tr>
				<tr>
					<td class="teahcerTitle">	
						<b>選擇教職員編號:</b>
					</td>
					<td>
						<jsp:useBean id="teacherSvc" scope="page" class="com.teacher.model.TeacherService" />
						<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/teacher/teacher.do" >
							<select size="1" name="t_num">
								<c:forEach var="teacherVO" items="${teacherSvc.all}" > 
									<option value="${teacherVO.t_num}">${teacherVO.t_num}
								</c:forEach>   
							</select>
							<input type="hidden" name="action" value="getOne_For_Display">
							<input type="submit" value="送出">
						</FORM>
					</td>
				</tr>
  				<tr>
  					<td class="teahcerTitle">
						<b>選擇教職員姓名:</b>
     				</td>
     				<td>
     					<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/teacher/teacher.do" >
     						<select size="1" name="t_num">
								<c:forEach var="teacherVO" items="${teacherSvc.all}" > 
									<option value="${teacherVO.t_num}">${teacherVO.t_name}
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
				<h3>新增教職員資料</h3>
				<a href='addTeacher.jsp'>Add a new Teacher.</a>
			</c:if>

		</div>
	</div>
	
</body>
	<%@ include file="/back-end/includePage/BootStrap"%>
</html>