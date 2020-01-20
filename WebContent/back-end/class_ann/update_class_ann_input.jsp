<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com._class.model.*"%>
<%@ page import="com.class_ann.model.*"%>

<% 
	Class_annVO class_annVO = (Class_annVO)request.getAttribute("class_annVO");
%>


<html>
<head>

<title>修改公告頁面-2</title>
<%@ include file="/back-end/includePage/Head"%>
</head>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/class_ann/css/select_page.css">
<body>

	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->


	<div class="col-lg-10 col-md-10 cb rightContent">
		
	<div><!-- 排版用 --></div>
		
		<h1 class="up_h1">修改公告資料</h1>

			
		<%--錯誤列表 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">Oops!修改中出現錯誤!</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>


		<form method="post"
			action="<%=request.getContextPath()%>/class/class.do_ann"
			name="form_update1">
			<table>
				<tr>
					<td><b style="color:gray">班級公告編號</b></td>
					<td><b style="color:blue"><%=class_annVO.getCs_ann_num()%></b></td>
				</tr>
				
				
				<jsp:useBean id="classSvc" scope="page"
					class="com._class.model.ClassService" />
				
				<tr>
					<td><b style="color:gray">修改班級編號</b></td>
					<td><select name="cs_num">
							<c:forEach var="classVO" items="${classSvc.all}">
								<option value="${classVO.cs_num}"
									${(classVO.cs_num==class_annVO.cs_num)? 'selected':'' }>${classVO.cs_num}
							</c:forEach>
					</select></td>
				</tr>
			



<!-- 				<tr> -->
<!-- 					<td><b style="color:gray">原公告種類</b></td> -->
<%-- 					<td><b style="color:blue"><%=class_annVO.getCs_ann_kind()%></b></td> --%>
<!-- 				</tr> -->

				<tr>
					<td><b style="color:gray">新公告種類</b></td>
					<td><input type="text" name="cs_ann_kind" 
						value="<%=class_annVO.getCs_ann_kind()%>" /></td>
							
				</tr>

<!-- 				<tr> -->
<!-- 					<td><b style="color:gray">原公告標題</b></td> -->
<%-- 					<td><b style="color:blue"><%=class_annVO.getCs_ann_ti()%></b></td> --%>
<!-- 				</tr> -->
				<tr>
					<td><b style="color:gray">新公告標題</b></td>
					<td><input type="text" name="cs_ann_ti"
						value="<%=class_annVO.getCs_ann_ti()%>" /></td>
				</tr>
				
				
<!-- 				<tr> -->
<!-- 					<td><b style="color:gray">原公告內容</b></td> -->
<%-- 					<td><b style="color:blue"><%=class_annVO.getCs_ann_text()%></b></td> --%>
<!-- 				</tr> -->
				<tr>
					<td><b style="color:gray">新公告內容</b></td>
					<td><input type="text" name="cs_ann_text" 
						value="<%=class_annVO.getCs_ann_text()%>" /></td>
				</tr>
				
<!-- 				<tr> -->
<!-- 					<td><b style="color:gray">原公告時間</b></td> -->
<%-- 					<td><b style="color:blue"><%=class_annVO.getCs_ann_date()%></b></td> --%>
<!-- 				</tr> -->
				<tr>
					<td><b style="color:gray" >新公告時間</b></td>
					<td><input type="text" name="cs_ann_date"
						value="<%=class_annVO.getCs_ann_date()%>" /></td>
				</tr>
				
			</table>
			<input type="hidden" name="action" value="update"> <input
				type="hidden" name="cs_ann_num" value="<%=class_annVO.getCs_ann_num()%>">
			<input type="submit" value="送出修改">
		</form>
			
		<a
			href="<%=request.getContextPath()%>/back-end/class_ann/select_page.jsp"><input
			type="button" class="pre" value="回上一頁"></a>
	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>
</html>