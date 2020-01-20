package com.paymentform.model;

import java.sql.Date;

public class PMF_VO {
	private Integer pf_num;
	private Integer pml_trail;
	private Integer pf_status;
	private String st_num;
	private Date month;
	private Date pf_time;

	public Integer getPf_num() {
		return pf_num;
	}

	public void setPf_num(Integer pf_num) {
		this.pf_num = pf_num;
	}

	public Integer getPml_trail() {
		return pml_trail;
	}

	public void setPml_trail(Integer pml_trail) {
		this.pml_trail = pml_trail;
	}

	public String getPf_status() {
		if(pf_status == 1) {
		return "已繳費";
		}
		else if(pf_status == 0) {
		return "未繳費";
		}
		else return null;
	}

	public void setPf_status(Integer pf_status) {
		this.pf_status = pf_status;
	}

	public String getSt_num() {
		return st_num;
	}

	public void setSt_num(String st_num) {
		this.st_num = st_num;
	}

	public Date getMonth() {
		
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public Date getPf_time() {
		return pf_time;
	}

	public void setPf_time(Date pf_time) {
		this.pf_time = pf_time;
	}
}
