package com._class.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com._class.model.ClassService;
import com._class.model.ClassVO;

public class ClassServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/********* 1.接收請求參數，格式輸入錯誤之處理 *******************/
				String str = req.getParameter("cs_num");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入班級編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_manage/search_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************** 2.開始查詢資料 *************/
				ClassService classSvc = new ClassService();
				ClassVO classVO = classSvc.getOneClass(str);

				if (classVO == null) {
					errorMsgs.add("查無此資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_manage/search_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************** 3.查詢完成後轉交 *****************/
				req.setAttribute("classVO", classVO);
				String url = "/back-end/class_manage/search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/**************** 4.處理其他錯誤 *****************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_manage/search_page.jsp");
				failureView.forward(req, res);

			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/********** 1.接收請求參數，格式輸入錯誤之處理 **************/
				String cs_num = req.getParameter("cs_num");
				/***************** 2.開始查詢資料 ****************/
				ClassService classSvc = new ClassService();
				ClassVO classVO = classSvc.getOneClass(cs_num);
				/***************** 3.查詢完成後轉交 ********************/
				req.setAttribute("classVO", classVO);
				String url = "/back-end/class_manage/update_class_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************** 4.處理其他錯誤 ***********************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/class_manage/update_class_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/********** 1.接收請求參數，格式輸入錯誤之處理 **************/
				String cs_num = req.getParameter("cs_num").trim();

				String cs_name = req.getParameter("cs_name");
				if (cs_name == null || cs_name.trim().length() == 0) {
					errorMsgs.add("班級姓名 : 請勿空白");
				}

//				if (t_num_1 == null || t_num_1.trim().length() == 0) {
//					errorMsgs.add("導師編號_1 : 請勿空白");
//				}

//				if (t_num_2 == null || t_num_2.trim().length() == 0) {
//					errorMsgs.add("導師編號_2 : 請勿空白");
//				}

				String t_num_1 = req.getParameter("t_num_1");
				String t_num_2 = req.getParameter("t_num_2");
				if (t_num_1.equals(t_num_2)) {
					errorMsgs.add("班導師請勿重複");
				}

				Integer cs_years = null;
				try {
					cs_years = new Integer(req.getParameter("cs_years").trim());
				} catch (NumberFormatException e) {
					cs_years = 108;
					errorMsgs.add("學年度請填數字");
				}

				ClassVO classVO = new ClassVO();
				classVO.setCs_num(cs_num);
				classVO.setT_num_1(t_num_1);
				classVO.setT_num_2(t_num_2);
				classVO.setCs_name(cs_name);
				classVO.setCs_years(cs_years);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classVO", classVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/class_manage/update_class_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************** 2.開始修改資料 ****************/
				ClassService classSvc = new ClassService();
				classVO = classSvc.updateClass(cs_num, t_num_1, t_num_2, cs_name, cs_years);
				/***************** 3.修改完成後轉交 *****************/
				req.setAttribute("classVO", classVO);
				String url = "/back-end/class_manage/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/****************** 4.處理其他錯誤 *****************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/class_manage/update_class_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/********** 1.接收請求參數，格式輸入錯誤之處理 **************/
				String cs_name = req.getParameter("cs_name");
				if (cs_name == null || cs_name.trim().length() == 0) {
					errorMsgs.add("班級名稱 : 請勿空白");

				}
				String t_num_1 = req.getParameter("t_num_1");
				String t_num_2 = req.getParameter("t_num_2");
				if (t_num_1.equals(t_num_2)) {
					errorMsgs.add("班導師選取重複");
				}

				Integer cs_years = null;
				try {
					cs_years = new Integer(req.getParameter("cs_years").trim());
				} catch (NumberFormatException e) {

					cs_years = 108;
					errorMsgs.add("學年度 : 未填寫 或 格式錯誤");
				}

				ClassVO classVO = new ClassVO();

				classVO.setT_num_1(t_num_1);
				classVO.setT_num_2(t_num_2);
				classVO.setCs_name(cs_name);
				classVO.setCs_years(cs_years);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classVO", classVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_manage/add_class.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************** 2.開始新增資料 ****************/
				ClassService classSvc = new ClassService();
				classVO = classSvc.addClass(t_num_1, t_num_2, cs_name, cs_years);
				/***************** 3.新增完成後轉交 *****************/
				String url = "/back-end/class_manage/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/****************** 4.處理其他錯誤 *****************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_manage/add_class.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/********** 1.接收請求參數，格式輸入錯誤之處理 **************/
				String cs_num = req.getParameter("cs_num");
				if (cs_num.equals("C001") || cs_num.equals("C002") || cs_num.equals("C003")) {
					errorMsgs.add("請勿刪除預設班級(C001、C002、C003)");
				}
				/***************** 2.開始刪除資料 ****************/
				ClassService classSvc = new ClassService();
				classSvc.deleteClass(cs_num);
				/***************** 3.刪除完成後轉交 *****************/
				String url = "/back-end/class_manage/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/****************** 4.處理其他錯誤 *****************/
			} catch (Exception e) {
				// errorMsgs.add("資料刪除失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_manage/select_page.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
