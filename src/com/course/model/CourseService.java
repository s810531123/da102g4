package com.course.model;

import java.util.List;
import java.util.Map;

public class CourseService {
	private CourseDAO_interface dao;

	public CourseService() {
		dao = new CourseJNDIDAO();
	}

	public CourseVO addCourse(String t_num, String cou_name,
			String cou_introd, Integer cou_cost, Integer cou_min,Integer cou_max,
			java.sql.Date cou_sdate,java.sql.Date cou_edate,byte[] cou_pic) {

		CourseVO courseVO = new CourseVO();

		courseVO.setCou_cost(cou_cost);
		courseVO.setCou_edate(cou_edate);
		courseVO.setCou_sdate(cou_sdate);
		courseVO.setCou_introd(cou_introd);
		courseVO.setCou_min(cou_min);
		courseVO.setCou_max(cou_max);
		courseVO.setCou_name(cou_name);
		courseVO.setCou_pic(cou_pic);
		courseVO.setT_num(t_num);
		
		dao.insert(courseVO);

		return courseVO;
	}

	public CourseVO updateCourse(String t_num, String cou_name,
			String cou_introd, Integer cou_cost, Integer cou_min, Integer cou_max,
			java.sql.Date cou_sdate,java.sql.Date cou_edate,byte[] cou_pic, Integer cou_num) {

		CourseVO courseVO = new CourseVO();

		courseVO.setCou_num(cou_num);
		courseVO.setCou_cost(cou_cost);
		courseVO.setCou_edate(cou_edate);
		courseVO.setCou_sdate(cou_sdate);
		courseVO.setCou_introd(cou_introd);
		courseVO.setCou_min(cou_min);
		courseVO.setCou_max(cou_max);
		courseVO.setCou_name(cou_name);
		courseVO.setCou_pic(cou_pic);
		courseVO.setT_num(t_num);
		dao.update(courseVO);

		return courseVO;
	}
	
	public CourseVO updateCourseWithOutPic(String t_num, String cou_name,
			String cou_introd, Integer cou_cost, Integer cou_min, Integer cou_max,
			java.sql.Date cou_sdate,java.sql.Date cou_edate, Integer cou_num) {

		CourseVO courseVO = new CourseVO();

		courseVO.setCou_num(cou_num);
		courseVO.setCou_cost(cou_cost);
		courseVO.setCou_edate(cou_edate);
		courseVO.setCou_sdate(cou_sdate);
		courseVO.setCou_introd(cou_introd);
		courseVO.setCou_min(cou_min);
		courseVO.setCou_max(cou_max);
		courseVO.setCou_name(cou_name);
		courseVO.setT_num(t_num);
		dao.updateWithOutPic(courseVO);

		return courseVO;
	}
	
	public CourseVO updateCourseStatus(Integer cou_status,Integer cou_num) {

		CourseVO courseVO = new CourseVO();

		courseVO.setCou_num(cou_num);
		courseVO.setCou_status(cou_status);
		dao.updateStatus(courseVO);

		return courseVO;
	}

	public void deleteCourse(Integer cou_num) {
		dao.delete(cou_num);
	}

	public CourseVO getOneCourse(Integer cou_num) {
		return dao.findByPrimaryKey(cou_num);
	}

	public List<CourseVO> getAll() {
		return dao.getAll();
	}
	
	public List<CourseVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
