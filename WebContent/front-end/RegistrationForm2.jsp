<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.student.model.*"%>
<%@ page import="com.guardian.model.*"%>

<%
	StudentVO studentVO = (StudentVO) request.getAttribute("studentVO");
	GuardianVO guardianVO = (GuardianVO) request.getAttribute("guardianVO");
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
			$("#name").attr("value","美心");
			$("#id").attr("value","Z123456789");
			$("#r_address").attr("value","桃園市中壢區");
			$("#address").attr("value","桃園市中壢區");
			$("#birthday").attr("value","1992-01-01");
		});
	});
	
</script>

<style>
	#table-info{
		margin-left:34%;
		margin-top: 6%;
	}
	
	#addTitle{
		text-align:left;
	}
	
	#btn{
		margin-left: 48%;
		border-radius: 15px;
		cursor:pointer;
		background-color: lightblue;
	}
	
	font{
		margin-left:30%;
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
		margin-left: 34%;
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
						<form action="<%=request.getContextPath()%>/student/student.do" method="post" enctype="multipart/form-data">
							<table id="table-info">
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">
										相片:
									</td>
									<td class="infoContent">
										<input type="file" name="st_photo" onchange="openFile(event)" /><br>
										<img id="output" height="200" style="display:none">
									</td>
								</tr>
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">
										學生姓名:
									</td>
									<td>
										<input type="TEXT" name="st_name" size="30" value="<%= (studentVO==null)? "" : studentVO.getSt_name()%>" id="name"/>
									</td>
									<c:if test="${not empty errorMsgs.st_name}">
										<tr>
											<td class="errormsg" colspan="2">
												<font style="color:red">${errorMsgs.st_name}</font>
											</td>
										</tr>
									</c:if>									
								</tr>								
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">
										性別:
									</td>
									<td class="infoContent">
										<input type="radio" name="st_gender" value="男">男 
										<input type="radio" name="st_gender" value="女" checked="true">女</td>
								</tr>
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">
										學生身份證字號:
									</td>
									<td>
										<input type="TEXT" name="st_id" size="30" value="<%= (studentVO==null)? "" : studentVO.getSt_id()%>" id="id"/>
									</td>
								</tr>
								<c:if test="${not empty errorMsgs.st_id}">
									<tr>
										<td class="errormsg" colspan="2">
											<font style="color:red">${errorMsgs.st_id}</font>
										</td>
									</tr>
								</c:if>
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">
										戶籍地址:
									</td>
									<td>
										<input type="TEXT" name="st_r_address" size="30" value="<%= (studentVO==null)? "" : studentVO.getSt_r_address()%>" id="r_address"/>
									</td>
								</tr>
								<tr>
									<c:if test="${not empty errorMsgs.st_r_address}">
										<td class="errormsg" colspan="2">
											<font style="color:red">${errorMsgs.st_r_address}</font>
										</td>
									</c:if>
								</tr>
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">
										通訊地址:
									</td>
									<td>
										<input type="TEXT" name="st_address" size="30" value="<%= (studentVO==null)? "" : studentVO.getSt_address()%>" id="address"/>
									</td>
								</tr>
								<tr>
									<c:if test="${not empty errorMsgs.st_address}">
										<td class="errormsg" colspan="2">
											<font style="color:red">${errorMsgs.st_address}</font>
										</td>
									</c:if>
								</tr>								
								<tr class="listOne">
									<td class="addTitle" style="text-align: right;">
										生日:
									</td>
									<td>
										<input type="date" name="st_birthday" value="${param.st_birthday}" max="<%= new Date(System.currentTimeMillis())%>" id="birthday">
									</td>
								</tr>								
							</table>
							<input type="hidden" name="st_status" value="3">
							<input type="hidden" name="gd_id" value="${guardianVO.gd_id}">
							<input type="hidden" name="cs_num" value="C001">
							<input type="hidden" name="action" value="insert_student">
							<input type="submit" value="送出申請" id="btn">
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