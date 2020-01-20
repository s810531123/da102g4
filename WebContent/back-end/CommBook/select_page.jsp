<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page autoFlush="true" buffer="1094kb"%><!-- 解決執行緒相衝問題 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>聯絡簿首頁</title>
<%@ include file="/back-end/includePage/Head"%>
</head>

<!-- 自己的css -->
<link rel="stylesheet" href="./css/select.css">

<body>
	<%@ include file="/back-end/includePage/Nav"%>
	<%@ include file="/back-end/includePage/LeftSide"%>
	<div class="col-lg-10 col-md-10 cb rightContent ">
		<div class="color-manto">
		
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<c:forEach var="message" items="${errorMsgs}">
						${message}<br>
				</c:forEach>
			</c:if>	
			
			<!-- 老師未登入的阻擋 -->	
			<c:if test="${(tp == null) }"> 請老師先登入帳號  </c:if>
			
			<!-- 權限篩選 -->
			<c:if test="${(!(tp == null)) && (tp.t_num != 'T001') }">
			
				<!-- 老師群體發話 -->
				<jsp:include page="addCommBook2.jsp"></jsp:include>
				
				<!-- 星星分隔線 -->
				<img src="image/star.png" width="100%" id="ting">
				<h1 align="center"style="color:#FF6347;font-family: Microsoft JhengHei;">班 級 聯 絡 簿</h1>
				
					<!-- 指定日期查詢 -->				
					<div align="center">					
						<FORM METHOD="post" ACTION="commBook.do">
							<input type="hidden" name="action" value="showTwoDayFromTeacher">
							<b>請選擇日期：</b>
							<input type="text" name="comm_Date" id="f_date1" >
							<img src="image/search.png" class="search" title="查詢"> 
						</FORM>						
					</div>
					
					<!-- 查詢最近二筆資料 -->
					<%@ include file="getAllRecentDay.jsp"%>
			</c:if>
		</div>
	</div>

</body>
<%@ include file="/back-end/includePage/BootStrap"%>
<%@ include file="DateTime" %>
<script type="text/javascript">
	$(".search").click(function() {
		$(this).parent().submit();
	})
</script>
</html>