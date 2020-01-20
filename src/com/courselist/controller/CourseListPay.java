package com.courselist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.model.CourseService;
import com.course.model.CourseVO;
import com.courselist.model.CourseListService;
import com.courselist.model.CourseListVO;
import com.student.model.StudentVO;

@WebServlet("/CourseListPay")
public class CourseListPay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		if ("pay_status".equals(action)) {
			Integer cou_num = new Integer(req.getParameter("cou_num").trim());
			String st_num = req.getParameter("st_num");

			req.setAttribute("cou_num", cou_num);
			req.setAttribute("st_num", st_num);
			String url = "/front-end/course/payCourse.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

//			CourseListService coulSvc = new CourseListService();
//			coulSvc.getOneCourseList(cou_num, st_num);
//			coulSvc.updateCourseListStatus(cou_num, st_num, 1);
		}

//		if("paypay".equals(action)) {
//			Integer cou_num = new Integer(req.getParameter("cou_num").trim());
//			String st_num = req.getParameter("st_num");
//			System.out.println("OK?");
//			CourseListService coulSvc = new CourseListService();
//			coulSvc.getOneCourseList(cou_num, st_num);
//			coulSvc.updateCourseListStatus(cou_num, st_num, 1);
//			
//			
//			String url = "/front-end/course/ownCourse.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("paypay".equals(action)) {
			Integer cou_num = new Integer(req.getParameter("cou_num").trim());
			String st_num = req.getParameter("st_num");
			System.out.println("OK?");
			CourseListService coulSvc = new CourseListService();
			coulSvc.getOneCourseList(cou_num, st_num);
			coulSvc.updateCourseListStatus(cou_num, st_num, 1);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//				StudentVO stVO = (StudentVO) req.getSession().getAttribute("stp");
//				CourseListService coulSvc = new CourseListService();
				CourseService couSvc = new CourseService();
//				List<Integer> cou_num = new ArrayList<Integer>();
				List<CourseVO> coulist = new ArrayList<CourseVO>();
				CourseVO courseVO = new CourseVO();
				List<CourseListVO> courseListVO = coulSvc.getCou_NumBySt_Num(st_num);

				for (CourseListVO c : courseListVO) {
					courseVO = couSvc.getOneCourse(c.getCou_num());
					coulist.add(courseVO);
				}
				System.out.println("size : " + coulist.size());
				if (coulist.size() == 0) {
					errorMsgs.add("您沒選過課");
				}
				req.setAttribute("coulist", coulist);
				req.setAttribute("size", coulist.size());

				String url = "/front-end/course/ownCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/course/ownCourse.jsp");
				failureView.forward(req, res);
			}
		}

	}
}