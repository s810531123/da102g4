package com.prescription.model;

import java.util.List;

public class PrescriptionService {
	private PrescriptionDAO_interface dao;
	
	public PrescriptionService() {
		dao = new PrescriptionDAO();
	}
	
	public PrescriptionVO addPrescription(String st_num,String pre_content,java.sql.Timestamp pre_time) {
		
		PrescriptionVO prescriptionVO = new PrescriptionVO();
		
		prescriptionVO.setSt_num(st_num);
		prescriptionVO.setPre_content(pre_content);
		prescriptionVO.setPre_time(pre_time);
		
		dao.insert(prescriptionVO);
		
		return prescriptionVO;
	}
	
	public void deletePrescription(Integer pre_id) {
		dao.delete(pre_id);
	}
	
	public PrescriptionVO updatePrescription(Integer pre_id,String st_num,String pre_content,java.sql.Timestamp pre_time) {
		
		PrescriptionVO prescriptionVO = new PrescriptionVO();
		
		prescriptionVO.setPre_id(pre_id);
		prescriptionVO.setSt_num(st_num);
		prescriptionVO.setPre_content(pre_content);
		prescriptionVO.setPre_time(pre_time);
		
		dao.insert(prescriptionVO);
		
		return prescriptionVO;
	}
	
	public PrescriptionVO getOnePrescription(Integer pre_id) {
		return dao.findByPrimaryKey(pre_id);
	}
	
	public List<PrescriptionVO> getAll() {
		return dao.getAll();
	}

}
