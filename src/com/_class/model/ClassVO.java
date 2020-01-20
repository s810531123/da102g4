package com._class.model;

import java.io.Serializable;

public class ClassVO implements Serializable{
	private String cs_num;
	private String t_num_1;
	private String t_num_2;
	private String cs_name;
	private Integer cs_years;
	
	public String getCs_num() {
		return cs_num;
	}
	public void setCs_num(String cs_num) {
		this.cs_num = cs_num;
	}
	public String getT_num_1() {
		return t_num_1;
	}
	public void setT_num_1(String t_num_1) {
		this.t_num_1 = t_num_1;
	}
	public String getT_num_2() {
		return t_num_2;
	}
	public void setT_num_2(String t_num_2) {
		this.t_num_2 = t_num_2;
	}
	public String getCs_name() {
		return cs_name;
	}
	public void setCs_name(String cs_name) {
		this.cs_name = cs_name;
	}
	public Integer getCs_years() {
		return cs_years;
	}
	public void setCs_years(Integer cs_years) {
		this.cs_years = cs_years;
	}
	
	
}
