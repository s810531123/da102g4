package com.paymentlist.model;

import java.util.List;
import java.util.Set;

public interface PMLDAO_interface {
	public void insert(PML_VO pmlVO);

	public void update(PML_VO pmlVO);

	public void delete(Integer detal_num);

	public PML_VO findByPrimaryKey(Integer detal_num);

	public List<PML_VO> getAll();

	public Set<PML_VO> getEmpsByDeptno(Integer pf_num);
}
