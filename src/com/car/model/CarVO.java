package com.car.model;

import java.io.Serializable;

public class CarVO implements Serializable{

	private Integer car_num;
	private String st_num;
	private String car_month;
	
	public CarVO() {
		super();
	}

	public Integer getCar_num() {
		return car_num;
	}

	public void setCar_num(Integer car_num) {
		this.car_num = car_num;
	}

	public String getSt_num() {
		return st_num;
	}

	public void setSt_num(String st_num) {
		this.st_num = st_num;
	}

	public String getCar_month() {
		return car_month;
	}

	public void setCar_month(String car_month) {
		this.car_month = car_month;
	}
	
	
}
