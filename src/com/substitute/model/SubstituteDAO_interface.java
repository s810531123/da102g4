package com.substitute.model;

import java.util.List;

public interface SubstituteDAO_interface {
	
	public void insert (SubstituteVO substituteVO);
	public void update (SubstituteVO substituteVO);
	public SubstituteVO findByPrimaryKey(Integer sub_Num);
	public List<SubstituteVO> getAll();
}
