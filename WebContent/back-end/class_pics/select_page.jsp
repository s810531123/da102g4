<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_pictures.model.*"%>
<%@ page import="com._class.model.*"%>
<% 
	Class_picturesVO pic = new Class_picturesVO();
	pic.setCs_num("C001");
	session.setAttribute("class_", pic);
	
	Class_picturesService class_picturesSvc = new Class_picturesService();
	List<Class_picturesVO> list = class_picturesSvc.getAll();
	
	
	String err = (String) request.getAttribute("errInsert");
%>

<%
	Class_picturesVO class_picturesVO = (Class_picturesVO)request.getAttribute("class_picturesVO");
%>

<html>
<head>
<script src="<%=request.getContextPath()%>/back-end/vendors/jquery/jquery-1.12.4.min.js"></script>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/class_pics/datetimepicker/jquery.datetimepicker.css" />
<title>班級相簿管理-1</title>
<%@ include file="/back-end/includePage/Head"%>
</head>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/class_pics/css/select_page.css">

<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<!-- 自己的畫面 -->
	<div class="col-lg-10 col-md-10 cb rightContent">
	<%@ include file="/back-end/class_pics/header_text"%>
		
		
<!-- 		進入新增燈箱			 -->
		<div class="select_do"><img src="<%=request.getContextPath()%>/back-end/class_pics/photo/icon_add_80.png" id="add_page"><br>上傳相片</div>
		
<!-- 		進入查詢頁面 -->
		<a href="<%=request.getContextPath()%>/back-end/class_pics/search_page.jsp">
		<div class="select_do"><img src="<%=request.getContextPath()%>/back-end/class_pics/photo/icon_search_80.png" id="search_page"><br>查詢照片</div>
		</a>
		
<!-- 		進入修改頁面 -->
		<a href="<%=request.getContextPath()%>/back-end/class_pics/update_input_page.jsp">
		<div class="select_do"><img src="<%=request.getContextPath()%>/back-end/class_pics/photo/icon_edit_1.png" id="search_page"><br>進入修改照片區</div>
		</a>
		
		
		
<!-- 刪除的燈箱		 -->
	<div class="lightbox_delete" id="lightbox2">
	<form method="post" action="<%= request.getContextPath()%>/class_pics/class_pics.do" class="light_delete">
		<br><b class="delete_text">您是否要刪除此照片?
		<div align="center">
			<input type="submit" value="刪除" id="delete">
			<input type="hidden" value="0" name="cs_pic_num">
			<input type="button" value="取消" class="close2">
			<input type="hidden" name="action" value="delete"> 
		</div>
	</form>	
	</div>
		
	
	
	
<!-- 新增的燈箱 -->
		<div class="lightbox_add" id="lightbox">
		<form method="post" action="<%= request.getContextPath()%>/class_pics/class_pics.do" enctype="multipart/form-data">
		<jsp:useBean id="classSvc" scope="page"
					class="com._class.model.ClassService" />
			<br>
			<br>			
		  <table class="table"><b class="title">班級相片新增上傳</b>		
			<tr>
				<td><b class="td_b">班級</b></td>
				<td><select name="cs_num">
				<c:forEach var="classVO" items="${classSvc.all}">
					<option value="${classVO.cs_num}"
					${(class_picturesVO.cs_num == classVO.cs_num)? 'selected':'' }>${classVO.cs_name}
				</c:forEach>	
				</select></td>
			</tr>
			
			<tr>
				<td><b class="td_b">上傳時間</b></td>
				<td><input type="text" name="ul_date" id="ul_date"></td>
			</tr>
			
			<tr>
				<td><b class="td_b">照片名稱</b></td>
				<td><input type="text" name="pic_cs" id="pic_cs"></td>
			</tr>
			<tr>
				<th><input type="file" name="pic" id="addPic"accept="image/gif, image/jpeg, image/png, image/jpg"></th>
				<th><img src="#" id="displayAddPic"></th>
			</tr>
		  </table>
		  
		  <c:if test="${(not empty errorMsgs)}">
					<font style="color: red">請修正以下欄位:</font>

					<c:forEach var="message" items="${errorMsgs}">
						<p style="color: red" class="message">${message}</p>
					</c:forEach>

				</c:if>
			<div align="center">
				<input type="submit" value="確認上傳" id="btn">
				<input type="button" value="取消" class="close" id="btnc">
				<input type="hidden" name="action" value="insert">
			</div>
		</form>
		</div>
	<% 	
		if("errInsert".equals(err)){ System.out.println("errInsert".equals(err)); System.out.println("ERRRRRR : " + err);%>
			<script type="text/javascript">
                        function init(){       
                                	document.getElementById("lightbox").style="display:block"
                        }
         
                window.onload=init;
                        </script>
	<% 	}  %>
		

			
			
			
