<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course List One</title>
</head>
<body>
		
		<jsp:include page="BackNav.jsp" flush="true" />
		
		<div class="col-lg-12 col-md-12 contentbox" >
		
			<jsp:include page="BackManagement.jsp" flush="true" />
			
				<div class="col-lg-10 col-md-10 rightContent" >
					
					<div class="row">
            			<div class="col-lg-10 cb course"><span class="lightgreen">美</span><span class="lightblue">心</span><span class="pink">幼</span><span class="gold">兒</span><span class="red">園</span> <span class="lightgreen">課</span><span class="gold">程</span><span class="pink">資</span><span class="lightblue">訊</span></div>
            			<div><img src="images/courseicon.png" class="logo" id="addCourse"></div>

            		</div>
					<!-- =============================== -->
					
					
					<div class="outer">
					
					
					<div   class="col-lg-12 col-md-10 CourseTitle">
			              <div class="courseTitle col-lg-2 col-md-2">課程名稱</div>
			              <div class="courseTitle col-lg-1 col-md-2">授課教師</div>
			              <div class="courseTitle col-lg-2 col-md-2">開課日期</div>
			              <div class="courseTitle col-lg-2 col-md-2">結束日期</div>
			              <div class="courseTitle col-lg-1 col-md-1">人數上限</div>
			              <div class="courseTitle col-lg-1 col-md-1">課程費用</div>
			              <div class="courseTitle col-lg-1 col-md-1">課程狀態</div>
			              <div class="courseTitle col-lg-1 col-md-1"></div>
			            </div>
			           
		<jsp:useBean id="teaSvc" class="com.teacher.model.TeacherService"/>
			            <div   class="col-lg-12 col-md-10 myCourse">
			            
			              <div class="showCoures col-lg-2 col-md-2">${teaSvc.getOneTeacher("T003")}</div>
			              <div class="showCoures col-lg-1 col-md-2">${courseVO.t_num}</div>
			              <div class="showCoures col-lg-2 col-md-2">${courseVO.cou_sdate}</div>
			              <div class="showCoures col-lg-2 col-md-2">${courseVO.cou_edate}</div>
			              <div class="showCoures col-lg-1 col-md-1">${courseVO.cou_max}</div>
			              <div class="showCoures col-lg-1 col-md-1">${courseVO.cou_cost}</div>
			              <div class="showCoures col-lg-1 col-md-1">
			                <img src="images/downarrow.png" class="arrow">
			              </div> 
			              <div class="showCoures col-lg-1 col-md-1">
			                <img src="images/magnifier.png" class="magnifier">
			              </div>
			            </div>
					
						
					</div>
					
					
					<!-- =============================== -->
				</div>
				
			<jsp:include page="BackFooter.jsp" flush="true" />
			
		</div>
		
	


 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>