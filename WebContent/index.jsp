<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//예를들면, http://localhost:8077/SimpleBoard와 같이 url을 입력시 기본으로 실행되는 페이지.
	//WEB-INF의 web.xml에서 설정함.
	response.sendRedirect("HomeServlet");
%>