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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/class_manage/css/select_page.css">
<title>查詢學生假單頁面-2</title>
<%@ include file="/back-end/includePage/Head"%>
</head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/class_manage/css/search_page.css">
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->
	<div class="col-lg-10 col-md-10 cb rightContent">

		<form method="post"
			action="<%=request.getContextPath()%>/student_leave/student_leave.do">
			<h3 class="sear_h3">以 假單編號 查詢</h3>
			<input type="text" name="st_list_num"> <input type="hidden"
				name="action" value="getOne_For_Display"> <input
				type="submit" value="開始查詢">
		</form>
		

		<table>
			<tr>
				<td><b style="color: black">假單編號</b></td>

				<td><b style="color: black">學生學號</b></td>

				<td><b style="color: black">請假起始日期</b></td>

				<td><b style="color: black">請假結束日期</b></td>

				<td><b style="color: black">請假假別</b></td>
				
				<td><b style="color: black">請假事由</b></td>
			</tr>

			<tr>
				<td><b style="color: blue">${student_leave_listVO.getSt_list_num()}</td>
				<td><b style="color: gray">${student_leave_listVO.getSt_num()}</td>
				<td><b style="color: gray"><fmt:formatDate value="${student_leave_listVO.getStart_date()}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><b style="color: gray"><fmt:formatDate value="${student_leave_listVO.getEnd_date()}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><b style="color: gray">${student_leave_listVO.getSt_list_sort()}</td>
				<td><b style="color: gray">${student_leave_listVO.getSt_list_note()}</td>
			</tr>

<%-- 				<%@ include file="page1.file"%> --%>
			
				
		</table>
<%-- 				<%@ include file="page2.file"%> --%>
		<%--錯誤列表--%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: purple">Oops!請再檢查一下!</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: purple">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		
		<a
			href="<%=request.getContextPath()%>/back-end/student_leave_list/select_page.jsp"><input
			type="button" class="pre" value="回上一頁"></a>

	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>

</html>