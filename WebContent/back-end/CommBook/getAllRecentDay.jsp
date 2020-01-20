<%@ page import="java.util.Set"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.student.model.StudentVO"%>
<%@ page import="java.util.List"%>
<%@ page import="com.commBook.model.CommBookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	z-index: 3;
	border: 1px solid #842b00;
}

th {
	background-color:#FFFACD;
}

td{
	text-align: left;
}

.commDay{
	width: 35%;
}

.itemName{
	width: 15%;
	text-align: center !important;
}

.pencil{
	text-align: right !important;
}

.bottom-line{
	border-bottom:solid 3px black;
}

</style>
</head>

<body>
	<c:if test="${allStudentCommBook == null}">
		<%
			System.out.println("沒有資料");
		%>
		<jsp:forward page="commBook.do">
			<jsp:param name="action" value="showTwoDayFromTeacher" />
		</jsp:forward>
	</c:if>



	<%
		Map<StudentVO, ArrayList<CommBookVO>> allStudentCommBook = (LinkedHashMap<StudentVO, ArrayList<CommBookVO>>) request
				.getAttribute("allStudentCommBook");
	%>
	<%
		Set<StudentVO> studentSet = allStudentCommBook.keySet();
	%>

	<%
		for (StudentVO studentVO : studentSet) {
			List<CommBookVO> list = allStudentCommBook.get(studentVO);
			if (list.isEmpty()) {
				pageContext.setAttribute("noStudent", "醒醒吧~你還沒寫過聯絡簿！！");
			} else {
				CommBookVO newCommBook = list.get(0);
				String date = newCommBook.getComm_Date().toString(); //拿出"最新一筆"的日期資訊
				//判斷"今日"跟"最新一筆"的日期是否相同，若相同則在 pageContext 放入 Today 資料，並顯示 True，不然則不放
				//若不放，則 EL 拿到資料是 false，也就不顯示按鈕
				if (date.equals(new java.sql.Date(System.currentTimeMillis()).toString())) {
					pageContext.setAttribute("Today", true);
					request.setAttribute("newCommBook", newCommBook);
				}
			}
			break;
		}
	%>


	<c:if test="${noStudent != null}">
		${noStudent}
	</c:if>

	<c:if test="${noStudent == null }">
	<img src="image/star.png" width="90%">
	<img src="image/Logo.png" width="80px" align="right">
	<!-- 系統判斷今天日期才可修改群體發話 -->
