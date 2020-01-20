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

	<title>教職員資料 - listSelf.jsp</title>
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
					<th><font color="white">教職員編號</font></th>
					<th><font color="white">姓名</font></th>
					<th><font color="white">性別</font></th>
					<c:if test="${tp.t_num eq teacherVO.t_num}">
						<th><font color="white">身份證字號</font></th>
					</c:if>
					<th><font color="white">E-mail</font></th>
					<c:if test="${tp.t_num eq teacherVO.t_num}">
						<th><font color="white">戶籍地址</font></th>
						<th><font color="white">通訊地址</font></th>
					</c:if>
					<th><font color="white">職稱</font></th>
					<th><font color="white">生日</font></th>
					<c:if test="${tp.t_num eq teacherVO.t_num}">
						<th><font color="white">密碼</font></th>
					</c:if>
					<th><font color="white">狀態</font></th>
					<th><font color="white">照片</font></th>
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