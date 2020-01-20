<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.student.model.*"%>
<%@ page import="com.courselist.model.*"%>
<%@ page import="java.util.*"%>

<%
  	CourseVO couVO = (CourseVO) request.getAttribute("courseVO");
	StudentVO stVO = new StudentVO();
	
// 	CourseListService coulSvc = new CourseListService();
	
%>
<%
    CourseService couSvc = new CourseService();
    List<CourseVO> list = couSvc.getAll();
    pageContext.setAttribute("list",list);
    
    
//     CourseListService coulSvc = new CourseListService();
	  
    HashMap color = new HashMap();
	color.put(0,"primary");
	color.put(1,"secondary");
	color.put(2,"tertiary");
	color.put(3,"quarternary");
	
	HashMap bgColor = new HashMap();
	bgColor.put(0, "lavenderblush");	
	bgColor.put(1, "seashell");
	bgColor.put(2, "azure");
	bgColor.put(3, "lightyellow");			
	bgColor.put(4, "cornsilk");	
	bgColor.put(5, "floralwhite");	
	bgColor.put(6, "honeydew");	
	
				 
%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Kiddos - Free Bootstrap 4 Template by Colorlib</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">




    
    <link href="https://fonts.googleapis.com/css?family=Work+Sans:100,200,300,400,500,600,700,800,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Fredericka+the+Great" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/animate.css">
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/magnific-popup.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/aos.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/ionicons.min.css">
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/flaticon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/icomoon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/course/css/frontCourse.css">

<!-- sweet alert .css & .js cdn -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>

    
    <style type="text/css">
    
    .bg-light{
    	background:red;
    	
    }
    
    
    #YY{
    top: 60px;
    display: inline none;
    background-color: gold;
    }
    </style>
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/icomoon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/index_1.css">
    
  </head>
  <body onload="connect();" onunload="disconnect();">
 

  

    <%@ include file="/front-end/Nav"%>

    <%@ include file="/front-end/Top"%>
    <!-- END nav -->
  
   


      <!-- ======================================================================================= -->
