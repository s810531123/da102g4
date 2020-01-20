package com.commBook.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.student.model.StudentVO;

public class CommBookService {

	private CommBookDAO_interface dao;

	public CommBookService() {
		dao = new CommBookJNDIDAO();
	}

	//利用 t_num 找到屬於這個老師的學生名單
	public List<StudentVO> getStudentList(String t_num){
		
		// 找出老師所屬的班級編號
		String cs_num = dao.getClassNumByTNum(t_num);
	
		// 使用班級編號找出班級內所有學生資料 (包含學號、名字)
		List<StudentVO> studentList = null; //studentList 學生名冊/點名簿
		if (cs_num == null) {		
			throw new RuntimeException("老師請先登入");// 老師找不到班級(例如:園長、代課老師)	
		} else {
			studentList = dao.getStudentList(cs_num);
		}
		
		return studentList;
	}

	public CommBookVO addCommBook(String st_Num, String t_Num, java.sql.Date comm_Date, String comm_T_Msg,
			String comm_Res, String comm_P_Msg) {

		CommBookVO commBookVO = new CommBookVO();

		commBookVO.setSt_Num(st_Num);
		commBookVO.setT_Num(t_Num);
		commBookVO.setComm_Date(comm_Date);
		commBookVO.setComm_T_Msg(comm_T_Msg);
		commBookVO.setComm_Res(comm_Res);
		commBookVO.setComm_P_Msg(comm_P_Msg);
		dao.insert(commBookVO);

		return commBookVO;
	}
	
	// 改寫老師的addCommBook版本,因為實際上老師只需要填寫指定學生、日期、內容三個要素....所以從JSP送出的資料比真正insert的還少，但為了節省資源~重複利用!!
	public CommBookVO addCommBookVO(String st_Num, String t_Num, java.sql.Date comm_Date, String comm_T_Msg) {
		CommBookService commBookSvc = new CommBookService();
		String comm_Res = null;
		String comm_P_Msg = null;
		CommBookVO commBookVO= commBookSvc.addCommBook(st_Num, t_Num, comm_Date, comm_T_Msg, comm_Res, comm_P_Msg);
		return commBookVO;
	}
	
	//改寫成群體發話版本
	public void setAllStudentCommBook(String t_num, java.sql.Date comm_Date, String comm_T_Msg) {				
		CommBookService commBookSvc = new CommBookService();

		List<StudentVO> studentList = commBookSvc.getStudentList(t_num);
	
		for(StudentVO studentVO:studentList) {
			String st_Num = studentVO.getSt_num();		//取出 studentList內的每個學生物件，再取出裡面的學生學號
			CommBookVO commBookVO = commBookSvc.addCommBookVO(st_Num, t_num, comm_Date, comm_T_Msg);
		}
	}	

	public CommBookVO updateCommbookVO(Integer comm_Id, String st_Num, String t_Num, java.sql.Date comm_Date,
			String comm_T_Msg, String comm_Res, String comm_P_Msg) {

		CommBookVO commBookVO = new CommBookVO();

		commBookVO.setComm_Id(comm_Id);
		commBookVO.setSt_Num(st_Num);
		commBookVO.setT_Num(t_Num);
		commBookVO.setComm_Date(comm_Date);
		commBookVO.setComm_T_Msg(comm_T_Msg);
		commBookVO.setComm_Res(comm_Res);
		commBookVO.setComm_P_Msg(comm_P_Msg);
		dao.update(commBookVO);

		return commBookVO;
	}
		
	// 改寫前台的updateCommbookSt版本,因為實際上前台家長只能更改comm_P_Msg	   
	public CommBookVO updateCommbookSt(Integer comm_Id, String comm_P_Msg) {
		
		CommBookVO	commBookVO = null;
		CommBookService commBookSvc = new CommBookService();
		commBookVO = commBookSvc.getOneCommBookVO(comm_Id);
		
		commBookVO.setComm_P_Msg(comm_P_Msg);
		dao.update(commBookVO);
			
		return commBookVO;		
	}
	
	// 改寫後台的comm_Res版本,,因為實際上後台老師只能更改comm_Res	   
	public CommBookVO updateCommBookTRes(Integer comm_Id, String comm_Res) {
		
		CommBookVO commBookVO = null;
		CommBookService commBookSvc = new CommBookService();
		commBookVO = commBookSvc.getOneCommBookVO(comm_Id);
		
		commBookVO.setComm_Res(comm_Res);
		dao.update(commBookVO);
		
		return commBookVO;	
	}
	
	// 改寫後台的comm_T_Msg群體修改
	public CommBookVO updateCommBookMsg(String t_Num, String comm_Date, String comm_T_Msg) {
		
		CommBookVO commBookVO = null;
		CommBookService commBookSvc = new CommBookService();
		List<StudentVO> studentList = commBookSvc.getStudentList(t_Num);
		for(StudentVO studentVO:studentList) {
			String st_Num = studentVO.getSt_num();
			//DAO裡面的getRecentCommBookVO用toDate方式，將日期(String)轉換成Date物件，所以JSP、Servlet、Service都只能送String給DAO的getRecentCommBookVO
			List<CommBookVO> list = getRecentCommBookVO(st_Num, comm_Date);
			commBookVO = list.get(0);
			commBookVO.setComm_T_Msg(comm_T_Msg);
			dao.update(commBookVO);
		}
		return commBookVO;
	}

	public CommBookVO getOneCommBookVO(Integer comm_Id) {
		return dao.findByPrimaryKey(comm_Id);
	}

	public List<CommBookVO> getRecentCommBookVO(String st_Num, String dateStr) {
		List<CommBookVO> list = dao.getRecentCommBookVO(st_Num, dateStr);
		return list;
	}

	public Map<StudentVO, List<CommBookVO>> getAllStudentCommBook(String t_num, String dateStr) {		
		Map<StudentVO, List<CommBookVO>> allStudentCommBook = null;
		
		CommBookService commBookSvc = new CommBookService();
		List<StudentVO> studentList = commBookSvc.getStudentList(t_num);//用老師編號找到全部學生
		
		allStudentCommBook = new LinkedHashMap<StudentVO, List<CommBookVO>>();
		
		for(StudentVO studentVO:studentList) {
			String st_num = studentVO.getSt_num();		//取出 studentList內的每個學生物件，再取出裡面的學生學號
			List<CommBookVO> list = getRecentCommBookVO(st_num, dateStr);		//用學生學號跟日期資訊，找尋該學生最接近的聯絡簿list
			allStudentCommBook.put(studentVO, list);   //將學生物件當作key，學生的聯絡簿當作 value，放在MAP內
		}
		
		return allStudentCommBook;
	}	
	
	public List<CommBookVO> getfindByDate(String comm_Date){
		List<CommBookVO> list = dao.getfindByDate(comm_Date);		
		return list;
	}
	
	public Set<String> getCommBookDays(){
		Set<String> allCommBookDays = dao.showDays();		
		return allCommBookDays;
	}
}
