<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- jquery 사용 -->
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


   <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<meta charset="UTF-8">
<title>게시판</title>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		var formObj = $("form[name='writeForm']");
		$("#write_btn").on("click", function() {

			/* -------------게시글 중복체크---------- */
			$.ajax({
				url : "titleChk",
				type : "post",
				dataType : "json",
				data : {
					"title" : $("#title").val()
				},
				success : function(data) {
					if (data == 1) {
						alert("중복된 게시글.");
						location.href = "/board/writeView";
					}
				}
			})
			/* -------------게시글 중복체크---------- */

			if (fn_valiChk()) {
				return false;
			}
			formObj.attr("action", "/board/write");
			formObj.attr("method", "post");
			formObj.submit();
		});
	})
	function fn_valiChk() {
		var regForm = $("form[name='writeForm'] .form-control").length;
		for (var i = 0; i < regForm; i++) {
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
			<h3> 게시글 작성 페이지</h3>

		<hr />

		<section id="container">
			<form name="writeForm" method="post" action="/board/write">
		
					<div class="form-group">
						<label for="title" class="col-sm-2 control-label">제목</label>
						<input type="text" id="title" name="title" class=form-control title="제목을 입력하세요 " />
					</div>	
					<div class="form-group">		
						<label for="content" class="col-sm-2 control-label">내용</label> 
						<textarea id="content" name="content" class="form-control" title="내용을 입력하세요 "></textarea>
					</div>
					
					<div class="form-group">
						<label for="writer" class="col-sm-2 control-label">작성자</label>
						<input type="text" id="writer" name="writer" class="form-control" title="작성자를 입력하세요 " value="${check.user_id}" />
					</div>	
						<button id="write_btn" class="btn btn-outline-primary" type="submit">작성</button>

				
			</form>
		</section>
		<hr />
	</div>
</body>
</html>