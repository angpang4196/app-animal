package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.QueryStringBuilder;

@WebServlet("/test")
public class TestController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		// 요청 인자를 보내기 위한 쿼리 스트링을 만들 때
		String upr_cd = "624000";
		String upkind = req.getParameter("242000");
		String pageNo = req.getParameter("1");
		String bgnde = req.getParameter("20230404");
		String endde = null;
		
		// 쉽게 만들기 위해 Map 을 활용한다.
		Map<String, String> params = new HashMap<>();
		params.put("upr_cd", upr_cd);
		params.put("upkind", upkind);
		params.put("pageNo", pageNo);
		params.put("bgnde", bgnde);
		
		String queryString = QueryStringBuilder.build(params);
		System.out.println(queryString);
	}
}
