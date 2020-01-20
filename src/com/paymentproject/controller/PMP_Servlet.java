package com.paymentproject.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paymentproject.model.PMP_Service;

public class PMP_Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		List<String> errorMsgs = new LinkedList<String>();

		String enameReg = "^[\\u4e00-\\u9fa5_a-zA-Z0-9_]{0,10}$";
		String numReg = "^[0-9]*$";

		
		req.setAttribute("errorMsgs", errorMsgs);
		// 新增
	try {
			if ("insert".equals(action)) {

				String pml_item = req.getParameter("Pml_item");
				String pml_money = req.getParameter("Pml_money");				
				
				System.out.println(pml_item +"/n"+ pml_money+ !pml_money.trim().matches(numReg)+!pml_money.trim().matches(numReg));
				
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PMP.jsp");
				
				if (pml_money == null || pml_money.trim().length() == 0) {
					errorMsgs.add("新增的金額不得為空");
				} else if (!pml_money.trim().matches(numReg)) {
					errorMsgs.add("不得輸入數字外的金額");
				}

				if (pml_item == null || pml_item.trim().length() == 0) {
					errorMsgs.add("新增的項目名稱不得為空");
				} else if (!pml_item.trim().matches(enameReg)) {
					errorMsgs.add("不得輸入特殊符號");
				}

				if (!errorMsgs.isEmpty()) {
					failureView.forward(req, res);		
					return;
				}
				int money = Integer.parseInt(pml_money.trim());
				System.out.println(money);
				PMP_Service pmpsvc = new PMP_Service();
				pmpsvc.addProject(pml_item.trim(), money);
				failureView.forward(req, res);

			}
		} catch (Exception e) {

			errorMsgs.add(e.getMessage());

			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PMP.jsp");

			failureView.forward(req, res);

		}

		// 刪除
		if ("delete".equals(action)) {

			Integer pml_num = new Integer(req.getParameter("pml_num"));
			PMP_Service pmpsvc = new PMP_Service();
			pmpsvc.deleteDept(pml_num);

			String url = "/back-end/payment/PMP.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 修改
		if ("update".equals(action)) {
			String pml_num = req.getParameter("pml_num");
			String pml_item = req.getParameter("pml_item");
			String pml_money = req.getParameter("pml_money");

			System.out.println(pml_money);
			
			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PMP.jsp");

			if (pml_money == null || pml_money.trim().length() == 0) {
				errorMsgs.add("新增的金額不得為空");
			} else if (!pml_money.trim().matches(numReg)) {
				errorMsgs.add("不得輸入數字外");
			}

			if (pml_item == null || pml_item.trim().length() == 0) {
				errorMsgs.add("新增的項目名稱不得為空");
			} else if (!pml_item.trim().matches(enameReg)) {
				errorMsgs.add("不得輸入特殊符號");
			}

			if (!errorMsgs.isEmpty()) {
				failureView.forward(req, res);
				return;
			}

			PMP_Service pmpsvc = new PMP_Service();
			pmpsvc.update(pml_num.trim(), pml_item.trim(), pml_money.trim());
			failureView.forward(req, res);

		}
	}
}