package com.arrive_leave_time.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com._class.model.ClassService;
import com.alt.model.AltService;
import com.alt.model.AltVO;
import com.student.model.StudentService;
import com.student.model.StudentVO;


@WebServlet("/ArriveLeaveTimeServlet")
public class ArriveLeaveTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		
		/******************新增到離校時間*********************/
		
		
		if("insert".equals(action)) {
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			
			String fontyear = req.getParameter("year").trim().substring(0, 4).trim();
			String fontmonth = req.getParameter("month").trim().substring(0, 2).trim();
			String fontday = req.getParameter("day").trim();
			String st_num = req.getParameter("st_num").trim();
			
			if(fontday.length() == 1) {
				fontday = req.getParameter("day").trim().substring(0, 1).trim();
			} else if (fontday.length() == 2){
				fontday = req.getParameter("day").trim().substring(0, 2).trim();
			} else 
				fontday = fontday.substring(0,fontday.indexOf("到")).trim();
			
			String arr = req.getParameter("arr").trim();
			String lea = req.getParameter("lea").trim();
			
			String arrTime = fontyear + "-" + fontmonth + "-" + fontday + " " + arr;
			String leaTime = fontyear + "-" + fontmonth + "-" + fontday + " " + lea;
				System.out.println(arrTime);
				System.out.println(arrTime.length());
			if(arr.length() == 5) {
				arrTime = arrTime + ":00";
			}
			java.sql.Timestamp arrive = null;
			if(arr.length() > 0) {
				arrive = java.sql.Timestamp.valueOf(arrTime);
			}
			
			if(lea.length() == 5) {
				leaTime = leaTime + ":00";
			}
			java.sql.Timestamp leave = null;
			if(lea.length() > 0) {
				leave = java.sql.Timestamp.valueOf(leaTime);
			}

			AltService altSvc = new AltService();
	
			JSONObject obj = new JSONObject();
			
			if(arr.length() > 0 && lea.length() == 0) {
				altSvc.addAlt(st_num, arrive, leave, arrive);
				obj.put("arr", arrive.toString().substring(11, 19));
				obj.put("lea", "");
				obj.put("action", "arr");
			}
			if(arr.length() > 0 && lea.length() > 0) {
				altSvc.addAlt(st_num, arrive, leave, arrive);
				obj.put("lea", leave.toString().substring(11, 19));
				obj.put("arr", arrive.toString().substring(11, 19));
				obj.put("action", "both");
			}
			
			out.write(obj.toString());
			out.flush();
			out.close();
		}
		
		
		/******************修改到離校時間*********************/
		
		if("update".equals(action)) {
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			
			String fontyear = req.getParameter("year").trim().substring(0, 4).trim();
			String fontmonth = req.getParameter("month").trim().substring(0, 2).trim();
//			String frontday = req.getParameter("day").trim().substring(0, 2).trim();
			String fontday = req.getParameter("day").trim();
			String st_num = req.getParameter("st_num").trim();
			
			if(fontday.length() == 1) {
				fontday = req.getParameter("day").trim().substring(0, 1).trim();
			} else if (fontday.length() == 2){
				fontday = req.getParameter("day").trim().substring(0, 2).trim();
			} else 
				fontday = fontday.substring(0,fontday.indexOf("到")).trim();
			
			String arr = req.getParameter("arr").trim();
			String lea = req.getParameter("lea").trim();
			
			System.out.println(arr.equals(""));
			System.out.println(arr.length() == 0);
			String todayDate = fontyear + "-" + fontmonth + "-" + fontday;
			String arrTime = fontyear + "-" + fontmonth + "-" + fontday + " " + arr;
			String leaTime = fontyear + "-" + fontmonth + "-" + fontday + " " + lea;
			
			java.sql.Date arrive1 = null;
			if(arr.length() > 0 || lea.length() > 0) {
				arrive1 = java.sql.Date.valueOf(todayDate);
			}
			
			if(arr.length() == 5) {
				arrTime = arrTime + ":00";
			}
			java.sql.Timestamp arrive = null;
			if(arr.length() > 0) {
				arrive = java.sql.Timestamp.valueOf(arrTime);
			}
			
			if(lea.length() == 5) {
				leaTime = leaTime + ":00";
			}
			java.sql.Timestamp leave = null;
			if(lea.length() > 0) {
				leave = java.sql.Timestamp.valueOf(leaTime);
			}
				
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String todayd = sdf.format(arrive1);

			AltService altSvc = new AltService();
			AltVO a = altSvc.getOneBySt_numAndDate(st_num, todayd);
			System.out.println("S001 ArrTime : " + a.getAlt_Arr());
			System.out.println("S001 LeaTime : " + a.getAlt_Lea());
			
			String ab = a.getAlt_Arr().toString().substring(11, 19);
			System.out.println("ab" + ab);
			String ba = null;
			if(a.getAlt_Lea() != null) {
				ba = a.getAlt_Lea().toString().substring(11, 19);
			}
			
			JSONObject obj = new JSONObject();

			if(lea.length() > 0 && arr.length() == 0) {
				altSvc.updateArrOrLea(st_num, a.getAlt_Arr(), leave, todayd);
				obj.put("lea", leave.toString().substring(11, 19));
				obj.put("arr", ab);
			
				obj.put("day", fontday);
				obj.put("action", "lea");
			}
			if(arr.length() > 0 && lea.length() == 0) {
				altSvc.updateArrOrLea(st_num, arrive, a.getAlt_Arr(), todayd);
				obj.put("arr", arrive.toString().substring(11, 19));
				obj.put("lea", ba);
				obj.put("day", fontday);
				obj.put("action", "arr");
			}
			if(arr.length() > 0 && lea.length() > 0) {
				altSvc.updateArrOrLea(st_num, arrive, leave, todayd);
				obj.put("lea", leave.toString().substring(11, 19));
				obj.put("arr", arrive.toString().substring(11, 19));
				obj.put("day", fontday);
				obj.put("action", "both");
			}
			
//			System.out.println("arrive : " + arrive);
			
			
			
			
			
			out.write(obj.toString());
			out.flush();
			out.close();
		}
		
		
		
		/****************進入修改取值到輸入**********************/

		
		if("updateCreate".equals(action)) {
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
		
			String day2 = req.getParameter("day2").trim();
			
			String arr = day2.substring(day2.indexOf("到")+2,day2.indexOf("離"));
			String lea = day2.substring(day2.indexOf("離")+2,day2.length());
			System.out.println("arr : " + arr);
			System.out.println("lea : " + lea);
			JSONObject obj = new JSONObject();
			
			obj.put("arr", arr);
			obj.put("lea", lea);
			
			out.write(obj.toString());
			out.flush();
			out.close();
		}
		
		
		/****************進入修改取值到輸入**********************/
		
		/****************Ajax動態下拉選單***********************/
		if("selectStNameBar".equals(action)) {
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();

			
			JSONArray array = new JSONArray();
			String cs_num = req.getParameter("cs_num");
			
			StudentService stSvc = new StudentService();
			
			List<StudentVO> list = stSvc.getStudentByCs_Num(cs_num);
			
			for(StudentVO st : list) {
				JSONObject obj = new JSONObject();
				obj.put("st_num", st.getSt_num());
				obj.put("st_name", st.getSt_name());
				array.put(obj);
			}
			out.write(array.toString());
			out.flush();
			out.close();
			
		}
		
		/****************Ajax 查詢學生到離校*********************/
		if("selectStArriveLeaveTime".equals(action)) {
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
	
			JSONArray array = new JSONArray();
			
			String frontyear = req.getParameter("year").trim().substring(0, 4).trim();
			String frontmonth = req.getParameter("month").trim().substring(0, 2).trim();
			String st_num = req.getParameter("st_num");
			System.out.println(st_num);
			AltService altSvc = new AltService();
			List<AltVO> list = altSvc.getAltBySt_Num(st_num);
			System.out.println("************************* : " + frontmonth);
			System.out.println("************************* : " + frontyear);
			for(AltVO alt : list) {
				JSONObject obj = new JSONObject();
				String altArr = alt.getAlt_Arr().toString();
				String altLea = null;
				String year = altArr.substring(0, 4);
				String month = altArr.substring(5, 7);
				String day = altArr.substring(8, 10);
				String arrTime = altArr.substring(11, 19);
				String leaTime = null;
				
				try {
					altLea = alt.getAlt_Lea().toString();
					leaTime = altLea.substring(11, 19);
				} catch(NullPointerException e) {
					altLea = "";
					leaTime = "";
				}
				
				
				Integer d = new Integer(day);
				Integer m = new Integer(month);
				if(d<10) {
					day = d.toString();
				}
				if(m<10) {
					month = m.toString();
				}
				if((frontmonth.equals(month)) && (frontyear.equals(year))) {
					System.out.println("match");
					obj.put("year", year);
					obj.put("month", month);
					obj.put("day", day);
					obj.put("arrTime", arrTime);
					obj.put("leaTime", leaTime);
				}
				
				
				
				array.put(obj);
				
//				System.out.println("Year : "+altArr.substring(0, 4));
//				System.out.println("Month : "+altArr.substring(5, 7));
//				System.out.println("Day : "+altArr.substring(8, 10));
//				System.out.println("ArrTime : "+altArr.substring(11, 19));
//				System.out.println("LeaTime : " + leaTime);
			}
			
			out.write(array.toString());
			out.flush();
			out.close();
			
		}
		
		
		/****************Ajax 查詢學生到離校 UP*********************/
		if("selectStArriveLeaveTimeUP".equals(action)) {
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
	
			JSONArray array = new JSONArray();
			String frontyear = req.getParameter("year").trim().substring(0, 4).trim();
			String frontmonth = req.getParameter("month").trim().substring(0, 2).trim();
			Integer fmonth = new Integer(frontmonth);
			Integer fyear = new Integer(frontyear);
			fmonth++;
			if(fmonth == 13) {
				fmonth = 1;
				fyear++;
			}
			String st_num = req.getParameter("st_num");
			AltService altSvc = new AltService();
			List<AltVO> list = altSvc.getAltBySt_Num(st_num);
			System.out.println("///////////////////////////////" + fyear);
			System.out.println("************************* : " + fmonth);
			for(AltVO alt : list) {
				JSONObject obj = new JSONObject();
				String altArr = alt.getAlt_Arr().toString();
				String altLea = null;
				String year = altArr.substring(0, 4);
				String month = altArr.substring(5, 7);
				String day = altArr.substring(8, 10);
				String arrTime = altArr.substring(11, 19);
				String leaTime = null;
				
				try {
					altLea = alt.getAlt_Lea().toString();
					leaTime = altLea.substring(11, 19);
				} catch(NullPointerException e) {
					altLea = "";
					leaTime = "";
				}
				
				
				Integer d = new Integer(day);
				Integer m = new Integer(month);
				Integer y = new Integer(year);
				System.out.println("fyear.equals(year) : " + fyear.equals(y));
				if(d<10) {
					day = d.toString();
				}
				
				if((m.equals(fmonth)) && (fyear.equals(y))) {
					System.out.println("match");
					obj.put("year", year);
					obj.put("month", month);
					obj.put("day", day);
					obj.put("arrTime", arrTime);
					obj.put("leaTime", leaTime);
				}	
				array.put(obj);
			}	
			out.write(array.toString());
			out.flush();
			out.close();		
		}
		
		
		/****************Ajax 查詢學生到離校 DOWN*********************/
		if("selectStArriveLeaveTimeDOWN".equals(action)) {
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
	
			JSONArray array = new JSONArray();
			String frontyear = req.getParameter("year").trim().substring(0, 4).trim();
			String frontmonth = req.getParameter("month").trim().substring(0, 2).trim();
			Integer fmonth = new Integer(frontmonth);
			Integer fyear = new Integer(frontyear);
			fmonth--;
			if(fmonth == 0) {
				fmonth = 12;
				fyear--;
			}
			String st_num = req.getParameter("st_num");
			AltService altSvc = new AltService();
			List<AltVO> list = altSvc.getAltBySt_Num(st_num);
			System.out.println("************************* : " + fmonth);
			System.out.println("************************* : " + fyear);
			for(AltVO alt : list) {
				JSONObject obj = new JSONObject();
				String altArr = alt.getAlt_Arr().toString();
				String altLea = null;
				String year = altArr.substring(0, 4);
				String month = altArr.substring(5, 7);
				String day = altArr.substring(8, 10);
				String arrTime = altArr.substring(11, 19);
				String leaTime = null;
				
				try {
					altLea = alt.getAlt_Lea().toString();
					leaTime = altLea.substring(11, 19);
				} catch(NullPointerException e) {
					altLea = "";
					leaTime = "";
				}
				
				
				Integer d = new Integer(day);
				Integer m = new Integer(month);
				Integer y = new Integer(year);
				if(d<10) {
					day = d.toString();
				}
				
				System.out.println("fMonth : " + fmonth);
				System.out.println("month : " + m);
				System.out.println("======================");
				if((fmonth.equals(m)) && (fyear.equals(y))) {
					System.out.println("match");
					obj.put("year", year);
					obj.put("month", month);
					obj.put("day", day);
					obj.put("arrTime", arrTime);
					obj.put("leaTime", leaTime);
				}	
				array.put(obj);
			}	
			out.write(array.toString());
			out.flush();
			out.close();		
		}
		
		if("selectAllStOnDay".equals(action)) {
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
	
			JSONArray array = new JSONArray();
			String frontyear = req.getParameter("year").trim().substring(0, 4).trim();
			String frontmonth = req.getParameter("month").trim().substring(0, 2).trim();
//			String frontday = req.getParameter("day").trim().substring(0, 2).trim();
			String frontday = req.getParameter("day").trim();
			String cs_num = req.getParameter("cs_num").trim();
			
			if(frontday.length() == 1) {
				frontday = req.getParameter("day").trim().substring(0, 1).trim();
			} else if (frontday.length() == 2){
				frontday = req.getParameter("day").trim().substring(0, 2).trim();
			}
			
			Integer fmonth = new Integer(frontmonth);
			Integer fyear = new Integer(frontyear);
			Integer fday = null;
			try {
				fday = new Integer(frontday);
			} catch(NumberFormatException e) {
				fday = new Integer(req.getParameter("day").trim().substring(0, 1).trim());
			}
			
			
			AltService altSvc = new AltService();
			List<AltVO> list = altSvc.getAll();
//			System.out.println("************************* : " + fmonth);
//			System.out.println("************************* : " + fyear);
			StudentService stSvc = new StudentService();
			for(AltVO alt : list) {
				JSONObject obj = new JSONObject();
				String altArr = alt.getAlt_Arr().toString();
				String altLea = null;
				String leaTime = null;
				try {
					altLea = alt.getAlt_Lea().toString();
					leaTime = altLea.substring(11, 19);
				} catch(NullPointerException e) {
					altLea = "";
					leaTime = "";
				}
				
				System.out.println("altLea : "+altLea);
				String year = altArr.substring(0, 4);
				String month = altArr.substring(5, 7);
				String day = altArr.substring(8, 10);
				String arrTime = altArr.substring(11, 19);
				
				
				StudentVO stVO = stSvc.getStudentBySt_Num(alt.getSt_Num());
				
				Integer d = new Integer(day);
				Integer m = new Integer(month);
				Integer y = new Integer(year);
				
				
//				System.out.println("fMonth : " + fmonth);
//				System.out.println("month : " + m);
//				System.out.println("day : " + d);
//				System.out.println("fday : " + fday);
//				System.out.println((fday.equals(d)));
				System.out.println("======================");
				if((fmonth.equals(m)) && (fyear.equals(y)) && (fday.equals(d)) && (cs_num.equals(stVO.getCs_num()))) {
					System.out.println(stVO.getSt_name());
					System.out.println("match");
					obj.put("year", year);
					obj.put("month", month);
					obj.put("day", day);
					obj.put("arrTime", arrTime);
					obj.put("leaTime", leaTime);
					obj.put("st_name", stVO.getSt_name());
					array.put(obj);
				}	
				
			}	
			out.write(array.toString());
			out.flush();
			out.close();
			
		}
		
		
	}

}
