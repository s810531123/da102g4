<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.student.model.*"%>

<%@ page import="java.sql.Date"%>

<%
	StudentVO studentVO = (StudentVO) request.getAttribute("studentVO");
	StudentVO stp = (StudentVO) session.getAttribute("stp");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>學生資料修改 - update_student_input.jsp</title>

<%@ include file="../Head"%>

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
</script>

<style>
#table-1 {
	width: 450;
	margin-left: 35%;
	color: black;
}

td {
	text-align: left;
}

.updateTitle {
	text-align: right;
}

#submit {
	text-align: left;
}

.error {
	text-align: center;
	margin-left: 45%;
}

#old {
	max-width: 100px;
	max-heigh: 100px;
}

#hi {
	position: absolute;
	z-index: 10;
	margin-left: 78%
}

#back{
	margin-left:66%;
}

table#table-1 {
    margin-left: 60%;
    width:600px;
}

#btn{
	margin-left:69%;
}

#output{
	width: 100px;
	height:100px;
}
</style>

</head>
<body>
	<%@ include file="../Nav"%>
	<%@ include file="../Top"%>



	<section class="ftco-section">
		<div class="container">
			<div class="bfontcol-lg-12">
				<div class="outer">
					<div id="myOwnView">
							<a
								href="<%=request.getContextPath()%>/front-end/personInfo.jsp"><img
								src="<%=request.getContextPath()%>/front-end/student/images/back1.gif"
								width="100" height="32" border="0" id="back"></a>


						<%-- 錯誤表列 --%>

						<c:if test="${not empty errorMsgs}">
							<table class="error">
								<tr>
									<td class="error"><font style="color: red">請修正以下錯誤:</font>
									</td>
								</tr>
								<tr>
									<td class="error"><c:forEach var="message"
											items="${errorMsgs}">
											<span style="color: red">${message}</span>
											<br>
										</c:forEach></td>
								</tr>
							</table>
						</c:if>
						
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/student/student.do"
							name="form1" enctype="multipart/form-data">
							<table id="table-1">
								<tr>
									<td>大頭貼預覽:</td>
									<td><br>
										<input type="file" name="st_photo" onchange="openFile(event)"><br>
										<img src="<%= request.getContextPath()%>/getSt_photo/getSt_photo.do?st_num=${stp.st_num}" id="old">
										<img id="output" style="display:none">
									</td>
								</tr>
								<tr>
									<td><br><input type="hidden" name="st_name"
										value="<%=studentVO.getSt_name()%>"></td>
								</tr>
								<tr>
									<td>
										<%if (studentVO.getSt_gender().equals("男")) {%> 
										<input
										type="hidden" name="st_gender" value="男" checked="男">
										<%} else {%>
										<input type="hidden" name="st_gender" value="女" checked="女">
										<%}%>
									</td>
								</tr>
								<tr>
									<td><input type="hidden" name="st_birthday"
										value="<%=studentVO.getSt_birthday()%>"
										max="<%=new Date(System.currentTimeMillis())%>"></td>
								</tr>
								<tr>
									<td><input type="hidden" name="st_id"
										value="<%=studentVO.getSt_id()%>"></td>
								</tr>
								<tr>
									<td><input type="hidden" name="gd_id"
										value="<%=studentVO.getGd_id()%>"></td>
								</tr>
								<tr>
									<td><input type="hidden" name="st_r_address"
										value="<%=studentVO.getSt_r_address()%>"></td>
								</tr>
								<tr>
									<td><input type="hidden" name="st_address"
										value="<%=studentVO.getSt_address()%>"></td>
								</tr>
								<tr>
									<td><input type="hidden" name="cs_num"
										value="<%=studentVO.getCs_num()%>"></td>
								</tr>
									<tr>
										<td><input type="hidden" name="st_password" value="<%=studentVO.getSt_password()%>"></td>
									</tr>
								<c:if
									test="${stp.st_num ne studentVO.st_num || stp.st_num ne tp.t_num}">
									<input type="hidden" name="st_password"
										value="<%=studentVO.getSt_password()%>">
								</c:if>
								<tr>
									<td><input type="hidden" name="st_status"
										value="<%=studentVO.getSt_status()%>"></td>
								</tr>
							</table>
							<br> <input type="hidden" name="action" value="updatePhoto">
							<input type="hidden" name="st_num"
								value="<%=studentVO.getSt_num()%>"> <input type="submit"
								value="更換" id="btn">
						</FORM>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="../Footer"%>
</body>



</html>