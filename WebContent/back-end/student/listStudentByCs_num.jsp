<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.student.model.*"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="com._class.model.*"%>

<%! 
	List<StudentVO> list;
	ClassVO classVO;
%>
<%
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
	ClassService classSvc = new ClassService();
	List<ClassVO> vo = classSvc.getAll();
	
	for(ClassVO vo2 : vo){
		if(vo2.getT_num_1().equals(tp.getT_num()) || vo2.getT_num_2().equals(tp.getT_num())){
			StudentService studentSvc = new StudentService();
			list = studentSvc.getStudentByCs_Num(vo2.getCs_num());
			classVO = vo2;
		}
	}
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("classVO", classVO);
	
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

	<title>所有學生資料 - listAllStudent.jsp</title>
	<%@ include file="/back-end/includePage/Head"%>
	
	<style>

	  
	  #table-1 th {
	    border: 1px solid #CCCCFF;
	  }
	  
	  #table-1 td {
	    border: 1px solid #CCCCFF;
	  }
	  
	  #table-1 {
		width: -webkit-fill-available  !important;
		background-color: white;
		margin-top: 5px;
		margin-bottom: 5px;
		margin-left:5px;
		text-align: center;
	  }
	  
	  img {
	  	max-width:100px;
	  	max-heigh:100px;
	  }
	  
	  #addNew{
		margin-left: -85%;
	  }
	  
	  #what td{
	  	text-align:right;
	  }
	  
		#hi{
			position:absolute;
			z-index:10;
			margin-left:78%
		}
	  
	  #what {
	  	margin-left:3%;
	  }
	  
	  #listTitle {
	  	background:#73685d;
	  }
	  
	  
	</style>

</head>
<body>

	<%@ include file="/back-end/includePage/Nav"%>

	<%@ include file="/back-end/includePage/LeftSide"%>

	
	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="own">
		<br><br>
			
			<c:if test="${tp.t_job eq '班導師'}">
				<table id="what">
					<tr>
						<td>
							查詢學號:
						</td>
						<td>
			  				<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/student/student.do" >
							     <input type="text" name="st_num" onkeyup="this.value=this.value.toUpperCase()" >
							     <input type="hidden" name="cs_num" value="${classVO.cs_num}">
							     <input type="hidden" name="action" value="getOneBy_SelfClss_For_Display">
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
			</c:if>
			
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
					<td><font color="white">照片</font></td>
				</tr>
				<c:if test="${tp.t_job eq '班導師'}">
					<c:forEach var="studentVO" items="${list}">
						<tr class="listAll">
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
					</c:forEach>
				</c:if>					
			</table>
			<c:if test="${tp.t_job eq '老師'}">
				<h4><font color="red">您無查詢權限</font></h4>
			</c:if>	
		</div>
	</div>
</body>
	<%@ include file="/back-end/includePage/BootStrap"%>
</html>