<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

<title>Mysite</title>
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="post" action=" ${pageContext.request.contextPath}/user/join">
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="" />
	
						<label class="block-label" for="email">이메일</label>
						<input id="email" name="email" type="text" value="" />
						<input type="button" value="id 중복체크" id ="checkbtn">
						<div id="checkresult" ></div>
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="" />
						
						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
						</fieldset>
						
						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
							<label>서비스 약관에 동의합니다.</label>
						</fieldset>
						
						<input type="submit" value="가입하기">
						
					</form>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div><!-- /container -->
</body>
<script type="text/javascript">

$("#checkbtn").on("click",function(){
	console.log("중복체크 클릭 ");
	var email =$("[name=email]").val(); 
	console.log("중복검사할 메일 :"+email);
	$.ajax({
		url : "${pageContext.request.contextPath}/user/joincheck",
		type : "post",
		//contentType : "application/json",
		data : {email:email},//파라미터로 보낼때  주소위에 달고가는거랑 똑같다고 생각해주면됨 
		//dataType : "parameter", //보낼때 타입 : 파라미터 /json선택가능 
		success : function(emailcheck){
			console.log("submit  success성공 " );
			/*성공시 처리해야될 코드 작성*/
					
					console.log("emailcheck : "+emailcheck);
					
					$("#checkresult").html(emailcheck);
				

		
			}
			,
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

});



</script>
</html>		
		