<jsp:useBean id="coulSvc" class="com.courselist.model.CourseListService"/>
<jsp:useBean id="teaSvc" class="com.teacher.model.TeacherService"/>
<% int courseColor=0; int divColor = 0;%>
<c:forEach var="courseVO" items="${list}">
		<c:if test="${courseVO.cou_status == 1}">
		
		
		<%-- ===========================課程資訊=============================== --%>


		<div class="updateAddCourse col-lg-12 container" id="front${courseVO.cou_num}">
            
                <div class="lightbox col-lg-10" style="background:<%=bgColor.get(divColor++) %> !important">
                <% if(courseColor == 4){courseColor = 0;} if(divColor == 7){divColor = 0;} %>
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/courselist/courselist.do" enctype="multipart/form-data">
                 
                   <!-- ======= -->
					
					<div class="row rowbox">
						
                        <div class="col-lg-6 courseName">
                            <div id="courseName">課程名稱：　${courseVO.cou_name}</div>
                            
                            <input type="hidden" name="cou_num" value="${courseVO.cou_num}">
                        </div>

                        
                        
                         <div class="col-lg-6">
	                        <div>授課教師：　${teaSvc.getOneTeacher(courseVO.t_num).t_name}</div></div>
                   </div>
                   
                   <div class="row">
                   
                   		<div class="col-lg-6">
                          <!-- <p>圖片</p> -->
                         
                          <img src="<%=request.getContextPath()%>/img/img.do?cou_num=${courseVO.cou_num}" class="displayUpdatePic" id="upPic${courseVO.cou_num}">   
                        </div>
                        
                        <div class="col-lg-6">
                          <!-- <p>圖片</p> -->
                         
                          <img src="<%=request.getContextPath()%>/getT_photo/getT_photo.do?t_num=${courseVO.t_num}" class="displayUpdatePic" id="upPic${courseVO.cou_num}">   
                        </div>
                   	
                   </div>
                   
                   <!-- ======= -->

                   <hr>

                   <div class="row rowbox courseText">

                      <div class="col-6">
                        <div>課程介紹 ：　</div>
                        <div class="introd">${courseVO.cou_introd}</div> 
                      </div>   
                           
                   </div>
                    
                      <hr>
                      
                    <div class="row rowbox">                   
	                     
	                      <div class="col-lg-3">
	                        <div>開課日期</div>${courseVO.cou_sdate}</div>
	                      <div class="col-lg-3">
	                        <div>結束日期</div>${courseVO.cou_edate}</div>
	                      <div class="col-lg-3">
	                        <div>已報名人數</div>${coulSvc.getSignUpCount(courseVO.cou_num)} 人</div>
                    </div>

                      <hr>
                    <div class="row rowbox">
	                      <div class="col-lg-3">
	                        <div>開課人數</div><div>${courseVO.cou_min} 人</div>
	                      </div>
	                      
	                      <div class="col-lg-3">
	                        <div>人數上限</div><div>${courseVO.cou_max} 人</div>
	                      </div>
	                      
	                      <div class="col-lg-3">
	                        <div>課程費用</div><div>${courseVO.cou_cost} ＄</div>
	                      </div>          
                    </div>  
                    <hr>
							<div align="center">
							<c:set var="i" value="0"/>
							<c:forEach var="coul" items="${coulSvc.getCou_NumBySt_Num(stp.st_num)}">
								<c:if test="${coul.cou_num == courseVO.cou_num && (coul.pay_status != 2)}">
									<c:set var="i" value="1"/>
									<input class="btn courseLightBtn" type=submit id="updateConfirm"  value="已選課" disabled style="color:red;">
								</c:if>
								<c:if test="${(coul.cou_num == courseVO.cou_num) && (coul.pay_status == 2)}">
									<c:set var="i" value="1"/>
									<input type="hidden" name="action" value="updateStatus">
									<input type="hidden" name="AddOrUpdateStatus" value="1">
									<input class="btn courseLightBtn signCourse" type="button" id="updateConfirm"  value="確認選課">
								</c:if>
							</c:forEach>
		                    <c:if test="${i == 0}">
		                    	<input class="btn courseLightBtn signCourse" type="button" id="updateConfirm"  value="確認選課">
		                    	<input type="hidden" name="AddOrUpdateStatus" value="0">
		                    	<input type="hidden" name="action" value="insert">
		                    </c:if>
		                    <input class="btn courseLightBtn CourseCancel" type="button" value="取消">
		                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		                    <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
		                    <input type="hidden" name="cou_num" value="${courseVO.cou_num}">
		                    <input type="hidden" name="st_num" value="${stp.st_num}">
		                    <input type="hidden" name="action" value="insert">
		                    
	                  	</div>
	                  	<input type="hidden" name="cou_num" value="${courseVO.cou_num}">
	                  </form>
                </div>
              </div> 
</c:if>
</c:forEach>

		<%-- ===========================課程資訊=============================== --%>
			



      <!-- ======================================================================================= -->

    <section class="ftco-section">
     
      <div class="container">
        <div class="row justify-content-center mb-5 pb-2">
          <div class="col-md-8 text-center heading-section ftco-animate">
            <h2 class="mb-4"><span>我們</span> 的課程</h2>
            <p id="testWS">快來刷卡花大錢上課 讓我們能夠賺大錢整天吃飽睡睡飽吃快來刷卡睡睡飽吃快來刷卡花大錢上課 讓我們能夠賺大錢整天吃飽睡睡賺大錢整天吃飽睡睡飽吃</p>
          </div>
          <div>
          	<img src="<%=request.getContextPath()%>/front-end/course/images/open-book.png" class="logo" id="ownCourse">
          	<form action="<%=request.getContextPath()%>/courselist/courselist.do" METHOD="post">
          		<input type="hidden" name="action" value="selectCourse">
          	
          	</form>
          </div>
        </div>

        <div class="row col-lg-12">
		<% courseColor=0; divColor = 0;%>
	<c:forEach var="courseVO" items="${list}">
		<c:if test="${courseVO.cou_status == 1}">
		
		
		<%-- ===========================課程資訊=============================== --%>
		<%-- ===========================課程資訊=============================== --%>
			
          <div class="col-md-6 col-lg-3 ftco-animate">
            <div class="pricing-entry bg-light pb-4 text-center" style="background:<%=bgColor.get(divColor++) %> !important">
              <div>
                <h3 class="mb-3">${courseVO.cou_name}</h3>
                <p><span class="price">$ ${courseVO.cou_cost}</span> <span class="per">/ </span></p>
              </div>
              <img src="<%=request.getContextPath()%>/img/img.do?cou_num=${courseVO.cou_num}" class="img" style="width:300px;height:150px;">
