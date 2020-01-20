package com.schedule.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.*;
import com.onduty.model.*;
import com.schedule.model.*;
import com.teacher.model.*;


@ServerEndpoint("/TSshedule")
public class TScheduleServlet extends HttpServlet {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());   
	
	
        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    		doPost(req, res);
    		
    	}

    	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    		
    		res.setCharacterEncoding("UTF-8");
    		res.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = res.getWriter();
    		
    		
    		String action = req.getParameter("action");
    		/*************************查詢全部***********************************/
    		
    		TScheduleService tscheduleSvc = new TScheduleService();
    		
    		if("getAll".equals(action)) {
    			
    			String year = req.getParameter("year");
    			String month = req.getParameter("month");
    			/*****全部班表資料****/
    			List<TScheduleVO> listScheduleVO = tscheduleSvc.getByMonth(new Integer(year), new Integer(month)+1);

    			String jsonStr = Schedule_Format.getAllScheduleJsonString(listScheduleVO);
    			out.write(jsonStr);
    			out.flush();
    		}	
    		
    			
    		
    		/********編輯日期改變****************************/
    		if("dateChangeTeacher".equals(action)) {
    			
    			String changeDate = req.getParameter("changeDate");
    			
//    			HttpSession session = req.getSession();
//    			List<TScheduleVO> listScheduleVO = tscheduleSvc.getByDate(changeDate);
//    			int index = 0;
//    			for(TScheduleVO vo : listScheduleVO) {
//    				String t_num = vo.getT_num();
//    				session.setAttribute("tNum_" + index++, t_num);
//    				
//    			}
    			
    			
    			
    			/*****傳送所有老師資料***/
    			String jsonStr = Schedule_Format.getAllTeacher();
    			out.write(jsonStr);
    			out.flush();
    		}
    		if("dateChangeOnDuty".equals(action)) {
    			/***傳送所有班表資料***/
    			String jsonStr = Schedule_Format.getAllOnDuty();
    			out.write(jsonStr);
    			out.flush();
    		}
    			
    		
    		/**老師值改變**/
    		if("teacherChange".equals(action)) {
    			String teacherChange_1 = req.getParameter("teacherChange_1");
    			String teacherChange_2 = req.getParameter("teacherChange_2");
    			String teacherChange_3 = req.getParameter("teacherChange_3");
    			String teacherChange_4 = req.getParameter("teacherChange_4");
    			String jsonStr = Schedule_Format.getLeftoverTeacher(teacherChange_1, teacherChange_2, teacherChange_3, teacherChange_4);
    			out.write(jsonStr);
    			out.flush();
    		}
    		
    		
    		/**班別值改變**/
    		if("classChange".equals(action)) {
    			String classChange_1 = req.getParameter("classChange_1");
    			String classChange_2 = req.getParameter("classChange_2");
    			String classChange_3 = req.getParameter("classChange_3");
    			String classChange_4 = req.getParameter("classChange_4");
    			String jsonStr = Schedule_Format.getLeftoverClass(classChange_1, classChange_2, classChange_3, classChange_4);
    			out.write(jsonStr);
    			out.flush();
    		}
    		
    		
    		
    		
    		
    		if("addDateChange".equals(action)) {
    			String changeDate = req.getParameter("changeDate");
    			List<TScheduleVO> listScheduleVO = tscheduleSvc.getByDate(changeDate);
    			
    			String jsonStr = Schedule_Format.getAll_teacher_class_onDuty(listScheduleVO);
    			out.write(jsonStr);
    			out.flush();
    		}
    		
    		
    		if("deleteDateChange".equals(action)) {
    			String deleteDateChange = req.getParameter("deleteDateChange");
    			List<TScheduleVO> listScheduleVO = tscheduleSvc.getByDate(deleteDateChange);
    			String jsonStr = Schedule_Format.getAllScheduleJsonString(listScheduleVO);
    			out.write(jsonStr);
    			out.flush();
    		}
    		
    			
    			
    		/* SOP 點選日期 看到所有教職員班表 預設班表上的 沒有就請選擇  判斷同一天不能選擇一樣老師 班別 ， 傳到後端判斷有沒有這個班表資料 有的話就修改 沒有就新增*/
    			
    		
    		
    		
    		/***********************老師查看自己當月班表*********************/
    		if("getAllOneTeacher".equals(action)) {
    			
    			HttpSession session = req.getSession();
    			TeacherVO t_num_Login = (TeacherVO)session.getAttribute("tp");
    			
    			String year = req.getParameter("year");
    			String month = req.getParameter("month");
    			
    			List<TScheduleVO> listScheduleVO = tscheduleSvc.getByMonthFromOneTeacher(new Integer(year), new Integer(month) + 1, t_num_Login.getT_num());
    			String jsonStr = Schedule_Format.getAllScheduleJsonString(listScheduleVO);
    			out.write(jsonStr);
    			out.flush();
    			
    		}
    		
    		
    	}
    	
    	
    	
    	//新增修改
    	public void inert_update(JsonObject jOb) {
    		TScheduleService tscheduleSvc = new TScheduleService();
    		
//    		JsonObject  jOb = gson.fromJson(message, JsonObject.class);
    		
    		String teacherChange_1 = jOb.get("teacherChange_1").getAsString();
			String teacherChange_2 = jOb.get("teacherChange_2").getAsString();
			String teacherChange_3 = jOb.get("teacherChange_3").getAsString();
			String teacherChange_4 = jOb.get("teacherChange_4").getAsString();
			String onDutyChange_1 = jOb.get("onDutyChange_1").getAsString();
			String onDutyChange_2 = jOb.get("onDutyChange_2").getAsString();
			String onDutyChange_3 = jOb.get("onDutyChange_3").getAsString();
			String onDutyChange_4 = jOb.get("onDutyChange_4").getAsString();
			String selectDate = jOb.get("selectDate").getAsString();
    		
    		
			List<TScheduleVO> listScheduleVO = tscheduleSvc.getByDate(selectDate);
			Iterator<TScheduleVO> it = listScheduleVO.iterator();
			
			boolean b1 = true;
			while(it.hasNext()) {
				TScheduleVO vo = it.next();
				Integer ts_num = vo.getTs_num();
				String t_num = vo.getT_num();
				Integer on_duty_num = vo.getOn_duty_num();
				java.sql.Date ts_date = vo.getTs_date();
				if((!"-1".equals(onDutyChange_1)) && (!"-1".equals(teacherChange_1)) && (onDutyChange_1.equals(String.valueOf(on_duty_num)))) {
					TScheduleVO upVo = tscheduleSvc.updateTSchedule(ts_num, teacherChange_1, new Integer(onDutyChange_1), ts_date);
					for (Session session : connectedSessions) {
						if (session.isOpen()) {
							session.getAsyncRemote().sendText(Schedule_Format.getUpdateScheduleVOJsonStr(upVo));
						}
					}
					b1 = false;
				}
			}
			while(b1 && (!"-1".equals(onDutyChange_1)) && (!"-1".equals(teacherChange_1))) {
				TScheduleVO addVO = tscheduleSvc.addTSchedule(teacherChange_1, new Integer(onDutyChange_1), java.sql.Date.valueOf(selectDate));
				for (Session session : connectedSessions) {
					if (session.isOpen()) {
						session.getAsyncRemote().sendText(Schedule_Format.getAddScheduleVOJsonStr(addVO));
					}
				}
				break;
			}
			/******/
			List<TScheduleVO> listScheduleVO2 = tscheduleSvc.getByDate(selectDate);
			Iterator<TScheduleVO> it2 = listScheduleVO2.iterator();
			boolean b2 = true;
			while(it2.hasNext()) {
				TScheduleVO vo = it2.next();
				Integer ts_num = vo.getTs_num();
				String t_num = vo.getT_num();
				Integer on_duty_num = vo.getOn_duty_num();
				java.sql.Date ts_date = vo.getTs_date();
				if((!"-1".equals(onDutyChange_2)) && (!"-1".equals(teacherChange_2)) && (onDutyChange_2.equals(String.valueOf(on_duty_num)))) {
					TScheduleVO upVo = tscheduleSvc.updateTSchedule(ts_num, teacherChange_2, new Integer(onDutyChange_2), ts_date);
					for (Session session : connectedSessions) {
						if (session.isOpen()) {
							session.getAsyncRemote().sendText(Schedule_Format.getUpdateScheduleVOJsonStr(upVo));
						}
					}
					b2 = false;
				}
			}
			while(b2 && (!"-1".equals(onDutyChange_2)) && (!"-1".equals(teacherChange_2))) {
				TScheduleVO addVO = tscheduleSvc.addTSchedule(teacherChange_2, new Integer(onDutyChange_2), java.sql.Date.valueOf(selectDate));
				for (Session session : connectedSessions) {
					if (session.isOpen()) {
						session.getAsyncRemote().sendText(Schedule_Format.getAddScheduleVOJsonStr(addVO));
					}
				}
				break;
			}
			/******/
			List<TScheduleVO> listScheduleVO3 = tscheduleSvc.getByDate(selectDate);
			Iterator<TScheduleVO> it3 = listScheduleVO3.iterator();
			boolean b3 = true;
			while(it3.hasNext()) {
				TScheduleVO vo = it3.next();
				Integer ts_num = vo.getTs_num();
				String t_num = vo.getT_num();
				Integer on_duty_num = vo.getOn_duty_num();
				java.sql.Date ts_date = vo.getTs_date();
				if((!"-1".equals(onDutyChange_3)) && (!"-1".equals(teacherChange_3)) && (onDutyChange_3.equals(String.valueOf(on_duty_num)))) {
					TScheduleVO upVo = tscheduleSvc.updateTSchedule(ts_num, teacherChange_3, new Integer(onDutyChange_3), ts_date);
					for (Session session : connectedSessions) {
						if (session.isOpen()) {
							session.getAsyncRemote().sendText(Schedule_Format.getUpdateScheduleVOJsonStr(upVo));
						}
					}
					b3 = false;
				}
			}
			while(b3 && (!"-1".equals(onDutyChange_3)) && (!"-1".equals(teacherChange_3))) {
				TScheduleVO addVO = tscheduleSvc.addTSchedule(teacherChange_3, new Integer(onDutyChange_3), java.sql.Date.valueOf(selectDate));
				for (Session session : connectedSessions) {
					if (session.isOpen()) {
						session.getAsyncRemote().sendText(Schedule_Format.getAddScheduleVOJsonStr(addVO));
					}
				}
				break;
			}
			/******/
			List<TScheduleVO> listScheduleVO4 = tscheduleSvc.getByDate(selectDate);
			Iterator<TScheduleVO> it4 = listScheduleVO4.iterator();
			boolean b4 = true;
			while(it4.hasNext()) {
				TScheduleVO vo = it4.next();
				Integer ts_num = vo.getTs_num();
				String t_num = vo.getT_num();
				Integer on_duty_num = vo.getOn_duty_num();
				java.sql.Date ts_date = vo.getTs_date();
				if((!"-1".equals(onDutyChange_4)) && (!"-1".equals(teacherChange_4)) && (onDutyChange_4.equals(String.valueOf(on_duty_num)))) {
					TScheduleVO upVo = tscheduleSvc.updateTSchedule(ts_num, teacherChange_4, new Integer(onDutyChange_4), ts_date);
					for (Session session : connectedSessions) {
						if (session.isOpen()) {
							session.getAsyncRemote().sendText(Schedule_Format.getUpdateScheduleVOJsonStr(upVo));
						}
					}
					b4 = false;
				}
			}
			while(b4 && (!"-1".equals(onDutyChange_4)) && (!"-1".equals(teacherChange_4))) {
				TScheduleVO addVO = tscheduleSvc.addTSchedule(teacherChange_4, new Integer(onDutyChange_4), java.sql.Date.valueOf(selectDate));
				for (Session session : connectedSessions) {
					if (session.isOpen()) {
						session.getAsyncRemote().sendText(Schedule_Format.getAddScheduleVOJsonStr(addVO));
					}
				}
				break;
			}
    		
    	}
    	
    	//刪除
    	public void delete(JsonObject jOb) {
    		String num = jOb.get("ts_num").getAsString();
    		TScheduleService tscheduleSvc = new TScheduleService();
    		
    		Integer ts_num = new Integer(num);
			if(!(ts_num.equals(-1))) {
				String str = Schedule_Format.getDeleteScheduleVOJsonStr(tscheduleSvc.getOneTSchedule(ts_num)) ;
				tscheduleSvc.deleteTSchedule(ts_num);
				for (Session session : connectedSessions) {
					if (session.isOpen()) {
						session.getAsyncRemote().sendText(str);
					}
				}
			}
    	}
    	
    	
    	
    	@OnOpen
    	public void onOpen(Session session) throws IOException{
    		connectedSessions.add(session);
    	}
    	
    	@OnMessage
    	public void onMessage(Session userSession, String message) throws IOException{
    		Gson gson = new Gson();
    		JsonObject  jOb = gson.fromJson(message, JsonObject.class);
    		String action = jOb.get("action").getAsString();
    		if("addSubmit".equals(action)) 
    			inert_update(jOb);
    		
    		if("deleteSubmit".equals(action))
    			delete(jOb);
    		
    	}
    	@OnClose
    	public void onClose(Session userSession, CloseReason reason) throws IOException{
    		connectedSessions.remove(userSession);
    	}
    	@OnError
    	public void onError(Session userSession, Throwable e) throws IOException{
    		System.out.println("Error: " + e.toString());
    	}

}
