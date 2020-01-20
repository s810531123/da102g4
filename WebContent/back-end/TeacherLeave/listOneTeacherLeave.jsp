<%@page import="java.util.Map"%>
<%@page import="java.util.Hashtable"%>
<%@page import="com.teacherLeave.model.TeacherLeaveVO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>假單資料 - listOneTeacherLeave.jsp</title>
<%@ include file="/back-end/includePage/Head"%>
<!-- 自己的css -->
<link rel="stylesheet" href="./css/listOne.css">
</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="color-manto">
			<%
				TeacherLeaveVO teacherLeaveVO = (TeacherLeaveVO) request.getAttribute("teacherLeaveVO");
				Map<Integer, String> reason = (Hashtable<Integer, String>) application.getAttribute("leaveReason");				
				Map<Integer, String> status = (Hashtable<Integer, String>) application.getAttribute("leaveStatus");
			%>
			<c:if test="${sessionScope.tp.t_num == null}">請先登入</c:if>
			<c:if test="${!(sessionScope.tp.t_num == null)}">
			<c:if test="${!(sessionScope.tp.t_num == 'T001')}">			
					<table border="1">
						<tr>
							<th>假單編號</th>
							<th>教師姓名</th>
							<th>假單起始日期</th>
							<th>假單結束日期</th>
							<th>請假假別</th>
							<th>請假事由</th>
							<th>假單申請日</th>
							<th>假單狀態</th>
						</tr>
						<tr>
							<td>${teacherLeaveVO.tl_Id}</td>
							<td>
								${tp.t_name}
							</td>
							<td>${teacherLeaveVO.tl_Sdate}</td>
							<td>${teacherLeaveVO.tl_Edate}</td>
							<td><%=reason.get(teacherLeaveVO.getTl_Type())%></td>
							<td>${teacherLeaveVO.tl_Reason}</td>
							<td>${teacherLeaveVO.tl_App_Date}</td>
							<td><%=status.get(teacherLeaveVO.getTl_Status())%></td>
						</tr>
					</table>
			</c:if>
			</c:if>
		</div>
	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>
</html>