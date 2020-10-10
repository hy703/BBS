<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>

<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 
<script src="/resources/js/jquery-3.5.1.min.js"></script>
<title>게시판</title>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		var formObj = $("form[name='updateForm']");

		$(".btn btn-outline-danger").on("click", function() {
			event.preventDefault();
			location.href = "http://localhost:8080/";
		})

		$(".btn btn-outline-warning").on("click", function() {
			if (fn_valiChk()) {
				return false;
			}
			formObj.attr("action", "/board/update");
			formObj.attr("method", "post");
			formObj.submit();
		})
	})

	function fn_valiChk() {
		var updateForm = $("form[name='updateForm'] .form-control").length;
		for (var i = 0; i < updateForm; i++) {
			if ($(".form-control").eq(i).val() == "" || $(".form-control").eq(i).val() == null) {
				alert($(".form-control").eq(i).attr("title"));
				return true;
			}
		}
	}
</script>
<body>
	<nav>
	<%@ include file="../loginNavBar.jsp" %> 
	</nav>
	
	<div class="container">
		<hr />
		<h3> 게시글 수정</h3>


		<hr />

		<section id="container">
			<form name="updateForm" role="form" method="post"
				action="/board/update">
				<input type="hidden" name="bno" value="${update.bno}" readonly="readonly" />

						<div class="form-group">
							<label for="title" class="col-sm-2 control-label">제목</label>
							<input type="text" id="title" name="title" value="${update.title}" class="form-control" title="제목을 입력하세요 " /></td>
						</div>
						
						<div class="form-group">
							<label for="content" class="col-sm-2 control-label">내용</label> 
							<textarea id="content" name="content" class="form-control"><c:out value="${update.content}" /></textarea>
						</div>
						
						<div class="form-group">
							<label for="writer" class="col-sm-2 control-label">작성자</label>
							<input type="text" id="writer" name="writer" class="form-control" value="${update.writer}" readonly="readonly" />
						</div>
						
						<div class="form-group">
							<label for="regdate">작성날짜</label> 
							<fmt:formatDate value="${update.regdate}" pattern="yyyy-MM-dd" />
						</div>
				<div>
					<button type="submit" class="btn btn-outline-warning">저장</button>
					<button type="submit" class="btn btn-outline-danger">취소</button>
				</div>
			</form>
		</section>
		<hr />
	</div>
</body>
</html>