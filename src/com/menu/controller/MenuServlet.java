package com.menu.controller;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.menu.model.*;

public class MenuServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("GetMenuByDate".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("mu_Date");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入日期");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Menu/menu_select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Menu/menu_select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MenuService menuSvc = new MenuService();
				List<MenuVO> listVO = menuSvc.getMenuBymu_Date(str);

				if (listVO == null || listVO.isEmpty()) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Menu/menu_select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("listVO", listVO); // 資料庫取出的VO物件,存入req
//				System.out.println("listVO size:"+listVO.size());

				String url = "/back-end/Menu/listMenuByDate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 GetResFormSelect.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Menu/menu_select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer mu_Num = new Integer(req.getParameter("mu_Num"));

				/*************************** 2.開始查詢資料 ****************************************/
				MenuService menuSvc = new MenuService();
				MenuVO menuVO = menuSvc.getOneMenu(mu_Num);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("menuVO", menuVO);
				String url = "/back-end/Menu/update_menu_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_menu_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Menu/getAllMenu.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_res_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer mu_Num = new Integer(req.getParameter("mu_Num").trim());

				String mu_Name = req.getParameter("mu_Name");
				if (mu_Name == null || mu_Name.trim().length() == 0) {
					errorMsgs.add("餐點名稱: 請勿空白");
				}

				String mu_Time = req.getParameter("mu_Time");
				if (mu_Time == null || mu_Time.trim().length() == 0) {
					errorMsgs.add("餐點時段: 請勿空白");
				}
				java.sql.Date mu_Date = null;
				try {
					mu_Date = java.sql.Date.valueOf(req.getParameter("mu_Date").trim());
				} catch (IllegalArgumentException e) {
					mu_Date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入餐點日期!");
				}

				// Send the use back to the form, if there were errors
				MenuVO menuVO = new MenuVO();
				menuVO.setMu_Num(mu_Num);
				menuVO.setMu_Name(mu_Name);
				menuVO.setMu_Time(mu_Time);
				menuVO.setMu_Date(mu_Date);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("menuVO", menuVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Menu/getAllMenu.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				MenuService menuSvc = new MenuService();
				menuVO = menuSvc.updateMenu(mu_Num, mu_Name, mu_Time, mu_Date);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("menuVO", menuVO);
				String url = "/back-end/Menu/getAllMenu.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Menu/getAllMenu.jsp");
				failureView.forward(req, res);
			}
		}

//
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mu_Name = req.getParameter("mu_Name");

				String mu_Time = req.getParameter("mu_Time");

				java.sql.Date mu_Date = null;
				try {
					mu_Date = java.sql.Date.valueOf(req.getParameter("mu_Date").trim());
				} catch (IllegalArgumentException e) {
					mu_Date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入餐點日期!");
				}
				
				
				MenuVO menuVO = new MenuVO();
				menuVO.setMu_Name(mu_Name);
				menuVO.setMu_Time(mu_Time);
				menuVO.setMu_Date(mu_Date);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("menuVO", menuVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Menu/MenuAdd.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 *****************************************/
				MenuService menuSvc = new MenuService();
				menuVO = menuSvc.addMenu(mu_Name, mu_Time, mu_Date);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("menuVO", menuVO);
				String url = "/back-end/Menu/getAllMenu.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Menu/MenuAdd.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer mu_Num = new Integer(req.getParameter("mu_Num"));

				/*************************** 2.開始刪除資料 ***************************************/
				MenuService menuSvc = new MenuService();
				menuSvc.deleteMenu(mu_Num);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Menu/getAllMenu.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Menu/getAllMenu.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
