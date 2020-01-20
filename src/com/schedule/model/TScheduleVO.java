package com.schedule.model;

import java.io.Serializable;
import java.sql.Date;

public class TScheduleVO implements Serializable{
	
	private Integer ts_num;
	private String t_num;
	private Integer on_duty_num;
	private Date ts_date;
	
	public TScheduleVO() {
		super();
	}

	public Integer getTs_num() {
		return ts_num;
	}

	public void setTs_num(Integer ts_num) {
		this.ts_num = ts_num;
	}

	public String getT_num() {
		return t_num;
	}

	public void setT_num(String t_num) {
		this.t_num = t_num;
	}

	public Integer getOn_duty_num() {
		return on_duty_num;
	}

	public void setOn_duty_num(Integer on_duty_num) {
		this.on_duty_num = on_duty_num;
	}

	public Date getTs_date() {
		return ts_date;
	}

	public void setTs_date(Date ts_date) {
		this.ts_date = ts_date;
	}
	
	
}
