package com.commBook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.student.model.StudentVO;

public class CommBookJDBCDAO implements CommBookDAO_interface {

	private final static String driver = "oracle.jdbc.driver.OracleDriver";

	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO COMMUNICATION_BOOK (COMM_ID, ST_NUM, T_NUM, COMM_DATE, COMM_T_MSG, COMM_RES, COMM_P_MSG) VALUES (comm_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE COMMUNICATION_BOOK SET ST_NUM=?, T_NUM=?, COMM_DATE=?, COMM_T_MSG=?, COMM_RES=?, COMM_P_MSG=? WHERE COMM_ID = ?";

	private static final String GET_ONE_STMT = "SELECT COMM_ID, ST_NUM, T_NUM,to_char(COMM_DATE,'yyyy-mm-dd') COMM_DATE, COMM_T_MSG, COMM_RES, COMM_P_MSG FROM COMMUNICATION_BOOK WHERE COMM_ID = ?";

	private static final String GET_RECENT_DAY = "SELECT COMM_ID, ST_NUM, T_NUM,to_char(COMM_DATE,'yyyy-mm-dd') COMM_DATE, COMM_T_MSG, COMM_RES, COMM_P_MSG FROM COMMUNICATION_BOOK  WHERE ST_NUM = ? AND COMM_DATE <= TO_DATE(?,'yyyy-MM-dd') ORDER BY COMM_DATE DESC";

	private static final String GET_CLASS_NO_ByT_NUM = "SELECT CS_NUM FROM CLASS WHERE ((T_NUM_1=?) or (T_NUM_2=?))";

	private static final String GET_STUDENT_LIST = "SELECT * FROM STUDENT WHERE CS_NUM = ? ";
	
	private static final String GET_BY_COMMBOOK_DATE ="SELECT * FROM COMMUNICATION_BOOK WHERE to_char(COMM_DATE,'YYYY-MM-DD')Like ?";
	
	private static final String GET_Days = "Select to_char(comm_date,'yyyy-MM-dd') from Communication_book group by comm_date order by comm_date desc";

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		}
	}

	@Override
	public void insert(CommBookVO commBookVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, commBookVO.getSt_Num());
			pstmt.setString(2, commBookVO.getT_Num());
			pstmt.setDate(3, commBookVO.getComm_Date());
			pstmt.setString(4, commBookVO.getComm_T_Msg());
			pstmt.setString(5, commBookVO.getComm_Res());
			pstmt.setString(6, commBookVO.getComm_P_Msg());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured" + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured" + se.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured" + se.getMessage());
				}
			}
		}
	}

	@Override
	public void update(CommBookVO commBookVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, commBookVO.getSt_Num());
			pstmt.setString(2, commBookVO.getT_Num());
			pstmt.setDate(3, commBookVO.getComm_Date());
			pstmt.setString(4, commBookVO.getComm_T_Msg());
			pstmt.setString(5, commBookVO.getComm_Res());
			pstmt.setString(6, commBookVO.getComm_P_Msg());
			pstmt.setInt(7, commBookVO.getComm_Id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured" + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public CommBookVO findByPrimaryKey(Integer comm_Id) {

		CommBookVO commBookVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, comm_Id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				commBookVO = new CommBookVO();
				commBookVO.setComm_Id(rs.getInt("comm_Id"));
				commBookVO.setSt_Num(rs.getString("st_Num"));
				commBookVO.setT_Num(rs.getNString("t_Num"));
				commBookVO.setComm_Date(rs.getDate("comm_Date"));
				commBookVO.setComm_T_Msg(rs.getString("comm_T_Msg"));
				commBookVO.setComm_Res(rs.getString("comm_Res"));
				commBookVO.setComm_P_Msg(rs.getString("comm_P_Msg"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return commBookVO;
	}

	@Override
	public List<CommBookVO> getRecentCommBookVO(String st_Num, String dateStr) {

		List<CommBookVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_RECENT_DAY);

			pstmt.setString(1, st_Num);
			pstmt.setString(2, dateStr);

			rs = pstmt.executeQuery();
			list = new ArrayList<CommBookVO>();

			while (rs.next()) {

				CommBookVO commBookVO = new CommBookVO();
				commBookVO.setComm_Id(rs.getInt("comm_Id"));
				commBookVO.setSt_Num(rs.getString("st_Num"));
				commBookVO.setT_Num(rs.getNString("t_Num"));
				commBookVO.setComm_Date(rs.getDate("comm_Date"));
				commBookVO.setComm_T_Msg(rs.getString("comm_T_Msg"));
				commBookVO.setComm_Res(rs.getString("comm_Res"));
				commBookVO.setComm_P_Msg(rs.getString("comm_P_Msg"));
				list.add(commBookVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("Database error" + se.toString());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public String getClassNumByTNum(String t_num) {

		String cs_num = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_CLASS_NO_ByT_NUM);

			pstmt.setString(1, t_num);
			pstmt.setString(2, t_num);

			rs = pstmt.executeQuery();

			while (rs.next())
				cs_num = rs.getString("cs_num");

		} catch (SQLException se) {
			throw new RuntimeException("Database error" + se.toString());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return cs_num;
	}

	@Override
	public List<StudentVO> getStudentList(String cs_num) {
		List<StudentVO> list = null;
		StudentVO studentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STUDENT_LIST);
			pstmt.setString(1, cs_num);
			rs = pstmt.executeQuery();
			list = new ArrayList<StudentVO>();
			
			while(rs.next()) {
				studentVO = new StudentVO();
				studentVO.setSt_num(rs.getString("st_num"));
				studentVO.setCs_num(rs.getString("cs_num"));
				studentVO.setSt_name(rs.getString("st_name"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("Database error" + se.toString());
		} finally {
			if(con != null ) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<CommBookVO> getfindByDate(String comm_Date) {
		
		List<CommBookVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;			

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_COMMBOOK_DATE);

			pstmt.setString(1, comm_Date);

			rs = pstmt.executeQuery();
			list = new ArrayList<CommBookVO>();

			while (rs.next()) {

				CommBookVO commBookVO = new CommBookVO();
				commBookVO.setComm_Id(rs.getInt("comm_Id"));
				commBookVO.setSt_Num(rs.getString("st_Num"));
				commBookVO.setT_Num(rs.getNString("t_Num"));
				commBookVO.setComm_Date(rs.getDate("comm_Date"));
				commBookVO.setComm_T_Msg(rs.getString("comm_T_Msg"));
				commBookVO.setComm_Res(rs.getString("comm_Res"));
				commBookVO.setComm_P_Msg(rs.getString("comm_P_Msg"));
				list.add(commBookVO);			
			}
		} catch (SQLException se) {
			throw new RuntimeException("Database error" + se.toString());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public Set<String> showDays() {
		
		Set<String> set = new LinkedHashSet<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Days);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String date = rs.getString(1);
				set.add(date);
			}	
		} catch (SQLException se) {
			throw new RuntimeException("Datebase error" + se.toString());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}	
		return set;
	}
	
	
	
	
	
	public static void main(String[] args) {

		CommBookJDBCDAO dao = new CommBookJDBCDAO();

		// 新增
//		CommBookVO commBookVO1 = new CommBookVO();
//		commBookVO1.setSt_Num("S001");
//		commBookVO1.setT_Num("T002");
//		commBookVO1.setComm_Date(java.sql.Date.valueOf("2019-09-26"));
//		commBookVO1.setComm_T_Msg("參加DA102結訓，寶貝表現得很棒。");
//		commBookVO1.setComm_Res("感謝家長的配合");
//		commBookVO1.setComm_P_Msg("謝謝老師");
//		dao.insert(commBookVO1);

		// 修改
//		CommBookVO commBookVO2 = new CommBookVO();
//		commBookVO2.setComm_Id(1041);
//		commBookVO2.setSt_Num("S001");
//		commBookVO2.setT_Num("T002");
//		commBookVO2.setComm_Date(java.sql.Date.valueOf("2019-09-26"));
//		commBookVO2.setComm_T_Msg("參加DA102結訓，寶貝表現得很棒。");
//		commBookVO2.setComm_Res("感謝家長的配合^_^");
//		commBookVO2.setComm_P_Msg("感謝老師!!!!");
//		dao.update(commBookVO2);

		// 查詢
//		Integer comm_Id = 1041;
//		CommBookVO commBookVO3 = dao.findByPrimaryKey(comm_Id);
//		System.out.print(commBookVO3.getComm_Id() + ",");
//		System.out.print(commBookVO3.getSt_Num() + ",");
//		System.out.print(commBookVO3.getT_Num() + ",");
//		System.out.print(commBookVO3.getComm_Date() + ",");
//		System.out.print(commBookVO3.getComm_T_Msg() + ",");
//		System.out.print(commBookVO3.getComm_Res() + ",");
//		System.out.println(commBookVO3.getComm_P_Msg());
//		System.out.println("--------------------------------");

		// 查詢 一位幼兒 (S001) 的指定日期前(2019-09-25)聯絡簿資料
//		String st_Num = "S001";
//		String dateStr = "2019-09-25";
//
//		List<CommBookVO> list = dao.getRecentCommBookVO(st_Num, dateStr);
//		for (CommBookVO aCommBook : list) {
//			System.out.print(aCommBook.getComm_Id() + ",");
//			System.out.print(aCommBook.getSt_Num() + ",");
//			System.out.print(aCommBook.getT_Num() + ",");
//			System.out.print(aCommBook.getComm_Date() + ",");
//			System.out.print(aCommBook.getComm_T_Msg() + ",");
//			System.out.print(aCommBook.getComm_Res() + ",");
//			System.out.println(aCommBook.getComm_P_Msg());
//			System.out.println();
//		}

		String cs_num = "C001";
		
		List<StudentVO> list3 = dao.getStudentList(cs_num);
		for(StudentVO aStudent : list3) {
			System.out.print(aStudent.getSt_num()+ ",");
			System.out.print(aStudent.getCs_num()+ ",");
			System.out.print(aStudent.getSt_name());
			System.out.println();
		}
		

		// 指定日期查詢所有幼兒的聯絡簿資料(後台)
//		String comm_Date = "2019-09-25";
//
//		List<CommBookVO> list2 = dao.getfindByDate(comm_Date);
//		for (CommBookVO aCommBook : list2) {
//			System.out.print(aCommBook.getComm_Id() + ",");
//			System.out.print(aCommBook.getSt_Num() + ",");
//			System.out.print(aCommBook.getT_Num() + ",");
//			System.out.print(aCommBook.getComm_Date() + ",");
//			System.out.print(aCommBook.getComm_T_Msg() + ",");
//			System.out.print(aCommBook.getComm_Res() + ",");
//			System.out.println(aCommBook.getComm_P_Msg());
//			System.out.println();
//		}

	}

}
