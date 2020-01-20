package com.guardian.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GuardianMAIN {

	public static void main(String[] args) {
		
		GuardianService svc = new GuardianService();
		
		//新增
		GuardianVO vo1 = svc.addGuardian("Z123456784", "美心的媽媽", "女", "kkk@gmail.com", "Kouhshoung", "母女", "912345680", Date.valueOf("1993-01-01"));
		System.out.println(vo1.getGd_id());
		System.out.println(vo1.getGd_name());
		System.out.println(vo1.getGd_gender());
		System.out.println(vo1.getGd_email());
		System.out.println(vo1.getGd_address());
		System.out.println(vo1.getGd_rel());
		System.out.println(vo1.getGd_phone());
		System.out.println(vo1.getGd_birthday());
		
		
		//修改
		GuardianVO vo2 = svc.updateGuardian("Z123456784", "美心的媽媽", "女", "kkk@gmail.com", "Tainan", "母女", "912345680", Date.valueOf("1993-01-01"));
		System.out.println(vo2.getGd_id());
		System.out.println(vo2.getGd_name());
		System.out.println(vo2.getGd_gender());
		System.out.println(vo2.getGd_email());
		System.out.println(vo2.getGd_address());
		System.out.println(vo2.getGd_rel());
		System.out.println(vo2.getGd_phone());
		System.out.println(vo2.getGd_birthday());
		
		
		//查詢
		List<GuardianVO> list = new ArrayList<>();
		list = svc.getAll();
		for(GuardianVO vo3 : list) {
			System.out.println(vo3.getGd_id());
			System.out.println(vo3.getGd_name());
			System.out.println(vo3.getGd_gender());
			System.out.println(vo3.getGd_email());
			System.out.println(vo3.getGd_address());
			System.out.println(vo3.getGd_rel());
			System.out.println(vo3.getGd_phone());
			System.out.println(vo3.getGd_birthday());
			System.out.println("===============================");
		}
		
		
		GuardianVO vo4 = svc.getGuardianByGd_Id("S123456785");
		System.out.println(vo4.getGd_id());
		System.out.println(vo4.getGd_name());
		System.out.println(vo4.getGd_gender());
		System.out.println(vo4.getGd_email());
		System.out.println(vo4.getGd_address());
		System.out.println(vo4.getGd_rel());
		System.out.println(vo4.getGd_phone());
		System.out.println(vo4.getGd_birthday());
		
		
//		//刪除
//		svc.deleteGuardian("Z123456784");
		
		

	}

}
