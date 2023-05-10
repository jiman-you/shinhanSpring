<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<form action="/education/first/paramTest">
아이디:<input type="text" name="userid" value="hello">
패스워드:<input type="password" name="userpass" value="babo">
이메일:<input type="email" name="email2" value="asd@asd">
주소:<input type="text" name="address" >
나이:<input type="text" name="age" >
<input type="submit" value="서버에 전송">
</form>
<ul>
	<li><a href="first/sample1">sample1연결</a></li>
	<li><a href="first/sample2">sample2연결</a></li>
	<li><a href="first/a.do">a연결</a></li>
</ul>
<P>  The time on the server is ${serverTime}. </P>
<P>  나의 이름은 ${myname }. </P>
<P>  나의 나이는 ${myage}. </P>
<P>  나의 차는 ${mycar.model}. </P>
<P>  나의 차는 ${mycar.price}. </P>

</body>
</html>
