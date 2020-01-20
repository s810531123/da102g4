package com.teacherrec.model;

import java.sql.Date;
import java.io.Serializable;

public class TeacherrecVO implements java.io.Serializable{
	
	private Integer r_Num;
	private String t_Num;
	private java.sql.Timestamp on_Time;
	private java.sql.Timestamp off_Time;
	
	public TeacherrecVO() {
		super();
	}

	public Integer getR_Num() {
		return r_Num;
	}

	public void setR_Num(Integer r_Num) {
		this.r_Num = r_Num;
	}

	public String getT_Num() {
		return t_Num;
	}

	public void setT_Num(String t_Num) {
		this.t_Num = t_Num;
	}

	public java.sql.Timestamp getOn_Time() {
		return on_Time;
	}

	public void setOn_Time(java.sql.Timestamp on_Time) {
		this.on_Time = on_Time;
	}

	public java.sql.Timestamp getOff_Time() {
		return off_Time;
	}

	public void setOff_Time(java.sql.Timestamp off_Time) {
		this.off_Time = off_Time;
	}
	
	

}
