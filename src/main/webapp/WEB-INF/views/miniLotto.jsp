<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value="/js/jquery-1.10.2.js" />"></script>
<link rel="stylesheet" href="<c:url value='/css/mini-lotto.css' />">
<link rel="stylesheet" href="<c:url value='/css/order-manage.css' />">
<title>돈암동 미니로또</title>
</head>
<body>
	<div id="header"></div>
	<div class="lotto">
		<img id="image" width=300px height=300px src="img/minilotto.png" />
	</div>
	<br>
	<div class="buttons">

		<input type=button id="start" value="시 작">
		<input type=button id="stop"  value="정 지">
	</div>
	<br>
	<div id="container"></div>

	<br>
	<br>
	<div class="name-shadow">Created By Kyome</div>
	
	<script src="<c:url value="/js/miniLotto.js" />"></script>
</body>
</html>