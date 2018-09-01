<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <%@include file="nav.jsp" %> --%>
	<form action="${pageContext.request.contextPath}/add-worship" method="post">
		<table>
			<tr>
				<td>입력</td>

			</tr>
			<tr>
				<td>Worship ID</td>
				<td><input type="text" name="worshipId"></td>
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
			<tr>
				<td><input type="submit" value="등록"></td>
			</tr>


		</table>


	</form>

	<%-- <%@include file="footer.jsp" %> --%>
</body>
</html>