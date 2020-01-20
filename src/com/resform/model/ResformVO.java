package com.resform.model;


import java.util.*;

import java.sql.*;


public class ResformVO implements java.io.Serializable {

	private Integer res_Num;
	private String t_Num;
	private String res_Name;
	private String res_Email;
	private String res_Phone;
	private java.sql.Timestamp res_Date;
	private String res_Msg;
	
	public ResformVO() {
		super();
	}
	

	public Integer getRes_Num() {
		return res_Num;
	}

	public void setRes_Num(Integer res_Num) {
		this.res_Num = res_Num;
	}

	public String getT_Num() {
		return t_Num;
	}

	public void setT_Num(String t_Num) {
		this.t_Num = t_Num;
	}

	public String getRes_Name() {
		return res_Name;
	}

	public void setRes_Name(String res_Name) {
		this.res_Name = res_Name;
	}

	public String getRes_Email() {
		return res_Email;
	}

	public void setRes_Email(String res_Email) {
		this.res_Email = res_Email;
	}

	public String getRes_Phone() {
		return res_Phone;
	}

	public void setRes_Phone(String res_Phone) {
		this.res_Phone = res_Phone;
	}

	public java.sql.Timestamp getRes_Date() {
		return res_Date;
	}

	public void setRes_Date(java.sql.Timestamp res_Date) {
		this.res_Date = res_Date;
	}

	public String getRes_Msg() {
		return res_Msg;
	}

	public void setRes_Msg(String res_Msg) {
		this.res_Msg = res_Msg;
	}

	
}
