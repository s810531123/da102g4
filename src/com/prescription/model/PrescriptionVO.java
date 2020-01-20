package com.prescription.model;

import java.io.Serializable;

import com.sun.jmx.snmp.Timestamp;

public class PrescriptionVO implements Serializable{
	private Integer pre_id;
	private String st_num;
	private String pre_content;
	private java.sql.Timestamp pre_time;
	public Integer getPre_id() {
		return pre_id;
	}
	public void setPre_id(Integer pre_id) {
		this.pre_id = pre_id;
	}
	public String getSt_num() {
		return st_num;
	}
	public void setSt_num(String st_num) {
		this.st_num = st_num;
	}
	public String getPre_content() {
		return pre_content;
	}
	public void setPre_content(String pre_content) {
		this.pre_content = pre_content;
	}
	public java.sql.Timestamp getPre_time() {
		return pre_time;
	}
	public void setPre_time(java.sql.Timestamp pre_time) {
		this.pre_time = pre_time;
	}

}
