
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resform.model.*"%>
<%@page autoFlush="true" buffer="1049kb"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="../Head"%>
<style>


.out {
	background-image: url("<%=request.getContextPath()%>/front-end/ResForm/image/29397135_p13.png") !important;
	background-repeat: repeat;
	margin: 0;
	padding: 0;
	min-height: 600px;
	height: 100%;
	width: 100%;
	border: 3px solid rgba(0, 0, 0, 0);
	padding: 0;
}
.responseMsg{
border:3px dashed black;
border-color:#796400;
border-radius:10px;
margin-left:auto;
margin-right:auto;
margin-top:100px;
text-align:center;
font-weight:bold;
width:500px;
padding:30px;
background-color:rgba(255, 255, 255, 0.5);

}

</style>


</head>
<body>

	<%@ include file="../Nav"%>
	<%@ include file="../Top"%>

	<!-- ======================================================================================= -->

	<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="out">
					<!-- ======================================================================================= -->

					<div class="context">


							<div class="responseMsg"><h3 class="text1" margin="20px">已成功預約！</h3><h3>請至E-mail信箱確認資訊！</h3><img src="<%=request.getContextPath()%>/front-end/ResForm/image/134958.png" width="100" height="100"></div>

					</div>
				</div>
			</div>
		</div>
	</section>
<%@ include file="footer_test"%>

</body>
<%@ include file="/back-end/includePage/BootStrap"%>



</html>