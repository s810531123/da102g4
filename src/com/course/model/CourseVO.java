package com.course.model;

public class CourseVO implements java.io.Serializable{
	private Integer cou_num;
	private String t_num;
	private String cou_name;
	private String cou_introd;
	private Integer cou_cost;
	private Integer cou_min;
	private Integer cou_max;
	private Integer cou_status;
	private java.sql.Date cou_sdate;
	private java.sql.Date cou_edate;
	private byte[] cou_pic;
	
	
	public Integer getCou_min() {
		return cou_min;
	}
	public void setCou_min(Integer cou_min) {
		this.cou_min = cou_min;
	}
	public Integer getCou_num() {
		return cou_num;
	}
	public void setCou_num(Integer cou_num) {
		this.cou_num = cou_num;
	}
	public String getT_num() {
		return t_num;
	}
	public void setT_num(String t_num) {
		this.t_num = t_num;
	}
	public String getCou_name() {
		return cou_name;
	}
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}
	public String getCou_introd() {
		return cou_introd;
	}
	public void setCou_introd(String cou_introd) {
		this.cou_introd = cou_introd;
	}
	public Integer getCou_cost() {
		return cou_cost;
	}
	public void setCou_cost(Integer cou_cost) {
		this.cou_cost = cou_cost;
	}
	public Integer getCou_max() {
		return cou_max;
	}
	public void setCou_max(Integer cou_max) {
		this.cou_max = cou_max;
	}
	public Integer getCou_status() {
		return cou_status;
	}
	public void setCou_status(Integer cou_status) {
		this.cou_status = cou_status;
	}
	public java.sql.Date getCou_sdate() {
		return cou_sdate;
	}
	public void setCou_sdate(java.sql.Date cou_sdate) {
		this.cou_sdate = cou_sdate;
	}
	public java.sql.Date getCou_edate() {
		return cou_edate;
	}
	public void setCou_edate(java.sql.Date cou_edate) {
		this.cou_edate = cou_edate;
	}
	public byte[] getCou_pic() {
		return cou_pic;
	}
	public void setCou_pic(byte[] cou_pic) {
		this.cou_pic = cou_pic;
	}
	
	

}
