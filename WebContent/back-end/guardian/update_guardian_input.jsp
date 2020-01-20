<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.guardian.model.*"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="java.sql.Date" %>

<%
	GuardianVO guardianVO = (GuardianVO) request.getAttribute("guardianVO");
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
<title>家長資料修改 - update_guardian_input.jsp</title>

	<%@ include file="/back-end/includePage/Head"%>
	
	<style>
		#table-1 {
			margin-left:auto;
			margin-right:auto;
			color:black;
			text-align:left;
		}
		#back{
			margin-left: -16%;
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
			<br>
			<h4><a href="<%= request.getContextPath()%>/guardian/guardian.do?gd_id=${guardianVO.gd_id}&action=getOne_For_Display"><img src="<%= request.getContextPath()%>/back-end/guardian/images/back.jpg" width="40" height="40" border="0" id="back" title="返回"></a></h4>

			<%-- 錯誤表列 --%>
			
			<c:if test="${not empty errorMsgs}">
				<table id="table-1">
					<tr>
						<td id="error">
							<font style="color:red">請修正以下錯誤:</font>
						</td>
					</tr>
					<tr>
						<td>	
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
						</td>
					</tr>
				</table>
			</c:if>

			<FORM METHOD="post" ACTION="guardian.do" name="form1">
				<table id="table-1">
					<tr class="updateOne">
						<td>家長姓名:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="gd_name" value="<%=guardianVO.getGd_name()%>"></td>
					</tr>
					<tr class="updateOne">
						<td>性別:<font color=red><b>*</b></font></td>
						<td>
						<% if(guardianVO.getGd_gender().equals("男")){%>
							<input type="radio" name="gd_gender" value="男" checked="男">男
							<input type="radio" name="gd_gender" value="女">女
						<% } else { %>
							<input type="radio" name="gd_gender" value="男">男
							<input type="radio" name="gd_gender" value="女" checked="女">女
						<% } %>
						</td>
					</tr>
					<tr class="updateOne">
						<td>身分證字號:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="gd_id" value="<%=guardianVO.getGd_id()%>"></td>
					</tr>
					<tr class="updateOne">
						<td>E-mail:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="gd_email" value="<%=guardianVO.getGd_email()%>"></td>
					</tr>
					<tr class="updateOne">
						<td>通訊地址:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="gd_address" value="<%=guardianVO.getGd_address()%>"></td>
					</tr>
					<tr class="updateOne">
						<td>關係:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="gd_rel" value="<%=guardianVO.getGd_rel()%>"></td>
					</tr>
					<tr class="updateOne">
						<td>連絡電話:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="gd_phone" value="<%=guardianVO.getGd_phone()%>"></td>
					</tr>
					<tr class="updateOne">
						<td>生日:<font color=red><b>*</b></font></td>
						<td><input type="date" name="gd_birthday" value="<%=guardianVO.getGd_birthday()%>" max="<%= new Date(System.currentTimeMillis())%>"></td>
					</tr>
				
					
				
				</table>
				<br>
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="gd_name" value="<%=guardianVO.getGd_name()%>">
				<input type="submit" value="送出修改">
			</FORM>
		</div>
	</div>
</body>

	<%@ include file="/back-end/includePage/BootStrap"%>
	
</html>