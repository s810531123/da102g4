<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="java.sql.Date" %>

<%
	TeacherVO teacherVO = (TeacherVO) request.getAttribute("teacherVO");
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
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
	<title>��¾����ƭק� - update_teacher_input.jsp</title>
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
			width:406px;
			margin-left:38%;
			color:black;
		}
		
		#btn1{
			margin-left:-2%;
		}
		
		
		td{
			text-align:left;
		}
		
		.updateTitle{
			text-align:right;
		}
		
		#submit {
			text-align:left;
		}
		
		#error {
			text-align:center;
		}
		
		#old {
		  	max-width:200px;
		  	max-heigh:200px;
	    }
	    
	    #output{
		  	max-width:200px;
		  	max-heigh:200px;	    
	    }
	    
	    .errormsg{
	    	text-align:center;
	    }
	    
		#hi{
			position:absolute;
			z-index:10;
			margin-left:78%
		}	
		
		#back {
		    margin-left: -8%;
		    margin-right: 5%;
		}		    
	</style>

</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>

	<%@ include file="/back-end/includePage/LeftSide"%>

	
	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="own">

			<h3><a href="<%= request.getContextPath()%>/back-end/teacher/listAllTeacher.jsp"><img src="<%= request.getContextPath()%>/back-end/teacher/images/back.jpg" width="40" height="40" border="0" id="back"></a>��ƭק�</h3>

			

			<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/teacher/teacher.do" name="form1" enctype="multipart/form-data">
				<table id="table-1">
					<tr class="updateOne">
						<td class="updateTitle">�ۤ�:<font color=red><b>*</b></font></td>
						<td><input type="file" name="t_photo" onchange="openFile(event)"></td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">
							�w��:
						</td>
						<td>
							<img src="<%= request.getContextPath()%>/getT_photo/getT_photo.do?t_num=${teacherVO.t_num}" id="old">
							<img id="output" style="display:none">
						</td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">��¾���s��:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" value="<%=teacherVO.getT_num()%>" readonly></td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">�m�W:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="t_name" value="<%=teacherVO.getT_name()%>"></td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_name}</font>
							</td>
						</c:if>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">�ʧO:<font color=red><b>*</b></font></td>
						<td>
						<% if(teacherVO.getT_gender().equals("�k��")){%>
							<input type="radio" name="t_gender" value="�k��" checked="�k��">�k
							<input type="radio" name="t_gender" value="�k��">�k
						<% } else { %>
							<input type="radio" name="t_gender" value="�k��">�k
							<input type="radio" name="t_gender" value="�k��" checked="�k��">�k
						<% } %>
						</td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">�����Ҧr��:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="t_id" value="<%=teacherVO.getT_id()%>"></td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_id}</font>
							</td>
						</c:if>
					</tr>					
					<tr class="updateOne">
						<td class="updateTitle">E-mail:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="t_email" value="<%=teacherVO.getT_email()%>"></td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_email}</font>
							</td>
						</c:if>
					</tr>					
					<tr class="updateOne">
						<td class="updateTitle">���y�a�}:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="t_r_address" value="<%=teacherVO.getT_r_address()%>"></td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_r_address}</font>
							</td>
						</c:if>
					</tr>					
					<tr class="updateOne">
						<td class="updateTitle">�q�T�a�}:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="t_address" value="<%=teacherVO.getT_address()%>"></td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_address}</font>
							</td>
						</c:if>
					</tr>					
					<c:if test="${'���' eq tp.t_job}">
						<tr class="updateOne">
							<td class="updateTitle">¾��:<font color=red><b>*</b></font></td>
							<td>
								<c:if test="${'���' eq teacherVO.t_job}">
									<input type="radio" name="t_job" value="���" checked>���
									<input type="radio" name="t_job" value="�Z�ɮv">�Z�ɮv
									<input type="radio" name="t_job" value="�Ѯv">�Ѯv	
								</c:if>
								<c:if test="${'�Z�ɮv' eq teacherVO.t_job}">
									<input type="radio" name="t_job" value="���">���
									<input type="radio" name="t_job" value="�Z�ɮv" checked>�Z�ɮv
									<input type="radio" name="t_job" value="�Ѯv">�Ѯv	
								</c:if>
								<c:if test="${'�Ѯv' eq teacherVO.t_job}">
									<input type="radio" name="t_job" value="���">���
									<input type="radio" name="t_job" value="�Z�ɮv">�Z�ɮv
									<input type="radio" name="t_job" value="�Ѯv" checked>�Ѯv	
								</c:if>
							</td>
						</tr>
					</c:if>
					<c:if test="${teacherVO.t_job eq tp.t_job}">
						<input type="hidden" name="t_job" value="${teacherVO.t_job}">
					</c:if>
					<c:if test="${tp.t_num eq teacherVO.t_num}">
						<tr class="updateOne">
							<td class="updateTitle">�K�X:<font color=red><b>*</b></font></td>
							<td><input type="TEXT" name="t_password" value="<%=teacherVO.getT_password()%>"></td>
						</tr>
						<tr>
							<c:if test="${not empty errorMsgs}">
								<td colspan="2" class="errormsg">
									<font style="color:red">${errorMsgs.t_password}</font>
								</td>
							</c:if>
						</tr>						
					</c:if>
 					<c:if test="${tp.t_num ne teacherVO.t_num}">
							<input type="hidden" name="t_password" value="<%=teacherVO.getT_password()%>">
					</c:if>
					<tr class="updateOne">
						<td class="updateTitle">�ͤ�:<font color=red><b>*</b></font></td>
						<td><input type="date" name="t_birthday" value="<%=teacherVO.getT_birthday()%>" max="<%= new Date(System.currentTimeMillis())%>"></td>
					</tr>
					<tr class="updateOne">
						<td class="updateTitle">���A:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="t_status" value="<%=teacherVO.getT_status()%>"></td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_status}</font>
							</td>
						</c:if>
					</tr>					
					
				
				</table>
				<br>
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="t_num" value="<%=teacherVO.getT_num()%>">
				<input type="submit" value="�e�X�ק�" id="btn1">
			</FORM>
		</div>
	</div>

</body>

<%@ include file="/back-end/includePage/BootStrap"%>

</html>