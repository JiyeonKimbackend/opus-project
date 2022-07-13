package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Notice;
import com.my.exception.SelectException;
import com.my.repository.NoticeOracleRepository;
import com.my.repository.NoticeRepository;

/**
 * Servlet implementation class MainNoticeServlet
 * 공지사항 5개씩 출력
 */
@WebServlet("/mainnotice")
public class MainNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		// DB
		NoticeRepository repository = new NoticeOracleRepository();
		List<Notice> notices;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		try {
			notices = repository.selectNoticesByWritingtime(1, 5);
			map.put("status", 1);
			map.put("notices", notices);
		} catch (SelectException e) {
			e.printStackTrace();
//				notices = new ArrayList<>();
			map.put("status", 0);
			
		}
		
		String result = mapper.writeValueAsString(map);
		System.out.println(result);

		out.print(result);
	}
	
	
	
	}

