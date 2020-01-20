package com.resform.model;

import java.util.List;

public interface ResformDAO_interface {
	
	public void insert(ResformVO resformVO);
	public void update(ResformVO resformVO);
	public void delete(Integer res_Num);
	public ResformVO findByPrimaryKey(Integer res_Num);
	//用日期查詢
	public List<ResformVO> getResByres_Date(String datestr);
	//找全部
	public List<ResformVO> getAll();
	

}
