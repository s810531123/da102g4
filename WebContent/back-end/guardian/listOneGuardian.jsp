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
	<title>家長資料 - listOneGuardian.jsp</title>
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
		
			<h4><a href="<%= request.getContextPath()%>/back-end/student/listAllStudent.jsp"><img src="<%= request.getContextPath()%>/back-end/guardian/images/back.jpg" width="40" height="40" border="0" title="返回" id="back"></a></h4>


			<table id="table-1">
				<tr id="listTitle" height="50px">
					<th><font color="white">家長姓名</font></th>
					<th><font color="white">性別</font></th>
					<th><font color="white">身分證字號</font></th>
					<th><font color="white">E-mail</font></th>
					<th><font color="white">通訊地址</font></th>
					<th><font color="white">關係</font></th>
					<th><font color="white">連絡電話</font></th>
					<th><font color="white">生日</font></th>
					<c:if test="${'園長' eq tp.t_job}">
						<th><font color="white">修改</font></th>
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
					<c:if test="${'園長' eq tp.t_job}">
						<td>
							<form action="<%= request.getContextPath()%>/guardian/guardian.do" method="post">
								<input type="hidden" name="gd_id" value="${guardianVO.gd_id}">
								<input type="hidden" name="action" value="getOne_For_Update">
								<input type="submit" value="修改">
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