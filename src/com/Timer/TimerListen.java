package com.Timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;


import com.paymentproject.model.PMP_Service;
import com.payschedule.model.ScheduleDAO;
import com.payschedule.model.ScheduleVO;

public class TimerListen implements ServletContextAttributeListener {
	
	public static ScheduledExecutorService service = Executors.newScheduledThreadPool(15);
	
	



	@Override
	public void attributeReplaced(ServletContextAttributeEvent scab) {				
		
		Date date = null;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String datestr = (String) scab.getServletContext().getAttribute("date");

		String pml_num = (String) scab.getServletContext().getAttribute("pml_num");

		String choose = (String) scab.getServletContext().getAttribute("choose");

		if (datestr != null && pml_num != null && choose != null) {

			try {
				System.out.println("執行attributeAdded");
				
				System.out.println("設定排程為"+datestr +" 07:00:00");

				date = format.parse(datestr + " 07:00:00");	
				
				
				ScheduleVO vo = new ScheduleVO();
				
				PMP_Service svc = new PMP_Service();
				
				String chovo = svc.getOneDept(Integer.parseInt(pml_num)).getPml_item();
				
				vo.setPml_item(choose);
				
				vo.setPml_time(datestr + " 07:00:00");			
				
				vo.setPml_num(chovo);
				
				ScheduleDAO dao1 = new ScheduleDAO();
				
				dao1.insert(vo);
				
				TimeTask task = new TimeTask();

				task.setChoose(choose);

				task.setPml_num(pml_num);

				task.setDate(date);
				
				task.setDatestr(datestr + " 07:00:00");
				
				
				
				ScheduledFuture<?> t =service.scheduleAtFixedRate(
						task,
						0,
						60000,
						TimeUnit.MILLISECONDS);
				
				task.setT(t);
			

				//timer.schedule(task, date);

//				try {
//
//					Thread.sleep(20000);
//
//				} catch (InterruptedException e) {
//
//				}
				

				scab.getServletContext().setAttribute("date", null);

				scab.getServletContext().setAttribute("pml_num", null);

				scab.getServletContext().setAttribute("choose", null);

			} catch (ParseException e) {

				e.printStackTrace();

			}/*finally{
				timer.cancel();
				System.out.println("timer已關閉");
			}*/
		}

	}

	

	public void attributeAdded(ServletContextAttributeEvent scab) {
	
		Date date = null;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String datestr = (String) scab.getServletContext().getAttribute("date");

		String pml_num = (String) scab.getServletContext().getAttribute("pml_num");

		String choose = (String) scab.getServletContext().getAttribute("choose");

		if (datestr != null && pml_num != null && choose != null) {

			try {
				System.out.println("執行attributeAdded");
				
				System.out.println("設定排程為"+datestr +" 07:00:00");

				date = format.parse(datestr + " 07:00:00");	
				
				
				ScheduleVO vo = new ScheduleVO();
				
				PMP_Service svc = new PMP_Service();
				
				String chovo = svc.getOneDept(Integer.parseInt(pml_num)).getPml_item();
				
				vo.setPml_item(choose);
				
				vo.setPml_time(datestr + " 07:00:00");			
				
				vo.setPml_num(chovo);
				
				ScheduleDAO dao1 = new ScheduleDAO();
				
				dao1.insert(vo);
				
				TimeTask task = new TimeTask();

				task.setChoose(choose);

				task.setPml_num(pml_num);

				task.setDate(date);
				
				task.setDatestr(datestr + " 07:00:00");
				
				
				
				ScheduledFuture<?> t =service.scheduleAtFixedRate(
						task,
						0,
						60000,
						TimeUnit.MILLISECONDS);
				
				task.setT(t);
			

				//timer.schedule(task, date);

//				try {
//
//					Thread.sleep(20000);
//
//				} catch (InterruptedException e) {
//
//				}
				

				scab.getServletContext().setAttribute("date", null);

				scab.getServletContext().setAttribute("pml_num", null);

				scab.getServletContext().setAttribute("choose", null);

			} catch (ParseException e) {

				e.printStackTrace();

			}/*finally{
				timer.cancel();
				System.out.println("timer已關閉");
			}*/
		}

	}
}