package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/chicken")
public class ChickenServlet extends HttpServlet {

	// HttpServletRequest  : 요청자 정보, 전달 받은 데이터가 담긴 객체
	
	// HttpServletResponse : 응답 밥법을 제공하는 객체 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청 시 제출된 값(파라미터) 모두 얻어오기

		// 1) 값이 하나인 경우 : String getParameter("name")
		
		String chicken      = req.getParameter("chicken"); // 치킨
		String type         = req.getParameter("type");    // 뼈 / 순산
		String ordererName    = req.getParameter("ordererName");       // 주문자명
		String ordererAddress = req.getParameter("ordererAddress"); // 주소
		
		// 값이 여러 개인 이유
		// String[] req.getParameterValues("name")
		// -> 제출된 값 중 name속성값이 일치 하는 것을 모두 모아
		//    하나의 String[]로 반환
		
		// 단, name 속성 값이 일치하는게 없을 경우
		//     "null" 반환
		String[] options = req.getParameterValues("opt");
		
		// -------------------------------------------------------------------
		
		
		/* 선택한 치킨, 뼈/순살, 옵션에 따라 가격 책정하기 */
		int price = 0; // 가격 누적
		
		switch(chicken)	{
		case "후라이드"     :  price += 10000; break;
		case "양념"         :  price += 11000; break;
		case "간장", "마늘" :  price += 12000; break;
		}
		
		// 순살인 경우 2000 추가
		if(type.equals("boneless")) price += 2000;
		
		// 추가된 옵션이 있을 경우
		if(options != null) {
			
			for(String opt : options) {
				switch(opt) {
				case "치킨무" : price += 500;  break;
				case "콜라"   : price += 2000; break;
				case "치즈볼" : price += 3000; break;
				}
			}
			
			// -------------------------------------------------------
			
			// 응답 HTML 만들어서 출력하기
			
			// 응답 데이터의 형식/문자 인코딩 지정
			resp.setContentType("text/html; charset=utf-8");
			
			// 출력용 스트림 얻어오기
			PrintWriter out = resp.getWriter();
			
			// HTML 코드 모아둘 객체
			StringBuilder sb = new StringBuilder();
			
			sb.append("""
			<!DOCTYPE html>
			<html>
			 <head>
			  <meta charset= 'utf-8'>
			  <title>""");
			
			sb.append(ordererName + "님의 주문 영수증");
			sb.append("</title>");
			sb.append("</head>");
			
			
			
			sb.append("<body>");
			
			sb.append("<h3> 주문자명 : " + ordererName + "</h3>");
			
			sb.append("<h3> 주소 : " + ordererAddress + "</h3>");
			
			sb.append("<ul>");
				sb.append("<li>치킨 :" + chicken + "</li>");
				sb.append("<li>뼈/순살 :" + (type.equals("bone") ? "뼈" : "순살") + "</li>");
				
				// 옵션이 있을 경우
				if(options != null) {
					sb.append("<li>선택한 옵션 : ");
					
					// String.join() : String[] -> String 한 줄로 변환
					sb.append(String.join(" / ", options));
					// 치킨무 / 콜라 / 치즈볼
					
					sb.append("</li>");
				}
			sb.append("</ul>");
			
			sb.append("<h3>금액 : " + price + "</h3>");
			
			sb.append("</body>");
			sb.append("</html>");
			
			out.write(sb.toString()); // HTML 클라이언트에게 출력하기
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
