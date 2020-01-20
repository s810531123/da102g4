package com.afterschoolclass.model;

import java.util.List;

public class ASC_Service {

	private ASCDAO_interface dao;

	public ASC_Service() {
		dao = new ASC_DAO();
	}	

	public void addASC(ASC_VO ascVO) {
		dao.insert(ascVO);
	}	

	public void updateEmp(ASC_VO ascVO) {
		dao.update(ascVO);
	}

	public void deleteEmp(Integer ascVO) {
		dao.delete(ascVO);
	}

	public List<ASC_VO> getOneStu(String st_num) {
		return dao.findByPrimaryKey(st_num);
	}

	public List<ASC_VO> getAll() {
		return dao.getAll();
	}
	public List<ASC_VO> insert(){
		return dao.insert();
	}
	
}
