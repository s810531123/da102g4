<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.guardian.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>

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
			$("tr.listOne:even").css("background","rgba(195,195,195,0.8)");
			$("tr.listOne:odd").css("background","rgba(240,240,240,0.9)");
		});
	</script>
	<title>�a����� - listOneGuardian.jsp</title>
	<%@ include file="/back-end/includePage/Head"%>

	<style>
	  #table-1 {
		width: 1560px;
		background-color: white;
		margin-top: 5px;
		margin-bottom: 5px;
	  }
	  #table-1 th {
	    border: 1px solid #CCCCFF;
	  }
	  
	  #table-1 td {
	    border: 1px solid #CCCCFF;
	  }
	  #listTitle{
	  	background:#73685d;
	  }
	  
		#hi{
			position:absolute;
			z-index:10;
			margin-left:78%
		}
		
		#back {
		    margin-right: 98%;
		}		
	</style>

</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	

	<%@ include file="/back-end/includePage/LeftSide"%>


	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="own">
		
			<h4><a href="<%= request.getContextPath()%>/back-end/student/listAllStudent.jsp"><img src="<%= request.getContextPath()%>/back-end/guardian/images/back.jpg" width="40" height="40" border="0" title="��^" id="back"></a></h4>


			<table id="table-1">
				<tr id="listTitle" height="50px">
					<th><font color="white">�a���m�W</font></th>
					<th><font color="white">�ʧO</font></th>
					<th><font color="white">�����Ҧr��</font></th>
					<th><font color="white">E-mail</font></th>
					<th><font color="white">�q�T�a�}</font></th>
					<th><font color="white">���Y</font></th>
					<th><font color="white">�s���q��</font></th>
					<th><font color="white">�ͤ�</font></th>
					<c:if test="${'���' eq tp.t_job}">
						<th><font color="white">�ק�</font></th>
					</c:if>
				</tr>
				<tr class="listOne">
					<td><%=guardianVO.getGd_name()%></td>
					<td><%=guardianVO.getGd_gender()%></td>
					<td><%=guardianVO.getGd_id()%></td>
					<td><%=guardianVO.getGd_email()%></td>
					<td><%=guardianVO.getGd_address()%></td>
					<td><%=guardianVO.getGd_rel()%></td>
					<td><%=guardianVO.getGd_phone()%></td>
					<td><%=guardianVO.getGd_birthday()%></td>
					<c:if test="${'���' eq tp.t_job}">
						<td>
							<form action="<%= request.getContextPath()%>/guardian/guardian.do" method="post">
								<input type="hidden" name="gd_id" value="${guardianVO.gd_id}">
								<input type="hidden" name="action" value="getOne_For_Update">
								<input type="submit" value="�ק�">
							</form>
						</td>
					</c:if>
				</tr>
			</table>
			
		</div>
	</div>

</body>
	<%@ include file="/back-end/includePage/BootStrap"%>
</html>