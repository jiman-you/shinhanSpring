<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>${emp }</p>
	<p>first_name:${emp.first_name }</p>
	<p>last_name:${emp.last_name }</p>
	<p>address:${address }</p>
	
	<p>session에서 읽어오자: ${userInfo }</p>
</body>
</html>