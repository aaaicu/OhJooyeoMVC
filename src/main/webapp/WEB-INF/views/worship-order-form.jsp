<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Worship</title>
</head>
<body>
	<%-- <%@include file="nav.jsp" %> --%>
	<form action="${pageContext.request.contextPath}/add-worship-order" method="post">
	<b>Worship_id : </b> <input type="text" name="worship_id"></br></br>
	<table>
		<thead>
			<tr>
				<th>order</th>
				<th>title</th>
				<th>detail</th>			
				<th>presenter</th>
				<th>+</th>			
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td><input type="text" name="title"></td>				
				<td><input type="text" name="detail"></td>			
				<td><input type="text" name="presenter"></td>			
				<td>X</td>
			</tr>
		</tbody>
	</table>
	<input type="submit" value="확인">
	</form>

	<%-- <%@include file="footer.jsp" %> --%>
</body>
</html>