	<%@page import="com.shinhan.vo.EmpVO"%>
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
<title>직원조회</title>
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
		
		<table>
			<thead>
				<tr>
					
					<th id="aa">순서</th>
					<th>직원번호</th>
					<th>이름</th>
					<th>성</th>
					<th>이메일</th>
					<th>급여</th>
					<th>급여 누적값</th>
					<th>입사일</th>
					<th>전화번호</th>
					<th>직책</th>
					<th>매니져</th>
					<th>커미션</th>
					<th>부서</th>
					<th id="bb"></th>
				</tr>		
			</thead>
			<tbody>
			<!--for(EmpVO emp:empAll  -->
				<c:set var="totalSalary" value="0"/>
				<c:forEach items="${empAll }" var="emp" varStatus="status" >
				<c:set var="totalSalary" value="${totalSalary+emp.salary }"/>
					
				<tr>
				
					<td style="backgroun-color:${status.first||status.last?'orange':'white'};">${status.count }</td>
					<td><a href="empDetail.do?empid=${emp.employee_id }">${emp.employee_id }</a></td>
					<td>
						<a style="color:${fn:length(emp.first_name)>3?'red':'blue'};" href="empDetail.do?empid=${emp.employee_id }">
							${emp.first_name }
						</a>
					</td>
					<td>${emp.last_name }</td>
					<td>${emp.email}---
						${fn:substring(emp.email,0,fn:indexOf(emp.email,'@')) }
					</td>
					<td>
						<fmt:formatNumber value="${emp.salary }" groupingUsed="true" type="currency"></fmt:formatNumber>
					</td>
					<td>${totalSalary }</td>
					<td>
						<fmt:formatDate value="${emp.hire_date }" dateStyle="full"/>
					</td>
					<td>${emp.phone_number }</td>
					<td>${emp.job_id }</td>				
					<td>${emp.manager_id }</td>
					<td>
						<fmt:formatNumber value="${emp.commission_pct }" groupingUsed="true" type="percent"></fmt:formatNumber>
					</td>
					<td>${emp.department_id }</td>
					<td><button class="btnDel" data-del="${emp.employee_id }">삭제</button></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
	<script>
		$(function(){
			$(".btnDel").click(function(){
				var empid = $(this).attr("data-del");
				location.href="${path}/emp/empDelete.do?empid="+empid;
			})
			
		})
	</script>
</body>
</html>