<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.student.model.*"%>

<%
	StudentVO studentVO = (StudentVO) request.getAttribute("studentVO");
	StudentVO stp = (StudentVO) session.getAttribute("stp");
%>

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	function openFile(event) {
		var input = event.target; //取得上傳檔案
		var reader = new FileReader(); //建立FileReader物件

		reader.readAsDataURL(input.files[0]); //以.readAsDataURL將上傳檔案轉換為base64字串

		reader.onload = function() { //FileReader取得上傳檔案後執行以下內容
			var dataURL = reader.result; //設定變數dataURL為上傳圖檔的base64字串
			$('#output').attr('src', dataURL).show(); //將img的src設定為dataURL並顯示
			$('#old').hide();
		};
	}

	$(document).ready(function() {
		$("tr.listOne:even").css("background", "rgba(195,195,195,0.8)");
		$("tr.listOne:odd").css("background", "rgba(220,220,220,0.9)");
		
		$("#magic").click(function(){
			$("#name").attr("value","地方媽媽");
			$("#id").attr("value","S123456789");
			$("#email").attr("value","da102g4@gmail.com");
			$("#address").attr("value","桃園市中壢區");
			$("#phone").attr("value","0911111111");
			$("#rel").attr("value","母女");
			$("#birthday").attr("value","1992-01-01");
		});
	});
	
</script>

<style>
	#table-info{
		margin-left:38%;
		margin-top: 6%;
	}
	
	#btn{
		margin-left: 50%;
		border-radius: 15px;
    	background-color: lightblue;
    	cursor:pointer;		
	}

	font {
	    margin-left: 35%;
	}
	.outer {
		background-image: url(<%= request.getContextPath()%>/front-end/images/29397135_p13.png) !important;
		background-repeat: repeat;
		margin: 0;
		padding: 0;
		min-height: 600px;
		height: 100%;
		width: 100%;
		border: 3px solid rgba(0, 0, 0, 0);
		padding: 0;
	}	
	
	#magic{
		margin-left: 38%;
		border-radius: 15px;
    	background-color: lightblue;
    	cursor:pointer;	
	}
	
</style>


<meta charset="utf-8">
<%@ include file="Head"%>
<title>personInfo.jsp</title>
</head>
<body>

	<%@ include file="Nav"%>
	<%@ include file="Top"%>

	<!-- ======================================================================================= -->

	<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="outer">
					<div id="myOwnView">
						<form action="<%=request.getContextPath()%>/guardian/guardian.do" method="post">
							<table id="table-info">
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">家長姓名:</td>
									<td class="infoContent">
										<input type="TEXT" name="gd_name" size="30" value="${param.gd_name}" id="name"/>
									</td>
								</tr>
								<c:if test="${not empty errorMsgs.gd_name}">
									<tr>
										<td class="errormsg" colspan="2">
											<font style="color: red">${errorMsgs.gd_name}</font>
										</td>
									</tr>
								</c:if>
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">性別:</td>
									<td class="infoContent">
										<input type="radio" name="gd_gender" value="男">男 
										<input type="radio" name="gd_gender" value="女" checked="true">女
									</td>
								</tr>
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">身份證字號:</td>
									<td class="infoContent">
										<input type="TEXT" name="gd_id" size="30" value="${param.gd_id}"  id="id"/>
									</td>
								</tr>
								<c:if test="${not empty errorMsgs.gd_id}">
									<tr>								
										<td class="errormsg" colspan="2">
											<font style="color: red">${errorMsgs.gd_id}</font>
										</td>
									</tr>
								</c:if>
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">E-mail:</td>
									<td>
										<input type="TEXT" name="gd_email" size="30" value="${param.gd_email}" id="email"/>
									</td>
								</tr>
								<c:if test="${not empty errorMsgs.gd_email}">
									<tr>
										<td class="errormsg" colspan="2">
											<font style="color:red">${errorMsgs.gd_email}</font>
										</td>
									</tr>
								</c:if>
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">通訊地址:</td>
									<td>
										<input type="TEXT" name="gd_address" size="30" value="${param.gd_address}" id="address"/>
									</td>
								</tr>
								<c:if test="${not empty errorMsgs.gd_address}">
									<tr>
										<td class="errormsg" colspan="2">
											<font style="color:red">${errorMsgs.gd_address}</font>
										</td>
									</tr>
								</c:if>
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">連絡電話:</td>
									<td>
										<input type="TEXT" name="gd_phone" size="30" value="${param.gd_phone}" id="phone"/>
									</td>
								</tr>
								<c:if test="${not empty errorMsgs.gd_phone}">
									<tr>
										<td class="errormsg" colspan="2">
											<font style="color:red">${errorMsgs.gd_phone}</font>
										</td>
									</tr>
								</c:if>
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">關係:</td>
									<td>
										<input type="TEXT" name="gd_rel" size="30" 
										 value="${param.gd_rel}" id="rel">
									</td>
								</tr>
								<c:if test="${not empty errorMsgs.gd_rel}">
									<tr>
										<td class="errormsg" colspan="2">
											<font style="color:red">${errorMsgs.gd_rel}</font>
										</td>
									</tr>
								</c:if>
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">生日:</td>
									<td>
										<input type="date" name="gd_birthday" value="${param.gd_birthday}" max="<%= new Date(System.currentTimeMillis())%>"  id="birthday">
									</td>
								</tr>								
							</table>
							<input type="hidden" name="action" value="insert_guardian">
							<input type="submit" value="下一步" id="btn">
						</form>
						<input type="submit" value="點我" id="magic">
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

	<%@ include file="Footer"%>
</body>
</html>