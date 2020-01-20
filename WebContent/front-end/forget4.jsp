<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.student.model.*"%>

<%
	StudentVO studentVO = (StudentVO) request.getAttribute("studentVO");
	StudentVO stp = (StudentVO) session.getAttribute("stp");
%>

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/css/lightBox.css" />
<script>
	$(document).ready(function() {

		$("#Glogin").click(function() {
			$(".outbox").show();
		});

		$("#X").click(function() {
			$(".outbox").hide();
		});

		$('.outbox').click(function(e) {
			if (e.target == this) {
				$(this).css("display", "none");
			}
		});

	});
</script>

<style type="text/css">
#loginImg {
	width: 50px;
	height: 60px;
}
</style>

<meta charset="utf-8">
<%@ include file="Head"%>
<title>Insert title here</title>

<style>
table {
	text-color: black;
}
</style>

</head>
<body>



	<!-- ======================================================================================= -->

	<div class="outbox" id="lightbox">
		<div class="lightbox-target" id="notice">
			<div class="content">

				<div class="meiSin">
					<i><b><span class="blue">美</span> <span class="lightblue">心</span>
							<span class="pink">幼</span> <span class="gold">兒</span> <span
							class="red">園</span><br> <span class="lightgreen">家</span> <span
							class="gold">長</span> <span class="pink">登</span> <span
							class="lightblue">入</span></b></i>
				</div>

				<%-- 錯誤表列 --%>
				<table id="error">
					<tr>
						<td colspan='2'><c:if test="${not empty errorMsgs}">
								<c:forEach var="message" items="${errorMsgs}">
									<span style="color: red">${message}</span>
									<br>
								</c:forEach>
							</c:if></td>
					</tr>
				</table>

				<br>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/student/student.do">
					<table>
						<tr>
							<th>帳號:</th>
							<td><input type='text' name='account' required
								value="<%=(stp == null) ? "" : stp.getSt_num()%>"
								onkeyup="this.value=this.value.toUpperCase()" /></td>
							<td rowspan='2'><input type="hidden" name="action"
								value="login">
								<button type="submit">
									<img
										src="<%=request.getContextPath()%>/front-end/images/user.png"
										title="登入" id="loginImg">
								</button></td>
						</tr>
						<tr>
							<th>密碼:</th>
							<td><input type='password' name='password' required /></td>
						</tr>
					</table>
				</FORM>

			</div>
		</div>
	</div>
	<%@ include file="Nav"%>
	<%@ include file="Top"%>
	<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="outer">
					<table>
						<tr>
							<td>請至您的信箱收取密碼</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</section>


	<!-- ======================================================================================= -->

	<!-- 	<section class="ftco-section"> -->
	<!-- 		<div class="container"> -->
	<!-- 			<div class="col-lg-12"> -->
	<!-- 				<div class="outer"></div> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</section> -->

	<!-- ======================================================================================= -->

	<%@ include file="Footer"%>
</body>
</html>