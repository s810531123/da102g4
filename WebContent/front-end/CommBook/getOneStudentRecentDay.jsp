<%@page import="java.util.ArrayList"%>
<%@page import="com.commBook.model.CommBookVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查詢一位學生近二筆聯絡簿資料</title>
<style type="text/css">
.pencil {
	width:45px;
}

.commDay {
	width: 45%;
}

.itemName {
	width: 10%;
}

tr.one{
       background-color: #F0FFFF;
       border-top: 2px solid black;
       border-bottom: 2px solid black;
       font-size: 20px;
}

tr.two{
       background-color: #ffffdf;
       border-color: none;
       font-size: 20px;
}
tr.three{
        background-color: #ffffaa;
        border-color: none;
        font-size: 20px;
}

tr.four{
        background-color: #d3ff93;
        border-bottom: 2px solid black;/*加底線*/
        border-color: none;
           font-size: 20px;
}

th {
    text-align: center;
    font-family:Microsoft JhengHei;
}


td{
   font-size: 20px;
   text-align: left;
}
.a{
	font-family:Microsoft JhengHei;
	color:	#880000;
}

.tableBorder{
	border-right:2px;
	border-right-color: gray;
	border-right-style: solid;
	border-left:2px;
	border-left-color: gray;
	border-left-style: solid;
}

.content{
	padding-left: 10px;
}

</style>

</head>
<body>
	<c:if test="${commBookVOList == null }">
		<%
			System.out.println("沒有資料");
		%>
		<jsp:forward page="commBook.do">
			<jsp:param name="action" value="showTwoDayFromStudent" />
		</jsp:forward>
	</c:if>

	<h3 class="a" align="center">學生：${sessionScope.stp.st_name}</h3>
	<table>
		<%
			request.setAttribute("listSize", 0);//預先設定listSize=0...避免沒有聯絡簿的學生也印出來

			List<CommBookVO> commBookVOList = (ArrayList<CommBookVO>) request.getAttribute("commBookVOList");
			CommBookVO newCommBook = null;
			CommBookVO oldCommBook = null;

			if (commBookVOList.size() != 0) {
				request.setAttribute("listSize", commBookVOList.size());
				newCommBook = commBookVOList.get(0);
				request.setAttribute("newCommBook", newCommBook);//將日期較新的聯絡簿設定成 newCommBook
				request.removeAttribute("oldCommBook");
				if (commBookVOList.size() > 1) {
					oldCommBook = commBookVOList.get(1);
					request.setAttribute("oldCommBook", oldCommBook);//將日期較舊的聯絡簿設定成 oldCommBook
				}
			}
		%>
		<tr class="one">
			<th class="itemName">項目</th>
			<th class="commDay tableBorder">${oldCommBook.comm_Date}</th>
			<th class="commDay">${newCommBook.comm_Date}</th>
		</tr>

		<tr class="two">
			<th>老師的話</th>
			<td class="content tableBorder">${oldCommBook.comm_T_Msg}</td>
			<td class="content">${newCommBook.comm_T_Msg}</td>
		</tr>
		<tr class="three">
			<th>給家長的話</th>
			<td class="content tableBorder">${oldCommBook.comm_Res}</td>
			<td class="content">${newCommBook.comm_Res}</td>
		</tr>
		<tr class="four">
			<th>家長回應</th>
			<td class="content tableBorder">${oldCommBook.comm_P_Msg}</td>
			<td class="content">${newCommBook.comm_P_Msg}<%
				pageContext.setAttribute("Today", new java.sql.Date(System.currentTimeMillis()).toString());
			%> <c:if test="${Today == newCommBook.comm_Date}">
					<img src="image/pencil.png" class="pencil" src="pencil" data-toggle="modal" data-target="#exampleModal">
				</c:if>
			</td>
		</tr>
	</table>

	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" style="color:#20B2AA;font-family: Microsoft JhengHei; font-size: 30px;">
					家長回應
					<img src="image/Logo.png" width=50px id="ting"></h5>
					
					
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form METHOD="post" ACTION="commBook.do">
					<div class="modal-body">
						<input type="text" style="width: 460px" name="comm_P_Msg" value="${newCommBook.comm_P_Msg}" id="msg"> 
						<input type="hidden" name="comm_Id" value="${newCommBook.comm_Id}"> 
						<input type="hidden" name="action" value="updateStudent">
					</div>
					<div class="modal-footer">
						<input type="submit" value="送出" class="btn btn-secondary cancel">
						<button type="button" class="btn btn-secondary cancel"
							data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(".pencil").click(function() {
		$(this).parent().submit();
	})
	
	//神奇小按鈕
	$("#ting").click(function() {
		$(this).parent().submit();
		$("#msg").val("老師辛苦了!");
	});
	
</script>

</html>