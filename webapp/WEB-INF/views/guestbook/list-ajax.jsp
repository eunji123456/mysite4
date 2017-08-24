<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
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
				<div id="guestbook">

					<form method="post" id= "write-form"
						action="${pageContext.request.contextPath}/api/gb/add"> <!-- 여기서 submit 버튼을 누르면 add로 감 --> 
						<table>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name" id= "name" /></td>
								<td>비밀번호</td>
								<td><input type="password" name="password" id="passwpord"/></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>

					</form>
					<ul id="guestbook-list">
							
					</ul>

				</div>
				<!-- /guestbook -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
	<!-- /container -->
</body>
<script type="text/javascript">
$(document).ready(function(){	
	fetchlist();
	
	$("#write-form").on("submit",function(){
		event.preventDefault();//그전에 있었던 기능막는 방법 
		console.log("클릭됨 ");
		//값을 가져오는 두가지 방법 
		//var name =$("#name").val();//아이디를 매겨서 사용하는 방법 //확인 : console.log(name );
/* 		var name2= $("[name=name]").val();//name 으로 사용하는 방법 
		var password= $("[name=password]").val();
		var content= $("[name=content]").val(); */
		var gbinfo={
				name : $("[name=name]").val(),
				password : $("[name=password]").val(),
				content  : $("[name=content]").val()
				
		}
		
		
		$.ajax({
			url : "${pageContext.request.contextPath}/api/gb/add",
			type : "post",
			contentType : "application/json",
			//data : {name:name2,password:password,content:content},//파라미터로 보낼때  주소위에 달고가는거랑 똑같다고 생각해주면됨 
			data : JSON.stringify(gbinfo),
			dataType : "json", //보낼때 타입 : 파라미터 /json선택가능 
			
			success : function(guestbookvo){
				console.log("submit  success성공 " );
				/*성공시 처리해야될 코드 작성*/
				render(guestbookvo,"up");
				
				}
				,
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		
		
	});
});

function fetchlist(){
	
	$.ajax({
		url : "${pageContext.request.contextPath}/api/gb/list",
		type : "post",
		//contentType : "application/json",
		//data : {name: "홍길동"},
		dataType : "json",
		success : function(gblist){
		
			for(var i=0; i<gblist.length; i++){
				render(gblist[i],"down");
			}
			console.log(gblist);
			/*성공시 처리해야될 코드 작성*/
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
}

function render (guestbookVo,updown){
	
	console.log(guestbookVo);
	var str ="";
	str+="<li>";
	str+="	<table>";
	str+="		<tr>";
	str+="			<td>[" + guestbookVo.no + "]</td>";
	str+="			<td>["+guestbookVo.name+ "]</td>";
	str+="			<td>["+guestbookVo.regDate+ "]</td>";
	str+="			<td><a href='${pageContext.request.contextPath}/gb/deleteform?no=${vo.no }'>삭제</a></td>";
	str+="		</tr>";
	str+="		<tr>";
	str+="			<td colspan=4>"+guestbookVo.content+"</td>";
	str+="		</tr>";
	str+="	</table>";
	str+="	<br/>";
	str+="</li>";

	if(updown == "down"){
		$("#guestbook-list").append(str);//맨밑으로 저장됨 
		} else if(updown = "up"){
		$("#guestbook-list").prepend(str); //맨위로 저장 
		}else {$("#guestbook-list").append(str);}
}




</script>


</html>

