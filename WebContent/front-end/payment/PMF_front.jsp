
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.paymentform.model.*"%>
<%@page import="com.afterschoolclass.model.ASC_Service"%>
<%@page import="com.student.model.StudentVO"%>
<!DOCTYPE html>
<html>
<head>
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
				<div class="outer" >

					<!-- ----------------------------------------------------展示內容(查詢所有繳費單) -------------------------------------------------------------------------------------------------->
				
					<%
						PMF_Service dao = new PMF_Service();
						ASC_Service dao1 = new ASC_Service();
						StudentVO stp = (StudentVO) session.getAttribute("stp");						
						List<PMF_VO> list = dao.getOnePMF(stp.getSt_num());						
						pageContext.setAttribute("list", list);
					%>

					<table id="gradient-style" summary="Employee Pay Sheet">
						<thead>
							<tr>
								<th scope="col">繳費單編號</th>
								<th scope="col">年月份</th>
								<th scope="col">繳費金額</th>
								<th scope="col">繳費狀態</th>
								<th scope="col">繳費時間</th>
								<th scope="col">繳費方式</th>
					 		</tr>
						</thead>
						<tbody>
							<c:forEach var="pmfVO" items="${list}" begin="0" end="100">
								<tr>
									<td>
										<form METHOD="post"	ACTION="<%=request.getContextPath()%>/payment/pmf.do">
											<input type="hidden" name="action" value="select4"> <input
												type="hidden" name="pf_num" value="${pmfVO.pf_num}">
											<input type="submit" value="${pmfVO.pf_num}">
										</form>
									</td>
									<td><fmt:formatDate value="${pmfVO.month}"
											pattern="yyyy-MM" /></td>
									<td>${pmfVO.pml_trail }</td>
									<td>${pmfVO.pf_status}</td>
									<td>${pmfVO.pf_time}</td>
									<td>
										<form METHOD="post"
											ACTION="<%=request.getContextPath()%>/payment/pmf.do">
											<input type="hidden" name="action" value="paying2">
											<input type="hidden" name="pfl_num" value="${pmfVO.pf_num}">
											<input type="hidden" name="pfl_sum" value="${pmfVO.pml_trail}">
											<input type="submit" value="繳費">
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>


					<!-- ----------------------------------------------------展示內容(查詢所有繳費單) -------------------------------------------------------------------------------------------------->
				</div>
			</div>
		</div>
	</section>

	<%@ include file="../Footer"%>
</body>
</html>