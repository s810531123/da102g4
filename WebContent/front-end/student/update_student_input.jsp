<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.student.model.*"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="java.sql.Date"%>

<%
	StudentVO studentVO = (StudentVO) request.getAttribute("studentVO");
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
	StudentVO stp = (StudentVO) session.getAttribute("stp");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>�ǥ͸�ƭק� - update_student_input.jsp</title>

<%@ include file="../Head"%>

<script>
	function openFile(event) {
		var input = event.target; //���o�W���ɮ�
		var reader = new FileReader(); //�إ�FileReader����

		reader.readAsDataURL(input.files[0]); //�H.readAsDataURL�N�W���ɮ��ഫ��base64�r��

		reader.onload = function() { //FileReader���o�W���ɮ׫����H�U���e
			var dataURL = reader.result; //�]�w�ܼ�dataURL���W�ǹ��ɪ�base64�r��
			$('#output').attr('src', dataURL).show(); //�Nimg��src�]�w��dataURL�����
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
	margin-left:41%;
}

table#table-1 {
    margin-left: 40%;
}

#btn{
	margin-left:49%;
}

.error{
	margin-left: 64%;
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
								src="<%=request.getContextPath()%>/front-end/student/images/back.jpg"
								width="40" height="40" border="0" id="back"></a>


						<%-- ���~��C --%>

						<c:if test="${not empty errorMsgs}">
							<table class="error">
								<tr>
									<td class="error"><font style="color: red">�Эץ��H�U���~:</font>
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
									<td><input type="hidden" name="st_photo"></td>
								</tr>
								<tr>
									<td><br><input type="hidden" name="st_name"
										value="<%=studentVO.getSt_name()%>"></td>
								</tr>
								<tr>
									<td>
										<%if (studentVO.getSt_gender().equals("�k")) {%> 
										<input
										type="hidden" name="st_gender" value="�k" checked="�k">
										<%} else {%>
										<input type="hidden" name="st_gender" value="�k" checked="�k">
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
										<td class="updateTitle">�s�K�X:<font color=red><b>*</b></font></td>
										<td><input type="password" name="st_password"></td>
									</tr>
									<tr>
										<td class="updateTitle">�T�{�K�X:<font color=red><b>*</b></font></td>
										<td><input type="password" name="st_password_check"></td>
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
							<br> <input type="hidden" name="action" value="updatePassword">
							<input type="hidden" name="st_num"
								value="<%=studentVO.getSt_num()%>"> <input type="submit"
								value="�e�X�ק�" id="btn">
						</FORM>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="../Footer"%>
</body>



</html>