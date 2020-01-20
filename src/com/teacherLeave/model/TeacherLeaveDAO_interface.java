package com.teacherLeave.model;

import java.util.List;

public interface TeacherLeaveDAO_interface {
	
	public void insert (TeacherLeaveVO teacherLeaveVO);
	public void update (TeacherLeaveVO teacherLeaveVO);
    public void delete(Integer tl_Id);
	public TeacherLeaveVO findByPrimaryKey(Integer tl_Id);
	public List<TeacherLeaveVO> getAll();

}
