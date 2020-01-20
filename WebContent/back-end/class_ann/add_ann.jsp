<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_ann.model.*" %>
<%@ page import="com._class.model.*"%>
<%
	Class_annVO class_annVO = (Class_annVO)request.getAttribute("class_annVO");
%>

<!DOCTYPE html>
<html>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/class_ann/css/ann_new.css">
<script src="<%=request.getContextPath()%>/back-end/vendors/jquery/jquery-1.12.4.min.js"></script>
<head>

<title>新增班級公告頁面-2</title>

<%@ include file="/back-end/includePage/Head"%>
<%@ include file="/back-end/includePage/Nav"%>
<%@ include file="/back-end/includePage/LeftSide"%>
</head>
<body>

	<div class="col-lg-10 col-md-10 cb rightContent">



		<h1 class="add_h1">新增班級公告</h1>

		<a class="a"
			href="<%=request.getContextPath()%>/back-end/class_ann/select_page.jsp"><input
			type="button" class="pre" value="回上一頁"></a>

		<%--錯誤列表--%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red" size="5px">請修正以下錯誤</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: purple">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		
		

		<form method="post" action="<%=request.getContextPath()%>/class/class.do_ann" name="form_add">
		
			<table class="table">
			<jsp:useBean id="classSvc" scope="page" class="com._class.model.ClassService" />
			
				<tr>
					<td><b class="table_b">班級編號</b></td>
					<td><select name="cs_num">
							<c:forEach var="classVO" items="${classSvc.all}">
								<option value="${classVO.cs_num}"
									${(class_annVO.cs_num==classVO.cs_num)? 'selected':'' }>${classVO.cs_num}
							</c:forEach>
					</select></td>
				</tr>


				<tr>
					<td><b class="table_b">公告種類</b></td>
					<td><input type="text" name="cs_ann_kind"
						value="<%=(class_annVO == null) ? "" : class_annVO.getCs_ann_kind()%>" class="q1" />
					</td>
				</tr>
				
				
				<tr>
					<td><b class="table_b">公告標題</b></td>
					<td><input type="text" name="cs_ann_ti"
						value="<%=(class_annVO == null) ? "" : class_annVO.getCs_ann_ti()%>"  class="q2" />
					</td>
				</tr>
				
				
				<tr>
					<td><b class="table_b">公告內容</b></td>
					<td><input type="text" name="cs_ann_text"
						value="<%=(class_annVO == null) ? "" : class_annVO.getCs_ann_text()%>" class="q3" />
					</td>
				</tr>


				<tr>
					<td><b class="table_b">公告日期</b></td>
					<td><input type="text" name="cs_ann_date" placeholder=" 例 : 2019-01-01"
						value="<%=(class_annVO == null) ? "" : class_annVO.getCs_ann_date()%>" class="q4" />
					</td>
				</tr>
				
			</table>
			
			<input type="hidden" name="action" value="insert"> 
			<input type="submit" class="submit" value="送出新增"> 
			<input type="reset" class="reset" value="重新填寫">
			
		</form>
		
		
<!----------------------------------------------------------------------------------------->
		<div>
		<!-- 神奇小按鈕 -->
		<button class="quick_btn">
			<br>Magic
			<img src="<%=request.getContextPath()%>/back-end/class_ann/photo/magic_btn.png">
		</button>
		</div>
<!----------------------------------------------------------------------------------------->


	</div>
</body>


<script>
	$(document).ready(function() {
		$('.quick_btn').click(function() {
			$('.q1').attr('value','班級活動');
			$('.q2').attr('value','班級觀摩日注意事項');
			$('.q3').attr('value','8======D   毛毛蟲 ');
			$('.q4').attr('value','2019-09-26');
		});
	});


</script>


<%@ include file="/back-end/includePage/BootStrap"%>
</html>	
