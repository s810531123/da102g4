<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.courselist.model.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/back-end/includePage/Head"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/course/js/backCourse.js"></script>
 <!-- sweet alert .css & .js cdn -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/back-end/course/css/backcourse.css">
<%
	List<Integer> signCount = (List<Integer>) request.getAttribute("signCount");
	List<Integer> payCount = (List<Integer>) request.getAttribute("payCount");
	List<Integer> course_num = (List<Integer>) request.getAttribute("course_num");

	CourseVO courseVO = (CourseVO) request.getAttribute("courseVO");

// 	CourseListService coulSvc = new CourseListService();
%>
<jsp:useBean id="coulSvc" class="com.courselist.model.CourseListService"/>
<jsp:useBean id="listCourse_ByCompositeQuery" scope="request" type="java.util.List<CourseVO>" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<title>BackCourseListComposite</title>

<style type="text/css">
.word {
	font-weight: 700;
	color: blue;
	font-size: 18px;
}

.page:hover {
	font-weight: 900;
	color: red;
}

.page {
	font-weight: 900;
	color: orange;
	cursor: pointer;
	font-size: 25px;
}
</style>

</head>
<body>



	<%
		HashMap status = new HashMap();
		status.put(0, "downarrow.png");
		status.put(1, "uparrow.png");
		status.put(2,"開課中");
		status.put(3,"課程已結束");
		pageContext.setAttribute("status", status);

		HashMap payStatus = new HashMap();
		payStatus.put(0, "未繳費");
		payStatus.put(1, "已繳費");
		payStatus.put(2, "已取消選課");
		pageContext.setAttribute("payStatus", payStatus);
		String updatelight = (String) request.getAttribute("errUpdate");
		String insertlight = (String) request.getAttribute("errInsert");
	%>



