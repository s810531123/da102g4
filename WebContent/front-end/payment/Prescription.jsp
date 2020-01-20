<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.afterschoolclass.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../Head"%>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/payment/asc.css">	
</head>
<body >
	<%@ include file="../Nav"%>
	<%@ include file="../Top"%>
<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="outer">
				<!-- ----------------------------------------------------展示內容 -------------------------------------------------------------------------------------------------->
	<form>
					託藥內容<br><textarea cols="50" rows="5" maxlength="100"></textarea><br><input type="submit" value="送出">
					
	</form>
			<!-- ----------------------------------------------------展示內容 -------------------------------------------------------------------------------------------------->
		</div>
			</div>
		</div>
	</section>

	<%@ include file="../Footer"%>
</body>
</html>