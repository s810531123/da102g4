package com.afterschoolclass.model;

import java.util.List;

public interface ASCDAO_interface {
	
	public void insert(ASC_VO ascVO);
	
    public void update(ASC_VO ascVO);
    
    public void delete(Integer nursery_no);
    
    public  List<ASC_VO> findByPrimaryKey(String st_num);
    
    public List<ASC_VO> getAll();
    
    public List<ASC_VO> insert();
}
