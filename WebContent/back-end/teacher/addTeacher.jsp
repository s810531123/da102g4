<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.teacher.model.*"%>
<%@ page import="java.sql.Date" %>


<% 
	TeacherVO teacherVO = (TeacherVO) request.getAttribute("teacherVO");
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
	String key = (String) session.getAttribute("key");
%>


<html>
<head>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	
	<script>
		$(document).ready(function(){
			$("tr.addOne:even").css("background","rgba(195,195,195,0.8)");
			$("tr.addOne:odd").css("background","rgba(220,220,220,0.9)");
		});
	</script>

	<title>教職員資料新增 - addTeacher.jsp</title>
	<%@ include file="/back-end/includePage/Head"%>
	<script>
		function openFile(event){
			var input = event.target; //取得上傳檔案
			var reader = new FileReader(); //建立FileReader物件
	
			reader.readAsDataURL(input.files[0]); //以.readAsDataURL將上傳檔案轉換為base64字串
	
			reader.onload = function(){ //FileReader取得上傳檔案後執行以下內容
			var dataURL = reader.result; //設定變數dataURL為上傳圖檔的base64字串
			$('#output').attr('src', dataURL).show(); //將img的src設定為dataURL並顯示
			};
		}
	</script>


	<style>
		#table-1 {
			width:450;
			margin-left:35%;
			color:black;
		}
		
		#btn1{
			margin-left:40%;
		}
		
		
		.addTitle{
			text-align:right;
		}
		
		.error {
			margin-left:30%;
			text-align:center;
		}
		
		.errormsg{
			text-align:center;
		}
		
		#hi{
			position:absolute;
			z-index:10;
			margin-left:78%
		}
		
		#back {
		    margin-left: -13%;
		    margin-right: 7%;
		}		
	</style>

</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>
	

	<%@ include file="/back-end/includePage/LeftSide"%>


	
	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="own">
			<h3><a href="<%= request.getContextPath()%>/back-end/teacher/listAllTeacher.jsp"><img src="<%= request.getContextPath()%>/back-end/teacher/images/back.jpg" width="40" height="40" border="0" id="back"></a>資料新增</h3>
			
			<FORM action="<%= request.getContextPath()%>/teacher/teacher.do" method="post" enctype="multipart/form-data" name="form1">

				<table id="table-1">
					<tr class="addOne">
						<td class="addTitle">
							相片:
						</td>
						<td>
							<input type="file" name="t_photo" onchange="openFile(event)">
						</td>
					</tr>
					<tr class="addOne">
						<td class="addTitle">
							預覽:
						</td>
						<td>
							<img id="output" height="200" style="display:none">
						</td>
					</tr>
					<tr class="addOne">
						<td class="addTitle">
							教職員姓名:
						</td>
						<td>
							<input type="TEXT" name="t_name" size="30" 
							 value="<%= (teacherVO==null)? "" : teacherVO.getT_name()%>" />
						</td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_name}</font>
							</td>
						</c:if>
					</tr>
					<tr class="addOne">
						<td class="addTitle">
							性別:
						</td>
						<td>
							<input type="radio" name="t_gender" value="男" checked="true">男
							<input type="radio" name="t_gender" value="女">女
						</td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_gender}</font>
							</td>
						</c:if>
					</tr>
					<tr class="addOne">
						<td class="addTitle">
							身份證字號:
						</td>
						<td>
							<input type="TEXT" name="t_id" size="30" 
							 value="<%= (teacherVO==null)? "" : teacherVO.getT_id()%>" />
						</td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_id}</font>
							</td>
						</c:if>					
					</tr>
					<tr class="addOne">
						<td class="addTitle">
							E-mail:
						</td>
						<td>
							<input type="TEXT" name="t_email" size="30" 
							 value="<%= (teacherVO==null)? "" : teacherVO.getT_email()%>" />
						</td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_email}</font>
							</td>
						</c:if>					
					</tr>
					<tr class="addOne">
						<td class="addTitle">
							戶籍地址:
						</td>
						<td>
							<input type="TEXT" name="t_r_address" size="30" 
							 value="<%= (teacherVO==null)? "" : teacherVO.getT_r_address()%>" />
						</td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_r_address}</font>
							</td>
						</c:if>					
					</tr>
					<tr class="addOne">
						<td class="addTitle">
							通訊地址:
						</td>
						<td>
							<input type="TEXT" name="t_address" size="30" 
							 value="<%= (teacherVO==null)? "" : teacherVO.getT_address()%>" />
						</td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_address}</font>
							</td>
						</c:if>					
					</tr>
					<tr class="addOne">
						<td class="addTitle">
							職稱:
						</td>
						<td>
							<input type="radio" name="t_job" value="園長" readonly>園長
							<input type="radio" name="t_job" value="班導師" readonly>班導師
							<input type="radio" name="t_job" value="老師" readonly checked>老師
						</td>					
					</tr>
					<tr class="addOne">
						<td class="addTitle">
							生日:
						</td>
						<td>
							<input type="date" name="t_birthday" value="<%= (teacherVO==null)? new Date(System.currentTimeMillis()) : teacherVO.getT_birthday()%>" max="<%= new Date(System.currentTimeMillis())%>">
						</td>
					</tr>
					<tr class="addOne">
						<td class="addTitle">
							狀態:
						</td>
						<td>
							<input type="TEXT" name="t_status" size="30" 
							 value="<%= (teacherVO==null)? "1" : teacherVO.getT_status()%>" />
						</td>
					</tr>
					<tr>
						<c:if test="${not empty errorMsgs}">
							<td colspan="2" class="errormsg">
								<font style="color:red">${errorMsgs.t_status}</font>
							</td>
						</c:if>
					</tr>
					<tr>
						<td id="submit" colspan="2">
							<input type="hidden" name="action" value="insert">
							<input type="hidden" name="key" value="${key}">
							<input type="submit" value="送出新增" id="btn1">	
						</td>
					</tr>
				</table>
			</FORM>
		</div>
	</div>

</body>
<%@ include file="/back-end/includePage/BootStrap"%>
<% 
  Date t_birthday = null;
  try {
	  t_birthday = teacherVO.getT_birthday();
   } catch (Exception e) {
	   t_birthday = new Date(System.currentTimeMillis());
   }
%>


</html>

