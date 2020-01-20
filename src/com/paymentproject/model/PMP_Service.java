package com.paymentproject.model;

import java.util.List;
import java.util.Set;

public class PMP_Service {
	private PMPDAO_interface dao;

	public PMP_Service() {
		dao = new PMP_DAO();
	}

	public void update(String pml_num,String Pml_item,String pml_money) {
		PMP_VO pmpVO = new PMP_VO();
		pmpVO.setPml_num(Integer.parseInt(pml_num));
		pmpVO.setPml_item(Pml_item);
		pmpVO.setPml_money(Integer.parseInt(pml_money));
		pmpVO.setPf_status(1);
		dao.update(pmpVO);
	}
	public void addProject(String Pml_item,Integer pml_money) {
		PMP_VO pmpVO = new PMP_VO();		
		pmpVO.setPf_status(1);		
		pmpVO.setPml_item(Pml_item);
		pmpVO.setPml_money(pml_money);
		dao.insert(pmpVO); 
	}
	public List<PMP_VO> getAll() {
		return dao.getAll();
	}

	public PMP_VO getOneDept(Integer pml_num) {
		return dao.findByPrimaryKey(pml_num);
	}

	public Set<PMP_VO> getEmpsByDeptno(Integer pml_num) {
		return dao.getEmpsByDeptno(pml_num);
	}

	public void deleteDept(Integer pml_num) {
		dao.delete(pml_num);
	}
	
	public List<String> getAllpmpnum(){
		dao.getAllpmpnum();
		return null;
		
	}
	
	
}
