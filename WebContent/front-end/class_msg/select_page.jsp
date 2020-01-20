<%@page import="com.student.model.StudentService"%>
<%@page import="com.student.model.StudentVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_messageboard.model.*"%>
<%@ page import="com._class.model.*"%>
<%@ page import="com.guardian.model.*"%>
<%@ page import="com.teacher.model.*"%>


<jsp:useBean id="classSvc" scope="page" class="com._class.model.ClassService"/>
<jsp:useBean id="teacherSvc" scope="page" class="com.teacher.model.TeacherService"/>

<!-- css import -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/class_msg/css/select_page.css">

<% 
	Class_messageboardService class_messageboardSvc = new Class_messageboardService();
	List<Class_messageboardVO> list = class_messageboardSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<% 
	Class_messageboardVO msg = new Class_messageboardVO();
%>

<% 
	StudentService stSvc = new StudentService();
	


	StudentVO stVO = (StudentVO) session.getAttribute("stp");
	List<ClassVO> classList = null;
// 	ClassService cSvc = new ClassService();
	classList = classSvc.getAll();
	
	for(ClassVO cl : classList){
		if(cl.getCs_num().equals(stVO.getCs_num())){
			msg.setCs_num(cl.getCs_num());
		}
	}
	session.setAttribute("msg_front", msg);
	
// 	Class_messageboardVO class_messageboardVO = (Class_messageboardVO) request.getAttribute("class_messageboardVO");
%>





<html>
<%@ include file="/front-end/Head"%>
<%@ include file="/front-end/Nav"%>
<%@ include file="/front-end/Top"%>



<head>

<title>班級留言板</title>
</head>
<body>

	<section class="ftco-section">
	<div class="container">
	<div class="bfontcol-lg-12">
	<div class="outer">


	<table>
			<thead>
				<tr>
					<td><b style="color:black">留言編號</td>
					<td><b style="color:black">班級</td>
					<td><b style="color:black">教師</td>
					<td><b style="color:black">監護人</td>
					<td><b style="color:black">留言</td>
				</tr>
			</thead>
	

			<tbody>
				<c:forEach var="class_messageboardVO" items="${list}">
				
				
<!-- 				權限 -->
				<c:if test="${msg_front.cs_num == class_messageboardVO.cs_num}">
				
				
				
					<tr>
						<td><b style="color: blue">${class_messageboardVO.getMsg_num()}</td>
						
						
						<td>
						<c:forEach var="classVO" items="${classSvc.all}">
						<c:if test="${class_messageboardVO.cs_num == classVO.cs_num}">	
							<b style="color: gray">${classVO.getCs_name()}
						</c:if>	
						</c:forEach>
						</td>
						
						
						<td>
						<c:forEach var="teacherVO" items="${teacherSvc.all}">
						<c:if test="${class_messageboardVO.t_num == teacherVO.t_num}">	
							<b style="color: red">${teacherVO.getT_name()}
						</c:if>	
						</c:forEach>
						</td>
						
						
						
						
						<td>
						<c:forEach var="guardianVO" items="${guardianSvc.all}">
						<c:if test="${class_messageboardVO.gd_id == guardianVO.gd_id}">
							<b style="color: blue">${guardianVO.gd_name}
						</c:if>	
						</c:forEach>
						</td>
						
						
						<td><b style="color: gray">${class_messageboardVO.getMsg()}</td>
						
						
			
						
					</tr>
						
						
					</c:if>
					
					
				</c:forEach>
				
			</tbody>
		</table>
						<form method="post" action="<%=request.getContextPath()%>/class_msg/class_msg_front.do">
						
						<input type="text" name="msg" maxlength="3000" placeholder="請輸入留言">
<!-- 						<input type="hidden" name="st_num" value="S001"> -->
						<input type="hidden" name="cs_num" value="${msg_front.cs_num}">
						<input type="hidden" name="action" value="insert">
						<input type="submit" class="submit" value="家長留言">
						
						</form>
						
	</div>
	</div>
	</div>
	</section>
	
<%-- <%@ include file="/front-end/Footer"%> --%>



</body>
</html>