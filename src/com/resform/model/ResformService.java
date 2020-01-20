package com.resform.model;

import java.util.*;


public class ResformService {

	private ResformDAO_interface dao;

	public ResformService() {
		dao = new ResformJDBCDAO();
	}

	public ResformVO addResform(String t_Num, String res_Name, String res_Email, String res_Phone,
			java.sql.Timestamp res_Date, String res_Msg) {

		ResformVO resformVO = new ResformVO();

		resformVO.setT_Num(t_Num);
		resformVO.setRes_Name(res_Name);
		resformVO.setRes_Email(res_Email);
		resformVO.setRes_Phone(res_Phone);
		resformVO.setRes_Date(res_Date);
		resformVO.setRes_Msg(res_Msg);

		dao.insert(resformVO);

		return resformVO;
	}

	public List<ResformVO> getAll() {
		return dao.getAll();
	}

	public void deleteResform(Integer res_Num) {
		dao.delete(res_Num);
	}
	
	public ResformVO getOneRes(Integer res_Num) {
		return dao.findByPrimaryKey(res_Num);
	}

	public ResformVO update(Integer res_Num, String t_Num, String res_Name, String res_Email, String res_Phone, java.sql.Timestamp res_Date, String res_Msg) {

		ResformVO resformVO = new ResformVO();

		resformVO.setRes_Num(res_Num);
		resformVO.setT_Num(t_Num);
		resformVO.setRes_Name(res_Name);
		resformVO.setRes_Email(res_Email);
		resformVO.setRes_Phone(res_Phone);
		resformVO.setRes_Date(res_Date);
		resformVO.setRes_Msg(res_Msg);
		dao.update(resformVO);
		return resformVO;
	}
	public List<ResformVO> getResByres_Date(String datestr){
		return dao.getResByres_Date(datestr);
	}

}
