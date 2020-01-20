<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.student.model.*"%>
<%@ page import="com.student_leave_list.model.*"%>
<%@ page import="com._class.model.*"%>

<%
	Student_leave_listVO student_leave_listVO = (Student_leave_listVO)request.getAttribute("student_leave_listVO");
	
	StudentVO stVO = (StudentVO) session.getAttribute("stp");
	Student_leave_listService sllSvc = new Student_leave_listService();

	pageContext.setAttribute("st_list", sllSvc.findByPrimaryKeyyy(stVO.getSt_num()));
%>


<html>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/student_leave_list/css/leave_new.css">
<head>
<title>學生請假頁面</title>
<%@ include file="/front-end/Head"%>
																		
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/class_ann/datetimepicker/jquery.datetimepicker.css" />
</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/student_leave_list/css/select_page.css">

<jsp:useBean id="slSvc" class="com.student_leave_list.model.Student_leave_listService"/>
<jsp:useBean id="couSvc" class="com.course.model.CourseService"/>
<body>


	<%@ include file="/front-end/Nav"%>
	<%@ include file="/front-end/Top"%>

	<!-- 自己的畫面 -->


	<div class="col-lg-10 col-md-10 cb rightContent">
	
		<h1 class="add_h1">學生請假區</h1>
		
			
		<%--錯誤列表--%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red" size="5px">請修正以下錯誤</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: purple">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

<form method="post"
			action="<%=request.getContextPath()%>/student_leave/student_leave.do" name="form_add">
			<table class="table">
				<tr>
					<td><b>學生學號</b></td>
					<td><input type="hidden" name="st_num"  value="${stp.st_num}" /><b style="color:gray">${stp.st_num}</td>
				</tr>
				
				
				<tr>
					<td><b>請假起始時間</b></td>
					<td><input type="text" name="start_date" id="start_dateTime" class="qq1"></td>
				</tr>
				
				<tr>
					<td><b>請假結束時間</b></td>
					<td><input type="text" name="end_date" id="end_dateTime" class="qq2"></td>
				</tr>
				
			
				<tr>
					<td><b>請假假別</b></td>
					<td><select name="st_list_sort">
						<option value="0">病假(0)
						<option value="1">事假(1)
						<option value="2">喪假(2)
						<option value="3">其他(3)
					</select></td>
				</tr>	


				<tr>
					<td><b>請假事由</b></td>
					<td><input type="text" name="st_list_note"
						value="<%=(student_leave_listVO == null) ? "" : student_leave_listVO.getSt_list_note()%>" class="qq3" />
					</td>
				</tr>


			</table>
			<center><input type="hidden" name="action" value="insert"> <input
				type="submit" class="submit" value="送出假單"> <input
				type="reset" class="reset" value="重新填寫"></center>
		</form>
	
	

<!----------------------------magic-button----------------------------------->
	<div>
		<button class="quick_btn2">
			<br>P孩請假
			<img src="<%=request.getContextPath() %>/front-end/student_leave_list/photo/magic_btn.png">
		</button>
	</div>	
		
<!--------------------------------------------------------------------------->
			
		

		<hr class="hr">
		
		
		<table class="table"><center><b><h2 style="color:#5599FF">請 假 紀 錄</h2></center>
			<tr>
				<th style="color:#5599FF">假 單 編 號</th>
				<th style="color:#5599FF">學 生 學 號</th>
				<th style="color:#5599FF">請 假 起 始 時 間</th>
				<th style="color:#5599FF">請 假 結 束 時 間</th>
				<th style="color:#5599FF">請 假 假 別</th>
				<th style="color:#5599FF">請 假 事 由</th>
			</tr>
			
		<c:forEach var="stllVO" items="${slSvc.findByPrimaryKeyyy(stp.st_num)}">

			<tr>
				<th>${stllVO.st_list_num}</th>
				<th>${stllVO.st_num}</th>
				<th>${stllVO.start_date}</th>
				<th>${stllVO.end_date}</th>
				<th>${stllVO.st_list_sort}</th>
				<th>${stllVO.st_list_note}</th>
			</tr>
		</c:forEach>
			
		</table>
		
		
		
		
		

	</div>

	
<%-- 	<%@ include file="/front-end/Footer"%> --%>
	<script src="<%=request.getContextPath()%>/front-end/class_ann/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/class_ann/datetimepicker/datetimepicker/jquery.js"></script>
	
	
	
	
	
	
</body>




<script>

// calander
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#start_dateTime').datetimepicker({
	  format:'Y-m-d H:i',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_dateTime').val()?$('#end_dateTime').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
	 
	 $('#end_dateTime').datetimepicker({
	  format:'Y-m-d H:i',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_dateTime').val()?$('#start_dateTime').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
});



// magic-button
	$(document).ready(function() {
		$('.quick_btn2').click(function() {
			$('.qq1').attr('value','2019-09-26 10:10');
			$('.qq2').attr('value','2019-09-27 12:00');
			$('.qq3').attr('value','跳大象舞，著涼感冒了..');
		})
	})



</script>


	
	<!-- import swal -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
	<script src="sweetalert2.all.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
	
	
	<!-- set swal -->
	<c:if test="${addlist}">
<script>
Swal.fire({
  position: 'middle',
  type: 'success',
  title: '假單申請成功 !',
  showConfirmButton: false,
  timer: 2250
})
</script>
	</c:if>
	


</script>

</html>