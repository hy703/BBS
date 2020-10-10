<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>

	
	
	
		<br/>

		<section id="container">
			<form role="form" method="post" action="/board/write">
				<table class="table table-hover">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>등록일</th>
						<th>추천수</th>
						<th>조회수</th>
					</tr>

					<c:forEach items="${list}" var="list">
						<tr>
							<td><c:out value="${list.bno}" /></td>
							<td>
								<a href="/board/readView?bno=${list.bno}"> 
									<c:out value="${list.title}" />
								</a>
							</td>
							<td><c:out value="${list.writer}" /></td>
							<td><fmt:formatDate value="${list.regdate}"
									pattern="yyyy-MM-dd" /></td>
									<td><c:out value="${list.gerecommend}" /></td>
									<td><c:out value="${list.hit}" /></td>
						</tr>
					</c:forEach>

				</table>
				
			</form>
			
		</section>
		

		<hr />


</body>
</html>