package com.alt.model;

	import java.sql.Timestamp;
	import java.io.Serializable;

	public class AltVO implements java.io.Serializable{
		
		private Integer alt_No;
		private String st_Num;
		private java.sql.Timestamp alt_Arr;
		private java.sql.Timestamp alt_Lea;
		private java.sql.Timestamp alt_Leat;

		public AltVO() {
			super();
		}

		public Integer getAlt_No() {
			return alt_No;
		}

		public void setAlt_No(Integer alt_No) {
			this.alt_No = alt_No;
		}

		public String getSt_Num() {
			return st_Num;
		}

		public void setSt_Num(String st_Num) {
			this.st_Num = st_Num;
		}

		public java.sql.Timestamp getAlt_Arr() {
			return alt_Arr;
		}

		public void setAlt_Arr(java.sql.Timestamp alt_Arr) {
			this.alt_Arr = alt_Arr;
		}

		public java.sql.Timestamp getAlt_Lea() {
			return alt_Lea;
		}

		public void setAlt_Lea(java.sql.Timestamp alt_Lea) {
			this.alt_Lea = alt_Lea;
		}

		public java.sql.Timestamp getAlt_Leat() {
			return alt_Leat;
		}

		public void setAlt_Leat(java.sql.Timestamp alt_Leat) {
			this.alt_Leat = alt_Leat;
		}
		
	}
