<%@page import="com.hk.itedu.VO.S_Comment_VO"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.itedu.VO.S_Board_VO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	S_Board_VO vo = (S_Board_VO) request.getAttribute("vo");
	//DetailServlet에서 set한 attribute를 가져옴
	if (vo == null) {
		//만약 값이 안넘어오면 객체화.
		vo = new S_Board_VO();
		vo.setBoard_No(0);
		vo.setBoard_Title("null");
		vo.setBoard_Content("null");
		vo.setRegdate("0000-00-00");
		vo.setCnt(0);
	}
	List<S_Comment_VO> lsv = (List<S_Comment_VO>) request
			.getAttribute("lsv");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="common.css" />
<meta charset="UTF-8">
<title>글 보기</title>
</head>
<body>
	<div class="container">
		<header>
			<h1>글 보기</h1>
		</header>
		<div class="mid">
			<nav>
				<ul class="navul1">
					<li><a href="HomeServlet">홈</a></li>
					<li><a href="ListServlet">리스트</a></li>
					<li><a href="WriteServlet">글쓰기</a></li>
				</ul>
			</nav>
			<section class="detailsec">
				<table>
					<tr>
						<th>게시번호</th>
						<th><%=vo.getBoard_No()%></th>
						<th>제목</th>
						<th class="tabletitle"><%=vo.getBoard_Title()%></th>
						<th>게시일</th>
						<th><%=vo.getRegdate()%></th>
						<th>카운트</th>
						<th><%=vo.getCnt()%></th>
					</tr>
					<tr>
						<td colspan="8">내용 : <br /> <%=vo.getBoard_Content()%></td>
					</tr>
					<%
						if (lsv != null && lsv.size() > 0) {
							for (S_Comment_VO cvo : lsv) {
					%>
					<tr class="comment">
						<th>댓글번호</th>
						<td><%=cvo.getComment_no()%></td>
						<th>댓글내용</th>
						<td><%=cvo.getComment_content()%></td>
						<th>댓글일시</th>
						<td><%=cvo.getRegdate()%></td>
					</tr>
					<%
						}
						}
					%>
				</table>
				<form action="CommentServlet?Board_No=<%=vo.getBoard_No()%>"
					method="post">
					<label for="comment_content">댓글</label><input
						name="comment_content" required="required" id="comment_content" autocomplete="off" type="text" /> <input
						type="submit" value="댓글 달기" />
				</form>
			</section>
		</div>
		<footer>CopyRight by HkItEdu</footer>
	</div>
</body>
</html>