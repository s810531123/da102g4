package com.schedule.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.*;
import com.schedule.model.*;

@WebServlet("/testServlet")
public class testServlet extends HttpServlet {
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain");
			
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		if("123".equals(action)) {
			
			TScheduleService service = new TScheduleService();
			List<TScheduleVO> list = service.getAll();
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			
			String jsonStr = null;
			
			jsonStr =  gson.toJson(list);
			
			out.print(jsonStr);
//			out.write(jsonStr);
			out.flush();
		}
	
	
	}
	

}
