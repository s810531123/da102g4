package com.student_leave_list.model;

import java.sql.Date;
import java.sql.Timestamp;


public class Student_leave_listVO implements java.io.Serializable{
	
	private Integer st_list_num;
	private String st_num;
	private Timestamp start_date;
	private Timestamp end_date;
	private Integer st_list_sort;
	private String st_list_note;
	
	public Student_leave_listVO() {}

	public Student_leave_listVO(Integer st_list_num, String st_num, Timestamp start_date, Timestamp end_date,
			Integer st_list_sort, String st_list_note) {
		
		super();
		this.st_list_num = st_list_num;
		this.st_num = st_num;
		this.start_date = start_date;
		this.end_date = end_date;
		this.st_list_sort = st_list_sort;
		this.st_list_note = st_list_note;
	}

	
	public Integer getSt_list_num() {
		return st_list_num;
	}

	public void setSt_list_num(Integer st_list_num) {
		this.st_list_num = st_list_num;
	}

	public String getSt_num() {
		return st_num;
	}

	public void setSt_num(String st_num) {
		this.st_num = st_num;
	}

	public Timestamp getStart_date() {
		return start_date;
	}

	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	public Timestamp getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}

	public Integer getSt_list_sort() {
		return st_list_sort;
	}

	public void setSt_list_sort(Integer st_list_sort) {
		this.st_list_sort = st_list_sort;
	}

	public String getSt_list_note() {
		return st_list_note;
	}

	public void setSt_list_note(String st_list_note) {
		this.st_list_note = st_list_note;
	}
	
	
	
}