<%@ include file="/back-end/includePage/Nav"%>
<%@ include file="/back-end/includePage/LeftSide"%>

	

		



		<div class="col-lg-10 col-md-10 rightContent">

			<jsp:include page="addCourse.jsp" flush="true" />

			<%
				Integer num = 0;
			%>
			<!-- 課程資訊燈箱 -->
			<c:forEach var="courseVO" items="${listCourse_ByCompositeQuery}">

				<%--  選課詳情燈箱   --%>
			

				<% List<CourseListVO> CourseList = coulSvc.getAllByCou_num(/*course_num.get(num++)*/listCourse_ByCompositeQuery.get(num++).getCou_num()); 
					   pageContext.setAttribute("CourseList",CourseList);
				%>

				<%-- --%>
				<div class="updateAddCourseList col-lg-10 container"
					id="cl${courseVO.cou_num}">
					<div class="CLlightbox col-lg-10">

						<div class="row courselist courselistTitle">
							<div class="col-lg-2">學生姓名</div>
							<div class="col-lg-2">繳費狀態</div>
							<div class="col-lg-3">報名日期</div>
						</div>

						<c:forEach var="courseList" items="${CourseList}">
							<div class="row courselist">
								<div class="col-lg-2">${courseList.st_num}</div>
								<div class="col-lg-2">${payStatus[courseList.pay_status]}</div>
								<div class="col-lg-3">${courseList.cou_list_date}</div>
							</div>
						</c:forEach>
						<input class="btn courseLightBtn CourseCancel" type="button" value="取消">

					</div>
				</div>
				<%----%>

				<%--  選課詳情燈箱 ^  --%>

				<div class="updateAddCourse col-lg-10 container"
					id="${courseVO.cou_num}">

					<div class="lightbox col-lg-10">
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/course/course.do" enctype="multipart/form-data">

							<!-- ======= -->

							<div class="row rowbox">

								<div class="col-4">
									<div id="courseName">課程名稱</div>
									<div>
										<input type="text" name="cou_name" value="${courseVO.cou_name}">
									</div>
									<input type="file" name="cou_pic" id="updatePic" accept="image/gif, image/jpeg, image/png">
								</div>

								<div class="col-lg-6">
									<!-- <p>圖片</p> -->
									<div></div>
									<!-- -->
									<img
										src="<%=request.getContextPath()%>/img/img.do?cou_num=${courseVO.cou_num}" class="displayUpdatePic">
								</div>
							</div>

							<!-- ======= -->

							<hr>

							<div class="row rowbox courseText">

								<div class="col-6">
									<div>課程介紹</div>
									<div>
										<textarea name="cou_introd">${courseVO.cou_introd}</textarea>
									</div>
								</div>

							</div>

							<hr>

							<div class="row rowbox">
								<div class="col-lg-3">
									<div>授課教師</div>
									<div>
										<input type="text" name="t_num" value="${courseVO.t_num}">
									</div>
								</div>
								<div class="col-lg-3">
									<div>開課日期</div>
									<div>
										<input type="text" name="cou_sdate" id="cou_sdate1" value="${courseVO.cou_sdate}" class="stdate">
									</div>
								</div>
								<div class="col-lg-3">
									<div>結束日期</div>
									<div>
										<input type="text" name="cou_edate" id="cou_edate1" value="${courseVO.cou_edate}" class="edate">
									</div>
								</div>
							</div>

							<hr>
							<div class="row rowbox">
								<div class="col-lg-3">
									<div>開課人數</div>
									<div>
										<input type="text" name="cou_min" value="${courseVO.cou_min}">
									</div>
								</div>

								<div class="col-lg-3">
									<div>人數上限</div>
									<div>
										<input type="text" name="cou_max" value="${courseVO.cou_max}">
									</div>
								</div>

								<div class="col-lg-3">
									<div>課程費用</div>
									<div>
										<input type="text" name="cou_cost" value="${courseVO.cou_cost}">
									</div>
								</div>
							</div>

							<hr>


							<c:if
								test="${(not empty errorMsgs) && (courseVO.cou_num == requestScope.courseVO.cou_num)}">
								<font style="color: red">請修正以下欄位 ： </font>

								<c:forEach var="message" items="${errorMsgs}">
									<p style="color: red" class="mes">${message}</p>
								</c:forEach>

							</c:if>


							<div align="center">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
								<input class="btn courseLightBtn" type=submit id="updateConfirm" value="修改"> 
								<input class="btn courseLightBtn CourseCancel" type="button" value="取消">
							    <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
							    <input type="hidden" name="cou_num" value="${courseVO.cou_num}">
								<input type="hidden" name="action" value="update">

							</div>
						</form>
					</div>
				</div>
			</c:forEach>
			<!-- ==========課程資訊燈箱============ -->


			<div class="row">
				<div class="col-lg-10 cb course">
					<span class="lightgreen">美</span><span class="lightblue">心</span><span
						class="pink">幼</span><span class="gold">兒</span><span class="red">園</span>
					<span class="lightgreen">課</span><span class="gold">程</span><span
						class="pink">資</span><span class="lightblue">訊</span>
				</div>
				<div>
					<img src="<%=request.getContextPath()%>/back-end/images/courseicon.png" class="logo" id="addCourse">
				</div>

			</div>
			<!-- =============================== -->

			<!-- =============搜尋列表============ -->


			<jsp:useBean id="teaSvc" scope="page" class="com.teacher.model.TeacherService" />

			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/course.do">
				<div class="col-lg-10 row">
					<div class="col-lg-3">

						<b>教師姓名:</b> <select size="1" name="t_num">
							<option value="">全部教師
								<c:forEach var="teacherVO" items="${teaSvc.all}">
									<option value="${teacherVO.t_num}"
										${(t_num == teacherVO.t_num) ? 'selected' : ''}>${teacherVO.t_name}
								</c:forEach>
						</select>
					</div>
					<div class="col-lg-3">
						<b>課程狀態:</b> <select size="1" name="cou_status">
							<option value="" ${(cou_status == "") ? 'selected' : ''}>上架+下架

								<option value="0" ${(cou_status == '0') ? 'selected' : ''}>下架中
						          
							<option value="1" ${(cou_status == '1') ? 'selected' : ''}>上架中			
							
							<option value="2">開課中				          
						          <option value="3">已結束			          
						       
						</select>			   
						  </div>				  
						  <div class="col-lg-3">		  
						       <b>課程名稱:</b>
						       <input type="text" name="cou_name" value="${cou_name}">
					<input type="hidden" name="action" value="listCourse_ByCompositeQuery"></div>   
						  <input type="submit" value="查詢" class="courseLightBtn btn">
						  </div>
						</form>
						  
					
						 
					
					
					
					<!-- =============================== -->
					
					<div class="outer">
						
							<div class="row">		
			            <div class="col-lg-12 col-md-10 CourseTitleOut">
			              
				              <div class="courseTitle col-lg-2 col-md-2">課程名稱</div>
				              <div class="courseTitle col-lg-1 col-md-2">授課教師</div>
				              <div class="courseTitle col-lg-2 col-md-2">開課日期</div>
				              <div class="courseTitle col-lg-2 col-md-2">結束日期</div>
				              <div class="courseTitle col-lg-1 col-md-1">人數上限</div>
				              <div class="courseTitle col-lg-1 col-md-1">課程費用</div>
				              <div class="courseTitle col-lg-1 col-md-1">課程狀態</div>
				              <div class="courseTitle col-lg-1 col-md-1"></div>
			              </div>
			            </div>
	
			        <%@ include file="page/page1_ByCompositeQuery.file"%> 
			        <%
 			        	Integer p = 0;
			        
			        try {
			            whichPage = Integer.parseInt(request.getParameter("whichPage"));
			         } catch (NumberFormatException e) { 
			            whichPage=1;      
			         } 	
			        
 			        	if (whichPage >= 2) {
 			        		p = (whichPage - 1) * 3;
 			        	}
 			        %>
			       
					<c:forEach var="courseVO" items="${listCourse_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						
			            <div class="col-lg-12 col-md-10 myCourse">
			            	
			              <div class="showCoures col-lg-2 col-md-2">${courseVO.cou_name}</div>
			              <div class="showCoures col-lg-1 col-md-2">${courseVO.t_num}</div>
			              <div class="showCoures col-lg-2 col-md-2">${courseVO.cou_sdate}</div>
			              <div class="showCoures col-lg-2 col-md-2">${courseVO.cou_edate}</div>
			              <div class="showCoures col-lg-1 col-md-1">${courseVO.cou_max}</div>
			              <div class="showCoures col-lg-1 col-md-1">
							<p>${courseVO.cou_cost}   ＄</p>
						</div>
			              <div class="showCoures col-lg-1 col-md-1">
			                <c:choose>
							<c:when test="${courseVO.cou_status == 0 || courseVO.cou_status == 1}">
								<input type="hidden" name="cou_num" value="${courseVO.cou_num}">
								<input type="hidden" name="cou_status" value="${courseVO.cou_status}">
								<img src="<%=request.getContextPath()%>/back-end/images/${status[courseVO.cou_status]}" class="arrow">
							</c:when>
							<c:otherwise>
								<c:if test="${courseVO.cou_status == 2}">
									<div class="courseStatusColoring">課程進行中</div>	
								</c:if>	
								<c:if test="${courseVO.cou_status == 3}">
									<div class="courseStatusColorend">${status[courseVO.cou_status]}</div>	
								</c:if>					
							</c:otherwise>			              
			              </c:choose>  
			                <form METHOD="post" action="<%=request.getContextPath()%>/course/course.do" class="formStatus">
			                	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			            		<input type="hidden" name="cou_num" value="${courseVO.cou_num}">
			            		<input type="hidden" name="cou_status" value="${courseVO.cou_status}">			            		
			            		<input type="hidden" name="action" value="updateStatus">
			            		<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
			            	</form>
			                
			              </div> 
		          <div class="showCoures col-lg-1 col-md-1 magnifier1">
			                <img src="<%=request.getContextPath()%>/back-end/images/magnifier.png" class="magnifier">
			      </div>
			      <input type="hidden" value="${courseVO.cou_num}">
			    
			      
			              <!-- ======================== -->
			             
			    		  <!-- ======================== -->
		
			</div>
			    <div class="col-lg-12 paycount">
			      <div class="paycounttext">
			        <div class="col-lg-6"></div><c:set var="i" value="0"/>
			      	<div class="col-lg-2 spcount">已選課人數：　<%--${signCount.get(p)} --%><%--=signCount.get(p)--%>${coulSvc.getSignUpCount(courseVO.cou_num)}  </div> <%--=coulSvc.getSignUpCount(scount--) --%>
			      	<div class="col-lg-2 spcount">已繳費人數：　<%--${payCount.get(p)}--%><%--=payCount.get(p)--%>${coulSvc.getPayStatusCount(courseVO.cou_num)}   </div> <%--=coulSvc.getPayStatusCount(pcount--) --%>
							
			      	<div class="col-lg-2"> <%p++;%> 
			      		<img src="<%=request.getContextPath()%>/back-end/images/import.png" class="children">
			      		<input type="hidden" value="${courseVO.cou_num}">
			      	</div>  
			      </div>
			    </div>
	 </c:forEach>   
	 
			          <%@ include file="page/page2_ByCompositeQuery.file"%>
						
	</div>
									
					<!-- =============================== -->
				</div>
				
			
			
		
	
	
