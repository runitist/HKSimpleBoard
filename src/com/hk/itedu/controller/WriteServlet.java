package com.hk.itedu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hk.itedu.DAO.BoardDAO;
import com.hk.itedu.VO.S_Board_VO;

@WebServlet("/WriteServlet")
public class WriteServlet extends HttpServlet {
	//게시판의 글쓰기 화면으로 가는 서블릿. write.jsp와 이어진다.
	private static final long serialVersionUID = 1L;
	
    public WriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("write.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//write.jsp의 폼태그에서 보내온 post 요청을 처리하기위한 메서드.
		request.setCharacterEncoding("UTF-8");
		S_Board_VO vo = new S_Board_VO();
		
		int Board_No = Integer.parseInt(request.getParameter("Board_No"));//폼태그에서 넘어오는 파라미터를 받아옴.
		String Board_Title = request.getParameter("Board_Title");
		String Board_Content = request.getParameter("Board_Content");
		String Regdate = request.getParameter("Regdate");
		
		vo.setBoard_No(Board_No);//받아온 파라미터를 vo객체에 넣어줌.
		vo.setBoard_Title(Board_Title);
		vo.setBoard_Content(Board_Content);
		vo.setRegdate(Regdate);
		
		BoardDAO.insert_S_Board(vo);//BoardDAO의 글쓰기 메서드를 실행시킨다.
		
		response.sendRedirect("ListServlet");//글쓰기가 완료되면 ListServlet으로 redirect한다.
	}

}
