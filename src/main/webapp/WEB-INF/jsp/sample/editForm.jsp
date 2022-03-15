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
	$(document).ready(function() {
        $(document).on('click','#btn_list',function(e){
        	e.stopPropagation();
            e.preventDefault();
        	location.href="/example1/memberList";
        });
        
        $(document).on('click','#btn_edit',function(e){
        	e.stopPropagation();
            e.preventDefault();
        	location.href="/example1/memberEdit?memberNo=" + <c:out value='${member.memberNo}'/>;
        });
	});
</script>
<div class="container">
  <form:form id="form_detail" commandName="po">
    <div class="py-5 text-center">
        <h2>회원정보 상세</h2>
    </div>
    <div>
        <label for="memberNo">No.</label>
        <input type="text" id="memberNo" name="memberNo" class="form-control"
               value="<c:out value='${member.memberNo}'/>" readonly>
    </div>
    <div>
        <label for="memberId">Member ID</label>
        <input type="text" id="memberId" name="memberId" class="form-control"
               value="<c:out value='${member.memberId}'/>" readonly>
    </div>
    <div>
        <label for="firstName">First Name</label>
        <input type="text" id="firstName" name="firstName" class="form-control"
               value="<c:out value='${member.firstName}'/>" readonly>
    </div>
    <div>
        <label for="lastName"">Last Name</label>
        <input type="text" id="lastName" name="lastName" class="form-control"
               value="<c:out value='${member.lastName}'/>" readonly>
    </div>
    <div>
        <label for="address">Address</label>
        <input type="text" id="address" name="address" class="form-control"
               value="<c:out value='${member.address}'/>" readonly>
    </div>
    <div>
        <label for="phone">HP No.</label>
        <input type="text" id="phone" name="phone" class="form-control"
               value="<c:out value='${member.phone}'/>" readonly>
    </div>
    <hr class="my-4">
    <div class="row">
	    <div class="col">
	    	<button id="btn_edit" class="w-100 btn btn-primary btn-lg" onclick="#" type="submit">수정</button>
	    </div>
	    <div class="col">
	        <button id="btn_list" class="w-100 btn btn-secondary btn-lg" onclick="#" type="button">목록</button>
	    </div>
    </div>
  </form:form>
</div> <!-- /container -->
</body>
</html>