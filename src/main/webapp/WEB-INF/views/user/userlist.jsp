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
	<div id="root">
		<header>
			<h1>게시판</h1>
		</header>
		<hr />


		<hr />

		<section id="container">
		
				<table>
					<tr>
						<th>유저이름</th>
						<th>유형</th>

					</tr>

					<c:forEach items="${userlist}" var="list">
						<tr>
							<td><c:out value="${list.user_id}" /></td>
							<td><c:if test="${list.rule == 1 }">일반회원</c:if>
							<c:if test="${list.rule == 0 }">전문가회원</c:if></td>
						</tr>
					</c:forEach>

				</table>
		</section>
		<hr />
	</div>
</body>
</html>