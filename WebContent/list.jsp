<%@page import="com.hk.itedu.DAO.BoardDAO"%>
<%@page import="com.hk.itedu.VO.S_Board_VO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<S_Board_VO> lvo = (List<S_Board_VO>) request.getAttribute("lvo");
	//ListServlet에서 set한 attribute를 get으로 가져옴. 아무것도 없으면 null.
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="common.css" />
<meta charset="UTF-8">
<title>리스트</title>
</head>
<body>
	<div class="container">
		<header>
			<h1>리스트</h1>
		</header>
		<div class="mid">
			<nav>
				<ul class="navul1">
					<li><a href="HomeServlet">홈</a></li>
					<li><a href="ListServlet">리스트</a></li>
					<li><a href="WriteServlet">글쓰기</a></li>
				</ul>
			</nav>
			<section class="listsec">
				<table class="listtable">
					<tr>
						<th class="gno">게시번호</th>
						<th class="gna">제목</th>
						<th class="gda">게시일</th>
						<th class="gcnt">방문수</th>
					</tr>

					<%
						if (lvo != null && lvo.size() > 0) {
							for (S_Board_VO vo : lvo) {
					%>
					<tr>
						<th><%=vo.getBoard_No()%></th>
						<td><a href="DetailServlet?Board_No=<%=vo.getBoard_No()%>"><%=vo.getBoard_Title()%>(<%=BoardDAO.getCommentCnt(vo.getBoard_No()) %>)</a></td>
						<th><%=vo.getRegdate()%></th>
						<th><%=vo.getCnt()%></th>
					</tr>
					<%
						}
						}
					%>
				</table>
			</section>
		</div>
		<footer>CopyRight by HkItEdu</footer>
	</div>
</body>
</html>