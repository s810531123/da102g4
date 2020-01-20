<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.student.model.*"%>

<%
	StudentVO studentVO = (StudentVO) request.getAttribute("studentVO");
	StudentVO stp = (StudentVO) request.getAttribute("stp");
%>

<!DOCTYPE html>
<html>
<head>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/css/lightBox.css" />

<script>
	$(document).ready(function(){
		if(${! empty errorMsgs}){
			$(".outbox").show();
		};
	
		
		$("#Glogin").click(function(){
			$(".outbox").show();
		});
		
		
		$("#X").click(function(){
			$(".outbox").hide();
		});
		
		$('.outbox').click(function(e) {
		    if (e.target == this){
		       $(this).css("display","none");
		    }
		});
		
	});
	
</script>

<style type="text/css">
#loginImg {
	width: 50px;
	height: 60px;
}

#forgot:hover{
	color:red;
}
</style>



<meta charset="utf-8">
<%@ include file="Head"%>
<title>Insert title here</title>
</head>
<body>

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
						<tr>
							<td colspan='2'>
								<a href="<%=request.getContextPath()%>/front-end/forget3.jsp" id="forgot">忘記密碼</a>
							</td>
						</tr>
					</table>
				</FORM>

			</div>
		</div>
	</div>

	<%@ include file="Nav"%>
	<%@ include file="Top"%>

	<!-- ======================================================================================= -->

<section class="home-slider owl-carousel">
			<div class="slider-item" style="background-image:url(<%=request.getContextPath()%>/front-end/images/bg_1.jpg);">
				<div class="overlay"></div>
				<div class="container">
					<div class="row no-gutters slider-text align-items-center justify-content-center" data-scrollax-parent="true">
						<div class="col-md-8 text-center ftco-animate">
							<h1 class="mb-4">全世界都是 <span>小屁孩</span></h1>
							<p><a href="#" class="btn btn-secondary px-4 py-3 mt-3">不知道幹嘛的按鈕</a></p>
						</div>
					</div>
				</div>
			</div>
			<div class="slider-item" style="background-image:url(<%=request.getContextPath()%>/front-end/images/bg_2.jpg);">
				<div class="overlay"></div>
				<div class="container">
					<div class="row no-gutters slider-text align-items-center justify-content-center" data-scrollax-parent="true">
						<div class="col-md-8 text-center ftco-animate">
							<h1 class="mb-4">美心幼兒園<span> 熱烈招生中</span></h1>
							<p><a href="#" class="btn btn-secondary px-4 py-3 mt-3">不知道幹嘛的按鈕</a></p>
						</div>
					</div>
				</div>
			</div>
			<div class="slider-item" style="background-image:url(<%=request.getContextPath()%>/front-end/images/icon.png);">
				<div class="overlay"></div>
				<div class="container">
					<div class="row no-gutters slider-text align-items-center justify-content-center" data-scrollax-parent="true">
						<div class="col-md-8 text-center ftco-animate">
							<h1 class="mb-4">大小吳都好帥 <span>....吧?</span></h1>
							<p><a href="#" class="btn btn-secondary px-4 py-3 mt-3">小吳熬夜打Switch</a></p>
						</div>
					</div>
				</div>
			</div>
			<div class="slider-item" style="background-image:url(<%=request.getContextPath()%>/front-end/images/pic1.png);">
				<div class="overlay"></div>
				<div class="container">
					<div class="row no-gutters slider-text align-items-center justify-content-center" data-scrollax-parent="true">
						<div class="col-md-8 text-center ftco-animate">
							<h1 class="mb-4">恐龍家長<span> 快離開</span></h1>
							<p><a href="#" class="btn btn-secondary px-4 py-3 mt-3">不知道幹嘛的按鈕</a></p>
						</div>
					</div>
				</div>
			</div>
			<div class="slider-item" style="background-image:url(<%=request.getContextPath()%>/front-end/images/pic2.jpg);">
				<div class="overlay"></div>
				<div class="container">
					<div class="row no-gutters slider-text align-items-center justify-content-center" data-scrollax-parent="true">
						<div class="col-md-8 text-center ftco-animate">
							<h1 class="mb-4">媽寶屁孩<span> 不要來</span></h1>
							<p><a href="#" class="btn btn-secondary px-4 py-3 mt-3">不知道幹嘛的按鈕</a></p>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section class="ftco-services ftco-no-pb">
			<div class="container-wrap">
				<div class="row no-gutters">
					<div class="col-md-3 d-flex services align-self-stretch pb-4 px-4 ftco-animate bg-primary">
						<div class="media block-6 d-block text-center">
							<div class="icon d-flex justify-content-center align-items-center">
								<span class="flaticon-teacher"></span>
							</div>
							<div class="media-body p-2 mt-3">
								<h3 class="heading">JAVA 介紹</h3>
								<p>Java是一種廣泛使用的電腦程式設計語言，擁有跨平台、物件導向、泛型程式設計的特性，廣泛應用於企業級Web應用開發和行動應用開發。</p>
							</div>
						</div>
					</div>
					<div class="col-md-3 d-flex services align-self-stretch pb-4 px-4 ftco-animate bg-tertiary">
						<div class="media block-6 d-block text-center">
							<div class="icon d-flex justify-content-center align-items-center">
								<span class="flaticon-reading"></span>
							</div>
							<div class="media-body p-2 mt-3">
								<h3 class="heading">大吳神    介紹</h3>
								<p>吳老師擅長由淺入深，帶著初學者逐步領略JAVA之美。許多原本對JAVA灰心的同學，都在吳老師的神手教導下，重拾信心並愛上JAVA。吳老師十多年的教學經驗，桃李滿天下，因此大家都尊稱他為「吳神」。吳老師為TibaMe重新規劃Java資料庫應用實作課程，以最完整的內容，保證讓您大呼過癮。</p>
							</div>
						</div>
					</div>
					<div class="col-md-3 d-flex services align-self-stretch pb-4 px-4 ftco-animate bg-fifth">
						<div class="media block-6 d-block text-center">
							<div class="icon d-flex justify-content-center align-items-center">
								<span class="flaticon-books"></span>
							</div>
							<div class="media-body p-2 mt-3">
								<h3 class="heading">小吳   介紹</h3>
								<p>深得「吳神」肯定的新一代明星講師，總能用有趣簡單的方式讓學員進入門檻，深具耐心，對學員專題悉心指導與陪伴，學員評價超優。</p>
							</div>
						</div>
					</div>
					<div class="col-md-3 d-flex services align-self-stretch pb-4 px-4 ftco-animate bg-quarternary">
						<div class="media block-6 d-block text-center">
							<div class="icon d-flex justify-content-center align-items-center">
								<span class="flaticon-diploma"></span>
							</div>
							<div class="media-body p-2 mt-3">
								<h3 class="heading">Activity Diagram 之使用時機</h3>
								<p>描述業務流程或工作流程
									 描述使用者與系統之互動流程
									 描述操作流程
								 描述類別中操作之邏輯</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

