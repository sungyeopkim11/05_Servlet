package edu.kh.pratice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class Pratice2Setvlet extends HttpServlet {

	private List<String> nameList = null;
	
	public Pratice2Setvlet() {
		
		nameList = new ArrayList<String>();
		
		nameList.add("짱구");
		nameList.add("짱구");
		nameList.add("짱구");
		nameList.add("짱구");
		nameList.add("짱구");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String inputName = req.getParameter("inputName");
		
		int index = nameList.indexOf(inputName);
		
		
		if(index > -1) {
			String message = String.format("%s님은 %d번째 인덱스에 존재합니다",
					inputName, index);
			
			req.setAttribute("message", message);
			
			String path = "/WEB-INF/views/result.jsp";
			
			req.getRequestDispatcher(path).forward(req, resp);
		}
		
	}
}
