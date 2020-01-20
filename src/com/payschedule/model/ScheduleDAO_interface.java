package com.payschedule.model;

import java.util.List;
import java.util.Set;

import com.paymentproject.model.PMP_VO;

public interface ScheduleDAO_interface {
	public void insert(ScheduleVO pmpVO);

	public void update(ScheduleVO pmpVO);

	public void delete(String time ,String pml_item,String pml_num);

	public ScheduleVO findByPrimaryKey(Integer pml_num);

	public List<ScheduleVO> getAll();

	public Set<ScheduleVO> getEmpsByDeptno(Integer pf_num);
	
	public List<String> getAllpmpnum();
}
