package com.student_leave_list.model;

import java.util.List;

public class Student_leave_listService {
	
	private Student_leave_listDAO_interface dao;
	
	public Student_leave_listService() {
		dao = new Student_leave_listJNDIDAO();
	}
	
	
	public Student_leave_listVO addStudent_leave_list(String st_num,java.sql.Timestamp start_date,java.sql.Timestamp end_date,Integer st_list_sort,String st_list_note) {
		
		Student_leave_listVO student_leave_listVO = new Student_leave_listVO();	
		
		student_leave_listVO.setSt_num(st_num);
		student_leave_listVO.setStart_date(start_date);
		student_leave_listVO.setEnd_date(end_date);
		student_leave_listVO.setSt_list_sort(st_list_sort);
		student_leave_listVO.setSt_list_note(st_list_note);
		dao.insert(student_leave_listVO);
		
		return student_leave_listVO;
		
	}
	
	
	public Student_leave_listVO updateStudent_leave_list(Integer st_list_num,String st_num,java.sql.Timestamp start_date,java.sql.Timestamp end_date,Integer st_list_sort,String st_list_note) {

		Student_leave_listVO student_leave_listVO = new Student_leave_listVO();

		student_leave_listVO.setSt_list_num(st_list_num);
		student_leave_listVO.setSt_list_num(st_list_num);
		student_leave_listVO.setStart_date(start_date);
		student_leave_listVO.setEnd_date(end_date);
		student_leave_listVO.setSt_list_sort(st_list_sort);
		student_leave_listVO.setSt_list_note(st_list_note);
	
		dao.update(student_leave_listVO);

		return student_leave_listVO;
	}
	
	public void deleteStudent_leave_list(Integer st_list_num) {
		dao.delete(st_list_num);
	}

	public Student_leave_listVO getOneStudent_leave_list(Integer st_list_num) {
		return dao.findByPrimaryKey(st_list_num);
	}

	public List<Student_leave_listVO> getAll() {
		return dao.getAll();
	}
	
	public List<Student_leave_listVO> findByPrimaryKeyyy(String st_num) {
		return dao.findByPrimaryKeyyy(st_num);
		
	}
}	
