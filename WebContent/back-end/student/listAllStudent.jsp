<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.student.model.*"%>
<%@ page import="com.teacher.model.*"%>


<%
	StudentService studentSvc = new StudentService();
	List<StudentVO> list2 = studentSvc.getAll();
	List<StudentVO> list = new LinkedList<StudentVO>();
	for(StudentVO stVO : list2){
		if(stVO.getSt_status() != 3){
			list.add(stVO);
		}	
	}
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
		margin-left:-2px;
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
	  	margin-left:39%;
	  }
	  
	  #listTitle {
	  	background:#73685d;
	  }
	  
	  #table-3{
	  	margin-left:69%;
	  }
	  
	  #btn10{
	  	border-radius: 15px;
	  }
	  #btn11{
	  	border-radius: 15px;
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
				<c:if test="${'���' eq tp.t_job}">
					<table id="table-3">
						<tr>
							<td>
								<a href="<%= request.getContextPath()%>/back-end/student/Registration.jsp"><button>�f�֥ӽг�</button></a>
							</td>
							<td>
								<form action="<%= request.getContextPath()%>/back-end/guardian/addGuardian.jsp">
									<input type="submit" value="�s�W�ǥ͸��">
								</form>
							</td>
						</tr>
					</table>
				</c:if>	
			</div>
			
			<table id="what">
				<tr>
					<td>
						�d�߾Ǹ�:
					</td>
					<td>
		  				<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/student/student.do" >
						     <input type="text" name="st_num">
						     <input type="hidden" name="action" value="getOne_For_Display">
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
					<c:if test="${'���' eq tp.t_job}">
						<th><font color="white">�ק�</font></th>
						<th><font color="white">���@�H���</font></th>
					</c:if>
				</tr>

						<c:forEach var="studentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr class="listAll">
								<td>${studentVO.st_num}</td>
								<td>${studentVO.st_name}</td>
								<td>${studentVO.st_gender}</td>
								<td>${studentVO.st_birthday}</td>
								<td>${studentVO.st_id}</td>
								<td>${studentVO.st_r_address}</td>
								<td>${studentVO.st_address}</td>
								<td>${studentVO.cs_num}</td>
								<c:if test="${studentVO.st_status == 0}">
									<td>�b��</td>
								</c:if>
								<c:if test="${studentVO.st_status == 1}">
									<td>�w���~</td>
								</c:if>
								<c:if test="${studentVO.st_status == 2}">
									<td>���</td>
								</c:if>	
								<td>
									<img src="<%= request.getContextPath()%>/getSt_photo/getSt_photo.do?st_num=${studentVO.st_num}">
								</td>
								<c:if test="${'���' eq tp.t_job}">
									<td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/student/student.do" style="margin-bottom: 0px;">
											<input type="submit" value="�ק�" id="btn10">
											<input type="hidden" name="st_num"  value="${studentVO.st_num}">
											<input type="hidden" name="action"	value="getOne_For_Update">
										</FORM>
									</td>
									<td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/guardian/guardian.do" style="margin-bottom: 0px;">
											<input type="submit" value="�d��" id="btn11">
											<input type="hidden" name="gd_id"  value="${studentVO.gd_id}">
											<input type="hidden" name="action"	value="getOne_For_Display">
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