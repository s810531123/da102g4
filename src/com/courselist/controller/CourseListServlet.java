package com.courselist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.controller.MailService;
import com.course.model.*;
import com.courselist.model.*;
import com.guardian.model.GuardianService;
import com.guardian.model.GuardianVO;
import com.student.model.*;
import com.teacher.model.TeacherService;

@WebServlet("/CourseListServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CourseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			System.out.println("insert");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				Integer cou_num = null;
				try {
					cou_num = new Integer(req.getParameter("cou_num").trim());
				} catch (NumberFormatException e) {
					e.printStackTrace(System.err);
				}

				String st_num = req.getParameter("st_num").trim();
				java.sql.Date cou_list_date = new java.sql.Date(System.currentTimeMillis());
				
				// 查詢選課人數是否而額滿
				CourseService couSvc = new CourseService();
				CourseListService coulSvc = new CourseListService();
				CourseVO courseVO = new CourseVO();
				courseVO = couSvc.getOneCourse(cou_num);
				Integer signCount = coulSvc.getSignUpCount(cou_num);
				System.out.println("SVC1");
				
				if (courseVO.getCou_max() <= signCount) {
					errorMsgs.add("課程人數已滿，請選擇別門課程");
					System.out.println(courseVO.getCou_max());
					System.out.println(signCount);
					System.out.println("滿?");
				}

				CourseListVO courseListVO = new CourseListVO();

				courseListVO.setCou_num(cou_num);
				courseListVO.setSt_num(st_num);
				courseListVO.setPay_status(0);
				courseListVO.setCou_list_date(cou_list_date);
				if (!errorMsgs.isEmpty()) {
					System.out.println("full");
					req.setAttribute("errSignUp", "failSignUp");
					req.setAttribute("cou_num", cou_num);

					String url = "/front-end/course/index_1.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}

				/****************** 開始新增資料 **********************/
				coulSvc.insert(cou_num, st_num, 0, cou_list_date);
				System.out.println("Insert OK");
				
				/********************新增完成寄送郵件********************/
				StudentService stSvc = new StudentService();
				GuardianService gSvc = new GuardianService();
				StudentVO studentVO = stSvc.getStudentBySt_Num(st_num);
				GuardianVO guardianVO = gSvc.getGuardianByGd_Id(studentVO.getGd_id());
				TeacherService tSvc = new TeacherService();
				String subject = "美心幼兒園  選課通知";
				String messageText = "課程名稱 : " + courseVO.getCou_name() + 
									 "\n授課教師 : " + tSvc.getOneTeacher(courseVO.getT_num()).getT_name()
									+"\n開課日期 : " + courseVO.getCou_sdate() 
									+"\n結束日期 : " + courseVO.getCou_edate() 
									+"\n我們會讓您的孩子收穫滿滿";
				
				MailService mail = new MailService();
				mail.sendMail(guardianVO.getGd_email(), subject, messageText);
				System.out.println(guardianVO.getGd_email());
				
				/******************達到開課人數--通知繳費(個人)******************/
				
				if(coulSvc.getSignUpCount(cou_num) > courseVO.getCou_min()) {
					guardianVO = gSvc.getGuardianByGd_Id(stSvc.getStudentBySt_Num(st_num).getGd_id());
					
					String subject1 = "美心幼兒園  課程繳費通知";
					String messageText1 = "課程名稱 : " + courseVO.getCou_name() + 
							 "\n授課教師 : " + tSvc.getOneTeacher(courseVO.getT_num()).getT_name()
							+"\n開課日期 : " + courseVO.getCou_sdate() 
							+"\n結束日期 : " + courseVO.getCou_edate() 
							+"\n已達開課人數 請盡速繳費"
							+"\n繳費連結 : https://da102g4.tk" + req.getContextPath() + "/courselistPay/courselistPay.do?"
									+ "action=pay_status&cou_num=" + courseVO.getCou_num() + "&st_num=" + st_num;
					
					mail.sendMail(guardianVO.getGd_email(), subject1, messageText1);
				}
				
				/******************達到開課人數--通知繳費(全部)******************/
				
				if(coulSvc.getSignUpCount(cou_num) == courseVO.getCou_min()) {
					List<CourseListVO> list =  coulSvc.getAllByCou_num(cou_num);
					
					for(CourseListVO coullist : list) {
						guardianVO = gSvc.getGuardianByGd_Id(stSvc.getStudentBySt_Num(coullist.getSt_num()).getGd_id());
						String subject1 = "美心幼兒園  課程繳費通知";
						String messageText1 = "課程名稱 : " + courseVO.getCou_name() + 
								 	 "\n授課教師 : " + tSvc.getOneTeacher(courseVO.getT_num()).getT_name()
									+"\n開課日期 : " + courseVO.getCou_sdate() 
									+"\n結束日期 : " + courseVO.getCou_edate() 
									+"\n已達開課人數 請盡速繳費"
									+"\n繳費連結 : https://da102g4.tk" + req.getContextPath() + "/courselistPay/courselistPay.do?"
									+ "action=pay_status&cou_num=" + courseVO.getCou_num() + "&st_num=" + st_num;;
						
						mail.sendMail(guardianVO.getGd_email(), subject1, messageText1);
					}
					
				}
				
				/******************達到繳費人數--通知上課(全部)******************/
				System.out.println("PayCount : " +coulSvc.getPayStatusCount(cou_num));
				System.out.println("MIN : " +courseVO.getCou_min());
				if(coulSvc.getPayStatusCount(cou_num) == courseVO.getCou_min()) {
					List<CourseListVO> list =  coulSvc.getAllByCou_num(cou_num);
					
					for(CourseListVO coullist : list) {
						guardianVO = gSvc.getGuardianByGd_Id(stSvc.getStudentBySt_Num(coullist.getSt_num()).getGd_id());
						String subject1 = "美心幼兒園  開課通知";
						String messageText1 = "課程名稱 : " + courseVO.getCou_name() + 
								 	 "\n授課教師 : " + tSvc.getOneTeacher(courseVO.getT_num()).getT_name()
									+"\n開課日期 : " + courseVO.getCou_sdate() 
									+"\n結束日期 : " + courseVO.getCou_edate() 
									+"\n請依開課日期上課";
						
						mail.sendMail(guardianVO.getGd_email(), subject1, messageText1);
					}
					
				}
				
				/***************** 新增完成開始轉交 *********************/
				String url = "/front-end/course/index_1.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				System.out.println("exception");
				errorMsgs.add("選擇課程失敗");
				String url = "/front-end/course/index_1.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}

		}
		
		if("selectCourse".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				StudentVO stVO = (StudentVO) req.getSession().getAttribute("stp");
				CourseListService coulSvc = new CourseListService();
				CourseService couSvc = new CourseService();
				List<Integer> cou_num = new ArrayList<Integer>();
				List<CourseVO> coulist = new ArrayList<CourseVO>();
				CourseVO courseVO = new CourseVO();
				List<CourseListVO> courseListVO = coulSvc.getCou_NumBySt_Num(stVO.getSt_num());
				
				for(CourseListVO c : courseListVO) {
					courseVO = couSvc.getOneCourse(c.getCou_num());
					coulist.add(courseVO);
				}
				System.out.println("size : "+coulist.size());
				if(coulist.size() == 0) {
					errorMsgs.add("您沒選過課");
				}
				
				req.setAttribute("size", coulist.size());
				
				if (!errorMsgs.isEmpty()) {	
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/course/ownCourse.jsp");
					failureView.forward(req, res);
					return;//程式中斷.................
				}
				
				/*****************查詢完成*準備轉交******************/
				req.setAttribute("coulist", coulist);
				
				String url = "/front-end/course/ownCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
				
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/course/ownCourse.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
			if("updateStatus".equals(action)) {
			
				System.out.println("status");
				Integer cou_num = null;
				try {
					cou_num = new Integer(req.getParameter("cou_num").trim());
				} catch(NumberFormatException e) {
					e.printStackTrace(System.err);
				}
				
				String st = req.getParameter("AddOrUpdateStatus");
				
				StudentVO studentVO = (StudentVO) req.getSession().getAttribute("stp");
				CourseListService coulSvc = new CourseListService();
				
				if(st.equals("1")) {
					coulSvc.updateCourseListStatus(cou_num, studentVO.getSt_num(), 0);
				} else {
					coulSvc.updateCourseListStatus(cou_num, studentVO.getSt_num(), 2);
				}
				
				List<CourseVO> coulist = new ArrayList<CourseVO>();
//				StudentVO stVO = (StudentVO) req.getSession().getAttribute("stVO");
				
				CourseService couSvc = new CourseService();
				
				
				CourseListVO courseListVO = new CourseListVO();
				CourseVO courseVO = new CourseVO();
				
				courseListVO.setCou_num(cou_num);
				courseListVO.setSt_num(studentVO.getSt_num());
				courseListVO.setPay_status(2);
				
				
				List<CourseListVO> courseList = coulSvc.getCou_NumBySt_Num(studentVO.getSt_num());
				
				for(CourseListVO c : courseList) {
					courseVO = couSvc.getOneCourse(c.getCou_num());
					coulist.add(courseVO);
				}
				
				
				req.setAttribute("coulist", coulist);
				req.setAttribute("size", coulist.size());
				req.setAttribute("courseListVO", courseListVO);
				String url = "/front-end/course/ownCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		
	}
}
