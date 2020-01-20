package com.student_leave.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.student_leave_list.model.Student_leave_listService;
import com.student_leave_list.model.Student_leave_listVO;




public class Student_leave_listServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) 
		throws ServletException,IOException{
			
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer st_list_num = null;
			try {
				/************1.接收請求，處理格式輸入錯誤**************/
				try {
					st_list_num = new Integer(req.getParameter("st_list_num").trim());
				}catch(NumberFormatException e) {
					st_list_num = 1001;
					errorMsgs.add("請輸入正確假單編號");
				}catch(NullPointerException ee) {
					st_list_num= 1001;
					errorMsgs.add("請輸入班級編號");
				}
				/************2.開始查資料**************************/
				Student_leave_listService student_leave_listSvc = new Student_leave_listService();
				Student_leave_listVO student_leave_listVO = student_leave_listSvc.getOneStudent_leave_list(st_list_num);
				System.out.println(student_leave_listVO.getSt_list_num());
				System.out.println(student_leave_listVO.getSt_num());
				if(student_leave_listVO == null) {
					errorMsgs.add("查無此資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView  =req.getRequestDispatcher("/back-end/student_leave_list/search_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/************3.查詢完成後轉交***********************/
				req.setAttribute("student_leave_listVO", student_leave_listVO);
				String url = "/back-end/student_leave_list/search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/************4.處理其他錯誤************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/student_leave_list/search_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer st_list_num = null;
			
			try {
				/************1.接收請求參數，處理格式輸入錯誤***********/
				try {
					st_list_num = new Integer(req.getParameter("st_list_num").trim());
				}catch(NumberFormatException e) {
					st_list_num = 1001;
					errorMsgs.add("請輸入假單編號");
				}catch (NullPointerException ee) {
					st_list_num = 1001;
					errorMsgs.add("請輸入假單編號");
				}
				/************2.開始修改資料************************/
				Student_leave_listService student_leave_listSvc = new Student_leave_listService();
				Student_leave_listVO student_leave_listVO = student_leave_listSvc.getOneStudent_leave_list(st_list_num);
				/************3.修改完成後轉交**********************/
				req.setAttribute("student_leave_leatVO", student_leave_listVO);
				String url ="";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/************4.處理其他錯誤***********************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料" +e.getMessage());
				RequestDispatcher failureView  = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}
		
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer st_list_num = null;
			
			try {
				/************1.接收請求參數，處理格式輸入錯誤**********/
				try {
					st_list_num = new Integer(req.getParameter("st_list_num").trim());
				}catch(NumberFormatException e) {
					st_list_num = 1001;
					errorMsgs.add("請輸入假單編號");
				} catch(NullPointerException ee) {
					st_list_num = 1001;
					errorMsgs.add("請輸入假單編號");
				}
				
				String st_num = req.getParameter("st_num").trim();
				if(st_num == null || st_num.trim().length() == 0) {
					errorMsgs.add("學生學號:請勿空白");
				}
				
				java.sql.Timestamp start_date = null;
				try {
					start_date = java.sql.Timestamp.valueOf(req.getParameter("start_date").trim());
				}catch(IllegalArgumentException e) {
					start_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入開始請假時間");
				}
				
				java.sql.Timestamp end_date = null;
				try {
					end_date = java.sql.Timestamp.valueOf(req.getParameter("end_date").trim());
				}catch(IllegalArgumentException e) {
					end_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入請假結束時間");
				}
				
				Integer st_list_sort = null;
				
				try {
					st_list_sort = new Integer(req.getParameter("st_list_sort").trim());
				}catch(NumberFormatException e) {
					errorMsgs.add("請填入正確假別");
				}catch(NullPointerException ee) {
					errorMsgs.add("請填入假別");
				}
				
				String st_list_note =req.getParameter("st_list_note");
				if(st_list_note == null || st_list_note.trim().length() == 0) {
					errorMsgs.add("請填入請假事由");
				}
				
				Student_leave_listVO student_leave_listVO = new Student_leave_listVO();
				student_leave_listVO.setSt_list_num(st_list_num);
				student_leave_listVO.setSt_num(st_num);
				student_leave_listVO.setStart_date(start_date);
				student_leave_listVO.setEnd_date(end_date);
				student_leave_listVO.setSt_list_sort(st_list_sort);
				student_leave_listVO.setSt_list_note(st_list_note);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("student_leave_listVO", student_leave_listVO);
					RequestDispatcher failureView = req.getRequestDispatcher("");
					failureView.forward(req, res);
					return;
				}
				/*************2.開始修改資料***************************/
				Student_leave_listService student_leave_listSvc = new Student_leave_listService();
				student_leave_listVO = student_leave_listSvc.updateStudent_leave_list(st_list_num, st_num, start_date, end_date, st_list_sort, st_list_note);
				/*************3.修改完成，準備轉交**********************/
				req.setAttribute("student_leave_listVO", student_leave_listVO);
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************4.處理其他錯誤**************************/
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}
		
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			java.sql.Timestamp start_date = null;
			java.sql.Timestamp end_date = null;
			
			try {
				/************1.接收請求參數，處理錯誤格式*****************/
				String st_num = req.getParameter("st_num");
				if(st_num == null || st_num.trim().length() == 0) {
					errorMsgs.add("學生學號:請勿空白");
				}

				
				String start_date1 = req.getParameter("start_date");
				
				try {
				
					start_date1 += ":00";
					start_date = java.sql.Timestamp.valueOf(start_date1);
						
				}catch(IllegalArgumentException e) {
					 
					errorMsgs.add("請輸入開始請假時間");
				}
				
				//
				String end_date1 = req.getParameter("end_date");
				try {
					end_date1 += ":00";
					end_date = java.sql.Timestamp.valueOf(end_date1);
				}catch(IllegalArgumentException e) {
					 
					errorMsgs.add("請輸入開始請假時間");
				}
				
				
				
				
				Integer st_list_sort = null;
				
				try {
					st_list_sort = new Integer(req.getParameter("st_list_sort").trim());
				}catch(NumberFormatException e) {
					errorMsgs.add("請填入正確假別");
				}catch(NullPointerException ee) {
					errorMsgs.add("請填入假別");
				}
				
				String st_list_note =req.getParameter("st_list_note");
				if(st_list_note == null || st_list_note.trim().length() == 0) {
					errorMsgs.add("請填入請假事由");
				}
				
				Student_leave_listVO student_leave_listVO = new Student_leave_listVO();
				student_leave_listVO.setSt_num(st_num);
				student_leave_listVO.setStart_date(start_date);
				student_leave_listVO.setEnd_date(end_date);
				student_leave_listVO.setSt_list_sort(st_list_sort);
				student_leave_listVO.setSt_list_note(st_list_note);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("student_leave_listVO", student_leave_listVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/student_leave_list/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***********2.開始新增資料********************************/
				Student_leave_listService student_leave_listSvc = new Student_leave_listService();
				student_leave_listVO = student_leave_listSvc.addStudent_leave_list(st_num, start_date, end_date, st_list_sort, st_list_note);
				/***********3.新增完成，準備轉交***************************/
				req.setAttribute("addlist", true);
				String url = "/front-end/student_leave_list/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***********4.處理其他錯誤*******************************/
			}catch(Exception e) {
				errorMsgs.add("請遵循正確格式填寫");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/student_leave_list/select_page.jsp");
				failureView.forward(req, res);
			}	
		}
		
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/**********1.接收請求參數********************************/
				Integer st_list_num = new Integer(req.getParameter("st_list_num"));
				/**********2.開始刪除資料********************************/
				Student_leave_listService student_leave_listSvc = new Student_leave_listService();
				student_leave_listSvc.deleteStudent_leave_list(st_list_num);
				/**********3.刪除完成，準備轉交***************************/
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/**********4.處理其他錯誤********************************/		
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}
		
	}
}
