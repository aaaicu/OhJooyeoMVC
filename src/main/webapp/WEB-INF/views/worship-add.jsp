<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link rel="stylesheet" href="<c:url value='/css/order-manage.css' />">
<title>Add Worship</title>
</head>
<body>
	<%-- <%@include file="nav.jsp" %> --%>
	<%-- <p><c:url value="/js/insert-worship.js" /> </p> --%>
	<%-- <%@include file="nav.jsp" %> --%>

	<div id = "worshipArea">
	<form id = "worshipForm">
	<div>
		<span>
		Worship ID : <select id="selectWorshipId" name="selectWorshipId">
		</select>
		</span>
		<span>
		<input type = "button" value = "삭제" id = "deleteWorship">
		</span>
	</div>
	<ul id="worshipInfo">
		<li id = "ws0">
			<table>
				<tr>
					<td>
					<input type='hidden' name='worshipUpdateYN' value ='0'>
					Worship Date
					</td>
					<td><input type="text" class ='chkTarget' name="worshipDate"></td>
				</tr>
				<tr>
					<td>Main Presenter</td>
					<td><input type="text" class ='chkTarget' name="mainPresenter"></td>
				</tr>
				<tr>
					<td>Next Presenter</td>
					<td><input type="text" class ='chkTarget' name="nextPresenter"></td>
				</tr>
				<tr>
					<td>Next Prayer</td>
					<td><input type="text" class ='chkTarget' name="nextPrayer"></td>
				</tr>
				<tr>
					<td>Next Offer</td>
					<td><input type="text" class ='chkTarget' name="nextOffer"></td>
				</tr>
			</table>
		</li>
	</ul>
	</form>
	</div>
	<div id = "renderArea">
		<div id = "orderArea">	
			<form id = "orderForm">
			예배순서 <span><input type = "button" name = "order" class = "addHtml" value = "순서추가"></span><br/>
			<ul id="orderList"></ul>
			</form>
		</div>
		<div id = "adArea">	
			<form id = "adForm">
			광고 <span><input type = "button" name = "ad" class = "addHtml" value = "광고추가"></span><br/>
			<ul id="adList"></ul>
			</form>
		</div>
	</div>

	<br/>
	<br/> 
	<input type="button" id = "updateOrders" value="확인">
	<%-- <%@include file="footer.jsp" %> --%>
</body>
<script src="<c:url value="/js/insert-worship.js" />" ></script>
<script src="<c:url value="/js/order-manage.js" />" ></script>

<script type ="text/javascript">
$(document).ready(function(){
	
	updateWorshipInit();
	init();
});

function updateWorshipInit(){
	/* 예배ID 리스트 비동기식으로 조회 */
	$.ajax({
		url : "${pageContext.request.contextPath }/getWorshipIdList",
		type : "post",
		contentType : "application/json",
		data : "admin",
		dataType : "json",
		success : function(worshipIdList){
			$("#selectWorshipId").children().remove();
			for(var i = 0; i <worshipIdList.length; i++){
				$("#selectWorshipId").prepend("<option>"+worshipIdList[i]+"</option>")
			}
			if(worshipIdList.length > 0 ) {
				getWorshipInfo(worshipIdList[0]);
				getWorshipDetailList(worshipIdList[0],"order");
				getWorshipDetailList(worshipIdList[0],"ad");
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});  

}

</script>

</html>