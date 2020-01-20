<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.student.model.*" %>

<% 
	StudentVO studentVO = (StudentVO) request.getAttribute("studentVO");
	StudentVO stp = (StudentVO) session.getAttribute("stp");
%>

<!DOCTYPE html>
<html>
<head>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
		function openFile(event){
			var input = event.target; //取得上傳檔案
			var reader = new FileReader(); //建立FileReader物件
	
			reader.readAsDataURL(input.files[0]); //以.readAsDataURL將上傳檔案轉換為base64字串
	
			reader.onload = function(){ //FileReader取得上傳檔案後執行以下內容
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
	#table-info{
		margin-left:25%;
		font-size:18px;
		background: rgba(195,195,195,0.8);
	}
	
	.imgSt{
		width:400px;
		height:400px;
	}
	
	#output{
		width:400px;
		height:400px;		
	}
	
	#btn-submit{
		text-align:center;
	}
	
	.infoTitle{
		color: purple;
		text-align:left;
		width: 150px;
	}
	
	.infoContent{
		color:purple;
		width:200px;
	}
	
	#btn{
		margin-left: 35%;
	}
	
	#guardianInfo{
		margin-left:46%;
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
							<c:forEach var="guardianVO" items="${guardianSvc.all}">
								<c:if test="${stp.gd_id eq guardianVO.gd_id}">
									<table id="table-info">
										<tr class="listOne">
											<td class="infoContent" style="width=100px;" rowspan="7">
												<img src="<%= request.getContextPath()%>/getSt_photo/getSt_photo.do?st_num=${stp.st_num}" id="old" class="imgSt">
												<img id="output" style="display:none">
											</td>										
											<td class="infoTitle" style="text-align:right;">
												學號:
											</td>
											<td class="infoContent">
												${stp.st_num}
												<input type="hidden" name="st_num" value="${stp.st_num}">
											</td>
										</tr>
										<tr class="listOne">
											<td class="infoTitle" style="text-align:right;">
												姓名:
											</td>
											<td class="infoContent">
												${stp.st_name}
												<input type="hidden" name="st_name" value="${stp.st_name}">
											</td>
										</tr>
										<tr class="listOne">
											<td class="infoTitle" style="text-align:right;">
												身分證字號:
											</td>
											<td class="infoContent">
												${stp.st_id}
												<input type="hidden" name="st_id" value="${stp.st_id}">
											</td>
										</tr>
										<tr class="listOne">
											<td class="infoTitle" style="text-align:right;">
												性別:
											</td>
											<td class="infoContent">
												${stp.st_gender}
												<input type="hidden" name="st_gender" value="${stp.st_gender}">
											</td>
										</tr>
										<tr class="listOne">
											<td class="infoTitle" style="text-align:right;">
												班別:
											</td>
											<td class="infoContent">
												${stp.cs_num}
												<input type="hidden" name="cs_num" value="${stp.cs_num}">
											</td>
										</tr>
										<tr class="listOne">
											<td class="infoTitle" style="text-align:right;">
												通訊地址:
											</td>
											<td class="infoContent">
												${stp.st_address}
												<input type="hidden" name="st_address" value="${stp.st_address}">
											</td>
										</tr>
										<tr class="listOne">
											<td class="infoTitle" style="text-align:right;">
												戶籍地址:
											</td>
											<td class="infoContent">
												${stp.st_r_address}
												<input type="hidden" name="st_r_address" value="${stp.st_r_address}">
											</td>
										</tr>
										<tr class="listOne">
											<td colspan="2">
												<form action="<%= request.getContextPath()%>/student/student.do" method="post" enctype="multipart/form-data">
													<input type="hidden" name="action" value="updatePhoto">
													<input type="hidden" name="st_num" value="${stp.st_num}">
													<input type="file" name="st_photo" onchange="openFile(event)">
													<input type="submit" value="上傳" id="btn">
												</form>
											</td>
											<td id="btn-submit" class="infoContent" colspan='2'>
												<form action="<%= request.getContextPath()%>/student/student.do" method="post">
													<input type="hidden" name="action" value="getOne_For_Update_St">
													<input type="hidden" name="st_num" value="${stp.st_num}">
													<input type="submit" value="更改密碼">
												</form>
											</td>	
										</tr>
										<tr>
											<td colspan="4">
												<form action="<%= request.getContextPath()%>/guardian/guardian.do" action="post">
													<input type="hidden" name="action" value="getSelfGuardian">
													<input type="hidden" name="gd_id" value="${stp.gd_id}">
													<input type="submit" value="家長資料" id="guardianInfo">
												</form>
											</td>
										</tr>
									</table>
								</c:if>
							</c:forEach>
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