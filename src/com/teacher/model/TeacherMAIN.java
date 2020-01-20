package com.teacher.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TeacherMAIN {

	public static void main(String[] args) {
		
		TeacherService svc = new TeacherService();
		
//		//新增
//		TeacherVO vo1 = svc.addTeacher("吳神", "男性", "S123456789", "10@gmail.com", "桃園市中壢區中大路300號", "桃園市中壢區中大路300號", "老師", "teacher_10", Date.valueOf("2001-01-01"), 1);
//		System.out.println(vo1.getT_name());
//		System.out.println(vo1.getT_gender());
//		System.out.println(vo1.getT_id());
//		System.out.println(vo1.getT_email());
//		System.out.println(vo1.getT_r_address());
//		System.out.println(vo1.getT_address());
//		System.out.println(vo1.getT_job());
//		System.out.println(vo1.getT_password());
//		System.out.println(vo1.getT_birthday());
//		System.out.println(vo1.getT_status());
//		
//		
//		//修改
//		TeacherVO vo2 = svc.updateTeacher("T032", "吳神", "男性", "S123456789", "teacher_10@gmail.com", "Tainan", "Tainan", "老師", "teacher_10", Date.valueOf("2001-01-01"), 1);
//		System.out.println(vo2.getT_num());
//		System.out.println(vo2.getT_name());
//		System.out.println(vo2.getT_gender());
//		System.out.println(vo2.getT_id());
//		System.out.println(vo2.getT_email());
//		System.out.println(vo2.getT_r_address());
//		System.out.println(vo2.getT_address());
//		System.out.println(vo2.getT_job());
//		System.out.println(vo2.getT_password());
//		System.out.println(vo2.getT_birthday());
//		System.out.println(vo2.getT_status());
		
		
		
		//查詢
		List<TeacherVO> list = new ArrayList<>();
		list = svc.getAll();
		for(TeacherVO vo3 : list) {
			System.out.println(vo3.getT_num());
			System.out.println(vo3.getT_name());
			System.out.println(vo3.getT_gender());
			System.out.println(vo3.getT_id());
			System.out.println(vo3.getT_email());
			System.out.println(vo3.getT_r_address());
			System.out.println(vo3.getT_address());
			System.out.println(vo3.getT_job());
			System.out.println(vo3.getT_password());
			System.out.println(vo3.getT_birthday());
			System.out.println(vo3.getT_status());
			System.out.println("===============================");
		}
		
		
		TeacherVO vo4 = svc.getOneTeacher("T032");
		System.out.println(vo4.getT_num());
		System.out.println(vo4.getT_name());
		System.out.println(vo4.getT_gender());
		System.out.println(vo4.getT_id());
		System.out.println(vo4.getT_email());
		System.out.println(vo4.getT_r_address());
		System.out.println(vo4.getT_address());
		System.out.println(vo4.getT_job());
		System.out.println(vo4.getT_password());
		System.out.println(vo4.getT_birthday());
		System.out.println(vo4.getT_status());
		
		
//		//刪除
//		svc.deleteTeacher("T032");
		
		
		
		

	}

}
