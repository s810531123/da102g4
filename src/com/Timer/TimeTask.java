package com.Timer;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.TimerTask;
import java.util.concurrent.ScheduledFuture;



import com.car.model.CarService;
import com.car.model.CarVO;

import com.paymentform.model.PMF_Service;
import com.paymentform.model.PMF_VO;
import com.paymentlist.model.PML_Service;
import com.paymentlist.model.PML_VO;
import com.paymentproject.model.PMP_Service;

import com.payschedule.model.ScheduleDAO;
import com.student.model.StudentService;
import com.student.model.StudentVO;


public class TimeTask extends TimerTask {
	private java.util.Date date;
	private String pml_num;
	private String choose;
	private ScheduledFuture<?> t;
	private String datestr;

	public String getDatestr() {
		return datestr;
	}

	public void setDatestr(String datestr) {
		this.datestr = datestr;
	}

	public ScheduledFuture<?> getT() {
		return t;
	}

	public void setT(ScheduledFuture<?> t) {
		this.t = t;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public String getPml_num() {
		return pml_num;
	}

	public void setPml_num(String pml_num) {
		this.pml_num = pml_num;
	}

	@Override
	public void run() {		
		
		String datestr2 = this.datestr;
	
		java.util.Date curr = new java.util.Date();
		java.util.Date date = this.date;
		System.out.println(curr.toString().substring(0,16));
		System.out.println(date.toString().substring(0,16));
		if (curr.toString().substring(0,16).equals(date.toString().substring(0,16))) {

			String pml_num = this.pml_num;
			String choose = this.choose;

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			String datestr = format.format(date);
			try {
				date = format.parse(datestr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date sd = new java.sql.Date(date.getTime());

			PMF_Service pmfsvc = new PMF_Service();
			PMF_VO pmfVO = new PMF_VO();
			PMP_Service pmpsvc = new PMP_Service();
			pmfVO.setPf_status(0);
			pmfVO.setMonth(sd);
			pmfVO.setPml_trail(0);
			pmfVO.setPf_time(null);
			PML_VO pmlVO = new PML_VO();
			PML_Service pmlsvc = new PML_Service();
			if ("所有學生".equals(choose)) {  

				StudentService STsvc = new StudentService();
				for (String str : STsvc.getStudentNum()) {  
					if (pmfsvc.havepmf(str, sd)) {
						pmlVO.setPml_num(Integer.parseInt(pml_num));
						pmlVO.setPf_num(pmfsvc.getOnePMF(str, sd));
						pmlsvc.addASC(pmlVO);
						System.out.println(pmfsvc.getOnePMF(str, sd)+"/n"+pmpsvc.getOneDept(Integer.parseInt(pml_num)).getPml_money());
						pmfsvc.addmoney(pmfsvc.getOnePMF(str, sd), pmpsvc.getOneDept(Integer.parseInt(pml_num)).getPml_money());
					} else {
						int pf_num = pmfsvc.getcurrseq1();
						pmfVO.setSt_num(str);
						pmfsvc.addPMF2(pmfVO);
						//System.out.println(pmpsvc.getOneDept(Integer.parseInt(pml_num)).getPml_money());
					    pmfsvc.addmoney(pf_num, pmpsvc.getOneDept(Integer.parseInt(pml_num)).getPml_money());

						pmlVO.setPml_num(Integer.parseInt(pml_num));
						pmlVO.setPf_num(pf_num);
						pmlsvc.addASC(pmlVO);
					}
				}
			} else if ("向日葵班".equals(choose)) {
				StudentService STsvc = new StudentService();
				for (StudentVO sdvo : STsvc.getStudentByCs_Num("C001")) {
					String st_num = sdvo.getSt_num();
					if (pmfsvc.havepmf(st_num, sd)) {
						pmlVO.setPml_num(Integer.parseInt(pml_num));
						pmlVO.setPf_num(pmfsvc.getOnePMF(st_num, sd));
						pmlsvc.addASC(pmlVO);
						pmfsvc.addmoney(pmfsvc.getOnePMF(st_num, sd), pmpsvc.getOneDept(Integer.parseInt(pml_num)).getPml_money());
					} else {
						int pf_num = pmfsvc.getcurrseq1();
						pmfVO.setSt_num(st_num);
						pmfsvc.addPMF2(pmfVO);
						
						  pmfsvc.addmoney(pf_num, pmpsvc.getOneDept(Integer.parseInt(pml_num)).getPml_money());

						pmlVO.setPml_num(Integer.parseInt(pml_num));
						pmlVO.setPf_num(pf_num);
						pmlsvc.addASC(pmlVO);
					}
				}
			} else if ("玫瑰班".equals(choose)) {
				StudentService STsvc = new StudentService();
				for (StudentVO sdvo : STsvc.getStudentByCs_Num("C002")) {
					String st_num = sdvo.getSt_num();
					if (pmfsvc.havepmf(st_num, sd)) {
						pmlVO.setPml_num(Integer.parseInt(pml_num));
						pmlVO.setPf_num(pmfsvc.getOnePMF(st_num, sd));
						pmlsvc.addASC(pmlVO);
						pmfsvc.addmoney(pmfsvc.getOnePMF(st_num, sd), pmpsvc.getOneDept(Integer.parseInt(pml_num)).getPml_money());
					} else {
						int pf_num = pmfsvc.getcurrseq1();
						pmfVO.setSt_num(st_num);
						pmfsvc.addPMF2(pmfVO);
						
						  pmfsvc.addmoney(pf_num, pmpsvc.getOneDept(Integer.parseInt(pml_num)).getPml_money());

						pmlVO.setPml_num(Integer.parseInt(pml_num));
						pmlVO.setPf_num(pf_num);
						pmlsvc.addASC(pmlVO);
					}
				}
			} else if ("櫻花班".equals(choose)) {
				StudentService STsvc = new StudentService();
				for (StudentVO sdvo : STsvc.getStudentByCs_Num("C003")) {
					String st_num = sdvo.getSt_num();
					if (pmfsvc.havepmf(st_num, sd)) {
						pmlVO.setPml_num(Integer.parseInt(pml_num));
						pmlVO.setPf_num(pmfsvc.getOnePMF(st_num, sd));
						pmlsvc.addASC(pmlVO);
						pmfsvc.addmoney(pmfsvc.getOnePMF(st_num, sd), pmpsvc.getOneDept(Integer.parseInt(pml_num)).getPml_money());
					} else {
						int pf_num = pmfsvc.getcurrseq1();
						pmfVO.setSt_num(st_num);
						pmfsvc.addPMF2(pmfVO);
						
						  pmfsvc.addmoney(pf_num, pmpsvc.getOneDept(Integer.parseInt(pml_num)).getPml_money());

						pmlVO.setPml_num(Integer.parseInt(pml_num));
						pmlVO.setPf_num(pf_num);
						pmlsvc.addASC(pmlVO);
					}
				}
			} else if ("坐娃娃車的學生".equals(choose)) {
				CarService carsvc = new CarService();
				for (CarVO str : carsvc.getStNumByMonth("2019-09")) {
					String st_num = str.getSt_num();
					if (pmfsvc.havepmf(st_num, sd)) {
						pmlVO.setPml_num(Integer.parseInt(pml_num));
						pmlVO.setPf_num(pmfsvc.getOnePMF(st_num, sd));
						pmlsvc.addASC(pmlVO);
						pmfsvc.addmoney(pmfsvc.getOnePMF(st_num, sd), pmpsvc.getOneDept(Integer.parseInt(pml_num)).getPml_money());
					} else {
						int pf_num = pmfsvc.getcurrseq1();
						pmfVO.setSt_num(st_num);
						pmfsvc.addPMF2(pmfVO);
						
						  pmfsvc.addmoney(pf_num, pmpsvc.getOneDept(Integer.parseInt(pml_num)).getPml_money());

						pmlVO.setPml_num(Integer.parseInt(pml_num));
						pmlVO.setPf_num(pf_num);
						pmlsvc.addASC(pmlVO);
					}
				}
			}
			ScheduleDAO dao = new ScheduleDAO();
			PMP_Service dao2 = new PMP_Service();
			dao.delete(datestr2,choose,dao2.getOneDept(Integer.parseInt(pml_num)).getPml_item());
			System.out.println("完成");			
			 this.t.cancel(false);
		}
	}
}