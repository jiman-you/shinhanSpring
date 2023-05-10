<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath }" scope="application"></c:set>
<%
	//String company="신한금융소프트아카데미";
%>
   <div style="border:2px dotted red;">
 


<c:if test="${sessionScope.loginUser!=null }">
  <p>로그인한 사용자 : ${loginUser==null?"guest":loginUser.manager_name }</p>
	<button id="btnlogout" value="로구아웃">로구아웃</button>
	<img alt="귀여운사진" width="200" height="150" src="${path}/uploads/${loginUser.pic }">
</c:if>
<c:if test="${sessionScope.loginUser==null }">
	<button onclick="location.href='${path}/auth/loginCheck.do'">로구인</button>
</c:if>

</div>

<script>
$(function(){
	$("#btnlogout").on("click",function(){
		$.ajax({
			url:"${path}/auth/logout.do",
			success:function(responseData){
				alert(responseData+"로구아웃되셧습니다")
				location.href="${path}/auth/loginCheck.do";
				
			},
			error:function(message){
				alert(message);
				console.log(message);
			}
		});
	});
});
	
</script>