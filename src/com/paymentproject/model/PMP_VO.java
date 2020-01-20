package com.paymentproject.model;

public class PMP_VO {
	private Integer pml_num=0;
	private String pml_item=null;
	private Integer pml_money=0;
	private Integer pf_status=0;

	public Integer getPml_num() {
		return pml_num;
	}

	public void setPml_num(Integer pml_num) {
		this.pml_num = pml_num;
	}

	public String getPml_item() {
		return pml_item;
	}

	public void setPml_item(String pml_item) {
		this.pml_item = pml_item;
	}

	public Integer getPml_money() {
		return pml_money;
	}

	public void setPml_money(Integer pml_money) {
		this.pml_money = pml_money;
	}

	public Integer getPf_status() {
		return pf_status;
	}

	public void setPf_status(Integer pf_status) {
		this.pf_status =pf_status;
	}
}