<%-- 		<c:if test="${Today}"> --%>
<!-- 			<img src="image/star.png" width="90%"> -->
<!-- 			<img src="image/Logo.png" data-toggle="modal" data-target="#exampleModal" width="80px" title="修改老師的話" align="right"> -->
<!-- 			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 				<div class="modal-dialog" role="document"> -->
<!-- 					<div class="modal-content"> -->
<!-- 						<div class="modal-header">											 -->
<!-- 							<h5 class="modal-title" id="exampleModalLabel" style="color:F000000">修改老師的話</h5>				 -->
<!-- 						</div> -->
<!-- 						<form METHOD="post" ACTION="commBook.do"> -->
<!-- 							<div class="modal-body"> -->
<%-- 								<input type="text" style="width: 460px" name="comm_T_Msg" value="${newCommBook.comm_T_Msg}" minlength=5> --%>
<!-- 								<input type="hidden" name="action" value="updateT_Msg"> -->
<!-- 							</div> -->
<!-- 							<div class="modal-footer"> -->
<!-- 								老師的話 修改燈箱 -->
<!-- 								<img src="image/ok.png" width="30px" id="OK"> -->
<!-- 								<img src="image/cancel.png" width="30px" data-dismiss="modal"> -->
<!-- 							</div> -->
<!-- 						</form> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<%-- 		</c:if> --%>



		<%
			for (StudentVO studentVO : studentSet) {
		%>
		<%
			request.setAttribute("listSize", 0); //預先設定listSize=0...避免沒有聯絡簿的學生也印出來

					List<CommBookVO> commBookList = allStudentCommBook.get(studentVO);
					CommBookVO newCommBook = null;
					CommBookVO oldCommBook = null;

					if (commBookList.size() != 0) {
						request.setAttribute("listSize", commBookList.size());
						newCommBook = commBookList.get(0);
						request.setAttribute("newCommBook", newCommBook);//將日期較新的聯絡簿設定成 newCommBook
						oldCommBook = commBookList.get(1);
						request.setAttribute("oldCommBook", oldCommBook);//將日期較舊的聯絡簿設定成 oldCommBook
						break;
					}
		%>
		<%
			}
		%>
		<c:if test="${allStudentCommBook != null}">
			<table>
				<tr style="border-top:solid 3px black;">
					<th class="itemName bottom-line">姓名</th>
					<th class="itemName bottom-line" >項目</th>
					<th class="commDay bottom-line" >${oldCommBook.comm_Date }</th>
					<th class="commDay bottom-line" colspan="2" >${newCommBook.comm_Date }</th>
				</tr>
				<%
					for (StudentVO studentVO : studentSet) {
				%>
				<%
					request.setAttribute("listSize", 0); //預先設定listSize=0...避免沒有聯絡簿的學生也印出來

								List<CommBookVO> commBookList = allStudentCommBook.get(studentVO);
								CommBookVO newCommBook = null;
								CommBookVO oldCommBook = null;

								if (commBookList.size() != 0) {
									request.setAttribute("listSize", commBookList.size());
									request.removeAttribute("newCommBook");
									newCommBook = commBookList.get(0);
									request.setAttribute("newCommBook", newCommBook);//將日期較新的聯絡簿設定成 newCommBook
									request.removeAttribute("oldCommBook");
									if (commBookList.size() > 1) {
										oldCommBook = commBookList.get(1);
										request.setAttribute("oldCommBook", oldCommBook);//將日期較舊的聯絡簿設定成 oldCommBook
									}
								}
				%>



				<c:if test="${listSize != 0 }">
					<tr >
						<th rowSpan=3 class="bottom-line"><%=studentVO.getSt_name()%></th>
						<td class="itemName">老師的話</td>
						<td>${oldCommBook.comm_T_Msg}</td>
						<td colspan="2">${newCommBook.comm_T_Msg}</td>
					</tr>
					<tr>
						<td class="itemName">給家長的話</td>
						<td>${oldCommBook.comm_Res}</td>
						
						<c:if test="${Today}">
							<td style="border-right: 0px solid;">${newCommBook.comm_Res}</td>
							<td style="border-left: 0px solid;">
								<img src="image/pencil.png" class="pencil" title="請修改" data-toggle="modal" data-target="#comm_${newCommBook.comm_Id}" width="30px">
								<div class="modal fade" id="comm_${newCommBook.comm_Id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header" style="border-bottom:none">
												<h5 class="modal-title" id="exampleModalLabel" style="color:#20B2AA;font-family: Microsoft JhengHei; font-size: 30px;" >
												給家長的話<img src="image/Logo.png" width=50px id="ting"></h5>
											</div>
											<form METHOD="post" ACTION="commBook.do">
												<div class="modal-body" style="border-bottom:none;">
													<input type="hidden" name="comm_Id" value="${newCommBook.comm_Id}"> 
													<input type="text" style="width: 460px" name="comm_Res" value="${newCommBook.comm_Res}" id="stmsg"> 
													<input type="hidden" name="action" value="update_Res">
												</div>
												<div class="modal-footer" style="border-top:none;">												
													<!--給家長的話 修改燈箱 -->
													<button type="submit" class="btn-primary" style="font-size:20px;border-radius: 15px">送出</button>
													<button type="submit" class="btn-danger" style="font-size:20px;border-radius: 15px">取消</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</td>
						</c:if>
						<c:if test="${!Today}">
							<td colspan="2">${newCommBook.comm_Res}</td>
						</c:if>
					</tr>
					<tr>
						<td class="itemName bottom-line">家長回應</td>
						<td class="bottom-line"> ${oldCommBook.comm_P_Msg}</td>
						<td colspan="2" class="bottom-line">${newCommBook.comm_P_Msg}</td>
					</tr>
				</c:if>

				<%
					}
				%>
			</table>
		</c:if>
	</c:if>


</body>


<script type="text/javascript">

	<!--修改老師的話-->
	$(".Logo").click(function() {
		$(this).parent().submit();
	})
	
	<!--修改給家長的話-->
	$(".pencil").click(function() {
		$(this).parent().submit();
	})
	
	//神奇小按鈕
// 	$("#ting").click(function() {
// 		$("#stmsg").val("今天在學校表現很棒喔!");
// 	});
	
	<!--修改老師的話 確認--> 
	$(".OK2").click(function() {
		$(this).parent().parent().submit();
	})
	
	
	$("#cancel2").click(function() {
		$(this).parent().parent().submit();
	})
	
});
	
</script>
</html>