<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com._class.model.*"%>
<%@ page import="com.student.model.*"%>
<%@ page import="com.student_leave_list.model.*"%>

<%
	Student_leave_listService student_leave_listSvc = new Student_leave_listService();
	List<Student_leave_listVO> list = student_leave_listSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>

<title>學生假單管理首頁-1</title>
<%@ include file="/back-end/includePage/Head"%>
</head>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/student_leave_list/css/select_page.css">
<body>

<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->
	<div class="col-lg-10 col-md-10 cb rightContent">

		<%@ include file="/back-end/student_leave_list/header_text"%>


		<table>
		
			<thead>
				<tr>

					<a href="<%=request.getContextPath()%>/back-end/student_leave_list/search_page.jsp"><b>條件查詢學生假單
						<img src="<%=request.getContextPath()%>/back-end/student_leave_list/photo/search_icon.png">
					</a>
				</tr>
			</thead>
			
		</table>
		<table>
			<thead>
				<tr>
					<th><b style="color:black">假單編號</th>
					<th><b style="color:black">學生學號</th>
					<th><b style="color:black">請假起始日期</th>
					<th><b style="color:black">請假結束日期</th>
					<th><b style="color:black">請假假別</th>
					<th><b style="color:black">請假事由</th>
				</tr>
			</thead>
			<%@ include file="page1.file"%>

			<tbody>
				<c:forEach var="student_leave_listVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td><b style="color: blue">${student_leave_listVO.st_list_num}</td>
						<td><b style="color: gray">${student_leave_listVO.st_num}</td>
						<td><b style="color: gray"><fmt:formatDate value="${student_leave_listVO.start_date}" pattern="yyyy-MM-dd HH:mm"/> </td>
						<td><b style="color: gray"><fmt:formatDate value="${student_leave_listVO.end_date}" pattern="yyyy-MM-dd HH:mm"/> </td>
						<td><b style="color: gray">${student_leave_listVO.st_list_sort}</td>
						<td><b style="color: gray">${student_leave_listVO.st_list_note}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@ include file="page2.file"%>
	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>

</html>