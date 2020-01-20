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
	<title>幼兒資料 - listOneStudent.jsp</title>
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
					<th><font color="white">學生學號</font></th>
					<th><font color="white">姓名</font></th>
					<th><font color="white">性別</font></th>
					<th><font color="white">生日</font></th>
					<th><font color="white">身分證字號</font></th>
					<th><font color="white">戶籍地址</font></th>
					<th><font color="white">通訊地址</font></th>
					<th><font color="white">班別</font></th>
					<th><font color="white">狀態</font></th>
					<th><font color="white">照片</font></th>
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