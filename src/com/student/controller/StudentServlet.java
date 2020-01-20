
package com.student.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.guardian.model.GuardianService;
import com.guardian.model.GuardianVO;
import com.student.model.StudentService;
import com.student.model.StudentVO;
import com.teacher.model.TeacherService;
import com.teacher.model.TeacherVO;

@MultipartConfig
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String st_num = req.getParameter("st_num");
				String st_numReg = "^[S][0-9]{3}$";
				if (st_num == null || st_num.trim().length() == 0) {
					errorMsgs.add("請輸入學生學號");
				} else if (!st_num.trim().matches(st_numReg)) {
					errorMsgs.add("學號只能是S開頭加3位數字");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/student/listAllStudent.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				StudentService studentSvc = new StudentService();
				StudentVO studentVO = studentSvc.getStudentBySt_Num(st_num);
				if (studentVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/student/listAllStudent.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("studentVO", studentVO);
				String url = "/back-end/student/listOneStudent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/student/listAllStudent.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOneBy_SelfClss_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String st_num = req.getParameter("st_num");
				String st_numReg = "^[S][0-9]{3}$";
				if (st_num == null || st_num.trim().length() == 0) {
					errorMsgs.add("請輸入學生學號");
				} else if (!st_num.trim().matches(st_numReg)) {
					errorMsgs.add("學號只能是S開頭加3位數字");
				}

				String cs_num = req.getParameter("cs_num");

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/student/listStudentByCs_num.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				StudentService studentSvc = new StudentService();
				StudentVO studentVO = studentSvc.getStudentBySt_Num(st_num);

				if (studentVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!studentVO.getCs_num().equals(cs_num)) {
					errorMsgs.add("只可查詢您的班級學生");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/student/listStudentByCs_num.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("studentVO", studentVO);
				String url = "/back-end/student/listOneByCsNum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/student/listStudentByCs_num.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String st_num = req.getParameter("st_num");

				/*************************** 2.開始查詢資料 *****************************************/
				StudentService studentSvc = new StudentService();
				StudentVO studentVO = studentSvc.getStudentBySt_Num(st_num);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("studentVO", studentVO);
				String url = "/back-end/student/update_student_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/student/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update_St".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String st_num = req.getParameter("st_num");

				/*************************** 2.開始查詢資料 *****************************************/
				StudentService studentSvc = new StudentService();
				StudentVO studentVO = studentSvc.getStudentBySt_Num(st_num);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("studentVO", studentVO);
				String url = "/front-end/student/update_student_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/personInfo.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updatePassword".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String st_name = req.getParameter("st_name");

				String st_num = req.getParameter("st_num");

				String st_gender = req.getParameter("st_gender");

				String cs_num = req.getParameter("cs_num");

				String st_id = req.getParameter("st_id");

				String gd_id = req.getParameter("gd_id");

				String st_r_address = req.getParameter("st_r_address");

				String st_address = req.getParameter("st_address");

				String st_password = req.getParameter("st_password");
				String st_passwordReg = "^[(a-zA-Z0-9_)]{3,15}$";
				if (st_password == null || st_password.trim().length() == 0) {
					errorMsgs.add("預設密碼請勿空白");
				} else if (!st_password.trim().matches(st_passwordReg)) {
					errorMsgs.add("密碼只能是英文字母、數字和_");
				}

				String st_password_check = req.getParameter("st_password_check");
				if (st_password_check == null || st_password_check.trim().length() == 0) {
					errorMsgs.add("預設密碼請勿空白");
				} else if (!st_password_check.trim().matches(st_passwordReg)) {
					errorMsgs.add("密碼只能是英文字母、數字和_");
				} else if (!st_password.equals(st_password_check)) {
					errorMsgs.add("請確認密碼是否正確");
				}

				Date st_birthday = null;
				try {
					st_birthday = Date.valueOf(req.getParameter("st_birthday"));
				} catch (IllegalArgumentException e) {
					st_birthday = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日");
				}

				Integer st_status = null;
				try {
					st_status = new Integer(req.getParameter("st_status"));
					if (st_status < 0 || st_status > 3) {
						errorMsgs.add("學生狀態請輸入0-3");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入狀態");
				}

				StudentService studentService = new StudentService();
				StudentVO studentVO = studentService.getStudentBySt_Num(st_num);

				studentVO.setSt_num(st_num);
				studentVO.setCs_num(cs_num);
				studentVO.setSt_name(st_name);
				studentVO.setGd_id(gd_id);
				studentVO.setSt_gender(st_gender);
				studentVO.setSt_id(st_id);
				studentVO.setSt_r_address(st_r_address);
				studentVO.setSt_address(st_address);
				studentVO.setSt_password(st_password);
				studentVO.setSt_birthday(st_birthday);
				studentVO.setSt_status(st_status);
				studentVO.setSt_photo(studentVO.getSt_photo());

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("studentVO", studentVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/student/update_student_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/

				studentVO = studentService.updateStudent(st_num, cs_num, gd_id, st_name, st_gender, st_id, st_r_address,
						st_address, st_password, st_birthday, st_status, studentVO.getSt_photo());

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("studentVO", studentVO);
				String url = "/front-end/personInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/student/update_student_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String st_name = req.getParameter("st_name");
				String st_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (st_name == null || st_name.trim().length() == 0) {
					errorMsgs.add("學生姓名請勿空白");
				} else if (!st_name.trim().matches(st_nameReg)) {
					errorMsgs.add("學生姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String st_num = req.getParameter("st_num");
				if (st_num == null || st_num.trim().length() == 0) {
					errorMsgs.add("學號請勿空白");
				}

				String st_gender = req.getParameter("st_gender");
				if (st_gender == null || st_gender.trim().length() == 0) {
					errorMsgs.add("請選擇性別");
				}

				String cs_num = req.getParameter("cs_num");
				if (cs_num == null || cs_num.trim().length() == 0) {
					errorMsgs.add("班別請勿空白");
				}

				String st_id = req.getParameter("st_id");
				String st_idReg = "^[A-Za-z][12]\\d{8}$";
				if (st_id == null || st_id.trim().length() == 0) {
					errorMsgs.add("學生身份證字號請勿空白");
				} else if (st_id.trim().length() != 10) {
					errorMsgs.add("學生身份證字號長度必需為10");
				} else if (!st_id.trim().matches(st_idReg)) {
					errorMsgs.add("學生身份證格式不符");
				}

				String gd_id = req.getParameter("gd_id");
				String gd_idReg = "^[A-Za-z][12]\\d{8}$";
				if (gd_id == null || gd_id.trim().length() == 0) {
					errorMsgs.add("監護人身份證字號請勿空白");
				} else if (gd_id.trim().length() != 10) {
					errorMsgs.add("監護人身份證字號長度必需為10");
				} else if (!gd_id.trim().matches(gd_idReg)) {
					errorMsgs.add("監護人身份證字號格式不符");
				}

				String st_r_address = req.getParameter("st_r_address");
				String st_r_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{5,60}$";
				if (st_r_address == null || st_r_address.trim().length() == 0) {
					errorMsgs.add("戶籍地址請勿空白");
				} else if (st_r_address.trim().length() < 5 || st_r_address.trim().length() > 60) {
					errorMsgs.add("戶籍地址長度必需在5到60之間");
				} else if (!st_r_address.trim().matches(st_r_addressReg)) {
					errorMsgs.add("戶籍地址只能是中、英文字母、數字 , 且長度必需在5到60之間");
				}

				String st_address = req.getParameter("st_address");
				if (st_address == null || st_address.trim().length() == 0) {
					errorMsgs.add("通訊地址請勿空白");
				} else if (st_address.trim().length() < 5 || st_address.trim().length() > 60) {
					errorMsgs.add("通訊地址長度必需在5到60之間");
				} else if (!st_address.trim().matches(st_r_addressReg)) {
					errorMsgs.add("通訊地址只能是中、英文字母、數字 , 且長度必需在5到60之間");
				}

				String st_password = req.getParameter("st_password");
				String st_passwordReg = "^[(a-zA-Z0-9_)]{3,15}$";
				if (st_password == null || st_password.trim().length() == 0) {
					errorMsgs.add("預設密碼請勿空白");
				} else if (!st_password.trim().matches(st_passwordReg)) {
					errorMsgs.add("密碼只能是英文字母、數字和_");
				}

				Date st_birthday = null;
				try {
					st_birthday = Date.valueOf(req.getParameter("st_birthday"));
				} catch (IllegalArgumentException e) {
					st_birthday = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日");
				}

				Integer st_status = null;
				try {
					st_status = new Integer(req.getParameter("st_status"));
					if (st_status < 0 || st_status > 3) {
						errorMsgs.add("學生狀態請輸入0-3");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入狀態");
				}

				StudentService studentService = new StudentService();
				StudentVO studentVO = studentService.getStudentBySt_Num(st_num);
				Part src = req.getPart("st_photo");
				InputStream is = src.getInputStream();
				byte[] st_photo = getImg(is);
				if (st_photo.length == 0) {
					st_photo = studentVO.getSt_photo();
				}

				studentVO.setSt_num(st_num);
				studentVO.setCs_num(cs_num);
				studentVO.setSt_name(st_name);
				studentVO.setGd_id(gd_id);
				studentVO.setSt_gender(st_gender);
				studentVO.setSt_id(st_id);
				studentVO.setSt_r_address(st_r_address);
				studentVO.setSt_address(st_address);
				studentVO.setSt_password(st_password);
				studentVO.setSt_birthday(st_birthday);
				studentVO.setSt_status(st_status);
				studentVO.setSt_photo(st_photo);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("studentVO", studentVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/student/update_student_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/

				studentVO = studentService.updateStudent(st_num, cs_num, gd_id, st_name, st_gender, st_id, st_r_address,
						st_address, st_password, st_birthday, st_status, st_photo);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("studentVO", studentVO);
				String url = "/back-end/student/listOneStudent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/student/update_student_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updatePhoto".equals(action)) {

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String st_num = req.getParameter("st_num");

				StudentService studentService = new StudentService();
				StudentVO studentVO = studentService.getStudentBySt_Num(st_num);

				Part src = req.getPart("st_photo");
				InputStream is = src.getInputStream();
				byte[] st_photo = getImg(is);
				if (st_photo.length == 0) {
					st_photo = studentVO.getSt_photo();
				}
				String cs_num = studentVO.getCs_num();
				String gd_id = studentVO.getGd_id();
				String st_name = studentVO.getSt_name();
				String st_gender = studentVO.getSt_gender();
				String st_id = studentVO.getSt_id();
				String st_r_address = studentVO.getSt_r_address();
				String st_address = studentVO.getSt_address();
				String st_password = studentVO.getSt_password();
				Date st_birthday = studentVO.getSt_birthday();
				Integer st_status = studentVO.getSt_status();

				/*************************** 2.開始修改資料 *****************************************/

				studentVO = studentService.updateStudent(st_num, cs_num, gd_id, st_name, st_gender, st_id, st_r_address,
						st_address, st_password, st_birthday, st_status, st_photo);

				StudentVO stp = studentVO;
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				session.setAttribute("stp", stp);
				req.setAttribute("studentVO", studentVO);
				String url = "/front-end/personInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/personInfo.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);

			TeacherVO tp = (TeacherVO) session.getAttribute("tp");
			if (!tp.getT_job().equals("園長")) {
				res.sendRedirect(req.getContextPath() + "/back-end/BackIndex.jsp");
			}

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String st_name = req.getParameter("st_name");
				String st_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (st_name == null || st_name.trim().length() == 0) {
					errorMsgs.put("st_name", "學生姓名請勿空白");
				} else if (!st_name.trim().matches(st_nameReg)) {
					errorMsgs.put("st_name", "學生姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String gd_id = req.getParameter("gd_id");
				String gd_idReg = "^[A-Za-z][12]\\d{8}$";
				if (gd_id == null || gd_id.trim().length() == 0) {
					errorMsgs.put("gd_id", "監護人身份證字號請勿空白");
				} else if (gd_id.trim().length() != 10) {
					errorMsgs.put("gd_id", "監護人身份證字號長度必需為10");
				} else if (!gd_id.trim().matches(gd_idReg)) {
					errorMsgs.put("gd_id", "監護人身份證字號格式不符");
				}

				String st_gender = req.getParameter("st_gender");
				if (st_gender == null || st_gender.trim().length() == 0) {
					errorMsgs.put("st_gender", "請選擇性別");
				}

				String cs_num = req.getParameter("cs_num");
				if (cs_num == null || cs_num.trim().length() == 0) {
					errorMsgs.put("cs_num", "班別請勿空白");
				}

				String st_id = req.getParameter("st_id");
				String st_idReg = "^[A-Za-z][12]\\d{8}$";
				if (st_id == null || st_id.trim().length() == 0) {
					errorMsgs.put("st_id", "學生身份證字號請勿空白");
				} else if (st_id.trim().length() != 10) {
					errorMsgs.put("st_id", "學生身份證字號長度必需為10");
				} else if (!st_id.trim().matches(st_idReg)) {
					errorMsgs.put("st_id", "學生身份證格式不符");
				}

				String st_r_address = req.getParameter("st_r_address");
				if (st_r_address == null || st_r_address.trim().length() == 0) {
					errorMsgs.put("st_r_address", "戶籍地址請勿空白");
				}

				String st_address = req.getParameter("st_address");
				if (st_address == null || st_address.trim().length() == 0) {
					errorMsgs.put("st_address", "通訊地址請勿空白");
				}

				String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
				StringBuilder password = new StringBuilder();
				for (int i = 1; i < 7; i++) {
					int j = (int) (Math.random() * 61 + 1);
					password.append(str.charAt(j));
				}
				String st_password = password.toString();

				Date st_birthday = null;
				try {
					st_birthday = Date.valueOf(req.getParameter("st_birthday"));
				} catch (IllegalArgumentException e) {
					st_birthday = new Date(System.currentTimeMillis());
					errorMsgs.put("st_birthday", "請輸入生日");
				}

				Integer st_status = null;
				try {
					st_status = new Integer(req.getParameter("st_status"));
				} catch (NumberFormatException e) {
					st_status = 0;
					errorMsgs.put("st_status", "請輸入狀態");
				}

				Part src = req.getPart("st_photo");
				InputStream is = src.getInputStream();
				byte[] st_photo = getImg(is);

				GuardianService guardianSvc = new GuardianService();
				GuardianVO guardianVO = new GuardianVO();
				guardianVO = guardianSvc.getGuardianByGd_Id(gd_id);

				StudentVO studentVO = new StudentVO();
				studentVO.setCs_num(cs_num);
				studentVO.setSt_name(st_name);
				studentVO.setGd_id(gd_id);
				studentVO.setSt_gender(st_gender);
				studentVO.setSt_id(st_id);
				studentVO.setSt_r_address(st_r_address);
				studentVO.setSt_address(st_address);
				studentVO.setSt_password(st_password);
				studentVO.setSt_birthday(st_birthday);
				studentVO.setSt_status(st_status);
				studentVO.setSt_photo(st_photo);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("studentVO", studentVO);
					req.setAttribute("guardianVO", guardianVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/student/addStudent.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				StudentService studentSvc = new StudentService();
				studentVO = studentSvc.addStudent(cs_num, gd_id, st_name, st_gender, st_id, st_r_address, st_address,
						st_password, st_birthday, st_status, st_photo);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				studentVO = studentSvc.getStudentBySt_Id(st_id);
				String messageText = "Hello! " + guardianVO.getGd_name() + " 您的小孩帳號即為學生編號(" + studentVO.getSt_num()
						+ ")" + "，並請謹記此密碼: " + studentVO.getSt_password() + "\n" + " (已經啟用，可登入修改)";
				sendMail(guardianVO.getGd_email(), "美心幼兒園家長登入資料", messageText);
				req.setAttribute("studentVO", studentVO);
				String url = "/back-end/student/listAllStudent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.put("otherError", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/student/addStudent.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert_student".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String st_name = req.getParameter("st_name");
				String st_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (st_name == null || st_name.trim().length() == 0) {
					errorMsgs.put("st_name", "學生姓名請勿空白");
				} else if (!st_name.trim().matches(st_nameReg)) {
					errorMsgs.put("st_name", "學生姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String gd_id = req.getParameter("gd_id");

				String st_gender = req.getParameter("st_gender");
				
				String cs_num = req.getParameter("cs_num");
				
				String st_id = req.getParameter("st_id");
				String st_idReg = "^[A-Za-z][12]\\d{8}$";
				if (st_id == null || st_id.trim().length() == 0) {
					errorMsgs.put("st_id", "學生身份證字號請勿空白");
				} else if (st_id.trim().length() != 10) {
					errorMsgs.put("st_id", "學生身份證字號長度必需為10");
				} else if (!st_id.trim().matches(st_idReg)) {
					errorMsgs.put("st_id", "學生身份證格式不符");
				}
				
				String st_r_address = req.getParameter("st_r_address");
				if (st_r_address == null || st_r_address.trim().length() == 0) {
					errorMsgs.put("st_r_address", "戶籍地址請勿空白");
				}
				
				String st_address = req.getParameter("st_address");
				if (st_address == null || st_address.trim().length() == 0) {
					errorMsgs.put("st_address", "通訊地址請勿空白");
				}
				
				String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
				StringBuilder password = new StringBuilder();
				for (int i = 1; i < 7; i++) {
					int j = (int) (Math.random() * 61 + 1);
					password.append(str.charAt(j));
				}
				String st_password = password.toString();
				
				Date st_birthday = null;
				try {
					st_birthday = Date.valueOf(req.getParameter("st_birthday"));
				} catch (IllegalArgumentException e) {
					st_birthday = new Date(System.currentTimeMillis());
					errorMsgs.put("st_birthday", "請輸入生日");
				}
				
				Integer st_status = new Integer(req.getParameter("st_status"));
				
				Part src = req.getPart("st_photo");
				InputStream is = src.getInputStream();
				byte[] st_photo = getImg(is);
				
				GuardianService guardianSvc = new GuardianService();
				GuardianVO guardianVO = new GuardianVO();
				guardianVO = guardianSvc.getGuardianByGd_Id(gd_id);
				
				StudentVO studentVO = new StudentVO();
				studentVO.setCs_num(cs_num);
				studentVO.setSt_name(st_name);
				studentVO.setGd_id(gd_id);
				studentVO.setSt_gender(st_gender);
				studentVO.setSt_id(st_id);
				studentVO.setSt_r_address(st_r_address);
				studentVO.setSt_address(st_address);
				studentVO.setSt_password(st_password);
				studentVO.setSt_birthday(st_birthday);
				studentVO.setSt_status(st_status);
				studentVO.setSt_photo(st_photo);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("studentVO", studentVO);
					req.setAttribute("guardianVO", guardianVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/RegistrationForm2.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始新增資料 ***************************************/
				StudentService studentSvc = new StudentService();
				studentVO = studentSvc.addStudent(cs_num, gd_id, st_name, st_gender, st_id, st_r_address, st_address,
						st_password, st_birthday, st_status, st_photo);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				session.removeAttribute("guardianVO");
				String url = "/front-end/RegistrationSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.put("otherError", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/RegistrationForm2.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("agreeRegistration".equals(action)) {
			
			String st_num = req.getParameter("st_num");
			StudentService studentSvc = new StudentService();
			StudentVO studentVO = studentSvc.getStudentBySt_Num(st_num);
			
			String cs_num = studentVO.getCs_num();	
			String gd_id = studentVO.getGd_id();
			String st_name = studentVO.getSt_name();
			String st_gender = studentVO.getSt_gender();
			String st_id = studentVO.getSt_id();
			String st_r_address = studentVO.getSt_r_address();
			String st_address = studentVO.getSt_address();
			String st_password = studentVO.getSt_password();
			Date st_birthday = studentVO.getSt_birthday();
			byte[] st_photo = studentVO.getSt_photo();

			studentVO = studentSvc.updateStudent(st_num, cs_num, gd_id, st_name, st_gender, st_id, st_r_address, st_address, st_password, st_birthday, 0, st_photo);
			
			GuardianService guardianSvc = new GuardianService();
			GuardianVO guardianVO = guardianSvc.getGuardianByGd_Id(gd_id);
			String messageText = "Hello! " + guardianVO.getGd_name() + "\n" 
					+ " 您的小孩帳號即為學生編號:" + studentVO.getSt_num() + "\n"
					+ "並請謹記此密碼: " + studentVO.getSt_password() + "\n" + " (已經啟用，可登入修改)";
			sendMail(guardianVO.getGd_email(), "美心幼兒園家長登入資料", messageText);
			req.setAttribute("studentVO", studentVO);
			String url = "/back-end/student/listOneStudent.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);				
		}
		
		if("disagreeRegistration".equals(action)) {

			try {
				
				String st_num = req.getParameter("st_num");
				StudentService stSvc = new StudentService();
				GuardianService gdSvc = new GuardianService();
				StudentVO stVO = stSvc.getStudentBySt_Num(st_num);
				GuardianVO guardianVO = gdSvc.getGuardianByGd_Id(stVO.getGd_id());
				
				String messageText = "Hello! " + guardianVO.getGd_name() + "\n" + "您的小孩未註冊成功，請您見諒並感謝您的支持";
				sendMail(guardianVO.getGd_email(), "美心幼兒園家長登入資料", messageText);
				
				stSvc.deleteStudent(st_num);
				gdSvc.deleteGuardian(guardianVO.getGd_id());
				
				String url = "/back-end/student/Registration.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				String url = "/back-end/student/Registration.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			
			
		}
		

		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 除錯 **********************************/
			String account = req.getParameter("account");
			String st_accountReg = "^[(S0-9)]{4}$";
			if (account == null || account.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			} else if (account.trim().length() != 4) {
				errorMsgs.add("帳號長度必需為4");
			} else if (!account.trim().matches(st_accountReg)) {
				errorMsgs.add("帳號只能是S開頭加3位數字");
			}

			String password = req.getParameter("password");
			String st_passwordReg = "^[(a-zA-Z0-9_)]{3,15}$";
			if (password == null || password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			} else if (password.trim().length() > 15 || password.trim().length() < 3) {
				errorMsgs.add("密碼長度必需在3到15之間");
			} else if (!password.trim().matches(st_passwordReg)) {
				errorMsgs.add("密碼只能是英文字母、數字和_");
			}

			StudentVO loginCheck = new StudentVO();
			loginCheck.setSt_num(account);
			loginCheck.setSt_password(password);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Login.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 開始比對帳密 **********************************/
			StudentService svc = new StudentService();
			List<StudentVO> list = svc.getAll();

			/*************************** 比對成功 **********************************/
			try {
				for (StudentVO stp : list) {
					if (stp.getSt_num().equals(account) && stp.getSt_password().equals(password)) {
						session.setAttribute("stp", stp);
						RequestDispatcher successView = req.getRequestDispatcher("/front-end/FrontIndex.jsp");
						successView.forward(req, res);
						return;
					}
				}

				/*************************** 比對失敗 **********************************/
				for (StudentVO stp : list) {
					if (stp.getSt_num().equals(account)) {
						stp.setSt_num(account);
						stp.setSt_name(null);
						errorMsgs.add("密碼錯誤");
						req.setAttribute("stp", stp);
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Login.jsp");
						failureView.forward(req, res);
						return;
					}
				}
				throw new Exception();

				/*************************** 其他錯誤 **********************************/
			} catch (Exception e) {
				errorMsgs.add("帳號錯誤，請重新確認");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Login.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if ("logout".equals(action)) {
			session.invalidate();
			String url = req.getContextPath() + "/front-end/Login.jsp";
			res.sendRedirect(url);
			return;
		}

		if ("forget3".equals(action)) {

			List<String> errorMsgs = new LinkedList<>();
			StudentVO studentVO = new StudentVO();

			try {
				String st_id = req.getParameter("st_id").trim();

				String st_num = req.getParameter("st_num");

				StudentService studentSvc = new StudentService();
				studentVO = studentSvc.getStudentBySt_Num(st_num);

				if (st_id.equals(studentVO.getSt_id()) && st_num.equals(studentVO.getSt_num())) {
					GuardianService guardianSvc = new GuardianService();
					GuardianVO guardianVO = guardianSvc.getGuardianByGd_Id(studentVO.getGd_id());
					String messageText = "Hello! " + guardianVO.getGd_name() + " 您的密碼為" + studentVO.getSt_password();
					sendMail(guardianVO.getGd_email(), "美心幼兒園教師登入資料", messageText);
					String url = "/front-end/forget4.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				}
				throw new Exception();

			} catch (Exception e) {
				errorMsgs.add("查無資料，請重新輸入");
				req.setAttribute("errorMsgs", errorMsgs);
				String url = "/front-end/forget3.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
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
	
	

}
