package com.substitute.model;

import java.sql.Date;

public class SubstituteVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer sub_Num;
	private String cs_Num;
	private String t_Num;
	private java.sql.Date sub_Sdate; 
	private java.sql.Date sub_Edate;
	
	public SubstituteVO() {
		super();
	}

	public SubstituteVO(Integer sub_Num, String cs_Num, String t_Num, Date sub_Sdate, Date sub_Edate) {
		super();
		this.sub_Num = sub_Num;
		this.cs_Num = cs_Num;
		this.t_Num = t_Num;
		this.sub_Sdate = sub_Sdate;
		this.sub_Edate = sub_Edate;
	}

	public Integer getSub_Num() {
		return sub_Num;
	}

	public void setSub_Num(Integer sub_Num) {
		this.sub_Num = sub_Num;
	}

	public String getCs_Num() {
		return cs_Num;
	}

	public void setCs_Num(String cs_Num) {
		this.cs_Num = cs_Num;
	}

	public String getT_Num() {
		return t_Num;
	}

	public void setT_Num(String t_Num) {
		this.t_Num = t_Num;
	}

	public java.sql.Date getSub_Sdate() {
		return sub_Sdate;
	}

	public void setSub_Sdate(java.sql.Date sub_Sdate) {
		this.sub_Sdate = sub_Sdate;
	}

	public java.sql.Date getSub_Edate() {
		return sub_Edate;
	}

	public void setSub_Edate(java.sql.Date sub_Edate) {
		this.sub_Edate = sub_Edate;
	} 
	
	
}
