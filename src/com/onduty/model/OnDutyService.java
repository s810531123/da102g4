package com.onduty.model;

import java.util.List;

public class OnDutyService {

	private OnDutyDAO_interface dao;

	public OnDutyService() {
		dao = new OnDutyJNDIDAO();
	}
	
	public OnDutyVO addOnDuty(String  on_duty_cs, String on_duty_time) {
		
		OnDutyVO onDutyVO = new OnDutyVO();
		onDutyVO.setOn_duty_cs(on_duty_cs);
		onDutyVO.setOn_duty_time(on_duty_time);
		dao.insert(onDutyVO);
		
		return onDutyVO;
	}
	
	public OnDutyVO updateOnDuty(Integer on_duty_num, String  on_duty_cs, String on_duty_time) {
		
		OnDutyVO onDutyVO = new OnDutyVO();
		onDutyVO.setOn_duty_num(on_duty_num);
		onDutyVO.setOn_duty_cs(on_duty_cs);
		onDutyVO.setOn_duty_time(on_duty_time);
		dao.update(onDutyVO);
		
		return onDutyVO;
	}
	
	public void deleteOnDuty(Integer on_duty_num) {
		dao.delete(on_duty_num);
	}
	
	public OnDutyVO getOneOnDuty(Integer on_duty_num) {
		return dao.findByPrimaryKey(on_duty_num);
	}
	
	public List<OnDutyVO> getAll(){
		return dao.getAll();
	}
	
	
}
