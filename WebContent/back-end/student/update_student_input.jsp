<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.student.model.*"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="com._class.model.*"%>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.*"%>

<%
	StudentVO studentVO = (StudentVO) request.getAttribute("studentVO");
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
	StudentVO stp = (StudentVO) session.getAttribute("stp");
	ClassService classSvc = new ClassService();
	List<ClassVO> list = classSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	
	<script>
		$(document).ready(function(){
			$("tr.updateOne:even").css("background","rgba(195,195,195,0.8)");
			$("tr.updateOne:odd").css("background","rgba(220,220,220,0.9)");
		});
	</script>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>�ǥ͸�ƭק� - update_student_input.jsp</title>

	<%@ include file="/back-end/includePage/Head"%>
	
	<script>
		function openFile(event){
			var input = event.target; //���o�W���ɮ�
			var reader = new FileReader(); //�إ�FileReader����
	
			reader.readAsDataURL(input.files[0]); //�H.readAsDataURL�N�W���ɮ��ഫ��base64�r��
	
			reader.onload = function(){ //FileReader���o�W���ɮ׫����H�U���e
			var dataURL = reader.result; //�]�w�ܼ�dataURL���W�ǹ��ɪ�base64�r��
			$('#output').attr('src', dataURL).show(); //�Nimg��src�]�w��dataURL�����
			$('#old').hide();
			};
		}
	</script>
	
	<style>
		#table-1 {
			width:450;
			margin-left:35%;
			color:black;
		}
		
		td{
			text-align:left;
		}
		
		.updateTitle{
			text-align:right;
		}
		
		#btn1{
			margin-left:-5%;
		}
		
		.error {
			text-align:center;
			margin-left:41%;
		}
		#old {
		  	max-width:100px;
		  	max-heigh:100px;
		}
		
		#hi{
			position:absolute;
			z-index:10;
			margin-left:78%
		}
		
		#back {
		    margin-left: -13%;
		    margin-right: 6%;
		}		
		
	</style>

</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>

	<%@ include file="/back-end/includePage/LeftSide"%>

	
	
	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="own">

			<h3><a href="<%= request.getContextPath()%>/back-end/student/listAllStudent.jsp"><img src="<%= request.getContextPath()%>/back-end/teacher/images/back.jpg" width="40" height="40" border="0" id="back"></a>��ƭק�</h3>

			<%-- ���~��C --%>
			
			<c:if test="${not empty errorMsgs}">
				<table class="error">
					<tr>
						<td class="error">
							<font style="color:red">�Эץ��H�U���~:</font>
						</td>
					</tr>
					<tr>
						<td class="error">	
							<c:forEach var="message" items="${errorMsgs}">
								<span style="color:red">${message}</span><br>
							</c:forEach>
						</td>
					</tr>
				</table>
			</c:if>

			<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/student/student.do" name="form1" enctype="multipart/form-data">
				<table id="table-1">
					<tr class="updateOne">
						<td class="updateTitle">�ǥ;Ǹ�:<font color=red><b>*</b></font></td>
						<td><%=studentVO.getSt_num()%></td>
					</tr>
					<tr>
						<td class="updateTitle">�ۤ�:</td>
						<td><input type="file" name="st_photo" onchange="openFile(event)"></td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">
							�w��:
						</td>
						<td>
							<img src="<%= request.getContextPath()%>/getSt_photo/getSt_photo.do?st_num=${studentVO.st_num}" id="old">
							<img id="output" height="200" style="display:none">
						</td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">�m�W:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="st_name" value="<%=studentVO.getSt_name()%>"></td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">�ʧO:<font color=red><b>*</b></font></td>
						<td>
						<% if(studentVO.getSt_gender().equals("�k")){%>
							<input type="radio" name="st_gender" value="�k" checked="�k">�k
							<input type="radio" name="st_gender" value="�k">�k
						<% } else { %>
							<input type="radio" name="st_gender" value="�k">�k
							<input type="radio" name="st_gender" value="�k" checked="�k">�k
						<% } %>
						</td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">�ͤ�:<font color=red><b>*</b></font></td>
						<td><input type="date" name="st_birthday" value="<%=studentVO.getSt_birthday()%>" max="<%= new Date(System.currentTimeMillis())%>"></td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">�ǥͨ����Ҧr��:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="st_id" value="<%=studentVO.getSt_id()%>"></td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">���@�H�����Ҧr��:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="gd_id" value="<%=studentVO.getGd_id()%>"></td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">���y�a�}:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="st_r_address" value="<%=studentVO.getSt_r_address()%>"></td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">�q�T�a�}:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="st_address" value="<%=studentVO.getSt_address()%>"></td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">�Z�O:<font color=red><b>*</b></font></td>
						<td>	 
							 <select name="cs_num">
							 	<option value="">�п��
							 	<c:forEach var="classVO" items="${list}">
							 		<option value="${classVO.cs_num}">${classVO.cs_num}
							 	</c:forEach>
							 </select>
						</td>
					</tr>
					<c:if test="${stp.st_num eq studentVO.st_num}">
					<tr class="updateOne">
						<td class="updateTitle">�K�X:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="st_password" value="<%=studentVO.getSt_password()%>"></td>
					</tr>
					</c:if>
					<c:if test="${stp.st_num ne studentVO.st_num || stp.st_num ne tp.t_num}">
						<input type="hidden" name="st_password" value="<%=studentVO.getSt_password()%>">
					</c:if>
					<tr class="updateOne">
						<td class="updateTitle">���A:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="st_status" value="<%=studentVO.getSt_status()%>"></td>
					</tr>
				</table>
				<br>
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="st_num" value="<%=studentVO.getSt_num()%>">
				<input type="submit" value="�e�X�ק�" id="btn1">
			</FORM>

		</div>
	</div>
</body>

	<%@ include file="/back-end/includePage/BootStrap"%>

</html>