package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.service.BookService;


@WebServlet("/searchTitle")
public class BookSearchTitleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BookSearchTitleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력받고 
		String keyword = request.getParameter("USER_KEYWORD"); 
		//String keyword = request.getParameter("keyword");
		// 로직처리를 Service에게 위임
		// Service를 생성해서 Service에게 일을 시킨 후 결과를 받아와요!!  
		// 객체만들어주기
		BookService service = new BookService(); 
		// 서비스가 가지고 있는 메서드 호출
		List<String> list = service.getBooksTitle(keyword); //키워드를 줘야함, 메서드 이름은 transaction으로 잡아줘야함
		// String 이 아닌 list로 받는다
		
		// 출력처리(JSON)
		response.setContentType("text/plain; charset=UTF8");
		PrintWriter out = response.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		
		out.print(json);
		//out.print("list에 JSON 문자열을 보내요!!!");
		// JSON 간단하게 만드는거 JACKSON Library 사용
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
