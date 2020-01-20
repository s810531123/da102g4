package com.alt.model;

import java.util.List;

public interface AltDAO_interface {
	
	public void insert(AltVO altVO);
	public void update(AltVO altVO);
	public void delete(Integer alt_No);
	public AltVO findByPrimaryKey(Integer alt_No);
	//用學號查詢
	public List<AltVO> getAltBySt_Num(String st_Num);
	//找全部
	public List<AltVO> getAll();
	
	public void updateArrOrLea(AltVO altVO,String date);
	public AltVO getOneByDate(String st_num,String date);

}
