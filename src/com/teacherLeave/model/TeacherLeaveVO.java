package com.teacherLeave.model;

import java.sql.Date;

public class TeacherLeaveVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer tl_Id;
	private String t_Num;
	private java.sql.Date tl_Sdate;
	private java.sql.Date tl_Edate;
	private Integer tl_Type;
	private String tl_Reason;
	private java.sql.Date tl_App_Date;
	private Integer tl_Status;
	
	public TeacherLeaveVO() {
		super();
	}

	public TeacherLeaveVO(Integer tl_Id, String t_Num, Date tl_Sdate, Date tl_Edate, Integer tl_Type, String tl_Reason,
			Date tl_App_Date, Integer tl_Status) {
		super();
		this.tl_Id = tl_Id;
		this.t_Num = t_Num;
		this.tl_Sdate = tl_Sdate;
		this.tl_Edate = tl_Edate;
		this.tl_Type = tl_Type;
		this.tl_Reason = tl_Reason;
		this.tl_App_Date = tl_App_Date;
		this.tl_Status = tl_Status;
	}

	public Integer getTl_Id() {
		return tl_Id;
	}

	public void setTl_Id(Integer tl_Id) {
		this.tl_Id = tl_Id;
	}

	public String getT_Num() {
		return t_Num;
	}

	public void setT_Num(String t_Num) {
		this.t_Num = t_Num;
	}

	public java.sql.Date getTl_Sdate() {
		return tl_Sdate;
	}

	public void setTl_Sdate(java.sql.Date tl_Sdate) {
		this.tl_Sdate = tl_Sdate;
	}

	public java.sql.Date getTl_Edate() {
		return tl_Edate;
	}

	public void setTl_Edate(java.sql.Date tl_Edate) {
		this.tl_Edate = tl_Edate;
	}

	public Integer getTl_Type() {
		return tl_Type;
	}

	public void setTl_Type(Integer tl_Type) {
		this.tl_Type = tl_Type;
	}

	public String getTl_Reason() {
		return tl_Reason;
	}

	public void setTl_Reason(String tl_Reason) {
		this.tl_Reason = tl_Reason;
	}

	public java.sql.Date getTl_App_Date() {
		return tl_App_Date;
	}

	public void setTl_App_Date(java.sql.Date tl_App_Date) {
		this.tl_App_Date = tl_App_Date;
	}

	public Integer getTl_Status() {
		return tl_Status;
	}

	public void setTl_Status(Integer tl_Status) {
		this.tl_Status = tl_Status;
	}

}
