<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.student.model.*"%>
<%@ page autoFlush="true" buffer="1094kb"%><!-- 解決執行緒相衝問題 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="../Head"%>
<title>Insert title here</title>
<style type="text/css">
.outer {
	background: #FFFFFF !important;
}

body{
	color: #000000;
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
			<div align="center">
				<div class="outer">
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<c:if test="${(stp.st_num == null) }"> 監護人請先登入帳號  </c:if>
					<c:if test="${!(stp.st_num == null) }">
						
						<div>
							<img src="image/commBook.jpg" width="500px">
						</div><br>
						
						<!-- 指定日期查詢 -->
						<div>
							<FORM METHOD="post" ACTION="commBook.do">
								<input type="hidden" name="action" value="getOne_For_Display">
								<h5>請選擇日期：
								<input name="comm_Date" id="f_date1" type="text" >
								<button class="btn btn-secondary cancel">查詢</button>
								
							</FORM><br>		
						</div>
	
						<!-- 查詢最近二筆資料 -->
						<%@ include file="getOneStudentRecentDay.jsp"%>	
					</c:if>
				</div>
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

	<%@ include file="footer"%>
	<%@ include file="DateTime"%>
</body>

</html>