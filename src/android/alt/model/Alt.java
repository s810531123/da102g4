package android.alt.model;

import java.io.Serializable;

public class Alt implements Serializable {
	private int alt_no;
	private String st_num;
	private java.sql.Timestamp alt_arr;
	private java.sql.Timestamp alt_lea;
	private java.sql.Timestamp alt_leat;
	
	public Alt() {
		super();
	}
	
	 public Alt(String st_num) {
	        super();
	        this.st_num = st_num;
	     
	        
	    }

	
	public Alt(String st_num,java.sql.Timestamp alt_arr,java.sql.Timestamp alt_lea,java.sql.Timestamp alt_leat) {
        super();
        this.st_num = st_num;
        this.alt_arr  =alt_arr;
        this.alt_lea = alt_lea;
        this.alt_leat= alt_leat;
    }

	public int getAlt_no() {
		return alt_no;
	}

	public void setAlt_no(int alt_no) {
		this.alt_no = alt_no;
	}

	public String getSt_num() {
		return st_num;
	}

	public void setSt_num(String st_num) {
		this.st_num = st_num;
	}

	public java.sql.Timestamp getAlt_arr() {
		return alt_arr;
	}

	public void setAlt_arr(java.sql.Timestamp alt_arr) {
		this.alt_arr = alt_arr;
	}

	public java.sql.Timestamp getAlt_lea() {
		return alt_lea;
	}

	public void setAlt_lea(java.sql.Timestamp alt_lea) {
		this.alt_lea = alt_lea;
	}

	public java.sql.Timestamp getAlt_leat() {
		return alt_leat;
	}

	public void setAlt_leat(java.sql.Timestamp alt_leat) {
		this.alt_leat = alt_leat;
	}
	
	

}
