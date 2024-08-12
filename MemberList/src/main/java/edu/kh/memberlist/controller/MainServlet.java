package edu.kh.memberlist.controller;

import java.io.IOException;

import edu.kh.memberlist.service.MemberListService;
import edu.kh.memberlist.service.MemberListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class MainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 테스트용 샘플 데이터
//		req.setAttribute("str", "메인 페이지 값 전달됨");
		try {
		MemberListService service = new MemberListServiceImpl();
		String path = "/WEB-INF/views/main.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		
		} catch(Exception e ) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
