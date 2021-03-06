<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<script src="/resources/js/jquery-3.5.1.min.js"></script>
<title>게시판</title>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		var formObj = $("form[name='updateForm']");

		$(".cancel_btn").on("click", function() {
			location.href = "/board/readView?bno=${replyUpdate.bno}"
		})

	})
</script>
<body>

	<div id="root">
		<header>
			<h1>게시판</h1>
		</header>
		<hr />


		<hr />

		<section id="container">
			<form name="updateForm" role="form" method="post"
				action="/board/replyUpdate">
				<input type="hidden" name="bno" value="${replyUpdate.bno}"
					readonly="readonly" /> <input type="hidden" id="rno" name="rno"
					value="${replyUpdate.rno}" />
				<table>
					<tbody>
						<tr>
							<td><label for="content">댓글 내용</label><input type="text"
								id="content" name="content" value="${replyUpdate.content}" /></td>
						</tr>

					</tbody>
				</table>
				<div>
					<button type="submit" class="update_btn">저장</button>
					<button type="button" class="cancel_btn">취소</button>
				</div>
			</form>
		</section>
		<hr />
	</div>
</body>
</html>