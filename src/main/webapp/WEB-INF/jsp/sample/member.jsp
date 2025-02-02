<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML>
<head>
    <meta charset="utf-8">
    <script type="text/javascript" src="/js/jquery-1.12.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.1.9.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css" />
</head>
<body>
<script>
    var memberId = $("#memberNo").val();
    
	$(document).ready(function() {
        $(document).on('click','#btn_list',function(e){
        	e.stopPropagation();
            e.preventDefault();
        	location.href="/example1/memberList";
        });
        
        $(document).on('click','#btn_delete',function(e){
        	e.stopPropagation();
            e.preventDefault();
        	var param = $("#form_detail").serializeArray();
        	var answer = confirm("정말 삭제하시겠습니까?");
        	
        	if(answer) {
	            $.ajax({
	                type : 'post',
	                url : "/example1/memberDelete",
	                data : param,
	                dataType : 'json'
	            }).done(function(result) {
	                if (result) {
	                	console.log(result.responseJSON);
	                	alert('삭제가 완료되었습니다.');
	                	
	                	location.href="/example1/memberList";
	                }
	            }).fail(function(result) {
	                if (result && result.status == 403) {
	                	alert('삭제에 실패하였습니다..(403)')
	                } else {
	                	console.log(result.responseJSON);
	                	alert('삭제에 실패하였습니다..')
	                }
	            });
        	}
        });
        
        $(document).on('click','#btn_edit',function(e){
        	e.stopPropagation();
            e.preventDefault();
            var param = $("#form_detail").serializeArray();
            $.ajax({
                type : 'post',
                url : "/example1/memberEdit",
                data : param,
                dataType : 'json'
            }).done(function(result) {
                if (result) {
                	console.log(result.responseJSON);
                	alert('등록에 성공하였습니다.');
                	
                	location.href="/example1/memberList";
                	
                } else {
                    alert('등록에 실패하였습니다.');
                }
            }).fail(function(result) {
                if (result && result.status == 403) {
                	alert('등록에 실패하였습니다.(403)')
                } else {
                	console.log(result.responseJSON);
                	alert('등록에 실패하였습니다.')
                }
            });
        });
	});
</script>
<div class="container">
  <form:form id="form_detail" commandName="po">
    <div class="py-5 text-center">
        <h2>회원정보 상세 1</h2>
    </div>
    <div>
        <label for="memberNo">No.</label>
        <input type="text" id="memberNo" name="memberNo" class="form-control"
               value="<c:out value='${member.memberNo}'/>" readonly>
    </div>
    <div>
        <label for="memberId">Member ID</label>
        <input type="text" id="memberId" name="memberId" class="form-control"
               value="<c:out value='${member.memberId}'/>" >
    </div>
    <div>
        <label for="firstName">First Name</label>
        <input type="text" id="firstName" name="firstName" class="form-control"
               value="<c:out value='${member.firstName}'/>" >
    </div>
    <div>
        <label for="lastName"">Last Name</label>
        <input type="text" id="lastName" name="lastName" class="form-control"
               value="<c:out value='${member.lastName}'/>" >
    </div>
    <div>
        <label for="address">Address</label>
        <input type="text" id="address" name="address" class="form-control"
               value="<c:out value='${member.address}'/>" >
    </div>
    <div>
        <label for="phone">HP No.</label>
        <input type="text" id="phone" name="phone" class="form-control"
               value="<c:out value='${member.phone}'/>" >
    </div>
    <hr class="my-4">
    <div class="row">
	    <div class="col">
	    	<button id="btn_edit" class="w-100 btn btn-primary btn-lg" onclick="#" type="submit">수정</button>
	    </div>
	    <div class="col">
	        <button id="btn_delete" class="w-100 btn btn-secondary btn-lg" onclick="#" type="submit">삭제</button>
	    </div>
	    <div class="col">
	        <button id="btn_list" class="w-100 btn btn-secondary btn-lg" onclick="#" type="button">목록</button>
	    </div>
    </div>
  </form:form>
</div> <!-- /container -->
</body>
</html>