<section class="ftco-gallery">
			<div class="container-wrap">
				<div class="row no-gutters">
					<div class="col-md-3 ftco-animate">
						<a href="images/image_1.jpg" class="gallery image-popup img d-flex align-items-center" style="background-image: url(<%=request.getContextPath()%>/front-end/images/image_1.jpg);">
							<div class="icon mb-4 d-flex align-items-center justify-content-center">
								<span class="icon-instagram"></span>
							</div>
						</a>
					</div>
					<div class="col-md-3 ftco-animate">
						<a href="images/image_2.jpg" class="gallery image-popup img d-flex align-items-center" style="background-image: url(<%=request.getContextPath()%>/front-end/images/image_2.jpg);">
							<div class="icon mb-4 d-flex align-items-center justify-content-center">
								<span class="icon-instagram"></span>
							</div>
						</a>
					</div>
					<div class="col-md-3 ftco-animate">
						<a href="images/image_3.jpg" class="gallery image-popup img d-flex align-items-center" style="background-image: url(<%=request.getContextPath()%>/front-end/images/image_3.jpg);">
							<div class="icon mb-4 d-flex align-items-center justify-content-center">
								<span class="icon-instagram"></span>
							</div>
						</a>
					</div>
					<div class="col-md-3 ftco-animate">
						<a href="images/image_4.jpg" class="gallery image-popup img d-flex align-items-center" style="background-image: url(<%=request.getContextPath()%>/front-end/images/image_4.jpg);">
							<div class="icon mb-4 d-flex align-items-center justify-content-center">
								<span class="icon-instagram"></span>
							</div>
						</a>
					</div>
				</div>
			</div>
		</section>




	<!-- ======================================================================================= -->






<!-- 	<section class="ftco-section"> -->
<!-- 		<div class="container"> -->
<!-- 			<div class="bfontcol-lg-12"> -->
<!-- 				<div class="outer">自己的功能</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</section> -->

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