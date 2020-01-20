<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>

<%
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
%>

<!DOCTYPE html>
<html>
<head>
	<title>IBM Guardian: Home</title>
	<%@ include file="/back-end/includePage/Head"%>
	<style>
		#table-1 {
			margin-left:auto;
			margin-right:auto;
			border: 1px solid;
			text-align:left;
		}
		.teahcerTitle{
			text-align:right;
		}
	</style>

</head>
<body>

	<%@ include file="/back-end/includePage/Nav"%>
	

	<%@ include file="/back-end/includePage/LeftSide"%>


	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="own">
		
			<h3>�d�ߩҦ��a�����:</h3>
			
			<a href='<%= request.getContextPath()%>/back-end/guardian/listAllGuardian.jsp'>�I���d�ߩҦ��a�����</a><br><br>
				
			<%-- ���~��C --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">�Эץ��H�U���~:</font>
				<c:forEach var="message" items="${errorMsgs}">
					<span style="color:red">${message}</span>
				</c:forEach>
			</c:if>
			
			<table id="table-1">
				<tr>
					<td>
						<b>��J�a�������Ҧr��:</b>
					</td>
					<td>
						<FORM METHOD="post" ACTION="guardian.do" >
				        	<input type="text" name="gd_id">
				        	<input type="hidden" name="action" value="getOne_For_Display">
				        	<input type="submit" value="�e�X">
			    		</FORM>
					</td>
				</tr>
				<tr>
					<td>
						<b>��ܮa���m�W:</b>
					</td>
					<td>
						<jsp:useBean id="guardianSvc" scope="page" class="com.guardian.model.GuardianService" />
						<FORM METHOD="post" ACTION="guardian.do" >
						    <select size="1" name="gd_id">
								<c:forEach var="guardianVO" items="${guardianSvc.all}" > 
									<option value="${guardianVO.gd_id}">${guardianVO.gd_name}
								</c:forEach>   
							</select>
							<input type="hidden" name="action" value="getOne_For_Display">
							<input type="submit" value="�e�X">
						</FORM>
					</td>
				</tr>
			</table>
			
			<br><br>
			
			<h3>�a���޲z</h3>
			
			<a href='addGuardian.jsp'>Add a new Guardian.</a>
		</div>
	</div>
</body>
	<%@ include file="/back-end/includePage/BootStrap"%>
</html>