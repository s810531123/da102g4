<%@page import="com.student.model.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.afterschoolclass.model.*"%>
<%
	ASC_Service dao = new ASC_Service();
	StudentVO stp = (StudentVO) session.getAttribute("stp");
	pageContext.setAttribute("list", dao.getOneStu(stp.getSt_num()));
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/payment/asc.css">
<meta charset="UTF-8">
<%@ include file="../Head"%>

<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/payment/asc.css">
</head>
<body>
	<%@ include file="../Nav"%>
	<%@ include file="../Top"%>
	<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="outer">

					<br>



					<table id="gradient-style" summary="Meeting Results">
						<thead>
							<tr>
								<th scope="col">托育編號</th>
								<th scope="col">托育費用</th>
								<th scope="col">離校時間</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="ascVO" items="${list}" begin="0" end="100">
								<tr>
									<td>${ascVO.nursery_no}</td>
									<td>${ascVO.st_sum }</td>
									<td>${ascVO.time }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- ---------------------------------------------------顯示查詢結果 -------------------------------------------------------------------------------------------------->
					<!-- ----------------------------------------------------展示內容 -------------------------------------------------------------------------------------------------->
				</div>
			</div>
		</div>
	</section>

	<%@ include file="../Footer"%>
</body>
</html>