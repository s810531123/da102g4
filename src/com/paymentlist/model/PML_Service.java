package com.paymentlist.model;

import java.util.List;

public class PML_Service {
	private PMLDAO_interface dao;

	public PML_Service() {
		dao = new PML_DAO();
	}	

	public void addASC(PML_VO ascVO) {
		dao.insert(ascVO);
	}	

	public void updateEmp(PML_VO ascVO) {
		dao.update(ascVO);
	}

	public void deleteEmp(Integer ascVO) {
		dao.delete(ascVO);
	}

	public PML_VO getOneEmp(Integer nursery_no) {
		return dao.findByPrimaryKey(nursery_no);
	}

	public List<PML_VO> getAll() {
		return dao.getAll();
	}
}
