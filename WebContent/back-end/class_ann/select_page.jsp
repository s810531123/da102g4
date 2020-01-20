<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com._class.model.*"%>
<%@ page import="com.class_ann.model.*"%>

<!-- css import -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/class_ann/css/select_page.css">


<%
	Class_annService class_annSvc = new Class_annService();
	List<Class_annVO> list = class_annSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	Class_annVO class_annVO = (Class_annVO) request.getAttribute("class_annVO");
%>

<html>
<%@ include file="/back-end/includePage/Head"%>
<%@ include file="/back-end/includePage/Nav"%>
<%@ include file="/back-end/includePage/LeftSide"%>
<head>

<title>班級公告管理首頁-1</title>
</head>

	
<body>

	<div class="col-lg-10 col-md-10 cb rightContent">
	
	
	
		<!-- img -->
		<img src="<%=request.getContextPath()%>/back-end/class_ann/photo/11111.png" class="mypic">
	
	
	
	<!-- 	color-text	 -->
	<%@ include file="/back-end/class_ann/header_text"%>

	
	
	
		<!-- 	button-href -->
		<table>
			<thead>
				<tr>
					<a href="<%=request.getContextPath()%>/back-end/class_ann/search_page.jsp">
						<button class="search_btn">查詢班級公告資料</button>
					</a>


					<a href="<%=request.getContextPath()%>/back-end/class_ann/add_ann.jsp">
						<button class="new_btn">新增班級公告資料</button>
					</a>
				</tr>
			</thead>
		</table>



		
		
		<table>
			<thead>
				<tr>
					<th><b class="table_b">公告編號</b></th>
					<th><b class="table_b">班級編號</b></th>
					<th><b class="table_b">公告種類</b></th>
					<th><b class="table_b">公告標題</b></th>
					<th><b class="table_b">公告內容</b></th>
					<th><b class="table_b">公告日期</b></th>
					<th><b class="table_b">修改公告</b></th>
					<th><b class="table_b">刪除公告</b></th>
				</tr>
			</thead>
			
			<%@ include file="page1.file"%>


			<tbody>
				<c:forEach var="class_annVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
<%-- 					<c:if test="${('園長' != tp.t_job) && (tp.cs_num == class_annVO.cs_num)}"> --%>
					<tr>
						<td><b class="table_bb">${class_annVO.cs_ann_num}</b></td>
						<td><b class="table_bb">${class_annVO.cs_num}</b></td>
						<td><b class="table_bb">${class_annVO.cs_ann_kind}</b></td>
						<td><b class="table_bb">${class_annVO.cs_ann_ti}</b></td>
						<td><b class="table_bb">${class_annVO.cs_ann_text}</b></td>
						<td><b class="table_bb">${class_annVO.cs_ann_date}</b></td>
						<td>
							<form method="post" action="<%=request.getContextPath()%>/class/class.do_ann">
								<input type="submit" value="修改" class="btn_update"> 
								<input type="hidden" name="cs_ann_num" value="${class_annVO.cs_ann_num}"> 
								<input type="hidden" name="action" value="getOne_For_Update">
							</form>
						</td>	
						
						<td>
							<form method="post" action="<%=request.getContextPath()%>/class/class.do_ann">
								<input type="submit" value="刪除" class="btn_delete"> 
								<input type="hidden" name="cs_ann_num" value="${class_annVO.cs_ann_num}">
								<input type="hidden" name="action" value="delete">
							</form>
						</td>
					</tr>
<%-- 					</c:if> --%>
				</c:forEach>
			</tbody>
		</table>
		<%@ include file="page2.file"%>
		
		
		
	</div>
	
	
	<!-- import swal -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
	<script src="sweetalert2.all.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
	
	
	<!-- set swal -->
	<c:if test="${add_ann}">
	<script>
Swal.fire({
  position: 'middle',
  type: 'success',
  title: '新增成功 !',
  showConfirmButton: false,
  timer: 2250
})
	</script>
	</c:if>
	
</body>
<%@ include file="/back-end/includePage/BootStrap"%>
</html>