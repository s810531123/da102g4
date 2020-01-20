package com.afterschoolclass.model;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ASC_VO {
	private Integer nursery_no;
	private Integer st_sum;
	private String st_num;
	private Date leveltime;
	private Timestamp time;

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getNursery_no() {
		return nursery_no;
	}

	public void setNursery_no(Integer nursery_no) {
		this.nursery_no = nursery_no;
	}

	public Integer getSt_sum() {
		return st_sum;
	}

	public void setSt_sum(Integer st_sum) {
		this.st_sum = st_sum;
	}

	public String getSt_num() {
		return st_num;
	}

	public void setSt_num(String st_num) {
		this.st_num = st_num;
	}

	public String getLeveltime() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ft.format(leveltime);
	}

	public void setLeveltime(Date leveltime) {
		this.leveltime = leveltime;
	}
}
