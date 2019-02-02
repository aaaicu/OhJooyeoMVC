<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home(Test Bootstrap)</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/order-manage.css">
	<script type="text/javascript"	src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>

<c:import url="/WEB-INF/views/includes/nav.jsp"></c:import>
<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
<div class ="container">

		<c:if test="${requestScope.pageName != null}">
		<c:import url="/WEB-INF/views/${requestScope.pageName }.jsp"></c:import>
		</c:if>
</div>
<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
</body>
</html>
