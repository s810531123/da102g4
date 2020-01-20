<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.guardian.model.*"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="java.sql.Date" %>

<% 
	GuardianVO guardianVO = (GuardianVO) request.getAttribute("guardianVO");
	String job = (String) session.getAttribute("job");
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
%>


<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>家長資料新增 - addGuardian.jsp</title>
	<%@ include file="/back-end/includePage/Head"%>
	
	<style>
		#table-2 {
			width:500px;
			display:inline-block;
			margin-left:10%;
			color:black;
		}
		
		
		.addTitle{
			text-align:right;
		}
		
		#submit {
			text-align:left;
		}
		
		.error {
			text-align:center;
		}
		
		#table-1{
			margin-left:40%;

		}
		
		.errormsg{
			text-align:left;
		}
		
		#hi{
			position:absolute;
			z-index:10;
			margin-left:78%
		}
		
		
	</style>

</head>
<body>
	
	<%@ include file="/back-end/includePage/Nav"%>

	<%@ include file="/back-end/includePage/LeftSide"%>



	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="own">

			<h4><a href="<%= request.getContextPath()%>/back-end/student/listAllStudent.jsp"><img src="<%= request.getContextPath()%>/back-end/guardian/images/back.jpg" width="40" height="40" border="0" id="back"></a></h4>



			<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/guardian/guardian.do" name="form1">
				<table id="table-2">
					<tr>
						<td class="addTitle">
							家長姓名:
						</td>
						<td>
							<input type="TEXT" name="gd_name" size="30" 
							 value="${param.gd_name}" />
						</td>
						<td class="errormsg">
							<font style="color:red">${errorMsgs.gd_name}</font>
						</td>
					</tr>
					<tr>
						<td class="addTitle">
							性別:
						</td>
						<td>
							<input type="radio" name="gd_gender" value="男" checked="true">男
							<input type="radio" name="gd_gender" value="女">女
						</td>
					</tr>
					<tr>
						<td class="addTitle">
							身份證字號:
						</td>
						<td>
							<input type="TEXT" name="gd_id" size="30" 
							 value="${param.gd_id}" />
						</td>
						<td class="errormsg">
							<font style="color:red">${errorMsgs.gd_id}</font>
						</td>
					</tr>
					<tr>
						<td class="addTitle">
							E-mail:
						</td>
						<td>
							<input type="TEXT" name="gd_email" size="30" 
							 value="${param.gd_email}" />
						</td>
						<td class="errormsg">
							<font style="color:red">${errorMsgs.gd_email}</font>
						</td>
					</tr>
					<tr>
						<td class="addTitle">
							通訊地址:
						</td>
						<td>
							<input type="TEXT" name="gd_address" size="30" 
							 value="${param.gd_address}" />
						</td>
						<td class="errormsg">
							<font style="color:red">${errorMsgs.gd_address}</font>
						</td>
					</tr>
					<tr>
						<td class="addTitle">
							連絡電話:
						</td>
						<td>
							<input type="TEXT" name="gd_phone" size="30" 
							 value="${param.gd_phone}" />
						</td>
						<td class="errormsg">
							<font style="color:red">${errorMsgs.gd_phone}</font>
						</td>
					</tr>
					<tr>
						<td class="addTitle">
							關係:
						</td>
						<td>
							<input type="TEXT" name="gd_rel" size="30" 
							 value="${param.gd_rel}" />
						</td>
						<td class="errormsg">
							<font style="color:red">${errorMsgs.gd_rel}</font>
						</td>
					</tr>
					<tr>
						<td class="addTitle">
							生日:
						</td>
						<td>
							<input type="date" name="gd_birthday" value="<%= (guardianVO==null)? new Date(System.currentTimeMillis()) : guardianVO.getGd_birthday()%>" max="<%= new Date(System.currentTimeMillis())%>">
						</td>
					</tr>
				
				</table>
				<br>
				<input type="hidden" name="action" value="insert">
				<input type="submit" value="下一步">
				<br><br><br><br>
			</FORM>
			
			
			<form  METHOD="post" ACTION="<%= request.getContextPath()%>/guardian/guardian.do" name="form2">
				<h4 style='color:blue'>父母資料已存在請在此輸入資料</h4><br>
				<table id="table-1">
					<tr>
						<td>
							<input type="TEXT" name="gd_id" size="30" 
							 value="${param.gd_id}" placeholder="請輸入家長身分證字號"   onkeyup="this.value=this.value.toUpperCase()"/>
						</td>
						<td colspan="2" style='text-align:center;'>
							<input type="hidden" name="action" value="insert_Student_By_Gd_Id">
							<input type="submit" value="新增幼兒資料">
						</td>						
						<td>
							<font style="color:red">${errorMsgs.oneError}</font>
						</td>
					</tr>
				</table>
			</form>
			<br><br>
		</div>
	</div>
</body>
	<%@ include file="/back-end/includePage/BootStrap"%>
	<% 
	  Date gd_birthday = null;
	  try {
		  gd_birthday = guardianVO.getGd_birthday();
	   } catch (Exception e) {
		   gd_birthday = new Date(System.currentTimeMillis());
	   }
	%>

</html>

