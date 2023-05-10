<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html>
<head>
<title>부서상세보기</title>

<style>
	#container{
		border: 1px solid black;
		border-collapse:collapse;
		width:50%;
		margin:0 auto;
	}
	h1{
		text-align: center;
	}
	table{
		margin:0 auto;
	}
	
	td>input,label{
		width:200px;
	}
</style>
</head>
<body>
 <div id="container">
<h1>부서상세보기</h1>

<form method="post"   action="<%=request.getContextPath() %>/dept/deptUpdate.do">
<table >
  <tr  class="form-floating">
    <td><label for="department_id">부서번호</label></td>
    <td><input class="form-control"  type="number" 
    name="department_id" id="department_id" 
    required="required"
    value="${dept.department_id}"
    ></td>
  </tr>
  <tr>
    <td>부서이름</td>
    <td><input type="text" name="department_name" maxlength="20"
    		value=${dept.department_name} ></td>
  </tr>
  <tr>
    <td>매니저 아이디</td>
    <td><input type="text" name="manager_id" required="required"
    		value=${dept.manager_id}></td>
  </tr>
  <tr>
    <td>지역 번호</td>
    <td><input type="text" name="location_id" required="required"
    		value=${dept.location_id}></td>
  </tr>
  <tr style="text-align: center;  ">
    <td colspan="2" >
       <input type="submit" value="부서정보수정" style="margin: 10px 0  !important;">
    </td>
  </tr>
</table>
</form>

</body>
</html>
    