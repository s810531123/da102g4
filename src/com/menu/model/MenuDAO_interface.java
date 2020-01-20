package com.menu.model;

import java.util.*;

import com.alt.model.AltVO;

public interface MenuDAO_interface {
	
	public void insert(MenuVO menuVO);
	public void update(MenuVO menuVO);
	public void delete(Integer mu_Num);
	public MenuVO findByPrimaryKey(Integer mu_Num);
	//用日期查詢
	public List<MenuVO> getMenuBymu_Date(String datestr);
	//找全部
	public List<MenuVO> getAll();
	

}