<!-- 全部照片展示 -->
		<div class="into">
			<c:forEach var="class_picturesVO" items="<%=list%>">
			<c:if test="${class_.cs_num == class_picturesVO.cs_num}">
			
				<img src="<%= request.getContextPath()%>/get_pics/get_pics.do?cs_pic_num=${class_picturesVO.cs_pic_num}" class="in_pic" value="${class_picturesVO.cs_pic_num}">

					
				<input type="hidden"  name="cs_pic_num" value="${class_picturesVO.cs_pic_num}">
				
				<input type="hidden"  name="cs_pic_num" value="${class_picturesVO.cs_num}">
				 	
			</c:if>
			</c:forEach>
			<c:if test=""></c:if>
		</div>
		
	
	
	
	
	</div>




	
			

	
	<%@ include file="/back-end/includePage/BootStrap"%>

	
	<script src="<%=request.getContextPath()%>/back-end/class_pics/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/class_pics/datetimepicker/datetimepicker/jquery.js"></script>
	
	
	
	
	
</body>
<script>

//  關燈箱(新增)
	$('.close').click(function() {
		$('div.lightbox_add').hide(1000);
	})
// 	開燈箱(新增)
	$('#add_page').click(function(){
		$('div.lightbox_add').show(650);
	});		
// 	月曆(新增照片的時間)
	$.datetimepicker.setLocale('zh'); 
    $('#ul_date').datetimepicker({
       theme: '',         
       timepicker: true,   
       step: 1,            
       format: 'Y-m-d H:i',
       value: new Date(),
    });

    
// 關燈箱(刪除)
	$('.close2').click(function() {
		$('div.lightbox_delete').hide(1000);
		$('img.in_pic').css({"border":"4px dashed pink","border-radius":"0px","opacity":"0.65"});
	})
// 開燈箱(刪除)
 	$('img.in_pic').dblclick(function() {
		$('div.lightbox_delete').show(650);
		$(this).css({"border":"10px solid","border-radius":"10px","opacity":"1"});
	})
// 	取得刪除的物件
	$(".in_pic").dblclick(function() {
		$("#lightbox2").show(650);
		var pic_num = $(this).next().val();
		$("#delete").next().val(pic_num);
	})
	

// // 關燈箱(觀看)
// 	$('.close3').click(function() {
// 		$('div.lightbox_see').hide(1000);
// 		$('img.in_pic').css({"border":"4px dashed pink","border-radius":"0px","opacity":"0.65"});
// 	});
// // 開燈箱(觀看)
// 	$('img.in_pic').click(function() {
// 		$('div.lightbox_see').show(650);
// 		$(this).css({"border":"10px solid","border-radius":"10px","opacity":"1"});
// 	})
	
// // 	取得觀看的物件
// $(".in_pic").click(function() {
// 		$("#lightbox3").show(650);
// 		var pic_num2 = $(this).next().val();
// 		$(this).next().val(pic_num2);
// 	})
	
	
	
// 	刪照片、關閉觀看燈箱、SHOW出取消按鈕
	$('input#delete').click(function() {
		$('div.lightbox_see').hide();
		$('input.close3').show();
	})
// 取消刪照片、關閉觀看燈箱、SHOW出取消按鈕
	$('input.close2').click(function() {
		$('div.lightbox_see').hide();
		$('input.close3').show();
	})
// 進入刪除燈箱、隱藏觀看燈箱的取消按鈕
	$('img.in_pic').dblclick(function() {
		$('input.close3').hide();
	})

	$("#addPic").change(function() {
		readURL(this);
	});
	
	function readURL(input){
        if(input.files && input.files[0]){
          var reader = new FileReader();
          reader.onload = function (e) {
             $("#displayAddPic").attr('src', e.target.result);
          }
          reader.readAsDataURL(input.files[0]);
        }
      }
	
</script>
</html>