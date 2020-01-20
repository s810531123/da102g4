<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com._class.model.*"%>
<%@ page import="com.class_ann.model.*"%>

<!-- css import -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/class_ann/css/search_page.css">



<jsp:useBean id="class_annSvc" class="com.class_ann.model.Class_annService" />

<html>
<%@ include file="/back-end/includePage/Head"%>
<%@ include file="/back-end/includePage/Nav"%>
<%@ include file="/back-end/includePage/LeftSide"%>
<head>

<title>公告查詢頁面-2</title>
</head>
	
	<div class="col-lg-10 col-md-10 cb rightContent">

		<table>
		<thead>
			<tr>
				<th><b class="table_b">公告編號</b></th>
				<th><b class="table_b">班級編號</b></th>
				<th><b class="table_b">公告類別</b></th>
				<th><b class="table_b">公告標題</b></th>
				<th><b class="table_b">公告內容</b></th>
				<th><b class="table_b">公告時間</b></th>
			</tr>
		</thead>
		
		
		
		<tbody>
		<thead>
			<tr>
				<td><b class="table_bb">${class_annVO.cs_ann_num}</b></td>
				<td><b class="table_bb">${class_annVO.cs_num}</b></td>
				<td><b class="table_bb">${class_annVO.getCs_ann_kind()}</b></td>
				<td><b class="table_bb">${class_annVO.getCs_ann_ti()}</b></td>
				<td><b class="table_bb">${class_annVO.getCs_ann_text()}</b></td>
				<td><b class="table_bb">${class_annVO.getCs_ann_date()}</b></td>
			</tr>
		</thead>
		</table>

		<div><!--空白，排版用 --></div>
		
		<form method="post"	action="<%=request.getContextPath()%>/class/class.do_ann" class="form_under">
			<tr>以公告編號查詢:</tr>
			<tr><td><select name="cs_ann_num">
					<c:forEach var="class_annVO" items="${class_annSvc.all}">
						<option value="${class_annVO.cs_ann_num}">${class_annVO.cs_ann_num}
					</c:forEach>
				</select></td>
			</tr>
				
			<input type="hidden" name="action" value="getOne_For_Display"> 
			<input type="submit" value="開始查詢">
			
		</form>
		</tbody>
		
		
		<%--錯誤列表--%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">Oops!請再檢查一下!</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<div><!--空白，排版用 --></div>
		
		<a href="<%=request.getContextPath()%>/back-end/class_ann/select_page.jsp">
			<input type="button" class="pre" value="回上一頁">
		</a>

	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>

</html>