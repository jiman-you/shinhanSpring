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
		<!--  RestFull 방식 연습 -->
		<input type="number" id="empid" value="100"> 
		<button id="empRetrive">1건의 직원 조회</button>
		<button id="empAll">모든 직원 조회</button>
		<div id="resultMessage"></div>
		
	
		
		<table id="here">
		<h1>직원목록</h1>
	
		<button id="empinsert" onclick="location.href='empinsert.do'" 
		type="button" class="btn btn-success">직원등록</button>
		<a id = "empinsert2" type="button" class="btn btn-success" href="empinsert.do">Link</a>
		<hr>
		<button id = "empinsert3" type="button" class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">Modal이용 직원등록</button>

		
		<div>
		부서<select id ="deptSelect" multiple="multiple">
				<option value="0" selected="selected">전체</option>
			 	 <c:forEach items="${deptList }" var="dept" varStatus="status">
					 <option  ${emp.department_id==dept.department_id?"selected":"" } value="${dept.department_id }">${status.count}: ${dept.department_name }</option>
				</c:forEach>
			 </select>
		 직급 <select id ="jobSelect" >
		 		<option value="">전체</option>
				<c:forEach items="${jobList }" var="job">
					<option ${emp.job_id==job.job_id?"selected":"" } value="${job.job_id }">${job.job_title }</option>
				</c:forEach>
			</select>
		 salary<input type="number" id ="salSelect" value="1000"><span>이상</span>
		 입사일 <input type="date" id="dateSelect" value="2000-01-01"/><span>이후</span>
		<button id="btnRetrieve" >조건조회</button>
		</div>
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
					<td>${emp.email}
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
					<td><button class="btnDel" data-del="${emp.employee_id }"><img style="width:50px; height:50px;" src="${path}/images/trash.png"></button></td>
					<td><button class="btnDelRest" data-del="${emp.employee_id }"><img style="width:50px; height:50px;" src="${path}/images/trash.png"></button></td>
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
			$(".btnDelRest").click(function(){
				var empid = $(this).attr("data-del");
				 $.ajax({
						url:"${path}/restemp/empDelete.do/"+empid ,
						method:"delete",
						success:function(data){
							alert(data);
							location.href="${path}/emp/emplist.do";	
						},
					 	error:function(){}
					 });

		});
		$("#btnRetrieve").click(function(){
				console.log($("#deptSelect").val());
				$.ajax({
		            url : "empCondition.do",
		            data : {
		            	deptid:$("#deptSelect").val(),
		            	jobid:$("#jobSelect").val(),
		            	salary:$("#salSelect").val(),
		            	hiredate:$("#dateSelect").val()||undefined
		            	},
		            success : function(responseTable) {
		            
						$("#here").html(responseTable);
		            },
		    
		            error : function(message) {
		            	$("#here").html(message);
		            }
		        }); // $.ajax */
			})
		})
	</script>
	<script>
	/*restful 방식 연습  */
 $(function(){
	 $("#empRetrive").click(function(){
			 var empid=$("#empid").val();
		 $.ajax({
			url:"${path}/restemp/empDetail.do/" + empid,
			
			success:function(data){
				console.log(data);	
				$("#resultMessage").html(data.first_name);
			},
		 	error:function(){}
		 
		 });
	 });
	 $("#empAll").click(function(){	
		 $.ajax({
			url:"${path}/restemp/emplist.do/" ,
			
			success:function(data){
				console.log(data);	
				//$("#resultMessage").html(data.first_name);
				var output="<ul>";
				$.each(data.emplist,function(index,item){
					output += "<li>"+item.first_name+"</li>"
				})
				$("#resultMessage").html(output+"</ul>");
			},
		 	error:function(){}
		 
		 });
	 });
 })
 
</script>
</body>
</html>