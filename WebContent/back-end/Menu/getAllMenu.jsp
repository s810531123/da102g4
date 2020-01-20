<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<%
	MenuService menuSvc = new MenuService();
	List<MenuVO> list = menuSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>

<title>所有餐點資料</title>


<style>
div.context {
	background-image: url(image/foodbackground.png);
	background-repeat: repeat;
	margin: 0;
	padding: 50px;
	height: 100%;
	border: none;
}

table {
	width: 90%;
	background-color: white;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: 25px;
}

table, th, td {
	z-index: 3;
	border: 2px solid #622F2D;
	border-style: solid;
}

th, td {
	padding: 5px;
	text-align: center;
}

th {
	background-color: #e7f3f4;
}

div.inputsomething {
	background-color: rgba(255, 255, 255, 0.6);
	border-radius: 10px;
	border-style: dotted;
	border-color: #743a3a;
	padding: 20px;
	border-width: 3px;
	margin: 20px;
	width: 90%;
	margin-left: auto;
	margin-right: auto;
}

<
style>.izumi-color {
	border-color: none;
}
</style>
<%@ include file="/back-end/includePage/Head"%>
</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->

	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="izumi-color">

			<div class="row">
				<div class="col">
					<a href='MenuAdd.jsp'>
						<button type="button" class="btn-success float-right">新增餐點</button>
					</a> <a href='menu_select_page.jsp'><button type="button"
							class="btn-warning float-right">查詢餐點</button></a> <a
						href='getAllMenu.jsp'>
						<button type="button" class="btn-link float-left">餐點列表</button>
					</a>
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
				<div class="inputsomething">
					<table>
						<tr>
							<th>餐點編號</th>
							<th>餐點名稱</th>
							<th>餐點時段</th>
							<th>餐點日期</th>
							<th>修改</th>
							<th>刪除</th>
						</tr>
						<%@ include file="page1.file"%>
						<c:forEach var="menuVO" items="${list}" varStatus="idx"
							begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

							<tr>
								<td>${menuVO.mu_Num}</td>
								<td>${menuVO.mu_Name}</td>
								<td>${menuVO.mu_Time}</td>
								<td>${menuVO.mu_Date}</td>
								<td>
									<FORM METHOD="post" ACTION="menu.do"
										style="margin-bottom: 0px;">
										<input type="hidden" value="${menuVO.mu_Num}"> <input class="hover2" type="image" id="${menuVO.mu_Num}"
											name="submit" src="image/pen.png" alt="Submit" width="30px" height="30px" /> 
											<input type="hidden" name="mu_Num" value="${menuVO.mu_Num}"> 
											<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post" ACTION="menu.do"
										style="margin-bottom: 0px;">
										<input type="hidden" value="${menuVO.mu_Num}"> <input class="hover" type="image" id="${menuVO.mu_Num}"
											name="submit" src="image/delete_icon.png" alt="Submit" width="30px" height="30px" /> 
											<input type="hidden" name="mu_Num" value="${menuVO.mu_Num}"> 
											<input type="hidden" name="action" value="delete">

									</FORM>
								</td>
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

			<%@ include file="page2.file"%>
		</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>
<link href="css/menu.css" rel="stylesheet" type="text/css" />
</html>
