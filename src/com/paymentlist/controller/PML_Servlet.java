package com.paymentlist.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.paymentform.model.PMF_Service;
import com.paymentform.model.PMF_VO;
import com.paymentlist.model.*;

public class PML_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PML_Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("select".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String st_num = req.getParameter("St_num");

			if (st_num == null || st_num.trim().length() == 0) {
				errorMsgs.add("查詢的學號不得為空");
			} else {
				PMF_Service dao = new PMF_Service();
				List<PMF_VO> list = dao.getOnePMF(st_num);
				req.setAttribute("st_num", list);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PML.jsp");
				failureView.forward(req, res);
				return;
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PML.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if ("getAll".equals(action)) {
			PML_DAO dao = new PML_DAO();
			List<PML_VO> list = dao.getAll();

			HttpSession session = req.getSession();
			session.setAttribute("list", list);

			String url = "/payment/demo_v1.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
	}
}
