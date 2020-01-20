package com.class_pictures.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Class_picturesVO implements Serializable{
	private Integer cs_pic_num;
	private String cs_num;
	private Timestamp ul_date;
	private byte[] pic;
	private String pic_cs;
	
	public Class_picturesVO() {}

	public Class_picturesVO(Integer cs_pic_num, String cs_num, Timestamp ul_date, byte[] pic, String pic_cs) {
		super();
		this.cs_pic_num = cs_pic_num;
		this.cs_num = cs_num;
		this.ul_date = ul_date;
		this.pic = pic;
		this.pic_cs = pic_cs;
	}

	
	public Integer getCs_pic_num() {
		return cs_pic_num;
	}

	public void setCs_pic_num(Integer cs_pic_num) {
		this.cs_pic_num = cs_pic_num;
	}

	public String getCs_num() {
		return cs_num;
	}

	public void setCs_num(String cs_num) {
		this.cs_num = cs_num;
	}

	public Timestamp getUl_date() {
		return ul_date;
	}

	public void setUl_date(Timestamp ul_date) {
		this.ul_date = ul_date;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public String getPic_cs() {
		return pic_cs;
	}

	public void setPic_cs(String pic_cs) {
		this.pic_cs = pic_cs;
	}
	
	
}