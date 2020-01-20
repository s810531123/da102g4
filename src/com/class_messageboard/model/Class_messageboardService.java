package com.class_messageboard.model;

import java.util.List;



public class Class_messageboardService {
	
	private Class_messageboardDAO_interface dao;
	
	public Class_messageboardService() {
		dao = new Class_messageboardJNDIDAO();
	}
	
	public Class_messageboardVO addFontClass_messageboard(String cs_num,String gd_id,String msg) {
		Class_messageboardVO class_messageboardVO = new Class_messageboardVO();
		
		class_messageboardVO.setCs_num(cs_num);
		class_messageboardVO.setGd_id(gd_id);
		class_messageboardVO.setMsg(msg);
		dao.insertFont(class_messageboardVO);
		
		return class_messageboardVO;
		
	}
	
	
	public Class_messageboardVO addBackClass_messageboard(String cs_num,String t_num,String msg) {
		Class_messageboardVO class_messageboardVO = new Class_messageboardVO();
		
		class_messageboardVO.setCs_num(cs_num);
		class_messageboardVO.setT_num(t_num);
		class_messageboardVO.setMsg(msg);
		dao.insertBack(class_messageboardVO);
		
		return class_messageboardVO;
		
	}
	
	public Class_messageboardVO updateClass_messageboardVO(Integer msg_num, String cs_num, String gd_id,
			String t_num, String msg) {

		Class_messageboardVO class_messageboardVO = new Class_messageboardVO();

		class_messageboardVO.setMsg_num(msg_num);
		class_messageboardVO.setCs_num(cs_num);
		class_messageboardVO.setGd_id(gd_id);
		class_messageboardVO.setT_num(t_num);
		class_messageboardVO.setMsg(msg);
		dao.update(class_messageboardVO);

		return class_messageboardVO;
	}
	
	

	
	public void deleteClass_messageboard(Integer msg_num) {
		dao.delete(msg_num);
	}

	public Class_messageboardVO getOneEmp(Integer msg_num) {
		return dao.findByPrimaryKey(msg_num);
	}

	public List<Class_messageboardVO> getAll() {
		return dao.getAll();
	}
	
	
}
