package com.paymentform.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.paymentlist.model.PML_VO;
import com.paymentproject.model.PMP_VO;

public interface PMFDAO_interface {
	public void insert(PMF_VO pmfVO);
	
	public void insert2(PMF_VO pmfVO);

	public void update(PMF_VO pmfVO);

	public void delete(Integer pf_num);

	public List<PMF_VO> findByPrimaryKey(String st_num);

	public List<PMF_VO> getAll();

	public Set<PMF_VO> getEmpsByDeptno(Integer pf_num);
	
	public Integer getcurrseq1();
	
	public Integer getcurrseq2();		

	public List<PMP_VO> getPmlByPfnum(Integer pf_num);
	
	public boolean havepmf(String st_num,Date month);
	
	public Integer getOnePMF(String st_num,Date month);
	
	public void addmoney(Integer pf_num,Integer pml_trail);
	
	public void updateStatus(Integer num);
	
	public PMF_VO GetOnePMF(Integer num);
}
