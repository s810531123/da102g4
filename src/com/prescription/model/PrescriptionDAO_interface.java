package com.prescription.model;

import java.util.List;

public interface PrescriptionDAO_interface {
	public void insert(PrescriptionVO prescriptionVO);
	public void delete(Integer pre_id);
	public void update(PrescriptionVO prescriptionVO);
	public PrescriptionVO findByPrimaryKey(Integer pre_id);
	public List<PrescriptionVO> getAll();

}
