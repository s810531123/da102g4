package com.class_messageboard.model;

import java.io.Serializable;

public class Class_messageboardVO implements Serializable{
	private Integer msg_num;
	private String cs_num;
	private String gd_id;
	private String t_num;
	private String msg;
	
	public Class_messageboardVO() {}

	public Class_messageboardVO(Integer msg_num, String cs_num, String gd_id, String t_num, String t_msg, String gd_msg, String msg) {
		super();
		this.msg_num = msg_num;
		this.cs_num = cs_num;
		this.gd_id = gd_id;
		this.t_num = t_num;
		this.msg = msg;	
	}

	public Integer getMsg_num() {
		return msg_num;
	}

	public void setMsg_num(Integer msg_num) {
		this.msg_num = msg_num;
	}

	public String getCs_num() {
		return cs_num;
	}

	public void setCs_num(String cs_num) {
		this.cs_num = cs_num;
	}

	public String getGd_id() {
		return gd_id;
	}

	public void setGd_id(String gd_id) {
		this.gd_id = gd_id;
	}

	public String getT_num() {
		return t_num;
	}

	public void setT_num(String t_num) {
		this.t_num = t_num;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
	
	
	
}
