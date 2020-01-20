package com.student_leave_list.model;

import java.util.*;


public interface Student_leave_listDAO_interface {
	public void insert(Student_leave_listVO student_leave_listVO);
	public void update(Student_leave_listVO student_leave_listVO);
	public void delete(Integer st_list_num);
	public Student_leave_listVO findByPrimaryKey(Integer st_list_num);
	public List<Student_leave_listVO> findByPrimaryKeyyy(String st_num);
	public List<Student_leave_listVO> getAll();
}
