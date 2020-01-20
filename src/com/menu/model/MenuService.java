package com.menu.model;

import java.util.*;

import com.resform.model.ResformVO;

public class MenuService {

	private MenuDAO_interface dao;

	public MenuService() {
		dao = new MenuJDBCDAO();
	}

	public MenuVO addMenu(String mu_Name, String mu_Time, java.sql.Date mu_Date) {
		MenuVO menuVO = new MenuVO();

		menuVO.setMu_Name(mu_Name);
		menuVO.setMu_Time(mu_Time);
		menuVO.setMu_Date(mu_Date);

		dao.insert(menuVO);

		return menuVO;
	}

	public List<MenuVO> getAll() {
		return dao.getAll();
	}
	
	public void deleteMenu(Integer mu_Num) {
		dao.delete(mu_Num);
	}
	
	public MenuVO updateMenu(Integer mu_Num, String mu_Name, String mu_Time, java.sql.Date mu_Date) {
		
		MenuVO menuVO = new MenuVO();
		
		menuVO.setMu_Num(mu_Num);
		menuVO.setMu_Name(mu_Name);
		menuVO.setMu_Time(mu_Time);
		menuVO.setMu_Date(mu_Date);
		dao.update(menuVO);
		return menuVO;
	}

	public List<MenuVO> getMenuBymu_Date(String datestr) {
		return dao.getMenuBymu_Date(datestr);
	}
	
	public MenuVO getOneMenu(Integer mu_Num) {
		return dao.findByPrimaryKey(mu_Num);
	}

}
