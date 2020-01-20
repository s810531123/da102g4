package com.class_ann.controller;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.class_ann.model.Class_annService;
import com.class_ann.model.Class_annVO;

public class Class_annServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer cs_ann_num = null;
			try {
				/******************1.接收參數，格式錯誤之處理************************/
				try {
					cs_ann_num = new Integer(req.getParameter("cs_ann_num").trim());
				}catch(NumberFormatException e) {
					cs_ann_num = 1001;
					errorMsgs.add("請輸入正確公告編號");
				}catch(NullPointerException ee) {
					cs_ann_num =1001;
					errorMsgs.add("請輸入班級公告編號");
				}
				/****************2.開始查資料*********************/
				Class_annService class_annSvc = new Class_annService();
				Class_annVO class_annVO = class_annSvc.getOneClass_ann(cs_ann_num);
				System.out.println(class_annVO.getCs_num());
				System.out.println(class_annVO.getCs_ann_num());
				if(class_annVO == null) {
					errorMsgs.add("查無此資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_ann/search_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/****************3.查詢完成後轉交***********************************/
				req.setAttribute("class_annVO", class_annVO);
				String url = "/back-end/class_ann/search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/****************4.處理其他錯誤***************************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_ann/search_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer cs_ann_num = null;
			
			try {
				/*************1.接收請求參數，錯誤格式處理*********************/
				try {
					cs_ann_num = new Integer(req.getParameter("cs_ann_num").trim());
				}catch(NumberFormatException e) {
					cs_ann_num = 1001;
					errorMsgs.add("請輸入班級公告編號");
				}catch(NullPointerException ee) {
					cs_ann_num =1001;
					errorMsgs.add("請輸入班級公告編號");
				}
				/**************2.開始修改資料*****************************************/
				Class_annService class_annSvc = new Class_annService();
				Class_annVO class_annVO = class_annSvc.getOneClass_ann(cs_ann_num);
				/***************3.修改完成後轉交***********************************/
				req.setAttribute("class_annVO", class_annVO);
				String url ="/back-end/class_ann/update_class_ann_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/****************4.處理其他錯誤********************************************/
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_ann/update_class_ann_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer cs_ann_num = null;
			
			try {
				/********************1.接收請求參數，錯誤格式處理******************************/
				try {
					cs_ann_num = new Integer(req.getParameter("cs_ann_num").trim());
				}catch(NumberFormatException e) {
					cs_ann_num = 1001;
					errorMsgs.add("請輸入班級公告編號");
				}catch(NullPointerException ee) {
					cs_ann_num =1001;
					errorMsgs.add("請輸入班級公告編號");
				}
					
				
				String cs_num = req.getParameter("cs_num").trim();
				if(cs_num == null || cs_num.trim().length() == 0) {
					errorMsgs.add("班級姓名:請勿空白");
				}
				
				
				String cs_ann_kind  = req.getParameter("cs_ann_kind").trim();
				if(cs_ann_kind == null || cs_ann_kind.trim().length() == 0) {
					errorMsgs.add("班級公告種類:請勿空白");
				}
				
				String cs_ann_ti = req.getParameter("cs_ann_ti").trim();
				if(cs_ann_ti == null || cs_ann_ti.trim().length() == 0) {
					errorMsgs.add("班級公告標題:請勿空白");
				}
				
				String cs_ann_text = req.getParameter("cs_ann_text").trim();
				if(cs_ann_text == null || cs_ann_text.trim().length() == 0) {
					errorMsgs.add("班級公告內容:請勿空白");
				}
				
				java.sql.Date cs_ann_date = null;
				try {
					cs_ann_date = java.sql.Date.valueOf(req.getParameter("cs_ann_date").trim());
				}catch(IllegalArgumentException e) {
					cs_ann_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入班級公告日期");
				}
				
				Class_annVO class_annVO = new Class_annVO();
				class_annVO.setCs_ann_num(cs_ann_num);
				class_annVO.setCs_num(cs_num);
				class_annVO.setCs_ann_date(cs_ann_date);
				class_annVO.setCs_ann_ti(cs_ann_ti);
				class_annVO.setCs_ann_kind(cs_ann_kind);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("class_annVO", class_annVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_ann/update_class_ann_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/*********************2.開始修改資料***********************************/
				Class_annService class_annSvc = new Class_annService();
				class_annVO = class_annSvc.updateClass_ann(cs_ann_num, cs_num, cs_ann_text, cs_ann_date, cs_ann_ti, cs_ann_kind);
				
				/***********************3.修改完成，準備轉交*************************************/
				req.setAttribute("class_annVO", class_annVO);
				String url = "/back-end/class_ann/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***********************4.其他錯誤處理****************************/
			
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_ann/update_class_ann_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/*******************1.接收請求參數，處理錯誤格式*************************/
				String cs_num = req.getParameter("cs_num");
				if(cs_num == null || cs_num.trim().length() == 0) {
					errorMsgs.add("班級編號:請勿空白");
				}
				
				String cs_ann_text = req.getParameter("cs_ann_text");
				if(cs_ann_text == null || cs_ann_text.trim().length() == 0) {
					errorMsgs.add("班級公告內容:請勿空白");
				}
				
				java.sql.Date cs_ann_date = null;
				try {
					cs_ann_date = java.sql.Date.valueOf(req.getParameter("cs_ann_date").trim());
				}catch(IllegalArgumentException e) {
					cs_ann_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入班級公告日期");
				}
				
				String cs_ann_ti = req.getParameter("cs_ann_ti");
				if(cs_ann_ti == null || cs_ann_ti.trim().length() == 0) {
					errorMsgs.add("班級公告標題:班級公告標題請勿空白");
				}
				
				String cs_ann_kind = req.getParameter("cs_ann_kind");
				if(cs_ann_kind == null || cs_ann_kind.trim().length() == 0) {
					errorMsgs.add("班級公告種類:請勿空白");
				}
				
				Class_annVO class_annVO = new Class_annVO();
				class_annVO.setCs_num(cs_num);
				class_annVO.setCs_ann_text(cs_ann_text);
				class_annVO.setCs_ann_date(cs_ann_date);
				class_annVO.setCs_ann_ti(cs_ann_ti);
				class_annVO.setCs_ann_kind(cs_ann_kind);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("class_annVO", class_annVO);
					RequestDispatcher failureView =req.getRequestDispatcher("/back-end/class_ann/add_ann.jsp");
					failureView.forward(req, res);
					return;
				}
				/*********************2.開始新增資料*****************************/
				Class_annService class_annSvc = new Class_annService();
				class_annVO = class_annSvc.addClass_ann(cs_num, cs_ann_text, cs_ann_date, cs_ann_ti, cs_ann_kind);
				/**********************3.新增完成，準備轉交**************************************/
				req.setAttribute("add_ann",true);
				String url = "/back-end/class_ann/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*********************4.處理其他錯誤***********************/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_ann/add_ann.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*****************1.接收請求參數**********************/
				Integer cs_ann_num = new Integer(req.getParameter("cs_ann_num"));
				/******************2.開始刪除資料*******************************/
				Class_annService class_annSvc = new Class_annService();
				class_annSvc.deleteClass_ann(cs_ann_num);
				
				/*****************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/class_ann/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/*******************4.處理其他錯誤**************************************/
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_ann/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
