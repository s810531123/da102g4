package com.schedule.model;

import java.sql.Date;
import java.util.List;

public class TScheduleService {

	private TScheduleDAO_interface dao;
	
	public TScheduleService() {
		dao = new TScheduleJDBCDAO();
	}
	
	public TScheduleVO addTSchedule(String t_num, Integer on_duty_num, Date ts_date) {
		
		TScheduleVO tScheduleVO = new TScheduleVO();
		tScheduleVO.setT_num(t_num);
		tScheduleVO.setOn_duty_num(on_duty_num);
		tScheduleVO.setTs_date(ts_date);
		dao.insert(tScheduleVO);
		
		return tScheduleVO;
	}
	
	public TScheduleVO updateTSchedule(Integer ts_num, String t_num, Integer on_duty_num, Date ts_date) {
		
		TScheduleVO tScheduleVO = new TScheduleVO();
		tScheduleVO.setTs_num(ts_num);
		tScheduleVO.setT_num(t_num);
		tScheduleVO.setOn_duty_num(on_duty_num);
		tScheduleVO.setTs_date(ts_date);
		dao.update(tScheduleVO);
		
		return tScheduleVO;
	}
	
	public void deleteTSchedule(Integer ts_num) {
		dao.delete(ts_num);
	}
	
	public TScheduleVO getOneTSchedule(Integer ts_num) {
		return dao.findByPrimaryKey(ts_num);
	}
	
	public List<TScheduleVO> getAll(){
		return dao.getAll();
	}
	
	public List<TScheduleVO> getByMonth(Integer year, Integer month){
		return dao.findByMonth(year, month);
	}

	public List<TScheduleVO> getByDate(String date){
		return dao.findByDate(date);
	}
	
	public List<TScheduleVO> getByMonthFromOneTeacher(Integer year, Integer month, String t_num){
		return dao.findByMonthFromOneTeacher(year, month, t_num);
	}
}
