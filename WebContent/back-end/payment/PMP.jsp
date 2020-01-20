<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.paymentproject.model.*"%>
<%
	PMP_Service dao = new PMP_Service();
	List<PMP_VO> list = dao.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

<%@ include file="/back-end/includePage/Head"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/payment/asc.css">
<script>
	$(function() {
		$("#pml_item>input").change(
				function() {
					$(this).parents().next().next().find(".pml_item").attr(
							"value", $(this).val());
				});
		$("#pml_money>input").change(
				function() {
					$(this).parents().next().find(".pml_money").attr("value",
							$(this).val());
				});
	});
</script>
</head>
<body>
<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<div class="col-lg-10 col-md-10 cb rightContent" id="background">
	<br>	
	<div>
	<c:if test="${not empty errorMsgs}">
				<font><b style="color: white;font-size:30px">請修正以下錯誤</b></font>
				<br>
				<br>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
					<u style="color: white">${message}</u><br>
					</c:forEach>
				</ul>
			</c:if>
		<input type="button" value="繳費單查詢"
				onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMF_Home.jsp'">
			<input type="button" value="繳費單設定"
				onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMF_ADD.jsp'">
				</div>
				
			<form METHOD="post"
				ACTION="<%=request.getContextPath()%>/payment/pmp.do">
				<div id="insert">
					新增繳費項目名稱
					 <input type="text" name="Pml_item" maxlength="10"	value=" "> 繳費項目金額 
					 <input type="text" maxlength="10"	name="Pml_money" value=" "> 
					 <input type="hidden" name="action" value="insert"> 
					 <input type="submit" value="新增">
				</div>
			</form>
				<%@ include file="page1.file" %> 
			<table id="gradient-style" summary="Most Favorit Movies">
				<thead>
					<tr>
						<th scope="col">繳費項目編號</th>
						<th scope="col">繳費項目名稱</th>
						<th scope="col">繳費項目金額</th>
						<th scope="col">修改項目</th>
						<th scope="col">刪除項目</th>
					</tr>
				</thead>
				<tbody>
			
					<c:forEach var="pmpVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr class="alt">
							<td>${pmpVO.pml_num}</td>

							<td id="pml_item">
							<input type="text" maxlength="10" id="pml_num" class="pml_num"
								value="${pmpVO.pml_item}" style="text-align: center; width:200px"></td>

							<td id="pml_money">
							<input type="text" maxlength="6"
								value="${pmpVO.pml_money}" style="text-align: center; width:200px"></td>



							<td id="pml">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/payment/pmp.do">
									<input type="hidden" name="pml_num" class="pml_num"
										value="${pmpVO.pml_num}"> <input type="hidden"
										name="pml_money" class="pml_money" value="${pmpVO.pml_money}">
									<input type="hidden" name="pml_item" class="pml_item"
										value="${pmpVO.pml_item}"> <input type="hidden"
										name="action" value="update"> <input type="submit"
										value="修改">
								</FORM>
							</td>


							<td><FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/payment/pmp.do">
									<input type="submit" value="刪除"> <input type="hidden"
										name="pml_num" value="${pmpVO.pml_num}"> <input
										type="hidden" name="action" value="delete">
								</FORM></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>		
			<%@ include file="page2.file" %>
			
	</div>
	
<%@ include file="/back-end/includePage/BootStrap"%>
</body>

</html>