package com.student.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class StudentMAIN {

	public static void main(String[] args) {
		
		
		
		StudentService svc = new StudentService();
		
//		//新增
//		StudentVO vo1 = svc.addStudent("C001", "Z123456784", "美心", "女", "S000000010", "Kouhshoung", "Kouhshoung", "000010", Date.valueOf("2019-01-11"), 0);
//		
//		System.out.println(vo1.getCs_num());
//		System.out.println(vo1.getGd_id());
//		System.out.println(vo1.getSt_name());
//		System.out.println(vo1.getSt_gender());
//		System.out.println(vo1.getSt_id());
//		System.out.println(vo1.getSt_r_address());
//		System.out.println(vo1.getSt_address());
//		System.out.println(vo1.getSt_password());
//		System.out.println(vo1.getSt_birthday());
//		System.out.println(vo1.getSt_status());
//		
//		
//		//修改
//		StudentVO vo2 = svc.updateStudent("S013", "C001", "Z123456784", "美心", "女", "S000000010", "Tainan", "Tainan", "000010", Date.valueOf("2019-01-11"), 0);
//		
//		System.out.println(vo2.getSt_num());
//		System.out.println(vo2.getCs_num());
//		System.out.println(vo2.getGd_id());
//		System.out.println(vo2.getSt_name());
//		System.out.println(vo2.getSt_gender());
//		System.out.println(vo2.getSt_id());
//		System.out.println(vo2.getSt_r_address());
//		System.out.println(vo2.getSt_address());
//		System.out.println(vo2.getSt_password());
//		System.out.println(vo2.getSt_birthday());
//		System.out.println(vo2.getSt_status());
		
		//查詢
		List<StudentVO> list1 = new ArrayList<StudentVO>();
		list1 = svc.getAll();
		
		for(StudentVO vo3 : list1) {
			System.out.println(vo3.getSt_num());
			System.out.println(vo3.getCs_num());
			System.out.println(vo3.getGd_id());
			System.out.println(vo3.getSt_name());
			System.out.println(vo3.getSt_gender());
			System.out.println(vo3.getSt_id());
			System.out.println(vo3.getSt_r_address());
			System.out.println(vo3.getSt_address());
			System.out.println(vo3.getSt_password());
			System.out.println(vo3.getSt_birthday());
			System.out.println(vo3.getSt_status());
			System.out.println("==================================");
		}
		
		StudentVO vo4 = svc.getStudentBySt_Num("S013");
		
		System.out.println(vo4.getSt_num());
		System.out.println(vo4.getCs_num());
		System.out.println(vo4.getGd_id());
		System.out.println(vo4.getSt_name());
		System.out.println(vo4.getSt_gender());
		System.out.println(vo4.getSt_id());
		System.out.println(vo4.getSt_r_address());
		System.out.println(vo4.getSt_address());
		System.out.println(vo4.getSt_password());
		System.out.println(vo4.getSt_birthday());
		System.out.println(vo4.getSt_status());
		
		
		List<StudentVO> list2 = new ArrayList<StudentVO>();
		list2 = svc.getStudentByCs_Num("C001");
		
		for(StudentVO vo5 : list2) {
			System.out.println(vo5.getSt_num());
			System.out.println(vo5.getCs_num());
			System.out.println(vo5.getGd_id());
			System.out.println(vo5.getSt_name());
			System.out.println(vo5.getSt_gender());
			System.out.println(vo5.getSt_id());
			System.out.println(vo5.getSt_r_address());
			System.out.println(vo5.getSt_address());
			System.out.println(vo5.getSt_password());
			System.out.println(vo5.getSt_birthday());
			System.out.println(vo5.getSt_status());
			System.out.println("==================================");
		}
		
		StudentVO vo6 = svc.getStudentBySt_Name_and_Cs_Num("美心", "C001");
		System.out.println(vo6.getSt_num());
		System.out.println(vo6.getCs_num());
		System.out.println(vo6.getGd_id());
		System.out.println(vo6.getSt_name());
		System.out.println(vo6.getSt_gender());
		System.out.println(vo6.getSt_id());
		System.out.println(vo6.getSt_r_address());
		System.out.println(vo6.getSt_address());
		System.out.println(vo6.getSt_password());
		System.out.println(vo6.getSt_birthday());
		System.out.println(vo6.getSt_status());
//		
//		
//		//刪除
//		svc.deleteStudent("S013");
		

	}

}
