package com.paymentproject.model;

import java.util.List;
import java.util.Set;

public interface PMPDAO_interface {
	public void insert(PMP_VO pmpVO);

	public void update(PMP_VO pmpVO);

	public void delete(Integer pml_num);

	public PMP_VO findByPrimaryKey(Integer pml_num);

	public List<PMP_VO> getAll();

	public Set<PMP_VO> getEmpsByDeptno(Integer pf_num);
	
	public List<String> getAllpmpnum();
}
