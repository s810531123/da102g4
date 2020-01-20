<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>請假作業</title>
<%@ include file="/back-end/includePage/Head"%>
<!-- 自己的css -->
<link rel="stylesheet" href="./css/select.css">
<style type="text/css">
.star{
	margin-left: 15%;
	width:70%;
}
</style>
</head>
<body>
<%@ include file="/back-end/includePage/Nav"%>
	
		<%@ include file="/back-end/includePage/LeftSide"%>
	
	<div class="col-lg-10 col-md-10 cb rightContent ">
		<div class="color-manto">
			<%-- 錯誤表列 --%>
			<div class="error">
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<br>
			<h2 align="center" style="color:#20B2AA;font-family: Microsoft JhengHei;">教 職 員 假 單 功 能 列 表</h2>
			<img src="image/star.png" class="star">
			<div class="row">	
				<div style="margin-right: 5px">
					<div style="text-align: center; font-size: 30px; color: #880088 ;font-family: Microsoft JhengHei">新增請假單</div>
					<div class="card-body text-primary">
						<a href="addTeacherLeave.jsp">
							<img src="image/new.png" class="fixImg">
						</a>
						<form action="addTeacherLeave.jsp"></form>
					</div>
				</div>
				<!-- 查詢全部 -->
				<div>
					<div style="text-align: center; font-size: 30px; color: #880088 ;font-family: Microsoft JhengHei">查詢全部</div>
					<div class="card-body text-primary">					
 						<a href="listAllTeacherLeave.jsp">
 							<img src="image/search.png" class="fixImg">
 						</a>
					</div>
				</div>		
			</div>
		</div>
	</div>

	




<!-- 第一版 -->
<!-- 		<Form action="addTeacherLeave.jsp"> -->
<!-- 			<button class="btn btn-primary">GO</button> -->
<!-- 		</Form> -->
<!-- 		<FORM METHOD="post" ACTION="teacherLeave.do"> -->
<!-- 			<b>選擇假單編號(如1001)</b>  -->
<!-- 			<input type="text" name="tl_Id">  -->
<!-- 			<input type="hidden" name="action" value="getOne_For_Display">  -->
<!-- 			<input type="submit" value="查詢一筆"> -->
<!-- 	    </FORM> -->
<!-- 		<FORM METHOD="post" ACTION="teacherLeave.do"> -->
<!-- 			<b>查詢全部</b>  -->
<!-- 			<input type="hidden" name="action" value="getAll_For_Display">  -->
<!-- 			<input type="submit" value="查詢全部"> -->
<!-- 		</FORM> -->

<!-- 第二版 -->
<!-- 		<h1 style="text-align:center">請假系統</h1> -->
<!-- 		<div class="card" style="width: 18rem;"> -->
<!-- 			<div class="card-body"> -->
<!-- 				<b class="card-title">新增請假單</b> -->
<!-- 				<Form action="addTeacherLeave.jsp"> -->
<!-- 					<button class="btn btn-primary">GO</button> -->
<!-- 				</Form> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="card" style="width: 18rem;"> -->
<!-- 			<div class="card-body"> -->
<!-- 				<FORM METHOD="post" ACTION="teacherLeave.do" class="card-text"> -->
<!-- 					<b class="card-title">假單編號</b>  -->
<!-- 					<input type="text" name="tl_Id" placeholder="如1001" style="width: 100px"> -->
<!-- 					<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 					<input type="submit" class="btn btn-primary" value="送出查詢"> -->
<!-- 				</FORM> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="card" style="width: 18rem;"> -->
<!-- 			<div class="card-body"> -->
<!-- 				<FORM METHOD="post" ACTION="teacherLeave.do" class="card-text"> -->
<!-- 					<b class="card-title">查詢全部</b><br> -->
<!-- 					<input type="hidden" name="action" value="getAll_For_Display">  -->
<!-- 					<input type="submit" class="btn btn-primary" value="送出查詢"> -->
<!-- 				</FORM> -->
<!-- 			</div> -->
<!-- 		</div> -->

<!-- 暫時登入測試 -->
<!-- 		<div class="card" style="width: 18rem;"> -->
<!-- 			<div class="card-body"> -->
<!-- 				<FORM METHOD="post" ACTION="teacherLeave.do" class="card-text"> -->
<!-- 					<input type="hidden" name="action" value="login"> -->
<!-- 					<input type="hidden" name="t_Num" value="T001"> -->
<!-- 					<button>園長</button> -->
<!-- 				</FORM> -->
<!-- 				<FORM METHOD="post" ACTION="teacherLeave.do" class="card-text"> -->
<!-- 					<input type="hidden" name="action" value="login"> -->
<!-- 					<input type="text" name="t_Num" placeholder="T002" style="width:50px"> -->
<!-- 					<button>老師登入</button> -->
<!-- 				</FORM> -->
<!-- 				登入身分: -->
<%-- 				<input type="submit" <c:if test="${!(sessionScope.tp.t_num == 'T001')}">class="btn-warning"</c:if> value="老師"> --%>
<%-- 				<input type="submit" <c:if test="${sessionScope.tp.t_num == 'T001'}">class="btn-warning"</c:if> value="園長"> --%>
<!-- 			</div> -->
<!-- 		</div> -->
</body>
<%@ include file="/back-end/includePage/BootStrap"%>
</html>