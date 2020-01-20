package com.menu.model;

import java.sql.Date;
import java.io.Serializable;

public class MenuVO implements java.io.Serializable{
	
	private Integer mu_Num;
	private String mu_Name;
	private String mu_Time;
	private java.sql.Date mu_Date;
	
	public MenuVO() {
		super();
	}

	
	public Integer getMu_Num() {
		return mu_Num;
	}
	
	public void setMu_Num(Integer mu_Num) {
		this.mu_Num = mu_Num;
	}
	
	public String getMu_Name() {
		return mu_Name;
	}
	
	public void setMu_Name(String mu_Name) {
		this.mu_Name = mu_Name;
	}
	
	public String getMu_Time() {
		return mu_Time;
	}
	
	public void setMu_Time(String mu_Time) {
		this.mu_Time = mu_Time;
	}
	
	public java.sql.Date getMu_Date() {
		return mu_Date;
	}
	
	public void setMu_Date(java.sql.Date mu_Date) {
		this.mu_Date = mu_Date;
	}
	
}
