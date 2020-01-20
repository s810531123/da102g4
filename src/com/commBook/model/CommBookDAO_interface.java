package com.commBook.model;

import java.util.List;
import java.util.Set;

import com.student.model.StudentVO;

public interface CommBookDAO_interface {
	
	public void insert (CommBookVO commBookVO);
	public void update (CommBookVO commBookVO);
	public CommBookVO findByPrimaryKey(Integer comm_Id);
	// 查詢1位幼兒的所有聯絡簿資料(前台)
	public List<CommBookVO> getRecentCommBookVO(String st_Num, String dateStr);
	public String getClassNumByTNum(String t_num);
	public List<StudentVO> getStudentList(String cs_num);
	// 指定日期查詢所有幼兒的聯絡簿資料(後台)
	public List<CommBookVO> getfindByDate(String comm_Date);
	public Set<String> showDays();
}
