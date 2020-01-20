package com.guardian.model;

import java.sql.Date;
import java.util.List;


public class GuardianService {

	private GuardianDAO_interface dao;

	public GuardianService() {
		dao = new GuardianJNDIDAO();
	}

	public GuardianVO addGuardian(String gd_id, String gd_name, String gd_gender, String gd_email, String gd_address,
			String gd_rel, String gd_phone, Date gd_birthday) {

		GuardianVO guardianVO = new GuardianVO();

		guardianVO.setGd_id(gd_id);
		guardianVO.setGd_name(gd_name);
		guardianVO.setGd_gender(gd_gender);
		guardianVO.setGd_email(gd_email);
		guardianVO.setGd_address(gd_address);
		guardianVO.setGd_rel(gd_rel);
		guardianVO.setGd_phone(gd_phone);
		guardianVO.setGd_birthday(gd_birthday);
		dao.insert(guardianVO);

		return guardianVO;
	}

	public GuardianVO updateGuardian(String gd_id, String gd_name, String gd_gender, String gd_email, String gd_address,
			String gd_rel, String gd_phone, Date gd_birthday) {

		GuardianVO guardianVO = new GuardianVO();

		guardianVO.setGd_id(gd_id);
		guardianVO.setGd_name(gd_name);
		guardianVO.setGd_gender(gd_gender);
		guardianVO.setGd_email(gd_email);
		guardianVO.setGd_address(gd_address);
		guardianVO.setGd_rel(gd_rel);
		guardianVO.setGd_phone(gd_phone);
		guardianVO.setGd_birthday(gd_birthday);
		dao.update(guardianVO);

		return guardianVO;
	}

	public void deleteGuardian(String gd_id) {

		dao.delete(gd_id);

	}

	public List<GuardianVO> getAll() {
		return dao.getAll();
	}

	public GuardianVO getGuardianByGd_Id(String gd_id) {

		return dao.getGuardianByGd_Id(gd_id);
	}

}
