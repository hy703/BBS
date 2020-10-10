<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<!-- jquery 사용 -->
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


   <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>



<title>게시판</title>
</head>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
/* 						var check = $('#check').val();

						if(check==0) {
							alert("관리자 권한 부여");
							} */
						
						var formObj = $("form[name='readForm']");

						// 수정 
						$("#update_btn").on("click", function() {
							formObj.attr("action", "/board/updateView");
							formObj.attr("method", "get");
							formObj.submit();
						})

						// 삭제
						$("#delete_btn").on("click", function() {

							var deleteYN = confirm("삭제하시겠습니까?");
							if (deleteYN == true) {
								formObj.attr("action", "/board/delete");
								formObj.attr("method", "post");
								formObj.submit();
							}

						})

						// 목록
						$("#list_btn").on("click", function() {

							location.href = "home";
						})

						//댓글작성 처리
						$(".replyWriteBtn").on("click", function() {
							var formObj = $("form[name='replyForm']");
							formObj.attr("action", "/board/replyWrite");
							formObj.submit();
						});

						//일반 추천
						$(".gerecommend_btn")
								.on(
										"click",
										function() {
											location.href = "/board/gerecommend?bno=${read.bno}";
										});

						//전문가 추천
						$(".marecommend_btn")
								.on(
										"click",
										function() {
											location.href = "/board/marecommend?bno=${read.bno}";
										});

						//댓글 수정 View
						$("#replyUpdateBtn")
								.on(
										"click",
										function() {
											location.href = "/board/replyUpdateView?bno=${read.bno}"
													+ "&rno="
													+ $(this).attr("data-rno");
										});

						$("#replyDeleteBtn")
								.on(
										"click",
										function() {
											alert("클릭");
											$.ajax({
														url : "/board/replyDelete",
														type : "post",
														dataType : "text",
														data : {
															"rno" : $(this)
																	.attr(
																			"data-rno")
														},
														success : function(data) {
															location.href = "/board/readView?bno=${read.bno}"
														},
														error : function(
																request,
																status, error) {
															alert("code = "
																	+ request.status
																	+ " message = "
																	+ request.responseText
																	+ " error = "
																	+ error);
															// 실패 시 처리
														}

													})
										});

					})
</script>


<body>
<input type="hidden" id="check" value="${check.rule}" />
	<nav>
		<%@ include file="../loginNavBar.jsp" %> 
	</nav>
	<div class="container">
		<hr />
		<h3> 상세 페이지</h3>
	

		<section id="container">
			<form name="readForm" role="form" method="post">
				<input type="hidden" id="bno" name="bno" value="${read.bno}" />
			</form>
			
					<div class="form-group">
						<label for="title" class="col-sm-2 control-label">제목</label>
						<input type="text" id="title" name="title" class="form-control" value="${read.title}" />
					</div>
					
					<div class="form-group">
						<label for="content" class="col-sm-2 control-label">내용</label> 
						<textarea id="content" name="content" class="form-control"><c:out value="${read.content}" /></textarea>
					</div>
					
					<div class="form-group">
						<label for="writer" class="col-sm-2 control-label">작성자</label>
						<input type="text" id="writer" name="writer" class="form-control" value="${read.writer}" />
					</div>
					
					<div class="form-group">
						<label for="regdate" class="col-sm-2 control-label">작성날짜</label> 
						<fmt:formatDate	value="${read.regdate}"  pattern="yyyy-MM-dd" />
					</div>
			
			<div>
				<c:choose>
					<c:when test="${check.rule == 1}">
						<button type="submit" class="btn btn-outline-primary">목록</button>
					</c:when>
					<c:when test="${check.rule == 0}">
					<div style="margin-left: 720px;">
						<button type="submit" id="update_btn" class="btn btn-outline-warning">수정</button>
						<button type="submit" id="delete_btn" class="btn btn-outline-danger">삭제</button>
						<button type="submit" id="list_btn" class="btn btn-outline-primary">목록</button>
					</div>
					</c:when>


				
			
				</c:choose>
			</div>

	<hr/>
			<!-- 댓글 -->
			<div id="reply">
				<ol class="replyList">
					<c:forEach items="${replyList}" var="replyList">
						<li>
							<p>
								작성자 : ${replyList.writer}<br /> 작성 날짜 :
								<fmt:formatDate value="${replyList.regdate}"
									pattern="yyyy-MM-dd" />
							</p>

							<p>${replyList.content}</p>
							<div>
							<c:if test="${replyList.writer eq check.user_id }">
								<button type="button" id="replyUpdateBtn" class="btn btn-outline-warning" data-rno="${replyList.rno}">수정</button>
								<button type="button" id="replyDeleteBtn" class="btn btn-outline-danger" data-rno="${replyList.rno}">삭제</button>
							</c:if>
							</div>

						</li>
						<hr/>
					</c:forEach>
				</ol>
			</div>

			<!-- 댓글 작성 -->
			<form name="replyForm" method="post" class="form-horizontal">
				<input type="hidden" id="bno" name="bno" value="${read.bno}" />

				<div class="form-group">
					<label for="writer" class="col-sm2 control-label">댓글 작성자</label>
						<div class="col-sm-10">
							<input type="text" id="writer" name="writer" class="form-control" value="${check.user_id}"/> 
						</div>
				</div>
				
				<div class="form-group">
					<label for="content" class="col-sm2 control-label">댓글 내용</label>
						<div class="col-sm-10">
							<input type="text" id="content" name="content" class="form-control" />
						</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="replyWriteBtn btn btn-success">작성</button>
					</div>
				</div>
			</form>

		</section>
		<hr />
	</div>
</body>
</html>