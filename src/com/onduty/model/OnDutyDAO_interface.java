package com.onduty.model;

import java.util.List;

public interface OnDutyDAO_interface {
	//新增
	public void insert(OnDutyVO onDutyVO);
	//修改
	public void update(OnDutyVO onDutyVO);
	//刪除
	public void delete(Integer on_duty_num);
	//主鍵查詢
	public OnDutyVO findByPrimaryKey(Integer on_duty_num);
	//查詢全部
	public List<OnDutyVO> getAll();
}
