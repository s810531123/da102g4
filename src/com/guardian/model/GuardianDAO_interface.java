package com.guardian.model;

import java.util.List;

import com.student.model.StudentVO;

public interface GuardianDAO_interface {
	
	public void insert(GuardianVO guardianVO);
	public void update(GuardianVO guardianVO);
	public void delete(String gd_id);
	
	//查詢所有監護人資料
	public List<GuardianVO> getAll();
	
	//以姓名查詢監護人資料
	public GuardianVO getGuardianByGd_Id(String gd_id);
	

}