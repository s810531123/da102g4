package com.car.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.car.model.*;

import sun.nio.cs.Surrogate;

public class CarServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		
		//主鍵查詢
		if("getOne_car".equals(action)) { // 來自select_page.jsp的請求
			
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
			try {
				/************接收請求參數 - 輸入格式的錯誤處理****************/
				String str = req.getParameter("car_num");
				
				if(str == null || (str.trim().length() == 0)) {
					errorMsgs.add("請輸入幼童車編號");
				}
				
				//如果有錯誤，結束程式 送出錯誤訊息
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/car/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer car_num = null;
				try {
	//				car_num = new Integer(str);
					car_num = Integer.parseInt(str.trim());
				}catch(NumberFormatException e){
					errorMsgs.add("幼童車編號格式不正確");
				}
				
				//如果有錯誤，結束程式 送出錯誤訊息
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/car/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/*****************2.開始查詢資料****************/
				CarService carSvr = new CarService();
				CarVO carVO = carSvr.getOneCar(car_num);
				if(carVO == null) {
					errorMsgs.add("查無此資料");
				}
				//如果有錯誤，結束程式 送出錯誤訊息
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/car/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/********3.查詢完成,準備轉交(Send the Success view)*****/
				req.setAttribute("carVO", carVO);
				String url = "/back-end/car/listOneCar.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/**********其他可能的錯誤處理***/
			} catch(Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/car/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//新增
		if("insert".equals(action)) {
			
				List<String> errorMsgs = new LinkedList<String>();			
				req.setAttribute("errorMsgs", errorMsgs);
			try {	
			/************接收請求參數 - 輸入格式的錯誤處理****************/
			
				String strNum = req.getParameter("st_num");
				String strMonth = req.getParameter("car_month");
				if(strMonth==null || strMonth.trim().length()==0) {
					errorMsgs.add("請選擇月份");
				}
				//比對月份
				CarService carSrv = new CarService();
				List<CarVO> list = carSrv.getCarByStNum(strNum);
				for(CarVO vo : list) {
					if(strMonth.equals(vo.getCar_month())) {
						errorMsgs.add("月份請勿重複");
					}
				}
				
				CarVO carVO = new CarVO();
				carVO.setSt_num(strNum);
				carVO.setCar_month(strMonth);
				
				//如果有錯誤，結束程式 送出錯誤訊息
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("carVO", carVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/car/addCar.jsp");
					failureView.forward(req, res);
					return;
				}
			
				/*******************2.開始新增資料*****************/
				
				carSrv.addCar(strNum, strMonth);
				/**********3.新增完成,準備轉交(Send the Success view)***/
				req.setAttribute("successAdd", true);
				String url = "/back-end/car/listAllCar.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				/**********其他可能的錯誤處理***/
			
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗"/*e.getMessage()*/);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/car/addCar.jsp");
				failureView.forward(req, res);
			}
		}
			
		//修改
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*******************接收請求參數*********************/
			try {
				Integer car_num = new Integer(req.getParameter("car_num").trim());
			/*****************開始查詢資料************************/	
				CarService carSrv = new CarService();
				CarVO carVO = carSrv.getOneCar(car_num);
				
			/*****************查詢完成,準備轉交*******************/	
				req.setAttribute("carVO", carVO);
				String url = "/back-end/car/updateCar.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				
			/****************其他可能的錯誤處理*******************/
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/car/listAllCar.jsp");
				failureView.forward(req, res);
			}
			
		}
			
			
			
			
			
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*******************接收請求參數*********************/
			try {
				
				String strCarNum = req.getParameter("car_num");
				String strStNum = req.getParameter("st_num");
				String strCarMonth = req.getParameter("car_month");
				
				Integer car_num = new Integer(strCarNum);
				
				if(strCarMonth==null || strCarMonth.trim().length()==0) {
					errorMsgs.add("請選擇月份");
				}
				
				//比對月份
				CarService carSrv = new CarService();
				List<CarVO> list = carSrv.getCarByStNum(strStNum);
				for(CarVO vo : list) {
					if(strCarMonth.equals(vo.getCar_month())) {
						errorMsgs.add("月份請勿重複");
					}
				}
				CarVO carVO = new CarVO();
				carVO.setCar_num(car_num);
				carVO.setSt_num(strStNum);
				carVO.setCar_month(strCarMonth);
				//如果有錯誤，結束程式 送出錯誤訊息
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("carVO", carVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/car/updateCar.jsp");
					failureView.forward(req, res);
					return;
				}
				/********************2.開始修改資料************************/
				carSrv.updateCar(car_num, strStNum, strCarMonth);
				
				/*************修改完成 轉交*******************/
				req.setAttribute("successUpdate", true);
				String url = "/back-end/car/listAllCar.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			/****************其他可能的錯誤處理***************/
			} catch(Exception e) {
				errorMsgs.add("修改資料失敗"/*+e.getMessage()*/);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/car/updateCar.jsp");
				failureView.forward(req, res);
			}
			
			
			
			
		}
		
	}
}
