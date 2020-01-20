package com.class_ann.model;

import java.util.List;



public class Class_annService {
	
	private Class_annDAO_interface dao;

	public Class_annService() {
		dao = new Class_annJNDIDAO();
	}

	public Class_annVO addClass_ann(String cs_num, String cs_ann_text, java.sql.Date cs_ann_date,
			String cs_ann_ti, String cs_ann_kind) {

		Class_annVO class_annVO = new Class_annVO();

		class_annVO.setCs_num(cs_num);
		class_annVO.setCs_ann_text(cs_ann_text);
		class_annVO.setCs_ann_date(cs_ann_date);
		class_annVO.setCs_ann_ti(cs_ann_ti);
		class_annVO.setCs_ann_kind(cs_ann_kind);
		dao.insert(class_annVO);

		return class_annVO;
	}

	public Class_annVO updateClass_ann(Integer cs_ann_num, String cs_num, String cs_ann_text,
			java.sql.Date cs_ann_date, String cs_ann_ti, String cs_ann_kind) {

		Class_annVO class_annVO = new Class_annVO();

		class_annVO.setCs_ann_num(cs_ann_num);
		class_annVO.setCs_num(cs_num);
		class_annVO.setCs_ann_text(cs_ann_text);
		class_annVO.setCs_ann_date(cs_ann_date);
		class_annVO.setCs_ann_ti(cs_ann_ti);
		class_annVO.setCs_ann_kind(cs_ann_kind);
		dao.update(class_annVO);

		return class_annVO;
	}

	public void deleteClass_ann(Integer cs_ann_num) {
		dao.delete(cs_ann_num);
	}

	public Class_annVO getOneClass_ann(Integer cs_ann_num) {
		return dao.findByPrimaryKey(cs_ann_num);
	}

	public List<Class_annVO> getAll() {
		return dao.getAll();
	}
}
