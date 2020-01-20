package com.car.model;

import java.util.List;

public class CarService {
	
	private CarDAO_interface dao;
	
	public CarService() {
		dao = new CarJNDIDAO();
	}
	
	public CarVO addCar(String st_num, String car_month) {
		
		CarVO carVO = new CarVO();
		
		carVO.setSt_num(st_num);
		carVO.setCar_month(car_month);
		dao.insert(carVO);
		
		return carVO;
	}
	
	public CarVO updateCar(Integer car_num, String st_num, String car_month) {
		
		CarVO carVO = new CarVO();
		
		carVO.setCar_num(car_num);
		carVO.setSt_num(st_num);
		carVO.setCar_month(car_month);
		dao.update(carVO);
		
		return carVO;
	}
	
	public void deleteCar(Integer car_num) {
		dao.delete(car_num);
	}
	
	public CarVO getOneCar(Integer car_num) {
		return dao.findByPrimaryKey(car_num);
	}
	
	public List<CarVO> getCarByStNum(String st_num){
		return dao.findByStNum(st_num);
	}
	
	public List<CarVO> getAll(){
		return dao.getAll();
	}
	
	public List<CarVO> getStNumByMonth(String month){
		return dao.getStNumByMonth(month);
	}
	
}
