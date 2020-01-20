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
	
	$(document).ready(function(){
		$("tr.listOne:even").css("background","rgba(195,195,195,0.8)");
		$("tr.listOne:odd").css("background","rgba(240,240,240,0.9)");
	});
	
</script>


<style>
#table-info {
	margin-left: 37%;
	font-size: 18px;
	border:1px solid blue;
	width: 400px;
}

.infoTitle {
	width: 200px;
}

.imgSt {
	width: 400px;
	height: 400px;
}

#output {
	width: 400px;
	height: 400px;
}

#btn-submit {
	text-align: center;
}

.infoTitle {
	color: purple;
	text-align: left;
	width: 100px;
	border:1px solid blue;
}

.infoContent {
	color: purple;
	width: 200px;
	border:1px solid blue;
}

#btn {
	margin-left: 35%;
}

h5 {
    margin-left: 48%;
}


</style>

<meta charset="utf-8">
<%@ include file="../Head"%>
<title>personInfo.jsp</title>
</head>
<body>

	<%@ include file="../Nav"%>
	<%@ include file="../Top"%>

	<!-- ======================================================================================= -->

	<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="outer">
					<div id="myOwnView">
						<h5>家長基本資料</h5>
						<table id="table-info">
							<tr class="listOne">
								<td class="infoTitle" style="text-align: right;">家長姓名:</td>
								<td class="infoContent"><%=guardianVO.getGd_name()%> <input
									type="hidden" name="gd_name"
									value="<%=guardianVO.getGd_name()%>"></td>
							</tr>
							<tr class="listOne">
								<td class="infoTitle" style="text-align: right;">性別:</td>
								<td class="infoContent"><%=guardianVO.getGd_gender()%> <input
									type="hidden" name="gd_gender"
									value="<%=guardianVO.getGd_gender()%>"></td>
							</tr>
							<tr class="listOne">
								<td class="infoTitle" style="text-align: right;">關係:</td>
								<td class="infoContent"><%=guardianVO.getGd_rel()%> <input
									type="hidden" name="gd_rel"
									value="<%=guardianVO.getGd_rel()%>"></td>
							</tr>
							<tr class="listOne">
								<td class="infoTitle" style="text-align: right;">身分證字號:</td>
								<td class="infoContent"><%=guardianVO.getGd_id()%> <input
									type="hidden" name="gd_id" value="<%=guardianVO.getGd_id()%>">
								</td>
							</tr>
							<tr class="listOne">
								<td class="infoTitle" style="text-align: right;">e-mail:</td>
								<td class="infoContent"><%=guardianVO.getGd_email()%> <input
									type="hidden" name="gd_email"
									value="<%=guardianVO.getGd_email()%>"></td>
							</tr>
							<tr class="listOne">
								<td class="infoTitle" style="text-align: right;">通訊地址:</td>
								<td class="infoContent"><%=guardianVO.getGd_address()%> <input
									type="hidden" name="gd_address"
									value="<%=guardianVO.getGd_address()%>"></td>
							</tr>
							<tr class="listOne">
								<td class="infoTitle" style="text-align: right;">連絡電話:</td>
								<td class="infoContent"><%=guardianVO.getGd_phone()%> <input
									type="hidden" name="gd_phone"
									value="<%=guardianVO.getGd_phone()%>"></td>
							</tr>
							<tr class="listOne">
								<td class="infoTitle" style="text-align: right;">生日:</td>
								<td class="infoContent"><%=guardianVO.getGd_birthday()%> <input
									type="hidden" name="gd_birthday"
									value="<%=guardianVO.getGd_birthday()%>"></td>
							</tr>
						</table>
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

	<%@ include file="../Footer"%>
</body>
</html>