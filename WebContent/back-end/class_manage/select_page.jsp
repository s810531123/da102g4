<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com._class.model.*"%>
<%@ page import="com.teacher.model.*"%>

<%
	ClassService classSvc = new ClassService();
	List<ClassVO> list = classSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	ClassVO classVO = (ClassVO) request.getAttribute("classVO");
%>
<jsp:useBean id="teacherSvc" class="com.teacher.model.TeacherService" />



<html>

<head>
<meta charset="UTF-8">
<title>班級資料管理首頁-1</title>
<%@ include file="/back-end/includePage/Head"%>
</head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/class_manage/css/select_page.css">
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->
	<div class="col-lg-10 col-md-10 cb rightContent">
<!-- 		<div class="own"> -->
			
			<%@ include file="/back-end/class_manage/header_text"%>
			<table>
				<thead>
					<tr>

						<a
							href="<%=request.getContextPath()%>/back-end/class_manage/search_page.jsp"><button
								class="search_btn">查詢班級資料</button></a>


						<a
							href="<%=request.getContextPath()%>/back-end/class_manage/add_class.jsp"><button
								class="new_btn">新增班級資料</button></a>

					</tr>
				</thead>
			</table>
			<table>
				<thead>
					<tr>
						<th>班級編號</th>
						<th>班級名稱</th>
						<th>班導師</th>
						<th>副班導師</th>
						<th>入學年度</th>
						<th>修改資料</th>
						<th>刪除資料</th>
					</tr>
				</thead>
				<%@ include file="page1.file"%>
				<tbody>
					<c:forEach var="classVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td><b style="color: blue">${classVO.cs_num}</td>
							<td><b style="color: gray">${classVO.cs_name}</td>
							<td><b style="color: gray">${classVO.t_num_1}  <b>${teacherSvc.getOneTeacher(classVO.t_num_1).t_name}</td>
							<td><b style="color: gray">${classVO.t_num_2}  <b>${teacherSvc.getOneTeacher(classVO.t_num_2).t_name}</td>
							<td><b style="color: gray">${classVO.cs_years}</td>
							<td>
								<form method="post"
									action="<%=request.getContextPath()%>/class/class.doclass">
									<input type="submit" value="修改"> <input type="hidden"
										name="cs_num" value="${classVO.cs_num}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</form>
							</td>
							<td>
								<form method="post"
									action="<%=request.getContextPath()%>/class/class.doclass">
									<input type="submit" value="刪除"> <input type="hidden"
										name="cs_num" value="${classVO.cs_num}"> <input
										type="hidden" name="action" value="delete">
								</form>
							</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			<%@ include file="page2.file"%>
				
			
<!-- 		</div> -->
	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>

</html>
	