
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resform.model.*"%>


<%
	ResformVO resformVO = (ResformVO) request.getAttribute("resformVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="../Head"%>
<style>
#table-1 {
	border: 3px dotted black;
	margin-left: auto;
	margin-right: auto;
	padding: 7px;
	margin-top: 20px;
}

#table-2 {
	border: 3px dotted black;
	margin-left: auto;
	margin-right: auto;
	padding: 7px;
	margin-top: 20px;
}

#table-1, th, td {
	border: 2px dotted black;
	padding: 8px;
	font-family: Microsoft JhengHei !important;
	color: #0B4114;
	background-color: #FFF1EB;
	font-weight: bolder;
}

.out {
	background-image: url(image/29397135_p13.png) !important;
	background-repeat: repeat;
	margin: 0;
	padding: 0;
	min-height: 600px;
	height: 100%;
	width: 100%;
	border: 3px solid rgba(0, 0, 0, 0);
	padding: 0;
}

#titleimage {
	height: 35%;
	width: 35%;
	display: block;
	margin: auto;
	margin-top: 20px;
}

.titleimage {
	background-color: rgba(255, 255, 255, 0.5);
	margin: auto;
}

.textout:hover {
	background-color: #FFF8EB;
}

.sbmbtn {
	position: absolute;
	left: 45%;
	margin-top: 20px;
}
.modaltitle{
margin-left:auto;
margin-right:auto;
}
#myBtn{
position: absolute;
	left: 47%;
	margin-top: 20px;
}
#amazingbtn{position: absolute;
	left: 40%;
	margin-top: 20px;}
</style>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 5; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 100%;
	height: 80%;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>

<script>
	$(document).ready(function() {
		$(".in").focusin(function() {
			$(this).css("background-color", "#FFF8EB");
		});
		$(".in").focusout(function() {
			$(this).css("background-color", "#FFFFFF");
		});
	});

	$(document).ready(function() {
		$(".in").focusin(function() {
			$(this).css("background-color", "#FFF8EB");
		});
		$(".in").focusout(function() {
			$(this).css("background-color", "#FFFFFF");
		});
	});
</script>
<script>
	$(document).ready(function() {
		 
			$("#amazingbtn").click(function(){
				$("#name1").val("當地媽媽");
				$("#email1").val("da102g4@gmail.com");
				$("#cellphone1").val("0912345678");
				$("#msg1").val("恐龍家長一號^-<");
			
			});
			
		$("#myBtn").click(function() {

			var result1 = $("#name1").val();
			$("#name2").val(result1);

			var result2 = $("#email1").val();
			$("#email2").val(result2);

			var result3 = $("#cellphone1").val();
			$("#cellphone2").val(result3);

			var result4 = $("#f_date3").val();
			$("#f_date4").val(result4);

			var result5 = $("#msg1").val();
			$("#msg2").val(result5);

		});
	});
	
	
	
</script>

</head>
<body>

	<%@ include file="../Nav"%>
	<%@ include file="../Top"%>

	<!-- ======================================================================================= -->

	<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="out">

					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<!-- ======================================================================================= -->

					<div class="context">
						<div class="titleimage">

							<img src="image/resformtitle.png" id="titleimage">

						</div>



						<table id="table-1">

							<tr>
								<td>預約人姓名：</td>
								<td><input class="in" type="TEXT" id="name1" size="20" placeholder=" 請輸入您的姓名" /></td>
							</tr>
							<tr>
								<td>預約人E-mail：</td>
								<td><input class="in" type="TEXT" id="email1" size="35" placeholder=" 請輸入E-mail"/></td>
							</tr>
							<tr>
								<td>預約人手機號碼：</td>
								<td><input class="in" type="TEXT" id="cellphone1" size="35" placeholder=" 請輸入您的手機號碼共10碼"
									maxlength="10" class="in" /></td>
							</tr>
							<tr>
								<td>預約時間：</td>
								<td><input class="in" id="f_date3" type="text" size="28" placeholder=" 請點我一下選擇預約時間"></td>
							</tr>
							<tr>
								<td>備註：</td>
								<td><textarea class="in" rows="5" cols="38" id="msg1" placeholder=" 其他想說的事情:)" /></textarea></td>
							</tr>
						</table>
						
						

						<button id="myBtn">確認預約單資料</button>
						<button id="amazingbtn">★</button>


					</div>

				</div>
			</div>
		</div>
	</section>


	<!-- The Modal -->
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<span class="close">&times;</span>
<h3 class="modaltitle">請確認您的預約單</h3>
			<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/ResForm/resform.do" name="form1">

				<table id="table-2">
					
					<tr>
						<td>預約人姓名：</td>
						<td><input class="in" type="TEXT" id="name2" name="res_Name"
							size="20" /></td>
					</tr>
					<tr>
						<td>預約人E-mail：</td>
						<td><input class="in" type="TEXT" id="email2"
							name="res_Email" size="35" /></td>
					</tr>
					<tr>
						<td>預約人手機號碼：</td>
						<td><input class="in" type="TEXT" id="cellphone2"
							name="res_Phone" size="35" maxlength="10" class="in" /></td>
					</tr>
					<tr>
						<td>預約時間：</td>
						<td><input class="in" name="res_Date" id="f_date4"
							type="text" size="28"></td>
					</tr>
					<tr>
						<td>備註：</td>
						<td><textarea class="in" name="res_Msg" rows="5" cols="38"
								id="msg2" /></textarea></td>
					</tr>
				</table>

				<input type="hidden" name="action" value="insert">
				<div class="sbmbtn">
					<input type="submit" value="送出表單">
				</div>

			</FORM>

		</div>

	</div>

	<script>
		// Get the modal
		var modal = document.getElementById("myModal");

		// Get the button that opens the modal
		var btn = document.getElementById("myBtn");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks the button, open the modal 
		btn.onclick = function() {
			modal.style.display = "block";
		}

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>

	<%@ include file="../Footer"%>
</body>


<%
	java.sql.Timestamp res_Date = null;
	try {
		res_Date = resformVO.getRes_Date();
	} catch (Exception e) {
		res_Date = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>
<%@ include file="DateTime.file"%>
<%@ include file="/back-end/includePage/BootStrap"%>



</html>