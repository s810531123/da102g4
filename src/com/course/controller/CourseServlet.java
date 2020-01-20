package com.course.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.course.model.CourseDAO;
import com.course.model.CourseVO;
import com.courselist.model.CourseListService;
import com.guardian.model.GuardianService;
import com.guardian.model.GuardianVO;
import com.onduty.model.OnDutyJDBCDAO;
import com.onduty.model.OnDutyVO;
import com.schedule.model.TScheduleJDBCDAO;
import com.schedule.model.TScheduleVO;
import com.student.model.StudentVO;
import com.teacher.model.TeacherVO;
import com.course.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
/*WS*/
@ServerEndpoint("/CourseWS/Hao")
/*WS*/
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*WS*/
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	
	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		connectedSessions.add(userSession);
		String text = String.format("Session ID = %s, connected;", userSession.getId());
		System.out.println(text);
	}
	
	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		for (Session session : connectedSessions) {
			if (session.isOpen()) {
				JSONObject obj = new JSONObject(message);
//				System.out.println(obj.toString());
				String action = (String) obj.get("action");
//				obj.put("newCourse", "newCourse");
				System.out.println("action : "+action);
				System.out.println("OBJ : " + obj.toString());
				
				if("newCourse".equals(action)) {
					session.getAsyncRemote().sendText(message);					
				}
				
				if("signCourse".equals(action)) {
					String num =  (String) obj.get("cou_num");
					Integer cou_num = new Integer(num);
					System.out.println("cou_num : "+cou_num);
					CourseListService coulSvc = new CourseListService();
					Integer count = coulSvc.getSignUpCount(cou_num);
					obj.put("count", count);
					obj.put("cou_num", cou_num);
					session.getAsyncRemote().sendText(obj.toString());
				}
			}
				
		}
		System.out.println("Message received: " + message);
	}
	/*WS*/
	
	protected void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		
		System.out.println(action);
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("cou_num");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入課程編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/course/listAllCourse.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer cou_num = null;
				try {
					cou_num = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("課程編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/course/listAllCourse.jsp");
					failureView.forward(req, res);
					return;//程式中斷.................
				}
				
				/***************************2.開始查詢資料*****************************************/
				CourseService couSvc = new CourseService();
				CourseVO courseVO = couSvc.getOneCourse(cou_num);
				if (courseVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/course/listAllCourse.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("courseVO", courseVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/course/listAllCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/course/listAllCourse.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		//Update Course Status
		if("updateStatus".equals(action)) {
			
			System.out.println("status");
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();

			JSONObject obj = new JSONObject();
			
			Integer cou_status = null;
			Integer cou_num = null;
			String requestURL = req.getParameter("requestURL");
			try {
				cou_num = new Integer(req.getParameter("cou_num").trim());
				cou_status = new Integer(req.getParameter("cou_status").trim());
			}catch (NumberFormatException num) {
				cou_status = 1;
			}
			
			if(cou_status == 1) {
				cou_status = 0;
			} else if (cou_status == 0) {
				cou_status = 1;
			}
				
			CourseService couSvc = new CourseService();
			CourseVO courseVO = couSvc.updateCourseStatus(cou_status, cou_num);
			
			obj.put("cou_status", cou_status);
			System.out.println(obj.toString());
			out.write(obj.toString());
			out.flush();
			out.close();
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			
			
//			if(requestURL.equals("/back-end/course/listAllCourse.jsp") ) {
//				req.setAttribute("courseVO", courseVO); // 資料庫update成功後,正確的的CourseVO物件,存入req
//				System.out.println("listAll");
//			}
//			
//			if(requestURL.equals("/back-end/course/listCourse_ByCompositeQuery.jsp")) {
//				HttpSession session = req.getSession();
//				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
//				List<CourseVO> list  = couSvc.getAll(map);
//				req.setAttribute("listCourse_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
//			}
			
//          System.out.println(requestURL);
			String url = requestURL;
			
//			String url = "/back-end/course/listAllCourse.jsp";
			
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//			successView.forward(req, res);
			
			
//			req.setAttribute("courseVO", courseVO); // 資料庫取出的empVO物件,存入req
//			String url = "/back-end/course/listAllCourse.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
		}
		
		
		
		if ("insert".equals(action)) { // 來自listAllCourse.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String cou_name = req.getParameter("cou_name");
				String cou_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cou_name == null || cou_name.trim().length() == 0) {
					errorMsgs.add("課程名稱　　　");
				} else if (!cou_name.trim().matches(cou_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("課程名稱只能2-10個中、英文或數字　　");
				}
				
				String cou_introd = req.getParameter("cou_introd").trim();
				if (cou_introd == null || cou_introd.trim().length() == 0) {
					errorMsgs.add("課程介紹　　　");
				}

				String t_num = req.getParameter("t_num").trim();
				if (t_num == null || t_num.trim().length() == 0) {
					errorMsgs.add("教師編號　　");
				}
					
				Part src = req.getPart("cou_pic");
				InputStream is = src.getInputStream();
				byte[] cou_pic = getImg(is);	
		
				java.sql.Date cou_sdate = null;
				try {
					cou_sdate = java.sql.Date.valueOf(req.getParameter("cou_sdate").trim());
					if(new java.sql.Date(System.currentTimeMillis()).after(cou_sdate)) {
						errorMsgs.add("你要在過去開課?  !　　");
					}
				} catch (IllegalArgumentException e) {
					cou_sdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("開課日期  !　　");
				}

				java.sql.Date cou_edate = null;
				try {
					cou_edate = java.sql.Date.valueOf(req.getParameter("cou_edate").trim());
				} catch (IllegalArgumentException e) {
					cou_edate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("結束日期  !　　");
				}
				
				Integer cou_cost = null;
				try {
					cou_cost = new Integer(req.getParameter("cou_cost").trim());
				} catch (NumberFormatException e) {
					cou_cost = 0;
					errorMsgs.add("課程費用　　");
				}
				
				Integer cou_min = null;
				try {
					cou_min = new Integer(req.getParameter("cou_min").trim());
				} catch (NumberFormatException e) {
					cou_min = 0;
					errorMsgs.add("開課人數　　");
				}
				
				Integer cou_max = null;
				try {
					cou_max = new Integer(req.getParameter("cou_max").trim());
				} catch (NumberFormatException e) {
					cou_max = 0;
					errorMsgs.add("人數上限");
				}

				CourseVO courseVO = new CourseVO();
				courseVO.setCou_name(cou_name);
				courseVO.setCou_introd(cou_introd);
				courseVO.setCou_sdate(cou_sdate);
				courseVO.setCou_edate(cou_edate);
				courseVO.setT_num(t_num);
				courseVO.setCou_min(cou_min);
				courseVO.setCou_max(cou_max);
				courseVO.setCou_cost(cou_cost);
				courseVO.setCou_pic(cou_pic);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("errInsert", "failInsert");
					req.setAttribute("courseVO", courseVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/course/listAllCourse.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				CourseService couSvc = new CourseService();
				courseVO = couSvc.addCourse(t_num, cou_name, cou_introd, cou_cost, cou_min, cou_max, cou_sdate, cou_edate, cou_pic);
	
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("courseVO", courseVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/course/listAllCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				req.setAttribute("err", "fail");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/course/listAllCourse.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自listAllCourse.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/back-end/course/listAllCourse.jsp】 或 【 /back-end/course/listCourse_ByCompositeQuery.jsp】
			System.out.println(requestURL);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String cou_name = req.getParameter("cou_name");
				
				String cou_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if (cou_name == null || cou_name.trim().length() == 0) {
					errorMsgs.add("課程名稱　　");
				} else if (!cou_name.trim().matches(cou_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("課程名稱只能2-10個中、英文或數字　　");
				}
				
				String cou_introd = req.getParameter("cou_introd").trim();
				if (cou_introd == null || cou_introd.trim().length() == 0) {
					errorMsgs.add("課程介紹　　");
				}

				String t_num = req.getParameter("t_num").trim();
				if (t_num == null || t_num.trim().length() == 0) {
					errorMsgs.add("教師編號　　");
				}
				
				Part src = req.getPart("cou_pic");
				InputStream is = src.getInputStream();
				byte[] cou_pic = getImg(is);
				
				
				
				java.sql.Date cou_sdate = null;
				try {
					cou_sdate = java.sql.Date.valueOf(req.getParameter("cou_sdate").trim());
					if(new java.sql.Date(System.currentTimeMillis()).after(cou_sdate)) {
						errorMsgs.add("你要在過去開課?  !　　");
					}
				} catch (IllegalArgumentException e) {
					cou_sdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("開課日期  !　　");
				}
				
				
				
				java.sql.Date cou_edate = null;
				try {
					cou_edate = java.sql.Date.valueOf(req.getParameter("cou_edate").trim());
				} catch (IllegalArgumentException e) {
					cou_edate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("結束日期  !　　");
				}
				
				
				Integer cou_cost = null;
				try {
					cou_cost = new Integer(req.getParameter("cou_cost").trim());
				} catch (NumberFormatException e) {
					cou_cost = 0;
					errorMsgs.add("課程費用　　");
				}
				
				Integer cou_num = null;
				try {
					cou_num = new Integer(req.getParameter("cou_num").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add(" ");
				}
				
				Integer cou_min = null;
				try {
					cou_min = new Integer(req.getParameter("cou_min").trim());
				} catch (NumberFormatException e) {
					cou_min = 0;
					errorMsgs.add("人數上限");
				}

				Integer cou_max = null;
				try {
					cou_max = new Integer(req.getParameter("cou_max").trim());
				} catch (NumberFormatException e) {
					cou_max = 0;
					errorMsgs.add("人數上限");
				}
				
				CourseVO courseVO = new CourseVO();
				courseVO.setCou_num(cou_num);
				courseVO.setCou_name(cou_name);
				courseVO.setCou_introd(cou_introd);
				courseVO.setCou_sdate(cou_sdate);
				courseVO.setCou_edate(cou_edate);
				courseVO.setT_num(t_num);
				courseVO.setCou_min(cou_min);
				courseVO.setCou_max(cou_max);
				courseVO.setCou_cost(cou_cost);
				courseVO.setCou_pic(cou_pic);
				
				CourseService couSvc = new CourseService();
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("err");
					req.setAttribute("errUpdate", "failUpdate");
					req.setAttribute("courseVO", courseVO); // 含有輸入格式錯誤的courseVO物件,也存入req
					
					if(requestURL.equals("/back-end/course/listCourse_ByCompositeQuery.jsp")) {
						HttpSession session = req.getSession();
						Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
						List<CourseVO> list  = couSvc.getAll(map);
						req.setAttribute("listCourse_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
					}
					
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 ***************************************/
				if(cou_pic.length == 0) {
					courseVO = couSvc.updateCourseWithOutPic(t_num, cou_name, cou_introd, cou_cost, cou_min, cou_max, cou_sdate, cou_edate, cou_num);
				} else {
					courseVO = couSvc.updateCourse(t_num, cou_name, cou_introd, cou_cost, cou_min, cou_max ,cou_sdate, cou_edate,cou_pic, cou_num);
				}

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				
				if(requestURL.equals("/back-end/course/listAllCourse.jsp") ) {
					req.setAttribute("courseVO", courseVO); // 資料庫update成功後,正確的的CourseVO物件,存入req
				}
				
				if(requestURL.equals("/back-end/course/listCourse_ByCompositeQuery.jsp")) {
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<CourseVO> list  = couSvc.getAll(map);
					req.setAttribute("listCourse_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
				}
				
				String url = requestURL;
				
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				req.setAttribute("errUpdate", "failUpdate");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/course/listAllCourse.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("listCourse_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("com");
			
			/******************************/

//			StudentVO studentVO = new StudentVO();
//			studentVO.setGd_id("G123456789");
//			
//			GuardianVO gVO = new GuardianVO();
//			
//			GuardianService gSvc = new GuardianService();
//			gVO = gSvc.getGuardianByGd_Id(studentVO.getGd_id());
//			System.out.println(gVO.getGd_name());
//			System.out.println(gVO.getGd_phone());
//			
//			TeacherVO tVO = new TeacherVO();
//			tVO.setT_num("T007");
//			
//			List<TScheduleVO> tslist = new ArrayList<TScheduleVO>();
//			List<OnDutyVO> onlist = new ArrayList<OnDutyVO>();
//			
//			OnDutyVO onVO = new OnDutyVO();
//			TScheduleJDBCDAO tsdao = new TScheduleJDBCDAO();
//			tslist = tsdao.findByT_num(tVO.getT_num());
//			
//			OnDutyJDBCDAO ondao = new OnDutyJDBCDAO();
//			
//			for(TScheduleVO tsVO1 : tslist) {
//				onVO = ondao.findByPrimaryKey(tsVO1.getOn_duty_num());
//				onlist.add(onVO);
//			}
//			
//			for(int i=0 ; i < tslist.size() ; i++) {
//				System.out.print("日期 : " + tslist.get(i).getTs_date());
//				System.out.print(" | 值班 : " + onlist.get(i).getOn_duty_cs());
//				System.out.print(" | 時間 : " + onlist.get(i).getOn_duty_time());
//				System.out.println();
//			}
			
			/******************************/
			
			
			
			String t_num = req.getParameter("t_num");
			String cou_status = req.getParameter("cou_status");
			String cou_name = req.getParameter("cou_name");
			System.out.println(cou_status);

//			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				CourseService couSvc = new CourseService();
				List<CourseVO> list  = couSvc.getAll(map);
				
				CourseListService coulSvc = new CourseListService(); 
				
				List<Integer> signCount = new ArrayList<Integer>();
				List<Integer> payCount = new ArrayList<Integer>();
				List<Integer> course_num = new ArrayList<Integer>();
				
				for(CourseVO courseVO : list) {
					course_num.add(courseVO.getCou_num());
					signCount.add(coulSvc.getSignUpCount(courseVO.getCou_num()));   //計算該門課已報名人數
					payCount.add(coulSvc.getPayStatusCount(courseVO.getCou_num())); //計算該門課已繳費人數
				}
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("cou_name", cou_name); 
				req.setAttribute("t_num", t_num); 
				req.setAttribute("cou_status", cou_status);
				req.setAttribute("course_num", course_num);
				req.setAttribute("payCount", payCount); // 計算出的已繳費人數,存入request
				req.setAttribute("signCount", signCount); // 計算出的已報名人數,存入request
				req.setAttribute("listCourse_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/course/listCourse_ByCompositeQuery.jsp"); // 成功轉交listCourse_ByCompositeQuery.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/course/selectCourse.jsp");
//				failureView.forward(req, res);
//			}
		}
	}
	
	public static byte[] getImg(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] data = new byte[8192];
		int i;
		while ((i = is.read(data, 0 ,data.length)) != -1) {
			baos.write(data, 0, i);
		}
		baos.close();
		is.close();
		return baos.toByteArray();
	}
	
	
	Timer t;
	Calendar now = new GregorianCalendar();
	
	Calendar cal = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE));//5代表 Calendar_DATE
	
	public void destroy() {
		t.cancel();
	}

//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		res.setContentType("text/plain;charset=UTF-8");
//		PrintWriter out = res.getWriter();
//	}

	public void init() throws ServletException {
//		System.out.println(now.getTime());
//		System.out.println(cal.getTime());
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				CourseService couSvc = new CourseService();
				for(CourseVO courseVO : couSvc.getAll()) {
					if(new java.sql.Date(System.currentTimeMillis()).after(courseVO.getCou_edate())) {
						courseVO.setCou_status(3);
						couSvc.updateCourseStatus(courseVO.getCou_status(), courseVO.getCou_num());
					}
					if((new java.sql.Date(System.currentTimeMillis()).after(courseVO.getCou_sdate())) && 
						(new java.sql.Date(System.currentTimeMillis()).before(courseVO.getCou_edate()))) {
						courseVO.setCou_status(2);
						couSvc.updateCourseStatus(courseVO.getCou_status(), courseVO.getCou_num());
					}
				}
			}
		}, cal.getTime(), 24*60*60*1000);
	}
	
}