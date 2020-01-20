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
				<c:if test="${'園長' eq tp.t_job}">
					<table id="table-3">
						<tr>
							<td>
								<a href="<%= request.getContextPath()%>/back-end/student/Registration.jsp"><button>審核申請單</button></a>
							</td>
							<td>
								<form action="<%= request.getContextPath()%>/back-end/guardian/addGuardian.jsp">
									<input type="submit" value="新增學生資料">
								</form>
							</td>
						</tr>
					</table>
				</c:if>	
			</div>
			
			<table id="what">
				<tr>
					<td>
						查詢學號:
					</td>
					<td>
		  				<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/student/student.do" >
						     <input type="text" name="st_num">
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
					<c:if test="${'園長' eq tp.t_job}">
						<th><font color="white">修改</font></th>
						<th><font color="white">監護人資料</font></th>
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
									<td>在學</td>
								</c:if>
								<c:if test="${studentVO.st_status == 1}">
									<td>已畢業</td>
								</c:if>
								<c:if test="${studentVO.st_status == 2}">
									<td>轉學</td>
								</c:if>	
								<td>
									<img src="<%= request.getContextPath()%>/getSt_photo/getSt_photo.do?st_num=${studentVO.st_num}">
								</td>
								<c:if test="${'園長' eq tp.t_job}">
									<td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/student/student.do" style="margin-bottom: 0px;">
											<input type="submit" value="修改" id="btn10">
											<input type="hidden" name="st_num"  value="${studentVO.st_num}">
											<input type="hidden" name="action"	value="getOne_For_Update">
										</FORM>
									</td>
									<td>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/guardian/guardian.do" style="margin-bottom: 0px;">
											<input type="submit" value="查看" id="btn11">
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