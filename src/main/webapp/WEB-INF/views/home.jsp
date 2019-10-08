<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home(Test Bootstrap)</title>
	<!-- <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"> -->
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
<script src="${pageContext.request.contextPath}/js/worship-base.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/update-worship.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/order-manage.js"></script>
<script type="text/javascript">


	
	function showModal(){
		$(".modal").modal();
	}
</script>
</body>
</html>
