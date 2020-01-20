package com.teacherLeave.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherLeaveJDBCDAO implements TeacherLeaveDAO_interface{
	
	private final static String  driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO TEACHER_LEAVE (TL_ID, T_NUM, TL_SDATE, TL_EDATE, TL_TYPE, TL_REASON, TL_APP_DATE, TL_STATUS) VALUES (tleave_seq.NEXTVAL, ?, ?, ?, ?, ?, sysdate, 0)";

	private static final String UPDATE = 	
			"UPDATE TEACHER_LEAVE SET  T_NUM=?, TL_SDATE=?, TL_EDATE=?, TL_TYPE=?, TL_REASON=?, TL_APP_DATE=?, TL_STATUS=? WHERE TL_ID = ?";

	private static final String DELETE =
			"DELETE FROM TEACHER_LEAVE  WHERE TL_ID = ?";

	private static final String GET_ONE_STMT = 
			"SELECT TL_ID, T_NUM, to_char(TL_SDATE,'yyyy-mm-dd')TL_SDATE, to_char(TL_EDATE,'yyyy-mm-dd')TL_EDATE, TL_TYPE, TL_REASON, to_char(TL_APP_DATE,'yyyy-mm-dd')TL_APP_DATE, TL_STATUS FROM TEACHER_LEAVE WHERE TL_ID = ? ";

	private static final String GET_ALL_STMT = 
			"SELECT TL_ID, T_NUM, to_char(TL_SDATE,'yyyy-mm-dd')TL_SDATE, to_char(TL_EDATE,'yyyy-mm-dd')TL_EDATE, TL_TYPE, TL_REASON, to_char(TL_APP_DATE,'yyyy-mm-dd')TL_APP_DATE, TL_STATUS FROM TEACHER_LEAVE ORDER BY TL_APP_DATE DESC";
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException ("Couldn't load database driver. " + e.getMessage());
		}
	}
	
	
	@Override
	public void insert(TeacherLeaveVO teacherLeaveVO) {

		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, teacherLeaveVO.getT_Num());
			pstmt.setDate(2, teacherLeaveVO.getTl_Sdate());
			pstmt.setDate(3, teacherLeaveVO.getTl_Edate());
			pstmt.setInt(4, teacherLeaveVO.getTl_Type());
			pstmt.setString(5, teacherLeaveVO.getTl_Reason());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured" + se.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					throw new RuntimeException ("A database error occured" + se.getMessage());
				}	
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					throw new RuntimeException ("A database error occured" + se.getMessage());
				}
			}		
		}
	}

	@Override
	public void update(TeacherLeaveVO teacherLeaveVO) {

		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
				
			pstmt.setString(1, teacherLeaveVO.getT_Num());
			pstmt.setDate(2, teacherLeaveVO.getTl_Sdate());
			pstmt.setDate(3, teacherLeaveVO.getTl_Edate());
			pstmt.setInt(4, teacherLeaveVO.getTl_Type());
			pstmt.setString(5, teacherLeaveVO.getTl_Reason());
			pstmt.setDate(6, teacherLeaveVO.getTl_App_Date());
			pstmt.setInt(7, teacherLeaveVO.getTl_Status());
			pstmt.setInt(8, teacherLeaveVO.getTl_Id());
			
			pstmt.executeUpdate();				

		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured" + se.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					throw new RuntimeException ("A database error occured" + se.getMessage());
				}	
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					throw new RuntimeException ("A database error occured" + se.getMessage());
				}
			}		
		}
		
	}

	@Override
	public void delete(Integer tl_Id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, tl_Id);
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured" + se.getMessage());
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public TeacherLeaveVO findByPrimaryKey(Integer tl_Id) {
		
		TeacherLeaveVO teacherLeaveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, tl_Id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				teacherLeaveVO = new TeacherLeaveVO();
				teacherLeaveVO.setTl_Id(rs.getInt("tl_Id"));
				teacherLeaveVO.setT_Num(rs.getNString("t_Num"));
				teacherLeaveVO.setTl_Sdate(rs.getDate("tl_Sdate"));
				teacherLeaveVO.setTl_Edate(rs.getDate("tl_Edate"));
				teacherLeaveVO.setTl_Type(rs.getInt("tl_Type"));
				teacherLeaveVO.setTl_Reason(rs.getString("tl_Reason"));
				teacherLeaveVO.setTl_App_Date(rs.getDate("tl_App_Date"));
				teacherLeaveVO.setTl_Status(rs.getInt("tl_Status"));	
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
		return teacherLeaveVO;
	}

	@Override
	public List<TeacherLeaveVO> getAll() {
		List<TeacherLeaveVO> list = new ArrayList<TeacherLeaveVO>();
		TeacherLeaveVO teacherLeaveVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				teacherLeaveVO = new TeacherLeaveVO();
				teacherLeaveVO.setTl_Id(rs.getInt("tl_Id"));
				teacherLeaveVO.setT_Num(rs.getNString("t_Num"));
				teacherLeaveVO.setTl_Sdate(rs.getDate("tl_Sdate"));
				teacherLeaveVO.setTl_Edate(rs.getDate("tl_Edate"));
				teacherLeaveVO.setTl_Type(rs.getInt("tl_Type"));
				teacherLeaveVO.setTl_Reason(rs.getString("tl_Reason"));
				teacherLeaveVO.setTl_App_Date(rs.getDate("tl_App_Date"));
				teacherLeaveVO.setTl_Status(rs.getInt("tl_Status"));
				list.add(teacherLeaveVO);
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
		return list;
	}

	public static void main (String[]args) {
		
		TeacherLeaveJDBCDAO dao = new TeacherLeaveJDBCDAO();
		
		// 新增
		TeacherLeaveVO teacherLeaveVO1 = new TeacherLeaveVO();
		teacherLeaveVO1.setT_Num("T006");
		teacherLeaveVO1.setTl_Sdate(java.sql.Date.valueOf("2019-10-01"));
		teacherLeaveVO1.setTl_Edate(java.sql.Date.valueOf("2019-10-03"));
		teacherLeaveVO1.setTl_Type(1); // 假別: 0-病假 1-事假 2-喪假 3-其它	
		teacherLeaveVO1.setTl_Reason("就是想放假");
//		teacherLeaveVO1.setTl_Status(0);// 狀態: 0-送出 1-成功2-退回		
		dao.insert(teacherLeaveVO1);
		
		// 修改
		TeacherLeaveVO teacherLeaveVO2 = new TeacherLeaveVO();
		teacherLeaveVO2.setTl_Id(1011);
		teacherLeaveVO2.setT_Num("T006");
		teacherLeaveVO2.setTl_Sdate(java.sql.Date.valueOf("2019-10-01"));
		teacherLeaveVO2.setTl_Edate(java.sql.Date.valueOf("2019-10-03"));
		teacherLeaveVO2.setTl_Type(1); // 假別: 0-病假 1-事假 2-喪假 3-其它	
		teacherLeaveVO2.setTl_Reason("不給假就搗蛋");
		teacherLeaveVO2.setTl_App_Date(java.sql.Date.valueOf("2019-09-26"));
		teacherLeaveVO2.setTl_Status(0);// 狀態: 0-送出 1-成功2-退回		
		dao.update(teacherLeaveVO2);
		
		// 刪除
		dao.delete(1011);
		
		// 查詢一筆
		Integer tl_Id = 1001;
		TeacherLeaveVO teacherLeaveVO3 = dao.findByPrimaryKey(tl_Id);
		System.out.print(teacherLeaveVO3.getTl_Id() + ",");
		System.out.print(teacherLeaveVO3.getT_Num() + ",");
		System.out.print(teacherLeaveVO3.getTl_Sdate() + ",");
		System.out.print(teacherLeaveVO3.getTl_Edate() + ",");
		System.out.print(teacherLeaveVO3.getTl_Type() + ",");
		System.out.print(teacherLeaveVO3.getTl_Reason() + ",");
		System.out.print(teacherLeaveVO3.getTl_App_Date() + ",");
		System.out.println(teacherLeaveVO3.getTl_Status());
		System.out.println("---------------------");
		
		// 查詢全部
		List<TeacherLeaveVO> list = dao.getAll();
		for(TeacherLeaveVO aTeacherLeave : list) {
			System.out.print(aTeacherLeave.getTl_Id() + ",");
			System.out.print(aTeacherLeave.getT_Num() + ",");
			System.out.print(aTeacherLeave.getTl_Sdate() + ",");
			System.out.print(aTeacherLeave.getTl_Edate() + ",");
			System.out.print(aTeacherLeave.getTl_Type() + ",");
			System.out.print(aTeacherLeave.getTl_Reason() + ",");
			System.out.print(aTeacherLeave.getTl_App_Date() + ",");
			System.out.println(aTeacherLeave.getTl_Status());
			System.out.println();
		}
	}
}
