<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com._class.model.*"%>
<%@ page import="com.class_ann.model.*"%>

<jsp:useBean id="class_annSvc" class="com.class_ann.model.Class_annService" />

<html>
<head>

<title>查詢公告</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/class_ann/css/select_page.css">
	<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/class_ann/css/search_page.css">
</head>
<%@ include file="/front-end/Head"%>
<%@ include file="/front-end/Nav"%>
	<!-- 自己的畫面 -->
	<div class="col-lg-10 col-md-10 cb rightContent">

		<form method="post"
			action="<%=request.getContextPath()%>/class/class.do_ann_front">
			<h3 class="sear_h3">以公告編號查詢</h3>
			<input type="text" name="cs_ann_num"> 
			<input type="hidden" name="action" value="getOne_For_Display"> 
			<input type="submit" value="開始查詢">
		</form>


		<table>
			<tr>
				<td><b style="color: black">公告編號</b></td>

				<td><b style="color: black">班級編號</b></td>

				<td><b style="color: black">公告類別</b></td>

				<td><b style="color: black">公告標題</b></td>

				<td><b style="color: black">公告內容</b></td>
				
				<td><b style="color: black">公告時間</b></td>
			</tr>

			<tr>
				<td><b style="color:blue">${class_annVO.cs_ann_num}</b></td>
				<td><b style="color:gray">${class_annVO.cs_num}</td>
				<td><b style="color:gray">${class_annVO.getCs_ann_kind()}</td>
				<td><b style="color:gray">${class_annVO.getCs_ann_ti()}</td>
				<td><b style="color:gray">${class_annVO.getCs_ann_text()}</td>
				<td><b style="color:gray">${class_annVO.getCs_ann_date()}</td>
			</tr>

		</table>

		<%--錯誤列表--%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">Oops!請再檢查一下!</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<a
			href="<%=request.getContextPath()%>/front-end/class_ann/select_page.jsp"><input
			type="button" class="pre" value="回上一頁"></a>

	</div>
</body>
<%-- <%@ include file="/front-end/Footer"%> --%>

</html>