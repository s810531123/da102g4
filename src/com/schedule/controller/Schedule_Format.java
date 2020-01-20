package com.schedule.controller;

import java.util.*;

import com.google.gson.*;
import com.onduty.model.*;
import com.schedule.model.TScheduleService;
import com.schedule.model.TScheduleVO;
import com.teacher.model.*;

public class Schedule_Format {
	
	public static OnDutyService onDutySvr = new OnDutyService();
	public static TeacherService teacherSvc = new TeacherService();
	public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
	
	public static String getAllScheduleJsonString(List list) {
		
		String jsonStrSchedule =  gson.toJson(list);
		JsonArray jsonArraySchedule = gson.fromJson(jsonStrSchedule, JsonArray.class);
		
		/*****全部值班資料****/
		List<OnDutyVO> listOnDutyVO = onDutySvr.getAll();
		String jsonStrOnDuty =  gson.toJson(listOnDutyVO);
		JsonArray jsonArrayOnDuty = gson.fromJson(jsonStrOnDuty, JsonArray.class);
		/*****全部老師資料****/
		List<TeacherVO> listTeacherVO = teacherSvc.getAll();
		String jsonStrTeacher =  gson.toJson(listTeacherVO);
		JsonArray jsonArrayTeacher = gson.fromJson(jsonStrTeacher, JsonArray.class);
		
		
		/********比對教師編號&班表編號*******/
		StringBuilder sb = new StringBuilder("[");

		for(JsonElement tsVO : jsonArraySchedule) {
			JsonObject obj = tsVO.getAsJsonObject();
			String tsVO_t_num = obj.get("t_num").getAsString();
			int tsVO_on_duty_num = obj.get("on_duty_num").getAsInt();
			String tsVO_ts_date = obj.get("ts_date").getAsString();
			int ts_num = obj.get("ts_num").getAsInt();
			for(JsonElement oDVO : jsonArrayOnDuty) {
				JsonObject obj2 = oDVO.getAsJsonObject();
				int oDVO_on_duty_num = obj2.get("on_duty_num").getAsInt();
				String oDVO_on_duty_cs = obj2.get("on_duty_cs").getAsString();
				if(tsVO_on_duty_num == oDVO_on_duty_num) {
					sb.append("{\"on_duty_cs\":\"" + oDVO_on_duty_cs +  "\",");
				}
			}
				
			for(JsonElement tVO :jsonArrayTeacher) {
				JsonObject obj3 = tVO.getAsJsonObject();
				String tVO_t_num = obj3.get("t_num").getAsString();
				String tVO_t_name = obj3.get("t_name").getAsString();
				if(tsVO_t_num.equals(tVO_t_num)) {
					sb.append("\"t_name\":\"" + tVO_t_name + "\",");
				}
			}
			
			sb.append("\"ts_date\":\"" + tsVO_ts_date + "\"," + "\"t_num\":\"" 
					+ tsVO_t_num +"\"," + "\"on_duty_num\":" + tsVO_on_duty_num + ",\"ts_num\":" +  ts_num + "},");
		}

		sb.replace(sb.length()-1, sb.length(), "]");
		
		return sb.toString();
	}
	
	//一筆新增班表資料轉jsonStr
	public static String getAddScheduleVOJsonStr(TScheduleVO vo) {
		String tsVO_t_num = vo.getT_num();
		int tsVO_on_duty_num = vo.getOn_duty_num();
		String  tsVO_ts_date = vo.getTs_date().toString();
		
		StringBuilder sb = new StringBuilder();
		/*****全部值班資料****/
		List<OnDutyVO> listOnDutyVO = onDutySvr.getAll();
		String jsonStrOnDuty =  gson.toJson(listOnDutyVO);
		JsonArray jsonArrayOnDuty = gson.fromJson(jsonStrOnDuty, JsonArray.class);
		/*****全部老師資料****/
		List<TeacherVO> listTeacherVO = teacherSvc.getAll();
		String jsonStrTeacher =  gson.toJson(listTeacherVO);
		JsonArray jsonArrayTeacher = gson.fromJson(jsonStrTeacher, JsonArray.class);
		for(JsonElement oDVO : jsonArrayOnDuty) {
			JsonObject obj2 = oDVO.getAsJsonObject();
			int oDVO_on_duty_num = obj2.get("on_duty_num").getAsInt();
			String oDVO_on_duty_cs = obj2.get("on_duty_cs").getAsString();
			if(tsVO_on_duty_num == oDVO_on_duty_num) {
				sb.append("{\"on_duty_cs\":\"" + oDVO_on_duty_cs +  "\",");
			}
		}
			
		for(JsonElement tVO :jsonArrayTeacher) {
			JsonObject obj3 = tVO.getAsJsonObject();
			String tVO_t_num = obj3.get("t_num").getAsString();
			String tVO_t_name = obj3.get("t_name").getAsString();
			if(tsVO_t_num.equals(tVO_t_num)) {
				sb.append("\"t_name\":\"" + tVO_t_name + "\",");
			}
		}
		sb.append("\"ts_date\":\"" + tsVO_ts_date + "\"," + "\"t_num\":\"" 
				+ tsVO_t_num +"\"," + "\"on_duty_num\":" + tsVO_on_duty_num  + ",\"action\":\"add\"}");
		
		return sb.toString();
	}
	
	
	//一筆修改班表資料轉jsonStr
	public static String getUpdateScheduleVOJsonStr(TScheduleVO vo) {
		
		String str = Schedule_Format.getAddScheduleVOJsonStr(vo);
		str = str.substring(0, str.length()-6) + "\"update\"}";
		
		return str;
	}
	
