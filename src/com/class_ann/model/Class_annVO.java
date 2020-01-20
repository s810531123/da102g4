package com.class_ann.model;

import java.io.Serializable;
import java.sql.Date;

public class Class_annVO implements Serializable{
	private Integer cs_ann_num;
	private String cs_num;
	private String cs_ann_text;
	private Date cs_ann_date;
	private String cs_ann_ti;
	private String cs_ann_kind;
	
	public Class_annVO() {}

	
	public Class_annVO(Integer cs_ann_num, String cs_num, String cs_ann_text, Date cs_ann_date, String cs_ann_ti,
			String cs_ann_kind) {
		super();
		this.cs_ann_num = cs_ann_num;
		this.cs_num = cs_num;
		this.cs_ann_text = cs_ann_text;
		this.cs_ann_date = cs_ann_date;
		this.cs_ann_ti = cs_ann_ti;
		this.cs_ann_kind = cs_ann_kind;
	}


	public Integer getCs_ann_num() {
		return cs_ann_num;
	}


	public void setCs_ann_num(Integer cs_ann_num) {
		this.cs_ann_num = cs_ann_num;
	}


	public String getCs_num() {
		return cs_num;
	}


	public void setCs_num(String cs_num) {
		this.cs_num = cs_num;
	}


	public String getCs_ann_text() {
		return cs_ann_text;
	}


	public void setCs_ann_text(String cs_ann_text) {
		this.cs_ann_text = cs_ann_text;
	}


	public Date getCs_ann_date() {
		return cs_ann_date;
	}


	public void setCs_ann_date(Date cs_ann_date) {
		this.cs_ann_date = cs_ann_date;
	}


	public String getCs_ann_ti() {
		return cs_ann_ti;
	}


	public void setCs_ann_ti(String cs_ann_ti) {
		this.cs_ann_ti = cs_ann_ti;
	}


	public String getCs_ann_kind() {
		return cs_ann_kind;
	}


	public void setCs_ann_kind(String cs_ann_kind) {
		this.cs_ann_kind = cs_ann_kind;
	}
	
	
	
	
}
