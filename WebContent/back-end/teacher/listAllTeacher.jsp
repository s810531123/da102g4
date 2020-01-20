<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.teacher.model.*"%>

<%
	TeacherService teacherSvc = new TeacherService();
	List<TeacherVO> list = teacherSvc.getAll();
	pageContext.setAttribute("list", list);
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
%>

<!DOCTYPE html>
<html>
<head>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	
	<script>
		$(document).ready(function(){
			$("tr.listAll:even").css("background","rgba(195,195,195,0.8)");
			$("tr.listAll:odd").css("background","rgba(240,240,240,0.9)");
		});
	</script>
	<%@ include file="/back-end/includePage/Head"%>
	<title>所有教職員資料 - listAllTeacher.jsp</title>

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
	  img{
	  	max-width:100px;
	  	max-heigh:100px;
	  }
	  
	  #addNew{
		margin-left: -85%;
	  }
	  
	  #what td{
	  	text-align:right;
	  }
	  
	  #what {
	  	margin-left:36%;
	  }
	  
	  #tTitle{
	  	background:#73685d;
	  }
	  
		#hi{
			position:absolute;
			z-index:10;
			margin-left:78%
		}
		
		#btn1{
			margin-left:45%;
		}
	</style>

</head>
<body>

	<%@ include file="/back-end/includePage/Nav"%>

	<%@ include file="/back-end/includePage/LeftSide"%>



	
	<div class="col-lg-10 col-md-10 cb rightContent">
		<br><br>
		<%@ include file="page1.file" %> 
		<div class="own">
			<div id="addNew">

				<c:if test="${'園長' eq tp.t_job}">
					<form action="<%= request.getContextPath()%>/teacher/teacher.do">
						<input type="hidden" name="action" value="insertKey">
						<input type="submit" value="新增教職員資料" id="btn1">
					</form>
				</c:if>
			</div>
			
			<table id="what">
  				<tr>
  					<td class="teahcerTitle">
						<b>輸入教職員編號:</b>				
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/teacher/teacher.do" >
						<input type="text" name="t_num"  onkeyup="this.value=this.value.toUpperCase()">
						<input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出">
						</FORM>
					</td>
					<c:if test="${not empty errorMsgs}">
						<td id="error">
							<c:forEach var="message" items="${errorMsgs}">
								<span style="color:red">${message}</span>
							</c:forEach>
						</td>
					</c:if>
				</tr>
     		</table>

			<table id="table-1">
				<tr id="tTitle" height="50px">
					<th><font color="white">教職員編號</font></th>
					<th><font color="white">姓名</font></th>
					<th><font color="white">性別</font></th>
					<c:if test="${'園長' eq tp.t_job}">
						<th><font color="white">身份證字號</font></th>
					</c:if>
					<th><font color="white">E-mail</font></th>
					<c:if test="${'園長' eq tp.t_job}">
						<th><font color="white">戶籍地址</font></th>
						<th><font color="white">通訊地址</font></th>
					</c:if>
					<th><font color="white">職稱</font></th>
					<th><font color="white">生日</font></th>
					<th><font color="white">狀態</font></th>
					<th><font color="white">照片</font></th>
					<c:if test="${'園長' eq tp.t_job}">
						<th><font color="white">修改</font></th>
					</c:if>
				</tr>
				
				<c:forEach var="teacherVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	
					<tr class="listAll">
						<td>${teacherVO.t_num}</td>
						<td>${teacherVO.t_name}</td>
						<td>${teacherVO.t_gender}</td>
						<c:if test="${'園長' eq tp.t_job}">
							<td>${teacherVO.t_id}</td>
						</c:if>
						<td>${teacherVO.t_email}</td>
						<c:if test="${'園長' eq tp.t_job}">
							<td>${teacherVO.t_r_address}</td> 
							<td>${teacherVO.t_address}</td>
						</c:if>
						<td>${teacherVO.t_job}</td>
						<td>${teacherVO.t_birthday}</td>
						<td>${teacherVO.t_status}</td>
						<td>
							<img src="<%= request.getContextPath()%>/getT_photo/getT_photo.do?t_num=${teacherVO.t_num}">
						</td>
						<c:if test="${'園長' eq tp.t_job}">
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/teacher/teacher.do" style="margin-bottom: 0px;">
									<input type="submit" value="修改">
									<input type="hidden" name="t_num"  value="${teacherVO.t_num}">
									<input type="hidden" name="action"	value="getOne_For_Update">
								</FORM>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2.file" %>
		</div>
	</div>

</body>
<%@ include file="/back-end/includePage/BootStrap"%>
</html>