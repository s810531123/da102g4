package com.commBook.model;

import java.sql.Date;

public class CommBookVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private  Integer comm_Id;
	private  String st_Num;
	private  String t_Num;
	private  java.sql.Date comm_Date;
	private  String comm_T_Msg;
	private  String comm_Res;
	private  String comm_P_Msg;
	
	public CommBookVO() {
		super();
	}

	public CommBookVO(Integer comm_Id, String st_Num, String t_Num, Date comm_Date, String comm_T_Msg, String comm_Res,
			String comm_P_Msg) {
		super();
		this.comm_Id = comm_Id;
		this.st_Num = st_Num;
		this.t_Num = t_Num;
		this.comm_Date = comm_Date;
		this.comm_T_Msg = comm_T_Msg;
		this.comm_Res = comm_Res;
		this.comm_P_Msg = comm_P_Msg;
	}

	public Integer getComm_Id() {
		return comm_Id;
	}

	public void setComm_Id(Integer comm_Id) {
		this.comm_Id = comm_Id;
	}

	public String getSt_Num() {
		return st_Num;
	}

	public void setSt_Num(String st_Num) {
		this.st_Num = st_Num;
	}

	public String getT_Num() {
		return t_Num;
	}

	public void setT_Num(String t_Num) {
		this.t_Num = t_Num;
	}

	public java.sql.Date getComm_Date() {
		return comm_Date;
	}

	public void setComm_Date(java.sql.Date comm_Date) {
		this.comm_Date = comm_Date;
	}

	public String getComm_T_Msg() {
		return comm_T_Msg;
	}

	public void setComm_T_Msg(String comm_T_Msg) {
		this.comm_T_Msg = comm_T_Msg;
	}

	public String getComm_Res() {
		return comm_Res;
	}

	public void setComm_Res(String comm_Res) {
		this.comm_Res = comm_Res;
	}

	public String getComm_P_Msg() {
		return comm_P_Msg;
	}

	public void setComm_P_Msg(String comm_P_Msg) {
		this.comm_P_Msg = comm_P_Msg;
	}
	 
	
	
}
