<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OhJooYeo Login</title>
<link rel="stylesheet" href="<c:url value='/css/login.css' />">
</head>
<body >
<div class="container">
  <div class="register-left">
    <div class="card">
      <h1 class="title">OhJooYeo</h1>
      <p class="desc">
        오늘의 주보,여기요!<p>
        <hr>
      <p class="desc">
        돈암동교회 청년부 주보관리 시스템
      </p>
    </div>
  </div>
  <div class="register-right">
    <div class="card">
      <div class="card--desc">
        <h2>Login</h2>
        <p>
          돈암동교회 청년부 주보앱 관리자 페이지입니다.
        </p><br>
      </div>
      <div class="card--register">
        <form id = "login-form" action="${pageContext.request.contextPath}/loginCheck" method = "post">
          <input type="text" name="id" placeholder="id" />
          <input type="password" name="pw" placeholder="password" />
          <input type="button" value="login" onClick = "document.getElementById('login-form').submit();"/>
        </form>
      </div>
      <div>
        <a href="#" class="policy">아이디찾기</a>	
        <a href="#" class="policy">비밀번호찾기</a>
      </div>

<!--       <div class="card--socialmedia">
        <p>Login with social media</p>
        <div class="party">
          <div class="socialmedia">
            <a class="media facebook">
            <span><i class="fab fa-facebook-f"></i></span>
            <span>Facebook</span>
          </a>
          </div>
          <div class="socialmedia">
            <a class="media twitter">
              <span><i class="fab fa-twitter"></i></span>
              <span>Twitter</span>
            </a>
          </div>
          <div class="socialmedia">
            <a class="media instagram">
              <span><i class="fab fa-instagram"></i></span>
              <span>Instragram</span>
            </a>
          </div>
        </div>
      </div> -->
    </div>
  </div>
</div>

    
</body>
</html>
<!-- Credit:
https://codepen.io/Zaku/pen/vzBKWe?editors=1100
 -->
