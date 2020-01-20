package com.pmf_schedule.model;

public class ScheduleVO {
	private String sdu_num;
	public String getSdu_num() {
		return sdu_num;
	}
	public void setSdu_num(String sdu_num) {
		this.sdu_num = sdu_num;
	}
	private String pml_num;
	private String pml_item;
	private String pml_time;
	
	public String getPml_num() {
		return pml_num;
	}
	public void setPml_num(String pml_num) {
		this.pml_num = pml_num;
	}
	public String getPml_item() {
		return pml_item;
	}
	public void setPml_item(String pml_item) {
		this.pml_item = pml_item;
	}
	public String getPml_time() {
		return pml_time;
	}
	public void setPml_time(String pml_time) {
		this.pml_time = pml_time;
	}
	
}
