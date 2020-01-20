<%@page import="com.commBook.model.CommBookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.student.model.*"%>
<%@page import="com.commBook.model.CommBookVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="../Head"%>
<title>查詢一位幼兒指定日期與昨天的資料</title>
<style type="text/css">
.outer {
	background: #FFFFFF !important;
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
					<!-- 自己的畫面 -->
					<!-- 查詢一位幼兒指定日期與昨天的資料(前台) -->
					<h3 align="center">${sessionScope.stp.st_name}的聯絡簿</h3>
				<table>
						<%  
							request.setAttribute("listSize", 0);//預先設定listSize=0...避免沒有聯絡簿的學生也印出來
						
							List<CommBookVO> commBookVOList = (ArrayList<CommBookVO>)request.getAttribute("commBookVOList");			
							CommBookVO newCommBook = null;
							CommBookVO oldCommBook = null;
							
							if(commBookVOList.size()!=0){
								request.setAttribute("listSize", commBookVOList.size());
								newCommBook = commBookVOList.get(0);
								request.setAttribute("newCommBook", newCommBook);//將日期較新的聯絡簿設定成 newCommBook
								request.removeAttribute("oldCommBook");
								if(commBookVOList.size()>1){
									oldCommBook = commBookVOList.get(1);
									request.setAttribute("oldCommBook", oldCommBook);//將日期較舊的聯絡簿設定成 oldCommBook
								}
							}
						%>
						<tr>
							<th>項目</th>
							<th>${oldCommBook.comm_Date}</th>
							<th>${newCommBook.comm_Date}</th>		
						</tr>
						
						<tr>
							<th>老師的話</th>
							<td>${oldCommBook.comm_T_Msg}</td>
							<td>${newCommBook.comm_T_Msg}</td>
						</tr>
						<tr>
							<th>給家長的話</th>
							<td>${oldCommBook.comm_Res}</td>
							<td>${newCommBook.comm_Res}</td>
						</tr>
						<tr>
							<th>家長回應</th>
							<td>${oldCommBook.comm_P_Msg}</td>
							<td>${newCommBook.comm_P_Msg}</td>
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

	<%@ include file="../Footer"%>
</body>
</html>