package com.guardian.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guardian.model.GuardianService;
import com.guardian.model.GuardianVO;
import com.teacher.model.TeacherService;
import com.teacher.model.TeacherVO;

public class GuardianServlet extends HttpServlet {
		
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
		}
		
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			
			
			if("getOne_For_Display".equals(action)) {
				
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String gd_id = req.getParameter("gd_id");
					if(gd_id == null || gd_id.trim().length() == 0) {
						errorMsgs.add("請輸入家長身分證字號");
					}
				
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/guardian/select_page.jsp");
						failureView.forward(req, res);
						return;
					}
				
					/***************************2.開始查詢資料*****************************************/
					GuardianService guardianSvc = new GuardianService();
					GuardianVO guardianVO = guardianSvc.getGuardianByGd_Id(gd_id);
					if(guardianVO == null) {
						errorMsgs.add("查無資料");
					}
					
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/guardian/select_page.jsp");
						failureView.forward(req, res);
						return;
					}
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("guardianVO", guardianVO);
					String url = "/back-end/guardian/listOneGuardian.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/guardian/select_page.jsp");
					failureView.forward(req, res);
				}
			}
			
			if("getSelfGuardian".equals(action)) {
				
				String gd_id = req.getParameter("gd_id");
				
				GuardianService guardianSvc = new GuardianService();
				GuardianVO guardianVO = guardianSvc.getGuardianByGd_Id(gd_id);
				
				req.setAttribute("guardianVO", guardianVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/guardian/listSelfInfo.jsp");
				successView.forward(req, res);
			}
			
			
			if("getOne_For_Update".equals(action)) {
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String gd_id = req.getParameter("gd_id");

					/***************************2.開始查詢資料*****************************************/
					GuardianService guardianSvc = new GuardianService();
					GuardianVO guardianVO = guardianSvc.getGuardianByGd_Id(gd_id);

					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("guardianVO", guardianVO);
					String url = "/back-end/guardian/update_guardian_input.jsp";
					RequestDispatcher successView = req
							.getRequestDispatcher(url);
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/guardian/select_page.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if("update".equals(action)) {
				
				List<String> errorMsgs = new LinkedList<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String gd_name = req.getParameter("gd_name");
					String gd_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if (gd_name ==null || gd_name.trim().length() == 0) {
						errorMsgs.add("家長姓名請勿空白");
					} else if (!gd_name.trim().matches(gd_nameReg)) {
						errorMsgs.add("教職員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
					}
					
					String gd_id = req.getParameter("gd_id");
					String gd_idReg = "^[A-Za-z][12]\\d{8}$";
					if (gd_id == null || gd_id.trim().length() == 0) {
						errorMsgs.add("身份證字號請勿空白");
					} else if (!gd_id.trim().matches(gd_idReg)) {
						errorMsgs.add("身分證格式不正確");
					}
					
					String gd_gender = req.getParameter("gd_gender");
					if (gd_gender == null || gd_gender.trim().length() == 0) {
						errorMsgs.add("請選擇性別");
					}
					
					String gd_email = req.getParameter("gd_email");
					String gd_emailReg = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$";
					if (gd_email == null || gd_email.trim().length() == 0) {
						errorMsgs.add("E-mail請勿空白");
					} else if (!gd_email.trim().matches(gd_emailReg)) {
						errorMsgs.add("e-mail格式不符合");
					}
					
					String gd_address = req.getParameter("gd_address");
					if (gd_address == null || gd_address.trim().length() == 0) {
						errorMsgs.add("通訊地址請勿空白");
					}
					
					String gd_rel = req.getParameter("gd_rel");
					if (gd_rel == null || gd_rel.trim().length() == 0) {
						errorMsgs.add("親子關係請勿空白");
					}
					
					String gd_phone = req.getParameter("gd_phone");
					String gd_phoneReg = "^09[0-9]{8}$";
					if (gd_phone == null || gd_phone.trim().length() == 0) {
						errorMsgs.add("連絡電話請勿空白");
					} else if (!gd_phone.trim().matches(gd_phoneReg)) {
						errorMsgs.add("電話格式不符");
					}
					
					Date gd_birthday = null;
					try {
						gd_birthday = Date.valueOf(req.getParameter("gd_birthday"));
					} catch (IllegalArgumentException e) {
						gd_birthday = new Date(System.currentTimeMillis());
						errorMsgs.add("請輸入生日");
					}
					
					
					GuardianVO guardianVO = new GuardianVO();
					guardianVO.setGd_name(gd_name);
					guardianVO.setGd_id(gd_id);
					guardianVO.setGd_gender(gd_gender);
					guardianVO.setGd_email(gd_email);
					guardianVO.setGd_address(gd_address);
					guardianVO.setGd_rel(gd_rel);
					guardianVO.setGd_phone(gd_phone);
					guardianVO.setGd_birthday(gd_birthday);
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("guardianVO", guardianVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/guardian/update_guardian_input.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始修改資料*****************************************/
					GuardianService guardianSvc = new GuardianService();
					guardianVO = guardianSvc.updateGuardian(gd_id, gd_name, gd_gender, gd_email, gd_address, gd_rel, gd_phone, gd_birthday);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("guardianVO", guardianVO);
					String url = "/back-end/guardian/listOneGuardian.jsp";
					RequestDispatcher successView = req
							.getRequestDispatcher(url);
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理*************************************/
				} catch(Exception e) {
					errorMsgs.add("修改資料失敗" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/guardian/update_guardian_input.jsp");
					failureView.forward(req, res);
				}
			}
			
			if("insert".equals(action)) {
				Map<String, String> errorMsgs = new LinkedHashMap<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String gd_name = req.getParameter("gd_name");
					String t_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if (gd_name ==null || gd_name.trim().length() == 0) {
						errorMsgs.put("gd_name", "家長姓名請勿空白");
					} else if (!gd_name.trim().matches(t_nameReg)) {
						errorMsgs.put("gd_name", "家長姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
					}
					
					String gd_id = req.getParameter("gd_id");
					String gd_idReg = "^[A-Za-z][12]\\d{8}$";
					if (gd_id == null || gd_id.trim().length() == 0) {
						errorMsgs.put("gd_id", "身份證字號請勿空白");
					} else if (!gd_id.trim().matches(gd_idReg)) {
						errorMsgs.put("gd_id", "身分證格式不正確");
					}
					
					String gd_gender = req.getParameter("gd_gender");
					if (gd_gender == null || gd_gender.trim().length() == 0) {
						errorMsgs.put("gd_gender", "請選擇性別");
					}
					
					String gd_email = req.getParameter("gd_email");
					String gd_emailReg = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$";
					if (gd_email == null || gd_email.trim().length() == 0) {
						errorMsgs.put("gd_email", "E-mail請勿空白");
					} else if (!gd_email.trim().matches(gd_emailReg)) {
						errorMsgs.put("gd_email", "e-mail格式不符合");
					}
					
					String gd_address = req.getParameter("gd_address");
					if (gd_address == null || gd_address.trim().length() == 0) {
						errorMsgs.put("gd_address", "通訊地址請勿空白");
					}
					
					String gd_rel = req.getParameter("gd_rel");
					if (gd_rel == null || gd_rel.trim().length() == 0) {
						errorMsgs.put("gd_rel", "親子關係請勿空白");
					}
					
					String gd_phone = req.getParameter("gd_phone");
					String gd_phoneReg = "^09[0-9]{8}$";
					if (gd_phone == null || gd_phone.trim().length() == 0) {
						errorMsgs.put("gd_phone", "連絡電話請勿空白");
					} else if (!gd_phone.trim().matches(gd_phoneReg)) {
						errorMsgs.put("gd_phone", "電話格式不符");
					}
					
					Date gd_birthday = null;
					try {
						gd_birthday = Date.valueOf(req.getParameter("gd_birthday"));
					} catch (IllegalArgumentException e) {
						gd_birthday = new Date(System.currentTimeMillis());
						errorMsgs.put("gd_birthday", "請輸入生日");
					}
					
					GuardianVO guardianVO = new GuardianVO();
					guardianVO.setGd_name(gd_name);
					guardianVO.setGd_id(gd_id);
					guardianVO.setGd_gender(gd_gender);
					guardianVO.setGd_email(gd_email);
					guardianVO.setGd_address(gd_address);
					guardianVO.setGd_rel(gd_rel);
					guardianVO.setGd_phone(gd_phone);
					guardianVO.setGd_birthday(gd_birthday);
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("guardianVO", guardianVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/guardian/addGuardian.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					GuardianService guardianSvc = new GuardianService();
					guardianVO = guardianSvc.addGuardian(gd_id, gd_name, gd_gender, gd_email, gd_address, gd_rel, gd_phone, gd_birthday);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					req.setAttribute("guardianVO", guardianVO);
					String url = "/back-end/student/addStudent.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.put("otherError", e.getMessage());
					System.out.println(e);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/guardian/addGuardian.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if("insert_guardian".equals(action)) {
				Map<String, String> errorMsgs = new LinkedHashMap<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				
				
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					GuardianService guardianSvc = new GuardianService();
					
					
					String gd_id = req.getParameter("gd_id");
					String gd_idReg = "^[A-Za-z][12]\\d{8}$";
					if (gd_id == null || gd_id.trim().length() == 0) {
						errorMsgs.put("gd_id", "身份證字號請勿空白");
					} else if (!gd_id.trim().matches(gd_idReg)) {
						errorMsgs.put("gd_id", "身分證格式不正確");
					}
								
					String gd_name = req.getParameter("gd_name");
					String t_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if (gd_name ==null || gd_name.trim().length() == 0) {
						errorMsgs.put("gd_name", "家長姓名請勿空白");
					} else if (!gd_name.trim().matches(t_nameReg)) {
						errorMsgs.put("gd_name", "家長姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
					}
					

					
					String gd_gender = req.getParameter("gd_gender");
					if (gd_gender == null || gd_gender.trim().length() == 0) {
						errorMsgs.put("gd_gender", "請選擇性別");
					}
					
					String gd_email = req.getParameter("gd_email");
					String gd_emailReg = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$";
					if (gd_email == null || gd_email.trim().length() == 0) {
						errorMsgs.put("gd_email", "E-mail請勿空白");
					} else if (!gd_email.trim().matches(gd_emailReg)) {
						errorMsgs.put("gd_email", "e-mail格式不符合");
					}
					
					String gd_address = req.getParameter("gd_address");
					if (gd_address == null || gd_address.trim().length() == 0) {
						errorMsgs.put("gd_address", "通訊地址請勿空白");
					}
					
					String gd_rel = req.getParameter("gd_rel");
					if (gd_rel == null || gd_rel.trim().length() == 0) {
						errorMsgs.put("gd_rel", "親子關係請勿空白");
					}
					
					String gd_phone = req.getParameter("gd_phone");
					String gd_phoneReg = "^09[0-9]{8}$";
					if (gd_phone == null || gd_phone.trim().length() == 0) {
						errorMsgs.put("gd_phone", "連絡電話請勿空白");
					} else if (!gd_phone.trim().matches(gd_phoneReg)) {
						errorMsgs.put("gd_phone", "電話格式不符");
					}
					
					Date gd_birthday = null;
					try {
						gd_birthday = Date.valueOf(req.getParameter("gd_birthday"));
					} catch (IllegalArgumentException e) {
						gd_birthday = new Date(System.currentTimeMillis());
						errorMsgs.put("gd_birthday", "請輸入生日");
					}
					
					GuardianVO guardianVO = new GuardianVO();
					guardianVO.setGd_name(gd_name);
					guardianVO.setGd_id(gd_id);
					guardianVO.setGd_gender(gd_gender);
					guardianVO.setGd_email(gd_email);
					guardianVO.setGd_address(gd_address);
					guardianVO.setGd_rel(gd_rel);
					guardianVO.setGd_phone(gd_phone);
					guardianVO.setGd_birthday(gd_birthday);
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("guardianVO", guardianVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/RegistrationForm1.jsp");
						failureView.forward(req, res);
						return;
					}
					
					if(guardianSvc.getGuardianByGd_Id(gd_id) != null) {
						guardianVO = guardianSvc.getGuardianByGd_Id(gd_id);
						req.setAttribute("guardianVO", guardianVO);
						String url = "/front-end/RegistrationForm2.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					guardianVO = guardianSvc.addGuardian(gd_id, gd_name, gd_gender, gd_email, gd_address, gd_rel, gd_phone, gd_birthday);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					req.setAttribute("guardianVO", guardianVO);
					String url = "/front-end/RegistrationForm2.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.put("otherError", e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/RegistrationForm1.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			
			if("insert_Student_By_Gd_Id".equals(action)) {
				Map<String, String> errorMsgs = new LinkedHashMap<>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String gd_id = req.getParameter("gd_id");
					if(gd_id == null || gd_id.trim().length() == 0) {
						errorMsgs.put("oneError", "請輸入家長身分證字號");
					}
				
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/guardian/addGuardian.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始查詢資料*****************************************/
					GuardianService guardianSvc = new GuardianService();
					GuardianVO guardianVO = guardianSvc.getGuardianByGd_Id(gd_id);
					if(guardianVO == null) {
						errorMsgs.put("oneError", "查無資料");
					}
					
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/guardian/addGuardian.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("guardianVO", guardianVO);
					String url = "/back-end/student/addStudent.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.put("oneError", "無法取得資料" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/guardian/addGuardian.jsp");
					failureView.forward(req, res);
				}
			}
			
		}
}
