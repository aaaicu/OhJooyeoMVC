<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>Delete Worship</title>
</head>
<body>
	<%-- <%@include file="nav.jsp" %> --%>
	<form action="${pageContext.request.contextPath}/delete-worship"
		method="post">
		<table>
			<tr>
				<td>삭제 목록</td>
			</tr>
			<tr>
				<td><input type="text" name="worshipId"></td>
			</tr>
			<tr>
				<td><input type="submit" value="삭제"></td>
			</tr>
		</table>
	</form>

	<%-- <%@include file="footer.jsp" %> --%>
</body>

<script type="text/javascript">
	$(document).ready(function() {

	});
</script>

</html>