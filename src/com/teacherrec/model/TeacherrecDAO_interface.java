package com.teacherrec.model;

import java.util.List;

public interface TeacherrecDAO_interface {
		
	public void insert(TeacherrecVO teacherrecVO);
	public void update(TeacherrecVO teacherrecVO);
	public void delete(Integer r_Num);
	public List<TeacherrecVO> getTrecByT_num(String T_Num);
	public List<TeacherrecVO> getAll();
}
