package com.schedule.model;

import java.util.List;

public interface TScheduleDAO_interface {
	
	public void insert(TScheduleVO tScheduleVO);
	public void update(TScheduleVO tScheduleVO);
	public void delete(Integer ts_num);
	public TScheduleVO findByPrimaryKey(Integer ts_num);
	public List<TScheduleVO> getAll();
	public List<TScheduleVO> findByMonth(Integer year, Integer month);
	public List<TScheduleVO> findByDate(String date);
	public List<TScheduleVO> findByT_num(String t_num);
	public List<TScheduleVO> findByMonthFromOneTeacher(Integer year, Integer month, String t_num);
	
}
