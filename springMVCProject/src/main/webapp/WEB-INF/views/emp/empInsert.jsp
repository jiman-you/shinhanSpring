<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
   <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath }" scope="application"></c:set>
  
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/commonfiles.jsp" %>
<title>직원등록1</title>
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
<!-- service의 out.write() -->
<%--JSP 주석 --%>
<%-- 
subject: <%=subject %><br>
score: <%=score %><br>
add함수: <%=add(100, 200) %><br>
age : <%=age %>
--%>
<div id="container">

<h1>직원정보 입력</h1>
<!-- http://localhost:9999/ -->
<form method="post"   action="<%=request.getContextPath() %>/emp/empinsert.do" class="mb-3" id="myfrm">
<table >
  <tr  class="form-floating">
    <td><label for="employee_id">직원번호</label></td>
    <td><input class="form-control"  type="number" 
    name="employee_id" id="employee_id" 
    required="required"
    placeholder="직원번호는sequence처리함"
    autofocus="autofocus"
    
   disabled="disabled"
    ></td>
  </tr>
  <tr>
    <td>firstName</td>
    <td><input type="text" name="first_name" maxlength="20" ></td>
  </tr>
  <tr>
    <td>LastName</td>
    <td><input type="text" name="last_name" required="required"></td>
  </tr>
  <tr>
    <td>이메일</td>
    <td><input type="email" name="email" required="required"></td>
  </tr>
  <tr>
    <td>phone</td>
    <td><input type="tel" name="phone_number" 
    value="010-7896-8963"
    pattern="010-[0-9]{4}-[0-9]{4}"></td>
  </tr>
  <tr>
    <td>salary</td>
    <td><input type="number" name="salary" ></td>
  </tr>
  <tr>
    <td>부서</td>
    <td>
    
    <select name="department_id">
     <option value = "0" >부서 없음</option>
    	<c:forEach items="${deptList }" var="dept" varStatus="status">
    		<option value="${dept.department_id }">${status.count}: ${dept.department_name }</option>
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
    		<option value="${manager.employee_id }">${manager.first_name }--${manager.last_name }</option>
    	</c:forEach>
    		
    </select>
    </td>
  </tr>
  <tr>
    <td>커미션</td>
    <td><input type="text" name="commission_pct"  value="0.2"></td>
  </tr>
  <tr>
    <td>입사일</td>
    <td><input type="date" name="hire_date" 
    value="2021-01-10"
    required="required"></td>
  </tr>
  <tr>
    <td>직급</td>
     <td>
    <select name="job_id">
    	<c:forEach items="${jobList }" var="job">
    		<option value="${job.job_id }">${job.job_title }</option>
    	</c:forEach>
    </select>
    </td>
  </tr>
  <tr style="text-align: center;">
    <td colspan="2">
       <input type="submit" value="직원등록" style="margin: 10px 0  !important;">
       <input type="button" value="직원등록(rest)" id="btnRestInsert" style="margin: 10px 0  !important;">
    </td>
  </tr>
</table>
</form>
<script>
	//contentType: 보내는 데이터의 형식은--- json이다
	$(function(){
		$("#btnRestInsert").click(function(){
			var aa = $("#myfrm").serializeArray();
			console.log(aa);
			var obj = {};
			$.each(aa,function(index,item){
				obj[item.name] = item.value;
			});
			console.log(obj);
			
	 		$.ajax({
				url:"${path}/restemp/empinsert.do",
				method:"POST",
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
    