package com.teacher.model;

import java.sql.Date;
import java.util.List;

public class TeacherService {
	
	private TeacherDAO_interface dao;
	
	public TeacherService() {
		dao = new TeacherJNDIDAO();
	}
	
	public TeacherVO addTeacher(String t_name ,String t_gender, String t_id, 
			String t_email, String t_r_address, String t_address, String t_job,
			String t_password,Date t_birthday, Integer t_status, byte[] t_photo) {
		
		TeacherVO teacherVO = new TeacherVO();
		
		teacherVO.setT_name(t_name);
		teacherVO.setT_gender(t_gender);
		teacherVO.setT_id(t_id);
		teacherVO.setT_email(t_email);
		teacherVO.setT_r_address(t_r_address);
		teacherVO.setT_address(t_address);
		teacherVO.setT_job(t_job);
		teacherVO.setT_password(t_password);
		teacherVO.setT_birthday(t_birthday);
		teacherVO.setT_status(t_status);
		teacherVO.setT_photo(t_photo);
		dao.insert(teacherVO);
		
		return teacherVO;
	}
	
	public TeacherVO updateTeacher(String t_num, String t_name ,String t_gender, String t_id, 
			String t_email, String t_r_address, String t_address, String t_job, String t_password,
			Date t_birthday, Integer t_status, byte[] t_photo) {
		
		TeacherVO teacherVO = new TeacherVO();
		
		teacherVO.setT_num(t_num);
		teacherVO.setT_name(t_name);
		teacherVO.setT_gender(t_gender);
		teacherVO.setT_id(t_id);
		teacherVO.setT_email(t_email);
		teacherVO.setT_r_address(t_r_address);
		teacherVO.setT_address(t_address);
		teacherVO.setT_job(t_job);
		teacherVO.setT_password(t_password);
		teacherVO.setT_birthday(t_birthday);
		teacherVO.setT_status(t_status);
		teacherVO.setT_photo(t_photo);
		dao.update(teacherVO);
		
		return teacherVO;
	}
	
	public void deleteTeacher(String t_num) {
		
		dao.delete(t_num);
		
	}
	
	public List<TeacherVO> getAll() {
		return dao.getAll();
	}
	
	public TeacherVO getOneTeacher(String t_num) {
		
		return dao.findByT_Num(t_num);
	}
	
	public TeacherVO findOneTeacher(String t_id) {
		return dao.findByT_Id(t_id);
	}

}
