package com.teacherLeave.model;

import java.util.List;

public class TeacherLeaveService {
	
	private TeacherLeaveDAO_interface dao;
	
	public TeacherLeaveService() {
		dao = new TeacherLeaveJDBCDAO();
	}
	
	public TeacherLeaveVO addTeacherLeave(String t_Num, java.sql.Date tl_Sdate, java.sql.Date tl_Edate, Integer tl_Type, String tl_Reason) {
		
		TeacherLeaveVO teacherLeaveVO = new TeacherLeaveVO();
		
		teacherLeaveVO.setT_Num(t_Num);
		teacherLeaveVO.setTl_Sdate(tl_Sdate);
		teacherLeaveVO.setTl_Edate(tl_Edate);
		teacherLeaveVO.setTl_Type(tl_Type);
		teacherLeaveVO.setTl_Reason(tl_Reason);
		
		dao.insert(teacherLeaveVO);
	
		return teacherLeaveVO;
	}
	
	public TeacherLeaveVO updateTeacherLeave(Integer tl_Id, String t_Num, java.sql.Date tl_Sdate, java.sql.Date tl_Edate, Integer tl_Type, String tl_Reason, java.sql.Date tl_App_Date, Integer tl_Status) {
		
		TeacherLeaveVO teacherLeaveVO = new TeacherLeaveVO();
		
		teacherLeaveVO.setTl_Id(tl_Id);
		teacherLeaveVO.setT_Num(t_Num);
		teacherLeaveVO.setTl_Sdate(tl_Sdate);
		teacherLeaveVO.setTl_Edate(tl_Edate);
		teacherLeaveVO.setTl_Type(tl_Type);
		teacherLeaveVO.setTl_Reason(tl_Reason);
		teacherLeaveVO.setTl_App_Date(tl_App_Date);
		teacherLeaveVO.setTl_Status(tl_Status);
		
		dao.update(teacherLeaveVO);
		
		return teacherLeaveVO;
		
	}
	
	public void daleteTeacherLeave(Integer tl_Id) {
		dao.delete(tl_Id);
	}
	
	public TeacherLeaveVO getOneTeacherLeave(Integer tl_Id) {
		return dao.findByPrimaryKey(tl_Id);
	}
	
	public List<TeacherLeaveVO> getAll(){
		return dao.getAll();
		
	}


}
