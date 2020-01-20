package com.paymentform.model;

import java.sql.Date;
import java.util.List;

import com.afterschoolclass.model.ASC_VO;
import com.paymentlist.model.PML_VO;
import com.paymentproject.model.PMP_VO;



public class PMF_Service {
	private PMFDAO_interface dao;

	public PMF_Service() {
		dao = new PMF_DAO();
	}	

	public Integer getcurrseq2() {		
		return dao.getcurrseq2();		
	}
	
	public Integer getcurrseq1() {		
		return dao.getcurrseq1();		
	}
	public void addPMF(PMF_VO asfVO) {
		dao.insert(asfVO);
	}	

	public void addPMF2(PMF_VO asfVO) {
		dao.insert2(asfVO);
	}	
	
	public void updatePMF(PMF_VO ascVO) {
		dao.update(ascVO);
	}
	
	public void updateStatus(Integer num) {
		dao.updateStatus(num);
	}

	public void deletePMF(Integer ascVO) {
		dao.delete(ascVO);
	}

	public List<PMF_VO> getOnePMF(String st_num) {
		return dao.findByPrimaryKey(st_num);
	}
	
	public Integer getOnePMF(String st_num,Date month) {
		return dao.getOnePMF(st_num,month);
	}
	 
	
	public List<PMF_VO> getAll() {
		return dao.getAll();
	}
	public List<PMP_VO> getPmlByPfnum(Integer pf_num){
		return dao.getPmlByPfnum(pf_num);
	}
	
	public boolean havepmf(String st_num, Date month) {
		return dao.havepmf(st_num,month);
	}
	public void addmoney(Integer pf_num,Integer pml_trail) {
		dao.addmoney(pf_num, pml_trail);
	}
	public PMF_VO GetOnePMF(Integer num) {
		return dao.GetOnePMF(num);
	}
}
