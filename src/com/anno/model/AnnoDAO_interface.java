package com.anno.model;

import java.util.*;

public interface AnnoDAO_interface {
	
	public void insert(AnnoVO annoVO);
	public void update(AnnoVO annoVO);
	public void delete(Integer anno_no);
	public AnnoVO findByPrimaryKey(Integer anno_no);
	public List <AnnoVO> getAll();

}
