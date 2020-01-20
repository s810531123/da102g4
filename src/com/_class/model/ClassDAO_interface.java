package com._class.model;

import java.util.List;

public interface ClassDAO_interface {
	public void insert(ClassVO classVO);
	public void delete(String cs_num);
	public void update(ClassVO classVO);
	public ClassVO findByPrimaryKey(String cs_num);
	public List<ClassVO> getAll();
	
	

}
