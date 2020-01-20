package com.student.model;

import java.io.Serializable;
import java.sql.*;

public class StudentVO implements Serializable{
	
	private String st_num;
	private String cs_num;
	private String gd_id;
	private String st_name;
	private String st_gender;
	private String st_id;
	private String st_r_address;
	private String st_address;
	private String st_password;
	private Date st_birthday;
	private byte[] st_photo;
	private Integer st_status;
	
	public StudentVO() {
		super();
	}

	public String getSt_num() {
		return st_num;
	}

	public void setSt_num(String st_num) {
		this.st_num = st_num;
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

	public String getSt_name() {
		return st_name;
	}

	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}

	public String getSt_gender() {
		return st_gender;
	}

	public void setSt_gender(String st_gender) {
		this.st_gender = st_gender;
	}

	public String getSt_id() {
		return st_id;
	}

	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}

	public String getSt_r_address() {
		return st_r_address;
	}

	public void setSt_r_address(String st_r_address) {
		this.st_r_address = st_r_address;
	}

	public String getSt_address() {
		return st_address;
	}

	public void setSt_address(String st_address) {
		this.st_address = st_address;
	}

	public String getSt_password() {
		return st_password;
	}

	public void setSt_password(String st_password) {
		this.st_password = st_password;
	}

	public Date getSt_birthday() {
		return st_birthday;
	}

	public void setSt_birthday(Date st_birthday) {
		this.st_birthday = st_birthday;
	}

	public byte[] getSt_photo() {
		return st_photo;
	}

	public void setSt_photo(byte[] st_photo) {
		this.st_photo = st_photo;
	}

	public Integer getSt_status() {
		return st_status;
	}

	public void setSt_status(Integer st_status) {
		this.st_status = st_status;
	}

}
