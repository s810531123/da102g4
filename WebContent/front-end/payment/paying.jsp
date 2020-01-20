<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%
	String trail = (String) request.getAttribute("pfl_sum2");
	String num = (String) request.getAttribute("pfl_num2");
	String num5 = (String) request.getAttribute("pfl_num5");
	pageContext.setAttribute("pfl_sum5", num5);
	pageContext.setAttribute("pfl_sum3", trail);
	pageContext.setAttribute("pfl_num3", num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<%@ include file="../Head"%>
<title>Insert title here</title>
<script>
	$(function() {
		$("#btn").click(function() {
			$("#txt01").attr("value", "5417101210000339");
			$("input[type=radio]")[1].checked = true;
			$("[name='name1']").attr("value", "野原");
			$("[name='name2']").attr("value", "廣志");
		});

	});
</script>
</head>
<body>

	<%@ include file="../Nav"%>
	<%@ include file="../Top"%>

	<!-- ======================================================================================= -->
	<c:if test="${not empty errorMsgs}">

		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li><b style="color: red; text-align: center">${message}</b></li>
			</c:forEach>
		</ul>

	</c:if>
	<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="outer">
					
					<br>
					<form METHOD="post"
						ACTION="<%=request.getContextPath()%>/payment/pmf.do">						
						<div class="row">
							<div class="col">
								<input type="text" class="form-control" name="name1" placeholder="姓氏">
							</div>
							<div class="col">
								<input type="text" class="form-control" name="name2" placeholder="名字">
							</div>
						</div>
						<br>
						輸入卡號 <input type="text" name="cardnumber" id="txt01"
							class="form-control form-control-lg" placeholder="請輸入卡號">

						 <br> 信用卡銀行: <select size="1" name="choose"
							class="form-control" id="exampleFormControlSelect1">
							<option value="花旗銀行">花旗銀行
							<option value="中國信託">中國信託
							<option value="玉山銀行">玉山銀行
						</select> <br> <br>
						<div style="font-size: 30px">
							繳費金額:
							<c:if test="${not empty pfl_sum3}">
								<b>${pfl_sum3}</b>
							</c:if>
							<c:if test="${not empty pfl_sum5}">
								<b>${pfl_sum5}</b>
							</c:if>

						</div>
						<br> <br> 繳款方式
						<div class="form-check">
							<input class="form-check-input" type="radio" name="exampleRadios"
								id="exampleRadios1" value="option1" checked> <label
								class="form-check-label" for="exampleRadios1"> 使用晶片金融卡 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="exampleRadios"
								id="exampleRadios2" value="option2"> <label
								class="form-check-label" for="exampleRadios2"> 使用活期性存款帳戶
							</label>
						</div>

						<br> <br> <input type="hidden" name="action"
							value="paying"> <input type="hidden" name="pfl_num4"
							value="<%=pageContext.getAttribute("pfl_num3")%>"> 
							<input
							type="submit" class="btn btn-primary" data-toggle="modal"
							data-target="#exampleModal" value="確認"> 
							
							<input
							type="button" value="回上一頁" class="btn btn-outline-primary"
							onclick="location.href='<%=request.getContextPath()%>/front-end/payment/PMF_front.jsp'">
					</form>
					<br>
				</div>
			</div>
		</div>
	</section>
	<input type="button" class="btn btn-outline-light" id="btn"
		value="神奇小按鈕">
	<%@ include file="../Footer"%>

	
	
</body>
</html>
