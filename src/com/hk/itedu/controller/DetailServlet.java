package com.hk.itedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hk.itedu.DAO.BoardDAO;
import com.hk.itedu.VO.S_Board_VO;
import com.hk.itedu.VO.S_Comment_VO;

@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	//게시판의 리스트의 a링크를 누른 화면으로 가는 서블릿. detail.jsp와 이어진다.
	private static final long serialVersionUID = 1L;
	int bn = 0;
 
    public DetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Board_No = Integer.parseInt(request.getParameter("Board_No"));//각 a테그의 ?url파라미터로 받아온 게시번호를 가져옴.
		S_Board_VO vo = BoardDAO.get_S_Board(Board_No);//가져온 게시번호를 사용해 DB의 글 본문을 가져옴.
		List<S_Comment_VO> lsv = BoardDAO.getComment(Board_No);
		
		if(bn != Board_No){
			BoardDAO.updateCnt(Board_No);//해당글 방문시 방문수를 늘려주기 위한 메서드 실행
			vo.setCnt(vo.getCnt()+1);
			bn = Board_No;
		}
		request.setAttribute("vo", vo);//가져온 글 본문의 VO 객체를 detail.jsp로 넘겨줌.
		request.setAttribute("lsv", lsv);
		RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
