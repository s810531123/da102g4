<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.guardian.model.*"%>
<%@ page import="com.teacher.model.*"%>

<%
	GuardianService guardianSvc = new GuardianService();
	List<GuardianVO> list = guardianSvc.getAll();
	pageContext.setAttribute("list", list);
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
%>

<!DOCTYPE html>
<html>
<head>
	<title>�Ҧ��a����� - listAllGuardian.jsp</title>

	<%@ include file="/back-end/includePage/Head"%>
	
	<style>
	  #table-1 {
		width: 1050px;
		background-color: white;
		margin-top: 5px;
		margin-bottom: 5px;
	  }
	  table, th, td {
	    border: 1px solid #CCCCFF;
	  }
	  th, td {
	    padding: 5px;
	    text-align: center;
	  }
	  
	  #hi {
		margin-top: 6%;
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

			<c:if test="${'���' eq teacherVO.t_job}">
		 		<h4><a href="<%= request.getContextPath()%>/back-end/guardian/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/guardian/images/back.gif" width="100" height="32" border="0">�^����</a></h4>
			</c:if>
			
			<c:if test="${'�Z�ɮv' eq teacherVO.t_job}">
		 		<h4><a href="<%= request.getContextPath()%>/back-end/guardian/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/guardian/images/back.gif" width="100" height="32" border="0">�^����</a></h4>
			</c:if>
			
			<c:if test="${'�Ѯv' eq teacherVO.t_job}">
		 		<h4><a href="<%= request.getContextPath()%>/back-end/guardian/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/guardian/images/back.gif" width="100" height="32" border="0">�^����</a></h4>
			</c:if>

			<%-- ���~��C --%>

			<c:if test="${not empty errorMsgs}">
				<table id="table-1">
					<tr>
						<td id="error">
							<font style="color:red">�Эץ��H�U���~:</font>
						</td>
					</tr>
					<tr>
						<td>	
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
						</td>
					</tr>
				</table>
			</c:if>

			<table id="table-1">
				<tr height="50px">
					<th>�a���m�W</th>
					<th>�ʧO</th>
					<th>�����Ҧr��</th>
					<th>E-mail</th>
					<th>�q�T�a�}</th>
					<th>���Y</th>
					<th>�s���q��</th>
					<th>�ͤ�</th>
					<c:if test="${'���' eq teacherVO.t_job}">
						<th>�ק�</th>
					</c:if>
				</tr>
				<%@ include file="page1.file" %> 
				<c:forEach var="guardianVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					
					<tr>
						<td>${guardianVO.gd_name}</td>
						<td>${guardianVO.gd_gender}</td>
						<td>${guardianVO.gd_id}</td>
						<td>${guardianVO.gd_email}</td>
						<td>${guardianVO.gd_address}</td>
						<td>${guardianVO.gd_rel}</td>
						<td>${guardianVO.gd_phone}</td>
						<td>${guardianVO.gd_birthday}</td>
						<c:if test="${'���' eq teacherVO.t_job}">
							<td>
						  		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/guardian/guardian.do" style="margin-bottom: 0px;">
									<input type="submit" value="�ק�">
									<input type="hidden" name="gd_id"  value="${guardianVO.gd_id}">
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