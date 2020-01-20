<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UPDATE Course</title>
</head>
<body>
       
              <!-- 課程資訊燈箱 -->

            <div class="updateCourse col-lg-10 container">
                <div class="lightbox">

                    <div class="row rowbox">

                        <div class="col-4">
                            <div id="courseName">課程名稱</div><div><input type="text" name="" id="" value="${courseVO.cou_name}"></div>
                            <div>已報名人數</div><div> </div>
                            <div>已繳費人數</div><div> </div>
                        </div>

                        <div class="col-lg-6">
                          <!-- <p>圖片</p> -->
                          <div>
                            <input type="file" name="cou_pic" id="updatePic" accept="image/gif, image/jpeg, image/png">
                            <input type="hidden" name="gogo">
                          </div>
                         <!---->   <img src="#" id="displayUpdatePic"> 
                        </div>
                   </div>

                   <hr>

                   <div class="row rowbox courseText">

                      <div class="col-6">
                        <div>課程介紹</div>
                         <div><textarea name="">${courseVO.cou_introd}</textarea></div> 
                      </div>        
                  </div>
                    
                      <hr>
                      
                    <div class="row rowbox">
                      
                      <div class="col-lg-4">
                        <div>授課教師</div><div><input type="text" name="" id="" value="${courseVO.t_num}"></div></div>

                      <div class="col-lg-4">
                        <div>開課日期</div><div><input type="date" name="" id="" value="${courseVO.cou_sdate}"></div></div>

                      <div class="col-lg-3">
                        <div>結束日期</div><div><input type="date" name="" id="" value="${courseVO.cou_edate}"></div></div>

                    </div>

                      <hr>

                    <div class="row rowbox">

                      <div class="col-lg-4">
                        <div>開課人數</div><div><input type="text" name="" id="" value=""></div></div>
                      <div class="col-lg-4">
                        <div>人數上限</div><div><input type="text" name="" id="" value="${courseVO.cou_max}"></div></div>
                      <div class="col-lg-3">
                        <div>課程費用</div><div><input type="text" name="" id="" value="${courseVO.cou_cost}"></div></div>
                   
                    </div>  

                    <hr>
 
                   


                    <div align="center">
                    <input class="btn courseLightBtn" type="button" id="updateConfirm"  value="修改">
                    <input class="btn courseLightBtn" type="button" id="updateCourseCancel" value="取消">
          
                  </div>
                </div>
              </div>
		
	

</body>
</html>