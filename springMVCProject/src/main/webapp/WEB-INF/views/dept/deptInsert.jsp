<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/commonfiles.jsp" %>
<title>부서</title>
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
<h1>부서정보 입력</h1>
<!-- http://localhost:9999/ -->
<form method="post"   action="<%=request.getContextPath() %>/dept/deptInsert.do" class="mb-3">
<table >
  <tr  class="form-floating">
    <td><label for="department_id">직원번호</label></td>
    <td><input class="form-control"  type="number" 
    name="department_id" id="department_id" 
    required="required"
    placeholder="부서번호는sequence처리함"
    autofocus="autofocus"
    
   disabled="disabled"
    ></td>
  </tr>
  <tr>
    <td>부서 이름</td>
    <td><input type="text" name="department_name" maxlength="20" ></td>
  </tr>
  <tr>
    <td>관리자 번호</td>
    <td><input type="text" name="manager_id" required="required"></td>
  </tr>
  <tr>
    <td>지역 번호</td>
    <td><input type="text" name="location_id" required="required"></td>
  </tr>
  
  <tr style="text-align: center;">
    <td colspan="2">
       <input type="submit" value="부서등록" style="margin: 10px 0  !important;">
    </td>
  </tr>
</table>
</form>

</body>
</html>
    