<%
			if ("failInsert".equals(insertlight)) {
		%>
			<script type="text/javascript">
			function init(){
				document.getElementById("insertdisplay").style="display:block"
			}
		<%System.out.println(insertlight);%>
		window.onload=init;
			</script>
	<%
		}
	%>
	
	
	<%
				if ("failUpdate".equals(updatelight)) {
			%>
			<script type="text/javascript">
			function init(){
				document.getElementById("<%=courseVO.getCou_num()%>").style = "display:block"
				}
			<%System.out.println(updatelight);%>
				window.onload = init;
			</script>
	<%
		}
	%>


</body>


<script>

$(".arrow").click(function() {
	var arrow = $(this);
	var cou_status = $(this).prev().val();
	var statusVal = $(this).prev();
	var cou_num = $(this).prev().prev().val();
	
	if(cou_status == 1){
		
		
	
		swal({
	        title: "是否將課程下架？",
	        html: "確定後課程將下架",
	        type: "question", // type can be "success", "error", "warning", "info", "question"
	        showCancelButton: true,
	    		showCloseButton: true,
	    }).then(
	    	   function (result) {
	        if (result) {
	            swal("完成!", "已將課程下架", "success");
	            
	            $.ajax({
	       		 type: "POST",
	       		 url: "<%=request.getContextPath()%>/course/course.do",
	       		 data: {action : "updateStatus" ,"cou_status": cou_status , "cou_num":cou_num},
	       		 dataType: "json",
	       		 async: true, // true預設是非同步，也就是"不會等待"; false是同步，"會等待"
	       		 success: function (data){
	       			
	       			if(data.cou_status == 1) {
	       				arrow.attr("src","<%=request.getContextPath()%>/back-end/images/uparrow.png");
	       			} else if(data.cou_status == 0) {
	       				arrow.attr("src","<%=request.getContextPath()%>/back-end/images/downarrow.png");
	       			}
	       			statusVal.val(data.cou_status);
	       			
	       	     },
	              error: function(){alert("AJAX-class發生錯誤囉!")}
	          })
	            
	            
	        }
	    }, function(dismiss) { // dismiss can be "cancel" | "overlay" | "esc" | "cancel" | "timer"
	    		swal("取消", "課程未更動", "error");
	    }).catch(swal.noop);
 	}
	
	
	
	if(cou_status == 0){
		
		
		
		swal({
	        title: "是否將課程上架？",
	        html: "確定後課程將上架",
	        type: "question", // type can be "success", "error", "warning", "info", "question"
	        showCancelButton: true,
	    		showCloseButton: true,
	    }).then(
	    	   function (result) {
	        if (result) {
	            swal("完成!", "已將課程上架", "success");
	            
	            $.ajax({
	       		 type: "POST",
	       		 url: "<%=request.getContextPath()%>/course/course.do",
	       		 data: {action : "updateStatus" ,"cou_status": cou_status , "cou_num":cou_num},
	       		 dataType: "json",
	       		 async: true, // true預設是非同步，也就是"不會等待"; false是同步，"會等待"
	       		 success: function (data){
	       			
	       			if(data.cou_status == 1) {
	       				arrow.attr("src","<%=request.getContextPath()%>/back-end/images/uparrow.png");
	       			} else if(data.cou_status == 0) {
	       				arrow.attr("src","<%=request.getContextPath()%>/back-end/images/downarrow.png");
	       			}
	       			statusVal.val(data.cou_status);
	       			
	       	     },
	              error: function(){alert("AJAX-class發生錯誤囉!")}
	          })
	            
	            
	        }
	    }, function(dismiss) { // dismiss can be "cancel" | "overlay" | "esc" | "cancel" | "timer"
	    		swal("取消", "課程未更動", "error");
	    }).catch(swal.noop);
 	}	
	
	
});

</script>


<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/course/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/course/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/course/datetimepicker/jquery.datetimepicker.full.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
</html>