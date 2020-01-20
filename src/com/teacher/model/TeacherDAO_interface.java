package com.teacher.model;

import java.util.List;

public interface TeacherDAO_interface {
	
	public void insert(TeacherVO teacherVO);
	public void update(TeacherVO teacherVO);
	public void delete(String t_num);
	
	//查詢全部老師資料
	public List<TeacherVO> getAll();
	
	//以教師編號查詢
	public TeacherVO findByT_Num(String t_num);
	
	public TeacherVO findByT_Id(String t_id);
	
	
	
}
