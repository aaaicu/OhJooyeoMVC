<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<header>

</header>
<div>
header
</div>


<%-- <div id="header">
	<h1><a href="${pageContext.request.contextPath}/${blogId}">${requestScope.blog.BLOGTITLE}</a></h1>
	<ul>
		<c:if test="${sessionScope.authUser.id ==blogId}">
			<li><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/basic">내블로그 관리</a></li>
		</c:if>
		
		<c:choose>
			<c:when test="${sessionScope.authUser != null}">
				<!-- 로그인 후 메뉴 -->
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			</c:when>
			<c:otherwise>
				<!-- 로그인 전 메뉴 -->
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			</c:otherwise>

		</c:choose>
	</ul>
</div> --%>
