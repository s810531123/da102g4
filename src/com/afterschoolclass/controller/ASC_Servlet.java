package com.afterschoolclass.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.afterschoolclass.model.ASC_Service;
import com.afterschoolclass.model.ASC_VO;
import com.paymentproject.model.PMP_Service;



public class ASC_Servlet extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 送出
		if ("select".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String st_num = req.getParameter("St_num");		

			if (st_num == null || st_num.trim().length() == 0 ) {
				errorMsgs.add("查詢的學號不得為空");
			} else {
				ASC_Service dao = new ASC_Service();
				List<ASC_VO> list = dao.getOneStu(st_num);
				req.setAttribute("st_num", list);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/payment/ASC_Home.jsp");
				failureView.forward(req, res);
				return;				
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/payment/ASC_Home.jsp");
				failureView.forward(req, res);
				return;
			}

		}

	
	if ("select_front".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		String st_num = req.getParameter("St_num");		

		if (st_num == null || st_num.trim().length() == 0 ) {
			errorMsgs.add("查詢的學號不得為空");
		} else {
			ASC_Service dao = new ASC_Service();
			List<ASC_VO> list = dao.getOneStu(st_num);
			req.setAttribute("st_num", list);
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/payment/ASC_front.jsp");
			failureView.forward(req, res);
			return;				
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/payment/ASC_front.jsp");
			failureView.forward(req, res);
			return;
		}

	}

}
}
