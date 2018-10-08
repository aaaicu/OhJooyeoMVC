<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>성경등록</title>
</head>
<body>
	<%-- <%@include file="nav.jsp" %> --%>

	<div>
		<span> 
			<select id="select-book" name="book">
			</select>
		</span> 
		<span> 
			<select id="select-chapter" name="chapter">
			</select>
		</span> 	
		<span> 
			<select id="select-section" name="section">
			</select>
		</span>
	</div>
	<div>
	<textarea>
		내용확인
	</textarea>
	</div>
	<div>
	<input type = "button" value = "전송">
	<input type = "button" value = "취소" onClick = "window.close()">
	</div>
	<%-- <%@include file="footer.jsp" %> --%>
</body>
<script type="text/javascript">

	
</script>

</html>