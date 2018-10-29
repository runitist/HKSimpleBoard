<%@page import="com.hk.itedu.DAO.BoardDAO"%>
<%@page import="com.hk.itedu.VO.S_Board_VO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int maxno = BoardDAO.getMaxNo()+1;
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="common.css" />
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<div class="container">
		<header>
			<h1>글쓰기</h1>
		</header>
		<div class="mid">
			<nav>
				<ul class="navul1">
					<li><a href="HomeServlet">홈</a></li>
					<li><a href="ListServlet">리스트</a></li>
					<li><a href="WriteServlet">글쓰기</a></li>
				</ul>
			</nav>
			<section class="writesec">
				<form action="WriteServlet" method="post">
					<label for="Board_No">게시번호(자동생성)</label> <input id="Board_No"
						name="Board_No" type="text" required="required"
						readonly="readonly" value="<%=maxno%>" /><br /> <label
						for="Board_Title">제목</label> <input id="Board_Title"
						name="Board_Title" type="text" autofocus="autofocus"
						autocomplete="off" required="required" /><br /> <label
						for="Board_Content">내용</label>
					<textarea name="Board_Content" id="Board_Content" cols="30"
						rows="10" required="required"></textarea>
					<br /> <label for="Regdate">게시일</label> <input id="Regdate"
						name="Regdate" type="date" required="required" /><br />
					<button type="submit">글쓰기</button>
					<a href="ListServlet"><button type="button">조회</button></a>
				</form>
			</section>
		</div>
		<footer>CopyRight by HkItEdu</footer>
	</div>
</body>
</html>