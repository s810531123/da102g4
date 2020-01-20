package com.Timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.afterschoolclass.model.ASC_Service;
import com.afterschoolclass.model.ASC_VO;
import com.paymentform.model.PMF_Service;
import com.paymentform.model.PMF_VO;

public class ASCSchedule implements ServletContextListener {
	Timer timer;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		
		
		TimerTask task = new TimerTask() {

			public void run() {
				Date date = new Date();  
				Date date2 = new Date();
				Date date3 = new Date();
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
				SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");
				//SimpleDateFormat sdf = new SimpleDateFormat("HH");
				//System.out.println(sdf2.format(date2).toString()+" 00:00");
				//System.out.println(sdf1.format(date).toString());
				java.util.Date date4 = new java.util.Date();				;	
				//System.out.println(sdf4.format(date)+"-01");
				java.sql.Date sqldate = java.sql.Date.valueOf(sdf4.format(date)+"-01");
				if ((sdf2.format(date).toString()+" 00:00").equals(sdf1.format(date).toString())) {
					ASC_Service dao = new ASC_Service();
					PMF_Service pmf_dao = new PMF_Service();					
					List<ASC_VO> vo1 = dao.insert();
					
					for (ASC_VO vo : vo1) {  
						try {
							date2 = sdf3.parse(vo.getLeveltime());
						} catch (ParseException e) {							
							e.printStackTrace();}	
//						System.out.println("stnum="+vo.getSt_num());
//						System.out.println("sql.date2="+new java.sql.Date(date2.getTime()));
						
						if(pmf_dao.havepmf(vo.getSt_num(), sqldate)) {
//							System.out.println("裡面有這個學號跟這個月份");
//							System.out.println(vo.getSt_num()+"這是學號");
//							System.out.println(new java.sql.Date(date2.getTime())+"這是時間");							
							pmf_dao.addmoney((pmf_dao.getOnePMF(vo.getSt_num(),sqldate)), vo.getSt_sum());
//							System.out.println("找出有這個學號跟月份的繳費單拿來跟金額來新增繳費單金額");
//							System.out.println(pmf_dao.getOnePMF(vo.getSt_num(),new java.sql.Date(date2.getTime()))+"這是帳單編號");
//							System.out.println(vo.getSt_sum()+"這是金額");							
						}
						else {
							PMF_VO pmf_vo = new PMF_VO();
							pmf_vo.setSt_num(vo.getSt_num());
							pmf_vo.setPf_status(0);
							pmf_vo.setPml_trail(vo.getSt_sum());
							pmf_vo.setMonth(sqldate);
							pmf_dao.addPMF(pmf_vo);
						}
						dao.addASC(vo);
					}
				}
			}
		};
		TimerListen.service.scheduleAtFixedRate(
				task,
				0,
				60000,
				TimeUnit.MILLISECONDS);	
	}
}