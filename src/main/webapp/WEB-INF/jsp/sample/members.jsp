<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<%--회원정보상세--%>
        $(document).on('click','a.detailLink',function(e){
        	e.stopPropagation();
            e.preventDefault();
     	    $("#memberNo").val($(this).data('member-no'));
            $('#form_detail').attr("action","/example1/memberDetail");
            $('#form_detail').submit();
        });
        
        <%--회원정보등록--%>
        $(document).on('click','#btn_regist',function(e){
        	e.stopPropagation();
            e.preventDefault();
        	location.href="/example1/memberRegist";
        });
	});
</script>
<div class="container" style="max-width: 600px">
	<form:form id="form_detail" action="/example1/userDetail"  commandName="so">
	    <input type="hidden" id="memberNo" name="memberNo" value=""/>
    </form:form>
    <div class="py-5 text-center">
        <h2>회원목록</h2>
    </div>
    <div class="row">
        <div class="col">
            <button id="btn_regist" class="w-100 btn btn-primary btn-lg" onclick="#" type="button">등록</button>
        </div>
    </div>
    <hr class="my-4">
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Address</th>
                <th>HP No.</th>
            </tr>
            </thead>
            <tbody id="tbody_member">
            <c:forEach items="${memberList.resultList}" var="member">
            <tr>
                <td><a href="#" class="detailLink" data-member-no='<c:out value="${member.memberNo}"/>'><c:out value="${member.memberId}"/></a></td>
                <td><a href="#" class="detailLink" data-member-no='<c:out value="${member.memberNo}"/>'><c:out value="${member.firstName}"/></a></td>
                <td><a href="#" class="detailLink" data-member-no='<c:out value="${member.memberNo}"/>'><c:out value="${member.lastName}"/></a></td>
                <td><c:out value="${member.address}"/></td>
                <td><c:out value="${member.phone}"/></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
