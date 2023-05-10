<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath }" scope="application"></c:set>

<!DOCTYPE html>
<html>
<head>
<%@include file="../common/commonfiles.jsp" %>
<title>직원상세보기</title>

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
	input[name='email'],
	input[name='department_id'],
	input[name='job_id'],
	input[name='salary'],
	input[name='hire_date'],
	input[name='manager_id']{
		background-color:pink;
	}
	td>input,label{
		width:200px;
	}
</style>
</head>
<body>
 <div id="container">
<h1>직원상세보기</h1>

<form method="post"   action="<%=request.getContextPath() %>/emp/empDetail.do" id = "myfrm">
<table >
  <tr  class="form-floating">
    <td><label for="employee_id">직원번호</label></td>
    <td><input class="form-control"  type="number" 
    name="employee_id" id="employee_id" 
    required="required"
    value="${emp.employee_id}"
    ></td>
  </tr>
  <tr>
    <td>firstName</td>
    <td><input type="text" name="first_name" maxlength="20"
    		value=${emp.first_name} ></td>
  </tr>
  <tr>
    <td>LastName</td>
    <td><input type="text" name="last_name" required="required"
    		value=${emp.last_name}></td>
  </tr>
  <tr>
    <td>이메일</td>
    <td><input type="email" name="email" required="required"
    		value=${emp.email}></td>
  </tr>
  <tr>
    <td>phone</td>
    <td><input type="tel" name="phone_number" 
    value=${emp.phone_number}
    pattern="010-[0-9]{4}-[0-9]{4}"></td>
  </tr>
  <tr>
    <td>salary</td>
    <td><input type="number" name="salary" value=${emp.salary}></td>
  </tr>
  <tr>
		 <td>부서</td>
			 <td>
			 
				 <select name="department_id">
				  <option value = "0" >부서 없음</option>
					  <c:forEach items="${deptList }" var="dept" varStatus="status">
					   <option  ${emp.department_id==dept.department_id?"selected":"" } value="${dept.department_id }">${status.count}: ${dept.department_name }</option>
				</c:forEach>
		   </select>
		   </td>
	  </tr>
	<tr>
    <td>메니져</td>
    <td>
    <select name="manager_id">
  	  <option value = "0" >매니저 없음</option>
    	<c:forEach items="${managerList }" var="manager">
    		<option  ${emp.manager_id==manager.employee_id?"selected":"" } value="${manager.employee_id }">${manager.first_name }--${manager.last_name }</option>
    	</c:forEach>
    </select>
    </td>
  </tr>
  <tr>
    <td>커미션</td>
    <td><input type="text" name="commission_pct"  value=${emp.commission_pct}></td>
  </tr>
  <tr>
    <td>입사일</td>
    <td><input type="date" name="hire_date" 
    value=${emp.hire_date}
    required="required"></td>
  </tr>
  <tr>
    <td>직급</td>
     <td>
    <select name="job_id">
    	<c:forEach items="${jobList }" var="job">
    		<option ${emp.job_id==job.job_id?"selected":"" } value="${job.job_id }">${job.job_title }</option>
    	</c:forEach>
    </select>
    </td>
  </tr>
  <tr style="text-align: center;  ">
    <td colspan="2" >
       <input type="submit" value="직원정보수정" style="margin: 10px 0  !important;">
       <input id = "btnRestUpdate"type="button" value="직원정보수정(rest방식)" style="margin: 10px 0  !important;">       
    </td>
  </tr>
</table>
</form>
<script>
	//contentType: 보내는 데이터의 형식은--- json이다
	$(function(){
		$("#btnRestUpdate").click(function(){
			var aa = $("#myfrm").serializeArray();
			console.log(aa);
			var obj = {};
			$.each(aa,function(index,item){
				obj[item.name] = item.value;
			});
			console.log(obj);
			
	 		$.ajax({
				url:"${path}/restemp/empDetail.do",
				method:"PUT",
				data:JSON.stringify(obj),
				contentType:"application/json",
				success:function(data){
					alert(data);
				},
				error:function(){}
			});	
	 	});
	});
</script>
</body>
</html>
    