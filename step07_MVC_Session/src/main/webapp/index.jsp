<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<% 
		Cookie cookie1 = new Cookie("id","test");
		cookie1.setMaxAge(60*60);
		response.addCookie(cookie1);
%>
<% 
/* Customer 객체 생성 String id, int age */
	session.setAttribute("id", "KYJ-spring-session");
	session.setAttribute("age", 32);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>

	<h2>index.jsp</h2>
	<hr><hr><hr>
	<H3>Cookie API Test</H3>
	<a href="cookieTest.do">cookieTest.do</a>
	<hr><hr><hr>
	
	<H3>Session API Test</H3>
	<a href="session/sessionTest1.do">1. session/sessionTest1.do</a><br>
	<a href="session/sessionTest2.do?id=spring&age=32">2. session/sessionTest2.do : DTO 객체를 세션에 저장</a><br>
	<hr><hr><hr>
	
	<h3>Login Test</h3>
	<a href="session/loginForm.do">로그인 하러 가기 </a>
	<c:if test="${sessionScope.isAdmin=='true'}"><a href="session/logout.do">로그아웃</a><br></c:if>
	
</body>
</html>