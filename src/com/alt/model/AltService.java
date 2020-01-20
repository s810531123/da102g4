package com.alt.model;

import java.util.List;
import java.util.Set;

public class AltService {

	private AltDAO_interface dao;

	public AltService() {
		dao = new AltJDBCDAO();
	}
	
//	public void updateArrOrLea(String )
	public AltVO getOneBySt_numAndDate(String st_num , String date) {
		return dao.getOneByDate(st_num, date);
	}

	public AltVO updateArrOrLea(String st_Num, java.sql.Timestamp alt_Arr, java.sql.Timestamp alt_Lea,String date) {

		AltVO altVO = new AltVO();
		
		altVO.setSt_Num(st_Num);
		altVO.setAlt_Arr(alt_Arr);
		altVO.setAlt_Lea(alt_Lea);
		dao.updateArrOrLea(altVO, date);
		return altVO;
	}
	
	
	public AltVO addAlt(String st_Num, java.sql.Timestamp alt_Arr, java.sql.Timestamp alt_Lea,
			java.sql.Timestamp alt_Leat) {

		AltVO altVO = new AltVO();

		altVO.setSt_Num(st_Num);
		altVO.setAlt_Arr(alt_Arr);
		altVO.setAlt_Lea(alt_Lea);
		altVO.setAlt_Leat(alt_Leat);

		dao.insert(altVO);

		return altVO;
	}

	public List<AltVO> getAll() {
		return dao.getAll();
	}

	public void deleteAlt(Integer alt_Num) {
		dao.delete(alt_Num);
	}

	public AltVO updateAlt(Integer alt_Num, String st_Num, java.sql.Timestamp alt_Arr, java.sql.Timestamp alt_Lea,java.sql.Timestamp alt_Leat) {

		AltVO altVO = new AltVO();
		
		altVO.setAlt_No(alt_Num);
		altVO.setSt_Num(st_Num);
		altVO.setAlt_Arr(alt_Arr);
		altVO.setAlt_Lea(alt_Leat);
		altVO.setAlt_Leat(alt_Leat);
		dao.update(altVO);
		return altVO;
	}

	public List<AltVO> getAltBySt_Num(String st_Num) {
		return dao.getAltBySt_Num(st_Num);
	}



}
