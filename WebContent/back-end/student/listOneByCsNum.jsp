<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.student.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>

<%
	StudentVO studentVO = (StudentVO) request.getAttribute("studentVO");
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
	<title>������ - listOneStudent.jsp</title>
	<%@ include file="/back-end/includePage/Head"%>

	<style>
	  #table-1 {
		width: -webkit-fill-available  !important;
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
	  #listTitle{
	  	background:#73685d;
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

		 	<a href="<%= request.getContextPath()%>/back-end/student/listStudentByCs_num.jsp"><img src="<%= request.getContextPath()%>/back-end/student/images/back1.gif" width="100" height="32" border="0"></a>

			<table id="table-1">
				<tr id="listTitle" height="50px">
					<th><font color="white">�ǥ;Ǹ�</font></th>
					<th><font color="white">�m�W</font></th>
					<th><font color="white">�ʧO</font></th>
					<th><font color="white">�ͤ�</font></th>
					<th><font color="white">�����Ҧr��</font></th>
					<th><font color="white">���y�a�}</font></th>
					<th><font color="white">�q�T�a�}</font></th>
					<th><font color="white">�Z�O</font></th>
					<th><font color="white">���A</font></th>
					<th><font color="white">�Ӥ�</font></th>
				</tr>
				<tr class="listOne">
					<td>${studentVO.st_num}</td>
					<td>${studentVO.st_name}</td>
					<td>${studentVO.st_gender}</td>
					<td>${studentVO.st_birthday}</td>
					<td>${studentVO.st_id}</td>
					<td>${studentVO.st_r_address}</td>
					<td>${studentVO.st_address}</td>
					<td>${studentVO.cs_num}</td>
					<td>${studentVO.st_status}</td>
					<td>
						<img src="<%= request.getContextPath()%>/getSt_photo/getSt_photo.do?st_num=${studentVO.st_num}">
					</td>
				</tr>
			</table>

		</div>
	</div>

</body>
	<%@ include file="/back-end/includePage/BootStrap"%>
</html>