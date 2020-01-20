package com.substitute.model;

import java.util.List;

public class SubstituteService {

	private SubstituteDAO_interface dao;
	
	public SubstituteService() {
		dao = new SubstituteJDBCDAO();
	}
	
	public SubstituteVO addSubstitute(String cs_Num, String t_Num, java.sql.Date sub_Sdate, java.sql.Date sub_Edate) {
	
		SubstituteVO substituteVO = new SubstituteVO();
		
		substituteVO.setCs_Num(cs_Num);
		substituteVO.setT_Num(t_Num);
		substituteVO.setSub_Sdate(sub_Sdate);
		substituteVO.setSub_Edate(sub_Edate);
		dao.insert(substituteVO);
		
		return substituteVO;
	}
	
	public SubstituteVO updateSubstitute(Integer sub_Num, String cs_Num, String t_Num, java.sql.Date sub_Sdate, java.sql.Date sub_Edate) {
		
		SubstituteVO substituteVO = new SubstituteVO();
		
		substituteVO.setSub_Num(sub_Num);
		substituteVO.setCs_Num(cs_Num);
		substituteVO.setT_Num(t_Num);
		substituteVO.setSub_Sdate(sub_Sdate);
		substituteVO.setSub_Edate(sub_Edate);
		dao.update(substituteVO);
		
		return substituteVO;
	}
	
	public SubstituteVO getOneSubstitute(Integer sub_Num) {
		return dao.findByPrimaryKey(sub_Num);
	}
	
	public List<SubstituteVO> getAll() {
		return dao.getAll();
	}

}
