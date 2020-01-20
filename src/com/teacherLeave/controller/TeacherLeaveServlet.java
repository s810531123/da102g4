package com.teacherLeave.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teacher.model.TeacherService;
import com.teacher.model.TeacherVO;
import com.teacherLeave.model.MailService;
import com.teacherLeave.model.TeacherLeaveService;
import com.teacherLeave.model.TeacherLeaveVO;
import com.teacherLeave.model.Util;

public class TeacherLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ServletContext context = null;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(context == null) {
			context = req.getServletContext();
			context.setAttribute("leaveReason", Util.leaveReason);
			context.setAttribute("leaveStatus", Util.leaveStatus);
		}	
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("進來了沒");
		
		//1. 從哪個頁面來:select_page.jsp -> addTeacherLeave.jsp -> TeacherLeaveServlet -> listAllTeacherLeave.jsp
		//2. 功能: 新增請假單
		//3. 結束後導向:listAllTeacherLeave.jsp
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// 理論上 登入的角色VO應該存在Session,所以這裡從req拿session，再取出VO，設定需要的資料
				HttpSession session = req.getSession();
				TeacherVO tp = (TeacherVO) session.getAttribute("tp");// 到時候要確認 用什麼方式拿到老師的VO

				String t_Num = null;
				if (tp != null) {
					t_Num = tp.getT_num();
				} else {
					throw new RuntimeException("請先登入");
				}

				Integer tl_Type = new Integer(req.getParameter("tl_Type").trim());
				String tl_Reason = req.getParameter("tl_Reason").trim();
				if (tl_Reason == null || tl_Reason.trim().length() == 0) {
					errorMsgs.add("請假事由勿空白");
				}

				java.sql.Date tl_Sdate = null;
				try {
					tl_Sdate = java.sql.Date.valueOf(req.getParameter("tl_Sdate").trim());
				} catch (IllegalArgumentException e) {
					tl_Sdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請設定請假起始日");
				}

				java.sql.Date tl_Edate = null;
				try {
					tl_Edate = java.sql.Date.valueOf(req.getParameter("tl_Edate").trim());
				} catch (IllegalArgumentException e) {
					tl_Edate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請設定請假結束日");
				}

				TeacherLeaveVO teacherLeaveVO = new TeacherLeaveVO();
				teacherLeaveVO.setTl_Type(tl_Type);
				teacherLeaveVO.setTl_Reason(tl_Reason);
				teacherLeaveVO.setTl_Sdate(tl_Sdate);
				teacherLeaveVO.setTl_Edate(tl_Edate);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacherLeaveVO", teacherLeaveVO);
					String url = "/back-end/TeacherLeave/addTeacherLeave.jsp"; // 跳轉回addTeacherLeave.jsp
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				TeacherLeaveService teacherLeaveSvc = new TeacherLeaveService();
				teacherLeaveVO = teacherLeaveSvc.addTeacherLeave(t_Num, tl_Sdate, tl_Edate, tl_Type, tl_Reason);
				List<TeacherLeaveVO> teacherLeaveVOList = teacherLeaveSvc.getAll();
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("teacherLeaveVOList", teacherLeaveVOList);
				String url = "/back-end/TeacherLeave/listAllTeacherLeave.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllTeacherLeave.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************/

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/TeacherLeave/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("tl_Id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入假單編號");
				}

				if (!errorMsgs.isEmpty()) {
					String url = "/back-end/TeacherLeave/select_page.jsp"; // 跳轉回select_page
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer tl_Id = null;
				try {
					tl_Id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("假單編號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					String url = "/back-end/TeacherLeave/select_page.jsp"; // 跳轉回select_page
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				TeacherLeaveService teacherLeaveSvc = new TeacherLeaveService();
				TeacherLeaveVO teacherLeaveVO = teacherLeaveSvc.getOneTeacherLeave(tl_Id);

				if (teacherLeaveVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					String url = "/back-end/TeacherLeave/select_page.jsp"; // 跳轉回select_page
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				//此部分使用 t_Num 去 Teacher表格拿取 TeacherVO 才能在頁面上顯示老師名字
				String t_Num = teacherLeaveVO.getT_Num();
				TeacherVO teacherVO = new TeacherService().getOneTeacher(t_Num);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("teacherLeaveVO", teacherLeaveVO);
				req.setAttribute("teacherVO", teacherVO); // 將老師物件送入 Jsp
				String url = "/back-end/TeacherLeave/listOneTeacherLeave.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneTeacherLeave.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/TeacherLeave/select_page.jsp");
				failureView.forward(req, res);
			}
		}



		if ("getOne_For_Update".equals(action)) {
			req.removeAttribute("errorMsgs");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer tl_Id = new Integer(req.getParameter("tl_Id").trim());
				/*************************** 2.開始查詢資料 *****************************************/
				TeacherLeaveService teacherLeaveSvc = new TeacherLeaveService();
				TeacherLeaveVO teacherLeaveVO = teacherLeaveSvc.getOneTeacherLeave(tl_Id);
				String t_Num = teacherLeaveVO.getT_Num();
				TeacherVO teacherVO = new TeacherService().getOneTeacher(t_Num);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("teacherVO", teacherVO);
				req.setAttribute("teacherLeaveVO", teacherLeaveVO);
				String url = "/back-end/TeacherLeave/update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/TeacherLeave/listAllTeacherLeave.jsp");
				failureView.forward(req, res);
			}
		}
		
		//1. 從哪個頁面來 
		//2. 功能
		//3. 結束後導向
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer tl_Id = new Integer(req.getParameter("tl_Id").trim());

				// 理論上 登入的角色VO應該存在Session,所以這裡從req拿session，再取出VO，設定需要的資料
				HttpSession session = req.getSession();
				TeacherVO tp = (TeacherVO) session.getAttribute("tp");// 到時候要確認 用什麼方式拿到老師的VO
				
				String t_Num = null;
				if (tp != null) {
					t_Num = tp.getT_num();
				} else {
					throw new RuntimeException("老師帳號未登入");
				}

				Integer tl_Type = new Integer(req.getParameter("tl_Type").trim());
				String tl_Reason = req.getParameter("tl_Reason").trim();
				if (tl_Reason == null || tl_Reason.trim().length() == 0) {
					errorMsgs.add("請假事由勿空白");
				}

				java.sql.Date tl_Sdate = null;
				try {
					tl_Sdate = java.sql.Date.valueOf(req.getParameter("tl_Sdate").trim());
				} catch (IllegalArgumentException e) {
					tl_Sdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請設定請假起始日");
				}

				java.sql.Date tl_Edate = null;
				try {
					tl_Edate = java.sql.Date.valueOf(req.getParameter("tl_Edate").trim());
				} catch (IllegalArgumentException e) {
					tl_Edate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請設定請假結束日");
				}

				java.sql.Date tl_App_Date = java.sql.Date.valueOf(req.getParameter("tl_App_Date").trim());

				Integer tl_Status = new Integer(req.getParameter("tl_Status").trim());

				TeacherLeaveVO teacherLeaveVO = new TeacherLeaveVO();
				teacherLeaveVO.setTl_Type(tl_Type);
				teacherLeaveVO.setTl_Reason(tl_Reason);
				teacherLeaveVO.setTl_Sdate(tl_Sdate);
				teacherLeaveVO.setTl_Edate(tl_Edate);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacherLeaveVO", teacherLeaveVO);
					String url = "/back-end/TeacherLeave/addTeacherLeave.jsp"; // 跳轉回addTeacherLeave.jsp
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				TeacherLeaveService teacherLeaveSvc = new TeacherLeaveService();
				teacherLeaveVO = teacherLeaveSvc.updateTeacherLeave(tl_Id, t_Num, tl_Sdate, tl_Edate, tl_Type,
						tl_Reason, tl_App_Date, tl_Status);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("teacherLeaveVO", teacherLeaveVO);
				String url = "/back-end/TeacherLeave/listAllTeacherLeave.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTeacherLeave.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/TeacherLeave/update.jsp");
				failureView.forward(req, res);
			}
		}
		// 暫時登入測試
		if (action.equals("login")) {
			String t_Num = req.getParameter("t_Num");
			TeacherService teacherSvc = new TeacherService();
			TeacherVO teacherVO = teacherSvc.getOneTeacher(t_Num);
			HttpSession session = req.getSession();
			session.setAttribute("teacherVO", teacherVO);

			String url = "/back-end/TeacherLeave/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		//園長審核假單
		if ("review".equals(action)) {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer tl_Id = new Integer(req.getParameter("tl_Id"));
			Integer tl_Status = new Integer(req.getParameter("tl_Status"));

			/*************************** 2.開始查詢資料 *****************************************/
			TeacherLeaveService teacherLeaveSvc = new TeacherLeaveService();
			TeacherLeaveVO teacherLeaveVO = teacherLeaveSvc.getOneTeacherLeave(tl_Id);
			teacherLeaveVO.setTl_Status(tl_Status);

			teacherLeaveSvc.updateTeacherLeave(teacherLeaveVO.getTl_Id(), teacherLeaveVO.getT_Num(),
					teacherLeaveVO.getTl_Sdate(), teacherLeaveVO.getTl_Edate(), teacherLeaveVO.getTl_Type(),
					teacherLeaveVO.getTl_Reason(), teacherLeaveVO.getTl_App_Date(), teacherLeaveVO.getTl_Status());
			
			//從teacherLeaveVO拿出老師編號
			String t_Num = teacherLeaveVO.getT_Num();
			
			//用老師編號，從 teacherService 拿到老師的物件
			TeacherService  teacherSvc = new TeacherService();
			TeacherVO teacherVO = teacherSvc.getOneTeacher(t_Num);

			//從老師的物件拿到老師的信箱
			String t_email= teacherVO.getT_email();
			
			//to 收件者信箱
		    String to = t_email;
		      
		    //信箱主旨
		    String subject = "「請假系統通知」";	      
		      
		    String ch_name = teacherVO.getT_name();
		    
		    //收件者內文
		    String messageText = "Hello " + ch_name + "：\n" +
		    				     "假單編號：" + tl_Id + "\n" +
		    				     "請假日期：" + teacherLeaveVO.getTl_Sdate()+ "〜" + teacherLeaveVO.getTl_Edate() +"\n"+
		    				     "假單審核「" + Util.leaveStatus.get(teacherLeaveVO.getTl_Status()) + "」" + "\n"+
		    				     "備註：如果你再請假，就不用來了^_^";

		    MailService mailService = new MailService();
		    mailService.sendMail(to, subject, messageText);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			action = "getAll_For_Display";
			/*************************** 其他可能的錯誤處理 *************/
		}
		
		//1. 從哪個頁面來:select_page.jsp -> TeacherLeaveServlet -> listAllTeacherLeave.jsp
		//2. 功能:查詢全部假單
		//3. 結束後導向:listAllTeacherLeave.jsp
		if ("getAll_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				/*************************** 2.開始查詢資料 *****************************************/
				TeacherLeaveService teacherLeaveSvc = new TeacherLeaveService();
				List<TeacherLeaveVO> teacherLeaveVOList = teacherLeaveSvc.getAll();
				List<TeacherVO> teacherVOList = new TeacherService().getAll(); // 拿出全部老師物件，並裝成List

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("teacherLeaveVOList", teacherLeaveVOList);
				req.setAttribute("teacherVOList", teacherVOList);// 將老師物件送入Jsp
				
				String url = "/back-end/TeacherLeave/listAllTeacherLeave.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllTeacherLeave.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/TeacherLeave/select_page.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
