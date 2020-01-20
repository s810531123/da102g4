<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_messageboard.model.*"%>
<%@ page import="com._class.model.*"%>
<%@ page import="com.guardian.model.*"%>
<%@ page import="com.teacher.model.*"%>


<% 
	Class_messageboardService class_messageboardSvc = new Class_messageboardService();
	List<Class_messageboardVO> list = class_messageboardSvc.getAll();
%>


<% 
// 	Class_messageboardVO class_messageboardVO = (Class_messageboardVO) request.getAttribute("class_messageboardVO");
%>

<% 
	TeacherService tSvc = new TeacherService();

	



	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
	List<ClassVO> classList = null;
	ClassService cSvc = new ClassService();
	classList = cSvc.getAll();
	Class_messageboardVO msg = new Class_messageboardVO();
	
	for(ClassVO cl : classList) {
		if((cl.getT_num_1().equals(tp.getT_num())) || (cl.getT_num_2().equals(tp.getT_num()))) {
			msg.setCs_num(cl.getCs_num());
		}
	}

	
	
	session.setAttribute("msg_back", msg);
%>

<jsp:useBean id="classSvc" scope="page" class="com._class.model.ClassService"/>
<jsp:useBean id="teacherSvc" scope="page" class="com.teacher.model.TeacherService"/>
<jsp:useBean id="guardianSvc" scope="page" class="com.guardian.model.GuardianService"/>


<html>
<head>
<title>班級留言板管理-1</title>
<%@ include file="/back-end/includePage/Head"%>
</head>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/class_ann/css/select_page.css">
	
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	
	
	<div class="col-lg-10 col-md-10 cb rightContent">
	
	

		<%@ include file="/back-end/class_msg/header_text"%>


		
		<table>
			<thead>
				<tr>
					<th><b class="table_b">留言編號</th>
					<th><b class="table_b">班級</th>
					<th><b class="table_b">教師</th>
					<th><b class="table_b">監護人</th>
					<th><b class="table_b">留言</th>
					<th><b class="table_b">刪除留言</th>
				</tr>
			</thead>
	

			<tbody>
				<c:forEach var="class_messageboardVO" items="<%=list %>">
				
				
<!-- 				權限 -->
				<c:if test="${msg_back.cs_num == class_messageboardVO.cs_num}">
	
				
					<tr>
						<td><b class="table_bb">${class_messageboardVO.getMsg_num()}</td>
						
						
						<td>
						<c:forEach var="classVO" items="${classSvc.all}">
						<c:if test="${class_messageboardVO.cs_num == classVO.cs_num}">	
							<b class="table_bb">${classVO.getCs_name()}
						</c:if>	
						</c:forEach>
						</td>
						
						
						<td>
						<c:forEach var="teacherVO" items="${teacherSvc.all}">
						<c:if test="${class_messageboardVO.t_num == teacherVO.t_num}">	
							<b class="table_bb" style="color:#FF3333">${teacherVO.getT_name()}
						</c:if>	
						</c:forEach>
						</td>
						
						
						<td>
						<c:forEach var="guardianVO" items="${guardianSvc.all}">	
						<c:if test="${class_messageboardVO.gd_id == guardianVO.gd_id}">
							<b class="table_bb" style="color:#5599FF">${guardianVO.gd_name}
						</c:if>
						</c:forEach>
						</td>
						
						
						
						<td><b class="table_bb">${class_messageboardVO.getMsg()}</td>
						
							
						
						<td>
							<form method="post" action="<%=request.getContextPath()%>/class_msg/class_msg.do">
								<input type="submit" value="刪除"> 
								<input type="hidden" name="msg_num" value="${class_messageboardVO.msg_num}">
								<input type="hidden" name="t_num" value="${msg_back.t_num}">
								<input type="hidden" name="cs_num" value="${msg_back.cs_num}"> 
								<input type="hidden" name="action" value="delete">
							</form>
						</td>
					</tr>
					
					
				</c:if>
				
				</c:forEach>
			</tbody>
		</table>
		
		<form method="post" action="<%= request.getContextPath()%>/class_msg/class_msg.do">
			<input type="text" name="msg" maxlength="3000" placeholder="請輸入留言">
			<input type="hidden" name="t_num" value="${msg_back.t_num}">
			<input type="hidden" name="cs_num" value="${msg_back.cs_num}">
			<input type="hidden" name="action" value="insert">
			<input type="submit" class="submit" value="教師留言">
		</form>
		
		
		

		
		
		
		
	</div>
</body>
<%@ include file="/back-end/includePage/BootStrap"%>

</html>