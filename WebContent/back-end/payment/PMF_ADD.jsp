<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.paymentform.model.*"%>
<%@ page import="com.paymentproject.model.*"%>
<%@ page import="com.pmf_schedule.model.*"%>
<%
	PMP_Service pmp_dao = new PMP_Service();
	List<PMP_VO> list = pmp_dao.getAll();
	pageContext.setAttribute("list", list);
	
	PMF_Service pmf_dao = new PMF_Service();
	List<PMF_VO> list2 = pmf_dao.getAll();
	pageContext.setAttribute("list2", list2);
	
	ScheduleDAO dao = new ScheduleDAO();
	List<ScheduleVO> list3 = dao.getAll();
	pageContext.setAttribute("list3", list3);
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/payment/asc.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$("#datepicker").on("change", function() {
			console.log($(this).val());
		});
	});
</script>
<meta charset="UTF-8">
<%@ include file="/back-end/includePage/Head"%>
<title>Insert title here</title>
</head>
<!-- ----------------------------------------------------展示內容(覆蓋排程器) -------------------------------------------------------------------------------------------------->
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<div class="col-lg-10 col-md-10 cb rightContent" id="background">
		<br>
		
		<c:if test="${not empty errorMsgs}">		
			<ul>
				<c:forEach var="message" items="${errorMsgs}">				
					<li><b style="color: white">${message}</b></li>
				</c:forEach>
			</ul>			
		</c:if>
		<input type="button" value="繳費單項目管理"
			onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMP.jsp'">
		<input type="button" value="繳費單查詢"
			onclick="location.href='<%=request.getContextPath()%>/back-end/payment/PMF_Home.jsp'">
		<br>
		
		<br>
		<form METHOD="post"
			ACTION="<%=request.getContextPath()%>/payment/pmf.do">
			<p>
				請輸入日期 <input type="date" name="date" value=""> <br>
				<br> 請輸入增加的繳費單項目 
				<select size="1" name="pml_num">
					<c:forEach var="pmpVO" items="${list}">
						<option value="${pmpVO.pml_num}">${pmpVO.pml_item}
					</c:forEach>
				</select> 請輸入增加的繳費單對象 <select size="1" name="choose">
					<option value="所有學生">所有學生
					<option value="向日葵班">向日葵班
					<option value="玫瑰班">玫瑰班
					<option value="櫻花班">櫻花班
					<option value="坐娃娃車的學生">坐娃娃車的學生
				</select>
				 <input type="hidden" name="action" value="insert"> <input
					type="submit" value="確定">
			</p>
		</form>


		<table id="gradient-style" summary="Meeting Results">
					<thead>
						<tr>
							<th>排程編號</th>
							<th>繳費對象</th>
							<th>繳費單項目</th>
							<th>排程日期</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach var="scheduleVO" items="${list3}">
							<tr>
								<td scope="col">${scheduleVO.sdu_num}</td>
								<td scope="col">${scheduleVO.pml_num}</td>
								<td scope="col">${scheduleVO.pml_item}</td>
								<td scope="col">${scheduleVO.pml_time}</td>							
							</tr>
						</c:forEach>							
					</tbody>
				</table>	

		<!-- ----------------------------------------------------展示內容(顯示所有托育內容) -------------------------------------------------------------------------------------------------->
	</div>

	<%@ include file="/back-end/includePage/BootStrap"%>
</body>
</html>