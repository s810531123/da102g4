<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resform.model.*"%>

<%
	ResformService resformSvc = new ResformService();
	List<ResformVO> list = resformSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="teacherSvc" scope="page"
	class="com.teacher.model.TeacherService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>所有預約單資料</title>
<style>
div.context {
	background-image: url(image/backimgjpg.jpg);
	background-repeat: repeat;
	margin: 0;
	padding: 50px;
	height: 100%;
	border: none;
}

table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	z-index: 3;
	border: 1px solid #0068ad;
}

th, td {
	padding: 5px;
	text-align: center;
}

th {
	background-color: #e7f3f4;
}
</style>
<%@ include file="/back-end/includePage/Head"%>
</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->

	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="own">
			<div class="row">
				<div class="col">
					<a href='search_page.jsp'><button type="button"
							class="btn-danger float-right">刪除預約單</button></a> <a
						href='update_res_list.jsp'><button type="button"
							class="btn-warning float-right">修改預約單</button></a> <a
						href='search_page.jsp'><button type="button"
							class="btn-success float-right">查詢預約單</button></a> <a
						href='getAllResForm.jsp'><button type="button"
							class="btn-link float-left">預約單列表</button></a>
				</div>
			</div>
		</div>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="context">

			<table>
				<tr>
					<th>預約導覽教師</th>
					<th>預約單編號</th>
					<th>預約人姓名</th>
					<th>預約人E-mail</th>
					<th>預約人電話號碼</th>
					<th>預約日期時間</th>
					<th>備註</th>
					<th>刪除預約單</th>
				</tr>
				<c:forEach var="resformVO" items="${list}" begin="0">

					<tr>
						<td><c:forEach var="teacherVO" items="${teacherSvc.all}">
								<c:if test="${resformVO.t_Num==teacherVO.t_num}">
									${teacherVO.t_name}
                    </c:if>
							</c:forEach></td>
						<td>${resformVO.res_Num}</td>
						<td>${resformVO.res_Name}</td>
						<td>${resformVO.res_Email}</td>
						<td>${resformVO.res_Phone}</td>
						<td>${resformVO.res_Date}</td>
						<td>${resformVO.res_Msg}</td>
						<td>
							<FORM METHOD="post" ACTION="resform.do" style="margin-bottom: 0px;">
								<input type="hidden" value="${resformVO.res_Num}"> <input
									class="hover" type="image" id="${resformVO.res_Num}" name="submit"
									src="image/delete_icon.png" alt="Submit" width="30px"
									height="30px" /> <input type="hidden" name="res_Num"
									value="${resformVO.res_Num}"> <input type="hidden"
									name="action" value="delete">

							</FORM></td>
					</tr>
								<script type="text/javascript">
								//用EL的方式取得餐點編號當ID?

								$(".hover")
										.mouseover(
												function() {
													$(this)
															.attr('src',
																	'image/delete_iconhover.png');
												})

								$(".hover").mouseout(
										function() {
											$(this).attr('src',
													'image/delete_icon.png');
										})
										
										
										
								$(".hover2")
										.mouseover(
												function() {
													$(this)
															.attr('src',
																	'image/pen_move.gif');
												})

								$(".hover2").mouseout(
										function() {
											$(this).attr('src',
													'image/pen.png');
										})

								// 	function onHover() {
								// 		var no = $(this).next().val();
								// 		console.log(no)
								// 		$(this).attr('src', 'image/delete_iconhover.png');
								// 		console.log($(this).attr("src"))

								// 	}

								// 	function offHover() {
								// 		$('#${menuVO.mu_Num}').attr('src', 'image/delete_icon.png');
								// 	}
							</script>
				</c:forEach>
			</table>

		</div>
	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>
<link href="css/resform.css" rel="stylesheet" type="text/css" />
</html>
