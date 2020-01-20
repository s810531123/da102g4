package com.student.model;

import java.util.List;
import java.util.Set;

public interface StudentDAO_interface {
	
	public void insert(StudentVO studentVO);
	public void update(StudentVO studentVO);
	public void delete(String st_num);
	public List<StudentVO> getAll();
	
	//以學生姓名查詢學生資料(因可能同名，故回傳List)
	public List<StudentVO> getStudentBySt_Name(String st_name);
	
	//以學生學號查詢學生資料
	public StudentVO getStudentBySt_Num(String st_num);
	
	//查詢某班級的學生(一對多)(回傳List)
	public List<StudentVO> getStudentByCs_Num(String cs_num);
	
	//以學生姓名及班級編號查詢學生資料
	public StudentVO getStudentBySt_Name_and_Cs_Num(String st_name, String cs_num);
	
	public List<String> get_AllNum();
	public StudentVO getStudentBySt_Id(String st_id);
	
	
}