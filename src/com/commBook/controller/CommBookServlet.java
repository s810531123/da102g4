package com.commBook.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commBook.model.CommBookService;
import com.commBook.model.CommBookVO;
import com.student.model.StudentService;
import com.student.model.StudentVO;
import com.teacher.model.TeacherService;
import com.teacher.model.TeacherVO;
import com.teacherLeave.model.TeacherLeaveService;
import com.teacherLeave.model.TeacherLeaveVO;

public class CommBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static ServletContext context = null;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("聯絡簿Servlet進來了");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		//1. 從哪個頁面來:(select_page.jsp) include (getAllRecentDay.jsp) 
		//2. 功能:查詢最近二筆資料
		//3. 結束後導向:select_page.jsp
		if ("showTwoDayFromTeacher".equals(action)) {
			
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
			try {	
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String date = req.getParameter("comm_Date");
				
				// 判斷date == null，代表Parameter沒有拿到date資料，所以date等於今天的日期
				if (date == null || date.trim().length() == 0) {
	//				date = "2019-09-25";
	//			    System.currentTimeMillis():系統類別底下的一個方法，拿到當下時間的毫秒數
					date = new java.sql.Date(System.currentTimeMillis()).toString(); 
				}
				// session內可取得登入老師的資訊
				HttpSession session = req.getSession();
				TeacherVO tp = (TeacherVO) session.getAttribute("tp");
				String t_num = null;
	
				if (tp != null) {
					t_num = tp.getT_num();
				} else {//沒登入時要怎麼處理
					errorMsgs.add("請先登入");
				}
				System.out.println(t_num);
	
				/*************************** 2.開始查詢資料 *****************************************/
				CommBookService commBookSvc = new CommBookService();// 這裡要找出 老師底下所有學生的全部聯絡簿資訊
																	// 1. 老師編號 -> 班級編號 -> 學生名單 2. 日期
																	// 拿到的資料是 Map<StudentVO, List<CommBookVO>>
	
				Map<StudentVO, List<CommBookVO>> allStudentCommBook = commBookSvc.getAllStudentCommBook(t_num, date);
				
				
				context = req.getServletContext();
				
				Set<String> allCommBookDays = commBookSvc.getCommBookDays();

				if(allCommBookDays.contains(new java.sql.Date(System.currentTimeMillis()).toString())) {
					context.setAttribute("addCommBook", true);
					System.out.println(true);
				}else {
					context.setAttribute("addCommBook", false);
					System.out.println(false);
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("allStudentCommBook", allStudentCommBook);
				
				String url = "/back-end/CommBook/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}catch(Exception e) {
				String url = "/back-end/CommBook/select_page.jsp";
				errorMsgs.add(e.getMessage());
				System.out.println(errorMsgs.size());
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		} 
		
		//1. 從哪個頁面來:select_page.jsp
		//2. 功能:指定日期拿到全部學生的聯絡簿資料
		//3. 結束後導向:getAllStudent.jsp
//		if ("getDesignationDay".equals(action)) {			
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {	
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String date = req.getParameter("comm_Date");
//				
//				// 判斷date == null，代表Parameter沒有拿到date資料，所以date等於今天的日期
//				if (date == null || date.trim().length() == 0) {
//					date = new java.sql.Date(System.currentTimeMillis()).toString(); 
//				}
//				// session內可取得登入老師的資訊
//				HttpSession session = req.getSession();
//				TeacherVO tp = (TeacherVO) session.getAttribute("tp");
//				String t_num = null;
//	
//				if (tp != null) {
//					t_num = tp.getT_num();
//				} else {
//					errorMsgs.add("請先登入");
//				}
//				System.out.println(t_num);
//	
//				/*************************** 2.開始查詢資料 *****************************************/
//				CommBookService commBookSvc = new CommBookService();	
//				Map<StudentVO, List<CommBookVO>> allStudentCommBook = commBookSvc.getAllStudentCommBook(t_num, date);
//	
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("allStudentCommBook", allStudentCommBook);	
//				String url = "/back-end/CommBook/getAllStudent.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				return;
//			} catch(Exception e) {
//				String url = "/back-end/CommBook/select_page.jsp";
//				errorMsgs.add(e.getMessage());
//				System.out.println(errorMsgs.size());
//				RequestDispatcher failureView = req.getRequestDispatcher(url);
//				failureView.forward(req, res);
//			}
//		} 
		
		if("insert".equals(action)) {
			System.out.println("進入insert");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				HttpSession session = req.getSession();
				TeacherVO tp = (TeacherVO) session.getAttribute("tp");// 到時候要確認 用什麼方式拿到老師的VO

				String t_Num = null;
				if (tp != null) {
					t_Num = tp.getT_num();
				} else {
					throw new RuntimeException("請先登入");
				}

				java.sql.Date comm_Date = new java.sql.Date(System.currentTimeMillis());
//				java.sql.Date comm_Date = java.sql.Date.valueOf("2019-09-26");

				String comm_T_Msg = req.getParameter("comm_T_Msg").trim();
				if (comm_T_Msg == null || comm_T_Msg.trim().length() == 0) {
					errorMsgs.add("老師的話請勿空白");
				}
				
				CommBookVO commBookVO = new CommBookVO();
				commBookVO.setComm_T_Msg(comm_T_Msg);
		
				if (!errorMsgs.isEmpty()) {
					String url = "/back-end/CommBook/select_page.jsp"; // 跳轉回addCommBook.jsp
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 ***********************************************/
				CommBookService commBookSvc = new CommBookService();
				commBookSvc.setAllStudentCommBook(t_Num, comm_Date, comm_T_Msg);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("commBookVO", commBookVO);
				context = req.getServletContext();
				context.setAttribute("addCommBook", true);
				String url ="/back-end/CommBook/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 ****************/	
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/CommBook/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("updateT_Msg".equals(action)) {

			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {				
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String comm_T_Msg = req.getParameter("comm_T_Msg").toString();
				if(comm_T_Msg.length()==0) {
					errorMsgs.add("內容不可空白");
				}
				
				TeacherVO tp = (TeacherVO) req.getSession().getAttribute("tp");
				String t_Num = null;
				if(tp!=null) {
					t_Num = tp.getT_num();
				}else {
					errorMsgs.add("老師請先登入");
				}
				
				String comm_Date = new java.sql.Date(System.currentTimeMillis()).toString();
				
				if(!errorMsgs.isEmpty()) {
					String url = "/back-end/CommBook/select_page.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始查詢資料 ***********************************************/
				CommBookService commBookSvc = new CommBookService();
				commBookSvc.updateCommBookMsg(t_Num, comm_Date, comm_T_Msg);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				String url = "/back-end/CommBook/select_page.jsp";
				RequestDispatcher successfulView = req.getRequestDispatcher(url);
				successfulView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add(e.getMessage());
				String url = "/back-end/CommBook/select_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		
		
		if("update_Res".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {				
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String comm_Res = req.getParameter("comm_Res").trim();
				Integer comm_Id  = new Integer(req.getParameter("comm_Id").trim());

				if(!errorMsgs.isEmpty()) {
					String url = "/back-end/CommBook/select_page.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始查詢資料 ***********************************************/
				CommBookService commBookSvc = new CommBookService();
				commBookSvc.updateCommBookTRes(comm_Id, comm_Res);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				String url = "/back-end/CommBook/select_page.jsp";
				RequestDispatcher successfulView = req.getRequestDispatcher(url);
				successfulView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add(e.getMessage());
				String url = "/back-end/CommBook/select_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
			
		}
		
		
//		if("insert2".equals(action)) {
//			System.out.println("進入insert");
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				HttpSession session = req.getSession();
//				TeacherVO tp = (TeacherVO) session.getAttribute("tp");// 到時候要確認 用什麼方式拿到老師的VO
//	
//				String t_Num = null;
//				if (tp != null) {
//					t_Num = tp.getT_num();
//				} else {
//					throw new RuntimeException("請先登入");
//				}
//						
//				String st_Num = req.getParameter("st_Num").trim();
//				if (st_Num == null || st_Num.trim().length() == 0) {
//					errorMsgs.add("學生編號請勿空白");
//				}
//				
//				java.sql.Date comm_Date = null;
//				try {
//					comm_Date = java.sql.Date.valueOf(req.getParameter("comm_Date").trim());
//				} catch (IllegalArgumentException e) {
//					comm_Date = new java.sql.Date(System.currentTimeMillis());
//				}
//	
//				String comm_T_Msg = req.getParameter("comm_T_Msg").trim();
//				if (comm_T_Msg == null || comm_T_Msg.trim().length() == 0) {
//					errorMsgs.add("老師的話請勿空白");
//				}
//				
//				
//				CommBookVO commBookVO = new CommBookVO();
//				commBookVO.setSt_Num(st_Num);
//				commBookVO.setComm_T_Msg(comm_T_Msg);
//		
//				
//				if (!errorMsgs.isEmpty()) {
//					String url = "/back-end/CommBook/addCommBook.jsp"; // 跳轉回addCommBook.jsp
//					RequestDispatcher failureView = req.getRequestDispatcher(url);
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//				/*************************** 2.開始查詢資料 ***********************************************/
//				CommBookService commBookSvc = new CommBookService();
//				commBookVO = commBookSvc.addCommBookVO(st_Num, t_Num, comm_Date, comm_T_Msg);
//				
//	//			List<CommBookVO> commBookVOList = commBookSvc????
//				
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//	//			req.setAttribute("commBookVOList", commBookVOList);
//				req.setAttribute("commBookVO", commBookVO);
//				String url ="/back-end/CommBook/getOneStudent.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				/*************************** 其他可能的錯誤處理 ****************/	
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/CommBook/select_page.jsp");
//				failureView.forward(req, res);
//			}
//			
//		}

//	=================================================================================================================
//	=================================================================================================================		
		
		//前台  指定日期查詢
		if("getOne_For_Display".equals(action)) {
			System.out.println("進入getOne_For_Display");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				HttpSession session = req.getSession();
				StudentVO stp =(StudentVO) session.getAttribute("stp");
				String st_Num = null;
				if(stp != null) {
					st_Num = stp.getSt_num(); // 登入進來給值
				}else {
					System.out.println("請先登入"); // 沒登入時的處理
				}
					
				String date = req.getParameter("comm_Date");
				
				if (date == null || date.trim().length() == 0) {
					date = new java.sql.Date(System.currentTimeMillis()).toString(); 
				}
				
				if (!errorMsgs.isEmpty()) {
					String url = "/front-end/CommBook/select_page.jsp"; // 跳轉回select_page
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}				
	
				if (!errorMsgs.isEmpty()) {
					String url = "/front-end/CommBook/select_page.jsp"; // 跳轉回select_page
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}	
				
				/*************************** 2.開始查詢資料 ***********************************************/
				CommBookService commBookSvc = new CommBookService();
				List<CommBookVO> commBookVOList = commBookSvc.getRecentCommBookVO(st_Num, date);
				
				if(commBookVOList == null) {
					errorMsgs.add("查無資料");
				}
			
				if (!errorMsgs.isEmpty()) {
					String url = "/front-end/CommBook/select_page.jsp"; // 跳轉回select_page
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("commBookVOList", commBookVOList);
				String url ="/front-end/CommBook/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 ****************/			
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/CommBook/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// 前台  查詢最近二筆資料
		if ("showTwoDayFromStudent".equals(action)) {
			System.out.println("進入showTwoDayFromStudent");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {	
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String date = req.getParameter("comm_Date");
				
				if (date == null || date.trim().length() == 0) {
					date = new java.sql.Date(System.currentTimeMillis()).toString();
				}
				// session內可取得登入學生的資訊
				HttpSession session = req.getSession();
				StudentVO stp =(StudentVO) session.getAttribute("stp");
				String st_Num = null;
				if(stp != null) {
					st_Num = stp.getSt_num(); // 登入進來給值
				}else {
					System.out.println("請先登入"); // 沒登入時的處理
				}
	
				/*************************** 2.開始查詢資料 *****************************************/
				CommBookService commBookSvc = new CommBookService();
				List<CommBookVO> commBookVOList = commBookSvc.getRecentCommBookVO(st_Num, date);
			
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("commBookVOList", commBookVOList);
				
				String url = "/front-end/CommBook/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				String url = "/front-end/CommBook/select_page.jsp";
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		} 
		//前台 修改
		if ("getOne_For_Update".equals(action)) {
			req.removeAttribute("errorMsgs");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer comm_Id = new Integer(req.getParameter("comm_Id").trim());
				/*************************** 2.開始查詢資料 *****************************************/
				CommBookService commBookSvc = new CommBookService();
				CommBookVO commBookVO = commBookSvc.getOneCommBookVO(comm_Id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("commBookVO", commBookVO);
				String url = "/front-end/CommBook/updateStudent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/CommBook/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// 前台 修改
		if("updateStudent".equals(action)) {
			System.out.println("進入updateStudent");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/			
				Integer comm_Id = new Integer(req.getParameter("comm_Id").trim()); 
				System.out.println(111111);
				HttpSession session = req.getSession();
				StudentVO stp =(StudentVO) session.getAttribute("stp");
				String st_Num = null;
				System.out.println(4);
				if(stp != null) {
					st_Num = stp.getSt_num(); // 登入進來給值
				}else {
					System.out.println("請先登入"); // 沒登入時的處理
				}
				System.out.println(222222);
				String comm_P_Msg = req.getParameter("comm_P_Msg").trim();
				
				CommBookVO commBookVO = new CommBookVO();
				commBookVO.setComm_P_Msg(comm_P_Msg);
				
				if (!errorMsgs.isEmpty()) {
					String url = "/front-end/CommBook/select_page.jsp"; // 跳轉回select_page
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 ***********************************************/
				CommBookService commBookSvc = new CommBookService();
				commBookVO = commBookSvc.updateCommbookSt(comm_Id,comm_P_Msg);			
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("commBookVO", commBookVO);
				String url = "/front-end/CommBook/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 ****************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/CommBook/select_page.jsp");
				failureView.forward(req, res);
			}	
		}
		
		
//		if () {
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			/*************************** 2.開始查詢資料 ***********************************************/
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			/*************************** 其他可能的錯誤處理 ****************/
//		}

	}

}
