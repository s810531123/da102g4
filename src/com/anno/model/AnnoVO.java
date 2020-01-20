package com.anno.model;

import java.sql.Date;

public class AnnoVO implements java.io.Serializable {
	private Integer anno_no;
	private String anno_cla;
	private String anno_title;
	private String anno_text;
	private Date anno_date;
	
	public AnnoVO() {
		
	};
	
	public Integer getAnno_no() {
		return anno_no;
	}
	public void setAnno_no(Integer anno_no) {
		this.anno_no = anno_no;
	}
	public String getAnno_cla() {
		return anno_cla;
	}
	public void setAnno_cla(String anno_cla) {
		this.anno_cla = anno_cla;
	}
	public String getAnno_title() {
		return anno_title;
	}
	public void setAnno_title(String anno_title) {
		this.anno_title = anno_title;
	}
	public String getAnno_text() {
		return anno_text;
	}
	public void setAnno_text(String anno_text) {
		this.anno_text = anno_text;
	}
	public Date getAnno_date() {
		return anno_date;
	}
	public void setAnno_date(Date anno_date) {
		this.anno_date = anno_date;
	}
	

}
