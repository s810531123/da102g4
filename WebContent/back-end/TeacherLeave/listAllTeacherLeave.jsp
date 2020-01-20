<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Map"%>
<%@page import="com.teacherLeave.model.TeacherLeaveVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有假單資料 - listAllTeacherLeave.jsp</title>
<%@ include file="/back-end/includePage/Head"%>
<!-- 自己的css -->
<link rel="stylesheet" href="./css/listAll.css">

</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	
		<%@ include file="/back-end/includePage/LeftSide"%>
	
	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="color-manto">
			<%					
				TeacherLeaveVO teacherLeaveVO = (TeacherLeaveVO) request.getAttribute("teacherLeaveVO");
			%>
			<c:if test="${sessionScope.tp.t_num == null}">請先登入</c:if>
			<c:if test="${!(sessionScope.tp.t_num == null)}">
			
				<!-- 將Servlet判斷加註於此，讓查詢全部可以取值 -->
				<c:if test="${teacherVOList == null}">
					<jsp:forward page="teacherLeave.do">
						<jsp:param name="action" value="getAll_For_Display" />
					</jsp:forward>
				</c:if>

				<!-- 園長身份辨別 -->
				<c:if test="${sessionScope.tp.t_num == 'T001'}">
					<table>
						<h2 align="center" style="color:#20B2AA;font-family: Microsoft JhengHei;">全 體 員 工 假 單</h2>
						<form method="post" action="teacherLeave.do">
							查詢假單狀態：
							<select name="tl_status">
								<c:forEach var="statusKey" items="${leaveStatus }">
									<option value="${statusKey.key}" ${(param.tl_status == statusKey.key) ? 'selected' : statusKey.key }>
										${statusKey.value}
									</option> 
								</c:forEach>
							</select> 
							<input type="hidden" name="action" value="getAll_For_Display">&nbsp&nbsp
							<button type="submit" class="btn-warning" style="border-radius: 7px">送出查詢</button>
						</form>
						<tr>
							<th>假單編號</th>
							<th>教師姓名</th>
							<th>假單起始日期</th>
							<th>假單結束日期</th>
							<th>請假假別</th>
							<th>請假事由</th>
							<th>假單申請日</th>
							<th>假單狀態</th>
							<th>審核進度</th>
						</tr>
						<c:forEach var="teacherLeaveVO" items="${teacherLeaveVOList}">
							<!-- 控制權共用，可接收Servlet曾經送出的任何參數 -->
							<c:if test="${param.tl_status != null && param.tl_status == teacherLeaveVO.tl_Status}">
								<tr>
									<td>${teacherLeaveVO.tl_Id}</td>
									<td>
										<!-- 取得教師姓名 -->
										<c:forEach var="teacherVO" items="${teacherVOList}">
											<c:if test="${teacherVO.t_num == teacherLeaveVO.t_Num}">
												${teacherVO.t_name}
											</c:if>
										</c:forEach>
									</td>
									<td>${teacherLeaveVO.tl_Sdate}</td>
									<td>${teacherLeaveVO.tl_Edate}</td>
									<td>
										<c:forEach var="Map" items="${leaveReason}">
											<c:if test="${Map.key == teacherLeaveVO.tl_Type}">
												${Map.value }
											</c:if>
										</c:forEach>
									</td>
									<td>${teacherLeaveVO.tl_Reason}</td>
									<td>${teacherLeaveVO.tl_App_Date}</td>
									<td>
										<c:forEach var="Map" items="${leaveStatus}">
											<c:if test="${Map.key == teacherLeaveVO.tl_Status}">
												${Map.value }
											</c:if>
										</c:forEach>
									</td>
									<td>
										<c:if test="${teacherLeaveVO.tl_Status == 0 }">
											<FORM method="post" action="teacherLeave.do">
												<button type="submit" class="btn-success" style="border-radius: 7px">審核</button>
												<input type="hidden" name="tl_Id" value="${teacherLeaveVO.tl_Id}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</c:if> 
										<c:if test="${!(teacherLeaveVO.tl_Status == 0)}">							
												已審核 																			
										</c:if>
									</td>
								</tr>
							</c:if>
							<!-- 控制權共用，可接收Servlet曾經送出的任何參數 -->
							<c:if test="${(param.tl_status == null)||(param.tl_status == '3')}">
								<tr>
									<td>${teacherLeaveVO.tl_Id}</td>
									<td>
										<c:forEach var="teacherVO" items="${teacherVOList}">
											<c:if test="${teacherVO.t_num == teacherLeaveVO.t_Num}">
												${teacherVO.t_name}
											</c:if>
										</c:forEach>
									</td>
									<td>${teacherLeaveVO.tl_Sdate}</td>
									<td>${teacherLeaveVO.tl_Edate}</td>
									<td>
										<c:forEach var="Map" items="${leaveReason}">
											<c:if test="${Map.key == teacherLeaveVO.tl_Type}">
												${Map.value }
											</c:if>
										</c:forEach>
									</td>
									<td>${teacherLeaveVO.tl_Reason}</td>
									<td>${teacherLeaveVO.tl_App_Date}</td>
									<td>
										<c:forEach var="Map" items="${leaveStatus}">
											<c:if test="${Map.key == teacherLeaveVO.tl_Status}">
												${Map.value }
											</c:if>
										</c:forEach>
									</td>

									<td>
										<c:if test="${teacherLeaveVO.tl_Status == 0 }">
											<FORM method="post" action="teacherLeave.do">
												<button type="submit" class="btn-success" style="border-radius: 7px">審核</button>
												<input type="hidden" name="tl_Id" value="${teacherLeaveVO.tl_Id}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</c:if> 
										<c:if test="${!(teacherLeaveVO.tl_Status == 0)}">							
												已審核 																			
										</c:if>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</c:if>

				<!-- 教師身份辨別 -->
				<c:if test="${!(sessionScope.tp.t_num == 'T001')}">
					<table border="1">
						<h2 align="center" style="color:#20B2AA;font-family: Microsoft JhengHei;">${sessionScope.tp.t_name}的假單</h2>
						<form method="post" action="teacherLeave.do">
							查詢假單狀態: 
							<select name="tl_status">
								<c:forEach var="statusKey" items="${leaveStatus}">
									<option value="${statusKey.key}" ${(param.tl_status == statusKey.key) ? 'selected' : statusKey.key }>
										${statusKey.value}
									</option> 
								</c:forEach>
							</select> 
							<input type="hidden" name="action" value="getAll_For_Display">&nbsp&nbsp
							<button type="submit" class="btn-warning" style="border-radius: 7px">送出查詢</button>
						</form>
						<tr>
							<th>假單編號</th>
							<th>假單起始日期</th>
							<th>假單結束日期</th>
							<th>請假假別</th>
							<th>請假事由</th>
							<th>假單申請日</th>
							<th>假單狀態</th>
							<th>修改</th>
						</tr>
						<c:forEach var="teacherLeaveVO" items="${teacherLeaveVOList}">
							<c:if test="${sessionScope.tp.t_num == teacherLeaveVO.t_Num}">
								<!-- 控制權共用，可接收Servlet曾經送出的任何參數 -->
								<c:if test="${param.tl_status != null && param.tl_status == teacherLeaveVO.tl_Status}">
									<tr>
										<td>${teacherLeaveVO.tl_Id}</td>
										<td>${teacherLeaveVO.tl_Sdate}</td>
										<td>${teacherLeaveVO.tl_Edate}</td>
										<td>
											<c:forEach var="Map" items="${leaveReason}">
												<c:if test="${Map.key == teacherLeaveVO.tl_Type}">
													${Map.value }
												</c:if>
											</c:forEach>
										</td>
										<td>${teacherLeaveVO.tl_Reason}</td>
										<td>${teacherLeaveVO.tl_App_Date}</td>
										<td>
											<c:forEach var="Map" items="${leaveStatus}">
												<c:if test="${Map.key == teacherLeaveVO.tl_Status}">
													${Map.value}
												</c:if>
											</c:forEach>
										</td>
										<td>
											<c:if test="${teacherLeaveVO.tl_Status == 0}">
												<FORM method="post" action="teacherLeave.do">
													<button type="submit" class="btn-success" style="border-radius: 7px">修改</button>
													<input type="hidden" name="tl_Id" value="${teacherLeaveVO.tl_Id}">
													<input type="hidden" name="action" value="getOne_For_Update">
												</FORM>
											</c:if> 
											<c:if test="${!(teacherLeaveVO.tl_Status == 0)}">		
													已審核，不可修改
											</c:if>
										</td>
									</tr>
								</c:if>
								<!-- 控制權共用，可接收Servlet曾經送出的任何參數 -->
								<c:if test="${(param.tl_status == null) || (param.tl_status == '3')}">
									<tr>
										<td>${teacherLeaveVO.tl_Id}</td>
										<td>${teacherLeaveVO.tl_Sdate}</td>
										<td>${teacherLeaveVO.tl_Edate}</td>
										<td>
											<c:forEach var="Map" items="${leaveReason}">
												<c:if test="${Map.key == teacherLeaveVO.tl_Type}">
													${Map.value }
												</c:if>
											</c:forEach>
										</td>
										<td>${teacherLeaveVO.tl_Reason}</td>
										<td>${teacherLeaveVO.tl_App_Date}</td>
										<td>
											<c:forEach var="Map" items="${leaveStatus}">
												<c:if test="${Map.key == teacherLeaveVO.tl_Status}">
													${Map.value}
												</c:if>
											</c:forEach>
										</td>
										<td>
											<c:if test="${teacherLeaveVO.tl_Status == 0}">
												<FORM method="post" action="teacherLeave.do">
													<button type="sybmit" class="btn-success" style="border-radius: 7px">修改</button>													
													<input type="hidden" name="tl_Id" value="${teacherLeaveVO.tl_Id}">
													<input type="hidden" name="action"value="getOne_For_Update">
												</FORM>
											</c:if> 
											<c:if test="${!(teacherLeaveVO.tl_Status == 0)}">		
													已審核，不可修改
											</c:if>
										</td>
									</tr>
								</c:if>
							</c:if>
						</c:forEach>
					</table>
				</c:if>
			</c:if>
		</div>
	</div>
</body>

<%@ include file="/back-end/includePage/BootStrap"%>
<script type="text/javascript">

	$(".btn").click(function() {
		$(this).parent().submit();
	})


</script>
</html>