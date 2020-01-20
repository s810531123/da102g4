<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<form action="commBook.do" method="post" >
		<table >
			<tr>
				<th rowspan="2" style="width: 30%"><img src="image/Logo.png" width=50px id="ting">老師群體發話</th>
				<td rowspan="2">
					<input type="text" name="comm_T_Msg" value="${param.comm_T_Msg }" id="msg" minlength=5 style="width:100%; height: 50px; text-align:left">
				</td>
			</tr>
			<tr>
				<th>
					<c:if test="${!addCommBook}">
						<button type="submit" class="btn-primary" style="border-radius: 7px;">寫入今日聯絡簿</button>
						<input type="hidden" name="action" value="insert">
					</c:if>
					<c:if test="${addCommBook}">
						<button type="submit" class="btn-success" style="border-radius: 7px;">修改今日老師的話</button>
						<input type="hidden" name="action" value="updateT_Msg">
					</c:if>			
				</th>
			</tr>
		</table>
	</form>
<script>
	$("#ting").click(function() {
		$("#msg").val("明天請帶玩具到學校!");
	});
</script>

