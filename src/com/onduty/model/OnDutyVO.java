package com.onduty.model;

import java.io.Serializable;

public class OnDutyVO implements Serializable{
	private Integer on_duty_num;
	private String  on_duty_cs;
	private String  on_duty_time;
	
	public OnDutyVO() {
		super();
	}

	public Integer getOn_duty_num() {
		return on_duty_num;
	}

	public void setOn_duty_num(Integer on_duty_num) {
		this.on_duty_num = on_duty_num;
	}

	public String getOn_duty_cs() {
		return on_duty_cs;
	}

	public void setOn_duty_cs(String on_duty_cs) {
		this.on_duty_cs = on_duty_cs;
	}

	public String getOn_duty_time() {
		return on_duty_time;
	}

	public void setOn_duty_time(String on_duty_time) {
		this.on_duty_time = on_duty_time;
	}
	
	
	
	
}
