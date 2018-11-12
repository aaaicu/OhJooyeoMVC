<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.js"></script>


<title>Update Worship</title>
</head>
<body>
	<%-- <%@include file="nav.jsp" %> --%>
	<p><c:url value="/js/insert-worship.js" /> </p>
	<div id = "worshipArea">
		<table>
			<tr>
				<td><div>
					Worship ID : <select id="selectWorshipId" name="selectWorshipId">
					</select>
				</div></td>		
			</tr>
			<tr>
				<td>Worship Date</td>
				<td><input type="text" name="worshipDate"></td>
			</tr>
			<tr>
				<td>Main Presenter</td>
				<td><input type="text" name="mainPresenter"></td>
			</tr>
			<tr>
				<td>Next Presenter</td>
				<td><input type="text" name="nextPresenter"></td>
			</tr>
			<tr>
				<td>Next Prayer</td>
				<td><input type="text" name="nextPrayer"></td>
			</tr>
			<tr>
				<td>Next Offer</td>
				<td><input type="text" name="nextOffer"></td>
			</tr>
		</table>
	</div>
	<div id = "renderArea">
		<div id = "orderArea">	
			예배순서 <span><input type = "button" name = "orderButton" class = "addHtml" value = "순서추가"></span><br/>
			<ul id="orderList"></ul>
		</div>
		<div id = "adArea">	
			광고 <span><input type = "button" name = "adButton" class = "addHtml" value = "광고추가"></span><br/>
			<ul id="adList"></ul>
		</div>
	</div>
	<br/>
	<br/> 
	<input type="button" id = "updateOrders" value="확인">
	<%-- <%@include file="footer.jsp" %> --%>
</body>
<script src="<c:url value="/js/insert-worship.js" />" ></script>
<script type ="text/javascript">
$(document).ready(function(){
	init();
	console.log(addOrderList);
});
</script>

</html>