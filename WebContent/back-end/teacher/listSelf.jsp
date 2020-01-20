<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.teacher.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			$("tr.listOne:even").css("background","rgba(195,195,195,0.8)");
			$("tr.listOne:odd").css("background","rgba(240,240,240,0.9)");
		});
	</script>

	<title>��¾����� - listSelf.jsp</title>
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
	  img {
	  	max-width:100px;
	  	max-heigh:100px;
	  }
	  #tTitle{
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
			<a href="<%=request.getContextPath()%>/back-end/teacher/personInfo.jsp"><img src="<%= request.getContextPath()%>/back-end/teacher/images/back.jpg" width="40" height="40" border="0" id="back"></a>
			<table id="table-1">
				<tr id="tTitle" height="50px">
					<th><font color="white">��¾���s��</font></th>
					<th><font color="white">�m�W</font></th>
					<th><font color="white">�ʧO</font></th>
					<c:if test="${tp.t_num eq teacherVO.t_num}">
						<th><font color="white">�����Ҧr��</font></th>
					</c:if>
					<th><font color="white">E-mail</font></th>
					<c:if test="${tp.t_num eq teacherVO.t_num}">
						<th><font color="white">���y�a�}</font></th>
						<th><font color="white">�q�T�a�}</font></th>
					</c:if>
					<th><font color="white">¾��</font></th>
					<th><font color="white">�ͤ�</font></th>
					<c:if test="${tp.t_num eq teacherVO.t_num}">
						<th><font color="white">�K�X</font></th>
					</c:if>
					<th><font color="white">���A</font></th>
					<th><font color="white">�Ӥ�</font></th>
				</tr>
				<tr class="listOne">
					<td>${teacherVO.t_num}</td>
					<td>${teacherVO.t_name}</td>
					<td>${teacherVO.t_gender}</td>
					<c:if test="${tp.t_num eq teacherVO.t_num}">
						<td>${teacherVO.t_id}</td>
					</c:if>
					<td>${teacherVO.t_email}</td>
					<c:if test="${tp.t_num eq teacherVO.t_num}">
						<td>${teacherVO.t_r_address}</td>
						<td>${teacherVO.t_address}</td>
					</c:if>
					<td>${teacherVO.t_job}</td>
					<td>${teacherVO.t_birthday}</td>
					<c:if test="${tp.t_num eq teacherVO.t_num}">
						<td>${teacherVO.t_password}</td>
					</c:if>
					<td>${teacherVO.t_status}</td>
					<td>
						<img src="<%= request.getContextPath()%>/getT_photo/getT_photo.do?t_num=${teacherVO.t_num}">
					</td>
				</tr>
			</table>

		</div>
	</div>

</body>
	<%@ include file="/back-end/includePage/BootStrap"%>
</html>