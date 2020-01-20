package com.student.model;

import java.sql.Date;
import java.util.List;


public class StudentService {
	
	private StudentDAO_interface dao;
	
	public StudentService() {
		dao = new StudentJNDIDAO();
	}
	
	public StudentVO addStudent(String cs_num, String gd_id, String st_name, String st_gender, String st_id,
			String st_r_address, String st_address, String st_password, Date st_birthday, Integer st_status, byte[] st_photo) {
		
		StudentVO studentVO = new StudentVO();

		studentVO.setCs_num(cs_num);
		studentVO.setGd_id(gd_id);
		studentVO.setSt_name(st_name);
		studentVO.setSt_gender(st_gender);
		studentVO.setSt_id(st_id);
		studentVO.setSt_r_address(st_r_address);
		studentVO.setSt_address(st_address);
		studentVO.setSt_password(st_password);
		studentVO.setSt_birthday(st_birthday);
		studentVO.setSt_status(st_status);
		studentVO.setSt_photo(st_photo);
		dao.insert(studentVO);
		
		return studentVO;	
	}
	
	public StudentVO updateStudent(String st_num, String cs_num, String gd_id, String st_name, String st_gender, String st_id,
			String st_r_address, String st_address, String st_password, Date st_birthday, Integer st_status, byte[] st_photo) {
		
		StudentVO studentVO = new StudentVO();

		studentVO.setSt_num(st_num);
		studentVO.setCs_num(cs_num);
		studentVO.setGd_id(gd_id);
		studentVO.setSt_name(st_name);
		studentVO.setSt_gender(st_gender);
		studentVO.setSt_id(st_id);
		studentVO.setSt_r_address(st_r_address);
		studentVO.setSt_address(st_address);
		studentVO.setSt_password(st_password);
		studentVO.setSt_birthday(st_birthday);
		studentVO.setSt_status(st_status);
		studentVO.setSt_photo(st_photo);
		dao.update(studentVO);
		
		return studentVO;	
	}
	
	
	public void deleteStudent(String st_num) {

		dao.delete(st_num);

	}

	public List<StudentVO> getAll() {
		return dao.getAll();
	}

	public StudentVO getStudentBySt_Num(String st_num) {

		return dao.getStudentBySt_Num(st_num);
	}
	
	
	public List<StudentVO> getStudentByCs_Num(String cs_num) {
		
		return dao.getStudentByCs_Num(cs_num);
	}
	
	
	public StudentVO getStudentBySt_Name_and_Cs_Num(String st_name, String cs_num) {
		
		return dao.getStudentBySt_Name_and_Cs_Num(st_name, cs_num);
	}
	
	public List<String> getStudentNum() {
		
		return dao.get_AllNum();		
	}
	
	public StudentVO getStudentBySt_Id(String st_id) {
		
		return dao.getStudentBySt_Id(st_id);
	}
	
	

}
