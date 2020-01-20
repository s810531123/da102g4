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

	<title>�Ҧ��ǥ͸�� - listAllStudent.jsp</title>
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
			
			<c:if test="${tp.t_job eq '�Z�ɮv'}">
				<table id="what">
					<tr>
						<td>
							�d�߾Ǹ�:
						</td>
						<td>
			  				<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/student/student.do" >
							     <input type="text" name="st_num" onkeyup="this.value=this.value.toUpperCase()" >
							     <input type="hidden" name="cs_num" value="${classVO.cs_num}">
							     <input type="hidden" name="action" value="getOneBy_SelfClss_For_Display">
							     <input type="submit" value="�e�X">
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
					<th><font color="white">�ǥ;Ǹ�</font></th>
					<th><font color="white">�m�W</font></th>
					<th><font color="white">�ʧO</font></th>
					<th><font color="white">�ͤ�</font></th>
					<th><font color="white">�����Ҧr��</font></th>
					<th><font color="white">���y�a�}</font></th>
					<th><font color="white">�q�T�a�}</font></th>
					<th><font color="white">�Z�O</font></th>
					<th><font color="white">���A</font></th>
					<td><font color="white">�Ӥ�</font></td>
				</tr>
				<c:if test="${tp.t_job eq '�Z�ɮv'}">
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
			<c:if test="${tp.t_job eq '�Ѯv'}">
				<h4><font color="red">�z�L�d���v��</font></h4>
			</c:if>	
		</div>
	</div>
</body>
	<%@ include file="/back-end/includePage/BootStrap"%>
</html>