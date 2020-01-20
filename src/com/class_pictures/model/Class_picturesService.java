package com.class_pictures.model;

import java.util.List;

public class Class_picturesService {
	
	private Class_picturesDAO_interface dao;
	
	public Class_picturesService() {
		dao = new Class_picturesJNDIDAO();	
	}
	
	public Class_picturesVO addClass_pictures(String cs_num, java.sql.Timestamp ul_date, byte[] pic,
			String pic_cs) {

		Class_picturesVO class_picturesVO = new Class_picturesVO();

		class_picturesVO.setCs_num(cs_num);
		class_picturesVO.setUl_date(ul_date);
		class_picturesVO.setPic(pic);
		class_picturesVO.setPic_cs(pic_cs);
		dao.insert(class_picturesVO);

		return class_picturesVO;
	}
	
	
	public Class_picturesVO updateClass_pictures(Integer cs_pic_num, String cs_num, java.sql.Timestamp ul_date,
			byte[] pic, String pic_cs) {

		Class_picturesVO class_picturesVO = new Class_picturesVO();

		class_picturesVO.setCs_pic_num(cs_pic_num);
		class_picturesVO.setCs_num(cs_num);
		class_picturesVO.setUl_date(ul_date);
		class_picturesVO.setPic(pic);
		class_picturesVO.setPic_cs(pic_cs);
	
		dao.update(class_picturesVO);

		return class_picturesVO;
	}
	
	public void deleteClass_pictures(Integer cs_pic_num) {
		dao.delete(cs_pic_num);
	}

	public Class_picturesVO getOneClass_pictures(Integer cs_pic_num) {
		return dao.findByPrimaryKey(cs_pic_num);
	}

	public List<Class_picturesVO> getAll() {
		return dao.getAll();
	}
	
	public Class_picturesVO getByPic_CS(String pic_cs) {
		return dao.findByPic_cs(pic_cs);
	}
	
}
