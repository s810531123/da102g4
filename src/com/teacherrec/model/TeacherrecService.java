package com.teacherrec.model;

import java.util.*;

public class TeacherrecService {

	private TeacherrecDAO_interface dao;

	public TeacherrecService() {
		dao = new TeacherrecJDBCDAO();
	}

	public TeacherrecVO addTercherrec(String t_Num, java.sql.Timestamp on_Time, java.sql.Timestamp off_Time) {

		TeacherrecVO trecVO = new TeacherrecVO();

		trecVO.setT_Num(t_Num);
		trecVO.setOn_Time(on_Time);
		trecVO.setOff_Time(off_Time);

		dao.insert(trecVO);

		return trecVO;
	}

	public List<TeacherrecVO> getAll() {
		return dao.getAll();
	}

	public void deleteTeacherrec(Integer r_Num) {
		dao.delete(r_Num);
	}

	public TeacherrecVO updateTeacherrec(Integer r_Num, String t_Num, java.sql.Timestamp on_Time,
			java.sql.Timestamp off_Time) {
		TeacherrecVO trecVO = new TeacherrecVO();
		trecVO.setR_Num(r_Num);
		trecVO.setT_Num(t_Num);
		trecVO.setOn_Time(on_Time);
		trecVO.setOff_Time(off_Time);
		dao.update(trecVO);
		return trecVO;
	}

	public List<TeacherrecVO> getTrecByT_num(String T_Num) {
		return dao.getTrecByT_num(T_Num);
	}

}
