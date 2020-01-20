package com.class_ann.model;

import java.util.List;

public interface Class_annDAO_interface {
	public void insert(Class_annVO class_annVO);
	public void update(Class_annVO class_annVO);
	public void delete(Integer cs_ann_num);
	public Class_annVO findByPrimaryKey(Integer cs_ann_num);
	public List<Class_annVO> getAll();
}