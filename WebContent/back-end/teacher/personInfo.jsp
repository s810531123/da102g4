<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.teacher.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/includePage/Head"%>
<title>personInfo</title>

	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

	<script>
		$(document).ready(function(){
			$("tr.listOne:even").css("background","rgba(195,195,195,0.8)");
			$("tr.listOne:odd").css("background","rgba(240,240,240,0.9)");
		});
	</script>
	
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
		    margin-right: 97%;
		}	  
	</style>
</head>
<body>
		<%@ include file="/back-end/includePage/Nav"%>
		<%@ include file="/back-end/includePage/LeftSide"%>
		<div class="col-lg-10 col-md-10 cb rightContent">
			<div class="own">
				<a href="<%=request.getContextPath()%>/back-end/BackIndex.jsp"><img src="<%= request.getContextPath()%>/back-end/teacher/images/back.jpg" width="40" height="40" border="0" id="back" title="返回"></a>
			<table id="table-1">
				<tr id="tTitle" height="50px">
					<th><font color="white">教職員編號</font></th>
					<th><font color="white">姓名</font></th>
					<th><font color="white">性別</font></th>
					<th><font color="white">身份證字號</font></th>
					<th><font color="white">E-mail</font></th>
					<th><font color="white">戶籍地址</font></th>
					<th><font color="white">通訊地址</font></th>
					<th><font color="white">職稱</font></th>
					<th><font color="white">生日</font></th>
					<th><font color="white">密碼</font></th>
					<th><font color="white">狀態</font></th>
					<th><font color="white">照片</font></th>
				</tr>
				<tr class="listOne">
					<td>${tp.t_num}</td>
					<td>${tp.t_name}</td>
					<td>${tp.t_gender}</td>
					<td>${tp.t_id}</td>
					<td>${tp.t_email}</td>
					<td>${tp.t_r_address}</td>
					<td>${tp.t_address}</td>
					<td>${tp.t_job}</td>
					<td>${tp.t_birthday}</td>
					<td>${tp.t_password}</td>
					<td>${tp.t_status}</td>
					<td>
						<img src="<%= request.getContextPath()%>/getT_photo/getT_photo.do?t_num=${tp.t_num}">
					</td>
				</tr>
			</table>
			<div>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/teacher/teacher.do" style="margin-bottom: 0px;">
					<input type="submit" value="修改">
					<input type="hidden" name="t_num"  value="${tp.t_num}">
					<input type="hidden" name="action"	value="updateSelf">
				</FORM>				
			</div>
			</div>
		</div>
		<%@ include file="/back-end/includePage/BootStrap"%>
</body>
</html>