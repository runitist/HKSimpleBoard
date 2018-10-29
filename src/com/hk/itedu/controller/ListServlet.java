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

@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	//게시판의 리스트 화면으로 가는 서블릿. list.jsp와 이어진다.
	private static final long serialVersionUID = 1L;
	
    public ListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<S_Board_VO> lvo = BoardDAO.get_S_Board_List();//BoardDAO의 리스트화 메서드를 실행해 넣어줌
		
		request.setAttribute("lvo", lvo);//갖고온 리스트를 attribute에 set함.
		
		RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
		rd.forward(request, response);//위에서 set한 attribute의 리스트를 list.jsp로 가져감.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	}

}
