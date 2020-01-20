package com.courselist.model;

import java.util.List;


public interface CourseListDAO_interface {
	public void insert(CourseListVO courseListVO);

	public void update(CourseListVO courseListVO);
	public void updateStatus(CourseListVO courseListVO);

	public void delete(Integer cou_num,String st_num);

	public CourseListVO findByPrimaryKey(Integer cou_num,String st_num);
	
	public List<CourseListVO> findByCourseNum(Integer cou_num); 
	
	public Integer signUpCount(Integer cou_num);
	
	public Integer payStatusCount(Integer cou_num);

	public List<CourseListVO> getCou_numBySt_num(String st_num);	
	
	public List<CourseListVO> getAll();

}
