package com.car.model;

import java.util.List;

public interface CarDAO_interface {

	//新增
		public void insert(CarVO carVO);
		//修改
		public void update(CarVO carVO);
		//刪除
		public void delete(Integer car_num);
		//主鍵查詢
		public CarVO findByPrimaryKey(Integer car_num);
		//學號查詢
		public List<CarVO> findByStNum(String st_num);
		//查詢全部
		public List<CarVO> getAll();
		
		public List<CarVO> getStNumByMonth(String month);
}
