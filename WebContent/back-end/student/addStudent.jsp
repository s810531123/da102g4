<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.student.model.*"%>
<%@ page import="com.guardian.model.*"%>
<%@ page import="java.sql.Date" %>
<%@ page import="com.teacher.model.*"%>
<%@ page import="com._class.model.*"%>
<%@ page import="java.util.*"%>

<% 
	StudentVO studentVO = (StudentVO) request.getAttribute("studentVO");
	GuardianVO guardianVO = (GuardianVO) request.getAttribute("guardianVO");
	ClassService classSvc = new ClassService();
	List<ClassVO> list = classSvc.getAll();
	pageContext.setAttribute("list", list);
	TeacherVO tp = (TeacherVO) session.getAttribute("tp");
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
	
	<title>學生資料新增 - addStudent.jsp</title>
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
			width:430px;
			position:inline-block;
			margin-left:35%;
			color:black;
			border:1px solid black;
		}
		
		tr td{
			border:1px solid black;
		}
		
		.addTitle{
			text-align:right;
		}
		
		#btn1{
			margin-left:-6%;
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
		
		#title {
		    margin-right: 7%;
		}
		
		#back {
		    margin-left: -7%;
		    margin-right: 6%;
		}
	</style>

</head>
<body>
	<%@ include file="/back-end/includePage/Nav"%>

	<%@ include file="/back-end/includePage/LeftSide"%>


	<div class="col-lg-10 col-md-10 cb rightContent">
		<div class="own">
			<h3><a href="<%= request.getContextPath()%>/back-end/guardian/addGuardian.jsp"><img src="<%= request.getContextPath()%>/back-end/student/images/back.jpg" width="40" height="40" border="0" id="back"></a><span id="title">資料新增</span></h3>


			<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/student/student.do" name="form1" enctype="multipart/form-data">
			<table id="table-1">
				<tr class="addOne">
					<td class="addTitle">
						相片:
					</td>
					<td>
						<input type="file" name="st_photo" onchange="openFile(event)">
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
						學生姓名:
					</td>
					<td>
						<input type="TEXT" name="st_name" size="30" 
						 value="<%= (studentVO==null)? "" : studentVO.getSt_name()%>" />
					</td>
				</tr>
				<c:if test="${not empty errorMsgs}">
					<tr>
						<td class="errormsg" colspan="2">
							<font style="color:red">${errorMsgs.st_name}</font>
						</td>
					</tr>
				</c:if>
				<tr class="addOne">
					<td class="addTitle">
						性別:
					</td>
					<td>
						<input type="radio" name="st_gender" value="男" checked="true">男
						<input type="radio" name="st_gender" value="女">女
					</td>
				</tr>
				<tr class="addOne">
					<td class="addTitle">
						學生身份證字號:
					</td>
					<td>
						<input type="TEXT" name="st_id" size="30" 
						 value="<%= (studentVO==null)? "" : studentVO.getSt_id()%>" />
					</td>
				</tr>
				<c:if test="${not empty errorMsgs}">
					<tr class="addOne">
						<td class="errormsg" colspan="2">
							<font style="color:red">${errorMsgs.st_id}</font>
						</td>
					</tr>
				</c:if>
				<tr class="addOne">
					<td class="addTitle">
						家長姓名:
					</td>
					<td>
						<input type="TEXT" name="gd_name" size="30" readonly value="<%= guardianVO.getGd_name()%>" />	 
					</td>
				</tr>
				
				<tr class="addOne">
					<td class="addTitle">
						家長身份證字號:
					</td>
					<td>
						<input type="TEXT" name="gd_id" size="30" readonly value="<%= guardianVO.getGd_id()%>" />	 
					</td>
				</tr>

				<tr class="addOne">
					<td class="addTitle">
						戶籍地址:
					</td>
					<td>
						<input type="TEXT" name="st_r_address" size="30" 
						 value="<%= (studentVO==null)? "" : studentVO.getSt_r_address()%>" />
					</td>
				</tr>
				<tr>
					<c:if test="${not empty errorMsgs}">
						<td class="errormsg" colspan="2">
							<font style="color:red">${errorMsgs.st_r_address}</font>
						</td>
					</c:if>
				</tr>
				<tr class="addOne">
					<td class="addTitle">
						通訊地址:
					</td>
					<td>
						<input type="TEXT" name="st_address" size="30" 
						 value="<%= (studentVO==null)? "" : studentVO.getSt_address()%>" />
					</td>
				</tr>
				<tr>
					<c:if test="${not empty errorMsgs}">
						<td class="errormsg" colspan="2">
							<font style="color:red">${errorMsgs.st_address}</font>
						</td>
					</c:if>
				</tr>
				<tr class="addOne">
					<td class="addTitle">
						班別:
					</td>
					<td>	 
						 <select name="cs_num">
						 	<option value="">請選擇
						 	<c:forEach var="classVO" items="${list}">
						 		<option value="${classVO.cs_num}">${classVO.cs_num}
						 	</c:forEach>
						 </select>
					</td>
				</tr>
				<tr>
					<c:if test="${not empty errorMsgs}">
						<td class="errormsg" colspan="2">
							<font style="color:red">${errorMsgs.cs_num}</font>
						</td>
					</c:if>
				</tr>
				<tr class="addOne">
					<td class="addTitle">
						生日:
					</td>
					<td>
						<input type="date" name="st_birthday" value="<%= (studentVO==null)? new Date(System.currentTimeMillis()) : studentVO.getSt_birthday()%>" max="<%= new Date(System.currentTimeMillis())%>">
					</td>
				</tr>
				<tr class="addOne">
					<td class="addTitle">
						狀態:
					</td>
					<td>
						<input type="TEXT" name="st_status" size="30" 
						 value="<%= (studentVO==null)? "0" : studentVO.getSt_status()%>" />
					</td>
				</tr>
				<tr>
					<c:if test="${not empty errorMsgs}">
						<td class="errormsg" colspan="2">
							<font style="color:red">${errorMsgs.st_status}</font>
						</td>
					</c:if>
				</tr>
			</table>
			<br>
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增" id="btn1"></FORM>
		</div>
	</div>
</body>
	<%@ include file="/back-end/includePage/BootStrap"%>
	<% 
	  Date St_birthday = null;
	  try {
		  St_birthday = studentVO.getSt_birthday();
	   } catch (Exception e) {
		   St_birthday = new Date(System.currentTimeMillis());
	   }
	%>
	
</html>

