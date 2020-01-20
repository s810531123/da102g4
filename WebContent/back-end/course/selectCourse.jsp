<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BackCourseSelectOne</title>
</head>
<body>
	<!-- ~~@~~ -->
		<jsp:include page="BackNav.jsp" flush="true" />
		
		<div class="col-lg-12 col-md-12 contentbox" >
		
			<jsp:include page="BackManagement.jsp" flush="true" />
			
				<div class="col-lg-10 col-md-10 rightContent" >
					
					<div class="row">
            			<div class="col-lg-10 cb course"><span class="lightgreen">美</span><span class="lightblue">心</span><span class="pink">幼</span><span class="gold">兒</span><span class="red">園</span> <span class="lightgreen">課</span><span class="gold">程</span><span class="pink">資</span><span class="lightblue">訊</span></div>
            			<div><img src="<%=request.getContextPath()%>/images/courseicon.png" class="logo" id="addCourse"></div>

            		</div>
					<!-- =============================== -->
					
					
					
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤:</font>
							<ul>
							    <c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						
						<a href='listAllCourse.jsp' class="listAllCourse">查詢全部課程</a>
						    
						 <jsp:useBean id="teaSvc" scope="page" class="com.teacher.model.TeacherService" />
					
						 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/course.do" >
						 <div class="col-lg-10 row">
						 <div class="col-lg-3">
						    
						        <b>教師姓名:</b>
						       <select size="1" name="t_num">
						       <option value="">全部教師
         						<c:forEach var="teacherVO" items="${teaSvc.all}" > 
          							<option value="${teacherVO.t_num}">${teacherVO.t_name}
        						 </c:forEach>   
       							</select>				
						  </div>						   
						  	<div class="col-lg-3">		    
						       <b>課程狀態:</b>
						       <select size="1" name="cou_status">
						       	  <option value="">上架+下架				         
						          <option value="0">下架中
						          <option value="1">上架中				          
						       </select>			   
						  </div>				  
						  <div class="col-lg-3">		  
						       <b>課程名稱:</b>
						       <input type="text" name="cou_name" >
						       <input type="hidden" name="action" value="listCourse_ByCompositeQuery">
						       
						  </div>   
						  <input type="submit" value="查詢" class="courseLightBtn btn">
						  </div>
						</form>
						
					<div class="outer">	
					
					</div>
					
					
					<!-- =============================== -->
				</div>
				
			<jsp:include page="BackFooter.jsp" flush="true" />
			
		</div>
		
	


 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>