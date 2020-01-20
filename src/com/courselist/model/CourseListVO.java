package com.courselist.model;

import java.io.Serializable;

public class CourseListVO implements Serializable{
	private Integer cou_num;
	private String st_num;
	private Integer pay_status;
	private java.sql.Date cou_list_date;
	
	public Integer getCou_num() {
		return cou_num;
	}
	public void setCou_num(Integer cou_num) {
		this.cou_num = cou_num;
	}
	public String getSt_num() {
		return st_num;
	}
	public void setSt_num(String st_num) {
		this.st_num = st_num;
	}
	public Integer getPay_status() {
		return pay_status;
	}
	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}
	public java.sql.Date getCou_list_date() {
		return cou_list_date;
	}
	public void setCou_list_date(java.sql.Date cou_list_date) {
		this.cou_list_date = cou_list_date;
	}
	
}
