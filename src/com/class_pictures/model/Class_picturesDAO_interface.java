package com.class_pictures.model;

import java.util.List;

public interface Class_picturesDAO_interface {
	public void insert(Class_picturesVO class_pictureVO);
	public void update(Class_picturesVO class_pictureVO);
	public void delete(Integer cs_pic_num);
	public Class_picturesVO findByPrimaryKey(Integer cs_pic_num);
	public List<Class_picturesVO> getAll();
	public Class_picturesVO findByPic_cs(String pic_cs);
	
}
