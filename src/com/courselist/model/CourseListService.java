package com.courselist.model;

import java.util.List;

public class CourseListService {
	CourseListDAO_interface dao;
	
	public CourseListService() {
		dao = new CourseListJNDIDAO();
	}
	
	public CourseListVO insert(Integer cou_num , String st_num , Integer pay_status , java.sql.Date cou_list_date) {
		
		CourseListVO courseListVO = new CourseListVO();
		
		courseListVO.setCou_num(cou_num);
		courseListVO.setSt_num(st_num);
		courseListVO.setPay_status(pay_status);
		courseListVO.setCou_list_date(cou_list_date);
		System.out.println("Service");
		dao.insert(courseListVO);
		System.out.println("Service1");
		return courseListVO;
	}
	
	public void delete(Integer cou_num , String st_num) {
		dao.delete(cou_num, st_num);
	}
	
	public CourseListVO updateCourseList(Integer cou_num , String st_num , Integer pay_status , java.sql.Date cou_list_date) {
		
		CourseListVO courseListVO = new CourseListVO();
		
		courseListVO.setSt_num(st_num);
		courseListVO.setCou_num(cou_num);
		courseListVO.setPay_status(pay_status);
		courseListVO.setCou_list_date(cou_list_date);
		dao.update(courseListVO);
		return courseListVO;
	}
	
	
public CourseListVO updateCourseListStatus(Integer cou_num , String st_num , Integer pay_status) {
		
		CourseListVO courseListVO = new CourseListVO();
		
		courseListVO.setSt_num(st_num);
		courseListVO.setCou_num(cou_num);
		courseListVO.setPay_status(pay_status);

		dao.updateStatus(courseListVO);
		return courseListVO;
	}
	
	
	public List<CourseListVO> getCou_NumBySt_Num(String st_num) {
		return dao.getCou_numBySt_num(st_num);
	}
	
	public List<CourseListVO> getAllByCou_num(Integer cou_num) {
		return dao.findByCourseNum(cou_num);
	}
	
	public Integer getSignUpCount(Integer cou_num) {
		return dao.signUpCount(cou_num);
	}
	
	public Integer getPayStatusCount(Integer cou_num) {
		return dao.payStatusCount(cou_num);
	}
	
	public CourseListVO getOneCourseList(Integer cou_num , String st_num) {
		return dao.findByPrimaryKey(cou_num, st_num);
	}
	
	
	public List<CourseListVO> getAll() {
		return dao.getAll();
	}

}
