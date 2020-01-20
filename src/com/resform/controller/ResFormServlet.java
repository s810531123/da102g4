package com.resform.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.resform.model.*;
import com.teacher.model.*;

public class ResFormServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");		
		

		if ("GetResFormByDay".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("res_Date");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入日期");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ResForm/search_page.jsp");
					failureView.forward(req, res);
					return;
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ResForm/search_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ResformService resformSvc = new ResformService();
				List<ResformVO> listVO = resformSvc.getResByres_Date(str);
				
				System.out.println(listVO);
				
				if (listVO == null || listVO.isEmpty()) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ResForm/search_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("listVO", listVO); // 資料庫取出的VO物件,存入req
//				System.out.println("listVO size:"+listVO.size());
//				System.out.println(listVO.get(0).getRes_Email());

				String url = "/back-end/ResForm/GetResFormSelect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 GetResFormSelect.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ResForm/search_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自update_res_list.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer res_Num = new Integer(req.getParameter("res_Num"));

				/*************************** 2.開始查詢資料 ****************************************/
				ResformService resformSvc = new ResformService();
				ResformVO resformVO = resformSvc.getOneRes(res_Num);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("resformVO", resformVO); // 資料庫取出的resformVO物件,存入req
				String url = "/back-end/ResForm/update_res_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_res_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ResForm/update_res_list.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_res_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer res_Num = new Integer(req.getParameter("res_Num").trim());

				String t_Num = req.getParameter("t_Num");
				if (t_Num == null || t_Num.trim().length() == 0) {
					errorMsgs.add("導覽教師姓名: 請勿空白");
				}

				String res_Name = req.getParameter("res_Name");
				String res_NameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (res_Name == null || res_Name.trim().length() == 0) {
					errorMsgs.add("預約人姓名: 請勿空白");
				} else if (!res_Name.trim().matches(res_NameReg)) {
					errorMsgs.add("預約人姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");

				}

				String res_Email = req.getParameter("res_Email");
				String res_EmailReg = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$";
				if (res_Email == null || res_Email.trim().length() == 0) {
					errorMsgs.add("預約人信箱: 請勿空白");
				} else if (!res_Email.trim().matches(res_EmailReg)) {
					errorMsgs.add("不符合電子信箱的格式!");
				}

				String res_Phone = req.getParameter("res_Phone");
				String res_PhoneReg = "^09[0-9]{8}$";
				if (res_Phone == null || res_Phone.trim().length() == 0) {
					errorMsgs.add("預約人手機號碼: 請勿空白");
				} else if (!res_Phone.trim().matches(res_PhoneReg)) {
					errorMsgs.add("不符合電子信箱的格式!");
				}

				java.sql.Timestamp res_Date = null;
				try {
					res_Date = java.sql.Timestamp.valueOf(req.getParameter("res_Date").trim());
				} catch (IllegalArgumentException e) {
					res_Date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入預約日期!");
				}
				String res_Msg = req.getParameter("res_Msg");
				
				
				ResformVO resformVO = new ResformVO();
				resformVO.setRes_Num(res_Num);
				resformVO.setT_Num(t_Num);
				resformVO.setRes_Name(res_Name);
				resformVO.setRes_Email(res_Email);
				resformVO.setRes_Phone(res_Phone);
				resformVO.setRes_Date(res_Date);
				resformVO.setRes_Msg(res_Msg);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("resformVO", resformVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ResForm/update_res_list.jsp");
					failureView.forward(req, res);
					return;
				}
			
				TeacherVO teacherVO = new TeacherService().getOneTeacher(t_Num);

				/*************************** 2.開始修改資料 *****************************************/
				ResformService resformSvc = new ResformService();
				resformVO = resformSvc.update(res_Num, t_Num, res_Name, res_Email, res_Phone, res_Date, res_Msg);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				
				
				req.setAttribute("resformVO", resformVO);
				req.setAttribute("teacherVO", teacherVO);
				String url = "/back-end/ResForm/update_res_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ResForm/update_res_list.jsp");
				failureView.forward(req, res);
			}
		}

//
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String t_Num = req.getParameter("t_Num");
				
				if (t_Num == null) {
					t_Num= "T001";	//預設教師為T001			
				}

				
				String res_Name = req.getParameter("res_Name");
				String res_NameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (res_Name == null || res_Name.trim().length() == 0) {
					errorMsgs.add("預約人姓名: 請勿空白");
				} else if (!res_Name.trim().matches(res_NameReg)) {
					errorMsgs.add("預約人姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");

				} 
				
				String res_Email = req.getParameter("res_Email");
				String res_EmailReg = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$";
				if (res_Email == null || res_Email.trim().length() == 0) {
					errorMsgs.add("預約人信箱: 請勿空白");
				} else if (!res_Email.trim().matches(res_EmailReg)) {
					errorMsgs.add("不符合電子信箱的格式!");
				}

				String res_Phone = req.getParameter("res_Phone");
				String res_PhoneReg = "^09[0-9]{8}$";
				if (res_Phone == null || res_Phone.trim().length() == 0) {
					errorMsgs.add("預約人手機號碼: 請勿空白");
				} else if (!res_Phone.trim().matches(res_PhoneReg)) {
					errorMsgs.add("不符合電子信箱的格式!");
				}

				java.sql.Timestamp res_Date = null;
				try {
					res_Date = java.sql.Timestamp.valueOf(req.getParameter("res_Date").trim() + ":00");
					
				} catch (IllegalArgumentException e) {
					res_Date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入預約日期!");
				}
				String res_Msg = req.getParameter("res_Msg");

				// Send the use back to the form, if there were errors
				ResformVO resformVO = new ResformVO();
				resformVO.setT_Num(t_Num);
				resformVO.setRes_Name(res_Name);
				resformVO.setRes_Email(res_Email);
				resformVO.setRes_Phone(res_Phone);
				resformVO.setRes_Date(res_Date);
				resformVO.setRes_Msg(res_Msg);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("resformVO", resformVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ResForm/ResAdd_front3.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 *****************************************/
				ResformService resformSvc = new ResformService();
				resformVO = resformSvc.addResform(t_Num, res_Name, res_Email, res_Phone, res_Date, res_Msg);

				/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
				req.setAttribute("resformVO", resformVO);
		
				String url = "/front-end/ResForm/ResAddSuccess.jsp";
			//	String url = "/front-end/ResForm/ResAdd_front.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 4.新增完成,準備寄出預約單資料 *************/

				
				//輸入主旨
				String subject = "美心幼兒園預約單通知";
			
				//拿取預約人資料
				String phone = resformVO.getRes_Phone();
				String to = resformVO.getRes_Email();
				String ch_name = resformVO.getRes_Name();
				java.sql.Timestamp resdate = resformVO.getRes_Date();
				
				String restime = "";  
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
				restime = sdf.format(resdate);  
				//收件者內文
				String messageText = 
				"您已預約成功! 以下是您的預約資訊" + "\n" +
				"預約人姓名：" + ch_name + "\n" +
				"預約人手機號碼：" + phone + "\n" +
				"預約時間：" + restime + "\n" +
				"期待您的光臨^-^~" ;
				 
				
				MailService mailService = new MailService();
			    mailService.sendMail(to, subject, messageText);				
				

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ResForm/ResAdd_front.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer res_Num = new Integer(req.getParameter("res_Num"));

				/*************************** 2.開始刪除資料 ***************************************/
				ResformService resSvc = new ResformService();
				resSvc.deleteResform(res_Num);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/ResForm/deleteResForm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ResForm/deleteResForm.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
