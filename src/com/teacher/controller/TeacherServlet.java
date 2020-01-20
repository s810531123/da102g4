package com.teacher.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.teacher.model.TeacherService;
import com.teacher.model.TeacherVO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@MultipartConfig
public class TeacherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String t_num = req.getParameter("t_num");
				String t_numReg = "^[T][0-9]{3}$";
				if (t_num == null || (t_num.trim()).length() == 0) {
					errorMsgs.add("請輸入教職員編號");
				} else if (!t_num.trim().matches(t_numReg)) {
					errorMsgs.add("學號只能是T開頭加3位數字");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/teacher/listAllTeacher.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println(t_num);

				/*************************** 2.開始查詢資料 *****************************************/
				TeacherService teacherSvc = new TeacherService();
				TeacherVO teacherVO = teacherSvc.getOneTeacher(t_num);
				if (teacherVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/teacher/listAllTeacher.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("teacherVO", teacherVO);
				String url = "/back-end/teacher/listOneTeacher.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/teacherlistAllTeacher.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String t_num = req.getParameter("t_num");

				/*************************** 2.開始查詢資料 *****************************************/
				TeacherService teacherSvc = new TeacherService();
				TeacherVO teacherVO = teacherSvc.getOneTeacher(t_num);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("teacherVO", teacherVO);
				String url = "/back-end/teacher/update_teacher_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/teacher/listAllTeacher.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("updateSelf".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String t_num = req.getParameter("t_num");

				/*************************** 2.開始查詢資料 *****************************************/
				TeacherService teacherSvc = new TeacherService();
				TeacherVO teacherVO = teacherSvc.getOneTeacher(t_num);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("teacherVO", teacherVO);
				String url = "/back-end/teacher/personInfo_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/teacher/personInfo.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("startupdateSelf".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String t_num = req.getParameter("t_num");

				String t_name = req.getParameter("t_name");
				String t_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (t_name == null || t_name.trim().length() == 0) {
					errorMsgs.put("t_name", "教職員姓名請勿空白");
				} else if (!t_name.trim().matches(t_nameReg)) {
					errorMsgs.put("t_name", "教職員姓名: 只能是中、英文字母 , 且長度必需在2到10之間");
				}

				String t_gender = req.getParameter("t_gender");

				String t_id = req.getParameter("t_id").trim();
				String t_idReg = "^[A-Za-z][12]\\d{8}$";
				if (t_id == null || t_id.trim().length() == 0) {
					errorMsgs.put("t_id", "身份證字號請勿空白");
				} else if (t_id.trim().length() != 10) {
					errorMsgs.put("t_id", "身份證字號長度必需為10");
				} else if (!t_id.trim().matches(t_idReg)) {
					errorMsgs.put("t_id", "身份證字號格式不正確");
				}

				String t_email = req.getParameter("t_email");
				String t_emailReg = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$";
				if (t_email == null || t_email.trim().length() == 0) {
					errorMsgs.put("t_email", "E-mail請勿空白");
				} else if (t_email.trim().length() < 10 || t_email.trim().length() > 30) {
					errorMsgs.put("t_email", "E-mail長度必需在10到30之間");
				} else if (!t_email.trim().matches(t_emailReg)) {
					errorMsgs.put("t_email", "E-mail格式不正確");
				}

				String t_r_address = req.getParameter("t_r_address");
				String t_r_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{5,60}$";
				if (t_r_address == null || t_r_address.trim().length() == 0) {
					errorMsgs.put("t_r_address", "戶籍地址請勿空白");
				} else if (t_r_address.trim().length() < 5 || t_r_address.trim().length() > 60) {
					errorMsgs.put("t_r_address", "戶籍地址長度必需在5到60之間");
				} else if (!t_r_address.trim().matches(t_r_addressReg)) {
					errorMsgs.put("t_r_address", "戶籍地址只能是中、英文字母、數字 , 且長度必需在5到60之間");
				}

				String t_address = req.getParameter("t_address");
				if (t_address == null || t_address.trim().length() == 0) {
					errorMsgs.put("t_address", "通訊地址請勿空白");
				} else if (t_address.trim().length() < 5 || t_address.trim().length() > 60) {
					errorMsgs.put("t_address", "通訊地址長度必需在5到60之間");
				} else if (!t_address.trim().matches(t_r_addressReg)) {
					errorMsgs.put("t_address", "通訊地址只能是中、英文字母、數字 , 且長度必需在5到60之間");
				}

				String t_job = req.getParameter("t_job");
				if (t_job == null || t_job.trim().length() == 0) {
					errorMsgs.put("t_job", "職稱請勿空白");
				}

				String t_password = req.getParameter("t_password");
				String t_passwordReg = "^[(a-zA-Z0-9_)]{3,15}$";
				if (t_password == null || t_password.trim().length() == 0) {
					errorMsgs.put("t_password", "密碼請勿空白");
				} else if (t_password.trim().length() > 15 || t_password.trim().length() < 3) {
					errorMsgs.put("t_password", "密碼長度必需在3到15之間");
				} else if (!t_password.trim().matches(t_passwordReg)) {
					errorMsgs.put("t_password", "密碼只能是英文字母、數字和_");
				}

				Date t_birthday = null;
				try {
					t_birthday = Date.valueOf(req.getParameter("t_birthday").trim());
				} catch (IllegalArgumentException e) {
					t_birthday = new Date(System.currentTimeMillis());
					errorMsgs.put("t_birthday", "請輸入生日");
				}

				Integer t_status = null;
				try {
					t_status = new Integer(req.getParameter("t_status").trim());
					if (t_status > 2 || t_status < 0) {
						errorMsgs.put("t_status", "狀態請輸入0-2");
					}
				} catch (NumberFormatException e) {
					t_status = 1;
					errorMsgs.put("t_status", "請輸入狀態");
				}

				TeacherService teacherSvc = new TeacherService();
				TeacherVO teacherVO = teacherSvc.getOneTeacher(t_num);
				Part src = req.getPart("t_photo");
				InputStream is = src.getInputStream();
				byte[] t_photo = getImg(is);
				if (t_photo.length == 0) {
					t_photo = teacherVO.getT_photo();
				}

				teacherVO.setT_name(t_name);
				teacherVO.setT_gender(t_gender);
				teacherVO.setT_id(t_id);
				teacherVO.setT_email(t_email);
				teacherVO.setT_r_address(t_r_address);
				teacherVO.setT_address(t_address);
				teacherVO.setT_job(t_job);
				teacherVO.setT_password(t_password);
				teacherVO.setT_birthday(t_birthday);
				teacherVO.setT_status(t_status);
				teacherVO.setT_num(t_num);
				teacherVO.setT_photo(t_photo);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacherVO", teacherVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/teacher/personInfo_update.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				teacherVO = teacherSvc.updateTeacher(t_num, t_name, t_gender, t_id, t_email, t_r_address, t_address,
						t_job, t_password, t_birthday, t_status, t_photo);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("teacherVO", teacherVO);
				session.setAttribute("tp", teacherVO);
				String url = "/back-end/teacher/listSelf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.put("errorMsg", "修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/teacher/personInfo_update.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String t_num = req.getParameter("t_num");

				String t_name = req.getParameter("t_name");
				String t_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (t_name == null || t_name.trim().length() == 0) {
					errorMsgs.put("t_name", "教職員姓名請勿空白");
				} else if (!t_name.trim().matches(t_nameReg)) {
					errorMsgs.put("t_name", "教職員姓名: 只能是中、英文字母 , 且長度必需在2到10之間");
				}

				String t_gender = req.getParameter("t_gender");

				String t_id = req.getParameter("t_id").trim();
				String t_idReg = "^[A-Za-z][12]\\d{8}$";
				if (t_id == null || t_id.trim().length() == 0) {
					errorMsgs.put("t_id", "身份證字號請勿空白");
				} else if (t_id.trim().length() != 10) {
					errorMsgs.put("t_id", "身份證字號長度必需為10");
				} else if (!t_id.trim().matches(t_idReg)) {
					errorMsgs.put("t_id", "身份證字號格式不正確");
				}

				String t_email = req.getParameter("t_email");
				String t_emailReg = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$";
				if (t_email == null || t_email.trim().length() == 0) {
					errorMsgs.put("t_email", "E-mail請勿空白");
				} else if (t_email.trim().length() < 10 || t_email.trim().length() > 30) {
					errorMsgs.put("t_email", "E-mail長度必需在10到30之間");
				} else if (!t_email.trim().matches(t_emailReg)) {
					errorMsgs.put("t_email", "E-mail格式不正確");
				}

				String t_r_address = req.getParameter("t_r_address");
				String t_r_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{5,60}$";
				if (t_r_address == null || t_r_address.trim().length() == 0) {
					errorMsgs.put("t_r_address", "戶籍地址請勿空白");
				} else if (t_r_address.trim().length() < 5 || t_r_address.trim().length() > 60) {
					errorMsgs.put("t_r_address", "戶籍地址長度必需在5到60之間");
				} else if (!t_r_address.trim().matches(t_r_addressReg)) {
					errorMsgs.put("t_r_address", "戶籍地址只能是中、英文字母、數字 , 且長度必需在5到60之間");
				}

				String t_address = req.getParameter("t_address");
				if (t_address == null || t_address.trim().length() == 0) {
					errorMsgs.put("t_address", "通訊地址請勿空白");
				} else if (t_address.trim().length() < 5 || t_address.trim().length() > 60) {
					errorMsgs.put("t_address", "通訊地址長度必需在5到60之間");
				} else if (!t_address.trim().matches(t_r_addressReg)) {
					errorMsgs.put("t_address", "通訊地址只能是中、英文字母、數字 , 且長度必需在5到60之間");
				}

				String t_job = req.getParameter("t_job");
				if (t_job == null || t_job.trim().length() == 0) {
					errorMsgs.put("t_job", "職稱請勿空白");
				}

				String t_password = req.getParameter("t_password");
				String t_passwordReg = "^[(a-zA-Z0-9_)]{3,15}$";
				if (t_password == null || t_password.trim().length() == 0) {
					errorMsgs.put("t_password", "密碼請勿空白");
				} else if (t_password.trim().length() > 15 || t_password.trim().length() < 3) {
					errorMsgs.put("t_password", "密碼長度必需在3到15之間");
				} else if (!t_password.trim().matches(t_passwordReg)) {
					errorMsgs.put("t_password", "密碼只能是英文字母、數字和_");
				}

				Date t_birthday = null;
				try {
					t_birthday = Date.valueOf(req.getParameter("t_birthday").trim());
				} catch (IllegalArgumentException e) {
					t_birthday = new Date(System.currentTimeMillis());
					errorMsgs.put("t_birthday", "請輸入生日");
				}

				Integer t_status = null;
				try {
					t_status = new Integer(req.getParameter("t_status").trim());
					if (t_status > 2 || t_status < 0) {
						errorMsgs.put("t_status", "狀態請輸入0-2");
					}
				} catch (NumberFormatException e) {
					t_status = 1;
					errorMsgs.put("t_status", "請輸入狀態");
				}

				TeacherService teacherSvc = new TeacherService();
				TeacherVO teacherVO = teacherSvc.getOneTeacher(t_num);
				Part src = req.getPart("t_photo");
				InputStream is = src.getInputStream();
				byte[] t_photo = getImg(is);
				if (t_photo.length == 0) {
					t_photo = teacherVO.getT_photo();
				}

				teacherVO.setT_name(t_name);
				teacherVO.setT_gender(t_gender);
				teacherVO.setT_id(t_id);
				teacherVO.setT_email(t_email);
				teacherVO.setT_r_address(t_r_address);
				teacherVO.setT_address(t_address);
				teacherVO.setT_job(t_job);
				teacherVO.setT_password(t_password);
				teacherVO.setT_birthday(t_birthday);
				teacherVO.setT_status(t_status);
				teacherVO.setT_num(t_num);
				teacherVO.setT_photo(t_photo);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacherVO", teacherVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/teacher/update_teacher_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				teacherVO = teacherSvc.updateTeacher(t_num, t_name, t_gender, t_id, t_email, t_r_address, t_address,
						t_job, t_password, t_birthday, t_status, t_photo);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("teacherVO", teacherVO);
				String url = "/back-end/teacher/listOneTeacher.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.put("errorMsg", "修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/teacher/update_teacher_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insertKey".equals(action)) {
			StringBuilder sb = new StringBuilder();
			String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			for(int i = 1; i <= 10; i++){
				int j = (int) (Math.random()*35 + 1);
				sb.append(str1.charAt(j));
				String key = sb.toString();
				session.setAttribute("key", key);
			}
			String url = "/back-end/teacher/addTeacher.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}

		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String t_name = req.getParameter("t_name");
				String t_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (t_name == null || t_name.trim().length() == 0) {
					errorMsgs.put("t_name", "教職員姓名請勿空白");
				} else if (!t_name.trim().matches(t_nameReg)) {
					errorMsgs.put("t_name", "教職員姓名: 只能是中、英文字母和_ , 且長度必需在2到10之間");
				}

				String t_gender = req.getParameter("t_gender");

				String t_id = req.getParameter("t_id").trim();
				String t_idReg = "^[A-Za-z][12]\\d{8}$";
				if (t_id == null || t_id.trim().length() == 0) {
					errorMsgs.put("t_id", "身份證字號請勿空白");
				} else if (t_id.trim().length() != 10) {
					errorMsgs.put("t_id", "身份證字號長度必需為10");
				} else if (!t_id.trim().matches(t_idReg)) {
					errorMsgs.put("t_id", "身份證字號格式不正確");
				}

				String t_email = req.getParameter("t_email");
				String t_emailReg = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$";
				if (t_email == null || t_email.trim().length() == 0) {
					errorMsgs.put("t_email", "E-mail請勿空白");
				} else if (t_email.trim().length() < 10 || t_email.trim().length() > 30) {
					errorMsgs.put("t_email", "E-mail長度必需在10到30之間");
				} else if (!t_email.trim().matches(t_emailReg)) {
					errorMsgs.put("t_email", "E-mail格式不正確");
				}

				String t_r_address = req.getParameter("t_r_address");
				String t_r_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{10,60}$";
				if (t_r_address == null || t_r_address.trim().length() == 0) {
					errorMsgs.put("t_r_address", "戶籍地址請勿空白");
				} else if (t_r_address.trim().length() < 10 || t_r_address.trim().length() > 60) {
					errorMsgs.put("t_r_address", "戶籍地址長度必需在10到60之間");
				} else if (!t_r_address.trim().matches(t_r_addressReg)) {
					errorMsgs.put("t_r_address", "戶籍地址只能是中、英文字母、數字");
				}

				String t_address = req.getParameter("t_address");
				if (t_address == null || t_address.trim().length() == 0) {
					errorMsgs.put("t_address", "通訊地址請勿空白");
				} else if (t_address.trim().length() < 10 || t_address.trim().length() > 60) {
					errorMsgs.put("t_address", "通訊地址長度必需在10到60之間");
				} else if (!t_address.trim().matches(t_r_addressReg)) {
					errorMsgs.put("t_address", "通訊地址只能是中、英文字母、數字 ");
				}

				String t_job = req.getParameter("t_job");
				if (t_job == null || t_job.trim().length() == 0) {
					errorMsgs.put("t_job", "職稱請勿空白");
				}

				String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
				StringBuilder password = new StringBuilder();
				for (int i = 1; i < 7; i++) {
					int j = (int) (Math.random() * 61 + 1);
					password.append(str.charAt(j));
				}
				String t_password = password.toString();

				Date t_birthday = null;
				try {
					t_birthday = Date.valueOf(req.getParameter("t_birthday").trim());
				} catch (IllegalArgumentException e) {
					t_birthday = new Date(System.currentTimeMillis());
					errorMsgs.put("t_birthday", "請輸入生日");
				}

				Integer t_status = null;
				try {
					t_status = new Integer(req.getParameter("t_status").trim());
					if (t_status > 2 || t_status < 0) {
						errorMsgs.put("t_status", "狀態請輸入0-2");
					}
				} catch (NumberFormatException e) {
					t_status = 1;
					errorMsgs.put("t_status", "請輸入狀態");
				}

				Part src = req.getPart("t_photo");
				InputStream is = src.getInputStream();
				byte[] t_photo = getImg(is);
				
				

				boolean b = isTheSameKey(req);
				if(b) {
					String url = "/back-end/teacher/listAllTeacher.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);	
					return;
				}

				
				TeacherVO teacherVO = new TeacherVO();
				teacherVO.setT_name(t_name);
				teacherVO.setT_gender(t_gender);
				teacherVO.setT_id(t_id);
				teacherVO.setT_email(t_email);
				teacherVO.setT_r_address(t_r_address);
				teacherVO.setT_address(t_address);
				teacherVO.setT_job(t_job);
				teacherVO.setT_password(t_password);
				teacherVO.setT_birthday(t_birthday);
				teacherVO.setT_status(t_status);
				teacherVO.setT_photo(t_photo);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacherVO", teacherVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/teacher/addTeacher.jsp");
					failureView.forward(req, res);
					return;
				}
				

				/*************************** 2.開始新增資料 ***************************************/
				TeacherService teacherSvc = new TeacherService();
				teacherVO = teacherSvc.addTeacher(t_name, t_gender, t_id, t_email, t_r_address, t_address, t_job,
						t_password, t_birthday, t_status, t_photo);
				

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				teacherVO = teacherSvc.findOneTeacher(t_id);
				String messageText = "Hello! " + teacherVO.getT_name() + " 您的帳號即為教師編號(" + teacherVO.getT_num() + ")" + "，並請謹記此密碼: " + teacherVO.getT_password() + "\n" +" (已經啟用，可登入修改)"; 
				sendMail(teacherVO.getT_email(), "美心幼兒園教師登入資料", messageText);
				session.removeAttribute("key");
				String url = "/back-end/teacher/listAllTeacher.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("errormsg", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/teacher/addTeacher.jsp");
				failureView.forward(req, res);
			}
		}

		if ("afterLogin".equals(action)) {
			String errorMsg = req.getParameter("errorMsg");
			if (!errorMsg.isEmpty()) {
				req.setAttribute("errorMsg", errorMsg);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Login.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		

		if ("login".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 除錯 **********************************/
			String account = req.getParameter("account");
			String t_accountReg = "^[(T0-9)]{4}$";
			if (account == null || account.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			} else if (account.trim().length() != 4) {
				errorMsgs.add("帳號長度必需為4");
			} else if (!account.trim().matches(t_accountReg)) {
				errorMsgs.add("帳號只能是英文字母、數字");
			}

			String password = req.getParameter("password");
			String t_passwordReg = "^[(a-zA-Z0-9_)]{3,15}$";
			if (password == null || password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			} else if (password.trim().length() > 15 || password.trim().length() < 3) {
				errorMsgs.add("密碼長度必需在3到15之間");
			} else if (!password.trim().matches(t_passwordReg)) {
				errorMsgs.add("密碼只能是英文字母、數字和_");
			}

			TeacherVO loginCheck = new TeacherVO();
			loginCheck.setT_id(account);
			loginCheck.setT_password(password);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Login.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 開始比對帳密 **********************************/
			TeacherService teacherSvc = new TeacherService();
			List<TeacherVO> list = teacherSvc.getAll();
			/*************************** 比對成功 **********************************/
			try {
				for (TeacherVO tp : list) {
					if ((tp.getT_num()).equals(account) && (tp.getT_password()).equals(password)) {
						/*************************** 確認權限 **********************************/
						if ("園長".equals(tp.getT_job())) {
							session.setAttribute("tp", tp);
							try {
//								String url = session.getAttribute("location").toString();
								res.sendRedirect(req.getContextPath() + "/back-end/BackIndex.jsp");
								return;
							} catch (Exception e) {
								RequestDispatcher successView = req.getRequestDispatcher("/back-end/BackIndex.jsp");
								successView.forward(req, res);
								return;
							}

						}
						if ("班導師".equals(tp.getT_job())) {
							session.setAttribute("tp", tp);
							try {
//								String url = session.getAttribute("location").toString();
								res.sendRedirect(req.getContextPath() + "/back-end/BackIndex.jsp");
								return;
							} catch (Exception e) {
								RequestDispatcher successView = req.getRequestDispatcher("/back-end/BackIndex.jsp");
								successView.forward(req, res);
								return;
							}
						}
						if ("老師".equals(tp.getT_job())) {
							session.setAttribute("tp", tp);
							try {
//								String url = session.getAttribute("location").toString();
								res.sendRedirect(req.getContextPath() + "/back-end/BackIndex.jsp");
								return;
							} catch (Exception e) {
								RequestDispatcher successView = req.getRequestDispatcher("/back-end/BackIndex.jsp");
								successView.forward(req, res);
								return;
							}
						}
					}
				}

				/*************************** 帳號或密碼錯 **********************************/
				for (TeacherVO tp : list) {
					if ((tp.getT_num()).equals(account)) {
						tp.setT_num(account);
						errorMsgs.add("密碼錯誤");
						req.setAttribute("errorMsgs", errorMsgs);
						req.setAttribute("tp", tp);
						String url = "/back-end/Login.jsp";
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);
						return;
					}
				}
				throw new Exception();

				/*************************** 帳密全錯 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無此帳號，請重新確認");
				String url = "/back-end/Login.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
		}

		if ("logout".equals(action)) {
			session.invalidate();
			String url = req.getContextPath() + "/back-end/Login.jsp";
			res.sendRedirect(url);
			return;
		}
		
		if("forget1".equals(action)) {
			String url = "/back-end/forget1.jsp";
			res.sendRedirect(url);
			return;
		}
		
		if("forget2".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<>();
			List<TeacherVO> list = new LinkedList<>();
			
			try {
				String t_id = req.getParameter("t_id").trim();
				String t_idReg = "^[A-Za-z][12]\\d{8}$";
				if (t_id == null || t_id.trim().length() == 0) {
					errorMsgs.add("身份證字號請勿空白");
				} else if (t_id.trim().length() != 10) {
					errorMsgs.add("身份證字號長度必需為10");
				} else if (!t_id.trim().matches(t_idReg)) {
					errorMsgs.add("身份證字號格式不正確");
				}
				
				String t_num = req.getParameter("t_num");
				String t_numReg = "^[(T0-9)]{4}$";
				if (t_num == null || (t_num.trim()).length() == 0) {
					errorMsgs.add("請輸入教職員編號");
				} else if (t_num.trim().length() != 4) {
					errorMsgs.add("教職員編號長度必需為4");
				} else if (!t_num.trim().matches(t_numReg)) {
					errorMsgs.add("教職員編號只能是英文字母、數字");
				}
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("errorMsgs", errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/forget1.jsp");
					failureView.forward(req, res);
					return;
				}
				
				TeacherService teacherSvc = new TeacherService();
				list = teacherSvc.getAll();
				
				for(TeacherVO teacherVO : list) {
					if(t_id.equals(teacherVO.getT_id()) && t_num.equals(teacherVO.getT_num())) {
						teacherVO = teacherSvc.findOneTeacher(t_id);
						String messageText = "Hello! " + teacherVO.getT_name() + " 您的密碼為" + teacherVO.getT_password(); 
						sendMail(teacherVO.getT_email(), "美心幼兒園教師登入資料", messageText);
						String url = "/back-end/forget2.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}
				}
				throw new Exception();
					
			} catch(Exception e) {
				errorMsgs.add("查無資料，請重新輸入");
				req.setAttribute("errorMsgs", errorMsgs);
				String url = "/back-end/forget1.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
		if("back".equals(action)) {
			String url = "/back-end/Login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}

	public static byte[] getImg(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i;
		byte[] data = new byte[8192];

		while ((i = is.read(data, 0, data.length)) != -1) {
			baos.write(data, 0, i);
		}
		baos.close();
		return baos.toByteArray();
	}

	public void sendMail(String to, String subject, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●須將myGmail的【安全性較低的應用程式存取權】打開
			final String myGmail = "ixlogic.wu@gmail.com";
			final String myGmail_password = "BBB45678";

			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setText(messageText);

			Transport.send(message);
			System.out.println("傳送成功!");

		} catch (Exception e) {
			System.out.println("傳送失敗!");
		}
	}
	
	public boolean isTheSameKey(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		String key = (String) session.getAttribute("key");
		
		if(key == null) {
			return true;
		}
		
		return false;
	}
	

}
