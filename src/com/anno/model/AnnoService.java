package com.anno.model;

import java.sql.Date;
import java.util.List;

public class AnnoService {
	
	private AnnoDAO_interface dao;
	
	public AnnoService() {
		dao =  new AnnoJDBCDAO();
	}
	public AnnoVO addAnno(String anno_cla,String anno_title,String anno_text,java.sql.Date anno_date) {
		 AnnoVO annoVO = new AnnoVO();
		 
		 annoVO.setAnno_cla(anno_cla);
		 annoVO.setAnno_title(anno_title);
		 annoVO.setAnno_title(anno_title);
		 annoVO.setAnno_date(anno_date);
		 dao.insert(annoVO);
		 
		 return annoVO;
	} 
	
	public AnnoVO updateAnno(Integer anno_no,String anno_cla,String anno_title,String anno_text,java.sql.Date anno_date ) {
		AnnoVO annoVO = new AnnoVO();
		 
		 annoVO.setAnno_no(anno_no);
		 annoVO.setAnno_cla(anno_cla);
		 annoVO.setAnno_title(anno_title);
		 annoVO.setAnno_title(anno_title);
		 annoVO.setAnno_date(anno_date);
		 dao.update(annoVO);
		 
		 return annoVO;
		
	}
	
	public void deleteAnno(Integer anno_no) {
		dao.delete(anno_no);
	}
	
	public AnnoVO getOneAnno(Integer anno_no) {
		return dao.findByPrimaryKey(anno_no);
		
	}
	
	public List<AnnoVO> getAll(){
		
		return dao.getAll();
	}

}
