package com.course.model;

import java.util.List;
import java.util.Map;

public interface CourseDAO_interface {
	public void insert(CourseVO courseVO);

	public void update(CourseVO courseVO);
	
	public void updateWithOutPic(CourseVO courseVO);

	public void updateStatus(CourseVO courseVO);

	public void delete(Integer cou_num);

	public CourseVO findByPrimaryKey(Integer cou_num);

	public List<CourseVO> getAll();
	
	public List<CourseVO> getAll(Map<String, String[]> map); 

}
