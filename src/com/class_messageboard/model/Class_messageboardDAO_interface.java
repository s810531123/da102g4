package com.class_messageboard.model;

import java.util.List;

public interface Class_messageboardDAO_interface {
	public void insertBack(Class_messageboardVO class_messageboardVO);
	public void insertFont(Class_messageboardVO class_messageboardVO);
	public void update(Class_messageboardVO class_messageboardVO);
	public void delete(Integer msg_num);
	public Class_messageboardVO findByPrimaryKey(Integer msg_num);
	public List<Class_messageboardVO> getAll();
}