<%--               <div class="img" style="background-image: url(<%=request.getContextPath()%>/img/img.do?cou_num=${courseVO.cou_num});width:300px;height:200px;"></div> --%>
              <div class="px-4">
                <p>${courseVO.cou_introd}</p>
              </div>
              <p class="button text-center">
              		<a href="#" class="btn btn-<%=color.get(courseColor++)%> px-4 py-3 courseList">Take A Course</a>
              		<input type="hidden" name="cou_num" value="${courseVO.cou_num}">
              </p>
            </div>			<% if(courseColor == 4){courseColor = 0;} if(divColor == 7){divColor = 0;} %>
          </div>

         </c:if> 									
        </c:forEach>

        </div> 
      </div>
    </section>
    
      <!-- ======================================================================================= -->
   

<!--     <section class="ftco-section"> -->
<!--       <div class="row container">　 -->
<!--         <div class="col-md-5">　 -->
<!--           <div class="outer">  　 -->

<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </section> -->



    
    
    
    <%@ include file="/front-end/Footer"%>
    <script src="<%=request.getContextPath()%>/front-end/course/js/frontCourse.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/front-end/course/js/jquery.growl.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath()%>/front-end/course/css/jquery.growl.css" rel="stylesheet" type="text/css" />
 
 
 	<script>
 	
 	
 	var MyPoint = "/CourseWS/Hao";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;
	console.log(endPointURL)
	var webSocket;
 	
	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			
		};

		
		
		/*WS*/
		webSocket.onmessage = function(event) {
			console.log(event)
			var jsonObj = JSON.parse(event.data);
			console.log(jsonObj.action)
			if(jsonObj.action == 'newCourse'){
<%-- 				$("#testWS").html("<a style='color:red;font-width:900;font-size:25px;' href='http://localhost:8081<%=request.getContextPath()%>/front-end/course/index_1.jsp'>有新課程囉~ 點擊瀏覽新課程</a>"); --%>
				var o = "<a  style='font-width:900;font-size:20px;' href='<%=request.getContextPath()%>/front-end/course/index_1.jsp'>有新課程囉~ 點擊瀏覽新課程</a>";
				$.growl.notice({ message: o });
			}
				
// 			console.log(jsonObj.URL)
		};

		webSocket.onclose = function(event) {
			
		};
	}
	
	function disconnect() {
		webSocket.close();		
	}
	/*WS*/
	
// 	$(".signCourse").click(function() {
// 		var cou_num = $(this).parent().next().val();
// 		var jsonObj = {
// 					"action" : "signCourse",	
// 					"cou_num": cou_num,
// 			};
// 			webSocket.send(JSON.stringify(jsonObj));
// 	})
	
	
 	
 	$(".signCourse").click(function() {
     	var b = $(this).parent().parent();
     	var cou_num = $(this).parent().next().val();
     	swal({
            title: "確定選課？",
            html: "按下確定將選課",
            type: "question", // type can be "success", "error", "warning", "info", "question"
            showCancelButton: true,
        		showCloseButton: true,
        }).then(
        	   function (result) {
            if (result) {
                swal("完成!", "已選課完成    請查看信箱", "success").then(function(){
                	b.submit();
                	var jsonObj = {
        					"action" : "signCourse",	
        		 			"cou_num": cou_num,
        			};
                	setTimeout(function(){webSocket.send(JSON.stringify(jsonObj));},500);
//         			webSocket.send(JSON.stringify(jsonObj));
                });    
            }
        }, function(dismiss) { // dismiss can be "cancel" | "overlay" | "esc" | "cancel" | "timer"
        		swal("取消", "課程未選取", "error"); 
        }).catch(swal.noop);
 	 });
 	
 	</script>
 
 
    </body>
  </html>