package com.class_messageboard.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.class_messageboard.model.Class_messageboardService;
import com.class_messageboard.model.Class_messageboardVO;
import com.student.model.StudentService;
import com.student.model.StudentVO;
import com.teacher.model.TeacherVO;


public class Class_messageboardServlet extends HttpServlet{
	public void doGet(HttpServletRequest req , HttpServletResponse res)throws ServletException,IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer msg_num = null;
			try {
				/**************1.接收請求參數，處理錯誤格式******************/
				try {
					msg_num = new Integer(req.getParameter("msg_num").trim());
				}catch(NumberFormatException e) {
					errorMsgs.add("請輸入正確留言編號");
				}catch(NullPointerException ee) {
					errorMsgs.add("請輸入留言編號");
				}		
				/**************2.開始查詢資料****************************/
				Class_messageboardService class_messageboardSvc = new Class_messageboardService();
				Class_messageboardVO class_messageboardVO = class_messageboardSvc.getOneEmp(msg_num);
				if(class_messageboardVO == null) {
					errorMsgs.add("查無此留言");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("");
					failureView.forward(req, res);
					return;
				}
				/**************3.查詢後轉交*****************************/
				req.setAttribute("class_messageboardVO",class_messageboardVO);
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/**************4.處理其他錯誤***************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得留言:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}
		
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer msg_num = null;
			
			try {
				/**************1.接收請求參數，處理錯誤格式***************/
				try {
					msg_num = new Integer(req.getParameter("msg_num").trim());
				}catch(NumberFormatException e) {
					errorMsgs.add("請輸入正確留言編號");
				}catch(NullPointerException ee) {
					errorMsgs.add("請輸入留言編號");
				}
				/*************2.開始修改資料**************************/
				Class_messageboardService class_messageboardSvc = new Class_messageboardService();
				Class_messageboardVO class_messageboardVO = class_messageboardSvc.getOneEmp(msg_num);
				/*************3.修改後轉交***************************/
				req.setAttribute("class_messageboardVO", class_messageboardVO);
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************4.處理其他錯誤*************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得留言:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}
		
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			TeacherVO tVO = (TeacherVO) req.getSession().getAttribute("tp");
			try {
				
				String cs_num = req.getParameter("cs_num");
				if(cs_num == null || cs_num.trim().length() ==0) {
					errorMsgs.add("班級編號:請勿空白");										
				}
				
				String t_num = req.getParameter("t_num");
				String msg = req.getParameter("msg");
				if(msg == null || msg.trim().length() == 0) {
					errorMsgs.add("留言內容請勿空白");
				}

				
				
				Class_messageboardVO class_messageboardVO = new Class_messageboardVO();
				class_messageboardVO.setCs_num(cs_num);
				class_messageboardVO.setT_num(tVO.getT_num());
				class_messageboardVO.setT_num(t_num);
				class_messageboardVO.setMsg(msg);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("class_messageboardVO", class_messageboardVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_msg/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************2.開始新增資料***********************/
				Class_messageboardService class_messageboardSvc = new Class_messageboardService();
				class_messageboardVO = class_messageboardSvc.addBackClass_messageboard(cs_num,  tVO.getT_num(), msg);
				/***************3.新增後轉交************************/
				String url = "/back-end/class_msg/select_page.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				res.sendRedirect(req.getContextPath() + "/back-end/class_msg/select_page.jsp");
				return;
				/***************4.處理其他錯誤**********************/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_msg/select_page.jsp");
				failureView.forward(req, res);
			}
		}	
		
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************1.接收請求參數*********************/
				Integer msg_num = new Integer(req.getParameter("msg_num"));
				/*************2.開始刪除資料*********************/
				Class_messageboardService class_messageboardSvc = new Class_messageboardService();
				class_messageboardSvc.deleteClass_messageboard(msg_num);
				/*************3.刪除後轉交**********************/
				String url = "/back-end/class_msg/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************4.處理其他錯誤********************/
			}catch(Exception e) {
				errorMsgs.add("刪除留言失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_msg/select_page.jsp");
				failureView.forward(req, res);
			}
		}	
	}
}
