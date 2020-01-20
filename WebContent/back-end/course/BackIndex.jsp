<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BackIndex</title>
</head>
<body>
	
		<jsp:include page="BackNav.jsp" flush="true" />
		
		<div class="col-lg-12 col-md-12 contentbox" >
		
			<jsp:include page="BackManagement.jsp" flush="true" />
			
				<div class="col-lg-10 col-md-10 rightContent" >
					<jsp:include page="updateCourse.jsp" flush="true" />
					
					<div class="row">
            			<div class="col-lg-10 cb course"><span class="lightgreen">美</span><span class="lightblue">心</span><span class="pink">幼</span><span class="gold">兒</span><span class="red">園</span> <span class="lightgreen">課</span><span class="gold">程</span><span class="pink">資</span><span class="lightblue">訊</span></div>
            			<div><img src="images/courseicon.png" class="logo" id="addCourse"></div>

            		</div>
					<!-- =============================== -->
					
					
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