	//一筆刪除班表資料轉jsonStr
	public static String getDeleteScheduleVOJsonStr(TScheduleVO vo) {
		
		String str = Schedule_Format.getAddScheduleVOJsonStr(vo);
		str = str.substring(0, str.length()-6) + "\"delete\"}";
		
		return str;
	}
	
	
	
	/**回傳所有老師**/
	public static String getAllTeacher() {
		List<TeacherVO> listTeacherVO = teacherSvc.getAll();
		listTeacherVO.remove(0);
		String jsonStrTeacher =  gson.toJson(listTeacherVO);
		return jsonStrTeacher;
	}
	
	/**回傳所有班別**/
	public static String getAllOnDuty() {
		List<OnDutyVO> listOnDutyVO = onDutySvr.getAll();
		String jsonStrOnDuty =  gson.toJson(listOnDutyVO);
		return jsonStrOnDuty;
	}
	
	/**回傳剩餘老師**/
	public static String getLeftoverTeacher(String teacher_1, String teacher_2, String teacher_3, String teacher_4) {
		List<TeacherVO> listTeacherVO = teacherSvc.getAll();
		listTeacherVO.remove(0); //移除園長
		
		
		/*****/
		Iterator<TeacherVO> it = listTeacherVO.iterator();
		while (it.hasNext()) {
			TeacherVO vo = it.next();
			String t_num = vo.getT_num();
			if(t_num.equals(teacher_1)) {
				it.remove();
			}
		}
		
		
		/*****/
		
		Iterator<TeacherVO> it2 = listTeacherVO.iterator();
		while (it2.hasNext()) {
			TeacherVO vo = it2.next();
			String t_num = vo.getT_num();
			if(t_num.equals(teacher_2)) {
				it2.remove();
			}
		}
		
		/*****/
		
		Iterator<TeacherVO> it3 = listTeacherVO.iterator();
		while (it3.hasNext()) {
			TeacherVO vo = it3.next();
			String t_num = vo.getT_num();
			if(t_num.equals(teacher_3)) {
				it3.remove();
			}
		}
		
		/*****/
		
		Iterator<TeacherVO> it4 = listTeacherVO.iterator();
		while (it4.hasNext()) {
			TeacherVO vo = it4.next();
			String t_num = vo.getT_num();
			if(t_num.equals(teacher_4)) {
				it4.remove();
			}
		}
		
		
				
		return gson.toJson(listTeacherVO);
	}
	
	
	/**回傳剩餘老師**/
	public static String getLeftoverClass(String class_1, String class_2, String class_3, String class_4) {
		List<OnDutyVO> listOnDutyVO = onDutySvr.getAll();
		
		/*****/
		Iterator<OnDutyVO> it = listOnDutyVO.iterator();
		while (it.hasNext()) {
			OnDutyVO vo = it.next();
			String on_duty_num = String.valueOf(vo.getOn_duty_num()) ;
			if(on_duty_num.equals(class_1)) {
				it.remove();
			}
		}
		
		/*****/
		Iterator<OnDutyVO> it2 = listOnDutyVO.iterator();
		while (it2.hasNext()) {
			OnDutyVO vo = it2.next();
			String on_duty_num = String.valueOf(vo.getOn_duty_num()) ;
			if(on_duty_num.equals(class_2)) {
				it2.remove();
			}
		}
		
		/*****/
		Iterator<OnDutyVO> it3 = listOnDutyVO.iterator();
		while (it3.hasNext()) {
			OnDutyVO vo = it3.next();
			String on_duty_num = String.valueOf(vo.getOn_duty_num()) ;
			if(on_duty_num.equals(class_3)) {
				it3.remove();
			}
		}
		
		/*****/
		Iterator<OnDutyVO> it4 = listOnDutyVO.iterator();
		while (it4.hasNext()) {
			OnDutyVO vo = it4.next();
			String on_duty_num = String.valueOf(vo.getOn_duty_num()) ;
			if(on_duty_num.equals(class_4)) {
				it4.remove();
			}
		}
		
		return gson.toJson(listOnDutyVO);
	}
	
	
	
	public static String getAll_teacher_class_onDuty(List list) {
		
		/*****全部值班資料****/
		List<OnDutyVO> listOnDutyVO = onDutySvr.getAll();
		String jsonStrOnDuty =  gson.toJson(listOnDutyVO);
		/*****全部老師資料****/
		List<TeacherVO> listTeacherVO = teacherSvc.getAll();
		String jsonStrTeacher =  gson.toJson(listTeacherVO);
		
		String everydaySchedule = Schedule_Format.getAllScheduleJsonString(list);
		
		StringBuilder sb = new StringBuilder("[");
		sb.append(jsonStrTeacher);
		sb.append(",");
		sb.append(jsonStrOnDuty);
		if(everydaySchedule.length() != 1) {
			sb.append(",");
			sb.append(everydaySchedule);
		}
		sb.append("]");
		
		
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		TScheduleService s = new TScheduleService();
		
		System.out.println(Schedule_Format.getUpdateScheduleVOJsonStr(s.getOneTSchedule(1001)));
	}
}
