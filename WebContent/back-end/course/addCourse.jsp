<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADD Course</title>
</head>
<body>

	<!-- 新增課程燈箱 -->
<jsp:useBean id="teaSvc" class="com.teacher.model.TeacherService"/>
	<div class=" col-lg-10 container updateAddCourse insertCourse" id="insertdisplay">

		<div class="lightbox">
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/course/course.do" enctype="multipart/form-data">

				<!-- ====== -->
				<div class="row rowbox">

					<div class="col-4">
						<div id="courseName">課程名稱</div>
						<div>
							<input type="text" name="cou_name" value="${(errInsert == 'failInsert')?courseVO.cou_name : "" }" id="cou_name">
						</div>

						<input type="file" name="cou_pic" id="addPic" accept="image/gif, image/jpeg, image/png, image/jpg">
					</div>

					<div class="col-lg-6">
						<!-- <p>圖片</p> -->
						<div></div>
						<img src="#" id="displayAddPic">
					</div>
				</div>
				<!-- ====== -->

				<!-- ====== -->
				<hr>

				<div class="row rowbox courseText">

					<div class="col-6">
						<div>課程介紹</div>
						<div>
							<textarea name="cou_introd" id="cou_introd">${(errInsert == 'failInsert')?courseVO.cou_introd : "" }</textarea>
						</div>
					</div>

				</div>
				<hr>

				<div class="row rowbox">

					<div class="col-lg-3">
						<div>授課教師</div>
						<div>
	                        <select size="1" name="t_num">
						       <option value="">全部教師
         						<c:forEach var="teacherVO" items="${teaSvc.all }" > 
          							<option value="${teacherVO.t_num }" ${teacherVO.t_num == courseVO.t_num ? 'selected' : ""}>${teacherVO.t_name}
        						 </c:forEach>   
       							</select>	
       						</div>
					</div>
					<div class="col-lg-3">
						<div>開課日期</div>
						<div>
							<input type="text" name="cou_sdate" value="${(errInsert == 'failInsert')?courseVO.cou_sdate : ""}" id="cou_sdate">
						</div>
					</div>
					<div class="col-lg-4">
						<div>結束日期</div>
						<div>
							<input type="text" name="cou_edate" value="${(errInsert == 'failInsert')?courseVO.cou_edate : ""}"  id="cou_edate">
						</div>
					</div>

				</div>
				<hr>

				<div class="row rowbox">

					<div class="col-lg-3">
						<div>開課人數</div>
						<div>
							 <input type="text" id="cou_min" name="cou_min" value="${(errInsert == 'failInsert')?courseVO.cou_min : ""}"> 
						</div>
					</div>

					<div class="col-lg-3">
						<div>人數上限</div>
						<div>
							<input type="text" id="cou_max" name="cou_max" value="${(errInsert == 'failInsert')?courseVO.cou_max : ""}">
						</div>
					</div>

					<div class="col-lg-3">
						<div>課程費用</div>
						<div>
							<input type="text" id="cou_cost" name="cou_cost" value="${(errInsert == 'failInsert')?courseVO.cou_cost : ""}">
						</div>
					</div>

				</div>

				<hr>

				<c:if test="${(not empty errorMsgs) && (errInsert == 'failInsert')}">
					<font style="color: red">請修正以下欄位:</font>

					<c:forEach var="message" items="${errorMsgs}">
						<p style="color: red" class="mes">${message}</p>
					</c:forEach>

				</c:if>

				<div align="center">
					<input class="btn courseLightBtn" type=submit id="updateConfirm" value="新增"> 
					<input class="btn courseLightBtn CourseCancel" type="button" value="取消"> 
					<input type="button" value="神奇" id="super" style="opacity: 0;">
					<input type="hidden" name="action" value="insert">
				</div>
			</form>
		</div>
	</div>



</body>


<!-- <link   rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" /> -->
<!-- <script src="datetimepicker/jquery.js"></script> -->
<!-- <script src="datetimepicker/jquery.datetimepicker.full.js"></script> -->
</html>