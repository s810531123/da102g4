package com._class.model;

import java.util.List;

public class ClassService {
	private ClassDAO_interface dao;
	
	public ClassService() {
		dao = new ClassJNDIDAO();
	}
	
	public ClassVO addClass(String t_num_1 , String t_num_2 , String cs_name , Integer cs_years) {
		
		ClassVO classVO = new ClassVO();
		
		classVO.setT_num_1(t_num_1);
		classVO.setT_num_2(t_num_2);
		classVO.setCs_name(cs_name);
		classVO.setCs_years(cs_years);
		dao.insert(classVO);
		
		return classVO;
	}
	
	public ClassVO updateClass(String cs_num,String t_num_1 , String t_num_2 , String cs_name , Integer cs_years) {
		
		ClassVO classVO = new ClassVO();
		
		classVO.setCs_num(cs_num);
		classVO.setT_num_1(t_num_1);
		classVO.setT_num_2(t_num_2);
		classVO.setCs_name(cs_name);
		classVO.setCs_years(cs_years);
		
		dao.update(classVO);
		return classVO;
	}
	
	public void deleteClass(String cs_num) {
		dao.delete(cs_num);
	}
	
	public ClassVO getOneClass(String cs_num) {
		return dao.findByPrimaryKey(cs_num);
	}
	
	public List<ClassVO> getAll() {
		return dao.getAll();
	}
}
