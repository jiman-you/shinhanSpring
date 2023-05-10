	<%@page import="com.shinhan.vo.DeptVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    session="true" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   <c:set var="path" value="${pageContext.request.contextPath }"/>
   
<!DOCTYPE html>
<html>
<head>
	<%@include file="../common/commonfiles.jsp" %>
<title>부서조회</title>
<style>
h1 {
	text-align: center;
	background-color: #00B9FF;
	border-radius: 20px;
	padding-top: 10px;
	padding-bottom: 10px;
	margin-left: 30%;
	margin-right: 30%;
}

th {
	background-color: #FFF56E;
	width: 100px;
	text-align: center;

	padding-top: 20px;
	padding-bottom: 20px;
	margin: 0;
}
#aa{
	border-top-left-radius: 20px;
}
#bb{

	border-top-right-radius: 20px;
}
td {
	width: 100px;
	text-align: center;
}

tbody > tr:hover {
	background:  #80E12A;
	cursor: pointer;
}

table {
	margin-left: auto;
	margin-right: auto;
	border-collapse: seperate !important;
	border-bottom-left-radius: 20px;
	border-bottom-right-radius: 20px;
	border-top-left-radius: 20px;
	border-top-right-radius: 20px;
	border-spacing: 0;
}
#empinsert{
		position: fixed;
			top:5px;
			right:5px;
			width:100px;
			height:50px;
}
a#empinsert2{
	position: fixed;
			top:60px;
			right:5px;
			width:100px;
			height:50px;
}
#empinsert3{
	position: fixed;
			top:110px;
			right:5px;
			width:100px;
			height:50px;
}

</style>

</head>
<body>


<div class="container mt-3">
		
		<h1>부서목록</h1>
		<a href="${path }/dept/deptInsert.do">신규 부서 등록</a>
		</select>
		<table>
			<thead>
				<tr>
					
					<th id="aa">순서</th>
					<th>부서번호</th>
					<th>부서이름</th>
					<th>관리자번호</th>
					<th>지역번호</th>
					<th id="bb"></th>
				</tr>		
			</thead>
			<tbody>
				<c:forEach items="${deptAll }" var="dept" varStatus="status" >
					
				<tr>
				
					<td style="backgroun-color:${status.first||status.last?'orange':'white'};">${status.count }</td>
					<td><a href="deptDetail.do?deptid=${dept.department_id }">${dept.department_id }</a></td>
					<td>${dept.department_name}</td>
					<td>${dept.manager_id}</td>
					<td>${dept.location_id }</td>

					<td><button class="btnDel" data-del="${dept.department_id }">삭제</button></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
	<script>
		$(function(){
			$(".btnDel").click(function(){
				var deptid = $(this).attr("data-del");
				location.href="${path}/dept/deptDelete.do?deptid="+deptid;
			})
			
		})
	</script>
</body>
</html>